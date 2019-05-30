package com.example.checkit.ConnectionClasses;

import com.example.checkit.Data.EventModel;
import com.example.checkit.Data.LeaderModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

//TODO 5/9/2019 skożystać z klasy ServerConnector,
// napisać operacje pobierania z servera oraz żądań
public class ServerGetOperations {


    private static final String TAG_ID = "id";
    private static final String TAG_EVENT_NAME = "name";
    private static final String TAG_LEADER = "leaderId";
    private static final String TAG_FIRSTNAME = "firstName";
    private static final String TAG_LASTNAME = "lastName";
    private static final String TAG_WORKERID = "workerId";
    private static final String TAG_EMAIL = "email";

    JSONParser jsonParser = new JSONParser();

    public ArrayList<EventModel> getEventList() {
        final String url = "https://178.43.70.175:8080/GetEventList";

        JSONObject json = jsonParser.getJSONFromUrl(url);
        JSONArray eventsArray;
        ArrayList<EventModel> eventList = new ArrayList<>();

        {
            {
                try {
                    eventsArray = new JSONArray(json);

                    for (int i = 0; i < eventsArray.length(); i++) {

                        JSONObject event = eventsArray.getJSONObject(i);

                        String id = event.getString(TAG_ID);
                        String name = event.getString(TAG_EVENT_NAME);
                        String leaderId = event.getString(TAG_LEADER);

                        LeaderModel leaderModel = getLeaderModel(leaderId);

                        EventModel model = new EventModel(id, name, leaderModel);
                        eventList.add(model);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
        return eventList;
    }

    public LeaderModel getLeaderModel(String leaderId) {
        String url = "https://178.43.70.175:8080/GetLeaderById?=x";
        url.replace("x",leaderId);
        LeaderModel leader = null;
        JSONObject json = jsonParser.getJSONFromUrl(url);

                try {
                    JSONObject jsonObject = json.getJSONObject(json.toString());
                    String id = jsonObject.getString(TAG_ID);
                    String firstName = jsonObject.getString(TAG_FIRSTNAME);
                    String lastName = jsonObject.getString(TAG_LASTNAME);
                    String workerId = jsonObject.getString(TAG_WORKERID);
                    String email = jsonObject.getString(TAG_EMAIL);

                    leader = new LeaderModel(id, firstName, lastName, workerId, email);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

        return leader;
        }

    public boolean isEmailAvailable(String email) {
        String url = "https://178.43.70.175:8080/CheckIfParticipantEmailAvailable?email=x";
        url.replace("x",email);
        JSONObject json = jsonParser.getJSONFromUrl(url);
        boolean isAvailable = false;

        try {
            JSONObject jsonObject = json.getJSONObject(json.toString());
            if (jsonObject.toString() == "true"){
                isAvailable = true;}

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return isAvailable;
    }

    public boolean validateLogin(String email, String password) {
        String url = "https://178.43.70.175:8080/http://178.43.70.175:8080/" +
                "validateLoginOfParticipant?email=@EMAIL&password=PASSWORD";

        url.replace("EMAIL",email);
        url.replace("PASSWORD",password);
        JSONObject json = jsonParser.getJSONFromUrl(url);
        boolean isValidate = false;

        try {
            JSONObject jsonObject = json.getJSONObject(json.toString());
            if (jsonObject.toString() == "true"){
                isValidate = true;}

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return isValidate;
    }

    }



