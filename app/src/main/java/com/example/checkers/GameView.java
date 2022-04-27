package com.example.checkers;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
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
        drawCanvasStyles(canvas);
        FieldDrawer.Draw(game.getField(),canvas);
        drawCheckers(canvas);
        drawEatenCheckers(canvas);
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
        if(game.getChoosedChecker() == null){
            game.setChoosedChecker(getCheckerByEvent(event));
            if(game.getChoosedChecker() != null){
                updateChoosedCheckerCoords(event);
            }
        }
        else{
            game.getChoosedChecker().setMovingCoords(new Point((int)event.getX(), (int)event.getY()));
        }
    }

    private  void handleEndMoveChecker(MotionEvent event){
        int x = (int)event.getX();
        int y = (int)event.getY();
        updateChoosedCheckerCoords(event);
        if(game.getChoosedChecker() != null && game.IsCanEat(game.getNextStepPlayerNum())){
            Checker checkerAfterMove = game.getField().getCheckerByCoords(x, y);
            if(game.IsEnableEatMove(checkerAfterMove)){
                if(game.tryMove(x ,y)){
                    if(!game.IsCanCheckerEat(game.getChoosedChecker(), game.getNextStepPlayerNum())){
                        game.cleanChoosedMove();
                        game.nextStep();
                    }
                }
            }
            else{
                game.cleanChoosedMove();
            }
        }
        else{
            if(game.tryMove(x ,y)){
                game.cleanChoosedMove();
                game.nextStep();
            }
        }
    }

    private  void updateChoosedCheckerCoords(MotionEvent event){
        if(game.getChoosedChecker() != null){
            game.getChoosedChecker().setMovingCoords(new Point((int)event.getX(), (int)event.getY()));
        }
    }

    private  Checker getCheckerByEvent(MotionEvent event){
        int x = (int)event.getX();
        int y = (int)event.getY();
        Checker checker= null;
        Checker checkerToFind = game.getField().getCheckerByCoords(x, y);
        if(game.getField().IsFieldClick(x, y)){
          checker = game.findChecker(checkerToFind, game.getNextStepPlayerNum());
        }
        return  checker;
    }


    private PlayingField  initializePlayingField(){
        int headerHeight = 150;
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
                        checker.getRadius(), canvas, (byte)0);
            }

        }
        for(int i = 0; i < game.getSecondPlayerCheckers().size(); i++) {
            Checker checker = game.getSecondPlayerCheckers().get(i);
            if (checker.getMovingCoords() == null) {
                CheckerDrawer.Draw(game.getField(), checker, canvas, (byte) 1);
            } else {
                CheckerDrawer.DrawByCoords(checker.getMovingCoords().x, checker.getMovingCoords().y,
                        checker.getRadius(), canvas, (byte) 1);
            }
        }
    }

    private  void drawEatenCheckers(Canvas canvas){
        if(game.getFirstPlayersCheckers().size() < game.getCountCheckers()){
            CheckerDrawer.DrawEatenCheckers(game.getField(), game.getField().getCellSize()/2,
                    game.getCountCheckers() - game.getFirstPlayersCheckers().size(),
                    canvas, (byte)0);
        }

        if(game.getSecondPlayerCheckers().size() < game.getCountCheckers()){
            CheckerDrawer.DrawEatenCheckers(game.getField(), game.getField().getCellSize()/2,
                    game.getCountCheckers() - game.getSecondPlayerCheckers().size(),
                    canvas, (byte)1);
        }
    }

    private  void drawCanvasStyles(Canvas canvas){
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        int backgroundColor = getResources().getColor(R.color.background);
        paint.setColor(backgroundColor);
        canvas.drawPaint(paint);
    }


}
