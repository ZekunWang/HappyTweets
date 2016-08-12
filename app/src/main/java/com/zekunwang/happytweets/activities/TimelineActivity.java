package com.zekunwang.happytweets.activities;

import com.astuetz.PagerSlidingTabStrip;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.zekunwang.happytweets.R;
import com.zekunwang.happytweets.adapters.ContactsAdapter;
import com.zekunwang.happytweets.adapters.SmartFragmentStatePagerAdapter;
import com.zekunwang.happytweets.fragments.ComposeDialogFragment;
import com.zekunwang.happytweets.fragments.HomeTimelineFragment;
import com.zekunwang.happytweets.fragments.MentionsTimelineFragment;
import com.zekunwang.happytweets.fragments.TweetsListFragment;
import com.zekunwang.happytweets.models.Tweet;
import com.zekunwang.happytweets.models.User;
import com.zekunwang.happytweets.others.HelperMethods;

import org.json.JSONObject;
import org.parceler.Parcels;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

public class TimelineActivity extends AppCompatActivity
    implements ComposeDialogFragment.ComposeDialogListener {

    private TwitterClient client;
    public TweetsPagerAdapter tweetsPagerAdapter;
    @BindView(R.id.tabs) PagerSlidingTabStrip tabStrip;
    @BindView(R.id.viewpager) ViewPager viewPager;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.fab) FloatingActionButton fab;
    public static final int REQUEST_COMPOSE = 20;
    public static final int REQUEST_REPLY = 21;
    public static final int REQUEST_DETAILS = 22;
    public static final int REQUEST_PROFILE = 23;
    public static User ACCOUNT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        // Bind views
        ButterKnife.bind(this);
        // Set Toolbar as Actionbar
        setSupportActionBar(toolbar);

        // Get client
        client = TwitterApplication.getRestClient();

        // Get account info
        getAccount();

        // Create new Adapter
        tweetsPagerAdapter = new TweetsPagerAdapter(getSupportFragmentManager(), ACCOUNT);
        // Set viewpager adapter for the pager
        viewPager.setAdapter(tweetsPagerAdapter);
        // Attach the pager tab to viewpager
        tabStrip.setViewPager(viewPager);
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
            getCurrentAdapter().update(position, updatedTweet);
        }
    }

    private ContactsAdapter getCurrentAdapter() {
        TweetsListFragment fg = getCurrentFragment();
        ContactsAdapter adapter = null;
        if (fg instanceof HomeTimelineFragment) {
            adapter = ((HomeTimelineFragment) fg).getAdapter();
        } else {
            adapter = ((MentionsTimelineFragment) fg).getAdapter();
        }
        return adapter;
    }

    private void getAccount() {
        client.getAccount(new JsonHttpResponseHandler(){
            // SUCCESS
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject jsonObject) {
                // Find account or create and save
                ACCOUNT = User.findOrCreateFromJson(jsonObject);
                // Update account if already exists
                User.update(ACCOUNT);
            }

            // FAILURE
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                JSONObject errorResponse) {
                if (errorResponse != null) {
                    Log.d("DEBUG", "Verify credentials error: " + errorResponse.toString());
                }
            }
        });
    }

    // Inflate the menu; this adds items to the action bar if it is present.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timeline, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id) {
            case R.id.action_profile:
                showProfile(ACCOUNT);
                break;
            case R.id.action_search:
                showSearchTweets();
                break;
            case R.id.action_message:
                showMessage();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showMessage() {
        Intent intent = new Intent(this, DirectMessageActivity.class);
        intent.putExtra("user", Parcels.wrap(ACCOUNT));
        startActivity(intent);
    }

    private void showSearchTweets() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    private void showProfile(User user) {
        // Go to detail activity
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("user", Parcels.wrap(user));
        startActivity(intent);
    }

    private void composeTweet() {
        ComposeDialogFragment composeDialogFragment = ComposeDialogFragment.newInstance(REQUEST_COMPOSE, null);
        composeDialogFragment.show(getFragmentManager(), "fragment_compose");
    }

    // double tap to scroll to top
    @OnClick({R.id.toolbar, R.id.fab})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar:
                getCurrentFragment().goToTop();
                break;
            case R.id.fab:
                composeTweet();
                break;
        }
    }

    // Get current fragment
    public TweetsListFragment getCurrentFragment() {
        return tweetsPagerAdapter.getCurrentFragment(viewPager.getCurrentItem());
    }

    // Return the order of fragments in the view pager
    public class TweetsPagerAdapter extends SmartFragmentStatePagerAdapter {
        private String[] tabTitles = {"Home", "Mentions"};
        private Map<Integer, TweetsListFragment> map = new HashMap<>();
        private User account;
        MenuItem miActionProgressItem;

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
                fg =  HomeTimelineFragment.newInstance(account);
            } else if (position == 1) {
                fg = MentionsTimelineFragment.newInstance(account);
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
