package edu.northeastern.numad22fa_team_22;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;

public class AboutTeamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_team);
        TextView team_name_textView = (TextView) findViewById(R.id.team_name_textView);
        TextView team_member_1_textView = (TextView) findViewById(R.id.team_member_1_textView);
        TextView team_member_2_textView = (TextView) findViewById(R.id.team_member_2_textView);
        TextView team_member_3_textView = (TextView) findViewById(R.id.team_member_3_textView);

        // display information
        team_name_textView.setText(R.string.team_name);
        team_member_1_textView.setText(String.format("%s : %s", getString(R.string.member_1_full_name), getString(R.string.member_1_email)));
        team_member_2_textView.setText(String.format("%s : %s", getString(R.string.member_2_full_name), getString(R.string.member_2_email)));
        team_member_3_textView.setText(String.format("%s : %s", getString(R.string.member_3_full_name), getString(R.string.member_3_email)));
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing Activity")
                .setMessage("Are you sure you want to close the AboutTeam activity?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}
