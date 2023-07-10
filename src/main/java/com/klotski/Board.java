package com.klotski;

import lombok.Data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.klotski.Constants.*;
import static java.lang.Math.abs;

@Data
public class Board implements Comparable<Board>{
    int[][] grid;
    List<Piece> pieces;
    Map<Integer, Piece> pieceMap;
    String boardKey = null;
    Board parent = null;
    Piece pick;
    int gx,gy;
    int dist;
    int steps = Integer.MAX_VALUE;

    public Board(int h, int w, int gx, int gy, List<Piece> pieces) {
        ROWS = h;
        COLS = w;
        this.gx = gx;
        this.gy = gy;
        this.grid = new int[ROWS][COLS];
        Arrays.stream(grid).forEach(a->Arrays.fill(a,-1));
        this.pieces = pieces;
        this.pieceMap = new HashMap<>();
        pieces.forEach(this::addPiece);
        this.pick = this.pieceMap.get(1);
        this.dist = abs(pick.y-this.gy)+abs(pick.x-this.gx);
    }

    private void addPiece(Piece p) {
        p.putInGrid(this);
        this.pieceMap.put(p.getId(), p);
    }

    public boolean isSolved(){
        return pick.x==gx && pick.y==gy;
    }

    public String getBoardKey(){
        StringBuilder sb = new StringBuilder();
        for(int[] row: grid){
            for(int el: row) {
                sb.append(effectiveType.getOrDefault(""+el,0));
            }
        }
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

    @Override
    public int compareTo(Board b) {
        return (this.dist+this.steps)-(b.dist+b.steps);
    }
}
