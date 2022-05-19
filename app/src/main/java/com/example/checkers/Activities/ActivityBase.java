package com.example.checkers.Activities;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import com.example.checkers.Models.User;

public abstract class ActivityBase extends AppCompatActivity {

    protected   void setUserToSharedPreference(User user){
        SharedPreferences.Editor userShapeEditor = getUserSharedEditor();
        userShapeEditor.putString("USER_NAME", user.getUserName());
        userShapeEditor.putInt("ID", user.getId());
        userShapeEditor.apply();
    }

    protected   void resetUserInSharedPreference(){
        SharedPreferences.Editor userShapeEditor = getUserSharedEditor();
        userShapeEditor.remove("USER_NAME");
        userShapeEditor.remove("ID");
        userShapeEditor.apply();
    }


    private SharedPreferences.Editor getUserSharedEditor(){
        String preferenceName = "USER_PREFERENCE";
        SharedPreferences userSharedPreferences = getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
        SharedPreferences.Editor userShapeEditor = userSharedPreferences.edit();
        return  userShapeEditor;
    }

    protected User getUserSharedPreference(){
        User user = null;
        String preferenceName = "USER_PREFERENCE";
        SharedPreferences userSharedPreferences = getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
        if(userSharedPreferences.contains("USER_NAME") && userSharedPreferences.contains("ID")){
            String userName = userSharedPreferences.getString("USER_NAME", "");
            int id = userSharedPreferences.getInt("ID", -1);
            user = new User(id, userName);
        }
        return  user;

    }
}
