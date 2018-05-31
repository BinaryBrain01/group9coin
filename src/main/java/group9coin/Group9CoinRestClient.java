package group9coin;

import group9coin.domain.Block;
import group9coin.domain.Difficulty;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Group9CoinRestClient implements WebServiceClient {

    private static final String REST_URI = "http://localhost:9000";
    private final RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    Group9CoinRestClient() {
        restTemplate = restTemplate();

    }

    @Override
    public Block getBlockAtEndOfLongestChain() {
        return restTemplate.
                getForObject(REST_URI + "/blocks/end-of-chain", Block.class);
    }

    @Override
    public Difficulty getDifficulty() {
        return restTemplate.
                getForObject(REST_URI + "/difficulty", Difficulty.class);
    }

    @Override
    public void postBlock(final Block block) {
        restTemplate.postForObject(REST_URI + "/blocks", block, Block.class);
    }


}
