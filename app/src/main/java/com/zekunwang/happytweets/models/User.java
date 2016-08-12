package com.zekunwang.happytweets.models;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.activeandroid.query.Update;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Table(name = "users")
@Parcel(analyze={User.class})   // add Parceler to ignore Model
public class User extends Model {

    @Column(name = "name")
    public String name;
    @Column(name = "uid", unique = true)
    public long uid;
    @Column(name = "screen_name")
    public String screenName;
    @Column(name = "profile_image_url")
    public String profileImageUrl;
    @Column(name = "profile_banner_url")
    public String profileBannerUrl;
    @Column(name = "friends_count")
    public long friends;
    @Column(name = "followers_count")
    public long followers;
    @Column(name = "following")
    public boolean following;
    @Column(name = "description")
    public String description;

    public User() {
        super();
    }

    public String getName() {
        return name;
    }

    public long getUid() {
        return uid;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getProfileBannerUrl() {
        return profileBannerUrl;
    }

    public long getFriends() {
        return friends;
    }

    public long getFollowers() {
        return followers;
    }

    public boolean isFollowing() {
        return following;
    }

    public String getDescription() {
        return description;
    }

    public void setFollowing(boolean following) {
        this.following = following;
        update(this);
    }

    // Finds existing user based on uid or creates new user and returns
    public static User findOrCreateFromJson(JSONObject json) {
        long rId = 0; // get just the remote id
        User user = null;

        try {
            rId = json.getLong("id");
            // Search for duplicate
            user = new Select().from(User.class).where("uid = ?", rId).executeSingle();
            if (user == null) {
                // create and return new user
                user = User.fromJSONObject(json);
                user.save();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return user;
    }

    /// Deserialize JSON => <User>
    public static User fromJSONObject(JSONObject jsonObject) {
        User user = new User();

        try {
            user.name = jsonObject.getString("name");
            user.uid = jsonObject.getLong("id");
            user.screenName = jsonObject.getString("screen_name");
            user.profileImageUrl = jsonObject.getString("profile_image_url");
            if (jsonObject.has("profile_banner_url")) {
                user.profileBannerUrl = jsonObject.getString("profile_banner_url");
            }
            user.friends = jsonObject.getLong("friends_count");
            user.followers = jsonObject.getLong("followers_count");
            user.following = jsonObject.getBoolean("following");
            user.description = jsonObject.getString("description");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return user;
    }

    /// Deserialize JSON => <User>
    public static List<User> fromJSONArray(JSONArray jsonArray) {
        List<User> users = new ArrayList<>();

        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                User user = User.fromJSONObject(jsonArray.getJSONObject(i));
                if (user != null) {
                    users.add(user);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return users;
    }

    /// Deserialize JSON => <User>
    public static List<Long> fromIdJSONArray(JSONArray jsonArray, int begin, int count) {
        List<Long> ids = new ArrayList<>();

        try {
            // Avoid index out of bound
            int max = Math.min(begin + count, jsonArray.length());
            
            for (int i = begin; i < max; i++) {
                ids.add(jsonArray.getLong(i));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return ids;
    }

    public static void update(User newFollow) {
        new Update(User.class)
            // For now, only undates parameters touched by developed funtionalities
            .set("friends_count = ?, followers_count = ?, following = ?", newFollow.getFriends(), newFollow.getFollowers(), newFollow.isFollowing())
            .where("uid = ?", newFollow.getUid())
            .execute();
    }
}
