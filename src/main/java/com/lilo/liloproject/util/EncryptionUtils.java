package com.lilo.liloproject.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class EncryptionUtils {

    private static final String HASH_ALGORITHM = "HmacSHA512";

    private EncryptionUtils() {
    }

    public static String hashString(final String plainText, final String salt) throws NoSuchAlgorithmException, InvalidKeyException {
        return hashString(plainText.getBytes(), salt.getBytes());
    }

    private static String hashString(final byte[] plainText, final byte[] salt) throws InvalidKeyException, NoSuchAlgorithmException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(salt, HASH_ALGORITHM);

        Mac mac = Mac.getInstance(HASH_ALGORITHM);

        mac.init(secretKeySpec);

        byte[] encryptedPassword = mac.doFinal(plainText);
        return DatatypeConverter.printBase64Binary(encryptedPassword);
    }
}
