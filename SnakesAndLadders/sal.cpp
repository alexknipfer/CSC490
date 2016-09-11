#include <iostream>
#include <fstream>
#include <iomanip>
#include <string>
#include "snakesAndLadders.h"

using namespace std;

int main(){
  SnakesAndLadders myGame;
  ifstream myFile;

  myGame.getFileName(myFile);
  myGame.getBoardLayout(myFile);
  return 0;
}

//******************************************************************************

void SnakesAndLadders::getFileName(ifstream &myFile){
  string fileName;

  cout << "Enter a file name: ";

    //keep prompting until user has typed in a valid file name
  while(true){
    cin >> fileName;
    myFile.open(fileName);

    if(!myFile){
      cout << "Invalid file name, Please enter a valid file name: ";
    }
    else{
      break;
    }
  }
}

//******************************************************************************

void SnakesAndLadders::getBoardLayout(ifstream &myFile){
  int start;
  char typeSnakeOrLadder;
  int end;

  myFile >> maxSquareNumber;
  myFile >> totalSnakesAndLadders;

    //go through snake and ladder combinations and add them to appropriate
    //vectors
  for(int x = 0; x < totalSnakesAndLadders; x++){
    myFile >> start;
    myFile >> typeSnakeOrLadder;
    myFile >> end;
    startSquare.push_back(start);
    type.push_back(typeSnakeOrLadder);
    endSquare.push_back(end);
  }
}
//******************************************************************************
