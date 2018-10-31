package ar.com.kecat.repositories;

import ar.com.kecat.models.Liquidacion;
import ar.com.kecat.models.Liquidacion.Estado;
import ar.com.kecat.models.Tarjeta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "/liquidaciones", collectionResourceRel = "liquidaciones")
public interface LiquidacionRepository extends CrudRepository<Liquidacion, Long> {

    @Override
    default void delete(Liquidacion liquidacion){
        liquidacion.setActivo(false);
        save(liquidacion);
    }

    @Query("SELECT liq FROM Liquidacion liq WHERE liq.estado = :estado AND liq.tarjeta = :tarjeta")
    Liquidacion findByEstadoAndTarjeta(@Param("estado") Estado estado, @Param("tarjeta") Tarjeta tarjeta);
}
