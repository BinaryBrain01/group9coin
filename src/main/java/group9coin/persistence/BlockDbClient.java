package group9coin.persistence;

import group9coin.domain.Block;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BlockDbClient {

    @Autowired
    BlockRepository repository;

    @Autowired
    BlockConverter blockConverter;

    public void saveBlock(final Block block) {
        repository.save(blockConverter.convertToDbEntity(block));
    }
}
