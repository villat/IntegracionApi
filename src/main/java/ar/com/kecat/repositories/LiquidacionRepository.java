package ar.com.kecat.repositories;

import ar.com.kecat.models.Liquidacion;
import ar.com.kecat.models.Liquidacion.Estado;
import ar.com.kecat.models.Tarjeta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(path = "/liquidaciones", collectionResourceRel = "liquidaciones")
public interface LiquidacionRepository extends CrudRepository<Liquidacion, Long> {

    @Override
    @RestResource(exported = false)
    <S extends Liquidacion> S save(S s);

    @Override
    default void delete(Liquidacion liquidacion){
        liquidacion.setActivo(false);
        liquidacion.setEstado(Estado.ELIMINADA);
        save(liquidacion);
    }

    List<Liquidacion> findByTarjeta(Tarjeta tarjeta);

    List<Liquidacion> findByEstado(Estado estado);

    Liquidacion findByEstadoAndTarjeta(@Param("estado") Estado estado, @Param("tarjeta") Tarjeta tarjeta);
}