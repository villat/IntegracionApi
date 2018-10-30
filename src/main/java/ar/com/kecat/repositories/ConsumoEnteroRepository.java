package ar.com.kecat.repositories;

import ar.com.kecat.models.ConsumoEntero;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "/consumosEnteros", collectionResourceRel = "consumosEnteros")
public interface ConsumoEnteroRepository extends CrudRepository<ConsumoEntero, Long> {

    @Override
    @RestResource(exported = false)
    <S extends ConsumoEntero> S save(S s);

    @Override
    @RestResource(exported = false)
    void delete(ConsumoEntero consumoEntero);

}
