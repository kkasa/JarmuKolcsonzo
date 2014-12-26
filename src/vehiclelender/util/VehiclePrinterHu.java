package vehiclelender.util;

import vehiclelender.vehicle.Car;
import vehiclelender.vehicle.Scooter;
import vehiclelender.Site;

/**
 *
 * @author rych
 */
public final class VehiclePrinterHu {

    public static void printAll(Site site) {
        System.out.println("A telephely adatai: " + site);
    }
    
    public static void printAll(Scooter scooter) {
        System.out.println("A robogó adatai: " + scooter);
    }

    public static void printAll(Car car) {
        String textMessage = car.toString();
        textMessage = textMessage.replace(Car.RANKMPOSTFIX_TOKEN, "km");
        textMessage = textMessage.replace(Car.SERVICEPERIODPOSTFIX_TOKEN, "km-enként");
        System.out.println("Az autó adatai: " + textMessage);
    }

}
