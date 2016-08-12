
package com.zekunwang.happytweets.models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.parceler.Generated;
import org.parceler.ParcelWrapper;
import org.parceler.ParcelerRuntimeException;

@Generated(value = "org.parceler.ParcelAnnotationProcessor", date = "2016-08-11T23:19-0500")
@SuppressWarnings({
    "unchecked",
    "deprecation"
})
public class Medium$$Parcelable
    implements Parcelable, ParcelWrapper<com.zekunwang.happytweets.models.Medium>
{

    private com.zekunwang.happytweets.models.Medium medium$$0;
    @SuppressWarnings("UnusedDeclaration")
    public final static Medium$$Parcelable.Creator$$0 CREATOR = new Medium$$Parcelable.Creator$$0();

    public Medium$$Parcelable(com.zekunwang.happytweets.models.Medium medium$$2) {
        medium$$0 = medium$$2;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel$$0, int flags) {
        write(medium$$0, parcel$$0, flags, new HashSet<Integer>());
    }

    public static void write(com.zekunwang.happytweets.models.Medium medium$$1, android.os.Parcel parcel$$1, int flags$$0, Set<Integer> identitySet$$0) {
        int identity$$0 = System.identityHashCode(medium$$1);
        parcel$$1 .writeInt(identity$$0);
        if (!identitySet$$0 .contains(identity$$0)) {
            identitySet$$0 .add(identity$$0);
            if (medium$$1 == null) {
                parcel$$1 .writeInt(-1);
            } else {
                parcel$$1 .writeInt(1);
                parcel$$1 .writeString(medium$$1 .mediaUrl);
                com.zekunwang.happytweets.models.Tweet$$Parcelable.write(medium$$1 .tweet, parcel$$1, flags$$0, identitySet$$0);
                parcel$$1 .writeString(medium$$1 .video);
                parcel$$1 .writeString(medium$$1 .type);
                parcel$$1 .writeString(medium$$1 .url);
                parcel$$1 .writeDouble(medium$$1 .ratio);
            }
        }
    }

    @Override
    public int describeContents() {
        return  0;
    }

    @Override
    public com.zekunwang.happytweets.models.Medium getParcel() {
        return medium$$0;
    }

    public static com.zekunwang.happytweets.models.Medium read(android.os.Parcel parcel$$3, Map<Integer, Object> identityMap$$0) {
        com.zekunwang.happytweets.models.Medium medium$$3;
        int identity$$1 = parcel$$3 .readInt();
        if (identityMap$$0 .containsKey(identity$$1)) {
            com.zekunwang.happytweets.models.Medium medium$$4 = ((com.zekunwang.happytweets.models.Medium) identityMap$$0 .get(identity$$1));
            if ((medium$$4 == null)&&(identity$$1 != 0)) {
                throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
            }
            return medium$$4;
        }
        if (parcel$$3 .readInt() == -1) {
            medium$$3 = null;
            identityMap$$0 .put(identity$$1, null);
        } else {
            com.zekunwang.happytweets.models.Medium medium$$5;
            identityMap$$0 .put(identity$$1, null);
            medium$$5 = new com.zekunwang.happytweets.models.Medium();
            identityMap$$0 .put(identity$$1, medium$$5);
            medium$$5 .mediaUrl = parcel$$3 .readString();
            Tweet tweet$$0 = com.zekunwang.happytweets.models.Tweet$$Parcelable.read(parcel$$3, identityMap$$0);
            medium$$5 .tweet = tweet$$0;
            medium$$5 .video = parcel$$3 .readString();
            medium$$5 .type = parcel$$3 .readString();
            medium$$5 .url = parcel$$3 .readString();
            medium$$5 .ratio = parcel$$3 .readDouble();
            medium$$3 = medium$$5;
        }
        return medium$$3;
    }

    public final static class Creator$$0
        implements Creator<Medium$$Parcelable>
    {


        @Override
        public Medium$$Parcelable createFromParcel(android.os.Parcel parcel$$2) {
            return new Medium$$Parcelable(read(parcel$$2, new HashMap<Integer, Object>()));
        }

        @Override
        public Medium$$Parcelable[] newArray(int size) {
            return new Medium$$Parcelable[size] ;
        }

    }

}
