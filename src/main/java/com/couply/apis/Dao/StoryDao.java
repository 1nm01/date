package com.couply.apis.Dao;

import com.couply.apis.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryDao extends JpaRepository<Story,Integer> {
}
