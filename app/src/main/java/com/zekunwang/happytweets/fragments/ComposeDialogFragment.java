package com.zekunwang.happytweets.fragments;


import com.bumptech.glide.Glide;
import com.zekunwang.happytweets.R;
import com.zekunwang.happytweets.activities.TimelineActivity;
import com.zekunwang.happytweets.models.Message;
import com.zekunwang.happytweets.models.Tweet;
import com.zekunwang.happytweets.models.User;
import com.zekunwang.happytweets.others.HelperMethods;

import org.parceler.Parcels;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class ComposeDialogFragment extends DialogFragment {

    @BindView(R.id.etContent) EditText etContent;
    @BindView(R.id.btnCompose) Button btnCompose;
    @BindView(R.id.tvAvailableChars) TextView tvAvailableChars;
    @BindView(R.id.ivProfile) ImageView ivProfile;
    @BindView(R.id.ivNavigationUp) ImageView ivNavigationUp;
    @BindView(R.id.tvNotice) TextView tvNotice;
    @BindView(R.id.tvScreenName) TextView tvScreenName;
    @BindView(R.id.tvUsername) TextView tvUsername;
    private Unbinder unbinder;
    private Tweet tweet;
    private Tweet retweetedStatus;
    private Tweet targetTweet;
    private User user;
    private int requestCode;
    Activity activity;

    // define listener to pass setting to activity
    public interface ComposeDialogListener {
        void onFinishComposeDialog(int requestCode, Tweet tweet, Message message);
    }

    public ComposeDialogFragment() {}


    public static ComposeDialogFragment newInstance(int requestCode, Tweet tweet, User user) {
        // pass setting to fragment
        ComposeDialogFragment composeDialogFragment = new ComposeDialogFragment();
        Bundle args = new Bundle();
        args.putInt("request_code", requestCode);
        if (tweet != null) {
            args.putParcelable("tweet", Parcels.wrap(tweet));
        } else if (user != null) {
            args.putParcelable("user", Parcels.wrap(user));
        }
        composeDialogFragment.setArguments(args);
        return composeDialogFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_compose, container);
        // bind fragment with ButterKnife
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
        activity = getActivity();

        // Show fake toolbar
        Glide.with(activity).load(TimelineActivity.ACCOUNT.getProfileImageUrl())
            .placeholder(R.drawable.ic_launcher)
            .bitmapTransform(new RoundedCornersTransformation(view.getContext(), 5, 0))
            .into(ivProfile);

        tvUsername.setText(TimelineActivity.ACCOUNT.getName());
        tvScreenName.setText("@" + TimelineActivity.ACCOUNT.getScreenName());

        // Get Tweet and request code
        requestCode = getArguments().getInt("request_code");

        // Put hashtags before input
        if (requestCode == TimelineActivity.REQUEST_REPLY) {
            tweet = Parcels.unwrap(getArguments().getParcelable("tweet"));
            retweetedStatus = tweet.getRetweetedStatus();
            targetTweet = retweetedStatus != null ? retweetedStatus : tweet;
            // @ status user screen name
            StringBuilder sb = new StringBuilder("@")
                .append(targetTweet.getUser().getScreenName())
                .append(' ');
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

            etContent.setText(sb.toString());

            // Move cursor to end of reply EditText
            etContent.setSelection(sb.length());

            // Set initial remaining char count
            tvAvailableChars.setText(String.valueOf(140 - etContent.length()));

            // Set notice TextView
            tvNotice.setVisibility(View.VISIBLE);
            tvNotice.setText("Replying to " + targetTweet.getUser().getName());
        } else if (requestCode == TimelineActivity.REQUEST_COMPOSE_MESSAGE) {
            user = Parcels.unwrap(getArguments().getParcelable("user"));
            // Set notice TextView
            tvNotice.setVisibility(View.VISIBLE);
            tvNotice.setText("New Message to " + user.getName());
            btnCompose.setText("Send");
        }

        // Set text change listener
        HelperMethods.setTextChangedListener(activity, etContent, tvAvailableChars, btnCompose);
    }

    @OnClick({R.id.btnCompose, R.id.ivNavigationUp})
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnCompose:
                if (etContent.getText().length() <= 0) {    // No input
                    dismiss();
                }

                if (requestCode == TimelineActivity.REQUEST_COMPOSE_MESSAGE) {
                    Message newMessage = new Message();
                    newMessage.text = etContent.getText().toString();
                    newMessage.recipient = user;
                    newMessage.sender = TimelineActivity.ACCOUNT;

                    // pass setting to activity via listener
                    ComposeDialogListener composeDialogListener
                        = (ComposeDialogListener) getActivity();
                    composeDialogListener.onFinishComposeDialog(requestCode, null, newMessage);
                } else {
                    Tweet newTweet = new Tweet();
                    newTweet.body = etContent.getText().toString();
                    newTweet.user = TimelineActivity.ACCOUNT;
                    if (tweet != null && requestCode == TimelineActivity.REQUEST_REPLY) {
                        newTweet.inReplyToStatusId = String.valueOf(tweet.getTid());
                    }

                    // pass setting to activity via listener
                    ComposeDialogListener composeDialogListener
                        = (ComposeDialogListener) getActivity();
                    composeDialogListener.onFinishComposeDialog(requestCode, newTweet, null);
                }
                // close fragment
                dismiss();
                break;
            case R.id.ivNavigationUp:
                dismiss();
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // unbind frrament and ButterKnife
        unbinder.unbind();
    }
}
