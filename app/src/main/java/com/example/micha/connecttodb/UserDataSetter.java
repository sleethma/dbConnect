package com.example.micha.connecttodb;

/**
 * Created by micha on 7/24/2017.
 */

public class UserDataSetter {
    String  mName, mPassword, mContact, mCountry;

    public UserDataSetter(String name, String password, String contact, String country){
        setmName(name);
        setmPassword(password);
        setmContact(contact);
        setmCountry(country);
    }

    private String getmName() {
        return mName;
    }

    private void setmName(String mName) {
        this.mName = mName;
    }

    private String getmPassword() {
        return mPassword;
    }

    private void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    private String getmContact() {
        return mContact;
    }

    private void setmContact(String mContact) {
        this.mContact = mContact;
    }

    private String getmCountry() {
        return mCountry;
    }

    private void setmCountry(String mCountry) {
        this.mCountry = mCountry;
    }
}
