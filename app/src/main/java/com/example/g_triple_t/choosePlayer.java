package com.example.g_triple_t;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class choosePlayer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_player);
        Intent intent = getIntent();
    }

    public void onClickPlayer(View view){
        Intent intent = new Intent(choosePlayer.this,gameField.class);
        long id = view.getId();
        intent.putExtra("PlayerNo",id);
        startActivity(intent);
    }
}