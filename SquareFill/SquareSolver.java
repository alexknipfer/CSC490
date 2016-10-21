
package squaresolver;

import java.util.Scanner;

/**
 *
 * @author duane_timmerberg
 */
public class SquareSolver {
    private static int solEven = 4;
    private static int[][] sol2array = { 
        {1,0,0},
        {1,0,1},
        {1,1,0},
        {1,1,1}                  };
    private static int sol3 = 6;
    private static int[][] sol3array = { 
        {2,0,0},
        {1,0,2},
        {1,1,2},
        {1,2,0},
        {1,2,1},
        {1,2,2}                  };
    private static int sol5 = 8;
    private static int[][] sol5array = { 
        {3,0,0},
        {2,0,3},
        {1,2,3},
        {1,2,4},
        {2,3,0},
        {1,3,2},
        {2,3,3},
        {1,4,2}                  };
    private static int sol7 = 9;
    private static int[][] sol7array = { 
        {4,0,0},
        {3,0,4},
        {2,3,4},
        {1,3,6},
        {3,4,0},
        {1,4,3},
        {1,4,6},
        {2,5,3},
        {2,5,5}                  };
    
   
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        System.out.print("gimme num num: ");
        final int SIZE = in.nextInt();
        //************************************************************************
        if (SIZE%2 == 0)
        {
            int[][] genericArray = new int[4][3];
            for (int i = 0;i<4;i++)
            {
                for (int j = 0;j<3;j++)
                {
                    genericArray[i][j] = sol2array[i][j] * (SIZE/2);
                }
            }
            System.out.print(solEven);
            System.out.println();
            for (int i = 0;i<4;i++)
            {
                for (int j = 0;j<3;j++)
                {
                    System.out.print(genericArray[i][j] + ", ");
                }
                System.out.println();
            }
        }//end if even
        //************************************************************************
        //************************************************************************
        else if (SIZE%3 == 0)
        {
            int[][] genericArray = new int[6][3];
            for (int i = 0;i<6;i++)
            {
                for (int j = 0;j<3;j++)
                {
                    genericArray[i][j] = sol3array[i][j] * (SIZE/3);
                }
            }
            System.out.print(sol3);
            System.out.println();
            for (int i = 0;i<6;i++)
            {
                for (int j = 0;j<3;j++)
                {
                    System.out.print(genericArray[i][j] + ", ");
                }
                System.out.println();
            }
        }//end if div by 3
        //************************************************************************
        //************************************************************************
        else if (SIZE%5 == 0)
        {
            int[][] genericArray = new int[8][3];
            for (int i = 0;i<8;i++)
            {
                for (int j = 0;j<3;j++)
                {
                    genericArray[i][j] = sol3array[i][j] * (SIZE/5);
                }
            }
            System.out.print(sol3);
            System.out.println();
            for (int i = 0;i<8;i++)
            {
                for (int j = 0;j<3;j++)
                {
                    System.out.print(genericArray[i][j] + ", ");
                }
                System.out.println();
            }
        }//end if div by 5
        //************************************************************************
        //************************************************************************
        else if (SIZE%7 == 0)
        {
            int[][] genericArray = new int[9][3];
            for (int i = 0;i<9;i++)
            {
                for (int j = 0;j<3;j++)
                {
                    genericArray[i][j] = sol3array[i][j] * (SIZE/7);
                }
            }
            System.out.print(sol3);
            System.out.println();
            for (int i = 0;i<9;i++)
            {
                for (int j = 0;j<3;j++)
                {
                    System.out.print(genericArray[i][j] + ", ");
                }
                System.out.println();
            }
        }//end if div by 7
        //************************************************************************
        //************************************************************************
        else
        {
            int square = 0;
            int[][] grid = new int[SIZE][SIZE];
            int[][] answerArray = new int[50][3];
            for (int i = 0; i < SIZE; i++)
            {
                for (int j = 0; j < SIZE; j++)
                {
                    grid[i][j] = 0;
                }
            }
            //********************************  STEUP GRID w/ first 4 squares **************************
            square = 1;
            for (int i =0;i<(SIZE+1)/2; i++)
                for (int j = 0; j < (SIZE+1)/2; j++)
                    grid[i][j] = square;
            square++;
            
            for (int i =(SIZE+1)/2;i<SIZE; i++)
                for (int j = 0; j < (SIZE+1)/2 - 1; j++)
                    grid[i][j] = square;
            square++;
            
            for (int i =0; i < (SIZE+1)/2 - 1; i++)
                for (int j = (SIZE+1)/2; j < SIZE; j++)
                    grid[i][j] = square;
            square++;
            
            grid[(SIZE+1)/2 - 1][(SIZE+1)/2] = square;
            square++;
            //********************************  SETUP Complete ****************************
            
            
            
            
            
            
            
            
            
            
        }//end else prime
    }//end main
    
}
