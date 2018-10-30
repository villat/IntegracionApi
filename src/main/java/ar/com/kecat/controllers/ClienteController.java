package ar.com.kecat.controllers;

import ar.com.kecat.forms.ClienteForm;
import ar.com.kecat.models.Cliente;
import ar.com.kecat.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


@RepositoryRestController
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PutMapping("/clientes/{idCliente}")
    @ResponseBody ResponseEntity<Cliente> putCliente(@PathVariable Long idCliente, @RequestBody ClienteForm clienteForm){
        Cliente cliente = clienteRepository.findOne(idCliente);
        if (updateCliente(clienteForm, cliente)) return new ResponseEntity<>(cliente, HttpStatus.ACCEPTED);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/clientes/{idCliente}")
    @ResponseBody ResponseEntity<Cliente> patchCliente(@PathVariable Long idCliente, @RequestBody ClienteForm clienteForm){
        Cliente cliente = clienteRepository.findOne(idCliente);
        if (updateCliente(clienteForm, cliente)) return new ResponseEntity<>(cliente, HttpStatus.ACCEPTED);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private boolean updateCliente(@RequestBody ClienteForm clienteForm, Cliente cliente) {
        if(cliente != null){
            if(clienteForm.getNombre() != null) cliente.setNombre(clienteForm.getNombre());
            if(clienteForm.getApellido() != null) cliente.setApellido(clienteForm.getApellido());
            clienteRepository.save(cliente);
            return true;
        }
        return false;
    }

}
