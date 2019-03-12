package com.example.a13146598.sensordb;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import com.google.gson.*;



public class MainActivity extends AppCompatActivity {


    public static String sensorServerURL = "http://10.0.2.2:8080/MobileAssignment-Server/sensorToDB";
    public TextView sensorValue;
    public TextView outName;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


     /*   final TextView outputName = (TextView) findViewById(R.id.outputName);
        String outName = getFromServer("sensorname");
      outputName.setText(outName);
*/

        final Button refresh = (Button) findViewById(R.id.refresh_id);
        refresh.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String result = getFromServer("Slider");
                Gson g = new Gson();
                 sensorData sdsd = g.fromJson(result, sensorData.class);
                TextView outputValue =(TextView)findViewById(R.id.outputValue);
                outputValue.setText(sdsd.getValue());
            }

        });


        final Button sendData = (Button) findViewById(R.id.sendData);
        sendData.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                EditText valueInput = (EditText)findViewById(R.id.valueInput);
                String sval = valueInput.getText().toString();

                EditText sensorname = (EditText)findViewById(R.id.sensorname);
                String sname = sensorname.getText().toString();

                sendToServer(sname,sval);
            }

        });










    }

    public String sendToServer(String sensorName, String sensorValue){
        URL url;
        HttpURLConnection conn;
        BufferedReader rd;
        String fullURL = sensorServerURL + "?sensorname="+sensorName+"&sensorvalue="+sensorValue;
        System.out.println("Sending data to: "+fullURL);
        String line;
        String result = "";
        try {
            url = new URL(fullURL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = rd.readLine()) != null) {
                result += line;
            }
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }


    public String getFromServer(String sensorName) {

        URL url;
        HttpURLConnection conn;
        BufferedReader rd;
        String fullURL = sensorServerURL + "?getdata=true&sensorname="+sensorName;
        System.out.println("Sending data to: " + fullURL);
        String line;
        String result = "";
        try {
            url = new URL(fullURL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = rd.readLine()) != null) {
                result += line;
            }
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;


    }
}