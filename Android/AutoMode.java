package com.example.pang.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.view.View;
import android.widget.TextView;
import android.view.ViewGroup;
import android.widget.Toast;
import android.graphics.Color;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

public class AutoMode extends AppCompatActivity   {
    Button bSwitchA,bDisconnectA;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_auto_mode);
            // Get reference of widgets from XML layout

            bSwitchA = (Button) findViewById(R.id.switchButtonA);
            bSwitchA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(AutoMode.this, ManualMode.class);
                    startActivity(intent);
                }
            });

            bDisconnectA = (Button) findViewById(R.id.disconnectButtonA);
            bDisconnectA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(AutoMode.this, MainActivity.class);
                    startActivity(intent);
                }
            });

            final Spinner spinner = (Spinner) findViewById(R.id.spinner);

            // Initializing a String Array
            String[] Destination = new String[]{
                    "Select Destination",
                    "A",
                    "B",
                    "C",
                    "D",
                    "Home"
            };

            final List<String> destinationList = new ArrayList<>(Arrays.asList(Destination));

            // Initializing an ArrayAdapter
            final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,R.layout.spinner_item,destinationList){
                @Override
                public boolean isEnabled(int position){
                   if(position == 0)
                    {
                        // Disable the first item from Spinner
                        // First item will be use for hint
                        return false;
                    }
                    else
                    {
                        return true;
                    }
                }
                @Override
                public View getDropDownView(int position, View convertView, ViewGroup parent) {
                    View view = super.getDropDownView(position, convertView, parent);
                    TextView tv = (TextView) view;
                    if(position == 0){
                        // Set the hint text color gray
                        tv.setTextColor(Color.GRAY);
                    }
                    else {
                        tv.setTextColor(Color.BLACK);
                    }
                    return view;
                }
            };
            spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
            spinner.setAdapter(spinnerArrayAdapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String selectedItemText = (String) parent.getItemAtPosition(position);
                    // If user change the default selection
                    // First item is disable and it is used for hint
                    if(position > 0){
                        AlertDialog alertDialog = new AlertDialog.Builder(AutoMode.this).create();
                        alertDialog.setTitle("Confirmation");
                        alertDialog.setMessage("Here is android alert dialog message " +selectedItemText);
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        //Toast.makeText(getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();// use dismiss to cancel alert dialog
                                    }
                                });
                        alertDialog.show();

                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

    public void showDialog(View view){
        AlertDialog alertDialog = new AlertDialog.Builder(AutoMode.this).create();
        alertDialog.setTitle("Confirmation");
        alertDialog.setMessage("Here is android alert dialog message");
        // Alert dialog button
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();// use dismiss to cancel alert dialog
                    }
                });
        alertDialog.show();
    }
}





//pppp
