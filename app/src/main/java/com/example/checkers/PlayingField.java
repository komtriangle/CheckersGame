package com.example.checkers;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class PlayingField {
    private int sideSize;
    private int top;
    private int left;
    private final int countCells = 8;
    private  int cellSize;

    public PlayingField(int sideSize, int top, int left)
    {
        this.sideSize = sideSize;
        this.top = top;
        this.left = left;
        this.cellSize = sideSize / countCells;
    }

    public int getSideSize() {
        return sideSize;
    }

    public int getTop() {
        return top;
    }

    public int getLeft() {
        return left;
    }

    public int getCountCells() {
        return countCells;
    }

    public int getCellSize() {
        return cellSize;
    }

    public  Checker getCheckerByCoords(int x, int y){
        int col = (int)(Math.floor((x - left) / cellSize) +1);
        int row = (int)(Math.floor((y - top) / cellSize) +1 );
        return  new Checker(row, col);
    }

    public boolean IsFieldClick( int x, int y){
        return  x >= left && y >= top
                && x <= left + sideSize && y <= top + sideSize;
    }
}


