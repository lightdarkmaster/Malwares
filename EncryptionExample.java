import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncryptionExample {
    public static void main(String[] args) {
        try {
            String originalText = "Hello, this is a secret message!";
            String secretKeyString = "MySecretKey12345"; // Replace with your secret key

            // Generate a secret key
            byte[] secretKeyBytes = secretKeyString.getBytes(StandardCharsets.UTF_8);
            SecretKey secretKey = new SecretKeySpec(secretKeyBytes, "AES");

            // Encrypt the message
            byte[] encryptedBytes = encrypt(originalText, secretKey);

            // Decrypt the encrypted message
            String decryptedText = decrypt(encryptedBytes, secretKey);

            System.out.println("Original: " + originalText);
            System.out.println("Encrypted: " + bytesToBase64(encryptedBytes));
            System.out.println("Decrypted: " + decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] encrypt(String plainText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
    }

    public static String decrypt(byte[] encryptedBytes, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    public static String bytesToBase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }
}
