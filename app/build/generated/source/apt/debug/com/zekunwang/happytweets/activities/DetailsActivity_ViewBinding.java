// Generated code from Butter Knife. Do not modify!
package com.zekunwang.happytweets.activities;

import android.view.MotionEvent;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import com.zekunwang.happytweets.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class DetailsActivity_ViewBinding<T extends DetailsActivity> implements Unbinder {
  protected T target;

  private View view2131361965;

  private View view2131361967;

  private View view2131361966;

  private View view2131361968;

  private View view2131361950;

  public DetailsActivity_ViewBinding(final T target, Finder finder, Object source) {
    this.target = target;

    View view;
    view = finder.findRequiredView(source, R.id.ivReply, "method 'onClick'");
    view2131361965 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = finder.findRequiredView(source, R.id.ivFavorite, "method 'onClick'");
    view2131361967 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = finder.findRequiredView(source, R.id.ivRetweet, "method 'onClick'");
    view2131361966 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = finder.findRequiredView(source, R.id.ivShare, "method 'onClick'");
    view2131361968 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = finder.findRequiredView(source, R.id.etReply, "method 'onFocusChange' and method 'onTouch'");
    view2131361950 = view;
    view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
      @Override
      public void onFocusChange(View p0, boolean p1) {
        target.onFocusChange(p0, p1);
      }
    });
    view.setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View p0, MotionEvent p1) {
        return target.onTouch(p0, p1);
      }
    });
  }

  @Override
  public void unbind() {
    if (this.target == null) throw new IllegalStateException("Bindings already cleared.");

    view2131361965.setOnClickListener(null);
    view2131361965 = null;
    view2131361967.setOnClickListener(null);
    view2131361967 = null;
    view2131361966.setOnClickListener(null);
    view2131361966 = null;
    view2131361968.setOnClickListener(null);
    view2131361968 = null;
    view2131361950.setOnFocusChangeListener(null);
    view2131361950.setOnTouchListener(null);
    view2131361950 = null;

    this.target = null;
  }
}
