package com.example.checkers;

import android.graphics.Point;

import androidx.annotation.Nullable;

public class Checker{
    private int row;
    private  int col;
    private  int radius;
    private Point movingCoords;

    public Checker(int row, int col, int radius){
        this.row = row;
        this.col = col;
        this.radius = radius;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public  Checker(int row, int col){
        this.row = row;
        this.col = col;
        this.radius = 0;
    }

    public  int getRow(){
        return  row;
    }

    public  int getCol(){
        return col;
    }

    public  int getRadius(){
        return  radius;
    }

    public Point getMovingCoords() {
        return movingCoords;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Checker other = (Checker) obj;

        if (row != other.getRow() || col != other.getCol()) {
            return false;
        }

        return true;
    }

    public void setMovingCoords(Point movingCoords) {
        this.movingCoords = movingCoords;
    }

    public  void move(int row, int col){
        this.row = row;
        this.col = col;
    }


}
