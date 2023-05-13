package travelCar_1;

public class Car {
    double tankVolume;
    double restTank;
    double fuelConsumption;
    String carName;

    public Car() {
    }

    public Car(double tankVolume, double restTank, double fuelConsumption) {
        this.tankVolume = tankVolume;
        this.restTank = restTank;
        this.fuelConsumption = fuelConsumption;
    }
    //залити повний бак з урахуванням обсягу залишку
    public static void addFuelTank(Car carName) {
        System.out.println("\nБак повний.");
        carName.restTank = carName.tankVolume;
    }

    //    визначити залишок палива після подолання N км
    public static double restFuel(Car carName, double driveWay) {
        return carName.restTank - driveWay * carName.fuelConsumption / 100;
    }

    //    визначити скільки треба до заправити палива для подолання N км
    public static double fuelFillTravel(Car carName, double driveWay) {
        double way = driveWay * carName.fuelConsumption / 100 - carName.restTank;
        if (way > 0) return way;
        else return 0;
    }
    static void printCarTth(Car car) {
        System.out.printf("Авто - %s" +
                        "\nВміст паливного баку, л - %s" +
                        "\nЗалишки палива, л - %s" +
                        "\nВитрати пального, л/100км - %s\n",
                car.carName, car.tankVolume, car.restTank, car.fuelConsumption);
    }
    static double calcCost(double volume, double price){
        return volume * price;
    }
}