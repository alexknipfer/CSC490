#include <iostream>
#include <string>
#include <vector>

using namespace std;

char PilarMap[25][25];
char LizardMap[25][25];

int main(){
  int testCases;
  int n;
  int d;
  string line;

  cin >> testCases;

  for(int x = 0; x < testCases; x++){
    cin >> n >> d;

    cin >> line;

    cout << line << endl;
    break;
    /*for(int i = 0; i < n; i++){
      cin >> PilarMap[i];
    }

    for(int j = 0; j < n; j++){
      cin >> LizardMap[j];
    }
  }

  for(int x = 0; x < n; x++){
    cout << PilarMap[x] << " ";
  }*/
}

  return 0;
}
