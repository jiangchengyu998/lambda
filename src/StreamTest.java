import java.util.ArrayList;
import java.util.List;

public class StreamTest {
    public static void main(String[] args) {
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

        stringCollection
                .stream()
                .filter((s) -> s.startsWith("a"))
                .forEach(System.out::println);
// "aaa2", "aaa1"
        System.out.println("=====================");
        stringCollection
                .stream()
                .sorted()
                .filter((s) -> s.startsWith("a"))
                .forEach(System.out::println);

        System.out.println("=====================");
        // 需要注意的是，排序只创建了一个排列好后的Stream，而不会影响原有的数据源，排序之后原数据stringCollection是不会被修改的：
//        System.out.println(stringCollection);

        stringCollection
                .stream()
                .map(String::toUpperCase)
                .sorted((a, b) -> b.compareTo(a))   // a与b的位置换了，说明是从小到大排序
                .forEach(System.out::println);
// "DDD2", "DDD1", "CCC", "BBB3", "BBB2", "AAA2", "AAA1"
    }
}
