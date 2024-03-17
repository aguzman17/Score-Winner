package com.example.winneractivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WinnerActivity extends AppCompatActivity {
    private TextView winningTeamTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("WinnerActivity", "inside of create");
        setContentView(R.layout.activity_winner);

        winningTeamTextView = findViewById(R.id.winningTeamTextView);

        Intent intent = getIntent();
        if (intent != null) {
            String winningTeam = intent.getStringExtra("winningTeam");
            String winningMargin = intent.getStringExtra("winningMargin");

            String winningText = "Winner: " + winningTeam + "\nWon by " + winningMargin + " point(s)";
            winningTeamTextView.setText(winningText);
        }
    }

    public void shareResult(View view) {
        Intent intent = getIntent();
        String winningTeam = intent.getStringExtra("winningTeam");
        int winningMargin = intent.getIntExtra("winningMargin", 0);

        String message = "The champion is " + winningTeam + " with a winning margin of " + winningMargin + " points.";

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(shareIntent, "Share via"));
    }

    public void callFriend(View view) {
        String phoneNumber = "1234567890"; // Replace with the actual phone number
        Uri phoneUri = Uri.parse("tel:" + phoneNumber);
        Intent callIntent = new Intent(Intent.ACTION_DIAL, phoneUri);
        startActivity(callIntent);
    }

    public void sendMessage(View view) {
        Intent intent = getIntent();
        String winningTeam = intent.getStringExtra("winningTeam");
        int winningMargin = intent.getIntExtra("winningMargin", 0);

        String message = "Congratulations to " + winningTeam + " for winning by " + winningMargin + " points!";

        Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
        smsIntent.setData(Uri.parse("smsto:" + "1234567890"));  // This ensures only SMS apps respond
        smsIntent.putExtra("sms_body", message);
        startActivity(smsIntent);

    }

    public void searchArena(View view) {
        String sport = "basketball";
        String searchQuery = sport + " near me";

        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(searchQuery));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}