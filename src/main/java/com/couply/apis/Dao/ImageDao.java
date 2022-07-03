package com.couply.apis.Dao;

import com.couply.apis.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageDao extends JpaRepository<Image,Integer> {
}
