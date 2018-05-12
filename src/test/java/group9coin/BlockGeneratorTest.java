package group9coin;

import group9coin.domain.Block;
import group9coin.domain.Difficulty;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BlockGeneratorTest {

    @InjectMocks
    BlockGenerator blockGenerator;

    @Mock
    Group9CoinRestClient restClient;

    @BeforeAll
    void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 4, 8, 16})
    @DisplayName("Next block is found successfully, difficulty:")
    public void nextBlockFoundSuccessfully(final int difficultyValue) throws DecoderException {
        Integer previousBlockNr = 42;
        setupPreviousBlock(previousBlockNr);
        setupDifficulty(difficultyValue);

        final Block nextBlock = blockGenerator.findNextBlock();

        assertEquals(++previousBlockNr, nextBlock.getHeader().getBlockNumber());
        final byte[] hash = Hex.decodeHex(nextBlock.getHash().toCharArray());
        assertTrue(HashUtil.isValid(hash, difficultyValue), "Hash is supposed to be valid");
    }

    private void setupDifficulty(final int difficultyValue) {
        final Difficulty difficulty = new Difficulty(difficultyValue);
        when(restClient.getDifficulty()).thenReturn(difficulty);
    }

    private void setupPreviousBlock(final int previousBlockNr) {
        final Block previousBlock = Mockito.mock(Block.class, RETURNS_DEEP_STUBS);
        when(previousBlock.getHeader().getBlockNumber()).thenReturn(previousBlockNr);
        when(previousBlock.getHash()).thenReturn(Hex.encodeHexString(DigestUtils.sha256("test")));
        when(restClient.getBlockAtEndOfLongestChain()).thenReturn(previousBlock);
    }


}