package raffle;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Raffle {

        private static ArrayList<Toy> toys = new ArrayList<>();
        private static PriorityQueue<Toy> prizes = new PriorityQueue<>();

        private static int idCounter = 0;

        public void addToy() {
            Scanner scan = new Scanner(System.in);
            String name;
            int frequencyOfLoss;
            while (true) {
                System.out.print("Введите название: ");
                name = scan.nextLine();
                if (name.isEmpty()) {
                    System.out.println("Неверный ввод. Попробуйте снова. ");
                    break;
                }
                System.out.print("Введите частоту выбывания: ");
                String fl = scan.nextLine();
                if (isDigit(fl)) {
                    frequencyOfLoss = Integer.parseInt(fl);
                    if (frequencyOfLoss <= 0) {
                        System.out.println("Неверный ввод. Попробуйте снова.");
                    } else {
                        Toy toy = new Toy(idCounter, name, frequencyOfLoss);
                        if (!toys.contains(toy) || toys.size() == 0) {
                            idCounter++;
                            toys.add(toy);
                            System.out.println("Добавлена новая игрушка");
                        } else {
                            System.out.println("Эта игрушка уже в призовом фонде.");
                        }
                    }
                } else {
                    System.out.println("Некорректный ввод. Попробуйте снова.");
                }
                break;
            }
        }

        public void setFrequency() {
            Scanner scan = new Scanner(System.in);
            System.out.print("Введите id игрушки: ");
            String id = scan.nextLine();
            if (isDigit(id)) {
                int selectedId = Integer.parseInt(id);
                if (selectedId >= 0 && selectedId < toys.size()) {
                    System.out.println("игрушка " + toys.get(selectedId).getToyName() +
                            " имеет частоту победы " + toys.get(selectedId).getFrequencyFallingToy());
                    System.out.print("Введите новую частоту выпадения: ");
                    id = scan.nextLine();
                    if (isDigit(id)) {
                        int newFrequency = Integer.parseInt(id);
                        toys.get(selectedId).setFrequencyFallingToy(newFrequency);
                        System.out.println("Частота была изменена.");
                    } else {
                        System.out.println("Неверный ввод. Попробуйте еще раз.");
                    }
                } else {
                    System.out.println("В призовом фонде нет игрушки с таким id.");
                }
            } else {
                System.out.println("Неверный ввод. Попробуйте еще раз.");
            }
        }

        private static boolean isDigit(String s) throws NumberFormatException {
            try {
                Integer.parseInt(s);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        public Toy getPrize() {
            if (prizes.size() == 0) {
                Random rnd = new Random();
                for (Toy toy : toys) {
                    for (int i = 0; i < toy.getFrequencyFallingToy(); i++) {
                        Toy temp = new Toy(toy.getToyId(), toy.getToyName(), rnd.nextInt(1, 10));
                        prizes.add(temp);
                    }
                }
            }
            return prizes.poll();
        }

        public void raffle() {
            if (toys.size() >= 2) {
                Toy prize = getPrize();
                System.out.println("Приз: " + prize.getToyName());
                resultFile(prize.getInfo());
            } else {
                System.out.println("В призовой фонд необходимо добавить не менее двух игрушек.");
            }
        }

        private void resultFile(String text) {
            File file = new File("result.txt");
            try {
                file.createNewFile();
            } catch (Exception ignored) {
                throw new RuntimeException();
            }
            try (FileWriter fw = new FileWriter("result.txt", true)) {
                fw.write(text + "\n");
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
}

