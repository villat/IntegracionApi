package ar.com.kecat.repositories;

import ar.com.kecat.models.Cobranza;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

@RepositoryRestResource
public interface CobranzaRepository extends CrudRepository<Cobranza, Long> {

    @Override
    default void delete(Long aLong){
        Optional.ofNullable(findOne(aLong)).map(cobranza -> {
            cobranza.setActivo(false);
            return save(cobranza);
        });
    }

    @Override
    default void delete(Cobranza cobranza){
        cobranza.setActivo(false);
        save(cobranza);
    }

    @Override
    @RestResource(exported = false)
    void delete(Iterable<? extends Cobranza> iterable);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}
