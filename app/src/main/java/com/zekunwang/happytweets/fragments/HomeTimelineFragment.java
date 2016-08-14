package com.zekunwang.happytweets.fragments;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.zekunwang.happytweets.R;
import com.zekunwang.happytweets.activities.DetailsActivity;
import com.zekunwang.happytweets.activities.TimelineActivity;
import com.zekunwang.happytweets.activities.TwitterApplication;
import com.zekunwang.happytweets.activities.TwitterClient;
import com.zekunwang.happytweets.adapters.ContactsAdapter;
import com.zekunwang.happytweets.models.Message;
import com.zekunwang.happytweets.models.Tweet;
import com.zekunwang.happytweets.models.User;
import com.zekunwang.happytweets.others.HelperMethods;
import com.zekunwang.happytweets.others.ItemClickSupport;

import org.json.JSONArray;
import org.json.JSONObject;
import org.parceler.Parcels;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cz.msebera.android.httpclient.Header;

public class HomeTimelineFragment extends TweetsListFragment
        implements ComposeDialogFragment.ComposeDialogListener {

    public static ContactsAdapter adapter;
    private User account;


    // Creates a new fragment given an int and title
    public static HomeTimelineFragment newInstance(User account) {
        HomeTimelineFragment fg = new HomeTimelineFragment();
        Bundle args = new Bundle();
        args.putParcelable("user", Parcels.wrap(account));
        fg.setArguments(args);
        return fg;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get saved Tweets from DB
        if (!HelperMethods.isConnected(getContext())) {
            tweets = ContactsAdapter.getAll();
        } else {
            tweets = new ArrayList<>();
            ContactsAdapter.clearAll();
        }

        // Contact adapter from data source
        account = Parcels.unwrap(getArguments().getParcelable("user"));
        adapter = new ContactsAdapter(getActivity(), tweets, account);
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
        Intent intent = new Intent(getContext(), DetailsActivity.class);
        intent.putExtra("tweet", Parcels.wrap(tweets.get(position)));
        intent.putExtra("position", position);
        getActivity().startActivityForResult(intent, TimelineActivity.REQUEST_DETAILS);
    }

    @Override
    public boolean itemLongClicked(final int position) {
        final Tweet tweet = tweets.get(position);
        if (TimelineActivity.ACCOUNT == null || tweet.getUser().getUid() != TimelineActivity.ACCOUNT.getUid()) {
            return false;
        }
        new AlertDialog.Builder(getContext())
            .setTitle("Delete")
            .setMessage("Are you sure to delete this tweet?")
            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // continue with delete
                    TwitterApplication.getRestClient().deleteTweet(tweet, new JsonHttpResponseHandler() {
                        // SUCCESS
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject jsonObject) {
                            Tweet newTweet = Tweet.fromJSONObject(jsonObject);
                            if (newTweet != null) {
                                adapter.remove(position);
                            }
                        }

                        // FAILURE
                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                            JSONObject errorResponse) {
                            if (errorResponse != null) {
                                Log.d("DEBUG",
                                    "Delete Tweet error: " + errorResponse.toString());
                            }
                        }
                    });
                }
            })
            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // do nothing
                }
            })
            .setIcon(R.drawable.ic_launcher)
            .show();
        return false;
    }

    @Override
    public void onFinishComposeDialog(int requestCode, Tweet tweet, Message message) {
        if (requestCode == TimelineActivity.REQUEST_REPLY) {
            HelperMethods.postTweet(HomeTimelineFragment.adapter, tweet);
        }
    }

    @Override
    public void endlessLoad(int page, int totalItemsCount) {
        populateTimeline(null, Long.parseLong(tweets.get(totalItemsCount - 1).getTid()) - 1);
    }

    public ContactsAdapter getAdapter() {
        return adapter;
    }

    // Sent API request to get Timeline JSON
    // Fill the listView by creating Tweet object from JSON
    // since: id > since (newer)
    // max: id <= max (older)
    public void populateTimeline(Long since, Long max) {
        TwitterApplication.getRestClient().getHomeTimeline(since, max, new JsonHttpResponseHandler(){
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
                Log.d("DEBUG", "Fetch home timeline error: " + errorResponse.toString());
            }
        });
    }
}
