import java.util.function.Supplier;

public class SupplierTest {

    public static void main(String[] args) {
        // supplier 供应商
        Supplier<Person> personSupplier = Person::new;
        System.out.println(personSupplier.get());
    }
}
