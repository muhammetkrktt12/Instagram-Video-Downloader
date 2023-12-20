package com.korkutata.instagramvideosaver.model;

public class ModelClass {

    String video_url;
    String display_url;

    //Constructor
    public ModelClass(String video_url, String display_url) {

        this.video_url = video_url;
        this.display_url = display_url;

    }

    //Getter-Setter
    public String getVideo_url() {

        return video_url;
    }

    public void setVideo_url(String video_url) {

        this.video_url = video_url;
    }

    public String getDisplay_url() {

        return display_url;
    }

    public void setDisplay_url(String display_url) {

        this.display_url = display_url;
    }

}
