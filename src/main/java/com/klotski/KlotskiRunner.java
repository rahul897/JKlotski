package com.klotski;

import java.util.*;

public class KlotskiRunner {
    public static void main(String[] args) {
//        Board board = new Board(5,4,Arrays.asList(
//                new Piece(0, 0, new int[][]{{1}, {1}}),
//                new Piece(0, 1, new int[][]{{0, 0}, {0, 0}}),
//                new Piece(0, 3, new int[][]{{2}, {2}}),
//                new Piece(2, 0, new int[][]{{3}, {3}}),
//                new Piece(2, 1, new int[][]{{4, 4}}),
//                new Piece(2, 3, new int[][]{{5}, {5}}),
//                new Piece(4, 0, new int[][]{{8}}),
//                new Piece(3, 1, new int[][]{{6}}),
//                new Piece(3, 2, new int[][]{{7}}),
//                new Piece(4, 3, new int[][]{{9}})
//        ));
        Board board = new Board(7,5,Arrays.asList(
                new Piece(2, 0, new int[][]{{1, 1, 1}}),
                new Piece(2, 3, new int[][]{{2, 2}}),
                new Piece(3, 0, new int[][]{{7, 7}}),
                new Piece(3, 2, new int[][]{{6, 6, 6}}),
                new Piece(4, 0, new int[][]{{3, 3, 3},{-1, -1, 3},{-1, -1, 3}}),
                new Piece(4, 3, new int[][]{{4, 4},{-1, 4}}),
                new Piece(5, 3, new int[][]{{5, -1},{5, 5}}),
                new Piece(5, 0, new int[][]{{0, 0},{0, 0}})
        ));

        board.printBoard();
        long startTime = System.currentTimeMillis();
        Queue<Board> queue = new ArrayDeque<>();
        Set<String> vis = new HashSet<>();
        queue.add(board);
        vis.add(board.getBoardKey());
        boolean solutionFound = false;
        Board curBoard = null;
        while(!queue.isEmpty()){
            Board cur = queue.remove();
            List<Board> nextBoards = PossibleBoardHelper.newBoards(cur);
            for(Board nextBoard : nextBoards){
                if(!vis.contains(nextBoard.getBoardKey())){
                    vis.add(nextBoard.getBoardKey());
                    queue.add(nextBoard);
                    nextBoard.parent = cur;
                    if(nextBoard.isSolved()){
                        curBoard = nextBoard;
                        solutionFound = true;
                    }
                }
            }
            if(solutionFound)break;
        }
//        System.out.println(vis);
        List<Board> boards = new ArrayList<>();
        if(solutionFound){
            while(curBoard!=null){
                boards.add(curBoard);
                curBoard = curBoard.parent;
            }
            Collections.reverse(boards);
            for(Board solBoard:boards){
                solBoard.printBoard();
            }
            System.out.println(boards.size());
        }
        long endTime = System.currentTimeMillis();
        System.out.printf("%f sec%n", (endTime - startTime)/1000f);

    }
}
