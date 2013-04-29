package com.vijayrc.octobox.domain;

import java.util.ArrayList;
import java.util.List;

public class Topic {

    private String name;

    private List<Tag> tags = new ArrayList<Tag>();

    public Topic(String name) {
        this.name = name;
    }

    public Topic add(Tag tag){
        tags.add(tag);
        return this;
    }

}
