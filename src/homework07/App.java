package homework07;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private static Object person;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Ввод данных покупателя
        System.out.println("Введите покупателей в формате: Имя = Деньги; Имя = Деньги");
        List<homework07.Person> persons = new ArrayList<>();

        String personsLine = scanner.nextLine().trim();

        homework07.Person person = null;
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
                    person = new homework07.Person(name, money);
                    persons.add(person);
                } catch (NumberFormatException e) {
                    System.out.println("Некорректное число денег: " + moneyStr);
                } catch (IllegalArgumentException e) {
                }
            }
        }

        System.out.println("Введите продукты в формате: Название = Цена; Название = Цена");
        System.out.println("Название = Цена:Скидка:ДатаОкончания (YYYY-MM-DD)");
        System.out.println("Пример: Хлеб=30; Сыр=200:20:2025-09-01; Молоко=50");
// создала список с каталогом продуктов
        List<homework07.Product> productsCatalog = new ArrayList<>();
// пока не завершится цикл (end) ввожу строку
        while (true) {
            try {
                String line = scanner.nextLine().trim();
                if (line.equalsIgnoreCase("END")) break;
                if (line.isEmpty()) continue;

                String[] productEntries = line.split(";");
                for (String entry : productEntries) {
                    entry = entry.trim();
                    if (entry.isEmpty()) continue;

                    String[] parts = entry.split("=");
                    if (parts.length != 2) {
                        System.out.println("Некорректный формат продукта: " + entry);
                        continue;
                    }

                    String productName = parts[0].trim();
                    if (productName.isEmpty()) {
                        System.out.println("Название продукта не может быть пустым");
                        continue;
                    }
                    if (productName.length() < 3) {
                        System.out.println("Название продукта не может быть короче 3 символов");
                        continue;
                    }

                    // ✅ Проверяем: со скидкой или обычный продукт
                    if (parts[1].contains(":")) {
                        String[] discountParts = parts[1].split(":");
                        if (discountParts.length != 3) {
                            System.out.println("Некорректный формат скидочного продукта: " + parts[1]);
                            continue;
                        }

                        try {
                            double cost = Double.parseDouble(discountParts[0].trim());
                            double discount = Double.parseDouble(discountParts[1].trim());
                            LocalDate endDate = LocalDate.parse(discountParts[2].trim());

                            homework07.Product.DiscountProduct product =
                                    new homework07.Product.DiscountProduct(productName, cost, discount, endDate);
                            productsCatalog.add(product);
                        } catch (Exception e) {
                            System.out.println("Ошибка при разборе скидочного продукта: " + parts[1]);
                        }
                    } else {
                        try {
                            double productCost = Double.parseDouble(parts[1].trim());
                            homework07.Product product = new homework07.Product(productName, productCost);
                            productsCatalog.add(product);
                        } catch (NumberFormatException e) {
                            System.out.println("Некорректное число для стоимости продукта: " + parts[1]);
                        }
                    }
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

                homework07.Person buyer = null; //если находится Имя (без учета регистра), выход из цикла
                for (homework07.Person p : persons) {
                    if (p.getName().equalsIgnoreCase(buyerName)) {
                        buyer = p;
                        break;
                    }
                }

                if (buyer == null) {// если не нашлось Имя, предупреждение и переход
                    System.out.println("Покупатель " + buyerName + " не найден.");
                    continue;
                }

                homework07.Product product = null; // проверка продукта в каталоге
                for (homework07.Product p : productsCatalog) {
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
        for(homework07.Person p:persons){
            System.out.println(p.toString());
        }

        scanner.close();
    }
}