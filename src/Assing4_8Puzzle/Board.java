package Assing4_8Puzzle;


import java.util.Iterator;

public class Board {
    private int length;
    private int dimension;
    private int[] arrayOfBoard;
    /**
     * construct a board from an n-by-n array of blocks
     * (where blocks[i][j] = block in row i, column j)
     * @param blocks
     */
    public Board(int[][] blocks){
        //TODO
        length = blocks.length;
        dimension = (int) Math.sqrt(length);
        for (int i = 0; i < dimension; i++){
            for (int j = 0; j < dimension; j++){
                arrayOfBoard[i*dimension + j] = blocks[i][j];
            }
        }
    }

    /**
     * board dimension n
     * @return
     */
    public int dimension(){
        return dimension;
    }

    /**
     * number of blocks out of place
     * @return
     */
    public int hamming() {
        //TODO
        int count = 0;
        for (int i = 1; i < length; i++) {
            if (i != arrayOfBoard[i - 1])
                count++;
        }
        return count;


    }

    /**
     * sum of Manhattan distances between blocks and goal
     * @return
     */
    public int manhattan(){
        //TODO
        int i = 0;
        int count = 0;
        while (++i < length) {
            if (arrayOfBoard[i - 1] == 0)
                continue;
            int d_value = Math.abs(arrayOfBoard[i - 1] - i);
            count += d_value / dimension + d_value % dimension;

        }
    }

    /**
     * is this board the goal board?
     * @return
     */
    public boolean isGoal(){
        //TODO
    }

    /**
     * a board that is obtained by exchanging any pair of blocks
     * @return
     */
    public Board twin(){
        //TODO
    }

    /**
     * does this board equal y?
     * @param y
     * @return
     */
    public boolean equal(Object y){
        //TODO
    }

    /**
     * all neighboring boards
     * @return
     */
    public Iterator<Board> neighbors(){
        //TODO
    }

    /**
     * string representation of this board
     * (in the output format specified below)
     * @return
     */
    public String toString(){
        //TODO
    }
    //  unit tests
    public static void main(String[] args){
        //TODO
    }
}
