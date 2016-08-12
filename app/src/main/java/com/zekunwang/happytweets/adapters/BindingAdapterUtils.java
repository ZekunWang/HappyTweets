package com.zekunwang.happytweets.adapters;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zekunwang.happytweets.R;
import com.zekunwang.happytweets.others.HelperMethods;
import com.zekunwang.happytweets.others.LinkifiedTextView;
import com.zekunwang.happytweets.others.TimeFormatter;

import android.databinding.BindingAdapter;
import android.net.ParseException;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class BindingAdapterUtils {
    @BindingAdapter({"bind:profileImageUrl"})
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext())
            .load(url)
            .placeholder(R.drawable.ic_launcher)
            .bitmapTransform(new RoundedCornersTransformation(view.getContext(), 5, 0))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(view);
    }

    @BindingAdapter({"bind:msgProfileImageUrl"})
    public static void loadMsgImage(ImageView view, String url) {
        Glide.with(view.getContext())
            .load(url)
            .placeholder(R.drawable.ic_launcher)
            .bitmapTransform(new RoundedCornersTransformation(view.getContext(), 25, 0))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(view);
    }

    @BindingAdapter({"bind:profileBannerUrl"})
    public static void loadBannerImage(ImageView view, String url) {
        Glide.with(view.getContext())
            .load(url)
            .placeholder(R.drawable.ic_launcher)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(view);
    }

    @BindingAdapter({"bind:relativeTime"})
    public static void loadRelativeTime(TextView view, String data) {
        if (view == null || data == null || data.length() == 0) {
            return;
        }
        String time = TimeFormatter.getTimeDifference(data);
        view.setText(time);
    }

    @BindingAdapter({"bind:linkifiedText"})
    public static void formatLinkifiedText(LinkifiedTextView view, String data) {
        if (view == null || data == null || data.length() == 0) {
            return;
        }
        view.setText(data);
        HelperMethods.formatBody(view.getContext(), view);
    }

    @BindingAdapter({"bind:timeFormat"})
    public static void loadTime(TextView view, String data) {
        if (view == null || data == null || data.length() == 0) {
            return;
        }
        SimpleDateFormat input = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZZ yyyy");
        SimpleDateFormat output = new SimpleDateFormat("HH:mm aa - dd MMM yy");
        String result = null;
        try {
            Date oneWayTripDate = input.parse(data);                 // parse input
            result = output.format(oneWayTripDate);    // format output
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        view.setText(result);
    }
}
