package Assign4_8Puzzle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.Stack;

public class Solver {

    private GameTree gameTree;
    private final boolean isSolvable;

    /**
     * find a solution to the initial board (using the A* algorithm)
     *
     * @param initial initial board
     */
    public Solver(Board initial) {

        if (initial == null)
            throw new IllegalArgumentException();
        int moves = 0;
        int manhattan = initial.manhattan();

        gameTree = new GameTree(initial, manhattan, moves);
        gameTree.prev = null;
        Board predecessor = null;
        MinPQ<GameTree> minPQ = new MinPQ<>();


        int twinMoves = 0;
        int twinManhattan = initial.twin().manhattan();

        GameTree twinGameTree = new GameTree(initial.twin(), twinManhattan, moves);
        twinGameTree.prev = null;
        Board twinPredecessor = null;
        MinPQ<GameTree> twinMinPQ = new MinPQ<>();

        while (!gameTree.board.isGoal() && !twinGameTree.board.isGoal()) {


            moves++;
            for (Board board : gameTree.board.neighbors()) {
                if (board.equals(predecessor))
                    continue;
                GameTree tempTree = new GameTree(board, board.manhattan(), moves);
                tempTree.prev = gameTree;
                minPQ.insert(tempTree);
            }
            gameTree = minPQ.delMin();
            predecessor = gameTree.prev.board;
            moves = gameTree.move;

            twinMoves++;
            for (Board twinBoard : twinGameTree.board.neighbors()) {
                if (twinBoard.equals(twinPredecessor))
                    continue;
                GameTree tempTree = new GameTree(twinBoard, twinBoard.manhattan(), twinMoves);
                tempTree.prev = twinGameTree;
                twinMinPQ.insert(tempTree);
            }
            twinGameTree = twinMinPQ.delMin();
            twinPredecessor = twinGameTree.prev.board;
            twinMoves = twinGameTree.move;
        }

        isSolvable = gameTree.board.isGoal();
    }

    private class GameTree implements Comparable<GameTree> {
        private final Board board;
        private GameTree prev;

        private final int move;
        private final int manhattan;
        private final int priority;

        private GameTree(Board board, int manhattan, int moves) {
            this.board = board;

            this.move = moves;
            this.manhattan = manhattan;
            this.priority = this.manhattan + this.move;
        }

        @Override
        public int compareTo(GameTree o) {
            if (this.priority > o.priority)
                return +1;
            if (this.priority < o.priority)
                return -1;
            return Integer.compare(this.manhattan, o.manhattan);
        }


    }

    /**
     * is the initial board solvable?
     *
     * @return is solvable?
     */
    public boolean isSolvable() {
        //TODO
        return isSolvable;
    }

    /**
     * min number of moves to solve initial board; -1 if unsolvable
     *
     * @return moves
     */
    public int moves() {
        //TODO
        if (isSolvable())
            return gameTree.priority;
        else
            return -1;
    }

    /**
     * sequence of boards in a shortest solution; null if unsolvable
     *
     * @return solution boards
     */
    public Iterable<Board> solution() {
        //TODO
        if (!isSolvable())
            return null;

        Stack<Board> stack = new Stack<>();
        GameTree temp = gameTree;
        while (temp != null) {
            stack.push(temp.board);
            temp = temp.prev;
        }
        Stack<Board> stack1 = new Stack<>();
        while (!stack.empty()) {
            stack1.push(stack.pop());
        }
        return stack1;
    }

    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
