package group9coin.db;

import org.springframework.data.repository.CrudRepository;

public interface BlockRepository extends CrudRepository<DbBlock, Long> {

}
