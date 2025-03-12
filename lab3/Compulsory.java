package lab3;

import lab3.aircrafts.*;

public class Compulsory {

  public static void main(String[] args) {

    // individual
    Airliner airliner = new Airliner(100, 0, 59, 400);
    System.out.printf("wing: %d; passengers: %d;\n",
        airliner.getWingSpan(),
        airliner.getPassengerCapacity());

    Freighter freighter = new Freighter(101, 1, 10000);
    System.out.printf("maximum payload: %d;\n",
        freighter.getMaxPayload());

    Drone drone = new Drone(102, 2, 5, 200);
    System.out.printf("wing: %d; maximum payload: %d;\n\n",
        drone.getWingSpan(),
        drone.getMaxPayload());

    // array
    Aircraft[] vehicles = { airliner, freighter, drone };
    for (int i = 0; i < vehicles.length; i++)
      System.out.printf("model: %d; name: %d;\n",
          vehicles[i].getModel(), vehicles[i].getName());
  }
}
