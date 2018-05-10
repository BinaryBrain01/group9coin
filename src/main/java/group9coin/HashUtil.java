package group9coin;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

public class HashUtil {

    static boolean isValid(final byte[] hash, final Integer difficulty) {
        final Integer leadingZeros = leadingZeroCount(hash);
//      System.out.println("validHash? difficulty " + difficulty + " <= leadingZeros " + leadingZeros );
        return difficulty <= leadingZeros;
    }

    static byte[] createHash(final String stringToHash) {
        return DigestUtils.sha256(stringToHash);
    }

    static String toString(final byte[] hash) {
        return Hex.encodeHexString(hash);
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
