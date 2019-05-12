public class ParkingSlot {
    private boolean free=true;
    private int number;
    private Car car;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public boolean isFree(){
        return free;
    }
}
