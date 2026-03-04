import java.util.*;
import java.util.stream.Collectors;

public class stream {

    public static void main(String[] args) {

        // INTEGER TASKS

        List<Integer> numbers = Arrays.asList(6, 3, 1, 8, 2, 3, 10, 2);

        // 1. Only even numbers
        List<Integer> evens = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Even numbers: " + evens);

        // 2. Sort descending
        List<Integer> sortedDesc = numbers.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println("Sorted descending: " + sortedDesc);

        // 3. Max and Min
        int max = numbers.stream()
                .mapToInt(n -> n)
                .max()
                .orElse(0);

        int min = numbers.stream()
                .mapToInt(n -> n)
                .min()
                .orElse(0);

        System.out.println("Max: " + max);
        System.out.println("Min: " + min);

        // 4. Remove duplicates
        List<Integer> unique = numbers.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Unique numbers: " + unique);


        // STRING TASKS

        List<String> names = Arrays.asList("Alice", "Bob", "Angela", "Charlie");

        // 5. Convert to uppercase
        List<String> upper = names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Uppercase: " + upper);

        // 6. Count names starting with A
        long countA = names.stream()
                .filter(name -> name.startsWith("A"))
                .count();
        System.out.println("Names starting with A: " + countA);

        // 7. Join into comma-separated string
        String joined = names.stream()
                .collect(Collectors.joining(", "));
        System.out.println("Joined names: " + joined);


        // STUDENT TASKS

        List<Student> students = Arrays.asList(
                new Student("Anna", "A1", 3.8),
                new Student("Bob", "A1", 3.2),
                new Student("Chris", "B1", 3.9),
                new Student("Diana", "A1", 3.6),
                new Student("Eric", "B1", 2.9)
        );

        // 8. Group by group
        Map<String, List<Student>> grouped = students.stream()
                .collect(Collectors.groupingBy(s -> s.group));
        System.out.println("Grouped by group: " + grouped);

        // 9. Average GPA
        double avgGpa = students.stream()
                .mapToDouble(s -> s.gpa)
                .average()
                .orElse(0.0);
        System.out.println("Average GPA: " + avgGpa);

        // 10. First 3 students with GPA > 3.5
        List<Student> top3 = students.stream()
                .filter(s -> s.gpa > 3.5)
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("First 3 students GPA > 3.5: " + top3);

        // 11. Count students with GPA > 3.5
        long countHighGpa = students.stream()
                .filter(s -> s.gpa > 3.5)
                .count();
        System.out.println("Students with GPA > 3.5: " + countHighGpa);
    }
}


// Student class

class Student {
    String name;
    String group;
    double gpa;

    Student(String name, String group, double gpa) {
        this.name = name;
        this.group = group;
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return name + "(" + group + ", GPA=" + gpa + ")";
    }
}