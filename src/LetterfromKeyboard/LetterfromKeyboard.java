package LetterfromKeyboard;
import java.util.Scanner;

public class LetterfromKeyboard {
    public static void main(String[] args){
        // Создан объект scan для чтения ввода из консоли
        Scanner scan = new Scanner(System.in);

        // создана строка, которая содержит все буквы клавиатуры английского алфавита попорядку
        String keyboard = "qwertyuiopasdfghjklzxcvbnm";
        //ввожу символ
        System.out.println("Введите букву английского алфавита: ");

        // считываю введеные символы, если введен не один символ,
        // то забираю первый символ
        char inputLetter = scan.nextLine().charAt(0);

        // выполняю поиск индекса введенного символа из строки keyboard

        int index = keyboard.indexOf(inputLetter);

        // запускаю цикл если - то
        if (index == -1) {
            // Если введенный символ в консоль не найден в строке keyboard, то вывести
            // сообщение
            System.out.println("Введенный символ не является буквой английского алфавита");

        } else {
            // если введенная буква есть в строке keyboard, то
            // находим индекс буквы слева из зацикленной строки keyboard
            int leftIndex = (index - 1 + keyboard.length()) % keyboard.length();
            //если index = 0 (первый символ из строки keyboard), мы получаем последний символ строки
            // для остальных берем предыдущий символ

            // Выводим букву слева от введенной
            System.out.println(keyboard.charAt(leftIndex));
            // Выводим букву слева от введенной
        }
        scan.close();
    }
}

