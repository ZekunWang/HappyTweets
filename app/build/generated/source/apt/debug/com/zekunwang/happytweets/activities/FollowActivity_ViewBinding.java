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

public class FollowActivity_ViewBinding<T extends FollowActivity> implements Unbinder {
  protected T target;

  private View view2131427465;

  private View view2131427467;

  public FollowActivity_ViewBinding(final T target, Finder finder, Object source) {
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
  }

  @Override
  public void unbind() {
    if (this.target == null) throw new IllegalStateException("Bindings already cleared.");

    view2131427465.setOnClickListener(null);
    view2131427465 = null;
    view2131427467.setOnClickListener(null);
    view2131427467 = null;

    this.target = null;
  }
}
