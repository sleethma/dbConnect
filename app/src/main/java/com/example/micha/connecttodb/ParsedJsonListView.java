package com.example.micha.connecttodb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by micha on 7/24/2017.
 */

public class ParsedJsonListView extends AppCompatActivity {
    String jsonString;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ListView userDataListView;
    UserDataAdapter userDataAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parsed_json_view);
        jsonString = getIntent().getExtras().getString("jsonToParseKey");
        //set adapter on listview
        userDataListView = (ListView) findViewById(R.id.lv_parseJSON);
        userDataAdapter = new UserDataAdapter(this, R.layout.json_list_item);
        userDataListView.setAdapter(userDataAdapter);

        String name, password, contact, country;
        try {
            jsonObject = new JSONObject(jsonString);
            jsonArray = jsonObject.getJSONArray("server_response");

            //loop through array and extract objects, adding them individually as setter objects,
            //and adding objects to list adapter.
            int count = 0;
            while(count < jsonArray.length()){
                JSONObject jo = jsonArray.getJSONObject(count);
                name = jo.getString("name");
                password = jo.getString("password");
                contact = jo.getString("contact");
                country = jo.getString("country");

                //make user data object of UserDataSetter class
                UserDataSetter userData = new UserDataSetter(name, password, contact, country);
                userDataAdapter.add(userData);

                count++;

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
