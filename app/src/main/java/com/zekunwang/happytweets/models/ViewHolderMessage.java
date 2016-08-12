package com.zekunwang.happytweets.models;

import com.zekunwang.happytweets.R;
import com.zekunwang.happytweets.activities.DetailsActivity;
import com.zekunwang.happytweets.databinding.ItemMessageBinding;
import com.zekunwang.happytweets.fragments.ComposeDialogFragment;
import com.zekunwang.happytweets.others.HelperMethods;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

public class ViewHolderMessage extends RecyclerView.ViewHolder {

    ItemMessageBinding binding;

    public ViewHolderMessage(ItemMessageBinding binding) {
        // Stores the itemView in a public final member variable that can be used
        // to access the context from any ViewHolder instance.
        super(binding.getRoot());
        this.binding = binding;
    }

    public ItemMessageBinding getBinding() {
        return binding;
    }
}
