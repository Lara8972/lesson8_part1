package homework06.TVset;

public class App {
    public static void main(String[] args) {
        // экземпляры телевизоров
        TVset TV1 = new TVset("Samsung", 55);
        TVset TV2 = new TVset("LG", 43);
        TVset TV3 = new TVset("Sony", 1100);

        // Проверяем на консоли
        TV1.info();

        System.out.println();
        // Пустая строка для разделения
        TV2.info();

        System.out.println();
        // Пустая строка для разделения
        TV3.info();
    }
}
