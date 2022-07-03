package com.couply.apis.services.image;

import com.couply.apis.Dao.ImageDao;
import com.couply.apis.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class imageOpImpl implements ImageOp{

    @Autowired
    ImageDao imageDao;

    @Override
    public byte[] getImage(Integer id) {
        Image image=imageDao.getById(id);
        return image.getImage();
    }

    @Override
    public Image saveImage(Image image) {
        imageDao.save(image);
        return image;
    }
}
