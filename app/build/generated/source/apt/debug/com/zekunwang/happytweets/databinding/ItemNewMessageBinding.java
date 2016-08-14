package com.zekunwang.happytweets.databinding;
import com.zekunwang.happytweets.R;
import com.zekunwang.happytweets.BR;
import android.view.View;
public class ItemNewMessageBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    public final android.widget.ImageView ivProfile;
    private final android.widget.RelativeLayout mboundView0;
    public final android.widget.TextView tvScreenName;
    public final android.widget.TextView tvUsername;
    // variables
    private com.zekunwang.happytweets.models.User mUser;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemNewMessageBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds);
        this.ivProfile = (android.widget.ImageView) bindings[1];
        this.ivProfile.setTag(null);
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvScreenName = (android.widget.TextView) bindings[3];
        this.tvScreenName.setTag(null);
        this.tvUsername = (android.widget.TextView) bindings[2];
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
        java.lang.String userGetNameUser = null;
        java.lang.String userGetProfileImageU = null;
        java.lang.String userGetScreenNameUse = null;
        com.zekunwang.happytweets.models.User user = mUser;
        java.lang.String stringUserGetScreenN = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (user != null) {
                    // read user.getName()
                    userGetNameUser = user.getName();
                    // read user.getProfileImageUrl()
                    userGetProfileImageU = user.getProfileImageUrl();
                    // read user.getScreenName()
                    userGetScreenNameUse = user.getScreenName();
                }


                // read ("@") + (user.getScreenName())
                stringUserGetScreenN = ("@") + (userGetScreenNameUse);
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            com.zekunwang.happytweets.adapters.BindingAdapterUtils.loadMsgImage(this.ivProfile, userGetProfileImageU);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvScreenName, stringUserGetScreenN);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvUsername, userGetNameUser);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    public static ItemNewMessageBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ItemNewMessageBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ItemNewMessageBinding>inflate(inflater, com.zekunwang.happytweets.R.layout.item_new_message, root, attachToRoot, bindingComponent);
    }
    public static ItemNewMessageBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ItemNewMessageBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.zekunwang.happytweets.R.layout.item_new_message, null, false), bindingComponent);
    }
    public static ItemNewMessageBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ItemNewMessageBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/item_new_message_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ItemNewMessageBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): user
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}