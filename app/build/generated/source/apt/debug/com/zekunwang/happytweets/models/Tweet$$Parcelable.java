
package com.zekunwang.happytweets.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.parceler.Generated;
import org.parceler.ParcelWrapper;
import org.parceler.ParcelerRuntimeException;

@Generated(value = "org.parceler.ParcelAnnotationProcessor", date = "2016-08-13T20:24-0500")
@SuppressWarnings({
    "unchecked",
    "deprecation"
})
public class Tweet$$Parcelable
    implements Parcelable, ParcelWrapper<com.zekunwang.happytweets.models.Tweet>
{

    private com.zekunwang.happytweets.models.Tweet tweet$$0;
    @SuppressWarnings("UnusedDeclaration")
    public final static Tweet$$Parcelable.Creator$$2 CREATOR = new Tweet$$Parcelable.Creator$$2();

    public Tweet$$Parcelable(com.zekunwang.happytweets.models.Tweet tweet$$2) {
        tweet$$0 = tweet$$2;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel$$0, int flags) {
        write(tweet$$0, parcel$$0, flags, new HashSet<Integer>());
    }

    public static void write(com.zekunwang.happytweets.models.Tweet tweet$$1, android.os.Parcel parcel$$1, int flags$$0, Set<Integer> identitySet$$0) {
        int identity$$0 = System.identityHashCode(tweet$$1);
        parcel$$1 .writeInt(identity$$0);
        if (!identitySet$$0 .contains(identity$$0)) {
            identitySet$$0 .add(identity$$0);
            if (tweet$$1 == null) {
                parcel$$1 .writeInt(-1);
            } else {
                parcel$$1 .writeInt(1);
                parcel$$1 .writeString(tweet$$1 .createdAt);
                parcel$$1 .writeString(tweet$$1 .inReplyToStatusId);
                com.zekunwang.happytweets.models.Tweet$$Parcelable.write(tweet$$1 .retweetedStatus, parcel$$1, flags$$0, identitySet$$0);
                if (tweet$$1 .media == null) {
                    parcel$$1 .writeInt(-1);
                } else {
                    parcel$$1 .writeInt(tweet$$1 .media.size());
                    for (com.zekunwang.happytweets.models.Medium medium$$0 : ((List<com.zekunwang.happytweets.models.Medium> ) tweet$$1 .media)) {
                        com.zekunwang.happytweets.models.Medium$$Parcelable.write(medium$$0, parcel$$1, flags$$0, identitySet$$0);
                    }
                }
                parcel$$1 .writeString(tweet$$1 .body);
                if (tweet$$1 .userMentions == null) {
                    parcel$$1 .writeInt(-1);
                } else {
                    parcel$$1 .writeInt(tweet$$1 .userMentions.length);
                    for (java.lang.String string$$0 : tweet$$1 .userMentions) {
                        parcel$$1 .writeString(string$$0);
                    }
                }
                com.zekunwang.happytweets.models.User$$Parcelable.write(tweet$$1 .user, parcel$$1, flags$$0, identitySet$$0);
                parcel$$1 .writeLong(tweet$$1 .tid);
                parcel$$1 .writeInt(tweet$$1 .retweetCount);
                parcel$$1 .writeInt(tweet$$1 .favoriteCount);
                parcel$$1 .writeInt((tweet$$1 .favorited? 1 : 0));
                parcel$$1 .writeInt((tweet$$1 .retweeted? 1 : 0));
            }
        }
    }

    @Override
    public int describeContents() {
        return  0;
    }

    @Override
    public com.zekunwang.happytweets.models.Tweet getParcel() {
        return tweet$$0;
    }

    public static com.zekunwang.happytweets.models.Tweet read(android.os.Parcel parcel$$3, Map<Integer, Object> identityMap$$0) {
        com.zekunwang.happytweets.models.Tweet tweet$$3;
        int identity$$1 = parcel$$3 .readInt();
        if (identityMap$$0 .containsKey(identity$$1)) {
            com.zekunwang.happytweets.models.Tweet tweet$$4 = ((com.zekunwang.happytweets.models.Tweet) identityMap$$0 .get(identity$$1));
            if ((tweet$$4 == null)&&(identity$$1 != 0)) {
                throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
            }
            return tweet$$4;
        }
        if (parcel$$3 .readInt() == -1) {
            tweet$$3 = null;
            identityMap$$0 .put(identity$$1, null);
        } else {
            com.zekunwang.happytweets.models.Tweet tweet$$5;
            identityMap$$0 .put(identity$$1, null);
            tweet$$5 = new com.zekunwang.happytweets.models.Tweet();
            identityMap$$0 .put(identity$$1, tweet$$5);
            tweet$$5 .createdAt = parcel$$3 .readString();
            tweet$$5 .inReplyToStatusId = parcel$$3 .readString();
            com.zekunwang.happytweets.models.Tweet tweet$$6 = com.zekunwang.happytweets.models.Tweet$$Parcelable.read(parcel$$3, identityMap$$0);
            tweet$$5 .retweetedStatus = tweet$$6;
            int int$$0 = parcel$$3 .readInt();
            ArrayList<com.zekunwang.happytweets.models.Medium> list$$0;
            if (int$$0 < 0) {
                list$$0 = null;
            } else {
                list$$0 = new ArrayList<com.zekunwang.happytweets.models.Medium>(int$$0);
                for (int int$$1 = 0; (int$$1 <int$$0); int$$1 ++) {
                    com.zekunwang.happytweets.models.Medium medium$$1 = com.zekunwang.happytweets.models.Medium$$Parcelable.read(parcel$$3, identityMap$$0);
                    list$$0 .add(medium$$1);
                }
            }
            tweet$$5 .media = list$$0;
            tweet$$5 .body = parcel$$3 .readString();
            int int$$2 = parcel$$3 .readInt();
            java.lang.String[] string$$1;
            if (int$$2 < 0) {
                string$$1 = null;
            } else {
                string$$1 = new java.lang.String[int$$2 ] ;
                for (int int$$3 = 0; (int$$3 <int$$2); int$$3 ++) {
                    string$$1 [int$$3 ] = parcel$$3 .readString();
                }
            }
            tweet$$5 .userMentions = string$$1;
            User user$$0 = com.zekunwang.happytweets.models.User$$Parcelable.read(parcel$$3, identityMap$$0);
            tweet$$5 .user = user$$0;
            tweet$$5 .tid = parcel$$3 .readLong();
            tweet$$5 .retweetCount = parcel$$3 .readInt();
            tweet$$5 .favoriteCount = parcel$$3 .readInt();
            tweet$$5 .favorited = (parcel$$3 .readInt() == 1);
            tweet$$5 .retweeted = (parcel$$3 .readInt() == 1);
            tweet$$3 = tweet$$5;
        }
        return tweet$$3;
    }

    public final static class Creator$$2
        implements Creator<Tweet$$Parcelable>
    {


        @Override
        public Tweet$$Parcelable createFromParcel(android.os.Parcel parcel$$2) {
            return new Tweet$$Parcelable(read(parcel$$2, new HashMap<Integer, Object>()));
        }

        @Override
        public Tweet$$Parcelable[] newArray(int size) {
            return new Tweet$$Parcelable[size] ;
        }

    }

}
