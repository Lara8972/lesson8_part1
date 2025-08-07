package homework07;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


class Person {
    private String name;            // Имя покупателя
    private double money;           // Количество денег
    private List<homework07.Product> products; // Пакет с продуктами

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
    public List<homework07.Product> getProducts() {
        return products;
    }

    //Метод покупки продукта, если покупка успешна - true, false - если не хватило денег
    public boolean buyProduct(homework07.Product product) {
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
        if (products.isEmpty()) {
            return name + " - Ничего не куплено";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(name).append(" - ");
            for (int i = 0; i < products.size(); i++) {
                Product p = products.get(i);
                double price;
                String note = "";
                if (p instanceof Product.DiscountProduct) {
                    Product.DiscountProduct dp = (Product.DiscountProduct) p;
                    price = dp.getCurrentCost(LocalDate.now());
                    note = (LocalDate.now().isBefore(dp.getendsDate())) ? " (со скидкой)" : " (без скидки)";
                } else {
                    price = p.getCost();
                }
                sb.append(p.getName()).append(" (").append(price).append("₽)").append(note);
                if (i != products.size() - 1) sb.append(", ");
            }
            return sb.toString();
        }
    }
}