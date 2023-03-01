package com.example.robinjava;

import static android.os.StrictMode.*;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;

public class ConnectionHelper {
    Connection con;
    String uname, pass, ip, port, database;

    public Connection connectionclass()
    {
        ip = "172.1.1.0";
        database = "App2";


        //ThreadPolicy policy = new ThreadPolicy().Builder().permitAll().build();
        //StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionUrl = null;

        try
        {

        }
        catch (Exception ex) {
            Log.e("Error ", ex.getMessage());
        }

        return connection;
    }
}
