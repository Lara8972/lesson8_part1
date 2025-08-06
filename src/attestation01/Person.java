package attestation01;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class Person {
    private String name;            // Имя покупателя
    private double money;           // Количество денег
    private List<Product> products; // Пакет с продуктами

    // Конструктор покупателя Имя = Деньги
    public Person(String name, double money) {
        setName(name);
        setMoney(money);
        this.products = new ArrayList<>();
    }

    // Геттер имени
    public String getName() {
        return name;
    }

    //Сеттер имени
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Имя не может быть пустым");
            return;
        }
        if (name.trim().length() < 3) {
            System.out.println("Имя не может быть короче 3 символов");
            return;
        }
        this.name = name.trim();
    }

    // Геттер денег
    public double getMoney() {
        return money;
    }

    // Сеттер денег
    public void setMoney(double money) {
        if (money < 0) {
            System.out.println("Деньги не могут быть отрицательными");
            return;
        }
        this.money = money;
    }

    // Геттер пакета
    public List<Product> getProducts() {
        return products;
    }

    //Метод покупки продукта, если покупка успешна - true, false - если не хватило денег
    public boolean buyProduct(Product product) {
        if (this.money >= product.getCost()) {
            this.money -= product.getCost();
            this.products.add(product);
            return true;
        } else {
            return false;
        }
    }

    // Переопределение toString для вывода информации о покупателе
    @Override
    public String toString() {
        if (products.isEmpty()){
            return name + " - Ничего не куплено";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(name).append(" - ");
            for (int i = 0; i < products.size(); i++) {
                sb.append(products.get(i).getName());
                if (i != products.size() - 1) sb.append(", ");
            }
            return sb.toString();
        }
    }
}