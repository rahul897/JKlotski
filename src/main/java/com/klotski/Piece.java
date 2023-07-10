package com.klotski;

import lombok.Data;

import java.util.Arrays;

import static com.klotski.Constants.*;

@Data
public class Piece {
    int x,y;
    int id;
    int[][] grid;
    int type;

    public Piece(int x, int y, int[][] grid) {
        this.x = x;
        this.y = y;
        this.id = Arrays.stream(grid[0]).max().orElse(-1);
        StringBuilder sb = new StringBuilder();
        for (int[] row : grid) {
            for (int el : row)
                sb.append(Integer.signum(el));
            sb.append(2);
        }
        String effKey = sb.toString();
        this.type = effectiveType.getOrDefault(effKey, effTypeIndex);
        effectiveType.put(""+id, type);
        if(!effectiveType.containsKey(effKey)) effectiveType.put(effKey, effTypeIndex++);

        this.grid = grid;
    }
    public boolean checkMove(int dx, int dy,Board board){
        int nx = x+dx;
        int ny = y+dy;
        int[][] boardGrid = board.getGrid();
        if(!(0<=nx && nx+grid.length<= HEIGHT && 0<=ny && ny+grid[0].length<= WIDTH)) return false;
        for(int row=0;row<grid.length;row++)
            for(int col=0;col<grid[0].length;col++){
                int curId = boardGrid[nx+row][ny+col];
                if(!(curId==-1 || curId==this.id))
                    return false;
            }
        return true;
    }

    public void putInGrid(Board board) {
        putInGrid(board, this.id);
    }
    private void putInGrid(Board board,int val) {
        int[][] boardGrid = board.getGrid();
        for(int row=0;row<grid.length;row++)
            for(int col=0;col<grid[0].length;col++)
                boardGrid[x+row][y+col] = val==-1?-1:grid[row][col];
    }

    public void move(int dx, int dy, Board board) {
        this.putInGrid(board, -1);
        this.x += dx;this.y += dy;
        this.putInGrid(board);
    }
}
