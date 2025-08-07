package homework06.TVset;
    // класс телевизор
public class TVset {

        // поля private
    private String model;
    private int chanal;
    // конструктор
    public TVset(String model, int chanal) {
        this.model = model;
        this.chanal = chanal;
    }
    // информация о телевизоре
    public void info() {
        System.out.println("Марка: " + model + " Текущий канал: " + chanal);
    }
}