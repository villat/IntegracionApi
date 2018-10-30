package ar.com.kecat.repositories;

import ar.com.kecat.models.Liquidacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

@RepositoryRestResource(path = "/liquidaciones", collectionResourceRel = "liquidaciones")
public interface LiquidacionRepository extends CrudRepository<Liquidacion, Long> {

    @Override
    default void delete(Liquidacion liquidacion){
        liquidacion.setActivo(false);
        save(liquidacion);
    }

}
