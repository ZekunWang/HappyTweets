package com.zekunwang.happytweets.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.zekunwang.happytweets.R;
import com.zekunwang.happytweets.adapters.ContactsAdapter;
import com.zekunwang.happytweets.adapters.SmartFragmentStatePagerAdapter;
import com.zekunwang.happytweets.databinding.ActivityProfileBinding;
import com.zekunwang.happytweets.databinding.ContentProfileBinding;
import com.zekunwang.happytweets.fragments.ComposeDialogFragment;
import com.zekunwang.happytweets.fragments.FavoritesTimelineFragment;
import com.zekunwang.happytweets.fragments.HomeTimelineFragment;
import com.zekunwang.happytweets.fragments.MediaTimelineFragment;
import com.zekunwang.happytweets.fragments.MentionsTimelineFragment;
import com.zekunwang.happytweets.fragments.TweetsListFragment;
import com.zekunwang.happytweets.fragments.UserTimelineFragment;
import com.zekunwang.happytweets.models.Message;
import com.zekunwang.happytweets.models.Tweet;
import com.zekunwang.happytweets.models.User;
import com.zekunwang.happytweets.others.HelperMethods;

import org.parceler.Parcels;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileActivity extends AppCompatActivity
    implements ComposeDialogFragment.ComposeDialogListener {

    private ActivityProfileBinding binding;
    private ContentProfileBinding subBinding;
    public TweetsPagerAdapter tweetsPagerAdapter;
    private User account;
    private Message message;
    private Tweet tweet;
    private int position;
    public static final int FOLLOWERS = 0;
    public static final int FRIENDS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Inflate the content view (replacing `setContentView`)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        subBinding = binding.includeDetails;
        // Set toolbar
        setSupportActionBar(binding.toolbar);

        // Get intent data
        position = getIntent().getIntExtra("position", -1);
        if (getIntent().hasExtra("tweet")) {
            tweet = Parcels.unwrap(getIntent().getParcelableExtra("tweet"));
            Tweet retweetedStatus = tweet.getRetweetedStatus();
            Tweet targetTweet = retweetedStatus != null ? retweetedStatus : tweet;
            account = targetTweet.getUser();
        } else if (getIntent().hasExtra("message")){
            message = Parcels.unwrap(getIntent().getParcelableExtra("message"));
            account = message.getSender().getUid() == TimelineActivity.ACCOUNT.getUid() ?
                message.getRecipient() : message.getSender();
        } else {
            account = Parcels.unwrap(getIntent().getParcelableExtra("user"));
        }
        // Bind data with layout
        binding.setUser(account);

        // Hide follow icon if is authenticating user
        if (account.getUid() == TimelineActivity.ACCOUNT.getUid()) {
            subBinding.ivFollow.setVisibility(View.INVISIBLE);
        }

        // Create new Adapter
        tweetsPagerAdapter = new TweetsPagerAdapter(getSupportFragmentManager(), account);
        // Set viewpager adapter for the pager
        subBinding.viewpager.setAdapter(tweetsPagerAdapter);
        // Attach the pager tab to viewpager
        subBinding.tabs.setViewPager(subBinding.viewpager);

        // Bind activity with ButterKnife
        ButterKnife.bind(this);
    }

    @Override
    public void onFinishComposeDialog(int requestCode, Tweet tweet) {
        if (requestCode == TimelineActivity.REQUEST_COMPOSE || requestCode == TimelineActivity.REQUEST_REPLY) {
            HelperMethods.postTweet(HomeTimelineFragment.adapter, tweet);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == TimelineActivity.REQUEST_DETAILS || 
                requestCode == TimelineActivity.REQUEST_PROFILE) && resultCode == TimelineActivity.RESULT_OK) {
            int position = data.getIntExtra("position", -1);
            Tweet updatedTweet = Parcels.unwrap(data.getParcelableExtra("tweet"));
            // Update position
            TweetsListFragment fg = getCurrentFragment();
            if (fg instanceof MediaTimelineFragment) {
                ((MediaTimelineFragment) fg).getAdapter().update(position, updatedTweet);
            } else {
                getContactsAdapter(fg).update(position, updatedTweet);
            }
        }
    }

    private ContactsAdapter getContactsAdapter(TweetsListFragment fg) {
        ContactsAdapter adapter = null;
        if (fg instanceof UserTimelineFragment) {
            adapter = ((UserTimelineFragment) fg).getAdapter();
        } else {
            adapter = ((FavoritesTimelineFragment) fg).getAdapter();
        }
        return adapter;
    }

    private void composeTweet() {
        ComposeDialogFragment composeDialogFragment = ComposeDialogFragment.newInstance(TimelineActivity.REQUEST_COMPOSE, null);
        composeDialogFragment.show(getFragmentManager(), "fragment_compose");
    }

    // double tap to scroll to top
    @OnClick({R.id.toolbar, R.id.fab, R.id.ivFollow, R.id.tvFollower,
        R.id.tvFollowerCount, R.id.tvFriend, R.id.tvFriendCount})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar:
                getCurrentFragment().goToTop();
                break;
            case R.id.fab:
                composeTweet();
                break;
            case R.id.ivFollow:
                // Set listener to follow icon
                HelperMethods.switchFollow(account, subBinding.ivFollow);
                break;
            case R.id.tvFollower:
            case R.id.tvFollowerCount:
                showFollow(FOLLOWERS);
                break;
            case R.id.tvFriend:
            case R.id.tvFriendCount:
                showFollow(FRIENDS);
                break;

        }
    }

    private void showFollow(int request) {
        Intent intent = new Intent(this, FollowActivity.class);
        intent.putExtra("request", request);
        intent.putExtra("user", Parcels.wrap(account));
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent data = new Intent();
        // Pass updated user
        data.putExtra("position", position);
        if (tweet != null) {
            data.putExtra("tweet", Parcels.wrap(tweet));
        } else if (message != null) {
            data.putExtra("message", Parcels.wrap(message));
        } else {
            data.putExtra("user", Parcels.wrap(account));
        }
        // Set result code
        setResult(RESULT_OK, data);

        finish();
    }

    // Get current fragment
    public TweetsListFragment getCurrentFragment() {
        return tweetsPagerAdapter.getCurrentFragment(subBinding.viewpager.getCurrentItem());
    }

    // Return the order of fragments in the view pager
    public class TweetsPagerAdapter extends SmartFragmentStatePagerAdapter {
        private String[] tabTitles = {"Tweets", "Media", "Likes"};
        private Map<Integer, TweetsListFragment> map = new HashMap<>();
        private User account;

        // Adapter gets the manager insert or remove fragment from activity
        public TweetsPagerAdapter(FragmentManager fm, User account) {
            super(fm);
            this.account = account;
        }

        // Decide fragment by tab position
        @Override
        public Fragment getItem(int position) {
            TweetsListFragment fg = null;
            if (position == 0) {
                fg = UserTimelineFragment.newInstance(account);
            } else if (position == 1) {
                fg = MediaTimelineFragment.newInstance(account);
            } else {
                fg = FavoritesTimelineFragment.newInstance(account);
            }
            map.put(position, fg);
            return fg;
        }

        // Decide tab name by tab position
        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

        // get total count of fragments
        @Override
        public int getCount() {
            return tabTitles.length;
        }

        // Get created fragment by position
        public TweetsListFragment getCurrentFragment(int position) {
            return map.get(position);
        }
    }
}
