
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Default values
        String mode = "enc";
        int key = 0;
        String data = "";
        String inFile = "";
        String outFile = "";
        String alg = "shift";

        // Parse command-line arguments
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-mode":
                    mode = args[++i];
                    break;
                case "-key":
                    key = Integer.parseInt(args[++i]);
                    break;
                case "-data":
                    data = args[++i];
                    break;
                case "-in":
                    inFile = args[++i];
                    break;
                case "-out":
                    outFile = args[++i];
                    break;
                case "-alg":
                    alg = args[++i];
                    break;
            }
        }

        // Choose the algorithm
        CipherAlgorithm cipherAlgorithm;
        if (alg.equals("unicode")) {
            cipherAlgorithm = new UnicodeCipher();
        } else {
            cipherAlgorithm = new ShiftCipher();
        }

        Cipher cipher = new Cipher(cipherAlgorithm);

        // Read data from file if -data is not provided
        if (data.isEmpty() && !inFile.isEmpty()) {
            try {
                data = cipher.readFile(inFile);
            } catch (FileNotFoundException e) {
                System.out.println("Error: Input file not found.");
                return;
            }
        }

        // Process the message
        String message = cipher.process(data, key, mode);

        // Write the result to the specified output file or to standard output
        if (outFile.isEmpty()) {
            System.out.println(message);
        } else {
            try {
                cipher.writeFile(outFile, message);
            } catch (IOException e) {
                System.out.println("Error: Unable to write to output file.");
            }
        }
    }
}