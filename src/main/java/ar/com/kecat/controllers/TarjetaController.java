package ar.com.kecat.controllers;

import ar.com.kecat.dto.TarjetaDTO;
import ar.com.kecat.models.Cliente;
import ar.com.kecat.models.Tarjeta;
import ar.com.kecat.repositories.ClienteRepository;
import ar.com.kecat.repositories.TarjetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


@RepositoryRestController
public class TarjetaController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Transactional
    @PostMapping("/tarjetas")
    @ResponseBody ResponseEntity<Tarjeta> postTarjeta(@RequestBody TarjetaDTO tarjetaDTO){
        Cliente cliente = clienteRepository.findOne(tarjetaDTO.getClienteId());
        if(cliente != null){
            Tarjeta tarjeta = Tarjeta.Builder.create()
                    .withCliente(cliente)
                    .withNroTarjeta(tarjetaDTO.getNroTarjeta())
                    .withCategoria(tarjetaDTO.getCategoria())
                    .withCodigoSeguridad(tarjetaDTO.getCodigoSeguridad())
                    .withDiaCierre(tarjetaDTO.getDiaCierre())
                    .withFechaValidaDesde(tarjetaDTO.getFechaValidaDesde())
                    .withFechaValidaHasta(tarjetaDTO.getFechaValidaHasta())
                    .withMontoLimite(tarjetaDTO.getMontoLimite())
                    .build();
            tarjetaRepository.save(tarjeta);
            return new ResponseEntity<>(tarjeta, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
