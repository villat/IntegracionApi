package ar.com.kecat.controllers;

import ar.com.kecat.dto.ClienteDTO;
import ar.com.kecat.models.Cliente;
import ar.com.kecat.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


@RepositoryRestController
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @Transactional
    @PutMapping("/clientes/{idCliente}")
    @ResponseBody ResponseEntity<Cliente> putCliente(@PathVariable Long idCliente, @RequestBody ClienteDTO clienteDTO){
        Cliente cliente = clienteRepository.findOne(idCliente);
        if(cliente != null){
            if(clienteDTO.getNombre() != null) cliente.setNombre(clienteDTO.getNombre());
            if(clienteDTO.getApellido() != null) cliente.setApellido(clienteDTO.getApellido());
            clienteRepository.save(cliente);
            return new ResponseEntity<>(cliente,HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
