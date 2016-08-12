package com.zekunwang.happytweets.fragments;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.zekunwang.happytweets.R;
import com.zekunwang.happytweets.activities.DetailsActivity;
import com.zekunwang.happytweets.activities.TimelineActivity;
import com.zekunwang.happytweets.activities.TwitterApplication;
import com.zekunwang.happytweets.activities.TwitterClient;
import com.zekunwang.happytweets.adapters.ContactsAdapter;
import com.zekunwang.happytweets.listeners.EndlessRecyclerViewScrollListener;
import com.zekunwang.happytweets.models.Tweet;
import com.zekunwang.happytweets.models.User;
import com.zekunwang.happytweets.others.HelperMethods;
import com.zekunwang.happytweets.others.ItemClickSupport;

import org.json.JSONArray;
import org.json.JSONObject;
import org.parceler.Parcels;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cz.msebera.android.httpclient.Header;

public class TweetsListFragment extends Fragment {

    protected List<Tweet> tweets;
    protected RecyclerView.Adapter<RecyclerView.ViewHolder> rvAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    @BindView(R.id.rvTweets) RecyclerView rvTweets;
    @BindView(R.id.swipeContainer) protected SwipeRefreshLayout swipeContainer;
    protected MenuItem miActionProgressItem;
    private Unbinder unbinder;
    final int TAP_THRESHOLD = 4000;
    long timeStamp;


    // 1.onAttach(Activity) called once the fragment is associated with its activity.
    // 2.onCreate(Bundle) called to do initial creation of the fragment.
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create layout manager
        mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    }

    // 3.onCreateView() creates and returns the view hierarchy associated with the fragment.
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent,
        @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tweets_list, parent, false);
        // bind fragment with ButterKnife
        unbinder = ButterKnife.bind(this, v);
        // Construct the adapter from data source
        rvTweets.setAdapter(rvAdapter);
        // Contact layout manager
        rvTweets.setLayoutManager(mLinearLayoutManager);
        // Set endless scroll
        rvTweets.addOnScrollListener(new EndlessRecyclerViewScrollListener(mLinearLayoutManager, rvAdapter) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                endlessLoad(page, totalItemsCount);
            }
        });

        // Add item click feature to RecyclerView
        ItemClickSupport.addTo(rvTweets).setOnItemClickListener(
            new ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                    itemClicked(position);
                }
            }
        );

        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Check connection
                if (HelperMethods.isConnected(getActivity())) {
                    swipeLoad();
                } else {
                    swipeContainer.setRefreshing(false);
                }
            }
        });

        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light);

        return v;
    }

    public void setProgressbar(MenuItem miActionProgressItem) {
        this.miActionProgressItem = miActionProgressItem;
    }

    public void swipeLoad() {
    }

    public void itemClicked(int position) {
    }

    public void endlessLoad(int page, int totalItemsCount) {
    }

    public void goToTop() {
        long newTimeStamp = System.currentTimeMillis();
        if (newTimeStamp - timeStamp <= TAP_THRESHOLD) {
            mLinearLayoutManager.scrollToPositionWithOffset(0, 0);
        }
        timeStamp = newTimeStamp;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // unbind frrament and ButterKnife
        unbinder.unbind();
    }
}
