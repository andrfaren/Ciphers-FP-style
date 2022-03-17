package fpstyle;

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

    public static String executeCommand(Command command) {

        String result = "";

        switch (command.getMode()) {

            case "enc":
                switch (command.getCipher()) {
                    case "shift" -> result = encryptShift(command.getMessage(), command.getKey());
                    case "unicode" -> result = encryptUnicode(command.getMessage(), command.getKey());
                }
                break;

            case "dec":
                result = switch (command.getCipher()) {
                    case "shift" -> decryptShift(command.getMessage(), command.getKey());
                    case "unicode" -> decryptUnicode(command.getMessage(), command.getKey());
                    default -> result;
                };
                break;
        }
        return result;
    }

    private static String encryptShift(String messageToEncrypt, int key) {
        StringBuilder builder = new StringBuilder();

        for(char letter: messageToEncrypt.toCharArray()) {

            if (Character.isAlphabetic(letter) && Character.isUpperCase(letter)) {
                builder.append((char)((letter + key - 'A') % 26 + 'A'));
            } else if (Character.isAlphabetic(letter) && Character.isLowerCase(letter)) {
                builder.append((char)((letter + key - 'a') % 26 + 'a'));
            } else {
                builder.append(letter);
            }
        }
        return builder.toString();
    }

    private static String decryptShift(String messageToDecrypt, int key) {
        StringBuilder builder = new StringBuilder();

        for(char letter: messageToDecrypt.toCharArray()) {

            if (Character.isAlphabetic(letter) && Character.isUpperCase(letter)) {
                builder.append((char)((letter + 26 - key - 'A') % 26 + 'A'));
            } else if (Character.isAlphabetic(letter) && Character.isLowerCase(letter)){
                builder.append((char)((letter + 26 - key - 'a') % 26 + 'a'));
            } else {
                builder.append(letter);
            }
        }

        return builder.toString();
    }

    private static String decryptUnicode(String message, int key) {

        char[] messageArray = message.toCharArray();

        char[] decryptedCharArray = new char[messageArray.length];

        // Move each char in original array by key and populate decrypted array
        for (int i = 0; i < decryptedCharArray.length; i++) {
            if (messageArray[i] - key < 32) {
                decryptedCharArray[i] = (char) (messageArray[i] - key + 95);

            } else {
                decryptedCharArray[i] = (char) (messageArray[i] - key);
            }
        }

        return String.valueOf(decryptedCharArray);
    }

    private static String encryptUnicode(String message, int key) {

        char[] messageArray = message.toCharArray();

        char[] encryptedCharArray = new char[messageArray.length];

        // Move each char in original array by key and populate encrypted array
        for (int i = 0; i < encryptedCharArray.length; i++) {
            if (messageArray[i] + key > 126) {
                encryptedCharArray[i] = (char) ((messageArray[i] + key) % 126 + 31);

            } else {
                encryptedCharArray[i] = (char) (messageArray[i] + key);
            }
        }

        return String.valueOf(encryptedCharArray);
    }


}
