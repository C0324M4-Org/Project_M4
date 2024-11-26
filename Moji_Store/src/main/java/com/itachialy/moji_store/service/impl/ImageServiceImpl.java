package com.itachialy.moji_store.service.impl;

import com.itachialy.moji_store.model.Image;
import com.itachialy.moji_store.repository.IImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ImageServiceImpl {
    @Autowired
    private final IImageRepository iImageRepository;

    public ImageServiceImpl(IImageRepository iImageRepository) {
        this.iImageRepository = iImageRepository;
    }

    public List<Image> getAllProductImages() {
        return iImageRepository.findAll();
    }

    public void addProductImage(Image productImage) {
        iImageRepository.save(productImage);
    }

}
