class laboratory {

  static void compulsory() {
    String languages[] = { "C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java" };

    int n = (int) (Math.random() * 1_000_000);
    n *= 3;
    int result = 0b10101;
    result += 0xFF;
    result *= 6;

    while (result > 9) {
      n = 0;
      while (result != 0) {
        n += result % 10;
        result /= 10;
      }

      result = n;
    }

    System.out.println(
        "Willy-nilly, this semester I will learn " +
            languages[result]);
  }

  static void homework() {
  }

  static void bonus() {
  }

  public static void main(String[] args) {
    compulsory();
    homework();
    bonus();
  }
}
