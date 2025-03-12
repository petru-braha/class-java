package lab2.allocation;

// This is an abstract class named Person which represents a person with a name and birth date
public abstract class Person {

  // Protected fields to store the name and birth date of the person
  protected final int name; // Name of the person (as an integer, presumably an ID or code)
  protected final int birthDate; // Birth date of the person (likely in some numerical format)

  // Constructor to initialize the name and birth date fields
  public Person(final int pName, final int pBirth) {
    name = pName; // Initialize the name field with the provided value
    birthDate = pBirth; // Initialize the birth date field with the provided value
  }

  // Getter method to retrieve the name of the person
  public int getName() {
    return name; // Return the value of the name field
  }

  // Getter method to retrieve the birth date of the person
  public int getBirthDate() {
    return birthDate; // Return the value of the birthDate field
  }

  // Overridden toString method to return a warning message if called
  @Override
  public String toString() {
    return "warning: incorrect instance"; // Return a warning message indicating an issue with the object instance
  }
}
