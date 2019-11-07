import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.simple.test.RepeatedSampleMessageProto.RepeatedSampleMessage;
import java.util.Arrays;

public class JsonParseMain {

  public static void main(String[] args) {
    System.out.println("------------------Inside JSON parse api--------------------");
    RepeatedSampleMessage.Builder builder = RepeatedSampleMessage.newBuilder();
    builder.setText("simple text !!");
    builder.addAllItems(Arrays.asList("1", "2", "4"));
    try {
      //convert builder to json
      System.out.println("Getting json from builder - ");
      String json = JsonFormat.printer().print(builder);
      System.out.println(json);

      //convert json to builder
      System.out.println("Getting builder from json- ");
      RepeatedSampleMessage.Builder builder2 = RepeatedSampleMessage.newBuilder();
      JsonFormat.parser().ignoringUnknownFields().merge(json, builder2);
      System.out.println(builder2.toString());
    } catch (InvalidProtocolBufferException ex) {
      System.out.println(ex);
    }
  }

}
