
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
public class Message$$Parcelable
    implements Parcelable, ParcelWrapper<com.zekunwang.happytweets.models.Message>
{

    private com.zekunwang.happytweets.models.Message message$$0;
    @SuppressWarnings("UnusedDeclaration")
    public final static Message$$Parcelable.Creator$$1 CREATOR = new Message$$Parcelable.Creator$$1();

    public Message$$Parcelable(com.zekunwang.happytweets.models.Message message$$2) {
        message$$0 = message$$2;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel$$0, int flags) {
        write(message$$0, parcel$$0, flags, new HashSet<Integer>());
    }

    public static void write(com.zekunwang.happytweets.models.Message message$$1, android.os.Parcel parcel$$1, int flags$$0, Set<Integer> identitySet$$0) {
        int identity$$0 = System.identityHashCode(message$$1);
        parcel$$1 .writeInt(identity$$0);
        if (!identitySet$$0 .contains(identity$$0)) {
            identitySet$$0 .add(identity$$0);
            if (message$$1 == null) {
                parcel$$1 .writeInt(-1);
            } else {
                parcel$$1 .writeInt(1);
                parcel$$1 .writeString(message$$1 .createdAt);
                com.zekunwang.happytweets.models.User$$Parcelable.write(message$$1 .sender, parcel$$1, flags$$0, identitySet$$0);
                com.zekunwang.happytweets.models.User$$Parcelable.write(message$$1 .recipient, parcel$$1, flags$$0, identitySet$$0);
                parcel$$1 .writeString(message$$1 .mid);
                parcel$$1 .writeString(message$$1 .text);
            }
        }
    }

    @Override
    public int describeContents() {
        return  0;
    }

    @Override
    public com.zekunwang.happytweets.models.Message getParcel() {
        return message$$0;
    }

    public static com.zekunwang.happytweets.models.Message read(android.os.Parcel parcel$$3, Map<Integer, Object> identityMap$$0) {
        com.zekunwang.happytweets.models.Message message$$3;
        int identity$$1 = parcel$$3 .readInt();
        if (identityMap$$0 .containsKey(identity$$1)) {
            com.zekunwang.happytweets.models.Message message$$4 = ((com.zekunwang.happytweets.models.Message) identityMap$$0 .get(identity$$1));
            if ((message$$4 == null)&&(identity$$1 != 0)) {
                throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
            }
            return message$$4;
        }
        if (parcel$$3 .readInt() == -1) {
            message$$3 = null;
            identityMap$$0 .put(identity$$1, null);
        } else {
            com.zekunwang.happytweets.models.Message message$$5;
            identityMap$$0 .put(identity$$1, null);
            message$$5 = new com.zekunwang.happytweets.models.Message();
            identityMap$$0 .put(identity$$1, message$$5);
            message$$5 .createdAt = parcel$$3 .readString();
            com.zekunwang.happytweets.models.User user$$0 = com.zekunwang.happytweets.models.User$$Parcelable.read(parcel$$3, identityMap$$0);
            message$$5 .sender = user$$0;
            com.zekunwang.happytweets.models.User user$$1 = com.zekunwang.happytweets.models.User$$Parcelable.read(parcel$$3, identityMap$$0);
            message$$5 .recipient = user$$1;
            message$$5 .mid = parcel$$3 .readString();
            message$$5 .text = parcel$$3 .readString();
            message$$3 = message$$5;
        }
        return message$$3;
    }

    public final static class Creator$$1
        implements Creator<Message$$Parcelable>
    {


        @Override
        public Message$$Parcelable createFromParcel(android.os.Parcel parcel$$2) {
            return new Message$$Parcelable(read(parcel$$2, new HashMap<Integer, Object>()));
        }

        @Override
        public Message$$Parcelable[] newArray(int size) {
            return new Message$$Parcelable[size] ;
        }

    }

}
