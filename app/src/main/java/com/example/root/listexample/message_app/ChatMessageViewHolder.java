package com.example.root.listexample.message_app;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.root.listexample.R;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by root on 1/3/17.
 */

public class ChatMessageViewHolder extends RecyclerView.ViewHolder {

    private final Activity activity;
    private TextView message;
    private TextView time;

    public ChatMessageViewHolder(Activity activity, View itemView) {
        super(itemView);
        this.activity = activity;
        message = (TextView) itemView.findViewById(R.id.message);
        time = (TextView) itemView.findViewById(R.id.message_post_time);

    }

    public void bind(ChatMessage chat) {
        message.setText(chat.getMessage());
        Timestamp haha = Timestamp.valueOf(chat.getTimestamp());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        time.setText(simpleDateFormat.format(haha));

    }
}
