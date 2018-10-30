package ar.com.kecat.controllers;

import ar.com.kecat.forms.ConsumoEnteroForm;
import ar.com.kecat.models.ConsumoEntero;
import ar.com.kecat.models.Establecimiento;
import ar.com.kecat.models.Tarjeta;
import ar.com.kecat.repositories.EstablecimientoRepository;
import ar.com.kecat.repositories.TarjetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;


@RepositoryRestController
public class ConsumoEnteroController {

    @Autowired
    private EstablecimientoRepository establecimientoRepository;

    @Autowired
    private TarjetaRepository tarjetaRepository;

    @PostMapping("/tarjetas/{idTarjeta}/consumosEnteros")
    @ResponseBody ResponseEntity<Tarjeta> postConsumoEntero(@PathVariable Long idTarjeta, @RequestBody ConsumoEnteroForm consumoEnteroForm){
        Establecimiento establecimiento = Optional.ofNullable(consumoEnteroForm.getIdEstablecimiento())
                .map(id -> establecimientoRepository.findOne(id)).orElse(null);
        if(establecimiento != null){
            Tarjeta tarjeta = tarjetaRepository.findOne(idTarjeta);
            //El código de seguridad debe coincidir con el de la tarjeta almacenada
            if(tarjeta != null && consumoEnteroForm.getCodigoSeguridad().compareTo(tarjeta.getCodigoSeguridad()) == 0){
                //FIXME: Traerme la liquidacion actual de la tarjeta y agregarla al with
                ConsumoEntero consumoEntero = ConsumoEntero.Builder.create()
                        .withDescripcion(consumoEnteroForm.getDescripcion())
                        .withEstablecimiento(establecimiento)
                        .withFecha(consumoEnteroForm.getFecha())
                        .withMontoTotal(consumoEnteroForm.getMontoTotal())
                        .build();

            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}