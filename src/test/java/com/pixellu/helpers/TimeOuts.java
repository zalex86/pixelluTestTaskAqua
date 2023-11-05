package com.pixellu.helpers;

import java.time.Duration;

public enum TimeOuts {
    ELEMENT_LOAD_TIMEOUT_S(Duration.ofSeconds(30)),
    ELEMENT_LOAD_TIMEOUT_MS(Duration.ofMillis(20000)),
    ELEMENT_LOAD_VALUE_TIMEOUT_MS(Duration.ofMillis(30000)),
    PAGE_LOAD_TIMEOUT_S(Duration.ofSeconds(15)),
    PAGE_LOAD_TIMEOUT_MS(Duration.ofMillis(15000)),
    ELEMENT_INVISIBILITY_TIMEOUT_MS(Duration.ofMillis(20000)),
    LIST_LOAD_TIMEOUT_S(Duration.ofSeconds(40)),
    FILE_LOAD_TIMEOUT_MS(Duration.ofMillis(20000)),
    WAIT_API_MS(Duration.ofMillis(120000)),
    WAIT_NEW_MESSAGE_S(Duration.ofSeconds(120)),
    WAIT_NEW_MESSAGE_MS(Duration.ofMillis(120000)),
    AJAX_LOADER_MS(Duration.ofMillis(30000)),
    SVG_LOADING_MS(Duration.ofMillis(3000)),
    VIDEO_LOADING_MS(Duration.ofMillis(60000));

    private final Duration value;

    TimeOuts(Duration value){
        this.value = value;
    }

    public Duration getValue(){
        return value;
    }
}
