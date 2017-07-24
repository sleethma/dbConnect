package com.example.micha.connecttodb;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import android.widget.TextView;

import static java.security.AccessController.getContext;


/**
 * Created by micha on 7/21/2017.
 */

public class BackgroundTask extends AsyncTask<String, Void, String> {

    Context mContextRegister;

    private static String jsonString;

    //creates textview constructor for thread access
    TextView jsonTextView = null;


    //postExecuteResponse: 0= no click/error; 1=registration; 2=get JSON
    int postExecuteResponse = 0;

    BackgroundTask(Context context, TextView textView) {
        this.mContextRegister = context;
        if(textView != null) {
            this.jsonTextView = textView;
        }
    }

    BackgroundTask(Context context) {
        this.mContextRegister = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {

        //stores path of db php registration script on host
        //server_IPv4/db_folder/file
        String reg_url = "http://brightlightproductions.online/add.php";
        String json_url = "http://brightlightproductions.online/getJSON.php";



        //checks which button was clicked
        String chosenBkgdTaskCheck = params[0];
        //assures register button sent this background call
        if (chosenBkgdTaskCheck.equals("register")) {
            //get params
            String name = params[1];
            String password = params[2];
            String contact = params[3];
            String country = params[4];

            //establish connection
            try {
                URL regUrl = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) regUrl.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                //make object of output stream
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));


                String data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&" +
                        URLEncoder.encode("contact", "UTF-8") + "=" + URLEncoder.encode(contact, "UTF-8") + "&" +
                        URLEncoder.encode("country", "UTF-8") + "=" + URLEncoder.encode(country, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();
                postExecuteResponse = 1;
                return "Registration Successful";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (chosenBkgdTaskCheck.equals("getJSON")) {
            try {
                URL jsonURL = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) jsonURL.openConnection();
                httpURLConnection.setRequestMethod("GET");
                //create inputstream
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();

                while ((jsonString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(jsonString + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                postExecuteResponse = 2;
                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (chosenBkgdTaskCheck.equals("parseJSON")){
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        switch (postExecuteResponse){
            case 0:
                break;
            case 1:
                Toast.makeText(mContextRegister, "Registration Success", Toast.LENGTH_LONG).show();
                break;
            case 2:
                if(jsonTextView != null){
                    jsonTextView.setText(result);
                    break;
                }
            case 3:
                Intent intent = new Intent(this.mContextRegister, ParsedJavaView.class);
                intent.putExtra("parseJSONKey",jsonString);
                mContextRegister.startActivity(intent);
                break;
        }
        postExecuteResponse = 0;
    }
    //getter for jsonSting
    public static String getJsonString() {
        return jsonString;
    }
}
