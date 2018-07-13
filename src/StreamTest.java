import java.util.*;
import java.util.concurrent.TimeUnit;

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

        // Stream提供了多种匹配操作，允许检测指定的Predicate是否匹配整个Stream。所有的匹配操作都是最终操作，并返回一个boolean类型的值。
        boolean anyStartsWithA =
                stringCollection
                        .stream()
                        .anyMatch((s) -> s.startsWith("a"));
        System.out.println(anyStartsWithA);      // true

        boolean allStartsWithA =
                stringCollection
                        .stream()
                        .allMatch((s) -> s.startsWith("a"));

        System.out.println(allStartsWithA);      // false

        boolean noneStartsWithZ =
                stringCollection
                        .stream()
                        .noneMatch((s) -> s.startsWith("z"));

        System.out.println(noneStartsWithZ);      // true

        // 计数是一个最终操作，返回Stream中元素的个数，返回值类型是long。
        long startsWithB =
                stringCollection
                        .stream()
                        .filter((s) -> s.startsWith("b"))
                        .count();
        System.out.println(startsWithB);    // 3


        // Reduce 规约
        // 这是一个最终操作，允许通过指定的函数来讲stream中的多个元素规约为一个元素，规越后的结果是通过Optional接口表示的：
        Optional<String> reduced =
                stringCollection
                        .stream()
                        .sorted()
                        .reduce((s1, s2) -> s1 + "#" + s2);
        reduced.ifPresent(System.out::println);
// "aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2"

        /**
         * 并行Streams
         前面提到过Stream有串行和并行两种，串行Stream上的操作是在一个线程中依次完成，而并行Stream则是在多个线程上同时执行。

         下面的例子展示了是如何通过并行Stream来提升性能：

         首先我们创建一个没有重复元素的大表：
         */
//        int max = 1000000;
//        List<String> values = new ArrayList<>(max);
//        for (int i = 0; i < max; i++) {
//            UUID uuid = UUID.randomUUID();
//            values.add(uuid.toString());
//        }

        // 计算看看排序耗时多久（不用并行功能）
//        long t0 = System.nanoTime();
//        long count = values.stream().sorted().count();
//        System.out.println(count);
//
//        long t1 = System.nanoTime();
//
//        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
//        System.out.println(String.format("sequential sort took: %d ms", millis));

        // 计算看看排序耗时多久（并行功能）
//        long t0 = System.nanoTime();
//        long count = values.parallelStream().sorted().count();
//        System.out.println(count);
//
//        long t1 = System.nanoTime();
//
//        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
//        System.out.println(String.format("parallel sort took: %d ms", millis));

        /**
         * Map
         前面提到过，Map类型不支持stream，不过Map提供了一些新的有用的方法来处理一些日常任务。
         */


        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }
//        map.forEach((id, val) -> System.out.println(val));
//
//        map.computeIfPresent(3, (num, val) -> val + num);
//        map.get(3);             // val33
//        System.out.println("map.get(3)"+map.get(3));
//        map.computeIfPresent(9, (num, val) -> null);
//        map.containsKey(9);     // false
//
//        map.computeIfAbsent(23, num -> "val" + num);
//        map.containsKey(23);    // true
//        System.out.println("========");
//        System.out.println(map.get(23));
//
//        map.remove(3);
//        map.computeIfAbsent(3, num -> "bam");
//        map.get(3);             // val33
//        System.out.println("map.get(3)+1 = "+map.get(3));


        // 接下来展示如何在Map里删除一个键值全都匹配的项：
        map.remove(3, "val3");
        map.get(3);             // val33
        map.remove(3, "val33");
        map.get(3);             // null
        System.out.println(map.get(3));
    }
}
