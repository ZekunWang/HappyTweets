
package android.databinding;
import com.zekunwang.happytweets.test.BR;
class DataBinderMapper {
    final static int TARGET_MIN_SDK = 21;
    public DataBinderMapper() {
    }
    public android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View view, int layoutId) {
        switch(layoutId) {
                case com.zekunwang.happytweets.R.layout.activity_profile:
                    return com.zekunwang.happytweets.databinding.ActivityProfileBinding.bind(view, bindingComponent);
                case com.zekunwang.happytweets.R.layout.item_medium:
                    return com.zekunwang.happytweets.databinding.ItemMediumBinding.bind(view, bindingComponent);
                case com.zekunwang.happytweets.R.layout.item_tweet:
                    return com.zekunwang.happytweets.databinding.ItemTweetBinding.bind(view, bindingComponent);
                case com.zekunwang.happytweets.R.layout.item_new_message:
                    return com.zekunwang.happytweets.databinding.ItemNewMessageBinding.bind(view, bindingComponent);
                case com.zekunwang.happytweets.R.layout.activity_details:
                    return com.zekunwang.happytweets.databinding.ActivityDetailsBinding.bind(view, bindingComponent);
                case com.zekunwang.happytweets.R.layout.content_details:
                    return com.zekunwang.happytweets.databinding.ContentDetailsBinding.bind(view, bindingComponent);
                case com.zekunwang.happytweets.R.layout.content_profile:
                    return com.zekunwang.happytweets.databinding.ContentProfileBinding.bind(view, bindingComponent);
                case com.zekunwang.happytweets.R.layout.item_follow:
                    return com.zekunwang.happytweets.databinding.ItemFollowBinding.bind(view, bindingComponent);
                case com.zekunwang.happytweets.R.layout.item_message:
                    return com.zekunwang.happytweets.databinding.ItemMessageBinding.bind(view, bindingComponent);
        }
        return null;
    }
    android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View[] views, int layoutId) {
        switch(layoutId) {
        }
        return null;
    }
    int getLayoutId(String tag) {
        if (tag == null) {
            return 0;
        }
        final int code = tag.hashCode();
        switch(code) {
            case 366786799: {
                if(tag.equals("layout/activity_profile_0")) {
                    return com.zekunwang.happytweets.R.layout.activity_profile;
                }
                break;
            }
            case -1330037907: {
                if(tag.equals("layout/item_medium_0")) {
                    return com.zekunwang.happytweets.R.layout.item_medium;
                }
                break;
            }
            case -657259653: {
                if(tag.equals("layout/item_tweet_0")) {
                    return com.zekunwang.happytweets.R.layout.item_tweet;
                }
                break;
            }
            case 1813869810: {
                if(tag.equals("layout/item_new_message_0")) {
                    return com.zekunwang.happytweets.R.layout.item_new_message;
                }
                break;
            }
            case -600875192: {
                if(tag.equals("layout/activity_details_0")) {
                    return com.zekunwang.happytweets.R.layout.activity_details;
                }
                break;
            }
            case -1544296056: {
                if(tag.equals("layout/content_details_0")) {
                    return com.zekunwang.happytweets.R.layout.content_details;
                }
                break;
            }
            case -576634065: {
                if(tag.equals("layout/content_profile_0")) {
                    return com.zekunwang.happytweets.R.layout.content_profile;
                }
                break;
            }
            case -128071511: {
                if(tag.equals("layout/item_follow_0")) {
                    return com.zekunwang.happytweets.R.layout.item_follow;
                }
                break;
            }
            case -1881166511: {
                if(tag.equals("layout/item_message_0")) {
                    return com.zekunwang.happytweets.R.layout.item_message;
                }
                break;
            }
        }
        return 0;
    }
    String convertBrIdToString(int id) {
        if (id < 0 || id >= InnerBrLookup.sKeys.length) {
            return null;
        }
        return InnerBrLookup.sKeys[id];
    }
    private static class InnerBrLookup {
        static String[] sKeys = new String[]{
            "_all"
            ,"medium"
            ,"message"
            ,"tweet"
            ,"user"};
    }
}