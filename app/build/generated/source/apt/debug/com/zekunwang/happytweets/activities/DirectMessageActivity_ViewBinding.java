// Generated code from Butter Knife. Do not modify!
package com.zekunwang.happytweets.activities;

import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import com.zekunwang.happytweets.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class DirectMessageActivity_ViewBinding<T extends DirectMessageActivity> implements Unbinder {
  protected T target;

  private View view2131361931;

  public DirectMessageActivity_ViewBinding(final T target, Finder finder, Object source) {
    this.target = target;

    View view;
    target.toolbar = finder.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    view = finder.findRequiredView(source, R.id.fab, "method 'onClick'");
    view2131361931 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.toolbar = null;

    view2131361931.setOnClickListener(null);
    view2131361931 = null;

    this.target = null;
  }
}
