package com.example.g_triple_t;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class gameField extends AppCompatActivity {

    private boolean win = false;
    private int count;
    private int totalChances = 0;
    private int[][] gameStateX = {
            {0,0,0},
            {0,0,0},
            {0,0,0}
    };
    private int[][] gameStateO = {
            {0,0,0},
            {0,0,0},
            {0,0,0}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_field);
        Intent intent = getIntent();
        long playerNo = intent.getLongExtra("PlayerNo", 0);
        TextView textView1 = (TextView) findViewById(R.id.titlePlayer);
        TextView textView2 = (TextView) findViewById(R.id.xORo);
        if(playerNo == R.id.player1) {
            textView1.setText(R.string.player1);
            textView2.setText(R.string.x);
            count = 1;
        }
        else if(playerNo == R.id.player2) {
            textView1.setText(R.string.player2);
            textView2.setText(R.string.o);
            count = -1;
        }
    }

    private boolean rowCrossed(int[][] gameState){
        for(int i=0;i<3;i++){
            if(gameState[i][0] == 1 && gameState[i][1] == 1 && gameState[i][2] == 1)
                return true;
        }
        return false;
    }
    private boolean columnCrossed(int[][] gameState){
        for(int i=0;i<3;i++){
            if(gameState[0][i] == 1 && gameState[1][i] == 1 && gameState[2][i] == 1)
                return true;
        }
        return false;
    }

    private boolean diaLeft(int[][] gameState){
        int count = 0;
        for(int i=0;i<3;i++){
            if(gameState[i][i] == 1)
                count++;
        }
        if(count == 3)
            return true;
        return false;
    }

    private boolean diaRight(int[][] gameState){
        int count = 0;
        for(int i=0;i<3;i++){
            if(gameState[3-i-1][i] == 1)
                count++;
        }
        if(count == 3)
            return true;
        return false;
    }

    public int onClickButton(View view) {

        Button xoButton = (Button) view;
        TextView textView1 = (TextView) findViewById(R.id.titlePlayer);
        TextView textView2 = (TextView) findViewById(R.id.xORo);
        TextView textView3 = (TextView) findViewById(R.id.winOrDraw);

        if ( win == false && !xoButton.getText().toString().equalsIgnoreCase("X") && !xoButton.getText().toString().equalsIgnoreCase("O") && totalChances != 9) {
            totalChances++;

            String coord = xoButton.getTag().toString();
            int i = Integer.parseInt(String.valueOf(coord.charAt(0)));
            int j = Integer.parseInt(String.valueOf(coord.charAt(1)));
            if(count == 1){
                Log.d("Coordinates-i",String.valueOf(i));
                Log.d("Coordinates-j", String.valueOf(j));
                gameStateX[i][j] = 1;
            }
            else if(count == -1){
                Log.d("Coordinates-i",String.valueOf(i));
                Log.d("Coordinates-j", String.valueOf(j));
                gameStateO[i][j] = 1;
            }

            if(rowCrossed(gameStateX) || columnCrossed(gameStateX) || diaLeft(gameStateX) || diaRight(gameStateX) || rowCrossed(gameStateO) || columnCrossed(gameStateO) || diaLeft(gameStateO) || diaRight(gameStateO)){
                win = true;
            }

            if(win == true){
                if(String.valueOf(textView1.getText()).equals("Player 1"))
                    textView3.setText("WINNER PLAYER 1");
                else
                    textView3.setText("WINNER PLAYER 2");
                if (count == 1) {
                    xoButton.setText("X");
                }
                else if (count == -1) {
                    xoButton.setText("O");
                }
                textView1.setText("GAME");
                textView2.setText("OVER");
                return 1;
            }

            if (count == 1) {
                xoButton.setText("X");
                textView1.setText(R.string.player2);
                textView2.setText(R.string.o);
            }
            else if (count == -1) {
                xoButton.setText("O");
                textView1.setText(R.string.player1);
                textView2.setText(R.string.x);
            }
            count *= (-1);
        }
        if(win != true && totalChances >= 9){
            textView1.setText("GAME");
            textView2.setText("OVER");
            textView3.setText("DRAW");
        }
        return 0;
    }
}