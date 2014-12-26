package vehiclelender.vehicle;

import vehiclelender.exceptions.VehicleIsNotRented;

/**
 *
 * @author rych
 */
public class Car extends Vehicle {

    public static final String RANKMPOSTFIX_TOKEN = "%ranKmPostfix%";
    public static final String SERVICEPERIODPOSTFIX_TOKEN = "%servicePeriodPostfix%";
    
    private int ranKm = 0;
    private int servicePeriod = 20000;
    private int lastServiceKm = 0;

    public Car(String manufacturer, String type, String color, int yearOfManufacture, int ranKm, int servicePeriod) {
        this(manufacturer, type, color, yearOfManufacture);
        this.ranKm = ranKm;
        this.servicePeriod = servicePeriod;
    }

    public Car(String manufacturer, String type, String color, int yearOfManufacture) {
        super(manufacturer, type, color, yearOfManufacture);
    }

    @Override
    public void bringBack() {
        throw new IllegalArgumentException("Illegal use of bringBack at Car. Use bringBack(int km) instead.");
    }

    public void bringBack(int km) throws VehicleIsNotRented {
        super.bringBack();
        ranKm += km;
    }

    public boolean isServiceRequired() {
        return ranKm - servicePeriod > lastServiceKm;
    }

    public void doService() {
        lastServiceKm = ranKm;
    }

    /**
     * @return the ranKm
     */
    public int getRanKm() {
        return ranKm;
    }

    /**
     * @return the servicePeriod
     */
    public int getServicePeriod() {
        return servicePeriod;
    }

    /**
     * @param servicePeriod the servicePeriod to set
     */
    public void setServicePeriod(int servicePeriod) {
        this.servicePeriod = servicePeriod;
    }

    @Override
    public String toString() {
        return this.getId() + ", "
                + this.getManufacturer() + " " + this.getType() + ", "
                + this.getColor() + ", " + this.getYearOfManufacture() + ", "
                + this.getRanKm() + RANKMPOSTFIX_TOKEN + " "
                + this.getServicePeriod() + SERVICEPERIODPOSTFIX_TOKEN;
    }

}
