package com.zekunwang.happytweets.databinding;
import com.zekunwang.happytweets.R;
import com.zekunwang.happytweets.BR;
import android.view.View;
public class ActivityProfileBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new android.databinding.ViewDataBinding.IncludedLayouts(16);
        sIncludes.setIncludes(0, 
            new String[] {"content_profile"},
            new int[] {9},
            new int[] {R.layout.content_profile});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.collapsing_toolbar, 10);
        sViewsWithIds.put(R.id.rvFollow, 11);
        sViewsWithIds.put(R.id.tvFriend, 12);
        sViewsWithIds.put(R.id.tvFollower, 13);
        sViewsWithIds.put(R.id.tabs, 14);
        sViewsWithIds.put(R.id.fab, 15);
    }
    // views
    public final android.support.design.widget.CollapsingToolbarLayout collapsingToolbar;
    public final android.support.design.widget.FloatingActionButton fab;
    public final com.zekunwang.happytweets.databinding.ContentProfileBinding includeDetails;
    public final android.widget.ImageView ivBackgroundImage;
    public final android.widget.ImageView ivFollow;
    public final android.widget.ImageView ivProfile;
    private final android.support.design.widget.CoordinatorLayout mboundView0;
    public final android.widget.RelativeLayout rvFollow;
    public final com.astuetz.PagerSlidingTabStrip tabs;
    public final com.zekunwang.happytweets.others.LinkifiedTextView tvBody;
    public final android.widget.TextView tvFollower;
    public final android.widget.TextView tvFollowerCount;
    public final android.widget.TextView tvFriend;
    public final android.widget.TextView tvFriendCount;
    public final android.widget.TextView tvScreenName;
    public final android.widget.TextView tvUsername;
    // variables
    private com.zekunwang.happytweets.models.User mUser;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityProfileBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 1);
        final Object[] bindings = mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds);
        this.collapsingToolbar = (android.support.design.widget.CollapsingToolbarLayout) bindings[10];
        this.fab = (android.support.design.widget.FloatingActionButton) bindings[15];
        this.includeDetails = (com.zekunwang.happytweets.databinding.ContentProfileBinding) bindings[9];
        this.ivBackgroundImage = (android.widget.ImageView) bindings[1];
        this.ivBackgroundImage.setTag(null);
        this.ivFollow = (android.widget.ImageView) bindings[2];
        this.ivFollow.setTag(null);
        this.ivProfile = (android.widget.ImageView) bindings[3];
        this.ivProfile.setTag(null);
        this.mboundView0 = (android.support.design.widget.CoordinatorLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rvFollow = (android.widget.RelativeLayout) bindings[11];
        this.tabs = (com.astuetz.PagerSlidingTabStrip) bindings[14];
        this.tvBody = (com.zekunwang.happytweets.others.LinkifiedTextView) bindings[6];
        this.tvBody.setTag(null);
        this.tvFollower = (android.widget.TextView) bindings[13];
        this.tvFollowerCount = (android.widget.TextView) bindings[8];
        this.tvFollowerCount.setTag(null);
        this.tvFriend = (android.widget.TextView) bindings[12];
        this.tvFriendCount = (android.widget.TextView) bindings[7];
        this.tvFriendCount.setTag(null);
        this.tvScreenName = (android.widget.TextView) bindings[5];
        this.tvScreenName.setTag(null);
        this.tvUsername = (android.widget.TextView) bindings[4];
        this.tvUsername.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
        }
        includeDetails.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (includeDetails.hasPendingBindings()) {
            return true;
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
            mDirtyFlags |= 0x2L;
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
            case 0 :
                return onChangeIncludeDetai((com.zekunwang.happytweets.databinding.ContentProfileBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeIncludeDetai(com.zekunwang.happytweets.databinding.ContentProfileBinding includeDetails, int fieldId) {
        switch (fieldId) {
            case BR._all: {
                synchronized(this) {
                        mDirtyFlags |= 0x1L;
                }
                return true;
            }
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
        long userGetFollowersUser = 0L;
        java.lang.String userGetScreenNameUse = null;
        java.lang.String stringUserGetFollowe = null;
        java.lang.String userGetProfileBanner = null;
        java.lang.String stringUserGetFriends = null;
        com.zekunwang.happytweets.models.User user = mUser;
        long userGetFriendsUser = 0L;
        boolean userIsFollowingUser = false;
        java.lang.String stringUserGetScreenN = null;
        java.lang.String userGetDescriptionUs = null;

        if ((dirtyFlags & 0x6L) != 0) {



                if (user != null) {
                    // read user.getName()
                    userGetNameUser = user.getName();
                    // read user.getProfileImageUrl()
                    userGetProfileImageU = user.getProfileImageUrl();
                    // read user.getFollowers()
                    userGetFollowersUser = user.getFollowers();
                    // read user.getScreenName()
                    userGetScreenNameUse = user.getScreenName();
                    // read user.getProfileBannerUrl()
                    userGetProfileBanner = user.getProfileBannerUrl();
                    // read user.getFriends()
                    userGetFriendsUser = user.getFriends();
                    // read user.isFollowing()
                    userIsFollowingUser = user.isFollowing();
                    // read user.getDescription()
                    userGetDescriptionUs = user.getDescription();
                }
                if((dirtyFlags & 0x6L) != 0) {
                    if (userIsFollowingUser) {
                        dirtyFlags |= 0x10L;
                        dirtyFlags |= 0x40L;
                    } else {
                        dirtyFlags |= 0x8L;
                        dirtyFlags |= 0x20L;
                    }}


                // read ("") + (user.getFollowers())
                stringUserGetFollowe = ("") + (userGetFollowersUser);
                // read ("@") + (user.getScreenName())
                stringUserGetScreenN = ("@") + (userGetScreenNameUse);
                // read ("") + (user.getFriends())
                stringUserGetFriends = ("") + (userGetFriendsUser);
                // read user.isFollowing() ? @android:drawable/ic_account_check : @android:drawable/ic_account_plus
                userIsFollowingUserA = (userIsFollowingUser) ? (getDrawableFromResource(R.drawable.ic_account_check)) : (getDrawableFromResource(R.drawable.ic_account_plus));
                // read user.isFollowing() ? @android:drawable/button_background : @android:drawable/button_background_disabled
                UserIsFollowingUserA1 = (userIsFollowingUser) ? (getDrawableFromResource(R.drawable.button_background)) : (getDrawableFromResource(R.drawable.button_background_disabled));
        }
        // batch finished
        if ((dirtyFlags & 0x6L) != 0) {
            // api target 1

            this.includeDetails.setUser(user);
            com.zekunwang.happytweets.adapters.BindingAdapterUtils.loadBannerImage(this.ivBackgroundImage, userGetProfileBanner);
            android.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(this.ivFollow, userIsFollowingUserA);
            android.databinding.adapters.ViewBindingAdapter.setBackground(this.ivFollow, UserIsFollowingUserA1);
            com.zekunwang.happytweets.adapters.BindingAdapterUtils.loadImage(this.ivProfile, userGetProfileImageU);
            com.zekunwang.happytweets.adapters.BindingAdapterUtils.formatLinkifiedText(this.tvBody, userGetDescriptionUs);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvFollowerCount, stringUserGetFollowe);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvFriendCount, stringUserGetFriends);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvScreenName, stringUserGetScreenN);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvUsername, userGetNameUser);
        }
        includeDetails.executePendingBindings();
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    public static ActivityProfileBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityProfileBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityProfileBinding>inflate(inflater, com.zekunwang.happytweets.R.layout.activity_profile, root, attachToRoot, bindingComponent);
    }
    public static ActivityProfileBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityProfileBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.zekunwang.happytweets.R.layout.activity_profile, null, false), bindingComponent);
    }
    public static ActivityProfileBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityProfileBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_profile_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityProfileBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): includeDetails
        flag 1 (0x2L): user
        flag 2 (0x3L): null
        flag 3 (0x4L): user.isFollowing() ? @android:drawable/ic_account_check : @android:drawable/ic_account_plus
        flag 4 (0x5L): user.isFollowing() ? @android:drawable/ic_account_check : @android:drawable/ic_account_plus
        flag 5 (0x6L): user.isFollowing() ? @android:drawable/button_background : @android:drawable/button_background_disabled
        flag 6 (0x7L): user.isFollowing() ? @android:drawable/button_background : @android:drawable/button_background_disabled
    flag mapping end*/
    //end
}