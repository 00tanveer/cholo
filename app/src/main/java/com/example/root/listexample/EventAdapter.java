package com.example.root.listexample;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.root.listexample.util.CustomVolleyRequest;
import com.example.root.listexample.util.EventListTest;

import java.util.List;

/**
 * Created by root on 12/8/16.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {

    private List<MyEvents> eventsList;
    private Context context;
    private ImageLoader imageLoader;

    public EventAdapter(List<MyEvents> eventsList, Context context) {
        this.context = context;
        this.eventsList = eventsList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_item, parent, false);
        return new MyViewHolder((view));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        //System.out.println(EventListTest.urls.get(position));
        MyEvents event = eventsList.get(position);
        /*System.out.println(event.getImageURl());
        System.out.println(context);
        System.out.println(event.getEventName());
        System.out.println(event.getEventLocation());
        System.out.println(event.getEventStartDate());
        System.out.println(event.getStartTime());
        System.out.println(event.getEndTime());
        System.out.println(event.getGoing());
        System.out.println(event.getInterested());*/


        imageLoader = MySingleton.getInstance(context).getImageLoader();
        holder.eventThumbnail.setImageUrl(event.getImageURl(), imageLoader);
        holder.eventName.setText(event.getEventName());
        holder.eventLocation.setText(event.getEventLocation());
        holder.eventDate.setText(event.getEventStartDate());
        holder.eventTime.setText(event.getStartTime() + " to " + event.getEndTime());
        holder.going.setText(event.getGoing());
        holder.interested.setText(event.getInterested());
    }

    @Override
    public int getItemCount() {
        return (null != eventsList ? eventsList.size() : 0);
    }

    public void clearAdapter(){
        eventsList.clear();
        notifyDataSetChanged();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        protected NetworkImageView eventThumbnail;
        protected TextView eventName, eventLocation, eventDate, eventTime, going, interested;

        public MyViewHolder(View view) {
            super(view);

            eventThumbnail = (NetworkImageView) view.findViewById(R.id.imageView2);

            eventThumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //load event activity
                    System.out.println("pressed");
                    Intent eventIntent = new Intent(v.getContext(), EventActivity.class);
                    v.getContext().startActivity(eventIntent);
                }
            });

            eventName = (TextView) view.findViewById(R.id.eventName);
            eventLocation = (TextView) view.findViewById(R.id.eventLocation);
            eventDate = (TextView) view.findViewById(R.id.eventDate);
            eventTime = (TextView) view.findViewById(R.id.eventTime);
            going = (TextView) view.findViewById(R.id.going);
            interested = (TextView) view.findViewById(R.id.interested);
        }
    }
}




