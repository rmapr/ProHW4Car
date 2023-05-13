package travelCar_2;

public class Car {
    private double tankVolume;
    private double restTank;
    private double fuelConsumption;

    public Car(double tankVolume, double restTank, double fuelConsumption) {
        this.tankVolume = tankVolume;
        this.restTank = restTank;
        this.fuelConsumption = fuelConsumption;
    }

    public double getTankVolume() {
        return tankVolume;
    }

    public double getRestTank() {
        return restTank;
    }

    public void setRestTank(double restTank) {
        this.restTank = restTank;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

}
