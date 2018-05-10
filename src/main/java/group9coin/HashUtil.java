package group9coin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

public class HashUtil {

    static <T> byte[] createHash(final T objectToHash) {
        final ObjectMapper objectMapper = new ObjectMapper();
        String json = "";

        try {
            json = objectMapper.writeValueAsString(objectToHash);
        } catch (final JsonProcessingException e) {
            System.out.println("Failure"); //TODO JD: throw proper exception

        }
        return DigestUtils.sha256(json);
    }

    static String toString(final byte[] hash) {
        return Hex.encodeHexString(hash);
    }

    static boolean isValid(final byte[] hash, final Integer difficulty) {
        final Integer leadingZeros = leadingZeroCount(hash);
        return difficulty <= leadingZeros;
    }

    private static int leadingZeroCount(final byte[] hash) {
        int totalCountLeadingZeros = 0;
        final byte[] var4 = hash;
        final int var5 = hash.length;

        for (int var6 = 0; var6 < var5; ++var6) {
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
