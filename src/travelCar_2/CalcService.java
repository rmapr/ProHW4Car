package travelCar_2;

public class CalcService {
    final int DISTANCE_ODESA_CURVED_LAKE = 179;
    final int DISTANCE_CURVED_LAKE_YASHKIV = 149;
    final int DISTANCE_YASHKIV_KYIV = 151;

    public void addFuelTank(Car carName) {
        System.out.println("\nБак повний.");
        carName.setRestTank(carName.getTankVolume());
    }

    //    визначити залишок палива після подолання N км
    public double restFuel(Car carName, double driveWay) {
        return carName.getRestTank() - driveWay * carName.getFuelConsumption() / 100;
    }

    //    визначити скільки треба до заправити палива для подолання N км
    public double fuelFillTravel(Car carName, double driveWay) {
        double way = driveWay * carName.getFuelConsumption() / 100 - carName.getRestTank();
        if (way > 0) return way;
        else return 0;
    }

    public double calcCost(double volume, double price){
        return volume * price;
    }
    public void printCarTth(Car car) {
        System.out.printf("ТТХ вашого авто:" +
                        "\nВміст паливного баку, л - %s" +
                        "\nЗалишки палива, л - %s" +
                        "\nВитрати пального, л/100км - %s\n\n",
                car.getTankVolume(), car.getRestTank(), car.getFuelConsumption());
    }
}
