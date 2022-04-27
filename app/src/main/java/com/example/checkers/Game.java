package com.example.checkers;

import java.util.ArrayList;

public class Game {

    private  PlayingField field;
    private ArrayList<Checker> firstPlayersCheckers;
    private  ArrayList<Checker> secondPlayerCheckers;
    private  final  int fieldSize = 8;
    private  byte nextStepPlayerNum = 0;
    private  Checker choosedChecker;

    public Checker getChoosedChecker() {
        return choosedChecker;
    }

    public  void nextStep(){
        if(nextStepPlayerNum == 0){
            nextStepPlayerNum = 1;
        }
        else{
            nextStepPlayerNum = 0;
        }
    }

    public void setChoosedChecker(Checker choosedChecker) {
        this.choosedChecker = choosedChecker;
    }

    public Game(PlayingField field){
        firstPlayersCheckers = new ArrayList<Checker>();
        secondPlayerCheckers = new ArrayList<Checker>();
        this.field = field;
        initializeCheckers();
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

    public PlayingField getField() {
        return field;
    }

    public ArrayList<Checker> getFirstPlayersCheckers() {
        return firstPlayersCheckers;
    }

    public ArrayList<Checker> getSecondPlayerCheckers() {
        return secondPlayerCheckers;
    }

    public  boolean IsAllowedMove(int row, int col){
        Checker checkerWithNewPosition = new Checker(row, col);
        if(choosedChecker == null){
            return  false;
        }
        if(checkerWithNewPosition.equals(choosedChecker)){
            return  false;
        }
        //Проверка на то что место для шашки свободно
        if (! IsPositionFree(firstPlayersCheckers, checkerWithNewPosition) ||
        ! IsPositionFree(secondPlayerCheckers, checkerWithNewPosition)){
            return  false;
        }

        //Проверка на недиагональный переход (вверх, вниз, влево или вправо)
        if((choosedChecker.getCol() + choosedChecker.getRow()) % 2 !=
                (checkerWithNewPosition.getCol() + checkerWithNewPosition.getRow()) % 2){
            return  false;
        }
        if (nextStepPlayerNum == 0 && choosedChecker.getRow() > checkerWithNewPosition.getRow()){
            return  false;
        }
        if(nextStepPlayerNum == 1 && choosedChecker.getRow() < checkerWithNewPosition.getRow()){
            return  false;
        }
        if(IsOneCellMove(checkerWithNewPosition)){
            return  true;
        }
        else {
            if(IsEnableEatMove(checkerWithNewPosition)){
                Checker checkerToEat = getCheckerToEat(checkerWithNewPosition);
                eat(checkerToEat);
                return  true;
            }
            return  false;
        }
    }



    public  boolean tryMove(int x, int y){
        boolean isMoved = false;
        Checker checker = findChecker(choosedChecker);
       if(checker != null){
           if(!field.IsFieldClick(x, y)) {
               if(checker != null){
                   checker.setMovingCoords(null);
               }
           }
           else {
               Checker checkerAfterMove = field
                       .getCheckerByCoords(checker.getMovingCoords().x, checker.getMovingCoords().y);
               if(IsAllowedMove(checkerAfterMove.getRow(), checkerAfterMove.getCol())){
                   checker.move(checkerAfterMove.getRow(), checkerAfterMove.getCol());
                   isMoved = true;
               }
               checker.setMovingCoords(null);
           }
       }
        setChoosedChecker(null);
        return  isMoved;
    }

     public Checker findChecker(Checker checker){
        Checker findedChecker = null;
        if(nextStepPlayerNum == 0){
            findedChecker = findChecker(firstPlayersCheckers, checker);
        }
        else{
            findedChecker = findChecker(secondPlayerCheckers, checker);
         }
        return  findedChecker;
    }

    private Checker findChecker(ArrayList<Checker> checkers, Checker checkerToFind){
        Checker findedChecker = null;
        for(int i =0; i < checkers.size(); i++){

            if(checkers.get(i).equals(checkerToFind)){
                findedChecker = checkers.get(i);
                break;
            }
        }
        return findedChecker;
    }

    private  boolean IsOneCellMove(Checker checkerAfterMove){
        if((Math.abs(choosedChecker.getRow() - checkerAfterMove.getRow()) == 1)
                || (Math.abs(choosedChecker.getCol() - checkerAfterMove.getCol())== 1)){
            return  true;
        }
        return  false;
    }

    private  boolean IsEnableEatMove(Checker checkerAfterMove){
        if((Math.abs(choosedChecker.getRow() - checkerAfterMove.getRow()) != 2)
                || (Math.abs(choosedChecker.getCol() - checkerAfterMove.getCol())!= 2)){
            return  false;
        }
        Checker checkerToEat = getCheckerToEat(checkerAfterMove);
        if(nextStepPlayerNum == 1){
            if(IsPositionFree(firstPlayersCheckers, checkerToEat)){
                return false;
            }
        }
        else {
            if(IsPositionFree(secondPlayerCheckers, checkerToEat)){
                return false;
            }
        }
        return  true;

    }

    private  Checker getCheckerToEat(Checker checkerAfterMove){
        int colCheckerToEat = Math.max(choosedChecker.getCol(), checkerAfterMove.getCol()) - 1;
        int rowCheckerToEat = Math.max(choosedChecker.getRow(), checkerAfterMove.getRow()) - 1;
        Checker checkerToEat = new Checker(rowCheckerToEat, colCheckerToEat);
        return  checkerToEat;
    }


    private  boolean IsPositionFree(ArrayList<Checker> checkers, Checker checkerToCheck){
        for(int i =0; i < checkers.size(); i++){
            Checker checker = checkers.get(i);
            if(checker.equals(checkerToCheck)){
                return  false;
            }
        }
        return  true;
    }

    private  void eat(Checker checkerToEat){
        if(nextStepPlayerNum == 0){
            removeChecker(secondPlayerCheckers, checkerToEat);
        }
        else{
            removeChecker(firstPlayersCheckers, checkerToEat);
        }
    }

    private  void removeChecker(ArrayList<Checker> checkers, Checker checkerToDelete){
        for(int i = 0; i< checkers.size(); i++){
            if(checkerToDelete.equals(checkers.get(i))){
                checkers.remove(i);
                break;
            }
        }
    }
}