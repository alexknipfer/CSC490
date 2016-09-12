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

  //initialize current position to starting position at 0
SnakesAndLadders::SnakesAndLadders(){
  currentPosition = 0;
}

//******************************************************************************

  //take input file name from console
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

  //get the board size and snakes and ladders positions
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

  //this function gets the amount of sequences and all the dice rolls
  //for each sequence
void SnakesAndLadders::getDiceRolls(ifstream &myFile){
  int numOfSequences;
  int numOfRolls;
  int diceValue;

    //get amount of dice sequences from file
  myFile >> numOfSequences;

    //loop through number of sequences to get rolls
  for(int x = 0; x < numOfSequences; x++){
    myFile >> numOfRolls;

      //now loop through rolls on current sequence
      //add the value of roll to the dice vector
    for(int y = 0; y < numOfRolls; y++){
      myFile >> diceValue;
      diceValues.push_back(diceValue);
    }

      //go analyze the dice rolls in current sequence
    analyzeDiceRolls();

      //if the position is >= board size then the sequence of rolls is a Winner
      //add win or loose to vector to print later after all sequences are complete
    if(currentPosition >= maxSquareNumber){
      gameResults.push_back("Winner!");
    }
    else{
      gameResults.push_back("Did not make it!");
    }

      //reset position and dice vector for next sequence
    diceValues.clear();
    currentPosition = 0;
  }
}

//******************************************************************************

  //adjust position for the current sequence of dice rolls
void SnakesAndLadders::analyzeDiceRolls(){
  for(int x = 0; x < diceValues.size(); x++){
    currentPosition += diceValues[x];
    if(currentPosition >= maxSquareNumber){
      return;
    }
      //go see if the roll landed on a snake or ladder
    analyzePosition();
  }
}

//******************************************************************************

  //determine if the roll landed on a snake or ladder and reset the position
  //to new position if landed on either
void SnakesAndLadders::analyzePosition(){
  for(int x = 0; x < startSquare.size(); x++){
    if(currentPosition == startSquare[x]){
      currentPosition = endSquare[x];
    }
  }
}

//******************************************************************************

  //print the game results for all sequences
void SnakesAndLadders::printResults(){
  for(int x = 0; x < gameResults.size(); x++){
    cout << gameResults[x] << endl;
  }
}

//******************************************************************************
