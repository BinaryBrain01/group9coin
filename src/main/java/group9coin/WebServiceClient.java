package group9coin;

import group9coin.domain.Block;
import group9coin.domain.Difficulty;

public interface WebServiceClient {

    Block getBlockAtEndOfLongestChain();

    Difficulty getDifficulty();

    void postBlock(final Block block);

}
