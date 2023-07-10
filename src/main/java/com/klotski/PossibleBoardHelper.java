package com.klotski;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.klotski.Constants.*;

public class PossibleBoardHelper {
    public static List<Board> newBoards(Board board){
        List<Board> boards = new ArrayList<>();
        for(Piece piece: board.getPieces())
            for(int[] dir: new int[][]{{-1,0},{1,0},{0,-1},{0,1}}) {
                if (piece.checkMove(dir[0], dir[1], board)) {
                    Board newBoard = new Board(HEIGHT, WIDTH,
                            board.getPieces().stream().map(p->new Piece(p.x,p.y,p.grid)).collect(Collectors.toList())
                    );
                    Piece newPiece = newBoard.pieceMap.get(piece.getId());
                    newPiece.move(dir[0], dir[1], newBoard);
                    boards.add(newBoard);
                }
            }
        return boards;
    }
}
