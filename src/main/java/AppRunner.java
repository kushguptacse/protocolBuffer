
import com.simple.test.OptionMessage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import simple.test.EnumSample;
import simple.test.EnumSample.DayOfWeek;
import simple.test.EnumSample.EnumMessage;
import simple.test.Simple;
import simple.test.Simple.SimpleMessage;
import simple.test.Simple.SimpleMessage.Builder;
import test.simple.complex.Complex;
import test.simple.complex.Complex.ComplexMessage;
import test.simple.complex.Complex.DummyMessage;

public class AppRunner {

  public static void main(String[] args) {
    System.out.println("hello world!");
    testWriteAndRead();
    testEnum();
    testComplex();
    testOption();
  }

  private static void testOption() {
    System.out.println("-------------------------------------");
    System.out.println("Inside Option test method");
    OptionMessage.Builder builder = OptionMessage.newBuilder();
    System.out.println(builder.setId(123).setText1("hello").build().toString());
  }

  private static void testComplex() {
    System.out.println("-------------------------------------");
    System.out.println("Inside complex message test");
    Complex.ComplexMessage.Builder builder = Complex.ComplexMessage.newBuilder();
    Complex.DummyMessage.Builder dummyBuilder = DummyMessage.newBuilder().setName("sachin")
        .setId(124);
    builder.setDummy(dummyBuilder.build());
    builder.addDummies(DummyMessage.newBuilder().setName("virat").setId(125))
        .addDummies(DummyMessage.newBuilder().setName("kohli").setId(126));
    ComplexMessage complexMessage = builder.build();
    System.out.println(complexMessage.toString());
    System.out.println("List data is : ");
    // get list from complexMessage
    complexMessage.getDummiesList().forEach(System.out::println);

  }

  private static void testEnum() {
    System.out.println("Inside Enum method ");
    System.out.println("----------------------------------------");
    EnumSample.EnumMessage.Builder builder = EnumSample.EnumMessage.newBuilder();
    builder.setDay(DayOfWeek.FRIDAY);
    builder.setId(345);
    EnumMessage message = builder.build();
    System.out.println(message.toString());
  }

  private static void testWriteAndRead() {
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
