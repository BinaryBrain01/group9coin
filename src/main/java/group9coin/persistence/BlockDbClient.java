package group9coin.persistence;

import group9coin.domain.Block;

public interface BlockDbClient {

    void saveBlock(final Block block);
}
