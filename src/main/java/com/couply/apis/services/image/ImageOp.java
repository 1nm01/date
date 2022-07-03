package com.couply.apis.services.image;

import com.couply.apis.Dao.ImageDao;
import com.couply.apis.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;

public interface ImageOp {

    public byte[] getImage(Integer id);
    public Image saveImage(Image image);

}
