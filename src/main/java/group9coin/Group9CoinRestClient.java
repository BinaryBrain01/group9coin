package group9coin;

import group9coin.domain.Block;
import group9coin.domain.Difficulty;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Group9CoinRestClient {

    private static final String REST_URI = "http://localhost:9000";
    private final RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    Group9CoinRestClient() {
        restTemplate = restTemplate();

    }

    Block getBlockAtEndOfLongestChain() {
        return restTemplate.
                getForObject(REST_URI + "/blocks/end-of-chain", Block.class);
    }

    Difficulty getDifficulty() {
        return restTemplate.
                getForObject(REST_URI + "/difficulty", Difficulty.class);
    }

    void postBlock(final Block block) {
        restTemplate.postForObject(REST_URI + "/blocks", block, Block.class);
    }


}
