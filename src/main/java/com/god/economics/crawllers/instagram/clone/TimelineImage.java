package com.god.economics.crawllers.instagram.clone;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * created By gOD on 9/6/2020 5:05 PM
 */
@Setter(value = AccessLevel.PUBLIC)
@Getter
@Accessors(chain = true)
public class TimelineImage {
    private String displayUrl;
    private String caption;

    public TimelineImage(String displayUrl) {
        this.displayUrl = displayUrl;
    }

    public TimelineImage(String displayUrl, String caption) {
        this.displayUrl = displayUrl;
        this.caption = caption;
    }
}
