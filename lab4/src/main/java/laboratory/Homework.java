package laboratory;

import com.github.javafaker.Faker;

public class Homework {

  public static void main(String[] args) {
    Faker fake = new Faker();
    System.out.println(fake.animal().name());
  }
  
}
