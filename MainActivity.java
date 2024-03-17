package com.example.winneractivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private int teamAScore = 0;
    private int teamBScore = 0;

    private ImageButton teamAButton;
    private ImageButton teamBButton;
    private ImageButton clearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teamAButton = findViewById(R.id.teamAButton);
        teamBButton = findViewById(R.id.teamBButton);
        clearButton = findViewById(R.id.clearButton);

        teamAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamAScore++;
                updateScores();
                checkWinner();
            }
        });

        teamBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamBScore++;
                updateScores();
                checkWinner();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearScores();
            }
        });
    }

    private void updateScores() {
        TextView scoreATextView = findViewById(R.id.score_teamA);
        TextView scoreBTextView = findViewById(R.id.score_teamB);

        scoreATextView.setText(String.valueOf(teamAScore));
        scoreBTextView.setText(String.valueOf(teamBScore));
    }

    private void checkWinner() {
        if (teamAScore >= 5 || teamBScore >= 5) {
            String winningTeam;
            int winningMargin;

            if (teamAScore > teamBScore) {
                winningTeam = "Team A";
                winningMargin = teamAScore - teamBScore;
            } else {
                winningTeam = "Team B";
                winningMargin = teamBScore - teamAScore;
            }

            // Start the WinnerActivity and pass the winning team's name and margin
            Intent intent = new Intent(this, WinnerActivity.class);
            intent.putExtra("winningTeam", winningTeam);
            intent.putExtra("winningMargin",""+ winningMargin);
            startActivity(intent);
        }
    }

    private void clearScores() {
        teamAScore = 0;
        teamBScore = 0;
        updateScores();
    }
}


