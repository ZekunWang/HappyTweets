package com.zekunwang.happytweets.fragments;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.zekunwang.happytweets.activities.DetailsActivity;
import com.zekunwang.happytweets.activities.TimelineActivity;
import com.zekunwang.happytweets.activities.TwitterApplication;
import com.zekunwang.happytweets.activities.TwitterClient;
import com.zekunwang.happytweets.adapters.MediaContactsAdapter;
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

import cz.msebera.android.httpclient.Header;

public class MediaTimelineFragment extends TweetsListFragment
        implements ComposeDialogFragment.ComposeDialogListener {

    private MediaContactsAdapter adapter;
	private User account;

    // Creates a new fragment given an int and title
    public static MediaTimelineFragment newInstance(User account) {
        MediaTimelineFragment fg = new MediaTimelineFragment();
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

        // Contact adapter from data source
        account = Parcels.unwrap(getArguments().getParcelable("user"));
        adapter = new MediaContactsAdapter(getActivity(), tweets);
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
    public void itemClicked(int position) {
        // Go to detail activity
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("tweet", Parcels.wrap(adapter.getTweet(position)));
        intent.putExtra("position", position);
        getActivity().startActivityForResult(intent, TimelineActivity.REQUEST_DETAILS);
    }

    @Override
    public void onFinishComposeDialog(int requestCode, Tweet tweet, Message message) {
        if (requestCode == TimelineActivity.REQUEST_REPLY) {
            HelperMethods.postTweet(HomeTimelineFragment.adapter, tweet);
        }
    }

    @Override
    public void endlessLoad(int page, int totalItemsCount) {
        populateTimeline(null, adapter.get(totalItemsCount - 1).getTweet().getTid() - 1);
    }

    public MediaContactsAdapter getAdapter() {
        return adapter;
    }

    // Sent API request to get Timeline JSON
    // Fill the listView by creating Tweet object from JSON
    // since: id > since (newer)
    // max: id <= max (older)
    public void populateTimeline(Long since, Long max) {
        TwitterApplication.getRestClient().getUserTimeline(account.getUid(), since, max, new JsonHttpResponseHandler(){
            // SUCCESS
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray jsonArray) {
                ArrayList<Tweet> newTweets = Tweet.fromJSONArray(jsonArray);
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
                    Log.d("DEBUG", "Fetch media timeline error: " + errorResponse.toString());
                }
            }
        });
    }
}
