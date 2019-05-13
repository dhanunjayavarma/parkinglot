public class Car {
    private String registrationNumber;
    private Colour colour;
    private Driver driver;

    public Car(String registrationNumber, Colour colour,Driver driver) {
        this.registrationNumber = registrationNumber;
        this.colour = colour;
        this.driver=driver;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Colour getColour() {
        return colour;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

}
