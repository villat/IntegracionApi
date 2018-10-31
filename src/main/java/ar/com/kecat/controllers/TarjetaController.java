package ar.com.kecat.controllers;

import ar.com.kecat.forms.TarjetaForm;
import ar.com.kecat.helpers.DateUtils;
import ar.com.kecat.models.Cliente;
import ar.com.kecat.models.Liquidacion;
import ar.com.kecat.models.Tarjeta;
import ar.com.kecat.repositories.ClienteRepository;
import ar.com.kecat.repositories.LiquidacionRepository;
import ar.com.kecat.repositories.TarjetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@RepositoryRestController
public class TarjetaController {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private LiquidacionRepository liquidacionRepository;
    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Autowired
    private LocalValidatorFactoryBean validator;
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @PostMapping("/tarjetas")
    @ResponseBody ResponseEntity postTarjeta(@Valid @RequestBody TarjetaForm tarjetaForm){
        Cliente cliente = Optional.ofNullable(tarjetaForm.getIdCliente())
                .map(id -> clienteRepository.findOne(id)).orElse(null);
        if(cliente != null){
            Tarjeta tarjeta = Tarjeta.Builder.create()
                    .withCliente(cliente)
                    .withNroTarjeta(tarjetaForm.getNroTarjeta())
                    .withCategoria(tarjetaForm.getCategoria())
                    .withCodigoSeguridad(tarjetaForm.getCodigoSeguridad())
                    .withDiaCierre(tarjetaForm.getDiaCierre())
                    .withFechaValidaDesde(tarjetaForm.getFechaValidaDesde())
                    .withFechaValidaHasta(tarjetaForm.getFechaValidaHasta())
                    .withMontoLimite(tarjetaForm.getMontoLimite())
                    .build();
            tarjetaRepository.save(tarjeta);

            //Se calculan las fechas necesarias para la liquidacion
            int diaCierre = tarjeta.getDiaCierre().intValue();
            Date fechaCierre = DateUtils.getDateOneMonthLaterByDay(diaCierre);
            Date fechaVencimiento = DateUtils.getDateOneMonthAnd14DaysLaterByDay(diaCierre);

            //Se inicializa la primer liquidación
            Liquidacion liquidacion = Liquidacion.Builder.create()
                    .withEstado(Liquidacion.Estado.ABIERTA)
                    .withTarjeta(tarjeta)
                    .withFechaCierre(fechaCierre)
                    .withFechaVencimiento(fechaVencimiento)
                    .build();
            liquidacionRepository.save(liquidacion);

            return ResponseEntity.status(HttpStatus.OK).body(tarjeta);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente no válido");
    }


}
