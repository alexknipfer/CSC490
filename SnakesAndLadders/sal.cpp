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
  myGame.getDiceRolls(myFile);
  myGame.printResults();
  return 0;
}

//******************************************************************************

SnakesAndLadders::SnakesAndLadders(){
  currentPosition = 0;
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

void SnakesAndLadders::getDiceRolls(ifstream &myFile){
  int numOfSequences;
  int numOfRolls;
  int diceValue;

  myFile >> numOfSequences;

  for(int x = 0; x < numOfSequences; x++){
    myFile >> numOfRolls;
    //cout << numOfRolls << endl;
    for(int y = 0; y < numOfRolls; y++){
      myFile >> diceValue;
      diceValues.push_back(diceValue);
    }

    analyzeDiceRolls();
    if(currentPosition >= maxSquareNumber){
      gameResults.push_back("Winner!");
    }
    else{
      gameResults.push_back("Did not make it!");
    }

    diceValues.clear();
    currentPosition = 0;
  }
}

//******************************************************************************

void SnakesAndLadders::analyzeDiceRolls(){
  for(int x = 0; x < diceValues.size(); x++){
    cout << currentPosition << endl;
    currentPosition += diceValues[x];
    if(currentPosition >= maxSquareNumber){
      return;
    }
    analyzePosition();
  }
}

//******************************************************************************

void SnakesAndLadders::analyzePosition(){
  for(int x = 0; x < startSquare.size(); x++){
    if(currentPosition == startSquare[x]){
      currentPosition = endSquare[x];
    }
  }
}

//******************************************************************************

void SnakesAndLadders::printResults(){
  for(int x = 0; x < gameResults.size(); x++){
    cout << gameResults[x] << endl;
  }
}

//******************************************************************************
