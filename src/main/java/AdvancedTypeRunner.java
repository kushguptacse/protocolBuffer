import com.advanced.example.AdvancedTypeExampleProto.AdvancedType;
import com.advanced.example.AdvancedTypeExampleProto.Demo;
import com.example.tutorial.AddressBook;
import com.example.tutorial.Person;
import com.example.tutorial.Person.PhoneNumber;
import com.example.tutorial.Person.PhoneType;
import com.google.protobuf.Duration;
import com.google.protobuf.Timestamp;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

public class AdvancedTypeRunner {

  public static void main(String[] args) {
    System.out.println("Inside Advanced Type Examples - ");
    AdvancedType.Builder builder = AdvancedType.newBuilder();
    builder.setId(123);
    //timestamp
    builder.setCurrentTime(Timestamp.newBuilder().setNanos(12).setSeconds(24));
    //duration
    builder.setDelay(Duration.newBuilder().setNanos(10).setSeconds(20));
    //map example
    builder.putDemos("key1", Demo.newBuilder().setDummy("dummy value").build());
    //one-of example. since name field is set at last it will ignore count field
    builder.setCount(1);
    builder.setName("oneof name");
    //print
    System.out.println(builder.build().toString());
    builder.getDemosMap().forEach((k,v)->System.out.println("key: "+k+",value: "+v));
  }

}
