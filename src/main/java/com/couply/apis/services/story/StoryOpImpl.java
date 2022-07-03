package com.couply.apis.services.story;

import com.couply.apis.Dao.StoryDao;
import com.couply.apis.entity.Story;
import com.couply.apis.services.story.StoryOp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StoryOpImpl implements StoryOp {
    @Autowired
    private StoryDao stories;
    @Override
    public List<Story> getStories() {
        return stories.findAll();
    }

    @Override
    public Story getStory(Integer id) {
        return stories.getById(id);
    }

    @Override
    public Story addStory(Story story) {
        return stories.save(story);
    }
}
