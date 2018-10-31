package ar.com.kecat.repositories;

import ar.com.kecat.models.Tarjeta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource
public interface TarjetaRepository extends CrudRepository<Tarjeta, Long> {

    @Override
    @RestResource(exported = false)
    <S extends Tarjeta> S save(S s);

    @Override
    default void delete(Tarjeta tarjeta){
        tarjeta.setActivo(false);
        save(tarjeta);
    }

    Tarjeta findByNroTarjeta(String nroTarjeta);

}
