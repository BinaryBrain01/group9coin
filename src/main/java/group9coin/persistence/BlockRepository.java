package group9coin.persistence;

import org.springframework.data.repository.CrudRepository;

public interface BlockRepository extends CrudRepository<BlockDbEntity, Long> {

}
