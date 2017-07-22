package com.example.micha.connecttodb;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by micha on 7/21/2017.
 */

public class BackgroundTask extends AsyncTask<String, Void, String> {

    Context mContextRegister;
    BackgroundTask(Context context){
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

        //assures register button sent this background call
        String registerBkgdTaskCheck = params[0];
        if(registerBkgdTaskCheck.equals("register")){
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
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));


                String data = URLEncoder.encode("name","UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                        URLEncoder.encode("password","UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&" +
                        URLEncoder.encode("contact","UTF-8") + "=" + URLEncoder.encode(contact, "UTF-8") + "&" +
                        URLEncoder.encode("country","UTF-8") + "=" + URLEncoder.encode(country, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();

                return "Registration Successful";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Toast.makeText(mContextRegister, "Registration Success", Toast.LENGTH_LONG).show();
    }


}
