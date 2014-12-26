package vehiclelender.vehicle;

import vehiclelender.util.IdGenerator;
import vehiclelender.exceptions.VehicleIsAlreadyRented;
import vehiclelender.exceptions.VehicleIsNotRented;

/**
 *
 * @author rych
 */
public abstract class Vehicle {
    
    private final int id;  
    private boolean free = true;    
    private String manufacturer;
    private String type;
    private String color;
    private int yearOfManufacture;
  
    /**
     * Constructor of abstract class Vehicle
     * @param manufacturer
     * @param type
     * @param color
     * @param yearOfManufacture 
     */
    public Vehicle(String manufacturer, String type, String color, int yearOfManufacture) {
        this.id = IdGenerator.getId();
        this.manufacturer = manufacturer;
        this.type = type;
        this.color = color;
        this.yearOfManufacture = yearOfManufacture;
    }
    
    /**
     * Rent a vehicle
     * @throws VehicleIsAlreadyRented 
     */
    public void rent() throws VehicleIsAlreadyRented {
        if (!isFree()) {
            throw new VehicleIsAlreadyRented();
        } else {
            free = true;
        }
    }
    
    /**
     * Bring back a rented vehicle
     * @throws VehicleIsNotRented 
     */
    public void bringBack() throws VehicleIsNotRented {
        if (isFree()) {
            throw new VehicleIsNotRented();
        } else {
            free = false;
        }        
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the free
     */
    public boolean isFree() {
        return free;
    }

    /**
     * @return the manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * @param manufacturer the manufacturer to set
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the yearOfManufacture
     */
    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    /**
     * @param yearOfManufacture the yearOfManufacture to set
     */
    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }
    
}
