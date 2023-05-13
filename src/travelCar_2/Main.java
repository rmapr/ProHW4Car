package travelCar_2;

import java.text.DecimalFormat;

public class Main {
    static double totalCost;
    static CalcService calcService;
    static Car car;

    public static void main(String[] args) {
        boolean tripIsNotPossible = false;
        double fuelPrice = Double.parseDouble(args[0]); // вартість палива
        System.out.println("Ціна палива " + fuelPrice + " ГРН/л\n");
        calcService = new CalcService();
        car = new Car(40, 7, 9.5);
        calcService.printCarTth(car);

        //1 Одеса - Криве Озеро
        double dist = calcService.DISTANCE_ODESA_CURVED_LAKE;
        double countFuelTravel = calcService.fuelFillTravel(car, dist);
        totalCost = 0; //загальна вартість поїздки

        if ((countFuelTravel + car.getRestTank()) > car.getTankVolume()) {
            System.out.println("Поїздка без додаткової заправки неможлива, " +
                    "або додатково з собою " +
                    new DecimalFormat("#.00").format(countFuelTravel - car.getTankVolume()) + "л палива");
            tripIsNotPossible = true;
        } else travel(car, countFuelTravel, dist, fuelPrice);

        if (!tripIsNotPossible) {
            // Перевіряю залишки палива
            //Перевіряю хвате проїхати ділянку
            System.out.print("\nм. Криве Озеро. Дозаправка.");
//2 Криве Озеро - Жашків
            car.setRestTank(calcService.restFuel(car, dist));
            System.out.println("\nЗалишилось палива після " + dist + "км - " +
                    new DecimalFormat("#.00").format(car.getRestTank()) + " л");
            dist = calcService.DISTANCE_CURVED_LAKE_YASHKIV;
            countFuelTravel = calcService.fuelFillTravel(car, dist);
            if (countFuelTravel > car.getTankVolume()) {
                System.out.println("Поїздка без додаткової заправки неможлива, " +
                        "або додатково з собою " +
                        new DecimalFormat("#.00").format(countFuelTravel - car.getTankVolume()) + "л палива");
                tripIsNotPossible = true;
            } else travel(car, countFuelTravel, dist, fuelPrice);

            if (!tripIsNotPossible) {
                // Перевіряю залишки палива
                //Перевіряю хвате проїхати ділянку
                System.out.print("\nм. Жашків. Дозаправка.");
//3 Жашків- Київ
                car.setRestTank(calcService.restFuel(car, dist));
                System.out.println("\nЗалишилось палива після " + dist + "км - " +
                        new DecimalFormat("#.00").format(car.getRestTank()) + " л");
                dist = calcService.DISTANCE_YASHKIV_KYIV;
                countFuelTravel = calcService.fuelFillTravel(car, dist);

                if (countFuelTravel > car.getTankVolume()) {
                    System.out.println("Поїздка без додаткової заправки неможлива, " +
                            "або додатково з собою " +
                            new DecimalFormat("#.00").format(countFuelTravel - car.getTankVolume()) + "л палива");
                    tripIsNotPossible = true;
                } else travel(car, countFuelTravel, dist, fuelPrice);

                if (!tripIsNotPossible) {
                    System.out.println("\nВи прибули у кінцевий пункт прихначення м.Київ.");
                    System.out.println("Загальні витрати на поїздку: " + new DecimalFormat("#.00").format(totalCost) + " грн.");
                    car.setRestTank(calcService.restFuel(car, dist));
                    System.out.println("В баку залишилось палива після поїздки:  " +
                            new DecimalFormat("#.00").format(car.getRestTank()) + " л");
                }
            }
        }
    }

    private static void travel(Car carName, double countFuelTravel, double dist, double fuelPrice) {
        if (countFuelTravel != 0) {
            System.out.println("Для подолання ділянки " + dist + "км з урахуванням залишку потрібно заправити: " +
                    new DecimalFormat("#.00").format(countFuelTravel) + "л");
            double countFuel = carName.getTankVolume() - carName.getRestTank();// долили кількість палива
            calcService.addFuelTank(carName);
            double cost = calcService.calcCost(countFuel, fuelPrice);
            totalCost += cost;
            System.out.println("Заправлено " + new DecimalFormat("#.00").format(countFuel) + "л вартістю: " +
                    new DecimalFormat("#.00").format(cost) + " грн.");
        } else {
            System.out.println("Заправка не потрібна, решти палива вистачить проїхати " +
                    dist + "км. Щасливої дороги!");
        }
    }
}
