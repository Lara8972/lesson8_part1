package homework011.test;
import homework011.model.Car;

import java.util.*;
import java.util.stream.*;

public class Main extends Car {// класс Main наследник класса Car и его параметров
    public Main(String number, String model, String color, int mileage, int price) {
        super(number, model, color, mileage, price);
    }

    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();
        // Создаю список автомобилей
        cars = Arrays.asList(
                new Car("a123me", "Mercedes", "White", 0, 8300000),
                new Car("b873of", "Volga", "Black", 0, 673000),
                new Car("w487mn", "Lexus", "Grey", 76000, 900000),
                new Car("p987hj", "Volga", "Red", 610, 704340),
                new Car("c987ss", "Toyota", "White", 254000, 761000),
                new Car("o983op", "Toyota", "Black", 698000, 740000),
                new Car("p146op", "BMW", "White", 271000, 850000),
                new Car("u893ii", "Toyota", "Purple", 210900, 440000),
                new Car("l097df", "Toyota", "Black", 108000, 780000),
                new Car("y876wd", "Toyota", "Black", 160000, 1000000)
        );

        // Входные данные для поиска
        String colorToFind = "Black";
        long mileageToFind = 0L;
        long priceFrom = 700_000L;
        long priceTo = 800_000L;
        String modelToFind1 = "Toyota";
        String modelToFind2 = "Volvo";

        // Печать базы
        System.out.println("Автомобили в базе:");
        System.out.println("Number Model Color Mileage Cost");
        cars.forEach(System.out::println);

        // Номера по цвету или пробегу
        List<String> numbers = cars.stream()
                .filter(c -> c.getColor().equalsIgnoreCase(colorToFind) || c.getMileage() == mileageToFind)
                .map(Car::getNumber)
                .collect(Collectors.toList());

        System.out.println("Номера автомобилей по цвету или пробегу: " + String.join(" ", numbers));

        // Количество уникальных автомобилей в диапазоне цен
        long uniqueCars = cars.stream()
                .filter(c -> c.getCost() >= priceFrom && c.getCost() <= priceTo)
                .map(Car::getNumber)
                .distinct()
                .count();
        System.out.println("Уникальные автомобили: " + uniqueCars + " шт.");

        // Цвет автомобиля с минимальной стоимостью
        String minCostColor = cars.stream()
                .min(Comparator.comparingLong(Car::getCost))
                .map(Car::getColor)
                .orElse("N/A");

        System.out.println("Цвет автомобиля с минимальной стоимостью: " + minCostColor);

        // Средняя стоимость модели
        double avgToyota = cars.stream()
                .filter(c -> c.getModel().equalsIgnoreCase(modelToFind1))
                .mapToLong(Car::getCost)
                .average()
                .orElse(0.0);

        double avgVolvo = cars.stream()
                .filter(c -> c.getModel().equalsIgnoreCase(modelToFind2))
                .mapToLong(Car::getCost)
                .average()
                .orElse(0.0);

        System.out.printf("Средняя стоимость модели %s: %,.2f%n", modelToFind1, avgToyota);
        System.out.printf("Средняя стоимость модели %s: %,.2f%n", modelToFind2, avgVolvo);
    }
}
