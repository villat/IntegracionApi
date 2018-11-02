package ar.com.kecat.repositories;

import ar.com.kecat.models.Cobranza;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource
public interface CobranzaRepository extends CrudRepository<Cobranza, Long> {

    @Override
    @RestResource(exported = false)
    <S extends Cobranza> S save(S s);

    @Override
    default void delete(Cobranza cobranza){
        cobranza.setActivo(false);
        save(cobranza);
    }

}
