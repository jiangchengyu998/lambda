public class Personmain {
    public static void main(String[] args) {

        // ����ֻ��Ҫʹ�� Person::new ����ȡPerson�๹�캯�������ã�Java���������Զ�����PersonFactory.create������ǩ����ѡ����ʵĹ��캯����
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");
        System.out.println(person);
    }
}
