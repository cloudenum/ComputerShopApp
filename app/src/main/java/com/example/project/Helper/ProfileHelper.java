package com.example.project.Helper;

import android.content.Context;

public class ProfileHelper {
    private Context context;
    private TinyDB tinyDB;

    public ProfileHelper(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public String getAddress() {
        Testing();
        String Address = "";

        if(!tinyDB.getString("address").isEmpty()){
            Address = tinyDB.getString("address");
        }

        return Address;
    }

    private void Testing(){
        tinyDB.putString("address", "Jakarta Selatan");
    }
}
