// Generated code from Butter Knife. Do not modify!
package com.zekunwang.happytweets.fragments;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import com.zekunwang.happytweets.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class TweetsListFragment_ViewBinding<T extends TweetsListFragment> implements Unbinder {
  protected T target;

  public TweetsListFragment_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.rvTweets = finder.findRequiredViewAsType(source, R.id.rvTweets, "field 'rvTweets'", RecyclerView.class);
    target.swipeContainer = finder.findRequiredViewAsType(source, R.id.swipeContainer, "field 'swipeContainer'", SwipeRefreshLayout.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.rvTweets = null;
    target.swipeContainer = null;

    this.target = null;
  }
}
