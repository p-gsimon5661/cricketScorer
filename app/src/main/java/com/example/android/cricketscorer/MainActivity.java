package com.example.android.cricketscorer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
          Switch  zwitch;
          boolean whichTeamBatting = false;
          int     teamBScore       = 0;
          int     teamAScore       = 0;
          int     teamBWicket      = 0;
          int     teamAWicket      = 0;

    //Constants for the scores
    final int     SIX              = 6;
    final int     FOUR             = 4;
    final int     THREE            = 3;
    final int     DOUBLE           = 2;
    final int     SINGLE           = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        zwitch = findViewById(R.id.on_off_switch);
    }

    public void ChangeTeamBatting(View view)
    {
        zwitch = findViewById(R.id.on_off_switch);
        whichTeamBatting = zwitch.isChecked();
        if(teamBScore > 0 && teamAScore > 0)
            gameOver(findViewById(R.id.battingTeam));

    }

    //six hit add 6 to score
    public void sixHit(View view)
    {
        if(whichTeamBatting)
        {
            teamBScore += SIX;
            displayScore(teamBScore);
        }
        else
        {
            teamAScore += SIX;
            displayScore(teamAScore);
        }
        CheckForWinner(view);
    }

    //four hit add 4 to score
    public void fourHit(View view)
    {
        if(whichTeamBatting)
        {
            teamBScore += FOUR;
            displayScore(teamBScore);
        }
        else
        {
            teamAScore += FOUR;
            displayScore(teamAScore);
        }
        CheckForWinner(view);
    }

    //3 singles hit add 3 to score
    public void threeSingles(View view)
    {
        if(whichTeamBatting)
        {
            teamBScore += THREE;
            displayScore(teamBScore);
        }
        else
        {
            teamAScore += THREE;
            displayScore(teamAScore);
        }
        CheckForWinner(view);
    }

    //2 singles hit add 3 to score
    public void twoSingles(View view)
    {
        if(whichTeamBatting)
        {
            teamBScore += DOUBLE;
            displayScore(teamBScore);
        }
        else
        {
            teamAScore += DOUBLE;
            displayScore(teamAScore);
        }
        CheckForWinner(view);
    }

    //single hit add 1 to score
    public void addARun(View view)
    {
        if(whichTeamBatting)
        {
            teamBScore += SINGLE;
            displayScore(teamBScore);
        }
        else
        {
            teamAScore += SINGLE;
            displayScore(teamAScore);
        }
        CheckForWinner(view);
    }

    //wicket fall
    public void wicket(View view)
    {
        if(whichTeamBatting)
        {
            teamBWicket += 1;

            if(teamBWicket < 10)
                displayWickets(teamBWicket);
            if(teamBWicket >= 10)
            {
                teamBWicket = 10;
                displayWickets(teamBWicket);
                zwitch.setChecked(false);
                whichTeamBatting = false;
                ChangeTeamBatting(view);
            }
        }
        else
        {
            teamAWicket += 1;

            if(teamAWicket < 10)
                displayWickets(teamAWicket);
            if(teamAWicket >= 10)
            {
                teamAWicket = 10;
                displayWickets(teamAWicket);
                zwitch.setChecked(true);
                whichTeamBatting = true;
                ChangeTeamBatting(view);
            }
        }
    }

    //reset all back to starting
    public void resetAll(View view)
    {
        teamBScore       = 0;
        teamAScore       = 0;
        teamBWicket      = 0;
        teamAWicket      = 0;

        TextView scoreBView = findViewById(R.id.team_b_score);
        scoreBView.setText(String.valueOf(teamBScore));

        TextView scoreAView = findViewById(R.id.team_a_score);
        scoreAView.setText(String.valueOf(teamAScore));

        TextView wicketBView = findViewById(R.id.team_b_wicket);
        wicketBView.setText(String.valueOf(teamBWicket));

        TextView wicketAView = findViewById(R.id.team_a_wicket);
        wicketAView.setText(String.valueOf(teamAWicket));

        zwitch.setChecked(false);
        whichTeamBatting = false;
        TextView resetText = findViewById(R.id.battingTeam);
        resetText.setText(String.valueOf("Which team is batting:"));

        //enable buttons
        findViewById(R.id.six_button).setEnabled(true);
        findViewById(R.id.four_button).setEnabled(true);
        findViewById(R.id.three_button).setEnabled(true);
        findViewById(R.id.two_button).setEnabled(true);
        findViewById(R.id.one_button).setEnabled(true);
        findViewById(R.id.extra_button).setEnabled(true);
        findViewById(R.id.gameOver_button).setEnabled(true);
        findViewById(R.id.wicket_button).setEnabled(true);
    }

    //gameOver
    public void gameOver(View view)
    {
        TextView winner = findViewById(R.id.battingTeam);

        if(teamBScore > teamAScore)
            winner.setText(String.valueOf("Team B wins!"));
        else if(teamBScore < teamAScore)
            winner.setText(String.valueOf("Team A wins!"));
        else
            winner.setText(String.valueOf("Game Drawn"));

        //disables buttons
        findViewById(R.id.six_button).setEnabled(false);
        findViewById(R.id.four_button).setEnabled(false);
        findViewById(R.id.three_button).setEnabled(false);
        findViewById(R.id.two_button).setEnabled(false);
        findViewById(R.id.one_button).setEnabled(false);
        findViewById(R.id.extra_button).setEnabled(false);
        findViewById(R.id.gameOver_button).setEnabled(false);
        findViewById(R.id.wicket_button).setEnabled(false);
    }

    //display team score
    public void displayScore(int score)
    {
        if(whichTeamBatting)
        {
            TextView scoreBView = findViewById(R.id.team_b_score);
            scoreBView.setText(String.valueOf(score));

        }
        else
        {
            TextView scoreAView = findViewById(R.id.team_a_score);
            scoreAView.setText(String.valueOf(score));
        }
    }

    //update team wickets
    public void displayWickets(int wicket)
    {
        if(whichTeamBatting)
        {
            TextView wicketBView = findViewById(R.id.team_b_wicket);
            wicketBView.setText(String.valueOf(wicket));
        }
        else
        {
            TextView wicketAView = findViewById(R.id.team_a_wicket);
            wicketAView.setText(String.valueOf(wicket));
        }
    }

    //single hit add 1 to score
    public void CheckForWinner(View view)
    {
        if(whichTeamBatting)
        {
            if(teamAScore > 0 && teamBScore > teamAScore)
            gameOver(view);
        }
        else
        {
           if(teamBScore > 0 && teamAScore > teamBScore )
               gameOver(view);
        }
    }
}
