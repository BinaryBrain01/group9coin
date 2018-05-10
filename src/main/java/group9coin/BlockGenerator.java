package group9coin;

import group9coin.domain.Block;
import group9coin.domain.Content;
import group9coin.domain.Header;
import group9coin.domain.PointDistribution;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class BlockGenerator {

    @Autowired
    private Group9CoinRestClient restClient;

    Block findNextBlock() {
        final Block block = restClient.getBlockAtEndOfLongestChain();
        System.out.println("previous hash: " + block.getHash());
        System.out.println("previous blockNr: " + block.getHeader().getBlockNumber());
        final Integer difficulty = restClient.getDifficulty().getValue();
        System.out.println("Difficulty: " + difficulty);

        final Block nextBlock = createBlock(block, difficulty);

        System.out.println("Found next block");

        return nextBlock;
    }


    private static Pair<String, Header> createHashAndHeader(final Block prevBlock, final Content content, final Integer difficulty) {
        final Integer blockNumber = prevBlock.getHeader().getBlockNumber() + 1;
        final String contentHash = HashUtil.toString(HashUtil.createHash(content));
        final String previousHash = prevBlock.getHash();
        return determineHashAndHeader(blockNumber, contentHash, previousHash, difficulty);
    }

    private static Pair<String, Header> determineHashAndHeader(final Integer blockNumber, final String contentHash, final String previousHash, final Integer difficulty) {
        Integer nonce = new Random().nextInt(Integer.MAX_VALUE);
        Header header = new Header(blockNumber, previousHash, contentHash, String.valueOf(nonce));
        byte[] hash = HashUtil.createHash(header);

        while (!HashUtil.isValid(hash, difficulty)) {
            nonce += 1;
            header = new Header(blockNumber, previousHash, contentHash, String.valueOf(nonce));
            hash = HashUtil.createHash(header);
        }
        final String stringHash = HashUtil.toString(hash);
        System.out.println("found hash string: " + stringHash);

        return new Pair<>(stringHash, header);
    }

    private static Content createContent() {
        final List<PointDistribution> pointDistribution = new ArrayList<>();
        final PointDistribution pointDistributionEntry = new PointDistribution("TAP-JD", 100);
        pointDistribution.add(pointDistributionEntry);
        return new Content(pointDistribution);
    }

    private static Block createBlock(final Block prevBlock, final Integer difficulty) {
        final Content content = createContent();
        final Pair<String, Header> hashAndHeader = createHashAndHeader(prevBlock, content, difficulty);
        return new Block(hashAndHeader.getKey(), hashAndHeader.getValue(), content);
    }


}
