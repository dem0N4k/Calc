import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        while (true) {
            System.out.println("\nEnter expression:");                              // т.к. в примере требований к заданию ввод/вывод были на английском, то и это так же
            String expression = new Scanner(System.in).nextLine().trim();
            System.out.println("Input:" + "\n" + expression + "\n");

            String[] split = expression.split("[/*+-]");
            char[] chars = expression.toCharArray();

            if (split.length == 1) {                                                    // выброс иисключения для ввода одного операнда
                throw new Exception("Cтрока не является математической операцией");
            }

            if (split.length == 2) {                                                    // проверка на длинну строки

                if (expression.matches("[1234567890 /*+-]+")) {                // проверка на ввод только цифр и необходимых знаков, арабские

                    int a = Integer.parseInt(split[0].trim());
                    int b = Integer.parseInt(split[1].trim());

                    if (a >= 1 && a <= 10 && b >= 1 && b <= 10) {                   // ограничитель чисел от 1 до 10 включительно

                        for (char symbol : chars) {
                            switch (symbol) {
                                case ('+'):
                                    System.out.println("Output:\n" + (a + b));
                                    break;
                                case ('-'):
                                    System.out.println("Output:\n" + (a - b));
                                    break;
                                case ('*'):
                                    System.out.println("Output:\n" + (a * b));
                                    break;
                                case ('/'):
                                    System.out.println("Output:\n" + (a / b));
                                    break;
                            }
                        }
                    } else {
                        throw new Exception("Формат математической операции не удовлетворяет заданию " +
                                "- операнд вне допустимого диапазона (от 1 до 10)");
                    }
                } else if (expression.matches("[IVXLC /*+-]+")) {                             // проверка на римские цифры + символы

                    RomanNumerals a = RomanNumerals.valueOf(split[0].trim());
                    RomanNumerals b = RomanNumerals.valueOf(split[1].trim());

                    if (a.getNumber() >= 1 && a.getNumber() <= 10 && b.getNumber() >= 1 && b.getNumber() <= 10) {

                        for (char symbol : chars) {
                            switch (symbol) {
                                case ('+'):
                                    System.out.println("Output:\n" + getByValue(a.getNumber() + b.getNumber()));
                                    break;
                                case ('-'):
                                    System.out.println("Output:\n" + getByValue(a.getNumber() - b.getNumber()));
                                    break;
                                case ('*'):
                                    System.out.println("Output:\n" + getByValue(a.getNumber() * b.getNumber()));
                                    break;
                                case ('/'):
                                    System.out.println("Output:\n" + getByValue(a.getNumber() / b.getNumber()));
                                    break;
                            }
                        }
                    } else {
                        throw new Exception("Формат математической операции не удовлетворяет заданию " +
                                "- операнд вне допустимого диапазона (от I до X)");
                    }
                } else {
                    throw new Exception("Используются разные системы ссчисления");
                }
            } else {
                throw new Exception("Формат математической операции не удовлетворяет заданию " +
                        "- два операнда и один оператор (+, -, /, *)");
            }
        }
    }

    static RomanNumerals getByValue(int value) throws Exception {
        for (RomanNumerals romanNumerals : RomanNumerals.values()) {
            if (romanNumerals.getNumber() == value)
                return romanNumerals;
        }
        throw new Exception("В римской системе нет отрицательных чисел");
    }
}
