package com.klotski;

import lombok.Data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.klotski.Constants.*;

@Data
public class Board{
    int[][] grid;
    List<Piece> pieces;
    Map<Integer, Piece> pieceMap;
    String boardKey = null;
    Board parent = null;

    public Board(int h, int w, List<Piece> pieces) {
        HEIGHT = h;
        WIDTH = w;
        this.grid = new int[HEIGHT][WIDTH];
        Arrays.stream(grid).forEach(a->Arrays.fill(a,-1));
        this.pieces = pieces;
        this.pieceMap = new HashMap<>();
        pieces.forEach(this::addPiece);
    }

    private void addPiece(Piece p) {
        p.putInGrid(this);
        this.pieceMap.put(p.getId(), p);
    }

    public boolean isSolved(){
        return grid[0][WIDTH - 1] == 0 && grid[1][WIDTH - 1] == 0;
//        return grid[HEIGHT-1][1]==0 && grid[HEIGHT -1][2]==0;
    }

    public String getBoardKey(){
        StringBuilder sb = new StringBuilder();
        for(int[] row: grid){
            for(int el: row) {
                sb.append(effectiveType.getOrDefault(""+el,0));
            }
        }
//        for(Piece piece:pieces){
//            sb.append(piece.getX());
//            sb.append(piece.getY());
//            sb.append(piece.getType());
//        }
        boardKey = sb.toString();
        return boardKey;
    }

    public void printBoard(){
        for(int[] row: grid){
            for(int el: row){
                System.out.print(el+" ");
            }
            System.out.println("\n");
        }
        System.out.println("------------------------------");
    }

}
