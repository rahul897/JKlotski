package com.klotski;

import java.util.*;

public class KlotskiRunner {

    public int solve(Board board){
        long startTime = System.currentTimeMillis();
        Queue<Board> queue = new PriorityQueue<>();
        Set<String> vis = new HashSet<>();
        board.steps = 0;
        queue.add(board);
        vis.add(board.getBoardKey());
        boolean solutionFound = false;
        Board curBoard = null;
        while(!queue.isEmpty()){
            Board cur = queue.remove();
            List<Board> nextBoards = PossibleBoardHelper.newBoards(cur);
            for(Board nextBoard : nextBoards){
                if(nextBoard.steps>cur.steps+1 && !vis.contains(nextBoard.getBoardKey())){
                    nextBoard.steps = cur.steps+1;
                    nextBoard.parent = cur;
                    vis.add(nextBoard.getBoardKey());
                    if (nextBoard.isSolved()) {
                        curBoard = nextBoard;
                        solutionFound = true;
                        break;
                    }
                    queue.add(nextBoard);
                }
            }
            if(solutionFound)break;
        }
        int steps = -1;
        if(solutionFound)
            steps = tracePath(curBoard);
        else System.out.println("no Solution found");
        long endTime = System.currentTimeMillis();
        System.out.printf("%f sec%n", (endTime - startTime)/1000f);
        return steps;
    }

    private int tracePath(Board curBoard) {
        List<Board> boards = new ArrayList<>();
        while(curBoard!=null){
            boards.add(curBoard);
            curBoard = curBoard.parent;
        }
        Collections.reverse(boards);
        for(Board solBoard:boards){
            solBoard.printBoard();
        }
        System.out.println("steps needed "+boards.size());
        return  boards.size();
    }

    public static void main(String[] args) {

        Board board = new Board(6,5,0,3,Arrays.asList(
                new Piece(4, 0, 1, new int[][]{{1, 1},{1, 1}}),
                new Piece(0, 1, 2, new int[][]{{1}}),
                new Piece(0, 3, 3, new int[][]{{1}}),
                new Piece(1, 3, 4, new int[][]{{1}}),
                new Piece(0, 2, 5, new int[][]{{2},{2}}),
                new Piece(0, 4, 6, new int[][]{{2},{2}}),
                new Piece(4, 2, 7, new int[][]{{2},{2}}),
                new Piece(3, 3, 8, new int[][]{{4, 4},{0, 4}}),
                new Piece(4, 3, 9, new int[][]{{5, 0},{5, 5}}),
                new Piece(1, 0, 10, new int[][]{{4, 4},{4, 0}}),
                new Piece(2, 0, 11, new int[][]{{0, 5},{5, 5}})
                ));
        KlotskiRunner kr = new KlotskiRunner();
        kr.solve(board);
    }
}
