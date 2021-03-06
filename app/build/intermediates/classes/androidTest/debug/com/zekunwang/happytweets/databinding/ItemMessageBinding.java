package com.zekunwang.happytweets.databinding;
import com.zekunwang.happytweets.R;
import com.zekunwang.happytweets.BR;
import android.view.View;
public class ItemMessageBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    public final android.widget.ImageView ivDirect;
    public final android.widget.ImageView ivProfile;
    private final android.widget.RelativeLayout mboundView0;
    public final com.zekunwang.happytweets.others.LinkifiedTextView tvBody;
    public final android.widget.TextView tvRelativeTime;
    public final android.widget.TextView tvScreenName;
    public final android.widget.TextView tvUsername;
    // variables
    private com.zekunwang.happytweets.models.Message mMessage;
    private com.zekunwang.happytweets.models.User mUser;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemMessageBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds);
        this.ivDirect = (android.widget.ImageView) bindings[5];
        this.ivDirect.setTag(null);
        this.ivProfile = (android.widget.ImageView) bindings[1];
        this.ivProfile.setTag(null);
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvBody = (com.zekunwang.happytweets.others.LinkifiedTextView) bindings[6];
        this.tvBody.setTag(null);
        this.tvRelativeTime = (android.widget.TextView) bindings[4];
        this.tvRelativeTime.setTag(null);
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
                mDirtyFlags = 0x4L;
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
            case BR.message :
                setMessage((com.zekunwang.happytweets.models.Message) variable);
                return true;
            case BR.user :
                setUser((com.zekunwang.happytweets.models.User) variable);
                return true;
        }
        return false;
    }

    public void setMessage(com.zekunwang.happytweets.models.Message message) {
        this.mMessage = message;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.message);
        super.requestRebind();
    }
    public com.zekunwang.happytweets.models.Message getMessage() {
        return mMessage;
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
        com.zekunwang.happytweets.models.User messageGetSenderMess = null;
        java.lang.String userGetNameUser = null;
        com.zekunwang.happytweets.models.Message message = mMessage;
        java.lang.String userGetProfileImageU = null;
        java.lang.String messageGetTextMessag = null;
        java.lang.String messageGetCreatedAtM = null;
        java.lang.String userGetScreenNameUse = null;
        boolean userGetUidUserMessag = false;
        long messageGetSenderMess1 = 0L;
        long userGetUidUser = 0L;
        com.zekunwang.happytweets.models.User user = mUser;
        android.graphics.drawable.Drawable UserGetUidUserMessag1 = null;
        java.lang.String stringUserGetScreenN = null;

        if ((dirtyFlags & 0x7L) != 0) {



                if (message != null) {
                    // read message.getSender()
                    messageGetSenderMess = message.getSender();
                }
                if (user != null) {
                    // read user.getUid()
                    userGetUidUser = user.getUid();
                }


                if (messageGetSenderMess != null) {
                    // read message.getSender().getUid()
                    messageGetSenderMess1 = messageGetSenderMess.getUid();
                }


                // read user.getUid() == message.getSender().getUid()
                userGetUidUserMessag = (userGetUidUser) == (messageGetSenderMess1);
                if((dirtyFlags & 0x7L) != 0) {
                    if (userGetUidUserMessag) {
                        dirtyFlags |= 0x10L;
                    } else {
                        dirtyFlags |= 0x8L;
                    }}


                // read user.getUid() == message.getSender().getUid() ? @android:drawable/ic_login : @android:drawable/ic_logout
                UserGetUidUserMessag1 = (userGetUidUserMessag) ? (getDrawableFromResource(R.drawable.ic_login)) : (getDrawableFromResource(R.drawable.ic_logout));
            if ((dirtyFlags & 0x5L) != 0) {

                    if (message != null) {
                        // read message.getText()
                        messageGetTextMessag = message.getText();
                        // read message.getCreatedAt()
                        messageGetCreatedAtM = message.getCreatedAt();
                    }
            }
            if ((dirtyFlags & 0x6L) != 0) {

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
        }
        // batch finished
        if ((dirtyFlags & 0x7L) != 0) {
            // api target 1

            android.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(this.ivDirect, UserGetUidUserMessag1);
        }
        if ((dirtyFlags & 0x6L) != 0) {
            // api target 1

            com.zekunwang.happytweets.adapters.BindingAdapterUtils.loadMsgImage(this.ivProfile, userGetProfileImageU);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvScreenName, stringUserGetScreenN);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvUsername, userGetNameUser);
        }
        if ((dirtyFlags & 0x5L) != 0) {
            // api target 1

            com.zekunwang.happytweets.adapters.BindingAdapterUtils.formatLinkifiedText(this.tvBody, messageGetTextMessag);
            com.zekunwang.happytweets.adapters.BindingAdapterUtils.loadRelativeTime(this.tvRelativeTime, messageGetCreatedAtM);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    public static ItemMessageBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ItemMessageBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ItemMessageBinding>inflate(inflater, com.zekunwang.happytweets.R.layout.item_message, root, attachToRoot, bindingComponent);
    }
    public static ItemMessageBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ItemMessageBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.zekunwang.happytweets.R.layout.item_message, null, false), bindingComponent);
    }
    public static ItemMessageBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ItemMessageBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/item_message_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ItemMessageBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): message
        flag 1 (0x2L): user
        flag 2 (0x3L): null
        flag 3 (0x4L): user.getUid() == message.getSender().getUid() ? @android:drawable/ic_login : @android:drawable/ic_logout
        flag 4 (0x5L): user.getUid() == message.getSender().getUid() ? @android:drawable/ic_login : @android:drawable/ic_logout
    flag mapping end*/
    //end
}