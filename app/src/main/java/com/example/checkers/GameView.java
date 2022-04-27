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


    private  int screenWidth;
    private  int screenHeight;
    private  Game game;
    public GameView(Context context) {
        super(context);
        initializeGame(context);
        PlayingField field =  initializePlayingField();
        this.game = new Game(field);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        FieldDrawer.Draw(game.getField(),canvas);
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
            System.out.println("UP");
            handleEndMoveChecker(event);
        }

        invalidate();
        return  true;
    }

    private  void handleMoveChecker(MotionEvent event){
        int x = (int)event.getX();
        int y = (int)event.getY();
        if(game.getChoosedChecker() == null){
            game.setChoosedChecker(getCheckerByEvent(event));
            if(game.getChoosedChecker() != null){
                game.getChoosedChecker().setMovingCoords(new Point((int)event.getX(), (int)event.getY()));
            }
        }
        else{
            game.getChoosedChecker().setMovingCoords(new Point((int)event.getX(), (int)event.getY()));
        }
    }

    private  void handleEndMoveChecker(MotionEvent event){
        int x = (int)event.getX();
        int y = (int)event.getY();
        if(game.tryMove(x ,y)){
            game.nextStep();
        }
    }

    private  Checker getCheckerByEvent(MotionEvent event){
        int x = (int)event.getX();
        int y = (int)event.getY();
        Checker checker= null;
        Checker checkerToFind = game.getField().getCheckerByCoords(x, y);
        if(game.getField().IsFieldClick(x, y)){
          checker = game.findChecker(checkerToFind);
        }
        return  checker;
    }


    private PlayingField  initializePlayingField(){
        int headerHeight = 300;
        int sideSize = (int)(this.screenWidth * 0.8);
        int top = ((this.screenHeight - sideSize) / 2) - headerHeight;
        int left = (this.screenWidth - sideSize) / 2;
        PlayingField field = new PlayingField(sideSize, top, left);
        return  field;
    }



    private  void drawCheckers(Canvas canvas){
        for(int i = 0; i < game.getFirstPlayersCheckers().size(); i++){
            Checker checker = game.getFirstPlayersCheckers().get(i);
            if(checker.getMovingCoords() == null){
                CheckerDrawer.Draw(game.getField(), checker, canvas, (byte)0);
            }
            else {
                CheckerDrawer.DrawByCoords(checker.getMovingCoords().x, checker.getMovingCoords().y,
                        checker, canvas, (byte)0);
            }

        }
        for(int i = 0; i < game.getSecondPlayerCheckers().size(); i++){
            Checker checker = game.getSecondPlayerCheckers().get(i);
            if(checker.getMovingCoords() == null){
                CheckerDrawer.Draw(game.getField(), checker, canvas, (byte)1);
            }
            else {
                CheckerDrawer.DrawByCoords(checker.getMovingCoords().x, checker.getMovingCoords().y,
                        checker, canvas, (byte)1);
            }
        }
    }


}
