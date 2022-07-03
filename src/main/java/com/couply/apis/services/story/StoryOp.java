package com.couply.apis.services.story;

import com.couply.apis.entity.Story;

import java.util.List;

public interface StoryOp {
    public List<Story> getStories();
    public Story getStory(Integer id);
    public Story addStory(Story story);
}
