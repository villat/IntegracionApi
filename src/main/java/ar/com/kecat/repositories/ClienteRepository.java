package ar.com.kecat.repositories;

import ar.com.kecat.models.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

@RepositoryRestResource
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

    @Override
    default void delete(Long aLong){
        Optional.ofNullable(findOne(aLong)).map(cliente -> {
            cliente.setActivo(false);
            return save(cliente);
        });
    }

    @Override
    default void delete(Cliente cliente){
        cliente.setActivo(false);
        save(cliente);
    }

    @Override
    @RestResource(exported = false)
    void delete(Iterable<? extends Cliente> iterable);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}
