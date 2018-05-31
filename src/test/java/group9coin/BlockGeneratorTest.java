package group9coin;

import group9coin.domain.Block;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BlockGeneratorTest {

    @InjectMocks
    private BlockGenerator blockGenerator;

    @BeforeAll
    void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 4, 8, 16})
    @DisplayName("Next block is found successfully, difficulty:")
    public void nextBlockFoundSuccessfully(final int difficultyValue) throws DecoderException {
        Integer previousBlockNr = 42;
        final Block previousBlock = setupPreviousBlock(previousBlockNr);

        final Block nextBlock = blockGenerator.findNextBlock(previousBlock, difficultyValue);

        assertEquals(++previousBlockNr, nextBlock.getHeader().getBlockNumber());
        final byte[] hash = Hex.decodeHex(nextBlock.getHash().toCharArray());
        assertTrue(HashUtil.isValid(hash, difficultyValue), "Hash is supposed to be valid");
    }


    private Block setupPreviousBlock(final int previousBlockNr) {
        final Block previousBlock = Mockito.mock(Block.class, RETURNS_DEEP_STUBS);
        when(previousBlock.getHeader().getBlockNumber()).thenReturn(previousBlockNr);
        when(previousBlock.getHash()).thenReturn(Hex.encodeHexString(DigestUtils.sha256("test")));
        return previousBlock;
    }

    @Nested
    @DisplayName("Finding next block when")
    class FindBlockTest {
        @Test
        @DisplayName("difficulty is 0")
        void difficulty0() {
            System.out.println("Example test for method A");
        }

        @Test
        @DisplayName("difficulty is 1")
        void difficulty1() {
            System.out.println("Example test for method A");
        }
    }

}