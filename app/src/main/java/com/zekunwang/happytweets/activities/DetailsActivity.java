package com.zekunwang.happytweets.activities;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zekunwang.happytweets.R;
import com.zekunwang.happytweets.databinding.ActivityDetailsBinding;
import com.zekunwang.happytweets.databinding.ContentDetailsBinding;
import com.zekunwang.happytweets.fragments.ComposeDialogFragment;
import com.zekunwang.happytweets.fragments.HomeTimelineFragment;
import com.zekunwang.happytweets.models.Medium;
import com.zekunwang.happytweets.models.Tweet;
import com.zekunwang.happytweets.models.User;
import com.zekunwang.happytweets.others.HelperMethods;

import org.parceler.Parcels;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import butterknife.OnTouch;

public class DetailsActivity extends AppCompatActivity
        implements ComposeDialogFragment.ComposeDialogListener{

    // Store the binding
    private ActivityDetailsBinding binding;
    ContentDetailsBinding subBinding;
    EditText etReply;
    Button btnCompose;
    TextView tvAvailableChars;
    Tweet tweet;
    Tweet retweetedStatus;
    Tweet targetTweet;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        // Inflate the content view (replacing `setContentView`)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);

        // Set Toolbar as Actionbar
        setSupportActionBar(binding.toolbar);
        // Get includeDetails
        subBinding = binding.includeDetails;

        // Get intent data
        tweet = Parcels.unwrap(getIntent().getParcelableExtra("tweet"));
        retweetedStatus = tweet.getRetweetedStatus();
        targetTweet = retweetedStatus != null ? retweetedStatus : tweet;
        position = getIntent().getIntExtra("position", 0);
        // Bind data with layout
        binding.setTweet(targetTweet);

        // Set retweeted status notice
        if (retweetedStatus != null) {
            subBinding.ivNotice.setVisibility(View.VISIBLE);
            subBinding.tvNotice.setVisibility(View.VISIBLE);
            if (TimelineActivity.ACCOUNT != null &&
                TimelineActivity.ACCOUNT.getName().equals(tweet.getUser().getName())) {
                subBinding.tvNotice.setText("You Retweeted");
            } else {
                subBinding.tvNotice.setText(tweet.getUser().getName() + " Retweeted");
            }
        }

        etReply = subBinding.etReply;
        btnCompose = subBinding.btnCompose;
        tvAvailableChars = subBinding.tvAvailableChars;

        // Inflate media
        List<Medium> media = targetTweet.getMedia();
        if (media != null) {
            if (media.size() != 0) {
                // Get image
                subBinding.ivMedia.setVisibility(View.VISIBLE);
                Glide.with(this)
                    .load(media.get(0).getMediaUrl())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(subBinding.ivMedia);
                // Get video
                final String videos = media.get(0).getVideos();
                if (videos != null) {
                    subBinding.vvMedia.setVisibility(View.VISIBLE);
                    // Play video
                    HelperMethods.playVideo(this, subBinding.vvMedia, subBinding.ivButtonImage, videos, false);
                }
            }
        }

        binding.includeDetails.ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProfile(position, tweet.getUser());
            }
        });

        // Set text change listener
        HelperMethods.setTextChangedListener(this, subBinding.etReply, subBinding.tvAvailableChars, subBinding.btnCompose);

        // Bind data with ButterKnife
        ButterKnife.bind(this);
    }

    public void showProfile(int position, User user) {
        // Go to profile activity
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("user", Parcels.wrap(user));
        startActivityForResult(intent, TimelineActivity.REQUEST_PROFILE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == TimelineActivity.REQUEST_DETAILS ||
            requestCode == TimelineActivity.REQUEST_PROFILE) && resultCode == TimelineActivity.RESULT_OK) {
            int position = data.getIntExtra("position", -1);
            Tweet updatedTweet = Parcels.unwrap(data.getParcelableExtra("tweet"));
            // Update position
            tweet = updatedTweet;
        }
    }

    @OnFocusChange(R.id.etReply)
    public void onFocusChange(View view, boolean b) {
        if (!b) {   // not focus now
            if (etReply.length() == 0) {
                btnCompose.setVisibility(View.GONE);
                tvAvailableChars.setVisibility(View.GONE);
            }
        } else {    // focus now
            btnCompose.setVisibility(View.VISIBLE);
            tvAvailableChars.setVisibility(View.VISIBLE);

            if (etReply.length() == 0) {
                // @ status user screen name
                StringBuilder sb = new StringBuilder("@")
                    .append(targetTweet.getUser().getScreenName()).append(' ');
                // @ retweet user's screen name
                if (retweetedStatus != null) {
                    sb.append('@').append(tweet.getUser().getScreenName()).append(' ');
                }
                // @ all mentioned user
                for (String screenName : targetTweet.getUserMentions()) {
                    if (!screenName.equals(targetTweet.getUser().getScreenName())) {
                        sb.append("@").append(screenName).append(' ');
                    }
                }

                etReply.setText(sb.toString());

                // Move cursor to end of reply EditText
                etReply.setSelection(sb.length());

                // Set initial remaining char count
                tvAvailableChars.setText(String.valueOf(140 - etReply.length()));
            }
        }
    }

    // Listener to expand icon
    @OnTouch(R.id.etReply)
    public boolean onTouch(View v, MotionEvent event) {
        final int DRAWABLE_LEFT = 0;
        final int DRAWABLE_TOP = 1;
        final int DRAWABLE_RIGHT = 2;
        final int DRAWABLE_BOTTOM = 3;

        if(event.getAction() == MotionEvent.ACTION_UP) {
            if(event.getRawX() >= (etReply.getRight() - etReply.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                // Expand reply triggered
                jumpToReply();
                return true;
            }
        }
        return false;
    }

    private void jumpToReply() {
        ComposeDialogFragment composeDialogFragment = ComposeDialogFragment.newInstance(TimelineActivity.REQUEST_REPLY, tweet);
        composeDialogFragment.show(getFragmentManager(), "fragment_compose");
    }

    @Override
    public void onFinishComposeDialog(int requestCode, Tweet t) {
        if (requestCode == TimelineActivity.REQUEST_REPLY) {
            HelperMethods.postTweet(HomeTimelineFragment.adapter, t);
        }
    }

    private void clearReply() {
        // Clear reply EditText if not empty
        etReply.setText("");
        etReply.clearFocus();
        btnCompose.setVisibility(View.GONE);
        tvAvailableChars.setVisibility(View.GONE);
    }

    @OnClick({R.id.ivReply, R.id.ivFavorite, R.id.ivRetweet, R.id.ivShare})
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.ivReply:
                jumpToReply();
                break;
            case R.id.ivFavorite:
                // Switch favorite
                HelperMethods.switchFavorite(tweet, subBinding.ivFavorite, subBinding.tvFavoriteCount);
                break;
            case R.id.ivRetweet:
                HelperMethods.switchRetweet(tweet, subBinding.ivRetweet, subBinding.tvRetweetCount);
                break;
            case R.id.ivShare:
                shareLink();
                break;
        }
    }

    public void shareLink() {
        if (tweet.media == null || tweet.media.size() == 0) {
            return;
        }
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, tweet.media.get(0).getUrl());
        startActivity(Intent.createChooser(shareIntent, "Share link using"));
    }

    // Clear focus on EditText when click outside
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }


    // Submit new Tweet
    public void onSubmit(View v) {
        if (etReply.getText().length() <= 0) {    // No input
            return;
        }

        Tweet newTweet = new Tweet();
        newTweet.body = etReply.getText().toString();
        newTweet.user = TimelineActivity.ACCOUNT;
        newTweet.inReplyToStatusId = targetTweet.getInReplyToStatusId();

        // Post new Tweet reply
        HelperMethods.postTweet(null, newTweet);
        // Clear EditText
        clearReply();
    }

    @Override
    public void onBackPressed()
    {
        // Prepare data intent
        Intent data = new Intent();
        // Pass relevant data back as a result
        data.putExtra("position", position);
        // Pass relevant data back as a result
        data.putExtra("tweet", Parcels.wrap(tweet));
        // Activity finished ok, return the data
        setResult(RESULT_OK, data); // set result code and bundle data for response
        finish(); // closes the activity, pass data to parent
    }
}
