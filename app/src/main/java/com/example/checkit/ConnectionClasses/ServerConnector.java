package com.example.checkit.ConnectionClasses;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.checkit.Data.User;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
//TODO 5/9/2019 napisać klasę do połączenia z serverem
//192.168.43.57:44384 port

// for emulator 10.0.2.2:44384    / :3000
//http://102.168.43.57:44384
public class ServerConnector extends Application {

    private final String serviceGetTrainURL = "http://178.43.70.175:8080/getparticipantbyid?id=5";
    private Context mContext;

    // TODO string builder
    public ServerConnector(Context context) {

        this.mContext = context;
    }

    public void sendRequestAndGetResponse(){
        String response = null;

        try {
            URL url = new URL(serviceGetTrainURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);

        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d("jsonResponse",response);

        User user = new Gson().fromJson(response, User.class);
        Log.d("TestJsonResponse",""+user.getName()+" "+user.getName()+" "+ user.getMail());


    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }

}
