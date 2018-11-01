package ar.com.kecat.repositories;

import ar.com.kecat.models.Cuota;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface CuotasRepository extends CrudRepository<Cuota, Long> {

}
