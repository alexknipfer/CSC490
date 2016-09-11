#include <string>
#include <iostream>
#include <vector>

using namespace std;

class SnakesAndLadders{
  public:
    void getFileName(ifstream &);
    void getBoardLayout(ifstream &);

  private:
    int maxSquareNumber;
    int totalSnakesAndLadders;
    vector<int> startSquare;
    vector<char> type;
    vector<int> endSquare;
};
