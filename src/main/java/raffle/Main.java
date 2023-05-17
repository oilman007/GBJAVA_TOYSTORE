package raffle;
import java.util.Scanner;

public class Main {
        public static void main(String[] args) {
            Raffle r = new Raffle();
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.print("""
                    Меню:
                    1 - Пополните призовой фонд новой игрушкой
                    2 - Измените частоту выпадения какой-нибудь игрушки
                    3 - Проведите розыгрыш и сохраните результаты
                    0 - Выход
                    >\s""");
                var choice = sc.next();
                switch (choice) {
                    case "1" -> r.addToy();
                    case "2" -> r.setFrequency();
                    case "3" -> r.raffle();
                    case "0" -> {
                        System.out.println("Пока");
                        System.exit(0);
                    }
                    default -> System.out.println("Некорректный ввод. Попробуйте снова.");
                }
            }
        }
}

