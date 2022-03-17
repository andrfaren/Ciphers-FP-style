package fpstyle;

public class Command {

    private final String message;
    private final String outputFilePath;
    private final String inputFilePath;
    private final String cipher;
    private final String mode;
    private final String outputMethod;
    private final String inputMethod;
    private final int key;


    public Command(String message, String outputFilePath, String inputFilePath, String cipher, String mode,
                   String outputMethod, String inputMethod, int key) {
        this.message = message;
        this.outputFilePath = outputFilePath;
        this.inputFilePath = inputFilePath;
        this.cipher = cipher;
        this.mode = mode;
        this.outputMethod = outputMethod;
        this.inputMethod = inputMethod;
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public String getOutputFilePath() {
        return outputFilePath;
    }

    public String getInputFilePath() {
        return inputFilePath;
    }

    public String getCipher() {
        return cipher;
    }

    public String getMode() {
        return mode;
    }

    public String getOutputMethod() {
        return outputMethod;
    }

    public String getInputMethod() {
        return inputMethod;
    }

    public int getKey() {
        return key;
    }

}
