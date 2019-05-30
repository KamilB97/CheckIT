package com.example.checkit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.checkit.ConnectionClasses.ServerGetOperations;
import com.example.checkit.Data.EventModel;
import com.example.checkit.Data.LeaderModel;

import java.util.ArrayList;

class EventListAdapter extends ArrayAdapter<EventModel> {

    private Context mContext;
    int mResource;
    ServerGetOperations serverOperations = new ServerGetOperations();

    public EventListAdapter(Context context, int resource, ArrayList<EventModel> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        String name = getItem(position).getName();
        String leaderId = getItem(position).getLeaderModel().getId();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent,false);

        StringBuilder sb = new StringBuilder();
        sb.append(getItem(position).getLeaderModel().getFirstName()+" ");
        sb.append(getItem(position).getLeaderModel().getLastName());

        TextView tvLeader = (TextView) convertView.findViewById(R.id.leader);
        TextView tvEvent = (TextView) convertView.findViewById(R.id.eventName);
        tvLeader.setText(leaderId);
        tvEvent.setText(sb.toString());

        return convertView;
    }
}
