package com.example.guessthenumber;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int result;
    int attempts = 0;
    int maxAttempts = 7;

    static int randomNumber(int upperlimit, int lowerlimit)
    {
        return (int)((Math.random() * (upperlimit - lowerlimit)) + lowerlimit);
    }

    public void makeToast(String str)
    {
        Toast toast = Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();

    }
    public void guessNumber(View view)
    {
        int numberGuessing;
        EditText variable = (EditText)findViewById(R.id.editId);
        numberGuessing = Integer.parseInt(variable.getText().toString());
        int a = ++attempts;
        int left = maxAttempts - a;
        if (a >= 7 && result != numberGuessing) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Answer was "+result+" Do you want to play again ?");
            builder.setTitle("Better Luck Next Time..!");
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    finish();
                    Intent send = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(send);
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    dialog.cancel();
                    finish();
                }
            });

            // Create the Alert dialog
            AlertDialog alertDialog = builder.create();

            // Show the Alert Dialog box
            alertDialog.show();
        }
        else if (numberGuessing > result) {
            makeToast("Too High! Attempt left "+left);
        } else if (numberGuessing < result){
            makeToast("Too Low! Attempt left "+left);
        }
        else {
            makeToast("Congratulations..!");
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Do you want to play again ?");
            builder.setTitle("Congratulations..!");
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    finish();
                                    Intent send = new Intent(MainActivity.this, MainActivity.class);
                                    startActivity(send);
                                }
                            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    dialog.cancel();
                                    finish();
                                }
                            });

            // Create the Alert dialog
            AlertDialog alertDialog = builder.create();

            // Show the Alert Dialog box
            alertDialog.show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int min = 1;
        int max = 100;
        result = randomNumber(min, max);
    }
}
