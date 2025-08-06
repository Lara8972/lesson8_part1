package attestation01;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private static Object person;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Ввод данных покупателя
        System.out.println("Введите покупателей в формате: Имя = Деньги; Имя = Деньги");
        List<Person> persons = new ArrayList<>();

        String personsLine = scanner.nextLine().trim();

        Person person = null;
        if (!personsLine.isEmpty()) {
            String[] personsArray = personsLine.split(";");
            for (String personStr : personsArray) {
                personStr = personStr.trim();
                if (personStr.isEmpty()) continue;

                String[] parts = personStr.split("=");
                if (parts.length != 2) {
                    System.out.println("Некорректный формат Имени: " + personStr);
                    continue;
                }

                String name = parts[0].trim();
                String moneyStr = parts[1].trim();
                try {
                    double money = Double.parseDouble(moneyStr);
                    if (name.isEmpty()) {
                        System.out.println("Имя не может быть пустым");
                        continue;
                    }
                    if (name.length() < 3) {
                        System.out.println("Имя не может быть короче 3 символов");
                        continue;
                    }
                    if (money < 0) {
                        System.out.println("Деньги не могут быть отрицательным числом");
                        continue;
                    }
                    person = new Person(name, money);
                    persons.add(person);
                } catch (NumberFormatException e) {
                    System.out.println("Некорректное число денег: " + moneyStr);
                } catch (IllegalArgumentException e) {
                }
            }
        }

        System.out.println("Введите продукты в формате: Название = Цена; Название = Цена");
// создала список с каталогом продуктов
        List<Product> productsCatalog = new ArrayList<>();
// пока не завершится цикл (end) ввожу строку
        while (true) {
            try {
                String line = scanner.nextLine().trim();
                if (line.equalsIgnoreCase("END")) break;
                // если строка пустая, пропускает, вводим строку
                if (line.isEmpty()) continue;
                // делю строку на отдельные записи через разделение ";";
                String[] productEntries = line.split(";");
                // в каждой записи убираю пробелы и пустые записи
                for (String entry : productEntries) {
                    entry = entry.trim();
                    if (entry.isEmpty()) continue;
                    // делю строку на продукт и цену через "=" на 2 части, если ошибка ввода, выводим предупреждение
                    String[] parts = entry.split("=");
                    if (parts.length != 2) {
                        System.out.println("Некорректный формат продукта: " + entry);
                        continue;
                    }

                    String productName = parts[0].trim();
                    // преобразование в число с типом double, если ошибка - предупреждение
                    double productCost;
                    try {
                        productCost = Double.parseDouble(parts[1].trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Некорректное число для стоимости продукта: " + parts[1]);
                        continue;
                    }

                    if (productName.isEmpty()) {
                        System.out.println("Название продукта не может быть пустым");
                        continue;
                    }

                    if (productCost < 0) {
                        System.out.println("Стоимость продукта не может быть отрицательной");
                        continue;
                    }
                    //создается новый объект на основании введенных данных  в каталог
                    Product product = new Product(productName, productCost);
                    productsCatalog.add(product);
                }

            } catch (Exception e) { //если любые неожиданные ошибки - предупреждение
                System.out.println("Некорректный ввод. Попробуйте снова.");
            }
        }

        System.out.println("За покупками:");

        while (true) {
            System.out.println("Введите покупателя и продукт через - , пример: Павел - Хлеб");

            String line = scanner.nextLine().trim();// считываю строку, удаляя пробелы

            if (line.equalsIgnoreCase("END")) break;

            try {
                String[] parts = line.split("-");// делю строку на 2 части (Имя - продукт), если ошибка - предупреждение
                if (parts.length < 2) {
                    System.out.println("Некорректный ввод. Попробуйте снова.");
                    continue;
                }

                String buyerName = parts[0].trim();
                String productName = parts[1].trim();

                Person buyer = null; //если находится Имя (без учета регистра), выход из цикла
                for (Person p : persons) {
                    if (p.getName().equalsIgnoreCase(buyerName)) {
                        buyer = p;
                        break;
                    }
                }

                if (buyer == null) {// если не нашлось Имя, предупреждение и переход
                    System.out.println("Покупатель " + buyerName + " не найден.");
                    continue;
                }

                Product product = null; // проверка продукта в каталоге
                for (Product p : productsCatalog) {
                    if (p.getName().equalsIgnoreCase(productName)) {
                        product = p;
                        break;
                    }
                }
                if (product == null) {// если продукт не найден, предупреждение и переход
                    System.out.println("Продукт " + productName + " не найден.");
                    continue;
                }

                boolean success = buyer.buyProduct(product); // покупка товара

                if (success) {//проверка успешна покупка или нет, денег не хватило
                    System.out.println(buyer.getName() + " купил " + product.getName());
                } else {
                    System.out.println(buyer.getName() + " не может позволить себе " + product.getName());
                }

            } catch (Exception e) { // проверка на ошибки
                System.out.println("Ошибка ввода. Попробуйте снова.");
            }
        }

        // Вывод результатов о всех покупках каждого покупателя по Имени
        for(Person p:persons){
            System.out.println(p.toString());
        }

        scanner.close();
    }
}