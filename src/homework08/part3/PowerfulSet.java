package homework08.part3;

import java.util.HashSet;
import java.util.Set;

public class PowerfulSet {

    // пересечение двух множеств
    public <T> Set<T> intersection(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set1);
        result.retainAll(set2); // Оставляю только элементы, которые есть в обоих множествах
        return result;
    }

    // объединения двух множеств
    public <T> Set<T> union(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set1);
        result.addAll(set2); // Добавляю все элементы из второго множества
        return result;
    }

    // нахождение относительного дополнения (элементы первого множества, которых нет во втором)
    public <T> Set<T> relativeComplement(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set1);
        result.removeAll(set2); // Удаляю из первого множества элементы, которые есть во втором
        return result;
    }

    // Пример
    public static void main(String[] args) {
        PowerfulSet powerfulSet = new PowerfulSet();

        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        set1.add(1);
        set1.add(2);
        set1.add(3);

        set2.add(0);
        set2.add(1);
        set2.add(2);
        set2.add(4);

        // Пересечение множеств
        System.out.println("Пересечение: " + powerfulSet.intersection(set1, set2));

        // Объединение множеств
        System.out.println("Объединение: " + powerfulSet.union(set1, set2));

        // Относительное дополнение
        System.out.println("Относительное дополнение: " + powerfulSet.relativeComplement(set1, set2));
    }
}