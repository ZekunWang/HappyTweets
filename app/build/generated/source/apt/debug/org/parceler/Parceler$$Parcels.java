
package org.parceler;

import java.util.HashMap;
import java.util.Map;
import com.zekunwang.happytweets.models.Medium;
import com.zekunwang.happytweets.models.Medium$$Parcelable;
import com.zekunwang.happytweets.models.Message;
import com.zekunwang.happytweets.models.Message$$Parcelable;
import com.zekunwang.happytweets.models.Tweet;
import com.zekunwang.happytweets.models.Tweet$$Parcelable;
import com.zekunwang.happytweets.models.User;
import com.zekunwang.happytweets.models.User$$Parcelable;

@Generated(value = "org.parceler.ParcelAnnotationProcessor", date = "2016-08-11T23:19-0500")
@SuppressWarnings({
    "unchecked",
    "deprecation"
})
public class Parceler$$Parcels
    implements Repository<org.parceler.Parcels.ParcelableFactory>
{

    private final Map<Class, org.parceler.Parcels.ParcelableFactory> map$$0 = new HashMap<Class, org.parceler.Parcels.ParcelableFactory>();

    public Parceler$$Parcels() {
        map$$0 .put(User.class, new Parceler$$Parcels.User$$Parcelable$$0());
        map$$0 .put(Tweet.class, new Parceler$$Parcels.Tweet$$Parcelable$$0());
        map$$0 .put(Message.class, new Parceler$$Parcels.Message$$Parcelable$$0());
        map$$0 .put(Medium.class, new Parceler$$Parcels.Medium$$Parcelable$$0());
    }

    public Map<Class, org.parceler.Parcels.ParcelableFactory> get() {
        return map$$0;
    }

    private final static class Medium$$Parcelable$$0
        implements org.parceler.Parcels.ParcelableFactory<Medium>
    {


        @Override
        public Medium$$Parcelable buildParcelable(Medium input) {
            return new Medium$$Parcelable(input);
        }

    }

    private final static class Message$$Parcelable$$0
        implements org.parceler.Parcels.ParcelableFactory<Message>
    {


        @Override
        public Message$$Parcelable buildParcelable(Message input) {
            return new Message$$Parcelable(input);
        }

    }

    private final static class Tweet$$Parcelable$$0
        implements org.parceler.Parcels.ParcelableFactory<Tweet>
    {


        @Override
        public Tweet$$Parcelable buildParcelable(Tweet input) {
            return new Tweet$$Parcelable(input);
        }

    }

    private final static class User$$Parcelable$$0
        implements org.parceler.Parcels.ParcelableFactory<User>
    {


        @Override
        public User$$Parcelable buildParcelable(User input) {
            return new User$$Parcelable(input);
        }

    }

}
