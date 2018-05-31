package group9coin.persistence;

import group9coin.domain.Block;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlockPostgresClient implements BlockDbClient {

    @Autowired
    BlockRepository repository;

    @Autowired
    BlockConverter blockConverter;

    @Override
    public void saveBlock(final Block block) {
        repository.save(blockConverter.convertToDbEntity(block));
    }
}
