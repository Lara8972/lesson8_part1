package attestation01;

import java.util.Objects;

public class Product {
    private String name;  // Название продукта
    private double cost;  // Стоимость продукта

    //Конструктор продукта с проверками на название продукта (не пустое), стоимость (не отрицательная)
    public Product(String name, double cost) {
        setName(name);
        setCost(cost);
    }

    // Геттер Имени
    public String getName() {
        return name;
    }

    // новое Имя
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Имя не может быть пустым");
            return;
        }
        this.name = name.trim();
    }

    // Геттер стоимости
    public double getCost() {
        return cost;
    }

    // проверка новая стоимость
    public void setCost(double cost) {
        if (cost < 0) {
            System.out.println("Деньги не могут быть отрицательными");
            return;
        }
        this.cost = cost;
    }

    //Переопределение equals для сравнения продуктов

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        homework06.Product product = (homework06.Product) o;
        return Double.compare(product.getCost(), cost) == 0 &&
                Objects.equals(name, product.getName());
    }

    //Переопределение hashCode
    @Override
    public int hashCode() {
        return Objects.hash(name, cost);
    }
}