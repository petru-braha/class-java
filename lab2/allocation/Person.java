package lab2.allocation;

public abstract class Person {

  protected final int name;
  protected final int birthDate;

  public Person(final int pName, final int pBirth) {
    name = pName;
    birthDate = pBirth;
  }

  public int getName() {
    return name;
  }

  public int getBirthDate() {
    return birthDate;
  }

  @Override
  public String toString() {
    return "warning: incorrect instance";
  }
}
