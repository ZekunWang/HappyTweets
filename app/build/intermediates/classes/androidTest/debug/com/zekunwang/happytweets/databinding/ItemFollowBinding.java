package com.zekunwang.happytweets.databinding;
import com.zekunwang.happytweets.R;
import com.zekunwang.happytweets.BR;
import android.view.View;
public class ItemFollowBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    public final android.widget.ImageView ivFollow;
    public final android.widget.ImageView ivProfile;
    private final android.widget.RelativeLayout mboundView0;
    public final com.zekunwang.happytweets.others.LinkifiedTextView tvBody;
    public final android.widget.TextView tvScreenName;
    public final android.widget.TextView tvUsername;
    // variables
    private com.zekunwang.happytweets.models.User mUser;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemFollowBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds);
        this.ivFollow = (android.widget.ImageView) bindings[2];
        this.ivFollow.setTag(null);
        this.ivProfile = (android.widget.ImageView) bindings[1];
        this.ivProfile.setTag(null);
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvBody = (com.zekunwang.happytweets.others.LinkifiedTextView) bindings[5];
        this.tvBody.setTag(null);
        this.tvScreenName = (android.widget.TextView) bindings[4];
        this.tvScreenName.setTag(null);
        this.tvUsername = (android.widget.TextView) bindings[3];
        this.tvUsername.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    public boolean setVariable(int variableId, Object variable) {
        switch(variableId) {
            case BR.user :
                setUser((com.zekunwang.happytweets.models.User) variable);
                return true;
        }
        return false;
    }

    public void setUser(com.zekunwang.happytweets.models.User user) {
        this.mUser = user;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.user);
        super.requestRebind();
    }
    public com.zekunwang.happytweets.models.User getUser() {
        return mUser;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        android.graphics.drawable.Drawable userIsFollowingUserA = null;
        java.lang.String userGetNameUser = null;
        java.lang.String userGetProfileImageU = null;
        android.graphics.drawable.Drawable UserIsFollowingUserA1 = null;
        java.lang.String userGetScreenNameUse = null;
        com.zekunwang.happytweets.models.User user = mUser;
        boolean userIsFollowingUser = false;
        java.lang.String stringUserGetScreenN = null;
        java.lang.String userGetDescriptionUs = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (user != null) {
                    // read user.getName()
                    userGetNameUser = user.getName();
                    // read user.getProfileImageUrl()
                    userGetProfileImageU = user.getProfileImageUrl();
                    // read user.getScreenName()
                    userGetScreenNameUse = user.getScreenName();
                    // read user.isFollowing()
                    userIsFollowingUser = user.isFollowing();
                    // read user.getDescription()
                    userGetDescriptionUs = user.getDescription();
                }
                if((dirtyFlags & 0x3L) != 0) {
                    if (userIsFollowingUser) {
                        dirtyFlags |= 0x8L;
                        dirtyFlags |= 0x20L;
                    } else {
                        dirtyFlags |= 0x4L;
                        dirtyFlags |= 0x10L;
                    }}


                // read ("@") + (user.getScreenName())
                stringUserGetScreenN = ("@") + (userGetScreenNameUse);
                // read user.isFollowing() ? @android:drawable/ic_account_check : @android:drawable/ic_account_plus
                userIsFollowingUserA = (userIsFollowingUser) ? (getDrawableFromResource(R.drawable.ic_account_check)) : (getDrawableFromResource(R.drawable.ic_account_plus));
                // read user.isFollowing() ? @android:drawable/button_background : @android:drawable/button_background_disabled
                UserIsFollowingUserA1 = (userIsFollowingUser) ? (getDrawableFromResource(R.drawable.button_background)) : (getDrawableFromResource(R.drawable.button_background_disabled));
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            android.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(this.ivFollow, userIsFollowingUserA);
            android.databinding.adapters.ViewBindingAdapter.setBackground(this.ivFollow, UserIsFollowingUserA1);
            com.zekunwang.happytweets.adapters.BindingAdapterUtils.loadImage(this.ivProfile, userGetProfileImageU);
            com.zekunwang.happytweets.adapters.BindingAdapterUtils.formatLinkifiedText(this.tvBody, userGetDescriptionUs);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvScreenName, stringUserGetScreenN);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvUsername, userGetNameUser);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    public static ItemFollowBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ItemFollowBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ItemFollowBinding>inflate(inflater, com.zekunwang.happytweets.R.layout.item_follow, root, attachToRoot, bindingComponent);
    }
    public static ItemFollowBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ItemFollowBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.zekunwang.happytweets.R.layout.item_follow, null, false), bindingComponent);
    }
    public static ItemFollowBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ItemFollowBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/item_follow_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ItemFollowBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): user
        flag 1 (0x2L): null
        flag 2 (0x3L): user.isFollowing() ? @android:drawable/ic_account_check : @android:drawable/ic_account_plus
        flag 3 (0x4L): user.isFollowing() ? @android:drawable/ic_account_check : @android:drawable/ic_account_plus
        flag 4 (0x5L): user.isFollowing() ? @android:drawable/button_background : @android:drawable/button_background_disabled
        flag 5 (0x6L): user.isFollowing() ? @android:drawable/button_background : @android:drawable/button_background_disabled
    flag mapping end*/
    //end
}