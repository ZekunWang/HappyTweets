package com.zekunwang.happytweets.activities;


import com.zekunwang.happytweets.models.Message;
import com.zekunwang.happytweets.models.Tweet;
import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.zekunwang.happytweets.models.User;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/*
 * 
 * This is the object responsible for communicating with a REST API. 
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes: 
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 * 
 * NOTE: You may want to rename this object based on the service i.e TwitterClient or FlickrClient
 * 
 */
public class TwitterClient extends OAuthBaseClient {
    public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class; // Change this
    public static final String REST_URL = "https://api.twitter.com/1.1/"; // Change this, base API URL
    public static final String REST_CONSUMER_KEY = "pTdf9vNU69mwzQU0nxI6xI9hz";       // Change this
    public static final String REST_CONSUMER_SECRET = "Nb17paJooVPCpfnpdJEO2oQ2pxBYsluINpB8r77O7uXrhMr4LM"; // Change this
    public static final String REST_CALLBACK_URL = "oauth://SimpleTweets"; // Change this (here and in manifest)

    public TwitterClient(Context context) {
        super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
    }

    // METHOD == ENDPOINT
    // HomeTimeline - Get us the home timeline
    //  GET statuses/home_timeline.json
    //      count=25
    //      since_id=1 (newer than 1)
    //      max_id=100 (older or equal to 100)
    public void getHomeTimeline(Long since, Long max, AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("statuses/home_timeline.json");
        // Specify params
        RequestParams params = new RequestParams();
        params.put("count", 25);
        if (since != null) {
            params.put("since_id", since);
        } else if (max != null) {
            params.put("max_id", max);
        }
        // Execute the request
        getClient().get(apiUrl, params, handler);
    }

    // MentionsTimeline - Get us the mentions timeline
    //  GET statuses/mentions_timeline.json
    //      count=25
    //      since_id=1 (newer than 1)
    //      max_id=100 (older or equal to 100)
    public void getMentionsTimeline(Long since, Long max, AsyncHttpResponseHandler handler) {
        Log.i("DEBUG", "mentions timeline...");
        String apiUrl = getApiUrl("statuses/mentions_timeline.json");
        // Specify params
        RequestParams params = new RequestParams();
        params.put("count", 25);
        if (since != null) {
            params.put("since_id", since);
        } else if (max != null) {
            params.put("max_id", max);
        }
        // Execute the request
        getClient().get(apiUrl, params, handler);
    }

    // UserTimeline - Get us the user timeline
    //  GET statuses/home_timeline.json
    //      count=25
    //      since_id=1 (newer than 1)
    //      max_id=100 (older or equal to 100)
    public void getUserTimeline(Long uid, Long since, Long max, AsyncHttpResponseHandler handler) {
        if (uid == null) {
            Log.i("DEBUG", "Invalid user id...");
            return;
        }
        String apiUrl = getApiUrl("statuses/user_timeline.json");
        // Specify params
        RequestParams params = new RequestParams();
        params.put("user_id", uid);
        params.put("count", 25);
        if (since != null) {
            params.put("since_id", since);
        } else if (max != null) {
            params.put("max_id", max);
        }
        // Execute the request
        getClient().get(apiUrl, params, handler);
    }

    // FavoritesTimeline - Get us the favorites timeline
    //  GET favorites/list.json
    //      count=25
    //      since_id=1 (newer than 1)
    //      max_id=100 (older or equal to 100)
    public void getFavoritesTimeline(Long uid, Long since, Long max, AsyncHttpResponseHandler handler) {
        if (uid == null) {
            Log.i("DEBUG", "Invalid user id...");
            return;
        }
        String apiUrl = getApiUrl("favorites/list.json");
        // Specify params
        RequestParams params = new RequestParams();
        params.put("user_id", uid);
        params.put("count", 25);
        if (since != null) {
            params.put("since_id", since);
        } else if (max != null) {
            params.put("max_id", max);
        }
        // Execute the request
        getClient().get(apiUrl, params, handler);
    }

    // Get Account Information
    public void getAccount(AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("account/verify_credentials.json");
        getClient().get(apiUrl, handler);
    }

    // Compose Tweet
    // POST statuses/update.json
    //      status="A new tweet"
    //      lat=37.7821120598956
    //      long=-122.400612831116
    public void composeTweet(Tweet tweet, AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("statuses/update.json");
        RequestParams params = new RequestParams();

        params.put("status", tweet.getBody());
        params.put("in_reply_to_status_id", tweet.getInReplyToStatusId());

        // Execute the request
        getClient().post(apiUrl, params, handler);
    }

    // Set Favorite For Tweet
    public void setFavorite(Tweet tweet, AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("favorites/create.json");
        RequestParams params = new RequestParams();

        params.put("id", String.valueOf(tweet.getTid()));

        // Execute the request
        getClient().post(apiUrl, params, handler);
    }

    // Unset Favorite For Tweet
    public void unSetFavorite(Tweet tweet, AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("favorites/destroy.json");
        RequestParams params = new RequestParams();

        params.put("id", String.valueOf(tweet.getTid()));

        // Execute the request
        getClient().post(apiUrl, params, handler);
    }

    // Set Retweet For Tweet
    public void setRetweet(Tweet tweet, AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("statuses/retweet/" + tweet.getTid() + ".json");

        // Execute the request
        getClient().post(apiUrl, handler);
    }

    // Set Retweet For Tweet
    public void unSetRetweet(Tweet tweet, AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("statuses/unretweet/" + tweet.getTid() + ".json");

        // Execute the request
        getClient().post(apiUrl, handler);
    }


    // Set Follow For User
    public void setFollow(User user, AsyncHttpResponseHandler handler) {
        if (user == null) {
            return;
        }

        String apiUrl = getApiUrl("friendships/create.json");
        RequestParams params = new RequestParams();

        params.put("user_id", String.valueOf(user.getUid()));

        // Execute the request
        getClient().post(apiUrl, params, handler);
    }

    // Unset Follow For User
    public void unSetFollow(User user, AsyncHttpResponseHandler handler) {
        if (user == null) {
            return;
        }

        String apiUrl = getApiUrl("friendships/destroy.json");
        RequestParams params = new RequestParams();

        params.put("user_id", String.valueOf(user.getUid()));

        // Execute the request
        getClient().post(apiUrl, params, handler);
    }

    // Get followers ids of a user
    public void getFollowersList(User user, AsyncHttpResponseHandler handler) {
        if (user == null) {
            return;
        }

        String apiUrl = getApiUrl("followers/ids.json");
        RequestParams params = new RequestParams();

        params.put("user_id", String.valueOf(user.getUid()));

        // Execute the request
        getClient().get(apiUrl, params, handler);
    }

    // Get friends ids of a user
    public void getFriendsList(User user, AsyncHttpResponseHandler handler) {
        if (user == null) {
            return;
        }

        String apiUrl = getApiUrl("friends/ids.json");
        RequestParams params = new RequestParams();

        params.put("user_id", String.valueOf(user.getUid()));

        // Execute the request
        getClient().get(apiUrl, params, handler);
    }

    // Get users info from ids
    public void getUsersList(List<Long> ids, AsyncHttpResponseHandler handler) {
        if (ids == null) {
            return;
        }

        String apiUrl = getApiUrl("users/lookup.json");
        RequestParams params = new RequestParams();

        String strIds = android.text.TextUtils.join(",", ids);
        params.put("user_id", strIds);

        // Execute the request
        getClient().get(apiUrl, params, handler);
    }

    // Get Tweet results from query
    public void getSearchList(String query, int mode , Long since, Long max, AsyncHttpResponseHandler handler) {
        if (query == null || query.length() == 0) {
            return;
        }

        String apiUrl = getApiUrl("search/tweets.json");
        RequestParams params = new RequestParams();

        params.put("q", query);
        params.put("result_type", mode == SearchActivity.TOP_TWEETS ? "popular" : "recent");
        params.put("count", 25);
        if (since != null) {
            params.put("since_id", since);
        } else if (max != null) {
            params.put("max_id", max);
        }

        // Execute the request
        getClient().get(apiUrl, params, handler);
    }

    // Get Message sent by authenticating user
    public void getSentMessages(Long since, Long max, AsyncHttpResponseHandler handler) {

        String apiUrl = getApiUrl("direct_messages/sent.json");
        RequestParams params = new RequestParams();

        params.put("count", 25);
        if (since != null) {
            params.put("since_id", since);
        } else if (max != null) {
            params.put("max_id", max);
        }

        // Execute the request
        getClient().get(apiUrl, params, handler);
    }

    // Get Message received by authenticating user
    public void getReceivedMessages(Long since, Long max, AsyncHttpResponseHandler handler) {

        String apiUrl = getApiUrl("direct_messages.json");
        RequestParams params = new RequestParams();

        params.put("count", 25);
        if (since != null) {
            params.put("since_id", since);
        } else if (max != null) {
            params.put("max_id", max);
        }

        // Execute the request
        getClient().get(apiUrl, params, handler);
    }

    // Pose new Message
    public void composeNewMessages(Message message, AsyncHttpResponseHandler handler) {

        String apiUrl = getApiUrl("direct_messages/new.json");
        RequestParams params = new RequestParams();

        params.put("user_id", "" + message.recipient.getUid());
        params.put("text", message.getText());

        // Execute the request
        getClient().post(apiUrl, params, handler);
    }

	/* 1. Define the endpoint URL with getApiUrl and pass a relative path to the endpoint
	 * 	  i.e getApiUrl("statuses/home_timeline.json");
	 * 2. Define the parameters to pass to the request (query or body)
	 *    i.e RequestParams params = new RequestParams("foo", "bar");
	 * 3. Define the request method and make a call to the client
	 *    i.e client.get(apiUrl, params, handler);
	 *    i.e client.post(apiUrl, params, handler);
	 */
}