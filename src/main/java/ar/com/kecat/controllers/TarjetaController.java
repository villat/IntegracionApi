package ar.com.kecat.controllers;

import ar.com.kecat.forms.TarjetaForm;
import ar.com.kecat.models.Cliente;
import ar.com.kecat.models.Tarjeta;
import ar.com.kecat.repositories.ClienteRepository;
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
import java.util.Optional;

@RepositoryRestController
public class TarjetaController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Autowired
    private LocalValidatorFactoryBean validator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @PostMapping("/tarjetas")
    @ResponseBody ResponseEntity<Tarjeta> postTarjeta(@Valid @RequestBody TarjetaForm tarjetaForm){
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
            return new ResponseEntity<>(tarjeta, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
