package com.example.android.RPSLS;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import static com.example.android.RPSLS.R.id.s2wonGamePlV;


public class AppActivity2 extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics);

        int wonGamePlayer = this.getIntent().getIntExtra("wonGamePlayer", 0);
        int gameNumber = this.getIntent().getIntExtra("gameNumber", 0);
        int wonGamePC = this.getIntent().getIntExtra("wonGamePC", 0);
        displayWonGamesPlayer(wonGamePlayer);
        displayWonGamesPC(wonGamePC);
        displayGameNumber(gameNumber);
    }
    public void displayWonGamesPlayer(int score)
    {
        TextView scorePlayer = (TextView) findViewById(s2wonGamePlV);
        scorePlayer.setText(String.valueOf(score));
    }
    public void displayWonGamesPC(int score)
    {
        TextView scorePc = (TextView) findViewById(R.id.s2wonGamePcV);
        scorePc.setText(String.valueOf(score));
    }
    public void displayGameNumber(int gameNumber)
    {
        TextView showGameNumberPlayer = (TextView) findViewById(R.id.s2gamesPlayedPcV);
        showGameNumberPlayer.setText(String.valueOf(gameNumber));
        TextView showGameNumberPC = (TextView) findViewById(R.id.s2gamesPlayedPlV);
        showGameNumberPC.setText(String.valueOf(gameNumber));
    }
}