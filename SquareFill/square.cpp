#include <iostream>
#include <fstream>
#include <iomanip>
#include <string>
#include <vector>

using namespace std;

int findMinimum(int squareSize){
  int minimum;
  int evenMinimum;

  if(squareSize % 2 == 0){
    evenMinimum = 4;
    return evenMinimum;
  }
  return 0;
}

void getCoordinates(int squareSize, int minimumSquares){
  vector<int> smallSquareSize;
  vector<int> x;
  vector<int> y;

  if(minimumSquares == 4){
    for(int x = 0; x < 4; x++){
      smallSquareSize.push_back(squareSize / 2);
    }
  }
  for(int x = 0; x < smallSquareSize.size(); x++){
    cout << smallSquareSize[x] << endl;
  }
}

int main(){
  int squareSize;
  int minimumSquares;

  cin >> squareSize;

  minimumSquares = findMinimum(squareSize);
  getCoordinates(squareSize, minimumSquares);
  return 0;
}
