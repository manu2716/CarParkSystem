import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class CarParkSystem {

    static int totalSlots = 100;
    // to calculate remaining slots
    static int availableSlots;
    static int farePerHour = 2;
    //map to store the license number of car and entry hour
    static Map<String, Integer> parkedCars = new HashMap<String,Integer>();

    public static void main(String args[]){

        availableSlots = totalSlots;
        Scanner sc = new Scanner(System.in);

        while(true){

            System.out.println("Choose the options below:");
            System.out.println("1. Park a car");
            System.out.println("2. Remove a car");
            System.out.println("3. Exit");

            int choice = sc.nextInt();

            switch(choice){
                case 1:
                    parkCar();
                    break;
                case 2:
                    removeCar();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    //this method stores the license number of the car and the hour at entry time
    public static void parkCar(){

            if(availableSlots == 0){
                System.out.println("Sorry, The parking is full for now! Please check after some time.");
                return;
            }

            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the license plate number of the car:");
            String licensePlate = sc.nextLine();

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            //Only saving hour in Map, Therefore an assumption that car checks out on the same day.
            parkedCars.put(licensePlate,now.getHour());
            availableSlots--;
            System.out.println("Car parked successfully. Available slots: " + availableSlots);
    }

    //calculates the fare charges during the parking
    public static void removeCar(){

            if(availableSlots == totalSlots){
                System.out.println("Sorry, The parking is full for now! Please check after some time.");
                return;
            }

            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the license number of the car to be checked out:");
            String licensePlate = sc.nextLine();

            if(Optional.ofNullable(parkedCars.get(licensePlate)).isPresent()) {
                int checkedInHour = parkedCars.get(licensePlate);
                LocalDateTime now = LocalDateTime.now();
                int checkOutTime = now.getHour();
                int duration = checkOutTime - checkedInHour;
                int charges = duration * farePerHour;
                availableSlots++;
                System.out.println("Total charges: " + charges);
                System.out.println("Car removed successfully. Available slots: " + availableSlots);
            }
            else{
                System.out.println("The car is not parked here.");
            }
    }
}
