package ar.com.kecat.controllers;

import ar.com.kecat.forms.CobranzaForm;
import ar.com.kecat.models.Cobranza;
import ar.com.kecat.models.Liquidacion;
import ar.com.kecat.repositories.CobranzaRepository;
import ar.com.kecat.repositories.LiquidacionRepository;
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
import java.math.BigDecimal;

@RepositoryRestController
public class CobranzaController {

    @Autowired
    private CobranzaRepository cobranzaRepository;
    @Autowired
    private LiquidacionRepository liquidacionRepository;

    @Autowired
    private LocalValidatorFactoryBean validator;
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @PostMapping("/liquidaciones/{idLiquidacion}/cobranzas")
    @ResponseBody ResponseEntity postCobranza(@PathVariable Long idLiquidacion, @Valid @RequestBody CobranzaForm cobranzaForm){
        Liquidacion liquidacion = liquidacionRepository.findOne(idLiquidacion);
        if(liquidacion == null || liquidacion.getPagada()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Liquidación no válida o ya pagada");

        BigDecimal saldoPendiente = liquidacion.calcularSaldoPendiente();
        BigDecimal montoCobranza = cobranzaForm.getMonto();
        if(saldoPendiente.compareTo(cobranzaForm.getMonto()) < 0)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El monto debe ser menor al saldo pendiente: $" + saldoPendiente);

        Cobranza cobranza = Cobranza.Builder.create()
                .withFecha(cobranzaForm.getFecha())
                .withLiquidacion(liquidacion)
                .withMonto(montoCobranza)
                .build();
        cobranzaRepository.save(cobranza);
        //Verifico si la liquidación ya quedó pagada
        if(saldoPendiente.compareTo(montoCobranza) == 0) {
            liquidacion.setPagada(true);
            liquidacionRepository.save(liquidacion);
        }
        return ResponseEntity.status(HttpStatus.OK).body(cobranza);
    }


}
