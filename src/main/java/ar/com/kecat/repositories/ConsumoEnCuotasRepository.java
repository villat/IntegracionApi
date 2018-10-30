package ar.com.kecat.repositories;

import ar.com.kecat.models.ConsumoEnCuotas;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "/consumosEnCuotas", collectionResourceRel = "consumosEnCuotas")
public interface ConsumoEnCuotasRepository extends CrudRepository<ConsumoEnCuotas, Long> {

    @Override
    @RestResource(exported = false)
    <S extends ConsumoEnCuotas> S save(S s);

    @Override
    @RestResource(exported = false)
    void delete(ConsumoEnCuotas consumoEnCuotas);

}
