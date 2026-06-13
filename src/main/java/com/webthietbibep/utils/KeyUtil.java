package com.webthietbibep.utils;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class KeyUtil {

    private static final String ALGORITHM = "RSA";
    private static final int KEY_SIZE = 2048;


    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        keyPairGenerator.initialize(KEY_SIZE);
        return keyPairGenerator.generateKeyPair();
    }

    public static String encodePublicKey(PublicKey publicKey) {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }


    public static String encodePrivateKeyToPem(PrivateKey privateKey) {
        String base64Encoded = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        // Add PEM header and footer
        return "-----BEGIN PRIVATE KEY-----\n"
                + base64Encoded.replaceAll("(.{64})", "$1\n")
                + "\n-----END PRIVATE KEY-----";
    }

    public static PublicKey decodePublicKey(String publicKeyBase64) throws NoSuchAlgorithmException, GeneralSecurityException {
        byte[] keyBytes = Base64.getDecoder().decode(publicKeyBase64);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance(ALGORITHM);
        return kf.generatePublic(spec);
    }


    public static PrivateKey decodePrivateKey(String privateKeyBase64) throws NoSuchAlgorithmException, GeneralSecurityException {
        // Remove PEM headers/footers if present before decoding
        String pureBase64 = privateKeyBase64
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");
        byte[] keyBytes = Base64.getDecoder().decode(pureBase64);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance(ALGORITHM);
        return kf.generatePrivate(spec);
    }

    public static boolean verifySign(String data, String signature, String publicKey){
        try{
            byte [] keyBytes = Base64.getDecoder().decode(publicKey);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            KeyFactory kf = KeyFactory.getInstance(ALGORITHM);
            PublicKey pubKey = kf.generatePublic(spec);
            Signature sig = Signature.getInstance("SHA256withRSA");
            sig.initVerify(pubKey);

            byte[] dataBytes = data.getBytes("UTF-8");
            sig.update(dataBytes);
            byte[] sigVerify = Base64.getDecoder().decode(signature);
            boolean verify = sig.verify(sigVerify);
            return verify;

        }catch (Exception e){
            System.err.println("Caught exception: " + e.toString());
            return false;
        }
    }
}