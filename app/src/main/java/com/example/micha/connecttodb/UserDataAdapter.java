package com.example.micha.connecttodb;

import android.content.Context;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by micha on 7/24/2017.
 */

public class UserDataAdapter extends ArrayAdapter {

    List list = new ArrayList();

    public UserDataAdapter(Context context, int resource) {
        super(context, resource);
    }



    public void add(UserDataSetter object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        UserDataHolder userDataHolder;
        if(listItemView == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            listItemView = inflater.inflate(R.layout.json_list_item, parent, false);
            //initialize and set views in UserDataHolder.class
            userDataHolder = new UserDataHolder();
            userDataHolder.name = (TextView) listItemView.findViewById(R.id.tv_name);
            userDataHolder.password = (TextView) listItemView.findViewById(R.id.tv_password);
            userDataHolder.contact = (TextView) listItemView.findViewById(R.id.tv_contact);
            userDataHolder.country = (TextView) listItemView.findViewById(R.id.tv_country);
            //TODO look up what setTag does
            listItemView.setTag(userDataHolder);
        }else{
            userDataHolder = (UserDataHolder) listItemView.getTag();
        }
        UserDataSetter userDataSetter = (UserDataSetter) this.getItem(position);
        userDataHolder.name.setText(userDataSetter.mName); //was supposed to use getter of UserDataSetter class
        userDataHolder.password.setText(userDataSetter.mPassword); //was supposed to use getter of UserDataSetter class
        userDataHolder.contact.setText(userDataSetter.mContact); //was supposed to use getter of UserDataSetter class
        userDataHolder.country.setText(userDataSetter.mCountry); //was supposed to use getter of UserDataSetter class

        return listItemView;
    }

    static class UserDataHolder{
        TextView name, password, contact, country;
    }
}
