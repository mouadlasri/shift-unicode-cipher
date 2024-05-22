
public class ShiftCipher implements CipherAlgorithm {
    @Override
    public String encrypt(String message, int key) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                encrypted.append((char) ((c - base + key) % 26 + base));
            } else {
                encrypted.append(c);
            }
        }
        return encrypted.toString();
    }

    @Override
    public String decrypt(String message, int key) {
        return encrypt(message, 26 - (key % 26));
    }
}