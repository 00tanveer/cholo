package com.example.root.listexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by root on 12/14/16.
 */

public class EventsFeed extends Fragment{

    private List<MyEvents> eventsList = new ArrayList<MyEvents>();
    private RecyclerView rv;
    private EventAdapter eventAdapter;
    private static final String url = "http://192.168.1.6/events.php";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rv = (RecyclerView) inflater.inflate(
                R.layout.fragment_event_list, container, false);
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));

        updateList(url);
        return rv;
    }

    public void updateList(String url){
        eventAdapter = new EventAdapter(eventsList, getActivity());
        rv.setAdapter(eventAdapter);

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        eventAdapter.clearAdapter();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("THIS IS JSON RESPONSE: :" + response.toString());
                try {
                    JSONArray events = response.getJSONArray("events");

                    for(int i=0; i<events.length(); i++){
                        JSONObject item = events.getJSONObject(i);
                        MyEvents event = new MyEvents();
                        event.setEventName(item.getString("name"));
                        event.setEventLocation(item.getString("location"));
                        event.setImageURL(item.getString("image"));
                        event.setEventStartDate(item.getString("date"));
                        event.setStartTime(item.getString("start_time"));
                        event.setEndTime(item.getString("end_time"));
                        event.setGoing(item.getString("going"));
                        event.setInterested(item.getString("interested"));

                        eventsList.add(event);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                eventAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error){
                    VolleyLog.d("response", "Error" + error.getMessage());
                }
        });
        queue.add(jsonObjectRequest);
    }

}
