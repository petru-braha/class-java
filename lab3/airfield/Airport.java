package lab3.airfield;

public class Airport {

  private final int countRunways;

  public Airport(final int r) {
    
    if (r >= 0) {
      countRunways = r;
      return;
    }

    System.out.printf("warning: %s - %s.\n",
        "Airport() failed",
        "invalid number of runways");
    countRunways = 0;
  }

  public final int getRunways(){
    return countRunways;
  }
}
