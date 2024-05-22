
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Cipher {
    private CipherAlgorithm algorithm;

    public Cipher(CipherAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public String process(String data, int key, String mode) {
        if (mode.equals("enc")) {
            return algorithm.encrypt(data, key);
        } else {
            return algorithm.decrypt(data, key);
        }
    }

    public String readFile(String fileName) throws FileNotFoundException {
        StringBuilder content = new StringBuilder();
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            content.append(scanner.nextLine());
            if (scanner.hasNextLine()) {
                content.append(System.lineSeparator());
            }
        }
        scanner.close();
        return content.toString();
    }

    public void writeFile(String fileName, String content) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        writer.write(content);
        writer.close();
    }
}