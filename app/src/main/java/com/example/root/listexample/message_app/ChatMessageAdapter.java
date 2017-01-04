package com.example.root.listexample.message_app;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.root.listexample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 1/3/17.
 */

public class ChatMessageAdapter extends RecyclerView.Adapter<ChatMessageViewHolder> {

    private Activity activity;
    List<ChatMessage> messages = new ArrayList<>();

    public ChatMessageAdapter(Activity activity) {
        this.activity = activity;
    }

    public void addMessage(ChatMessage chat) {
        messages.add(chat);
        notifyItemInserted(messages.size());
    }

    @Override
    public ChatMessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ChatMessageViewHolder(activity, activity.getLayoutInflater().inflate(R.layout.chat_message_item_self, parent, false));
    }

    @Override
    public void onBindViewHolder(ChatMessageViewHolder holder, int position) {
        holder.bind(messages.get(position));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}
