#include <iostream>
#include <chrono>

#include <algorithm>
#include <array>

using namespace std::chrono;
using std::chrono::steady_clock;

int main()
{
  constexpr int n = 100000;
  std::array<int, n> a;
  for (int i = 0; i < n; i++)
    a[i] = n - i;

  steady_clock::time_point t1 = steady_clock::now();
  std::sort(a.begin(), a.end());

  steady_clock::time_point t2 = steady_clock::now();
  std::cout << duration_cast<milliseconds>(t2 - t1).count() << '\n';
}
