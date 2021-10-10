package com.example.project.Domain;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.project.Helper.TinyDB;

public class ProfileDomain {
    private static ProfileDomain instance;

    private MutableLiveData<String> name;
    private MutableLiveData<String> address;
    private MutableLiveData<String> telp;
    private MutableLiveData<String> email;
    private MutableLiveData<String> imageURI;

    private ProfileDomain(Context context) {
        loadData(context);
    }

    public static ProfileDomain getInstance(Context context) {
        if (instance == null) {
            instance = new ProfileDomain(context);
        }

        return instance;
    }

    public void loadData(Context context) {
        TinyDB tinyDB = new TinyDB(context);
        getName().setValue(tinyDB.getString("name"));
        getAddress().setValue(tinyDB.getString("address"));
        getEmail().setValue(tinyDB.getString("email"));
        getTelp().setValue(tinyDB.getString("telp"));
        getImageURI().setValue(tinyDB.getString("profile_uri"));
    }

    public void save(Context context) {
        TinyDB tinyDB = new TinyDB(context);

        tinyDB.putString("name", getName().getValue());
        tinyDB.putString("address", getAddress().getValue());
        tinyDB.putString("email", getEmail().getValue());
        tinyDB.putString("telp", getTelp().getValue());
        tinyDB.putString("profile_uri", getImageURI().getValue());
    }

    public MutableLiveData<String> getName() {
        if (name == null) {
            name = new MutableLiveData<>();
        }

        return name;
    }

    public MutableLiveData<String> getAddress() {
        if (address == null) {
            address = new MutableLiveData<>();
        }

        return address;
    }

    public MutableLiveData<String> getEmail() {
        if (email == null) {
            email = new MutableLiveData<>();
        }

        return email;
    }

    public MutableLiveData<String> getTelp() {
        if (telp == null) {
            telp = new MutableLiveData<>();
        }

        return telp;
    }

    public MutableLiveData<String> getImageURI() {
        if (imageURI == null) {
            imageURI = new MutableLiveData<>();
        }

        return imageURI;
    }
}