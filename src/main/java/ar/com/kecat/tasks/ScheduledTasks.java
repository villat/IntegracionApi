package ar.com.kecat.tasks;

import ar.com.kecat.dtos.TransferenciaDTO;
import ar.com.kecat.helpers.DateUtils;
import ar.com.kecat.models.Cliente;
import ar.com.kecat.models.Cuota;
import ar.com.kecat.models.Liquidacion;
import ar.com.kecat.repositories.CuotasRepository;
import ar.com.kecat.repositories.LiquidacionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static final String URI = "http://192.168.216.34.8080/api/transferencia";
    private static final String CBU_NUESTRO = "444455555666666";

    @Autowired
    private LiquidacionRepository liquidacionRepository;
    @Autowired
    private CuotasRepository cuotasRepository;

    //Se ejecuta todos los días a la medianoche
    @Transactional
    @Scheduled(cron = "0 0 0 * * ?")
    public void procesarLiquidaciones() {
        log.info("Procesamiento de liquidaciones en curso {}", dateFormat.format(new Date()));

        final Date hoy = new Date();
        final RestTemplate restTemplate = new RestTemplate();
        final ObjectMapper jsonMapper = new ObjectMapper();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        liquidacionRepository.findByEstado(Liquidacion.Estado.ABIERTA).stream()
                .filter(liquidacion -> liquidacion.getFechaCierre().before(hoy))
                .forEach(liquidacion -> {
                    //Le avisamos al banco que le debite al cliente
                    Cliente cliente = liquidacion.getTarjeta().getCliente();
                    TransferenciaDTO transferenciaDTO = TransferenciaDTO.Builder.create()
                            .withDescripcion("Tarjeta de " + cliente.getNombreCompleto())
                            .withDestino(CBU_NUESTRO)
                            .withOrigen(cliente.getCbu())
                            .withMonto(liquidacion.calcularSaldoADebitar())
                            .withFecha(hoy)
                            .build();
                    try {
                        String transferenciaJson = jsonMapper.writeValueAsString(transferenciaDTO);
                        HttpEntity<String> entity = new HttpEntity<>(transferenciaJson, headers);
                        ResponseEntity<String> response = restTemplate.exchange(URI, HttpMethod.POST, entity, String.class);
                        log.info(response.toString());
                    } catch (JsonProcessingException e) {
                        log.error("No fue posible informarle al banco sobre la liquidacion: {}", liquidacion.getId());
                    }

                    //Cerramos la liquidacion anterior
                    liquidacion.setEstado(Liquidacion.Estado.CERRADA);

                    Liquidacion nuevaLiquidacion = Liquidacion.Builder.create()
                            .withFechaVencimiento(DateUtils.getDateOneMonthLaterByDate(liquidacion.getFechaVencimiento()))
                            .withFechaCierre(DateUtils.getDateOneMonthLaterByDate(liquidacion.getFechaCierre()))
                            .withTarjeta(liquidacion.getTarjeta())
                            .withEstado(Liquidacion.Estado.ABIERTA)
                            .build();
                    liquidacionRepository.save(nuevaLiquidacion);

                    //Generamos las nuevas cuotas en base a las que aun tienen cuotas pendientes
                    List<Cuota> cuotas = liquidacion.generarNuevasCuotas(nuevaLiquidacion);
                    cuotasRepository.save(cuotas);
                });

        log.info("Procesamiento de liquidaciones finalizado {}", dateFormat.format(new Date()));
    }
}