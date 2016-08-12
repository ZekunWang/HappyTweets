package com.zekunwang.happytweets.adapters;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zekunwang.happytweets.R;
import com.zekunwang.happytweets.activities.ProfileActivity;
import com.zekunwang.happytweets.activities.TimelineActivity;
import com.zekunwang.happytweets.databinding.ItemMediumBinding;
import com.zekunwang.happytweets.databinding.ItemMessageBinding;
import com.zekunwang.happytweets.fragments.ComposeDialogFragment;
import com.zekunwang.happytweets.models.Medium;
import com.zekunwang.happytweets.models.Message;
import com.zekunwang.happytweets.models.Tweet;
import com.zekunwang.happytweets.models.User;
import com.zekunwang.happytweets.models.ViewHolder;
import com.zekunwang.happytweets.models.ViewHolderMedium;
import com.zekunwang.happytweets.models.ViewHolderMessage;
import com.zekunwang.happytweets.others.HelperMethods;

import org.parceler.Parcels;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MessageContactsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // Store a member variable for the contacts
    private List<Message> contacts;

    // Store the context for easy access
    private Context context;
    private User account;

    // Define listener member variable
    private static OnItemClickListener listener;

    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public static OnItemClickListener getListener() {
        return listener;
    }

    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    // Pass in the contact array into the constructor
    public MessageContactsAdapter(Context context, List<Message> contacts, User account) {
        this.context = context;
        this.contacts = contacts;
        this.account = account;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return context;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolderMessage viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemMessageBinding binding = DataBindingUtil
            .inflate(inflater, R.layout.item_message, parent, false);
        // distinguish view by viewType
        //View contactView = inflater.inflate(R.layout.item_tweet, parent, false);
        viewHolder = new ViewHolderMessage(binding);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        // get data item for position
        final Message message = contacts.get(position);
        // Get binding
        final ItemMessageBinding binding = ((ViewHolderMessage) viewHolder).getBinding();

        // Reset views
        binding.ivProfile.setImageResource(0);

        // Get user other than authenticating user
        final User other = message.getSender().getUid() != account.getUid() ? message.getSender() : message.getRecipient();

        // Add Tweet to the binding and
        binding.setMessage(message);
        binding.setUser(other);

        binding.ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProfile(position, message);
            }
        });

        binding.executePendingBindings();
    }

    public void showProfile(int position, Message message) {
        // Go to profile activity
        Intent intent = new Intent(context, ProfileActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("message", Parcels.wrap(message));
        ((Activity) getContext()).startActivityForResult(intent, TimelineActivity.REQUEST_PROFILE);
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void clear() {
        // Clear Tweets list
        contacts.clear();
        // Notify adapter
        notifyDataSetChanged();
    }

    public void addAll(List<Message> list) {
        if (list != null) {
            if (contacts.size() == 0) {
                contacts.addAll(list);
            } else {
                int left = 0;
                for (Message message : list) {
                    left = binarySearch(left, message);
                    // Add to Tweets list
                    contacts.add(left++, message);
                }
            }
            notifyDataSetChanged();
        }
    }

    // Find the first message later than newMessage
    private int binarySearch(int left, Message newMessage) {
        int right = contacts.size(), mid = 0;
        while (left < right) {
            mid = left + (right - left) / 2;
            Message temp = contacts.get(mid);
            if (temp.getMid().compareTo(newMessage.getMid()) >= 0) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }

    public void add(Message newMessage) {
        // Add to Tweets list
        contacts.add(newMessage);
        // Notify adapter
        notifyItemChanged(contacts.size() - 1);
    }

    public void add(int pos, Message newMessage) {
        if (newMessage != null && pos >= 0 && pos <= contacts.size()) {
            // Add to Tweets list
            contacts.add(pos, newMessage);
            // Notify adapter
            notifyDataSetChanged();
        }
    }

    public Message getMessage(int position) {
        return contacts.get(position);
    }

    public void update(int pos) {
        if (pos >= 0 && pos < contacts.size()) {
            // Notify adapter
            notifyItemChanged(pos);
        }
    }

    public void update(int pos, Message mesage) {
        if (pos >= 0 && pos < contacts.size()) {
            contacts.set(pos, mesage);
            // Notify adapter
            notifyItemChanged(pos);
        }
    }

    public void remove(int pos) {
        if (pos >= 0 && pos < contacts.size()) {
            // Remove from Tweets list
            contacts.remove(pos);
            // Notify adapter
            notifyDataSetChanged();
        }
    }
}
