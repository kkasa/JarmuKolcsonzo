package vehiclelender.vehicle;

/**
 *
 * @author rych
 */
public class Scooter extends Vehicle {
    
    private boolean withHelmet = true;
    
    public Scooter(String manufacturer, String type, String color, int yearOfManufacture, boolean withHelmet) {
        super(manufacturer, type, color, yearOfManufacture);
        this.withHelmet = withHelmet;
    }

    /**
     * @return the withHelmet
     */
    public boolean isWithHelmet() {
        return withHelmet;
    }

    /**
     * @param withHelmet the withHelmet to set
     */
    public void setWithHelmet(boolean withHelmet) {
        this.withHelmet = withHelmet;
    }

    @Override
    public String toString() {
        return this.getId() + ", " + this.getManufacturer() + " " 
                + this.getType() + ", " + this.getColor() + ", " 
                + this.getYearOfManufacture() + ", " + this.isWithHelmet();
    }

    
    
}
