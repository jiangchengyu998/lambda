
@FunctionalInterface
public interface Converter<F, T> {
    T convert(F from);
    default void sayHello(){
        System.out.println("hello jdk8!");
    }
}