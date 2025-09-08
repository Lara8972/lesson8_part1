package homework08;
import java.util.ArrayList;

public class Uniques {
    public static <T> ArrayList<T> getUniques(ArrayList<T> list) { //Метод для извлечения уникальных элементов из списка
        ArrayList<T> uniqueList = new ArrayList<>(); // новый список для хранения уникальных элементов

        for (T element : list) { // прохожу по каждому элементу списка
            // проверяю, не содержится ли элемент уже в списке уникальных
            if (!uniqueList.contains(element)) {
                // если элемент уникален, добавляю его в uniqueList
                uniqueList.add(element);
            }
        }

        return uniqueList; // список с уникальными элементами
    }

    public static void main(String[] args) {
        // создала и заполнила ArrayList именами (элементами)
        ArrayList<String> name = new ArrayList<>();
        name.add("Федор");
        name.add("Степан");
        name.add("Алиса");
        name.add("Степан");
        name.add("Федор");

        ArrayList<String> uniqueName = getUniques(name);
        System.out.println(uniqueName);  // вывод в консоль
    }
}

