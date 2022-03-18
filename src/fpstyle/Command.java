package fpstyle;

public class Command {

    public final String message;
    public final String outputFilePath;
    public final String inputFilePath;
    public final String cipher;
    public final String mode;
    public final String outputMethod;
    public final String inputMethod;
    public final int key;


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
}
