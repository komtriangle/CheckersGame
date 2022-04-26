package com.example.checkers.Drawers;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.checkers.PlayingField;

public class FieldDrawer {

    public static void Draw(PlayingField field, Canvas canvas) {
        Paint darkPaint = getDarkCellPaint();
        Paint lightPaint = getLightCellPaint();
        for(int i = 0; i < field.getCountCells();i++){
            for(int j = 0; j < field.getCountCells(); j++){
                Rect rect = new Rect(field.getLeft() + field.getCellSize()*j,
                        field.getTop() + field.getCellSize()*i,
                        field.getLeft() + field.getCellSize()*(j+1),
                        field.getTop() +  field.getCellSize()*(i+1));
                if((i + j) % 2 == 0){
                    canvas.drawRect(rect, darkPaint);
                }
                else{
                    canvas.drawRect(rect, lightPaint);
                }
            }
        }
    }



    private static   Paint getDarkCellPaint(){
        Paint darkPaint = new Paint();
        darkPaint.setColor(Color.argb(71, 45, 6, 1));
        darkPaint.setStyle(Paint.Style.FILL);
        return  darkPaint;
    }

    private static Paint getLightCellPaint(){
        Paint lightPaint = new Paint();
        lightPaint.setColor(Color.argb(255, 169, 41, 1));
        lightPaint.setStyle(Paint.Style.FILL);
        return  lightPaint;
    }
}
