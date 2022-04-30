package fpstyle;

public record Command(String message, String outputFilePath, String inputFilePath,
                      String cipher, String mode, String outputMethod,
                      String inputMethod, int key) {

}
