package com.zekunwang.happytweets.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.zekunwang.happytweets.R;
import com.zekunwang.happytweets.fragments.ComposeDialogFragment;
import com.zekunwang.happytweets.fragments.MessageTimelineFragment;
import com.zekunwang.happytweets.fragments.NewMessageFragment;
import com.zekunwang.happytweets.models.Message;
import com.zekunwang.happytweets.models.Tweet;
import com.zekunwang.happytweets.models.User;

import org.json.JSONObject;
import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

public class DirectMessageActivity extends AppCompatActivity
    implements ComposeDialogFragment.ComposeDialogListener{

    @BindView(R.id.toolbar) Toolbar toolbar;
    private User account;
    final String TAG = "message";
    MessageTimelineFragment fg1;
    NewMessageFragment fg2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct_message);

        // Bind view with ButterKnife
        ButterKnife.bind(this);
        // Set toolbar
        setSupportActionBar(toolbar);
        // Set navigation up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Get data
        account = Parcels.unwrap(getIntent().getParcelableExtra("user"));
        // Replace fragment
        fg1 = MessageTimelineFragment.newInstance(account);
        fg2 = NewMessageFragment.newInstance(account);
        showMessageList();
    }

    @OnClick(R.id.fab)
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.fab:
                showFriendsList();
                break;
        }
    }

    private void showFriendsList() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flMessage, fg2);
        ft.commit();
    }

    private void showMessageList() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flMessage, fg1);
        ft.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == TimelineActivity.REQUEST_DETAILS ||
            requestCode == TimelineActivity.REQUEST_PROFILE) && resultCode == TimelineActivity.RESULT_OK) {
            int position = data.getIntExtra("position", -1);
            Message updatedMessage = Parcels.unwrap(data.getParcelableExtra("message"));
            // Update position
            fg1.getAdapter().update(position, updatedMessage);
        }
    }

    @Override
    public void onFinishComposeDialog(int requestCode, Tweet tweet, final Message newMessage) {
        TwitterApplication.getRestClient().composeNewMessages(newMessage, new JsonHttpResponseHandler(){
            // SUCCESS
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject jsonObject) {
                if (jsonObject != null) {
                    showMessageList();
                    // Update position
                    fg1.getAdapter().add(0, newMessage);

                }
            }

            // FAILURE
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                JSONObject errorResponse) {
                if (errorResponse != null) {
                    Log.d("DEBUG", "Compose message error: " + errorResponse.toString());
                }
            }
        });
    }
}
