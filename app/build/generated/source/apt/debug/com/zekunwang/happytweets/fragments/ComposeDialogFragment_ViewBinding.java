// Generated code from Butter Knife. Do not modify!
package com.zekunwang.happytweets.fragments;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import com.zekunwang.happytweets.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class ComposeDialogFragment_ViewBinding<T extends ComposeDialogFragment> implements Unbinder {
  protected T target;

  private View view2131361983;

  private View view2131361951;

  private View view2131361982;

  public ComposeDialogFragment_ViewBinding(final T target, Finder finder, Object source) {
    this.target = target;

    View view;
    view = finder.findRequiredView(source, R.id.etContent, "field 'etContent' and method 'onFocusChange'");
    target.etContent = finder.castView(view, R.id.etContent, "field 'etContent'", EditText.class);
    view2131361983 = view;
    view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
      @Override
      public void onFocusChange(View p0, boolean p1) {
        target.onFocusChange(p0, p1);
      }
    });
    view = finder.findRequiredView(source, R.id.btnCompose, "field 'btnCompose' and method 'onClick'");
    target.btnCompose = finder.castView(view, R.id.btnCompose, "field 'btnCompose'", Button.class);
    view2131361951 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.tvAvailableChars = finder.findRequiredViewAsType(source, R.id.tvAvailableChars, "field 'tvAvailableChars'", TextView.class);
    target.ivProfile = finder.findRequiredViewAsType(source, R.id.ivProfile, "field 'ivProfile'", ImageView.class);
    view = finder.findRequiredView(source, R.id.ivNavigationUp, "field 'ivNavigationUp' and method 'onClick'");
    target.ivNavigationUp = finder.castView(view, R.id.ivNavigationUp, "field 'ivNavigationUp'", ImageView.class);
    view2131361982 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.tvNotice = finder.findRequiredViewAsType(source, R.id.tvNotice, "field 'tvNotice'", TextView.class);
    target.tvScreenName = finder.findRequiredViewAsType(source, R.id.tvScreenName, "field 'tvScreenName'", TextView.class);
    target.tvUsername = finder.findRequiredViewAsType(source, R.id.tvUsername, "field 'tvUsername'", TextView.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.etContent = null;
    target.btnCompose = null;
    target.tvAvailableChars = null;
    target.ivProfile = null;
    target.ivNavigationUp = null;
    target.tvNotice = null;
    target.tvScreenName = null;
    target.tvUsername = null;

    view2131361983.setOnFocusChangeListener(null);
    view2131361983 = null;
    view2131361951.setOnClickListener(null);
    view2131361951 = null;
    view2131361982.setOnClickListener(null);
    view2131361982 = null;

    this.target = null;
  }
}
