package com.zekunwang.happytweets.fragments;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.zekunwang.happytweets.activities.DetailsActivity;
import com.zekunwang.happytweets.activities.ProfileActivity;
import com.zekunwang.happytweets.activities.TimelineActivity;
import com.zekunwang.happytweets.activities.TwitterApplication;
import com.zekunwang.happytweets.activities.TwitterClient;
import com.zekunwang.happytweets.adapters.MediaContactsAdapter;
import com.zekunwang.happytweets.adapters.MessageContactsAdapter;
import com.zekunwang.happytweets.models.Message;
import com.zekunwang.happytweets.models.Tweet;
import com.zekunwang.happytweets.models.User;
import com.zekunwang.happytweets.others.HelperMethods;

import org.json.JSONArray;
import org.json.JSONObject;
import org.parceler.Parcels;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MessageTimelineFragment extends TweetsListFragment {

    private MessageContactsAdapter adapter;
	private User account;
    private List<Message> messages;

    // Creates a new fragment given an int and title
    public static MessageTimelineFragment newInstance(User account) {
        MessageTimelineFragment fg = new MessageTimelineFragment();
        Bundle args = new Bundle();
        args.putParcelable("user", Parcels.wrap(account));
        fg.setArguments(args);
        return fg;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create new list
        tweets = new ArrayList<>();
        messages = new ArrayList<>();

        // Contact adapter from data source
        account = Parcels.unwrap(getArguments().getParcelable("user"));
        adapter = new MessageContactsAdapter(getActivity(), messages, account);
        rvAdapter = adapter;

        // Load for the first time
        swipeLoad();
    }

    @Override
    public void swipeLoad() {
        // Clear existing data
        adapter.clear();
        // Populate new data
        populateTimeline(null, null);
    }

    @Override
    public void endlessLoad(int page, int totalItemsCount) {
        populateTimeline(null, Long.parseLong(messages.get(totalItemsCount - 1).getMid()) - 1);
    }

    public MessageContactsAdapter getAdapter() {
        return adapter;
    }

    // Sent API request to get Timeline JSON
    // Fill the listView by creating Tweet object from JSON
    // since: id > since (newer)
    // max: id <= max (older)
    public void populateTimeline(Long since, Long max) {
        // Get received message
        TwitterApplication.getRestClient().getReceivedMessages(since, max, new JsonHttpResponseHandler(){
            // SUCCESS
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray jsonArray) {
                ArrayList<Message> newTweets = Message.fromJSONArray(jsonArray);
                if (newTweets != null) {
                    adapter.addAll(newTweets);
                }
            }

            // FAILURE
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                JSONObject errorResponse) {
                if (errorResponse != null) {
                    Log.d("DEBUG", "Fetch received messages error: " + errorResponse.toString());
                }
            }
        });

        // Get sent message
        TwitterApplication.getRestClient().getSentMessages(since, max, new JsonHttpResponseHandler(){
            // SUCCESS
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray jsonArray) {
                ArrayList<Message> newTweets = Message.fromJSONArray(jsonArray);
                if (newTweets != null) {
                    adapter.addAll(newTweets);
                    if (swipeContainer != null) {
                        // Disable refreshing icon
                        swipeContainer.setRefreshing(false);
                    }
                }
            }

            // FAILURE
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                JSONObject errorResponse) {
                if (errorResponse != null) {
                    Log.d("DEBUG", "Fetch sent messages error: " + errorResponse.toString());
                }
            }
        });
    }
}
