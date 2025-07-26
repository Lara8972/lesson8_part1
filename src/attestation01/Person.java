package attestation01;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

class Person{
    private String name; // имя
    private double money; // деньги
    private ArrayList<Product> bag; // продукты

    // имя (не короче 3 символов) и деньги (не отрицательные)

    public Person(String name, double money) {
        setName(name);
        //setMoney(money);
        this.bag = new ArrayList<>();
    }

    // Геттер имени
    public String getName() {
        return name;
    }

    // Сеттер имени с проверкой, новое имя
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Имя не может быть пустым");
            return;
        }
        if (name.length() < 3) {
            System.out.println("Имя не может быть короче 3 символов");
            return;
        }
        this.name = name;}

}