package group9coin.persistence;

import group9coin.domain.Block;
import org.springframework.stereotype.Service;

@Service
public class BlockConverter {

    public BlockDbEntity convertToDbEntity(final Block block) {
        return new BlockDbEntity.Builder()
                .blockNumber(block.getHeader().getBlockNumber())
                .hash(block.getHash())
                .build();
    }
}
