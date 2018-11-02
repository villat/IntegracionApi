package ar.com.kecat.controllers;

import ar.com.kecat.forms.ConsumoEnCuotasForm;
import ar.com.kecat.models.ConsumoEnCuotas;
import ar.com.kecat.models.Cuota;
import ar.com.kecat.models.Establecimiento;
import ar.com.kecat.models.Liquidacion;
import ar.com.kecat.models.Tarjeta;
import ar.com.kecat.repositories.ConsumoEnCuotasRepository;
import ar.com.kecat.repositories.CuotasRepository;
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
public class ConsumoEnCuotasController {

    @Autowired
    private ConsumoEnCuotasRepository consumoEnCuotasRepository;
    @Autowired
    private CuotasRepository cuotasRepository;
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

    @PostMapping("/tarjetas/{nroTarjeta}/consumosEnCuotas")
    @ResponseBody ResponseEntity postConsumoEnCuotas(@PathVariable String nroTarjeta, @Valid @RequestBody ConsumoEnCuotasForm consumoEnCuotasForm){
        Establecimiento establecimiento = Optional.ofNullable(consumoEnCuotasForm.getIdEstablecimiento())
                .map(id -> establecimientoRepository.findOne(id)).orElse(null);
        if(establecimiento == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Establecimiento no válido");

        Tarjeta tarjeta = tarjetaRepository.findByNroTarjeta(nroTarjeta);
        //El código de seguridad debe coincidir con el de la tarjeta almacenada
        if(tarjeta == null || consumoEnCuotasForm.getCodigoSeguridad().compareTo(tarjeta.getCodigoSeguridad()) != 0)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tarjeta o código de seguridad inválidos");

        if(!tarjeta.alcanzaLimiteDisponible(consumoEnCuotasForm.getMonto()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El limite disponible de la tarjeta no es suficiente para realizar el pago");

        Liquidacion liquidacion = liquidacionRepository.findByEstadoAndTarjeta(Liquidacion.Estado.ABIERTA, tarjeta);
        if(liquidacion == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay liquidación asociada, consulte a la empresa");

        //Generamos el consumo en cuotas
        ConsumoEnCuotas consumoEnCuotas = ConsumoEnCuotas.Builder.create()
                .withDescripcion(consumoEnCuotasForm.getDescripcion())
                .withEstablecimiento(establecimiento)
                .withFecha(consumoEnCuotasForm.getFecha())
                .withMonto(consumoEnCuotasForm.getMonto())
                .withLiquidacion(liquidacion)
                .withCantCuotas(consumoEnCuotasForm.getCantCuotas())
                .withInteres(consumoEnCuotasForm.getInteres())
                .build();
        consumoEnCuotasRepository.save(consumoEnCuotas);

        //Generamos la primer cuota
        Cuota cuota = Cuota.Builder.create()
                .withMontoCuota(consumoEnCuotas.calcularMontoCuota())
                .withNumeroDeCuota(1)
                .withConsumoEnCuotas(consumoEnCuotas)
                .withLiquidacion(liquidacion)
                .build();
        cuotasRepository.save(cuota);

        return ResponseEntity.status(HttpStatus.OK).body(consumoEnCuotas);
    }


}
