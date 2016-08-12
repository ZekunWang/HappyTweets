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
import com.zekunwang.happytweets.fragments.MessageTimelineFragment;
import com.zekunwang.happytweets.models.Message;
import com.zekunwang.happytweets.models.Tweet;
import com.zekunwang.happytweets.models.User;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DirectMessageActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    private User account;
    final String TAG = "message";

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
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flMessage, MessageTimelineFragment.newInstance(account), TAG);
        ft.commit();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == TimelineActivity.REQUEST_DETAILS ||
            requestCode == TimelineActivity.REQUEST_PROFILE) && resultCode == TimelineActivity.RESULT_OK) {
            int position = data.getIntExtra("position", -1);
            Message updatedMessage = Parcels.unwrap(data.getParcelableExtra("message"));
            // Update position
            MessageTimelineFragment fg = (MessageTimelineFragment) getSupportFragmentManager().findFragmentByTag(TAG);
            fg.getAdapter().update(position, updatedMessage);
        }
    }

}
