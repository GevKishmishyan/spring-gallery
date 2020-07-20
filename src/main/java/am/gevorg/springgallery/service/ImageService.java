package am.gevorg.springgallery.service;

import am.gevorg.springgallery.model.Category;
import am.gevorg.springgallery.model.Image;
import am.gevorg.springgallery.repository.CategoryRepository;
import am.gevorg.springgallery.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {


    @Value("${file.upload.dir}")
    private String uploadDir;

    private final ImageRepository imageRepository;

    public void save(Image image, MultipartFile file) throws IOException {
        if (image.getId() == 0) {
            String name = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File upImage = new File(uploadDir, name);
            file.transferTo(upImage);
            image.setPicUrl(name);
        } else {
            Image updatedImg = imageRepository.getOne(image.getId());
            if (file.isEmpty()){
                image.setPicUrl(updatedImg.getPicUrl());
            } else {
                String name = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                File upImage = new File(uploadDir, name);
                file.transferTo(upImage);
                image.setPicUrl(name);
            }
        }

        imageRepository.save(image);
    }

    public List<Image> imagesByCategory(int id) {
        return imageRepository.findAllByCategory_Id(id);
    }

    public void deleteImage(int id) {
        imageRepository.deleteById(id);
    }

    public Image getImage(int id) {
        return imageRepository.getOne(id);
    }

    public List<Image> allImgList() {
        return imageRepository.findAll();
    }
}
