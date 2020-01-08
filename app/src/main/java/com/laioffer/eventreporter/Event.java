package com.laioffer.eventreporter;

/**
 * Created by gy on 9/4/18.
 */

public class Event {
    /**
     * All data for a event.
     */
    private String title;
    private String address;
    private String description;
    private int like;
    private String id;
    private long time;
    private String username;
    private String imgUri;
    private int CommentNumber;
    private double latitude;
    private double longitude;

    /**
     * Constructor
     */
    public Event() {}
    public Event(String title, String address, String description) {
        this.title = title;
        this.address = address;
        this.description = description;
    }

    /**
     * Getters for private attributes of Event class.
     */
    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public int getLike() {
        return like;
    }

    public String getId() {
        return id;
    }

    public long getTime() {
        return time;
    }

    public String getUsername() {
        return username;
    }

    public String getImgUri() {
        return imgUri;
    }

    public int getCommentNumber() {
        return CommentNumber;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    /**
     * Setters for private attributes of Event class.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }

    public void setCommentNumber(int commentNumber) {
        CommentNumber = commentNumber;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
