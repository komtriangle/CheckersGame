package com.example.checkers.Activities.Interfaces;

import com.example.checkers.Models.GameResult;
import com.example.checkers.Models.User;

import java.util.ArrayList;

public interface IGameHistoryActivity {
    public void handleResultCallBack(ArrayList<GameResult> gameResults);
}
