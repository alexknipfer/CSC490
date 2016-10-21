import java.util.*;
import java.io.*;


public class Project5 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int vertX;
        int horzY;

        vertX = sc.nextInt();
        horzY = sc.nextInt();

            //keep getting city blocks until 0 and 0 is reached
        while(vertX != 0 && horzY != 0){

            int row = vertX + 1;
            int col = horzY + 1;

                //get total number of nodes
            int nodes = (row) * (col);

            int matrix[][] = buildMatrix(row, col, nodes, sc);

            for(int x = 0; x < nodes; x++){
                for(int y = 0; y < nodes; y++){
                    System.out.print(matrix[x][y] + " ");
                }
                System.out.println();
            }

                //get next city blocks
            vertX = sc.nextInt();
            horzY = sc.nextInt();

        }
    }

    public static int[][] buildMatrix(int row, int col, int nodes, Scanner sc){

            //initializes adjacency matrix to 0
        int[][] matrix = new int[nodes][nodes];

            //the street details (ie. E to W,etc) always needs pairs of two, so max is double
        int streetDetails = 2 * row;

        for(int x = 0; x < streetDetails - 1; x++){
                int r = x / 2;

                // East/west row
                if ( x % 2 == 0 ) {
                    for ( int c = 0; c < col - 1; c++ ) {
                        int speedLimit = sc.nextInt(); // speed limit
                        String direction = sc.next();

                            //road is closed if speed limit is 0, don't add
                        if(speedLimit == 0){
                            continue;
                        }

                        int w = r * col + c; // west node
                        int e = w + 1;        // east node
                        if ( direction.equals( "*" ) || direction.equals( ">" ) ){
                            matrix[w][e] = speedLimit;
                        }

                        if ( direction.equals( "*" ) || direction.equals( "<" ) ){
                            matrix[e][w] = speedLimit;
                        }

                    }
                }

                // North/South row
                else {
                    for ( int c = 0; c < col; c++ ) {
                        int speedLimit = sc.nextInt(); // speed limit
                        String direction = sc.next();

                            //road is closed if speed limit is 0, don't add
                        if(speedLimit == 0){
                            continue;
                        }

                        int n = r * col + c; // north node
                        int s = n + col;     // south node
                        if ( direction.equals( "*" ) || direction.equals( "v" ) ){
                            matrix[n][s] = speedLimit;
                        }

                        if ( direction.equals( "*" ) || direction.equals( "^" ) ){
                            matrix[s][n] = speedLimit;
                        }

                    }
                }
        }   //end row loop

            //do conversion to blips, which is 2520 / speed limit per side
        for(int xRow = 0; xRow < nodes; xRow++) {
            for (int xCol = 0; xCol < nodes; xCol++) {

                //get current speed limit in matrix
                int currSpeedLimit = matrix[xRow][xCol];

                //make sure speed limit is valid and convert by dividing
                if (matrix[xRow][xCol] > 0) {
                    matrix[xRow][xCol] = 2520 / currSpeedLimit;
                }
            }
        }
        return matrix;
    }
}
