package ar.com.kecat.repositories;

import ar.com.kecat.models.Establecimiento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

@RepositoryRestResource(path = "establecimientos")
public interface EstablecimientoRepository extends CrudRepository<Establecimiento, Long> {

    @Override
    default void delete(Long aLong){
        Optional.ofNullable(findOne(aLong)).map(establecimiento -> {
            establecimiento.setActivo(false);
            return save(establecimiento);
        });
    }

    @Override
    default void delete(Establecimiento establecimiento){
        establecimiento.setActivo(false);
        save(establecimiento);
    }

    @Override
    @RestResource(exported = false)
    void delete(Iterable<? extends Establecimiento> iterable);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}
