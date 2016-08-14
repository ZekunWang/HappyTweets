// Generated code from Butter Knife. Do not modify!
package com.zekunwang.happytweets.fragments;

import android.widget.EditText;
import butterknife.internal.Finder;
import com.zekunwang.happytweets.R;
import java.lang.Object;
import java.lang.Override;

public class HomeTimelineFragment_ViewBinding<T extends HomeTimelineFragment> extends TweetsListFragment_ViewBinding<T> {
  public HomeTimelineFragment_ViewBinding(T target, Finder finder, Object source) {
    super(target, finder, source);

    target.etReply = finder.findRequiredViewAsType(source, R.id.etReply, "field 'etReply'", EditText.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    super.unbind();

    target.etReply = null;
  }
}
