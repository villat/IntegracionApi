package ar.com.kecat.repositories;

import ar.com.kecat.models.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

    @Override
    default void delete(Cliente cliente){
        cliente.setActivo(false);
        save(cliente);
    }
}
