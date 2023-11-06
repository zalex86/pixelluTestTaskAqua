package com.pixellu.pages;

import com.pixellu.helpers.PageObjectUtils;

// page_url = https://www.pixellu.com/smartslides/
public class PixelluSmartslidesPage extends MainPage{
    public PixelluSmartslidesPage() {
        PageObjectUtils.waitPageIsPresentByURL("https://www.pixellu.com/smartslides/");
    }
}