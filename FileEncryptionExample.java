import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class FileEncryptionExample {
    public static void main(String[] args) {
        try {
            // Replace with your secret key (must be 16, 24, or 32 bytes long)
            String secretKeyString = "MySecretKey1234567";
            SecretKey secretKey = new SecretKeySpec(secretKeyString.getBytes(), "AES");

            String inputFilePath = "input.txt";
            String encryptedFilePath = "encrypted.enc";
            String decryptedFilePath = "decrypted.txt";

            // Encrypt the file
            encryptFile(secretKey, inputFilePath, encryptedFilePath);

            // Decrypt the encrypted file
            decryptFile(secretKey, encryptedFilePath, decryptedFilePath);

            System.out.println("File encryption and decryption completed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void encryptFile(SecretKey secretKey, String inputFilePath, String encryptedFilePath)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        try (FileInputStream inputFileStream = new FileInputStream(inputFilePath);
             FileOutputStream encryptedFileStream = new FileOutputStream(encryptedFilePath)) {
            try (CipherOutputStream cipherOutputStream = new CipherOutputStream(encryptedFileStream, cipher)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputFileStream.read(buffer)) != -1) {
                    cipherOutputStream.write(buffer, 0, bytesRead);
                }
            }
        }
    }

    public static void decryptFile(SecretKey secretKey, String encryptedFilePath, String decryptedFilePath)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        try (FileInputStream encryptedFileStream = new FileInputStream(encryptedFilePath);
             FileOutputStream decryptedFileStream = new FileOutputStream(decryptedFilePath)) {
            try (CipherInputStream cipherInputStream = new CipherInputStream(encryptedFileStream, cipher)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = cipherInputStream.read(buffer)) != -1) {
                    decryptedFileStream.write(buffer, 0, bytesRead);
                }
            }
        }
    }
}
