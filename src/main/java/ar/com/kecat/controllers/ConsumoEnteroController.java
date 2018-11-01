package ar.com.kecat.controllers;

import ar.com.kecat.forms.ConsumoEnteroForm;
import ar.com.kecat.models.ConsumoEntero;
import ar.com.kecat.models.Establecimiento;
import ar.com.kecat.models.Liquidacion;
import ar.com.kecat.models.Tarjeta;
import ar.com.kecat.repositories.ConsumoEnteroRepository;
import ar.com.kecat.repositories.EstablecimientoRepository;
import ar.com.kecat.repositories.LiquidacionRepository;
import ar.com.kecat.repositories.TarjetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Optional;


@RepositoryRestController
public class ConsumoEnteroController {

    @Autowired
    private ConsumoEnteroRepository consumoEnteroRepository;
    @Autowired
    private EstablecimientoRepository establecimientoRepository;
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

    @PostMapping("/tarjetas/{nroTarjeta}/consumosEnteros")
    @ResponseBody ResponseEntity postConsumoEntero(@PathVariable String nroTarjeta, @Valid @RequestBody ConsumoEnteroForm consumoEnteroForm){
        Establecimiento establecimiento = Optional.ofNullable(consumoEnteroForm.getIdEstablecimiento())
                .map(id -> establecimientoRepository.findOne(id)).orElse(null);
        if(establecimiento == null) ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Establecimiento no válido");

        Tarjeta tarjeta = tarjetaRepository.findByNroTarjeta(nroTarjeta);
        //El código de seguridad debe coincidir con el de la tarjeta almacenada
        if(tarjeta == null || consumoEnteroForm.getCodigoSeguridad().compareTo(tarjeta.getCodigoSeguridad()) != 0)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tarjeta o código de seguridad inválidos");

        if(!tarjeta.alcanzaLimiteDisponible(consumoEnteroForm.getMonto()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El limite disponible de la tarjeta no es suficiente para realizar el pago");

        Liquidacion liquidacion = liquidacionRepository.findByEstadoAndTarjeta(Liquidacion.Estado.ABIERTA, tarjeta);
        if(liquidacion == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay liquidación asociada, consulte a la empresa");

        ConsumoEntero consumoEntero = ConsumoEntero.Builder.create()
                .withDescripcion(consumoEnteroForm.getDescripcion())
                .withEstablecimiento(establecimiento)
                .withFecha(consumoEnteroForm.getFecha())
                .withMonto(consumoEnteroForm.getMonto())
                .withLiquidacion(liquidacion)
                .build();
        consumoEnteroRepository.save(consumoEntero);
        return ResponseEntity.status(HttpStatus.OK).body(consumoEntero);
    }

}
