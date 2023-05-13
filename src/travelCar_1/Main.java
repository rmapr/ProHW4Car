package travelCar_1;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static final int DISTANCE_ODESA_KYIV = 479;
    public static final int DISTANCE_ODESA_CURVED_LAKE = 179;
    public static final int DISTANCE_ODESA_YASHKIV = 328;
    public static final String CAR_SONATA = "Hyundai Sonata";
    public static final String CAR_ACENT = "Hyundai Acent";
    public static final String CAR_COROLLA = "Toyota Corolla";
    public static final String CAR_MATIZ = "Daewoo Matiz";
    public static double totalCost;

    public static void main(String[] args) {
        double fuelPrice = Double.parseDouble(args[0]); // вартість палива
        System.out.println("Ціна палива " + fuelPrice + " ГРН/л\n");

        Scanner sc = new Scanner(System.in);
        System.out.println("Виберіть машину: ");
        System.out.println("1: " + CAR_SONATA +
                "\n2: " + CAR_ACENT +
                "\n3: " + CAR_COROLLA +
                "\n4: " + CAR_MATIZ +
                "\n0: Exit");
        int carInd = sc.nextInt();
        Car car = new Car();

        switch (carInd) {
            case 1 -> {
                Car carSonata = new Car(70, 7, 9.5);
                carSonata.carName = CAR_SONATA;
                car = carSonata;
                car.printCarTth(car);
            }
            case 2 -> {
                Car carAcent = new Car(45, 10, 7.5);
                carAcent.carName = CAR_ACENT;
                car = carAcent;
                car.printCarTth(car);
            }
            case 3 -> {
                Car carCorolla = new Car(35, 12, 12);
                carCorolla.carName = CAR_COROLLA;
                car = carCorolla;
                car.printCarTth(car);
            }
            case 4 -> {
                Car carMatiz = new Car(35, 7, 6.7);
                carMatiz.carName = CAR_MATIZ;
                car = carMatiz;
                car.printCarTth(car);
            }
            default -> System.out.println("Ніхто нікуди не їде).");
        }
        if (carInd != 0) {
//1 Одеса - Криве Озеро
            double dist = DISTANCE_ODESA_CURVED_LAKE;
            double countFuelTravel = car.fuelFillTravel(car, dist);
            totalCost = 0; //загальна вартість поїздки

            travel(car, countFuelTravel, dist, fuelPrice);

            // Перевіряю залишки палива
            //Перевіряю хвате проїхати ділянку
            System.out.print("\nм. Криве Озеро. Дозаправка.");
//2 Криве Озеро - Жашків
            car.restTank = car.restFuel(car, dist);
            System.out.println("\nЗалишилось палива після " + dist + "км - " +
                    new DecimalFormat("#.00").format(car.restTank) + " л");
            dist = DISTANCE_ODESA_YASHKIV - DISTANCE_ODESA_CURVED_LAKE;
            countFuelTravel = car.fuelFillTravel(car, dist);

            travel(car, countFuelTravel, dist, fuelPrice);
            // Перевіряю залишки палива
            //Перевіряю хвате проїхати ділянку
            System.out.print("\nм. Жашків. Дозаправка.");
//3 Жашків- Київ
            car.restTank = car.restFuel(car, dist);
            System.out.println("\nЗалишилось палива після " + dist + "км - " +
                    new DecimalFormat("#.00").format(car.restTank) + " л");
            dist = DISTANCE_ODESA_KYIV - DISTANCE_ODESA_YASHKIV;
            countFuelTravel = car.fuelFillTravel(car, dist);

            travel(car, countFuelTravel, dist, fuelPrice);


            System.out.println("\nВи прибули у кінцевий пункт прихначення м.Київ.");
            System.out.println("Загальні витрати на поїздку: " + new DecimalFormat("#.00").format(totalCost) + " грн.");
            car.restTank = car.restFuel(car, dist);
            System.out.println("В баку залишилось палива після поїздки:  " +
                    new DecimalFormat("#.00").format(car.restTank) + " л");
        }
    }

    private static void travel(Car carName, double countFuelTravel, double dist, double fuelPrice) {
        if (countFuelTravel != 0) {
            System.out.println("Для подолання ділянки " + dist + "км з урахуванням залишку потрібно заправити: " +
                    new DecimalFormat("#.00").format(countFuelTravel) + "л");
            double countFuel = carName.tankVolume - carName.restTank;// долили кількість палива
            carName.addFuelTank(carName);
            double cost = carName.calcCost(countFuel, fuelPrice);
            totalCost += cost;
            System.out.println("Заправлено " + new DecimalFormat("#.00").format(countFuel) + "л вартістю: " +
                    new DecimalFormat("#.00").format(cost) + " грн.");
        } else {
            System.out.println("Заправка не потрібна, решти палива вистачить проїхати " +
                    dist + "км. Щасливої дороги!");
        }
    }
}
