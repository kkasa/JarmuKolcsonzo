package vehiclelender.util;

/**
 * Generate new id for vehicle
 * @author rych
 */
public final class IdGenerator {
    
    private static int id = 0;
    
    public synchronized static int getId() {
        return ++id;
    }
    
}
