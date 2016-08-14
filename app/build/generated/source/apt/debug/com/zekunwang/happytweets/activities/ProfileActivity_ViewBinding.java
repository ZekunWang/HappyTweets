// Generated code from Butter Knife. Do not modify!
package com.zekunwang.happytweets.activities;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import com.zekunwang.happytweets.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class ProfileActivity_ViewBinding<T extends ProfileActivity> implements Unbinder {
  protected T target;

  private View view2131361931;

  private View view2131361937;

  private View view2131361945;

  private View view2131361944;

  private View view2131361943;

  private View view2131361942;

  public ProfileActivity_ViewBinding(final T target, Finder finder, Object source) {
    this.target = target;

    View view;
    view = finder.findRequiredView(source, R.id.fab, "method 'onClick'");
    view2131361931 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = finder.findRequiredView(source, R.id.ivFollow, "method 'onClick'");
    view2131361937 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = finder.findRequiredView(source, R.id.tvFollower, "method 'onClick'");
    view2131361945 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = finder.findRequiredView(source, R.id.tvFollowerCount, "method 'onClick'");
    view2131361944 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = finder.findRequiredView(source, R.id.tvFriend, "method 'onClick'");
    view2131361943 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = finder.findRequiredView(source, R.id.tvFriendCount, "method 'onClick'");
    view2131361942 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  public void unbind() {
    if (this.target == null) throw new IllegalStateException("Bindings already cleared.");

    view2131361931.setOnClickListener(null);
    view2131361931 = null;
    view2131361937.setOnClickListener(null);
    view2131361937 = null;
    view2131361945.setOnClickListener(null);
    view2131361945 = null;
    view2131361944.setOnClickListener(null);
    view2131361944 = null;
    view2131361943.setOnClickListener(null);
    view2131361943 = null;
    view2131361942.setOnClickListener(null);
    view2131361942 = null;

    this.target = null;
  }
}
