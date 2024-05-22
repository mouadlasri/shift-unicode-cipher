
public class UnicodeCipher implements CipherAlgorithm {
    @Override
    public String encrypt(String message, int key) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : message.toCharArray()) {
            encrypted.append((char) ((c + key) % 128));
        }
        return encrypted.toString();
    }

    @Override
    public String decrypt(String message, int key) {
        StringBuilder decrypted = new StringBuilder();
        for (char c : message.toCharArray()) {
            int decryptedChar = (c - key) % 128;
            if (decryptedChar < 0) {
                decryptedChar += 128;
            }
            decrypted.append((char) decryptedChar);
        }
        return decrypted.toString();
    }
}