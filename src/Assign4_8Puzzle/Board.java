package Assign4_8Puzzle;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Stack;

public class Board {
    private final int n;
    private final int[] arrayOfBoard;

    /**
     * construct a board from an n-by-n array of blocks
     * (where blocks[i][j] = block in row i, column j)
     *
     * @param blocks content of board
     */
    public Board(int[][] blocks) {
        //TODO
        n = blocks.length;
        if (n < 2)
            throw new IllegalArgumentException();
        arrayOfBoard = transformTo1DArray(blocks);
        
    }

    /**
     * board dimension n
     *
     * @return dimension
     */
    public int dimension() {
        return n;
    }

    /**
     * number of blocks out of place
     *
     * @return hamming distance
     */
    public int hamming() {
        //  TODO
        int count = 0;
        for (int i = 1; i < arrayOfBoard.length; i++) {
            if (i != arrayOfBoard[i - 1])
                count++;
        }
        return count;


    }

    /**
     * sum of Manhattan distances between blocks and goal
     *
     * @return manhattan distance
     */
    public int manhattan() {
        //TODO
        int i = -1;
        int count = 0;
        while (++i < arrayOfBoard.length) {
            if (arrayOfBoard[i] == 0)
                continue;
            int x1 = (arrayOfBoard[i] - 1) / n;     //  arrayOfBoard[i]的值最终位置的坐标
            int y1 = (arrayOfBoard[i] - 1) % n;
            int x2 = i / n;                         //  一维数组中的i的位置(从0开始），转换为二维数组坐标(从0开始）
            int y2 = i % n;                         //  既当前位置坐标
            count += Math.abs(x2 - x1) + Math.abs(y2 - y1);

        }
        return count;
    }

    /**
     * is this board the goal board?
     *
     * @return is goal?
     */
    public boolean isGoal() {
        for (int i = 0; i < arrayOfBoard.length-1; i++)
            if (arrayOfBoard[i] != i + 1)
                return false;
        return true;
    }

    /**
     * a board that is obtained by exchanging any pair of blocks
     *
     * @return twin board
     */
    public Board twin() {
        //TODO
        int[] copy = Arrays.copyOf(arrayOfBoard, arrayOfBoard.length);

        if (copy[0] != 0 && copy[1] != 0) {
            swap(copy, 0, 1);
        } else swap(copy, 2, 3);

        int[][] twin = transformTo2DArray(copy);
        return new Board(twin);
    }


    /**
     * does this board equal y?
     *
     * @param y the object to be judged
     * @return is equal?
     */
    public boolean equals(Object y) {
        //TODO
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        return Arrays.equals(this.arrayOfBoard, that.arrayOfBoard);

    }


    /**
     * all neighboring boards
     *
     * @return neighboring boards
     */


    public Iterable<Board> neighbors() {
        Stack<Board> stack = new Stack<>();

        int p = 0;
        int x, y;
        for (int i = 0; i < arrayOfBoard.length; i++) {
            if (arrayOfBoard[i] == 0) {
                p = i;
                break;
            }
        }
        x = p / n;      //  第一维
        y = p % n;      //  第二维
        if (check(x - 1, y))
            getNeighbor(stack, x - 1, y, x, y);
        if (check(x + 1, y))
            getNeighbor(stack, x + 1, y, x, y);
        if (check(x, y - 1))
            getNeighbor(stack, x, y - 1, x, y);
        if (check(x, y + 1))
            getNeighbor(stack, x, y + 1, x, y);
        return stack;

    }


    /**
     * string representation of this board
     * (in the output format specified below)
     *
     * @return string representation
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(n);
        for (int i = 0; i < arrayOfBoard.length; i++) {
            if (i % n == 0)
                s.append("\n");
            s.append(String.format("%2d ", arrayOfBoard[i]));

        }
        s.append("\n");
        return s.toString();

    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private void swap(int[][] a, int x1, int y1, int x2, int y2) {
        int temp = a[x1][y1];
        a[x1][y1] = a[x2][y2];
        a[x2][y2] = temp;
    }

    private int[][] transformTo2DArray(int[] a) {
        int n = (int) Math.sqrt(a.length);
        int[][] twoD = new int[n][n];
        for (int i = 0; i < a.length; i++) {
            twoD[i / n][i % n] = a[i];
        }
        return twoD;
    }
    private int[] transformTo1DArray(int[][] a){
        int[] oneD = new int[a.length * a.length];
        for (int i = 0; i < a.length; i++)
            System.arraycopy(a[i], 0, oneD, i * a.length, a.length);
        return oneD;
    }

    private void getNeighbor(Stack<Board> stack, int x1, int y1, int x, int y) {
        int[][] temp;
        temp = transformTo2DArray(arrayOfBoard);
        swap(temp, x1, y1, x, y);
        stack.push(new Board(temp));
    }


    private boolean check(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    //  unit tests
    public static void main(String[] args) {
        //TODO
        int a[][] = new int[3][3];
        a[0][0] = 4;
        a[0][1] = 1;
        a[0][2] = 3;
        a[1][0] = 0;
        a[1][1] = 2;
        a[1][2] = 5;
        a[2][0] = 7;
        a[2][1] = 8;
        a[2][2] = 6;
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(2);
        stack.push(1);
        for (int b: stack){
            StdOut.println(b);
        }
        StdOut.println(stack.pop());
        Board board = new Board(a);
        StdOut.println("dimension:" + board.dimension());
        StdOut.println("Hamming:" + board.hamming());
        StdOut.println("Manhattan:" + board.manhattan());
        StdOut.println("isGoal:" + board.isGoal());
        Board twin = board.twin();
        StdOut.println("dimension:" + twin.dimension());
        StdOut.println("Hamming:" + twin.hamming());
        StdOut.println("Manhattan:" + twin.manhattan());
        StdOut.println("isGoal:" + twin.isGoal());
        StdOut.println(board);

        board.equals(twin);
        for (Board b : board.neighbors())
            StdOut.println(b);
        StdOut.println(twin);
        for (Board b : twin.neighbors())
            StdOut.println(b);
    }
}
