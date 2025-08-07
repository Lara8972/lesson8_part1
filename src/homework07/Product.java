package homework07;

import java.util.Objects;
import java.time.LocalDate;



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

        if (name.trim().length() < 3) {
            System.out.println("Название продукта не может быть короче 3 символов");
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
        if (cost <= 0) {
            System.out.println("Стоимость не может быть отрицательной или равна нулю");
            return;
        }
        this.cost = cost;
    }

    //Переопределение equals для сравнения продуктов

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.cost, cost) == 0 &&
                Objects.equals(name, product.name);
    }

    //Переопределение hashCode
    @Override
    public int hashCode() {
        return Objects.hash(name, cost);
    }

    //класс Продукты со скидкой, который наследуется из класса продукты
    public static class DiscountProduct extends Product {
        private double discount;  // Размер скидки в процентах (например, 20 для 20%)
        private LocalDate endsDate;// Дата окончания действия скидки

        public DiscountProduct(String name, double cost, double discount, LocalDate endsDate) {
            super(name, cost);
            setDiscount(discount);
            this.endsDate = endsDate;
        }

        // геттер размера скидки в %
        public double getDiscount() {
            return discount;
        }

        // сеттер скидка должна быть в диапазоне от 0 до 100
        public void setDiscount(double discount) {
            if (discount <= 0 || discount > 100) {
                System.out.println("Размер скидки должен быть между 0 и 100");
                return;
            }
            this.discount = discount;
        }

        // Если скидка действует, то считается цена со скидкой, иначе - цена без скидки.
        // currentDate текущая дата для проверки срока действия скидки
        public double getCurrentCost(LocalDate currentDate) {
            if (currentDate.isBefore(endsDate)) {// Скидка действует
                double discountedPrice = getCost() * (1 - discount / 100);
                return discountedPrice;
            } else {// Скидка истекла
                return getCost();
            }
        }

        // геттер для даты окончания скидки
        public LocalDate getendsDate() {
            return endsDate;
        }

        //сеттер даты окончания скидки
        public void setEndsDate(LocalDate endsDate) {
            this.endsDate = endsDate;
        }
    }
}



