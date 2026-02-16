import java.util.HashMap;
import java.util.Map;

public class studentscores {
    public static void main(String[] args) {


        // Create & Put
        HashMap<String, Integer> studentScores = new HashMap<>();
        studentScores.put("Aida", 85);
        studentScores.put("Azat", 92);
        studentScores.put("Dana", 77);

        // Печать карты и размера
        System.out.println("Initial map: " + studentScores);
        System.out.println("Size: " + studentScores.size());


        // Get & ContainsKey
        // Получение значения по ключу
        System.out.println("Score of Aida: " + studentScores.get("Aida"));

        // Проверка наличия ключа "Mira"
        if (studentScores.containsKey("Mira")) {
            System.out.println("Mira's score: " + studentScores.get("Mira"));
        } else {
            System.out.println("Mira not found in the map.");
        }


        // Update Existing Value

        Integer oldScore = studentScores.put("Dana", 80);
        System.out.println("Old score of Dana: " + oldScore);
        System.out.println("Updated map: " + studentScores);


        // Remove by Key
        // Удаление ключа "Azat"
        boolean removedAzat = studentScores.remove("Azat") != null;
        System.out.println("Azat removed: " + removedAzat);

        // Попытка удалить несуществующий ключ
        boolean removedNonExisting = studentScores.remove("NonExisting") != null;
        System.out.println("NonExisting removed: " + removedNonExisting);


        // isEmpty & clear

        System.out.println("Is map empty before clear? " + studentScores.isEmpty());
        studentScores.clear();
        System.out.println("Is map empty after clear? " + studentScores.isEmpty());

        // Вставляем снова некоторые элементы для продолжения
        studentScores.put("Aida", 85);
        studentScores.put("Azat", 92);
        studentScores.put("Dana", 77);

        // getOrDefault
        System.out.println("Score of Mira (getOrDefault): " + studentScores.getOrDefault("Mira", -1));
        System.out.println("Score of Aida (getOrDefault): " + studentScores.getOrDefault("Aida", -1));

        // putIfAbsent
        studentScores.putIfAbsent("Aida", 90);  // Aida уже есть, не изменится
        studentScores.putIfAbsent("Mira", 88);  // Mira нет, будет добавлен
        System.out.println("Map after putIfAbsent: " + studentScores);

        // replace
        studentScores.replace("Aida", 85, 86);  // Заменить, если старое значение 85
        studentScores.replace("Aida", 91);      // Безусловная замена
        System.out.println("Map after replace: " + studentScores);

        // попытка замены несуществующего ключа
        boolean replacedNonExisting = studentScores.replace("NonExisting", 100, 101); // Не произойдёт
        System.out.println("NonExisting replaced: " + replacedNonExisting);


        // Iterate over keys, values, entries
        System.out.println("\nAll keys:");
        for (String key : studentScores.keySet()) {
            System.out.println(key);
        }

        System.out.println("\nAll values:");
        for (Integer value : studentScores.values()) {
            System.out.println(value);
        }

        System.out.println("\nAll key=value pairs:");
        for (Map.Entry<String, Integer> entry : studentScores.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }


        // Count ≥ 80
        int count = 0;
        for (Integer score : studentScores.values()) {
            if (score >= 80) {
                count++;
            }
        }
        System.out.println("\nNumber of students with score ≥ 80: " + count);


        // Find max score
        int maxScore = Integer.MIN_VALUE;
        String topStudent = "";
        for (Map.Entry<String, Integer> entry : studentScores.entrySet()) {
            if (entry.getValue() > maxScore) {
                maxScore = entry.getValue();
                topStudent = entry.getKey();
            }
        }
        System.out.println("\nStudent with the highest score: " + topStudent + " with score " + maxScore);
    }
}
