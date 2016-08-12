// Generated code from Butter Knife. Do not modify!
package com.zekunwang.happytweets.activities;

import android.support.v7.widget.Toolbar;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import com.zekunwang.happytweets.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class DirectMessageActivity_ViewBinding<T extends DirectMessageActivity> implements Unbinder {
  protected T target;

  public DirectMessageActivity_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.toolbar = finder.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.toolbar = null;

    this.target = null;
  }
}