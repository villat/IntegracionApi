package ar.com.kecat.repositories;

import ar.com.kecat.models.Consumo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

@RepositoryRestResource(path = "/consumos", collectionResourceRel = "consumos")
public interface ConsumoRepository extends CrudRepository<Consumo, Long> {

    @Override
    default void delete(Long aLong){
        Optional.ofNullable(findOne(aLong)).map(consumo -> {
            consumo.setActivo(false);
            return save(consumo);
        });
    }

    @Override
    default void delete(Consumo consumo){
        consumo.setActivo(false);
        save(consumo);
    }

    @Override
    @RestResource(exported = false)
    void delete(Iterable<? extends Consumo> iterable);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}
