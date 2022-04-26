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
        DrawByCoords(x, y, checker, canvas, side);
    }

    public  static  void DrawByCoords(int x, int y, Checker checker, Canvas canvas, byte side){
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        if (side == 0){
            paint.setColor(Color.WHITE);
        }
        else{
            paint.setColor(Color.BLACK);
        }
        canvas.drawCircle(x, y, checker.getRadius(), paint);
    }
}
