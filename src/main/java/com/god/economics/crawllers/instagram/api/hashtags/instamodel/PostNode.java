package com.god.economics.crawllers.instagram.api.hashtags.instamodel;

import java.util.List;

public class PostNode {
    public Owner owner;
    public EdgeMediaToComment edge_media_to_comment;
    public String display_url;
    public String thumbnail_src;
    public int taken_at_timestamp;
    public String accessibility_caption;
    public String __typename;
    public String shortcode;
    public EdgeLikedBy edge_liked_by;
    public boolean is_video;
    public String id;
    public EdgeMediaToCaption edge_media_to_caption;
    public List<ThumbnailResource> thumbnail_resources;
    public Dimensions dimensions;
    public EdgeMediaPreviewLike edge_media_preview_like;

    static enum MediaType{
        GraphImage,GraphSidecar

    }
}