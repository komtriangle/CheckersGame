package com.example.checkers.Drawers;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.checkers.Checker;
import com.example.checkers.PlayingField;

public class CheckerDrawer {

    public  static void  Draw(PlayingField field, Checker checker, Canvas canvas, byte side){
        int cellSize = field.getCellSize();
        int x = field.getLeft() + (int)(cellSize * (checker.getCol() - 0.5));
        int y =  field.getTop() + (int)(cellSize * (checker.getRow() - 0.5));
        DrawByCoords(x, y, checker.getRadius(), canvas, side);
    }

    public  static  void DrawByCoords(int x, int y, int radius, Canvas canvas, byte side){
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        if (side == 0){
            paint.setColor(Color.WHITE);
        }
        else{
            paint.setColor(Color.BLACK);
        }
        canvas.drawCircle(x, y, radius, paint);
    }

    public  static void DrawEatenCheckers(PlayingField field, int checkersRadius, int count, Canvas canvas, byte side){
        if(side == 0){
            int top = field.getTop() - 3*(field.getSideSize() / field.getCountCells());
            int left = field.getLeft();
            for(int i =0; i< count; i++){
                int rowNum = 1 + (int)Math.floor((checkersRadius + i*field.getCellSize())/field.getSideSize());
                DrawByCoords(left + (checkersRadius + i*field.getCellSize())%field.getSideSize(),
                        top + checkersRadius*2*rowNum,
                        checkersRadius, canvas, side);
            }
        }
        else{
            int top = field.getTop() + field.getSideSize() + (field.getSideSize() / field.getCountCells());
            int left = field.getLeft();
            for(int i =0; i< count; i++){
                int rowNum = 1 + (int)Math.floor((checkersRadius + i*field.getCellSize())/field.getSideSize());
                DrawByCoords(left + (checkersRadius + i*field.getCellSize()) % field.getSideSize(),
                        top + checkersRadius*2*rowNum,
                        checkersRadius, canvas, side);
            }

        }
    }

}
