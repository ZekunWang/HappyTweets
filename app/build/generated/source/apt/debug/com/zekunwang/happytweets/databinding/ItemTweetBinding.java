package com.zekunwang.happytweets.databinding;
import com.zekunwang.happytweets.R;
import com.zekunwang.happytweets.BR;
import android.view.View;
public class ItemTweetBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.ivReference, 10);
        sViewsWithIds.put(R.id.tvNotice, 11);
        sViewsWithIds.put(R.id.ivNotice, 12);
        sViewsWithIds.put(R.id.ivMedia, 13);
        sViewsWithIds.put(R.id.vvMedia, 14);
        sViewsWithIds.put(R.id.ivReply, 15);
        sViewsWithIds.put(R.id.ivMessage, 16);
    }
    // views
    public final android.widget.ImageView ivFavorite;
    public final android.widget.ImageView ivMedia;
    public final android.widget.ImageView ivMessage;
    public final android.widget.ImageView ivNotice;
    public final android.widget.ImageView ivProfile;
    public final android.widget.ImageView ivReference;
    public final android.widget.ImageView ivReply;
    public final android.widget.ImageView ivRetweet;
    private final android.widget.RelativeLayout mboundView0;
    public final com.zekunwang.happytweets.others.LinkifiedTextView tvBody;
    public final android.widget.TextView tvFavoriteCount;
    public final android.widget.TextView tvNotice;
    public final android.widget.TextView tvRelativeTime;
    public final android.widget.TextView tvRetweetCount;
    public final android.widget.TextView tvScreenName;
    public final android.widget.TextView tvUsername;
    public final com.yqritc.scalablevideoview.ScalableVideoView vvMedia;
    // variables
    private com.zekunwang.happytweets.models.Tweet mTweet;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemTweetBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 17, sIncludes, sViewsWithIds);
        this.ivFavorite = (android.widget.ImageView) bindings[8];
        this.ivFavorite.setTag(null);
        this.ivMedia = (android.widget.ImageView) bindings[13];
        this.ivMessage = (android.widget.ImageView) bindings[16];
        this.ivNotice = (android.widget.ImageView) bindings[12];
        this.ivProfile = (android.widget.ImageView) bindings[1];
        this.ivProfile.setTag(null);
        this.ivReference = (android.widget.ImageView) bindings[10];
        this.ivReply = (android.widget.ImageView) bindings[15];
        this.ivRetweet = (android.widget.ImageView) bindings[6];
        this.ivRetweet.setTag(null);
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvBody = (com.zekunwang.happytweets.others.LinkifiedTextView) bindings[5];
        this.tvBody.setTag(null);
        this.tvFavoriteCount = (android.widget.TextView) bindings[9];
        this.tvFavoriteCount.setTag(null);
        this.tvNotice = (android.widget.TextView) bindings[11];
        this.tvRelativeTime = (android.widget.TextView) bindings[4];
        this.tvRelativeTime.setTag(null);
        this.tvRetweetCount = (android.widget.TextView) bindings[7];
        this.tvRetweetCount.setTag(null);
        this.tvScreenName = (android.widget.TextView) bindings[3];
        this.tvScreenName.setTag(null);
        this.tvUsername = (android.widget.TextView) bindings[2];
        this.tvUsername.setTag(null);
        this.vvMedia = (com.yqritc.scalablevideoview.ScalableVideoView) bindings[14];
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
            case BR.tweet :
                setTweet((com.zekunwang.happytweets.models.Tweet) variable);
                return true;
        }
        return false;
    }

    public void setTweet(com.zekunwang.happytweets.models.Tweet tweet) {
        this.mTweet = tweet;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
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
        java.lang.String tweetGetUserTweetGet = null;
        android.graphics.drawable.Drawable tweetIsFavoritedTwee = null;
        java.lang.String tweetGetBodyTweet = null;
        int tweetGetFavoriteCoun = 0;
        com.zekunwang.happytweets.models.User tweetGetUserTweet = null;
        boolean tweetIsRetweetedTwee = false;
        java.lang.String stringTweetGetUserTw = null;
        com.zekunwang.happytweets.models.Tweet tweet = mTweet;
        java.lang.String stringTweetGetFavori = null;
        java.lang.String tweetGetUserTweetGet1 = null;
        java.lang.String tweetGetUserTweetGet2 = null;
        boolean tweetIsFavoritedTwee1 = false;
        android.graphics.drawable.Drawable TweetIsRetweetedTwee1 = null;
        int tweetGetRetweetCount = 0;
        java.lang.String tweetGetCreatedAtTwe = null;
        java.lang.String stringTweetGetRetwee = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (tweet != null) {
                    // read tweet.getBody()
                    tweetGetBodyTweet = tweet.getBody();
                    // read tweet.getFavoriteCount()
                    tweetGetFavoriteCoun = tweet.getFavoriteCount();
                    // read tweet.getUser()
                    tweetGetUserTweet = tweet.getUser();
                    // read tweet.isRetweeted()
                    tweetIsRetweetedTwee = tweet.isRetweeted();
                    // read tweet.isFavorited()
                    tweetIsFavoritedTwee1 = tweet.isFavorited();
                    // read tweet.getRetweetCount()
                    tweetGetRetweetCount = tweet.getRetweetCount();
                    // read tweet.getCreatedAt()
                    tweetGetCreatedAtTwe = tweet.getCreatedAt();
                }
                if((dirtyFlags & 0x3L) != 0) {
                    if (tweetIsRetweetedTwee) {
                        dirtyFlags |= 0x20L;
                    } else {
                        dirtyFlags |= 0x10L;
                    }}
                if((dirtyFlags & 0x3L) != 0) {
                    if (tweetIsFavoritedTwee1) {
                        dirtyFlags |= 0x8L;
                    } else {
                        dirtyFlags |= 0x4L;
                    }}


                // read ("") + (tweet.getFavoriteCount())
                stringTweetGetFavori = ("") + (tweetGetFavoriteCoun);
                // read tweet.isRetweeted() ? @android:drawable/ic_twitter_retweet_lighted : @android:drawable/ic_twitter_retweet
                TweetIsRetweetedTwee1 = (tweetIsRetweetedTwee) ? (getDrawableFromResource(R.drawable.ic_twitter_retweet_lighted)) : (getDrawableFromResource(R.drawable.ic_twitter_retweet));
                // read tweet.isFavorited() ? @android:drawable/ic_heart_lighted : @android:drawable/ic_heart
                tweetIsFavoritedTwee = (tweetIsFavoritedTwee1) ? (getDrawableFromResource(R.drawable.ic_heart_lighted)) : (getDrawableFromResource(R.drawable.ic_heart));
                // read ("") + (tweet.getRetweetCount())
                stringTweetGetRetwee = ("") + (tweetGetRetweetCount);
                if (tweetGetUserTweet != null) {
                    // read tweet.getUser().getProfileImageUrl()
                    tweetGetUserTweetGet = tweetGetUserTweet.getProfileImageUrl();
                    // read tweet.getUser().getScreenName()
                    tweetGetUserTweetGet1 = tweetGetUserTweet.getScreenName();
                    // read tweet.getUser().getName()
                    tweetGetUserTweetGet2 = tweetGetUserTweet.getName();
                }


                // read ("@") + (tweet.getUser().getScreenName())
                stringTweetGetUserTw = ("@") + (tweetGetUserTweetGet1);
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            android.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(this.ivFavorite, tweetIsFavoritedTwee);
            com.zekunwang.happytweets.adapters.BindingAdapterUtils.loadImage(this.ivProfile, tweetGetUserTweetGet);
            android.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(this.ivRetweet, TweetIsRetweetedTwee1);
            com.zekunwang.happytweets.adapters.BindingAdapterUtils.formatLinkifiedText(this.tvBody, tweetGetBodyTweet);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvFavoriteCount, stringTweetGetFavori);
            com.zekunwang.happytweets.adapters.BindingAdapterUtils.loadRelativeTime(this.tvRelativeTime, tweetGetCreatedAtTwe);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvRetweetCount, stringTweetGetRetwee);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvScreenName, stringTweetGetUserTw);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvUsername, tweetGetUserTweetGet2);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    public static ItemTweetBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ItemTweetBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ItemTweetBinding>inflate(inflater, com.zekunwang.happytweets.R.layout.item_tweet, root, attachToRoot, bindingComponent);
    }
    public static ItemTweetBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ItemTweetBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.zekunwang.happytweets.R.layout.item_tweet, null, false), bindingComponent);
    }
    public static ItemTweetBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ItemTweetBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/item_tweet_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ItemTweetBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): tweet
        flag 1 (0x2L): null
        flag 2 (0x3L): tweet.isFavorited() ? @android:drawable/ic_heart_lighted : @android:drawable/ic_heart
        flag 3 (0x4L): tweet.isFavorited() ? @android:drawable/ic_heart_lighted : @android:drawable/ic_heart
        flag 4 (0x5L): tweet.isRetweeted() ? @android:drawable/ic_twitter_retweet_lighted : @android:drawable/ic_twitter_retweet
        flag 5 (0x6L): tweet.isRetweeted() ? @android:drawable/ic_twitter_retweet_lighted : @android:drawable/ic_twitter_retweet
    flag mapping end*/
    //end
}