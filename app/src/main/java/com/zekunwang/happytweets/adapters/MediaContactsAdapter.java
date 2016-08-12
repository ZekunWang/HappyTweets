package com.zekunwang.happytweets.adapters;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zekunwang.happytweets.R;
import com.zekunwang.happytweets.activities.ProfileActivity;
import com.zekunwang.happytweets.activities.TimelineActivity;
import com.zekunwang.happytweets.databinding.ItemMediumBinding;
import com.zekunwang.happytweets.fragments.ComposeDialogFragment;
import com.zekunwang.happytweets.models.Medium;
import com.zekunwang.happytweets.models.Tweet;
import com.zekunwang.happytweets.models.User;
import com.zekunwang.happytweets.models.ViewHolder;
import com.zekunwang.happytweets.models.ViewHolderMedium;
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

public class MediaContactsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // Store a member variable for the contacts
    private List<Medium> contacts;

    // Store the context for easy access
    private Context context;

    // Define listener member variable
    private static OnItemClickListener listener;
    // Media size
    int width;
    int height;

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
    public MediaContactsAdapter(Context context, List<Tweet> contacts) {
        this.context = context;
        width = (int) context.getResources().getDimension(R.dimen.item_media_width);
        height = (int) context.getResources().getDimension(R.dimen.item_media_height);
        this.contacts = getMediaList(contacts);
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return context;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolderMedium viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemMediumBinding binding = DataBindingUtil
            .inflate(inflater, R.layout.item_medium, parent, false);
        // distinguish view by viewType
        //View contactView = inflater.inflate(R.layout.item_tweet, parent, false);
        viewHolder = new ViewHolderMedium(binding);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        // get data item for position
        final Medium medium = contacts.get(position);
        // Get binding
        final ItemMediumBinding binding = ((ViewHolderMedium) viewHolder).getBinding();

        // Reset views
        binding.ivMedia.setImageResource(0);
        binding.vvMedia.setVisibility(View.GONE);

        // Add Tweet to the binding and
        binding.setMedium(medium);

        // Inflate media
        Glide.with(context)
            .load(medium.getMediaUrl())
            .override(width, height)
            .placeholder(R.drawable.ic_launcher_placeholder)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.ivMedia);

        // Get video
        final String videos = medium.getVideos();
        if (videos != null) {
            binding.vvMedia.setVisibility(View.VISIBLE);
            // Play video
            HelperMethods.playVideo(context, binding.vvMedia, null, videos, true);
        }
        binding.executePendingBindings();
    }

    List<Medium> getMediaList(List<Tweet> tweets) {
        List<Medium> mediaList = new ArrayList<>(), media;
        for (Tweet tweet : tweets) {
            media = getMedia(tweet);
            if (media != null && media.size() > 0) {
                mediaList.add(media.get(0));
            }
        }
        return mediaList;
    }

    List<Medium> getMedia(Tweet tweet) {
        Tweet retweetedStatus, targetTweet;
        retweetedStatus = tweet.getRetweetedStatus();
        targetTweet = retweetedStatus != null ? retweetedStatus : tweet;
        // Get media
        return targetTweet.getMedia();
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

    public void addAll(List<Tweet> list) {
        if (list != null) {
            // Add to Tweets list
            contacts.addAll(getMediaList(list));
            // Notify adapter
            notifyDataSetChanged();
        }
    }

    public void add(Tweet newTweet) {
        add(contacts.size(), newTweet);
    }

    public void add(int pos, Tweet newTweet) {
        if (newTweet != null && pos >= 0 && pos <= contacts.size()) {
            List<Medium> media = getMedia(newTweet);
            if (media != null && media.size() > 0) {
                // Add to Tweets list
                contacts.add(pos, media.get(0));
            }
            // Notify adapter
            notifyItemChanged(pos);
        }
    }

    public Tweet getTweet(int position) {
        return contacts.get(position).getTweet();
    }

    public Medium get(int pos) {
        if (pos >= 0 && pos < contacts.size()) {
            return contacts.get(pos);
        }
        return null;
    }

    public void update(int pos) {
        if (pos >= 0 && pos < contacts.size()) {
            // Notify adapter
            notifyItemChanged(pos);
        }
    }

    public void update(int pos, Tweet tweet) {
        if (pos >= 0 && pos < contacts.size()) {
            contacts.get(pos).tweet = tweet;
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
