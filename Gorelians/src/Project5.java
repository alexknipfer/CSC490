import java.util.*;

public class Project5 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int vertX;
        int horzY;

            //get set of cities
        vertX = sc.nextInt();
        horzY = sc.nextInt();

            //keep getting city blocks until 0 and 0 is reached
        while(vertX != 0 && horzY != 0){

            int row = vertX + 1;
            int col = horzY + 1;

                //get total number of nodes
            int nodes = (row) * (col);

            int matrix[][] = buildMatrix(row, col, nodes, sc);

            int smallestDistance = dijkstra(nodes, 0, matrix);

                //print route distance in blips
            if(smallestDistance != Integer.MAX_VALUE){
                System.out.println(smallestDistance + " blips");
            }

                //no route exists
            else{
                System.out.println("Holiday");
            }

                //get next set of cities
            vertX = sc.nextInt();
            horzY = sc.nextInt();

        }
    }

    //******************************************************************************************************************

    public static int[][] buildMatrix(int row, int col, int nodes, Scanner sc){

            //initializes adjacency matrix to 0
        int[][] matrix = new int[nodes][nodes];

            //the street details (ie. E to W,etc) always needs pairs of two, so max is double
        int streetDetails = 2 * row;

            //place values (speed limits) in proper place in adjacency matrix
        for(int x = 0; x < streetDetails - 1; x++){
                int addToNode = x / 2;

                //System.out.println("addToNode: " +  + " col: " + col);

                    //if value is even, placing value in matrix for east and west
                if (x % 2 == 0){
                    for (int c = 0; c < col - 1; c++) {
                        int speedLimit = sc.nextInt(); // speed limit
                        String direction = sc.next();

                            //calculate placement for east and west nodes
                        int westNode = (addToNode * col) + c;
                        int eastNode = westNode + 1;

                            //road is closed if speed limit is 0, don't add
                        if(speedLimit == 0) {
                            continue;
                        }

                            //check direction indicator to place value in matrix
                        switch(direction) {
                            case "*":
                                matrix[westNode][eastNode] = speedLimit;
                                break;

                            case ">":
                                matrix[westNode][eastNode] = speedLimit;
                                break;

                            case "<":
                                matrix[eastNode][westNode] = speedLimit;
                                break;
                        }
                    }
                }

                    //value is odd, place for north and south
                else{
                    for(int c = 0; c < col; c++) {
                        int speedLimit = sc.nextInt(); // speed limit
                        String direction = sc.next();

                            //calculate values for north and south nodes for matrix placement
                        int northNode = (addToNode * col) + c; // north node
                        int southNode = northNode + col;     // south node

                            //road is closed if speed limit is 0, don't add
                        if(speedLimit == 0){
                            continue;
                        }

                            //check direction indicator to place value in matrix
                        switch(direction){
                            case "*": matrix[northNode][southNode] = speedLimit;
                                break;

                            case "v": matrix[northNode][southNode] = speedLimit;
                                break;

                            case "^": matrix[southNode][northNode] = speedLimit;
                                break;
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

    //******************************************************************************************************************

        //use dijkstra's algorithm to find smallest path, method takes in the adjacency matrix

    public static int dijkstra(int node, int start, int[][] matrix)
    {
        boolean pathFound[] = new boolean[node];
        int vertDistance[] = new int[node];
        int parent[] = new int[node];

            //initialize arrays, path found to false and vert distance to "infinity"
        for(int i=0; i < node; i++)
        {
            pathFound[i] = false;
            vertDistance[i] = Integer.MAX_VALUE;
            parent[i] = -1;
        }

        // distance to start is 0 ...
        vertDistance[start] = 0;

        // guaranteed that starting vertex is cheapest from start, so add it first.
        int toV = start;

        // stop when the cheapest node left has already been located
        while (!pathFound[toV]) {

            // add best visible right now into result ...
            pathFound[toV] = true;

            // adjust all weights according to the newly added vertex
            for (int toW = 0; toW < node; toW++) {

                    //if weight is 0, start over
                if (toW == toV || matrix[toV][toW] == 0) {
                    continue;
                }

                    //set weight to current matrix value
                int weight = matrix[toV][toW];

                if (vertDistance[toW] > vertDistance[toV] + weight) {
                    vertDistance[toW] = vertDistance[toV] + weight;
                    parent[toW] = toV;
                }
            }

            // find next minimum vertex ...
            toV = 0;
            int lowestDist = Integer.MAX_VALUE;

                //go through and find the lowest distance
            for (int testV = 1; testV < node; testV++) {
                if (!pathFound[testV] && vertDistance[testV] < lowestDist) {
                    lowestDist = vertDistance[testV];
                    toV = testV;
                }
            }
        }
        return vertDistance[node - 1];
    }
}
