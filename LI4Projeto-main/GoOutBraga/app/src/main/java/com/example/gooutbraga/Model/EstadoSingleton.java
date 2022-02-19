package com.example.gooutbraga.Model;

import android.os.Build;

import androidx.annotation.RequiresApi;

public class EstadoSingleton {

    private static Estado instance = null;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static Estado getInstance(){
        if(instance == null){
            instance = new Estado();
        }

        return instance;
    }

}
