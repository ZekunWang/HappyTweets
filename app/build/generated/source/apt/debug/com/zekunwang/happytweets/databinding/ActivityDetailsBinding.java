package com.zekunwang.happytweets.databinding;
import com.zekunwang.happytweets.R;
import com.zekunwang.happytweets.BR;
import android.view.View;
public class ActivityDetailsBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new android.databinding.ViewDataBinding.IncludedLayouts(3);
        sIncludes.setIncludes(0, 
            new String[] {"content_details"},
            new int[] {1},
            new int[] {R.layout.content_details});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.toolbar, 2);
    }
    // views
    public final com.zekunwang.happytweets.databinding.ContentDetailsBinding includeDetails;
    private final android.support.design.widget.CoordinatorLayout mboundView0;
    public final android.support.v7.widget.Toolbar toolbar;
    // variables
    private com.zekunwang.happytweets.models.Tweet mTweet;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityDetailsBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 1);
        final Object[] bindings = mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds);
        this.includeDetails = (com.zekunwang.happytweets.databinding.ContentDetailsBinding) bindings[1];
        this.mboundView0 = (android.support.design.widget.CoordinatorLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.toolbar = (android.support.v7.widget.Toolbar) bindings[2];
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
            case BR.tweet :
                setTweet((com.zekunwang.happytweets.models.Tweet) variable);
                return true;
        }
        return false;
    }

    public void setTweet(com.zekunwang.happytweets.models.Tweet tweet) {
        this.mTweet = tweet;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.tweet);
        super.requestRebind();
    }
    public com.zekunwang.happytweets.models.Tweet getTweet() {
        return mTweet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeIncludeDetai((com.zekunwang.happytweets.databinding.ContentDetailsBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeIncludeDetai(com.zekunwang.happytweets.databinding.ContentDetailsBinding includeDetails, int fieldId) {
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
        com.zekunwang.happytweets.models.Tweet tweet = mTweet;

        if ((dirtyFlags & 0x6L) != 0) {
        }
        // batch finished
        if ((dirtyFlags & 0x6L) != 0) {
            // api target 1

            this.includeDetails.setTweet(tweet);
        }
        includeDetails.executePendingBindings();
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    public static ActivityDetailsBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityDetailsBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityDetailsBinding>inflate(inflater, com.zekunwang.happytweets.R.layout.activity_details, root, attachToRoot, bindingComponent);
    }
    public static ActivityDetailsBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityDetailsBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.zekunwang.happytweets.R.layout.activity_details, null, false), bindingComponent);
    }
    public static ActivityDetailsBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityDetailsBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_details_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityDetailsBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): includeDetails
        flag 1 (0x2L): tweet
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}