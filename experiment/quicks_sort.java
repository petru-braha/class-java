import java.util.Arrays;

public class quicks_sort {

  public static void main(String[] args) {

    int n = 100_000;
    int a[] = new int[n];
    for (int i = 0; i < n; i++)
      a[i] = n - i;

    long t1 = System.currentTimeMillis();
    Arrays.sort(a);

    long t2 = System.currentTimeMillis();
    System.out.println(t2 - t1);
  }
}
