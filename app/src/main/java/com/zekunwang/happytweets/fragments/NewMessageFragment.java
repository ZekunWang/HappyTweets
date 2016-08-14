package com.zekunwang.happytweets.fragments;

    import com.loopj.android.http.JsonHttpResponseHandler;
    import com.zekunwang.happytweets.activities.DetailsActivity;
    import com.zekunwang.happytweets.activities.ProfileActivity;
    import com.zekunwang.happytweets.activities.TimelineActivity;
    import com.zekunwang.happytweets.activities.TwitterApplication;
    import com.zekunwang.happytweets.activities.TwitterClient;
    import com.zekunwang.happytweets.adapters.NewMessageContactsAdapter;
    import com.zekunwang.happytweets.models.Message;
    import com.zekunwang.happytweets.models.Tweet;
    import com.zekunwang.happytweets.models.User;
    import com.zekunwang.happytweets.others.HelperMethods;

    import org.json.JSONArray;
    import org.json.JSONException;
    import org.json.JSONObject;
    import org.parceler.Parcels;

    import android.content.Intent;
    import android.os.Bundle;
    import android.support.annotation.Nullable;
    import android.util.Log;

    import java.util.ArrayList;
    import java.util.List;

    import cz.msebera.android.httpclient.Header;

public class NewMessageFragment extends TweetsListFragment {

    private NewMessageContactsAdapter adapter;
    private JSONArray jsonIds;
    private List<User> users;
    private User account;
    public final int LOAD_COUNT = 25;

    // define listener to pass setting to activity
    public interface NewMessageDialogListener {
        void onFinishNewMessageDialog(int resultCode);
    }

    // Creates a new fragment given an int and title
    public static NewMessageFragment newInstance(User account) {
        NewMessageFragment fg = new NewMessageFragment();
        Bundle args = new Bundle();
        args.putParcelable("user", Parcels.wrap(account));
        fg.setArguments(args);
        return fg;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create new list
        users = new ArrayList<>();

        // Contact adapter from data source
        account = Parcels.unwrap(getArguments().getParcelable("user"));
        adapter = new NewMessageContactsAdapter(getActivity(), users);
        rvAdapter = adapter;

        // Load for the first time
        swipeLoad();
    }

    @Override
    public void swipeLoad() {
        // Clear existing data
        adapter.clear();
        // Populate new ids
        populateIdList();
    }

    @Override
    public void itemClicked(int position) {
        // Go to profile activity
        composeMessage(users.get(position));
    }

    private void composeMessage(User user) {
        ComposeDialogFragment composeDialogFragment =
            ComposeDialogFragment.newInstance(TimelineActivity.REQUEST_COMPOSE_MESSAGE, null, user);
        composeDialogFragment.show(getFragmentManager(), "fragment_compose_message");
    }

    @Override
    public void endlessLoad(int page, int totalItemsCount) {
        populateUsersList();
    }

    public NewMessageContactsAdapter getAdapter() {
        return adapter;
    }

    // Sent API request to get ids JSON
    public void populateIdList() {
        TwitterApplication.getRestClient().getFriendsList(account, new JsonHttpResponseHandler(){
            // SUCCESS
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject jsonObject) {
                if (jsonObject != null) {
                    // View only first 5000 ids for simplicity
                    try {
                        jsonIds = jsonObject.getJSONArray("ids");
                        // Populate new users
                        populateUsersList();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            // FAILURE
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                JSONObject errorResponse) {
                if (errorResponse != null) {
                    Log.d("DEBUG", "Fetch follower ids error: " + errorResponse.toString());
                }
            }
        });
    }

    // Sent API request to get users JSON
    // Fill the listView by creating User object from JSON
    public void populateUsersList() {
        if (users.size() == jsonIds.length()) {
            if (swipeContainer != null) {
                // Disable refreshing icon
                swipeContainer.setRefreshing(false);
            }
            return;
        }

        // Get next id list
        List<Long> newIdList = User.fromIdJSONArray(jsonIds, users.size(), LOAD_COUNT);

        TwitterApplication.getRestClient().getUsersList(newIdList, new JsonHttpResponseHandler(){
            // SUCCESS
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray jsonArray) {
                List<User> newUsers = User.fromJSONArray(jsonArray);
                if (newUsers != null) {
                    adapter.addAll(newUsers);
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
                    Log.d("DEBUG", "Fetch follower users error: " + errorResponse.toString());
                }
            }
        });
    }
}
