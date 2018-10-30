package ar.com.kecat.repositories;

import ar.com.kecat.models.Cobranza;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CobranzaRepository extends CrudRepository<Cobranza, Long> {

    @Override
    default void delete(Cobranza cobranza){
        cobranza.setActivo(false);
        save(cobranza);
    }

}
