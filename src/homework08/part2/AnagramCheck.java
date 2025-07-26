package homework08.part2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class AnagramCheck {

    public static boolean isAnagram(String s, String t) {
        // Если длина строк разная - не могут быть анаграммами
        if (s.length() != t.length()) {
            return false;
        }

        // HashMap для подсчета частоты символов
        Map<Character, Integer> charCountMap = new HashMap<>();

        // частоту символов в первой строке
        for (char c : s.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }

        // Уменьшаем частоту символов на основе второй строки
        for (char c : t.toCharArray()) {
            if (!charCountMap.containsKey(c)) {
                return false;  // Если символ из второй строки не найден в первой, возвращаем false
            }
            charCountMap.put(c, charCountMap.get(c) - 1);
        }

        // Если все значения в HashMap равны нулю, значит строки - анаграммы
        for (int count : charCountMap.values()) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод строки
        System.out.println("Введите первую строку:");
        String s = scanner.nextLine().trim();
        System.out.println("Введите вторую строку:");
        String t = scanner.nextLine().trim();

        // Проверка на анаграмму
        if (isAnagram(s, t)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

        scanner.close();
    }
}
