import com.example.tutorial.AddressBook;
import com.example.tutorial.Person;
import com.example.tutorial.Person.PhoneNumber;
import com.example.tutorial.Person.PhoneType;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

public class AssignmentRunner {

  public static void main(String[] args) {
    System.out.println("Testing Write and Read operation");
    AddressBook.Builder addressBuilder = AddressBook.newBuilder();
    addressBuilder.addPeople(
        getPersonObject("virat", 123, "virat12@gmail.com",
            getPhoneNumbers(getPhoneNumberInstance("9998887771",
                PhoneType.MOBILE), getPhoneNumberInstance("120120001",
                PhoneType.HOME))));
    addressBuilder.addPeople(
        getPersonObject("sachin", 124, "sachin12@gmail.com",
            getPhoneNumbers(getPhoneNumberInstance("9998887772",
                PhoneType.WORK), getPhoneNumberInstance("120120002",
                PhoneType.HOME))));
    AddressBook addressBook = addressBuilder.build();

    writeFile(addressBook, "addressbookSer.bin");
    System.out.println();
    System.out.println("--------------------------------------------");
    System.out.println(readFile("addressbookSer.bin").toString());
  }

  private static void writeFile(AddressBook addressBook, String filePath) {
    System.out.println("Saving the AddressBook onto File System : " + filePath);
    try (OutputStream stream = new FileOutputStream(filePath)) {
      addressBook.writeTo(stream);
    } catch (Exception ex) {
      System.out.println(ex);
    }
  }

  private static AddressBook readFile(String filePath) {
    System.out.println("Reading the AddressBook from File System : " + filePath);
    try (InputStream stream = new FileInputStream(filePath)) {
      return AddressBook.parseFrom(stream);
    } catch (Exception ex) {
      System.out.println(ex);
    }
    return null;
  }

  private static PhoneNumber getPhoneNumberInstance(String phoneNumber, PhoneType phoneType) {
    PhoneNumber.Builder builder = PhoneNumber.newBuilder();
    builder.setNumber(phoneNumber);
    builder.setType(phoneType);
    return builder.build();
  }

  private static List<PhoneNumber> getPhoneNumbers(
      PhoneNumber... phoneNumber) {
    return Arrays.asList(phoneNumber);
  }

  private static Person getPersonObject(String name, int id, String email,
      List<PhoneNumber> phoneNumbers) {
    Person.Builder builder = Person.newBuilder();
    builder.setName(name);
    builder.setId(id);
    builder.setEmail(email);
    builder.addAllPhones(phoneNumbers);
    return builder.build();
  }
}
