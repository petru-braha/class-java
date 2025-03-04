#include <iostream>
#include <chrono>

using namespace std::chrono;
using std::chrono::steady_clock;

int main()
{
  constexpr int n = 100000;
  int a[n];
  for (int i = 0; i < n; i++)
    a[i] = n - i;

  steady_clock::time_point t1 = steady_clock::now();
  for (int i = 0; i < n - 1; i++)
    for (int j = 0; j < n - i - 1; j++)
      if (a[j] > a[j + 1]) {
        int aux = a[j];
        a[j] = a[j + 1];
        a[j + 1] = aux;
      }

  steady_clock::time_point t2 = steady_clock::now();
  std::cout << duration_cast<milliseconds>(t2 - t1).count() << '\n';
}
