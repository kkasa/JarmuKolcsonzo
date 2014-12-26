package vehiclelender.util;

import vehiclelender.vehicle.Car;
import vehiclelender.vehicle.Scooter;
import vehiclelender.Site;

/**
 *
 * @author rych
 */
public final class VehiclePrinterEn {

    public static void printAll(Site site) {
        System.out.println("Site details: " + site);
    }
    
    public static void printAll(Scooter scooter) {
        System.out.println("Scooter details: " + scooter);
    }

    public static void printAll(Car car) {
        String textMessage = car.toString();
        textMessage = textMessage.replace(Car.RANKMPOSTFIX_TOKEN, "km, service period:");
        textMessage = textMessage.replace(Car.SERVICEPERIODPOSTFIX_TOKEN, "km");
        System.out.println("Car details: " + textMessage);
    }

}
