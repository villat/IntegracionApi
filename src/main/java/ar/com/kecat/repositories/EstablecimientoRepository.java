package ar.com.kecat.repositories;

import ar.com.kecat.models.Establecimiento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "establecimientos", collectionResourceRel = "establecimientos")
public interface EstablecimientoRepository extends CrudRepository<Establecimiento, Long> {

    @Override
    default void delete(Establecimiento establecimiento){
        establecimiento.setActivo(false);
        save(establecimiento);
    }

}
