package com.laioffer.eventreporter;

/**
 * Created by gy on 9/8/18.
 */

public class Like {
    private String LikeId;
    private String UserId;
    private String eventId;

    public String getLikeId() {
        return LikeId;
    }

    public void setLikeId(String likeId) {
        LikeId = likeId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

}
