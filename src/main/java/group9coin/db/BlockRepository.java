package group9coin.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockRepository extends CrudRepository<DbBlock, Long> {

}
