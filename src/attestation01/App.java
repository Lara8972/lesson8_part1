package attestation01;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите имя покупателя:");
        String personName = "";
        while (personName.isEmpty() || personName.length() < 3) {
            personName = scanner.nextLine().trim();
            if (personName.isEmpty()) {
                System.out.println("Имя не может быть пустым. Введите снова:");
            } else if (personName.length() < 3) {
                System.out.println("Имя не может быть короче 3 символов. Введите снова:");
            }
        }
    }
}
