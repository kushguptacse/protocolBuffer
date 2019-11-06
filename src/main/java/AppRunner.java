import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import simple.test.Simple;
import simple.test.Simple.SimpleMessage;
import simple.test.Simple.SimpleMessage.Builder;

public class AppRunner {

  public static void main(String[] args) {
    System.out.println("hello world!");
    Builder messageBuilder = SimpleMessage.newBuilder();
    messageBuilder.setText("first proto message");
    SimpleMessage simple = messageBuilder.build();
    System.out.println("-------------------------------");
    System.out.println(simple.toString());
    writeFile(simple, "simpleSer.bin");
    readFile("simpleSer.bin");
  }

  private static void writeFile(SimpleMessage simple, String filePath) {
    System.out.println("-------------------------------");
    System.out.println("writing message to file: " + filePath);
    try (OutputStream out = new FileOutputStream(filePath)) {
      simple.writeTo(out);
      // To get byte array below commented method can be used-
      // person.toByteArray();
    } catch (IOException ex) {
      System.out.println(ex);
    }

  }

  private static void readFile(String filePath) {
    try (InputStream in = new FileInputStream(filePath)) {
      SimpleMessage simple = Simple.SimpleMessage.parseFrom(in);
      System.out.println("-------------------------------");
      System.out.println(simple.toString());
    } catch (IOException ex) {
      System.out.println(ex);
    }
  }
}
