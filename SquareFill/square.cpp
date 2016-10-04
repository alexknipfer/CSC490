#include <iostream>
#include <fstream>
#include <iomanip>
#include <string>
#include <vector>

using namespace std;

int findEvenMinimum(int squareSize){
  int minimum;
  int evenMinimum;

  if(squareSize % 2 == 0){
    evenMinimum = 4;
    return evenMinimum;
  }

  return 0;
}

int findOddMinimum(int squareSize, unsigned int n){

  if(n <= 3){
    return n;
  }

  int res = n;

  for(int x = 1; x <= squareSize - 1; x++){
    int temp = x*x;
    if(temp > n){
      break;
    }
    else{
      res = min(res, 1+ findOddMinimum(squareSize, n - temp));
    }
  }
  return res;
}

void getCoordinates(int squareSize, int minimumSquares){
  vector<int> smallSquareSize;
  vector<int> xCoor;
  vector<int> yCoor;

  if(minimumSquares == 4){
    for(int x = 0; x < 4; x++){
      smallSquareSize.push_back(squareSize / 2);
    }
    xCoor.push_back(0);
    yCoor.push_back(0);
    xCoor.push_back(squareSize / 2);
    yCoor.push_back(0);
    xCoor.push_back(0);
    yCoor.push_back(squareSize / 2);
    xCoor.push_back(squareSize / 2);
    xCoor.push_back(squareSize / 2);
  }
  for(int x = 0; x < smallSquareSize.size(); x++){
    cout << smallSquareSize[x] << " " << xCoor[x] << " " << yCoor[x] << endl;
  }
}

int main(){
  int squareSize;
  int minimumSquares;

  char board[21][21];

  cin >> squareSize;

  if(squareSize % 2 == 0){
    minimumSquares = findEvenMinimum(squareSize);
    cout << minimumSquares << endl;
    getCoordinates(squareSize, minimumSquares);

  }

  else{
    minimumSquares = findOddMinimum(squareSize, squareSize*squareSize);
    cout << minimumSquares << endl;
  }

  return 0;
}
