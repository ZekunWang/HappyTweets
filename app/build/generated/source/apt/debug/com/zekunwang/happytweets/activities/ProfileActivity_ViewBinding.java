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

  private View view2131427465;

  private View view2131427467;

  private View view2131427497;

  private View view2131427502;

  private View view2131427501;

  private View view2131427500;

  private View view2131427499;

  public ProfileActivity_ViewBinding(final T target, Finder finder, Object source) {
    this.target = target;

    View view;
    view = finder.findRequiredView(source, R.id.toolbar, "method 'onClick'");
    view2131427465 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = finder.findRequiredView(source, R.id.fab, "method 'onClick'");
    view2131427467 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = finder.findRequiredView(source, R.id.ivFollow, "method 'onClick'");
    view2131427497 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = finder.findRequiredView(source, R.id.tvFollower, "method 'onClick'");
    view2131427502 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = finder.findRequiredView(source, R.id.tvFollowerCount, "method 'onClick'");
    view2131427501 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = finder.findRequiredView(source, R.id.tvFriend, "method 'onClick'");
    view2131427500 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = finder.findRequiredView(source, R.id.tvFriendCount, "method 'onClick'");
    view2131427499 = view;
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

    view2131427465.setOnClickListener(null);
    view2131427465 = null;
    view2131427467.setOnClickListener(null);
    view2131427467 = null;
    view2131427497.setOnClickListener(null);
    view2131427497 = null;
    view2131427502.setOnClickListener(null);
    view2131427502 = null;
    view2131427501.setOnClickListener(null);
    view2131427501 = null;
    view2131427500.setOnClickListener(null);
    view2131427500 = null;
    view2131427499.setOnClickListener(null);
    view2131427499 = null;

    this.target = null;
  }
}
