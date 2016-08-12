package com.zekunwang.happytweets.adapters;

import com.zekunwang.happytweets.R;
import com.zekunwang.happytweets.activities.TimelineActivity;
import com.zekunwang.happytweets.databinding.ItemFollowBinding;
import com.zekunwang.happytweets.models.User;
import com.zekunwang.happytweets.models.ViewHolderFollow;
import com.zekunwang.happytweets.others.HelperMethods;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class FollowContactsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // Store a member variable for the contacts
    private List<User> contacts;

    // Store the context for easy access
    private Context context;

    // Store User
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
    public FollowContactsAdapter(Context context, List<User> contacts) {
        this(context, contacts, null);
    }
    public FollowContactsAdapter(Context context, List<User> contacts, User account) {
        this.contacts = contacts;
        this.context = context;
        this.account = account;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return context;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolderFollow viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemFollowBinding binding = DataBindingUtil
            .inflate(inflater, R.layout.item_follow, parent, false);
        // distinguish view by viewType
        //View contactView = inflater.inflate(R.layout.item_tweet, parent, false);
        viewHolder = new ViewHolderFollow(binding);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        // get data item for position
        final User user = contacts.get(position);
        // Get binding
        final ItemFollowBinding binding = ((ViewHolderFollow) viewHolder).getBinding();

        // Reset views
        binding.ivFollow.setVisibility(View.VISIBLE);

        // Add Tweet to the binding and
        binding.setUser(user);

        if (user.getUid() == TimelineActivity.ACCOUNT.getUid()) {
            binding.ivFollow.setVisibility(View.GONE);
        } else {
            // Set listener to follow icon
            binding.ivFollow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Switch favorite
                    HelperMethods.switchFollow(user, binding.ivFollow);
                }
            });
        }

        // Execute binding immediately
        binding.executePendingBindings();
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

    public void addAll(List<User> list) {
        if (list != null) {
            // Add to Tweets list
            contacts.addAll(list);
            // Notify adapter
            notifyDataSetChanged();
        }
    }

    public void add(User user) {
        add(contacts.size(), user);
    }

    public void add(int pos, User user) {
        if (user != null && pos >= 0 && pos <= contacts.size()) {
            // Add to Contact list
            contacts.add(pos, user);

            // Notify adapter
            notifyItemChanged(pos);
        }
    }

    public User getUser(int position) {
        return contacts.get(position);
    }

    public void update(int pos) {
        if (pos >= 0 && pos < contacts.size()) {
            // Notify adapter
            notifyItemChanged(pos);
        }
    }

    public void update(int pos, User user) {
        if (pos >= 0 && pos < contacts.size()) {
            contacts.set(pos, user);
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
