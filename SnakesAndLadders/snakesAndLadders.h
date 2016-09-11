#include <string>
#include <iostream>
#include <vector>

using namespace std;

class SnakesAndLadders{
  public:
    SnakesAndLadders();
    void getFileName(ifstream &);
    void getBoardLayout(ifstream &);
    void getDiceRolls(ifstream &);
    void analyzeDiceRolls();
    void analyzePosition();
    void printResults();

  private:
    int maxSquareNumber;
    int totalSnakesAndLadders;
    int currentPosition;
    vector<int> startSquare;
    vector<char> type;
    vector<int> endSquare;
    vector<int> diceValues;
    vector<string> gameResults;
};
