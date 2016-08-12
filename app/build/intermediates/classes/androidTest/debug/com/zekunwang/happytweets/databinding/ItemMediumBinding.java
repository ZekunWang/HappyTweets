package com.zekunwang.happytweets.databinding;
import com.zekunwang.happytweets.R;
import com.zekunwang.happytweets.BR;
import android.view.View;
public class ItemMediumBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.ivMedia, 1);
        sViewsWithIds.put(R.id.vvMedia, 2);
        sViewsWithIds.put(R.id.ivExpand, 3);
    }
    // views
    public final android.widget.ImageView ivExpand;
    public final android.widget.ImageView ivMedia;
    public final android.widget.RelativeLayout rlItemMedia;
    public final com.yqritc.scalablevideoview.ScalableVideoView vvMedia;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemMediumBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds);
        this.ivExpand = (android.widget.ImageView) bindings[3];
        this.ivMedia = (android.widget.ImageView) bindings[1];
        this.rlItemMedia = (android.widget.RelativeLayout) bindings[0];
        this.rlItemMedia.setTag(null);
        this.vvMedia = (com.yqritc.scalablevideoview.ScalableVideoView) bindings[2];
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
            case BR.medium :
                return true;
        }
        return false;
    }

    public void setMedium(com.zekunwang.happytweets.models.Medium medium) {
        // not used, ignore
    }
    public com.zekunwang.happytweets.models.Medium getMedium() {
        return null;
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
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    public static ItemMediumBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ItemMediumBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ItemMediumBinding>inflate(inflater, com.zekunwang.happytweets.R.layout.item_medium, root, attachToRoot, bindingComponent);
    }
    public static ItemMediumBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ItemMediumBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.zekunwang.happytweets.R.layout.item_medium, null, false), bindingComponent);
    }
    public static ItemMediumBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ItemMediumBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/item_medium_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ItemMediumBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): medium
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}