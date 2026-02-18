import java.util.*;

public class studentscores {
    public static void main(String[] args) {

        // ================= Create & Put =================
        HashMap<String, Integer> students = new HashMap<>();
        students.put("Aida", 85);
        students.put("Azat", 92);
        students.put("Dana", 77);
        System.out.println(students);
        System.out.println("Size: " + students.size());

        // ================= Get & containsKey =================
        System.out.println("Aida: " + students.get("Aida"));
        System.out.println("Mira: " + students.get("Mira"));
        if (students.containsKey("Mira"))
            System.out.println("Mira found");
        else
            System.out.println("Mira not found");

        // ================= Update =================
        Integer old = students.put("Dana", 80);
        System.out.println("Old Dana score: " + old);
        System.out.println(students);

        // ================= Remove =================
        System.out.println("Remove Azat: " + (students.remove("Azat") != null));
        System.out.println("Remove NonExisting: " + (students.remove("NonExisting") != null));

        // ================= isEmpty & clear =================
        System.out.println("Empty before clear: " + students.isEmpty());
        students.clear();
        System.out.println("Empty after clear: " + students.isEmpty());

        students.put("Aida", 85);
        students.put("Dana", 80);
        students.put("Azat", 92);

        // ================= getOrDefault =================
        String[] names = {"Aida", "Mira", "Dana"};
        for (String name : names) {
            int score = students.getOrDefault(name, -1);
            System.out.println(name + ": " + (score == -1 ? "Not found" : score));
        }

        // ================= putIfAbsent =================
        students.putIfAbsent("Aida", 90);
        students.putIfAbsent("Mira", 88);
        System.out.println(students);

        // ================= replace =================
        students.replace("Aida", 85, 86);
        students.replace("Aida", 91);
        System.out.println(students.replace("Unknown", 100));
        System.out.println(students);

        // ================= Iterate =================
        System.out.println("Keys: " + students.keySet());
        System.out.println("Values: " + students.values());
        for (Map.Entry<String, Integer> e : students.entrySet())
            System.out.println(e.getKey() + "=" + e.getValue());

        // Count ≥80
        int count = 0;
        for (int v : students.values())
            if (v >= 80) count++;
        System.out.println("Score >=80: " + count);

        // Max score
        int max = Integer.MIN_VALUE;
        String top = "";
        for (Map.Entry<String, Integer> e : students.entrySet()) {
            if (e.getValue() > max) {
                max = e.getValue();
                top = e.getKey();
            }
        }
        System.out.println("Max: " + top + " = " + max);

        // ================= Word Frequency =================
        String text = "Java is fun and Java is powerful and fun";
        HashMap<String, Integer> wordMap = new HashMap<>();
        for (String w : text.toLowerCase().split(" "))
            wordMap.put(w, wordMap.getOrDefault(w, 0) + 1);
        System.out.println(wordMap);

        // ================= Character Frequency =================
        String s = "Mississippi";
        HashMap<Character, Integer> charMap = new HashMap<>();
        for (char c : s.toCharArray())
            charMap.put(c, charMap.getOrDefault(c, 0) + 1);
        System.out.println(charMap);

        char most = 0; int maxFreq = 0;
        for (Map.Entry<Character,Integer> e : charMap.entrySet())
            if (e.getValue() > maxFreq) {
                maxFreq = e.getValue();
                most = e.getKey();
            }
        System.out.println("Most frequent: " + most);

        // ================= Group by Length =================
        String[] words = {"hi","book","java","sun","loop","map"};
        HashMap<Integer, ArrayList<String>> lengthMap = new HashMap<>();
        for (String w : words)
            lengthMap.computeIfAbsent(w.length(), k -> new ArrayList<>()).add(w);
        System.out.println(lengthMap);

        // ================= First Non-Repeating =================
        String str = "swiss";
        HashMap<Character,Integer> freq = new HashMap<>();
        for(char c: str.toCharArray())
            freq.put(c, freq.getOrDefault(c,0)+1);
        Character first = null;
        for(char c: str.toCharArray())
            if(freq.get(c)==1){ first=c; break; }
        System.out.println(first==null?"None":first);

        // ================= Two Sum =================
        int[] nums = {2,7,11,15};
        int target = 9;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int need = target - nums[i];
            if(map.containsKey(need)){
                System.out.println("Indices: "+map.get(need)+", "+i);
                break;
            }
            map.put(nums[i],i);
        }

        // ================= Detect Duplicates =================
        String[] fruits = {"apple","banana","apple","orange","banana","kiwi"};
        HashMap<String,Integer> fruitMap = new HashMap<>();
        for(String f:fruits)
            fruitMap.put(f, fruitMap.getOrDefault(f,0)+1);
        for(Map.Entry<String,Integer> e: fruitMap.entrySet()){
            if(e.getValue()==1)
                System.out.println("Unique: "+e.getKey());
            else
                System.out.println("Duplicate: "+e.getKey()+" x"+e.getValue());
        }

        // ================= Equals =================
        HashMap<String,Integer> m1 = new HashMap<>();
        HashMap<String,Integer> m2 = new HashMap<>();
        m1.put("A",1); m1.put("B",2);
        m2.put("B",2); m2.put("A",1);
        System.out.println("Equals: "+m1.equals(m2));
        System.out.println("HashCodes: "+m1.hashCode()+" "+m2.hashCode());

        // ================= Remove <60 =================
        Iterator<Map.Entry<String,Integer>> it = students.entrySet().iterator();
        while(it.hasNext())
            if(it.next().getValue()<60)
                it.remove();
        System.out.println(students);

        // ================= Merge =================
        HashMap<String,Integer> scores1 = new HashMap<>();
        scores1.put("Aida",40);
        scores1.put("Azat",35);
        scores1.put("Dana",50);

        HashMap<String,Integer> scores2 = new HashMap<>();
        scores2.put("Azat",10);
        scores2.put("Dana",5);
        scores2.put("Mira",45);

        for(Map.Entry<String,Integer> e: scores2.entrySet())
            scores1.merge(e.getKey(), e.getValue(), Integer::sum);
        System.out.println(scores1);

        // ================= Invert Map =================
        HashMap<Integer,List<String>> inverted = new HashMap<>();
        for(Map.Entry<String,Integer> e: students.entrySet())
            inverted.computeIfAbsent(e.getValue(),k->new ArrayList<>()).add(e.getKey());
        System.out.println(inverted);

        // ================= Top 2 Frequent Words =================
        List<Map.Entry<String,Integer>> list = new ArrayList<>(wordMap.entrySet());
        list.sort((a,b)->b.getValue()-a.getValue());
        System.out.println("Top1: "+list.get(0));
        System.out.println("Top2: "+list.get(1));
    }
}
