package vehiclelender;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import vehiclelender.util.VehiclePrinterEn;
import vehiclelender.vehicle.Car;
import vehiclelender.vehicle.Scooter;

/**
 *
 * @author rych
 */
public class Site {

    private final String city;
    private final String address;

    private final List<Scooter> scooters = new ArrayList<>();
    private final List<Car> cars = new ArrayList<>();

    public Site(String city, String address) {
        this.city = city;
        this.address = address;
    }

    public int getNumberOfCars() {
        return cars.size();
    }

    public int getNumberOfScooters() {
        return scooters.size();
    }

    public int getNumberOfVehicles() {
        return scooters.size() + cars.size();
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    public void addScooter(String manufacturer, String type, String color, int yearOfManufacture, boolean withHelmet) {
        this.scooters.add(new Scooter(manufacturer, type, color, yearOfManufacture, withHelmet));
    }

    public Scooter getScooter(int i) {
        return scooters.get(i);
    }

    public void addCar(String manufacturer, String type, String color, int yearOfManufacture) {
        this.cars.add(new Car(manufacturer, type, color, yearOfManufacture));
    }

    public void addCar(String manufacturer, String type, String color, int yearOfManufacture, int ranKm, int servicePeriod) {
        this.cars.add(new Car(manufacturer, type, color, yearOfManufacture, ranKm, servicePeriod));
    }

    public Car getCar(int i) {
        return cars.get(i);
    }

    /**
     * Load vehicles from xml file
     *
     * @param path
     */
    public void loadVehiclesFromFile(String path) {
        try {

            File fXmlFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            // Load scooters:
            NodeList nList = doc.getElementsByTagName("scooter");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    this.addScooter(
                            eElement.getElementsByTagName("manufacturer").item(0).getTextContent(),
                            eElement.getElementsByTagName("type").item(0).getTextContent(),
                            eElement.getElementsByTagName("color").item(0).getTextContent(),
                            Integer.parseInt(
                                    eElement.getElementsByTagName("yearOfManufacture").item(0).getTextContent()
                            ),
                            Boolean.parseBoolean(
                                    eElement.getElementsByTagName("withHelmet").item(0).getTextContent()
                            )
                    );

                }
            }

            // Load cars:
            nList = doc.getElementsByTagName("car");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    if (eElement.getElementsByTagName("ranKm").getLength() > 0) {
                        this.addCar(
                                eElement.getElementsByTagName("manufacturer").item(0).getTextContent(),
                                eElement.getElementsByTagName("type").item(0).getTextContent(),
                                eElement.getElementsByTagName("color").item(0).getTextContent(),
                                Integer.parseInt(
                                        eElement.getElementsByTagName("yearOfManufacture").item(0).getTextContent()
                                ),
                                Integer.parseInt(
                                        eElement.getElementsByTagName("ranKm").item(0).getTextContent()
                                ),
                                Integer.parseInt(
                                        eElement.getElementsByTagName("servicePeriod").item(0).getTextContent()
                                )
                        );
                    } else {
                        this.addCar(
                                eElement.getElementsByTagName("manufacturer").item(0).getTextContent(),
                                eElement.getElementsByTagName("type").item(0).getTextContent(),
                                eElement.getElementsByTagName("color").item(0).getTextContent(),
                                Integer.parseInt(
                                        eElement.getElementsByTagName("yearOfManufacture").item(0).getTextContent()
                                )
                        );
                    }

                }
            }

        } catch (IOException | NumberFormatException | ParserConfigurationException | DOMException | SAXException e) {
            e.printStackTrace();    // TODO: ?
        }
    }

    @Override
    public String toString() {
        return this.city + ", " + this.address;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        final int EXIT_MENU_ITEM = 0;
        final int LIST_VEHICLES_MENU_ITEM = 1;
        final int ADD_NEW_SCOOTER_MENU_ITEM = 2;
        final int ADD_NEW_CAR_MENU_ITEM = 3;

        Site t = new Site("Kaposvár", "Vár u. 230");

        VehiclePrinterEn.printAll(t);
        System.out.println("Number of vehicles: " + t.getNumberOfVehicles());
        System.out.println("Loading vehicles form XML file...");
        t.loadVehiclesFromFile("./xmlsources/jarmuvek.xml");
        System.out.println("Number of vehicles: " + t.getNumberOfVehicles());

        for (int i = 0; i < t.getNumberOfScooters(); i++) {
            VehiclePrinterEn.printAll(t.getScooter(i));
        }
        for (int i = 0; i < t.getNumberOfCars(); i++) {
            VehiclePrinterEn.printAll(t.getCar(i));
        }

        Scanner in = new Scanner(System.in);
        int menuItem;
        do {
            System.out.println("\n");
            System.out.println(LIST_VEHICLES_MENU_ITEM + ". List vehicles");
            System.out.println(ADD_NEW_SCOOTER_MENU_ITEM + ". Add new scooter");
            System.out.println(ADD_NEW_CAR_MENU_ITEM + ". Add new car\n");
            System.out.println(EXIT_MENU_ITEM + ". Exit");
            menuItem = in.nextInt();

            String manufacturer;
            String type;
            String color;
            int yearOfManufacture;
            boolean withHelmet;

            switch (menuItem) {
                case EXIT_MENU_ITEM:
                    break;
                case LIST_VEHICLES_MENU_ITEM:
                    for (int i = 0; i < t.getNumberOfScooters(); i++) {
                        VehiclePrinterEn.printAll(t.getScooter(i));
                    }
                    for (int i = 0; i < t.getNumberOfCars(); i++) {
                        VehiclePrinterEn.printAll(t.getCar(i));
                    }
                    break;
                case ADD_NEW_SCOOTER_MENU_ITEM:
                    System.out.println("Add new scooter:");
                    in.nextLine();
                    System.out.print("- Manufacturer: ");
                    manufacturer = in.nextLine();
                    System.out.print("- Type: ");
                    type = in.nextLine();
                    System.out.print("- Color: ");
                    color = in.nextLine();
                    System.out.print("- Year of manufacture: ");
                    yearOfManufacture = in.nextInt();
                    System.out.print("- With helmet? (y/n) ");
                    withHelmet = (in.next(".").charAt(0) == 'y');

                    t.addScooter(manufacturer, type, color, yearOfManufacture, withHelmet);
                    break;
                case ADD_NEW_CAR_MENU_ITEM:
                    System.out.println("Add new car:");
                    in.nextLine();
                    System.out.print("- Manufacturer: ");
                    manufacturer = in.nextLine();
                    System.out.print("- Type: ");
                    type = in.nextLine();
                    System.out.print("- Color: ");
                    color = in.nextLine();
                    System.out.print("- Year of manufacture: ");
                    yearOfManufacture = in.nextInt();
                    System.out.print("Do you have run km and service period? (y/n) ");
                    if (in.next(".").charAt(0) == 'y') {
                        System.out.print("- Run km: ");
                        int runKm = in.nextInt();
                        System.out.print("- Service period: ");
                        int servicePeriod = in.nextInt();

                        t.addCar(manufacturer, type, color, yearOfManufacture,
                                runKm, servicePeriod);
                    } else {
                        t.addCar(manufacturer, type, color, yearOfManufacture);
                    }
                    break;
                default:
                    System.err.println("Illegal value! Please pick a number from the list!");
            }
        } while (menuItem != EXIT_MENU_ITEM);

    }

}
