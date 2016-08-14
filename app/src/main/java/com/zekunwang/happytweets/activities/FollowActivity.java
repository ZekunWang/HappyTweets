package com.zekunwang.happytweets.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zekunwang.happytweets.R;
import com.zekunwang.happytweets.adapters.FollowContactsAdapter;
import com.zekunwang.happytweets.fragments.ComposeDialogFragment;
import com.zekunwang.happytweets.fragments.FollowListFragment;
import com.zekunwang.happytweets.fragments.FriendsListFragment;
import com.zekunwang.happytweets.fragments.HomeTimelineFragment;
import com.zekunwang.happytweets.fragments.TweetsListFragment;
import com.zekunwang.happytweets.models.Message;
import com.zekunwang.happytweets.models.Tweet;
import com.zekunwang.happytweets.models.User;
import com.zekunwang.happytweets.others.HelperMethods;

import org.parceler.Parcels;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class FollowActivity extends AppCompatActivity
    implements ComposeDialogFragment.ComposeDialogListener {

    int request;
    User account;
    final String TAG = "follow";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get intent data
        request = getIntent().getIntExtra("request", -1);
        account = Parcels.unwrap(getIntent().getParcelableExtra("user"));

        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Replace fragment base on request code
        if (request == ProfileActivity.FOLLOWERS) {
            ft.replace(R.id.flFollow, FollowListFragment.newInstance(account), TAG);
            setTitle("Followers");
        } else {
            ft.replace(R.id.flFollow, FriendsListFragment.newInstance(account), TAG);
            setTitle("Following");
        }
        // Complete the changes added above
        ft.commit();

        // Bind view with ButterKnife
        ButterKnife.bind(this);
    }

    @Override
    public void onFinishComposeDialog(int requestCode, Tweet tweet, Message message) {
        if (requestCode == TimelineActivity.REQUEST_COMPOSE) {
            HelperMethods.postTweet(HomeTimelineFragment.adapter, tweet);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == TimelineActivity.REQUEST_DETAILS ||
            requestCode == TimelineActivity.REQUEST_PROFILE) && resultCode == TimelineActivity.RESULT_OK) {
            int position = data.getIntExtra("position", -1);
            User updatedUser = Parcels.unwrap(data.getParcelableExtra("user"));
            // Update position
            getCurrentAdapter().update(position, updatedUser);
        }
    }

    private FollowContactsAdapter getCurrentAdapter() {
        FollowContactsAdapter adapter = null;
        TweetsListFragment fg = (TweetsListFragment) getSupportFragmentManager().findFragmentByTag(TAG);
        if (fg instanceof FollowListFragment) {
            adapter = ((FollowListFragment) fg).getAdapter();
        } else {
            adapter = ((FriendsListFragment) fg).getAdapter();
        }
        return adapter;
    }

    // double tap to scroll to top
    @OnClick({R.id.toolbar, R.id.fab})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar:
                // Get fragment
                TweetsListFragment fg = (TweetsListFragment) getSupportFragmentManager().findFragmentByTag(TAG);
                // go to top
                fg.goToTop();
                break;
            case R.id.fab:
                composeTweet();
                break;
        }
    }

    private void composeTweet() {
        ComposeDialogFragment composeDialogFragment
            = ComposeDialogFragment.newInstance(TimelineActivity.REQUEST_COMPOSE, null, null);
        composeDialogFragment.show(getSupportFragmentManager(), "fragment_compose");
    }

}
