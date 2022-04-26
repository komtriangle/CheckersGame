package com.example.checkers;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import com.example.checkers.Drawers.CheckerDrawer;
import com.example.checkers.Drawers.FieldDrawer;

import java.util.ArrayList;

public class GameView  extends View {

    private  PlayingField field;
    private ArrayList<Checker> firstPlayersCheckers;
    private  ArrayList<Checker> secondPlayerCheckers;
    private  int screenWidth;
    private  int screenHeight;
    private  final  int fieldSize = 8;
    private  int nextStepPlayerNum = 0;
    private  Checker choosedChecker;
    public GameView(Context context) {
        super(context);
        firstPlayersCheckers = new ArrayList<Checker>();
        secondPlayerCheckers = new ArrayList<Checker>();
        initializeGame(context);
        this.field = initializePlayingField();
        initializeCheckers();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        FieldDrawer.Draw(field, canvas);
        drawCheckers(canvas);
    }

    private  void initializeGame(Context context){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.screenHeight = displayMetrics.heightPixels;
        this.screenWidth = displayMetrics.widthPixels;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        if(event.getAction() == MotionEvent.ACTION_MOVE){
            handleMoveChecker(event);
        }
        else if(event.getAction() == MotionEvent.ACTION_UP){
            handleEndMoveChecker(event);
        }
        invalidate();
        return  true;
    }

    private  void handleMoveChecker(MotionEvent event){
        if(choosedChecker == null){
            choosedChecker = getCheckerByEvent(event);
            if(choosedChecker != null){
                choosedChecker.setMovingCoords(new Point((int)event.getX(), (int)event.getY()));
            }
        }
        else{
            choosedChecker.setMovingCoords(new Point((int)event.getX(), (int)event.getY()));
        }
    }

    private  void handleEndMoveChecker(MotionEvent event){
        int x = (int)event.getX();
        int y = (int)event.getY();
        if(!field.IsFieldClick(x, y)) {
            Checker checker = findCheckerFirstPlayer(choosedChecker);
            if(checker != null){
                checker.setMovingCoords(null);
            }
        }
    }

    private  Checker getCheckerByEvent(MotionEvent event){
        int x = (int)event.getX();
        int y = (int)event.getY();
        Checker checker= null;
        Checker checkerToFind = field.getCheckerByCoords(x, y);
        if(field.IsFieldClick(x, y)){
          checker = findCheckerFirstPlayer(checkerToFind);
        }
        return  checker;
    }

    private  Checker findCheckerFirstPlayer(Checker checker){
        Checker findedChecker = null;
        for(int i =0; i < firstPlayersCheckers.size(); i++){

            if(firstPlayersCheckers.get(i).equals(checker)){
                findedChecker = firstPlayersCheckers.get(i);
                break;
            }
        }
        return  findedChecker;
    }



    private PlayingField  initializePlayingField(){
        int headerHeight = 300;
        int sideSize = (int)(this.screenWidth * 0.8);
        int top = ((this.screenHeight - sideSize) / 2) - headerHeight;
        int left = (this.screenWidth - sideSize) / 2;
        PlayingField field = new PlayingField(sideSize, top, left);
        return  field;
    }

    private  void initializeCheckers(){
        initializeFirstPlayerCheckers();
        initializeSecondPlayerCheckers();
    }

    private void initializeFirstPlayerCheckers(){
        for(int i =0; i< 3;i++){
            for(int j = 0; j < fieldSize; j++){
                if((i + j) % 2 !=0){
                    Checker checker = new Checker(i+1, j+1, (int)field.getCellSize()/2);
                    firstPlayersCheckers.add(checker);
                }
            }
        }
    }

    private void initializeSecondPlayerCheckers(){
        for(int i =fieldSize-3; i< fieldSize;i++){
            for(int j = 0; j < fieldSize; j++){
                if((i + j) % 2 !=0){
                    Checker checker = new Checker(i+1, j+1, (int)field.getCellSize()/2);
                    secondPlayerCheckers.add(checker);
                }
            }
        }
    }

    private  void drawCheckers(Canvas canvas){
        for(int i = 0; i < firstPlayersCheckers.size(); i++){
            Checker checker = firstPlayersCheckers.get(i);
            if(checker.getMovingCoords() == null){
                CheckerDrawer.Draw(field, checker, canvas, (byte)0);
            }
            else {
                CheckerDrawer.DrawByCoords(checker.getMovingCoords().x, checker.getMovingCoords().y,
                        checker, canvas, (byte)0);
            }

        }
        for(int i = 0; i < secondPlayerCheckers.size(); i++){
            Checker checker = secondPlayerCheckers.get(i);
            if(checker.getMovingCoords() == null){
                CheckerDrawer.Draw(field, checker, canvas, (byte)1);
            }
            else {
                CheckerDrawer.DrawByCoords(checker.getMovingCoords().x, checker.getMovingCoords().y,
                        checker, canvas, (byte)1);
            }
        }
    }


}
