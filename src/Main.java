import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] arg) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        //Совершеннолетние
        long count = persons.stream()
                .filter(value -> value.getAge() < 18)
                .count();
        System.out.println(count);

        //призывники
        List<String> army = persons.stream()
                .filter(value -> value.getAge() > 18 && value.getAge() < 57)
                .filter(sex -> sex.getSex() == Sex.MAN)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(army);

        //фамилии
        List<String> work = persons.stream()
                .filter(education -> education.getEducation() == Education.HIGHER)
                .filter(p->p.getAge()>=18)
                .filter(p->(p.getSex()==Sex.WOMAN&&p.getAge()<55)||
                        (p.getSex()==Sex.MAN&&p.getAge()<60))
                .map(Person::getFamily)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        System.out.println(work);
    }

}
