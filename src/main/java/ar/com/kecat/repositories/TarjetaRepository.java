package ar.com.kecat.repositories;

import ar.com.kecat.models.Tarjeta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

@RepositoryRestResource
public interface TarjetaRepository extends CrudRepository<Tarjeta, Long> {

    @Override
    @RestResource(exported = false)
    <S extends Tarjeta> S save(S s);

    @Override
    @RestResource(exported = false)
    <S extends Tarjeta> Iterable<S> save(Iterable<S> iterable);

    @Override
    default void delete(Long aLong){
        Optional.ofNullable(findOne(aLong)).map(tarjeta -> {
            tarjeta.setActivo(false);
            return save(tarjeta);
        });
    }

    @Override
    default void delete(Tarjeta tarjeta){
        tarjeta.setActivo(false);
        save(tarjeta);
    }

    @Override
    @RestResource(exported = false)
    void delete(Iterable<? extends Tarjeta> iterable);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}
