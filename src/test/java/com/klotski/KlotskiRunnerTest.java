package com.klotski;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KlotskiRunnerTest {
    KlotskiRunner kr = new KlotskiRunner();

    @Test
    public void checkMainKlotskiBoard(){
        Board board = new Board(5,4,3,1, Arrays.asList(
                new Piece(0, 0, 10, new int[][]{{1}, {1}}),
                new Piece(0, 1, 1, new int[][]{{1, 1}, {1, 1}}),
                new Piece(0, 3, 2, new int[][]{{2}, {2}}),
                new Piece(2, 0, 3, new int[][]{{3}, {3}}),
                new Piece(2, 1, 4, new int[][]{{4, 4}}),
                new Piece(2, 3, 5, new int[][]{{5}, {5}}),
                new Piece(4, 0, 8, new int[][]{{8}}),
                new Piece(3, 1, 6, new int[][]{{6}}),
                new Piece(3, 2, 7, new int[][]{{7}}),
                new Piece(4, 3, 9, new int[][]{{9}})
        ));
        assertEquals(kr.solve(board),117);
    }

    @Test
    public void checkMoveItStandardBoard(){
        Board board = new Board(7,5,0,3,Arrays.asList(
                new Piece(2, 0, 8, new int[][]{{1, 1, 1}}),
                new Piece(2, 3, 2, new int[][]{{2, 2}}),
                new Piece(3, 0, 7, new int[][]{{7, 7}}),
                new Piece(3, 2, 6, new int[][]{{6, 6, 6}}),
                new Piece(4, 0, 3, new int[][]{{3, 3, 3},{0, 0, 3},{0, 0, 3}}),
                new Piece(4, 3, 4, new int[][]{{4, 4},{0, 4}}),
                new Piece(5, 3, 5, new int[][]{{5, 0},{5, 5}}),
                new Piece(5, 0, 1, new int[][]{{1, 1},{1, 1}})
        ));
        assertEquals(kr.solve(board),35);
    }

    @Test
    public void checkMoveItDiffcultBoard(){
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
        assertEquals(kr.solve(board),41);
    }
}
