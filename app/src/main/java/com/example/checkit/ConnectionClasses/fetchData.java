package com.example.checkit.ConnectionClasses;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class fetchData extends AsyncTask<Void,Void,Void> {
    String data = " ";
    @Override
    protected Void doInBackground(Void... voids) {
        try {
           // URL url = new URL("http://178.43.70.175:8080/getparticipantbyid?id=5");
           // URL url = new URL("http://178.43.70.175:8080/validateLoginOfParticipant?email=anna.nowak%40o2.pl&password=anna.nowak&fbclid=IwAR31yayC9YSc6a4SiesT-J4CbBiDutb86TiECO1VVlHbs6pImB2XuCxXzCQ");
            // URL url = new URL("http://178.43.70.175:8080/getparticipantbyid?id=5");
            //https://178.43.70.175:8080/GetEventList
            URL url = new URL("http://178.43.70.175:8080/getparticipantlist?fbclid=IwAR2s-5eYQbAXy_RBCYY8Qh5AERMKrmDPtRvkGN_Ube0N6PGqTy_LX4ixsRs    ");

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));

            String line = "";

            while(line != null){

                line = bufferedReader.readLine();
                data = data + line;
            }


            JSONArray jsonArray = new JSONArray(data);
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = (JSONObject)jsonArray.get(i);

            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Log.d("brz", data);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        Log.d("brz",data);




    }
}
