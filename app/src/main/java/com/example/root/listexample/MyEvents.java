package com.example.root.listexample;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by root on 12/14/16.
 */

public class MyEvents {
    private ImageView imageThumbnail;
    private String eventName, imageURL, eventLocation, eventStartDate, eventEndDate, startTime, endTime, going,
    interested;

    public MyEvents(String eventName, String imageURL, String eventLocation,
                    String eventStartDate, String startTime, String eventEndDate, String endTime,
                    String going, String interested) {
        this.eventName = eventName;
        this.imageURL = imageURL;
        this.eventLocation = eventLocation;
        this.eventStartDate = eventStartDate;
        this.startTime = startTime;
        this.eventEndDate = eventEndDate;
        this.endTime = endTime;
        this.going = going;
        this.interested = interested;
    }
    public MyEvents(){}

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getImageURl() { return imageURL; }

    public void setImageURL(String imageURL) { this.imageURL = imageURL; }

    public String getEventStartDate() {
        return eventStartDate;
    }

    public void setEventStartDate(String eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    public String getEventEndDate() {
        return eventEndDate;
    }

    public void setEventEndDate(String eventEndDate) {
        this.eventEndDate = eventEndDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getGoing() { return going; }

    public void setGoing(String going) { this.going = going; }

    public String getInterested() { return interested; }

    public void setInterested(String interested) { this.interested = interested; }
}
