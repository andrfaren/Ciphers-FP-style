package fpstyle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Util {
    public static Command generateCommand(String[] args) {

        // Set default values
        String message = "";
        String outputFilePath = null;
        String inputFilePath = null;
        String cipher = "shift";
        String mode = "enc";
        String outputMethod = "console";
        String inputMethod = "console";
        int key = 0;

        // Map arguments passed by user to fields in CommandArguments object
        for (int i = 0; i < args.length; i++) {

            if ("-mode".equals(args[i])) {
                mode = (args[i + 1]);

            } else if ("-key".equals(args[i])) {
                key = Integer.parseInt(args[i + 1]);

            } else if ("-data".equals(args[i])) {
                message = args[i + 1];

            } else if ("-in".equals(args[i])) {
                inputFilePath = args[i + 1];
                inputMethod = "file";

            } else if ("-out".equals(args[i])) {
                outputFilePath = args[i + 1];
                outputMethod = "file";

            } else if ("-alg".equals(args[i])) {
                cipher = args[i + 1];
            }
        }

        return new Command(message, outputFilePath, inputFilePath, cipher, mode, outputMethod, inputMethod, key);
    }

    public static void executeCommand(Command command) {

        String transformedMessage = "";

        // input
        switch (command.inputMethod()) {
            case "console":
                transformedMessage = command.message();
            case "file":
                transformedMessage = readFromFile(command.inputFilePath());

        }

        // process
        switch (command.mode()) {
            case "enc":
                switch (command.cipher()) {
                    case "shift" -> transformedMessage = encryptShift(command.message(), command.key());
                    case "unicode" -> transformedMessage = encryptUnicode(command.message(), command.key());
                }
                break;

            case "dec":
                transformedMessage = switch (command.cipher()) {
                    case "shift" -> decryptShift(command.message(), command.key());
                    case "unicode" -> decryptUnicode(command.message(), command.key());
                    default -> transformedMessage;
                };
                break;
        }

        //output
        switch (command.outputMethod()) {
            case "console" -> System.out.println(transformedMessage);
            case "file" -> outputToFile(transformedMessage, command.outputFilePath());
        }
    }

    private static String readFromFile(String inputFilePath) {

        StringBuilder fileContent = new StringBuilder();

        File inputFile = new File(inputFilePath);

        // Print location of input file
        System.out.println(inputFile.getAbsolutePath());

        try (Scanner scanner = new Scanner(inputFile)) {
            while (scanner.hasNextLine()) {
                fileContent.append(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.printf("File %s not found.\n", inputFilePath);
        }

        return fileContent.toString();
    }

    private static void outputToFile(String transformedMessage, String outputFilePath) {
        File outputFile = new File(outputFilePath);

        // Log location of output file
        System.out.println(outputFile.getAbsolutePath());
        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.write(transformedMessage);

        } catch (IOException e) {
            System.out.printf("An exception occurred %s", e.getMessage());
        }
    }

    private static String encryptShift(String messageToEncrypt, int key) {

        String encryptedMessage = messageToEncrypt.chars()
                .map(ch -> Character.isUpperCase(ch) ? (ch + key - 'A') % 26 + 'A' : (ch + key - 'a') % 26 + 'a')
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return encryptedMessage;
    }

    private static String decryptShift(String messageToDecrypt, int key) {

        String decryptedMessage = messageToDecrypt.chars()
                .map(ch -> Character.isUpperCase(ch) ? (ch + 26 - key - 'A') % 26 + 'A' : (ch + 26 - key - 'a') % 26 + 'a')
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return decryptedMessage;
    }

    private static String decryptUnicode(String messageToDecrypt, int key) {

        String decryptedMessage = messageToDecrypt.chars()
                .map(ch -> ch - key < 32 ? ch - key + 95 : ch - key)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return decryptedMessage;
    }

    private static String encryptUnicode(String messageToEncrypt, int key) {

        String encryptedMessage = messageToEncrypt.chars()
                .map(ch -> ch + key > 126 ? (ch + key) % 126 + 31 : ch + key)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return encryptedMessage;
    }


}
