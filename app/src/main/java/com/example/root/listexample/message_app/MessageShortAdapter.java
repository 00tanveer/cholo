package com.example.root.listexample.message_app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.root.listexample.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 12/28/16.
 */

public class MessageShortAdapter extends RecyclerView.Adapter<MessageShortAdapter.MyViewHolder> {

    private List<MessageShort> messageShortList = new ArrayList<>();
    private Context context;
    private TextView event_name, last_message, time;


    public MessageShortAdapter(List<MessageShort> list, Context context){
        this.messageShortList = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_feed_row, parent, false);
        return new MyViewHolder((view));
    }

    @Override
    public void onBindViewHolder(final MessageShortAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return messageShortList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View view) {
            super(view);

        }
    }


}
