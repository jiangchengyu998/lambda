public class ConverterTest {

    public static void main(String[] args) {

        int num = 1;

        Converter<Integer, String> converter = (from) -> String.valueOf(from + num);

        System.out.println(converter.convert(123));
    }

}
