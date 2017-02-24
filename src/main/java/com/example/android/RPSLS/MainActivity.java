package com.example.android.RPSLS;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements Parcelable {
    boolean lizard;
    boolean spock;
    boolean scissors;
    boolean paper;
    boolean rock;

    String pcSpock = "Spock";
    String pcScissors = "Scissors";
    String pcPaper = "Paper";
    String pcRock = "Rock";
    String pcLizard = "Lizard";

    int gameNumber = 1;
    int pointPlayer = 0;
    int pointPC = 0;
    static int wonGamePlayer = 0;
    int wonGamePC = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        /**clear the marking in main screen*/
        displayWhatHappened("");
        displayBanner("");
        displayBannerFigure("");
        displayPCFigure("");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mMenuInflater = getMenuInflater();
        mMenuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    /**
     * SELECTED MENU BUTTON ACTION?
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //handle item selection
        switch (item.getItemId()) {
            case R.id.buttonStatistics:
                //   MainActivity dataToSend = new MainActivity();
                Intent intent = new Intent(this, AppActivity2.class);
                intent.putExtra("wonGamePlayer", wonGamePlayer);
                intent.putExtra("gameNumber", gameNumber);
                intent.putExtra("wonGamePC", wonGamePC);
                startActivity(intent);
                return true;

            case R.id.buttonNew_game:
                gameNumber = 1;
                wonGamePlayer = 0;
                wonGamePC = 0;
                pointPC = 0;
                pointPlayer = 0;
                displayWhatHappened("");
                displayBanner("");
                displayBannerFigure("");
                displayPCFigure("");
                displayPlayerScore(pointPlayer);
                displayPCScore(pointPC);
                displayGameNumber(gameNumber);
        }
        return super.onOptionsItemSelected(item);
    }
    public void displayBanner(String score) {
        TextView scorePlayer = (TextView) findViewById(R.id.resultBanner);
        scorePlayer.setText(String.valueOf(score));
    }

    public void displayBannerFigure(String figure) {
        TextView scorePlayer = (TextView) findViewById(R.id.figurePlayer);
        scorePlayer.setText(String.valueOf(figure));
    }

    public void displayPCFigure(String figurePc) {
        TextView scorePlayer = (TextView) findViewById(R.id.figurePC);
        scorePlayer.setText(String.valueOf(figurePc));
    }

    public void displayWhatHappened(String whatHappened) {
        TextView scorePlayer = (TextView) findViewById(R.id.whatHappened);
        scorePlayer.setText(String.valueOf(whatHappened));
    }

    public void displayGameNumber(int gameNumber) {
        TextView GameNumber = (TextView) findViewById(R.id.gameNumber);
        GameNumber.setText(String.valueOf("Game#" + gameNumber));
    }

    public void displayPlayerScore(int score) {
        TextView scorePlayer = (TextView) findViewById(R.id.scorePlayer);
        scorePlayer.setText(String.valueOf(score));
    }

    public void displayPCScore(int score) {
        TextView scorePC = (TextView) findViewById(R.id.scorePC);
        scorePC.setText(String.valueOf(score));
    }
    /**
     * Button clicked Lizard
     */
    public void lizard(View v) {
        lizard = true;
        result();
        displayBannerFigure("Lizard");
    }

    public void spock(View v) {
        spock = true;
        result();
        displayBannerFigure("Spock");
    }

    public void scissors(View v) {
        scissors = true;
        result();
        displayBannerFigure("Scissors");
    }

    public void paper(View v) {
        paper = true;
        result();
        displayBannerFigure("Paper");
    }

    public void rock(View v) {
        rock = true;
        result();
        displayBannerFigure("Rock");
    }
    /**LEGEND OF FIGURES PC = X
     * 0 SPOCK
     * 1 SCISSORS
     * 2 PAPER
     * 3 ROCK
     * 4 LIZARD
     * */
    /*******
     * Changes Banner for winning string
     ********/
    public void calc() {
        displayPlayerScore(pointPlayer);
        displayPCScore(pointPC);
        if (pointPC == 3 || pointPlayer == 3) {

            if (pointPC == 3) {
                displayBanner(getString(R.string.pcWonRound));
                wonGamePC++;
            } else {
                displayBanner(getString(R.string.playerWonRound));
                wonGamePlayer++;
            }
        }
    }
    /*********
     * reset player/pc score
     *********/
    public void reset() {
        if (pointPC == 3 || pointPlayer == 3) {
            pointPC = 0;
            pointPlayer = 0;
            gameNumber++;
            displayGameNumber(gameNumber);
        }
    }
    public void result() {
        // RANDOM NUMBER GENERATED FOR PC AS HIS CHOICE OF FIGURE 0-4
        Random r = new Random();
        int pcNumber = r.nextInt(5);
        reset();

        if (lizard == true) {
            if (pcNumber == 4) {
                /**TIE*/
                displayBanner(getString(R.string.draw));
                displayPCFigure(pcLizard);
                displayWhatHappened(getString(R.string.whatHappenedDoubleKill));
            } else if (pcNumber == 0) {
                /**Player WIN - Lizard Poisons Spock*/
                displayBanner(getString(R.string.playerWin));
                displayPCFigure(pcSpock);
                displayWhatHappened(getString(R.string.whatHappenedLizardSpock));
                pointPlayer++;

            } else if (pcNumber == 1) {
                /**Player Lose - Scissors decapitate Lizard*/
                displayBanner(getString(R.string.playerLose));
                displayPCFigure(pcScissors);
                displayWhatHappened(getString(R.string.whatHappenedLizardScissors));
                pointPC++;

            } else if (pcNumber == 2) {
                /**Player Win - Lizard eats Paper*/
                displayBanner(getString(R.string.playerWin));
                displayPCFigure(pcPaper);
                displayWhatHappened(getString(R.string.whatHappenedLizardPaper));
                pointPlayer++;
            } else if (pcNumber == 3) {
                /**Player Loses - Rock Crushes Lizard*/
                displayBanner(getString(R.string.playerLose));
                displayPCFigure(pcRock);
                displayWhatHappened(getString(R.string.whatHappenedLizardRock));
                pointPC++;
            }
        } else if (spock == true) {
            if (pcNumber == 4) {
                /**Player Lose - Lizard Poisons Spock*/
                displayBanner(getString(R.string.playerLose));
                displayPCFigure(pcLizard);
                displayWhatHappened(getString(R.string.whatHappenedLizardSpock));
                pointPC++;
            } else if (pcNumber == 0) {
                /**draw*/
                displayBanner(getString(R.string.draw));
                displayPCFigure(pcSpock);
                displayWhatHappened(getString(R.string.whatHappenedDoubleKill));
            } else if (pcNumber == 1) {
                /**Player Win - Spock smashes Scissors*/
                displayBanner(getString(R.string.playerWin));
                displayPCFigure(pcScissors);
                displayWhatHappened(getString(R.string.whatHappenedSpockScissors));
                pointPlayer++;
            } else if (pcNumber == 2) {
                /**Player Lose - Paper disproves Spock*/
                displayBanner(getString(R.string.playerLose));
                displayPCFigure(pcPaper);
                displayWhatHappened(getString(R.string.whatHappenedSpockPaper));
                pointPC++;
            } else if (pcNumber == 3) {
                /**Player Win - Spock vaporizes Rock*/
                displayBanner(getString(R.string.playerWin));
                displayPCFigure(pcRock);
                displayWhatHappened(getString(R.string.whatHappenedSpockRock));
                pointPlayer++;
            }
        } else if (scissors == true) {
            if (pcNumber == 4) {
                /**Player Win - Scissors decapitate Lizard*/
                displayBanner(getString(R.string.playerWin));
                displayPCFigure(pcLizard);
                displayWhatHappened(getString(R.string.whatHappenedLizardScissors));
                pointPlayer++;
            } else if (pcNumber == 0) {
                /**Player Lose - Spock smashes Scissors*/
                displayBanner(getString(R.string.playerLose));
                displayPCFigure(pcSpock);
                displayWhatHappened(getString(R.string.whatHappenedSpockScissors));
                pointPC++;
            } else if (pcNumber == 1) {
                /**TIE*/
                displayBanner(getString(R.string.draw));
                displayPCFigure(pcScissors);
                displayWhatHappened(getString(R.string.whatHappenedDoubleKill));
            } else if (pcNumber == 2) {
                /**Player Win - Scissors cut Paper*/
                displayBanner(getString(R.string.playerWin));
                displayPCFigure(pcPaper);
                displayWhatHappened(getString(R.string.whatHappenedScissorsPaper));
                pointPlayer++;
            } else if (pcNumber == 3) {
                /**Player Loses - Rock Crushes Scissors*/
                displayBanner(getString(R.string.playerLose));
                displayPCFigure(pcRock);
                displayWhatHappened(getString(R.string.whatHappenedScissorsRock));
                pointPC++;
            }
        } else if (paper == true) {
            if (pcNumber == 4) {
                /**Player Lose - Lizard eats Paper*/
                displayBanner(getString(R.string.playerLose));
                displayPCFigure(pcLizard);
                displayWhatHappened(getString(R.string.whatHappenedLizardPaper));
                pointPC++;
            } else if (pcNumber == 0) {
                /**Player Win - Paper disproves Spock*/
                displayBanner(getString(R.string.playerWin));
                displayPCFigure(pcSpock);
                displayWhatHappened(getString(R.string.whatHappenedSpockPaper));
                pointPlayer++;
            } else if (pcNumber == 1) {
                /**Player Lose - Scissors cut Paper*/
                displayBanner(getString(R.string.playerLose));
                displayPCFigure(pcScissors);
                displayWhatHappened(getString(R.string.whatHappenedScissorsPaper));
                pointPC++;
            } else if (pcNumber == 2) {
                /**TIE*/
                displayBanner(getString(R.string.draw));
                displayPCFigure(pcPaper);
                displayWhatHappened(getString(R.string.whatHappenedDoubleKill));
            } else if (pcNumber == 3) {
                /**Player Win - Paper covers Rock*/
                displayBanner(getString(R.string.playerWin));
                displayPCFigure(pcRock);
                displayWhatHappened(getString(R.string.whatHappenedPaperRock));
                pointPlayer++;
            }
        } else if (rock == true) {
            if (pcNumber == 4) {
                /**Player Win - Rock crushes Lizard*/
                displayBanner(getString(R.string.playerWin));
                displayPCFigure(pcLizard);
                displayWhatHappened(getString(R.string.whatHappenedLizardRock));
                pointPlayer++;
            } else if (pcNumber == 0) {
                /**Player Lose - Spock vaporizes Rock*/
                displayBanner(getString(R.string.playerLose));
                displayPCFigure(pcSpock);
                displayWhatHappened(getString(R.string.whatHappenedSpockRock));
                pointPC++;
            } else if (pcNumber == 1) {
                /**Player Win - Rock crushes Scissors*/
                displayBanner(getString(R.string.playerWin));
                displayPCFigure(pcScissors);
                displayWhatHappened(getString(R.string.whatHappenedScissorsRock));
                pointPlayer++;
            } else if (pcNumber == 2) {
                /**Player Lose - Paper covers Rock*/
                displayBanner(getString(R.string.playerLose));
                displayPCFigure(pcPaper);
                displayWhatHappened(getString(R.string.whatHappenedPaperRock));
                pointPC++;
            } else if (pcNumber == 3) {
                /**TIE*/
                displayBanner(getString(R.string.draw));
                displayPCFigure(pcRock);
                displayWhatHappened(getString(R.string.whatHappenedDoubleKill));
            }
        }
        lizard = false;
        spock = false;
        scissors = false;
        paper = false;
        rock = false;
        calc();
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.lizard ? (byte) 1 : (byte) 0);
        dest.writeByte(this.spock ? (byte) 1 : (byte) 0);
        dest.writeByte(this.scissors ? (byte) 1 : (byte) 0);
        dest.writeByte(this.paper ? (byte) 1 : (byte) 0);
        dest.writeByte(this.rock ? (byte) 1 : (byte) 0);
        dest.writeString(this.pcSpock);
        dest.writeString(this.pcScissors);
        dest.writeString(this.pcPaper);
        dest.writeString(this.pcRock);
        dest.writeString(this.pcLizard);
        dest.writeInt(this.gameNumber);
        dest.writeInt(this.pointPlayer);
        dest.writeInt(this.pointPC);
        dest.writeInt(this.wonGamePlayer);
    }
    public MainActivity() {
    }
    protected MainActivity(Parcel in) {
        this.lizard = in.readByte() != 0;
        this.spock = in.readByte() != 0;
        this.scissors = in.readByte() != 0;
        this.paper = in.readByte() != 0;
        this.rock = in.readByte() != 0;
        this.pcSpock = in.readString();
        this.pcScissors = in.readString();
        this.pcPaper = in.readString();
        this.pcRock = in.readString();
        this.pcLizard = in.readString();
        this.gameNumber = in.readInt();
        this.pointPlayer = in.readInt();
        this.pointPC = in.readInt();
        this.wonGamePlayer = in.readInt();
    }
    public static final Creator<MainActivity> CREATOR = new Creator<MainActivity>() {
        @Override
        public MainActivity createFromParcel(Parcel source) {
            return new MainActivity(source);
        }

        @Override
        public MainActivity[] newArray(int size) {
            return new MainActivity[size];
        }
    };
}