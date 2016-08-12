package com.zekunwang.happytweets.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

// Parse JSON, Pass data, Encapsulate state logic
@Parcel(analyze={Message.class})   // add Parceler to ignore Model
public class Message {

    public String mid;
    public String text;
    public String createdAt;
    public User sender;
    public User recipient;

    public String getMid() {
        return mid;
    }

    public String getText() {
        return text;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public User getSender() {
        return sender;
    }

    public User getRecipient() {
        return recipient;
    }

    // Deserialize JSONObject and build Message object
    // Twitter.fromJSONObject("{...}") => <Message>
    public static Message fromJSONObject(JSONObject jsonObject) {
        Message message = new Message();

        try {
            message.mid = jsonObject.getString("id_str");
            message.text = jsonObject.getString("text");
            message.createdAt = jsonObject.getString("created_at");
            message.sender = User.findOrCreateFromJson(jsonObject.getJSONObject("sender"));
            message.recipient = User.findOrCreateFromJson(jsonObject.getJSONObject("recipient"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return message;
    }

    // Deserialize JSONArray and build Message objects
    // Twitter.fromJSONArray("{...}") => ArrayList<Message>
    public static ArrayList<Message> fromJSONArray(JSONArray jsonArray) {
        ArrayList<Message> messages = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Message message = fromJSONObject(jsonObject);
                if (message != null) {
                    messages.add(message);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return messages;
    }
}
