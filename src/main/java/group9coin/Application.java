package group9coin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import group9coin.domain.*;
import javafx.util.Pair;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class Application {

    private static final String REST_URI = "http://localhost:9000";
    private static  RestTemplate restTemplate;

    public static void main(final String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public RestTemplate restTemplate(final RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(final RestTemplate restTemplate, final ApplicationContext ctx) throws Exception {
        this.restTemplate = restTemplate;
        printBeanNames(ctx);
        return args -> {
            int count = 0;
            while (count <= 5) {
                final Block nextBlock = nextBlock();
                postBlock(nextBlock);
                count++;
            }
        };
    }

    private void printBeanNames(final ApplicationContext ctx) {
        final String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        System.out.println("Beans found:");
        for (final String beanName : beanNames) {
            System.out.println(beanName);
        }

    }

    public static Block nextBlock() {
        final Block block = getBlockAtEndOfLongestChain();
        System.out.println("previous hash: " + block.getHash());
        System.out.println("previous blockNr: " +block.getHeader().getBlockNumber());

        final Integer difficulty = getDifficulty().getValue();
        System.out.println("Difficulty: "+ difficulty);
        final Block nextBlock = createBlock(block, difficulty);
        System.out.println("Going to post block: "+ nextBlock);

        return nextBlock;
    }

    private static Block getBlockAtEndOfLongestChain() {
        return restTemplate.
                getForObject(REST_URI + "/blocks/end-of-chain", Block.class);
    }

    private static Difficulty getDifficulty( ) {
        return restTemplate.
                getForObject(REST_URI + "/difficulty", Difficulty.class);
    }

    private static void postBlock(final Block block) {
        restTemplate.postForObject(REST_URI + "/blocks", block, Block.class);
    }

    private static Pair<String, Header> createHashAndHeader(final Block prevBlock, final Content content, final Integer difficulty) {
        final Integer blockNumber = prevBlock.getHeader().getBlockNumber() + 1;
        final String contentHash = Hex.encodeHexString(createContentHash(content));
        final String previousHash = prevBlock.getHash();
        return determineHashAndHeader(blockNumber, contentHash, previousHash, difficulty);
    }

    private static Pair<String, Header> determineHashAndHeader(final Integer blockNumber, final String contentHash, final String previousHash, final Integer difficulty) {
        Integer nonce = new Random().nextInt(Integer.MAX_VALUE);
        Header header = new Header(blockNumber, previousHash, contentHash, String.valueOf(nonce));
        byte[] hash = createHash(header);

        while(!isValidHash(hash, difficulty)){
            nonce += 1;
            header = new Header(blockNumber, previousHash, contentHash, String.valueOf(nonce));
            hash = createHash(header);
        }
//        System.out.println("difficulty: " + difficulty);
        final String stringHash = Hex.encodeHexString(hash);
        System.out.println("found hash string: " + stringHash);


        return new Pair<>(stringHash, header);
    }

    private static boolean isValidHash(final byte[] hash, final Integer difficulty) {
        final Integer leadingZeros = leadingZeroCount(hash);
//      System.out.println("validHash? difficulty " + difficulty + " <= leadingZeros " + leadingZeros );
        return difficulty <= leadingZeros;
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
        return new Block(hashAndHeader.getKey(),  hashAndHeader.getValue(), content);

    }


    private static byte[] createContentHash(final Content content) {
        final ObjectMapper objectMapper = new ObjectMapper();
        String json = "";

        try {
            json = objectMapper.writeValueAsString(content);
        } catch (final JsonProcessingException e) {
            System.out.println("Failure");

        }
//        System.out.println(json);

        return createHash(json);
    }

    private static byte[] createHash(final Header header) {
        final ObjectMapper objectMapper = new ObjectMapper();
        String json = "";

        try {
            json = objectMapper.writeValueAsString(header);
        } catch (final JsonProcessingException e) {
            System.out.println("Failure");

        }
//        System.out.println(json);

        return createHash(json);
    }

    private static byte[] createHash(final String stringToHash) {
        return DigestUtils.sha256(stringToHash);
    }


    private static int leadingZeroCount(final byte[] hash) {
        int totalCountLeadingZeros = 0;
        final byte[] var4 = hash;
        final int var5 = hash.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            final byte b = var4[var6];
            final int lzc = leadingZeroCountOfByte(b);
            totalCountLeadingZeros += lzc;
            if (lzc != 8) {
                break;
            }
        }

        return totalCountLeadingZeros;
    }

    private static int leadingZeroCountOfByte(final byte b) {
        final int i = b & 255;
        return Integer.numberOfLeadingZeros(i) - 24;
    }



}