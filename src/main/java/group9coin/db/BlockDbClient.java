package group9coin.db;

import group9coin.domain.Block;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BlockDbClient {

    @Autowired
    BlockRepository repository;

    public void saveBlock(final Block block) {
        final DbBlock dbBlock = new DbBlock("" + block.getHeader().getBlockNumber(), block.getHash()); //TODO JD: fix type
        repository.save(dbBlock);
    }
}
