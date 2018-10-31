package ar.com.kecat.tasks;

import ar.com.kecat.helpers.DateUtils;
import ar.com.kecat.models.Liquidacion;
import ar.com.kecat.repositories.LiquidacionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private LiquidacionRepository liquidacionRepository;

    //Se ejecuta todos los días a la medianoche
    @Transactional
    @Scheduled(cron = "0 0 0 * * ?")
    public void reportCurrentTime() {
        log.info("Procesamiento de liquidaciones en curso {}", dateFormat.format(new Date()));

        final Date hoy = new Date();
        liquidacionRepository.findByEstado(Liquidacion.Estado.ABIERTA).stream()
                .filter(liquidacion -> liquidacion.getFechaCierre().before(hoy))
                .forEach(liquidacion -> {
                    liquidacion.setEstado(Liquidacion.Estado.CERRADA);
                    Liquidacion nuevaLiquidacion = Liquidacion.Builder.create()
                            .withFechaVencimiento(DateUtils.getDateOneMonthLaterByDate(liquidacion.getFechaVencimiento()))
                            .withFechaCierre(DateUtils.getDateOneMonthLaterByDate(liquidacion.getFechaCierre()))
                            .withTarjeta(liquidacion.getTarjeta())
                            .withEstado(Liquidacion.Estado.ABIERTA)
                            .build();
                    liquidacionRepository.save(nuevaLiquidacion);
                });

        log.info("Procesamiento de liquidaciones finalizado {}", dateFormat.format(new Date()));
    }
}