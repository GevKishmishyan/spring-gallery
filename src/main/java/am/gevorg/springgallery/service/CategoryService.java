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
public class CategoryService {

    @Value("${file.upload.dir}")
    private String uploadDir;

    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;

    public void save(Category category, MultipartFile file) throws IOException {
        if (category.getId() == 0) {
            String name = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File upImage = new File(uploadDir, name);
            file.transferTo(upImage);
            category.setPicUrl(name);
        } else {
            Category updatedCat = categoryRepository.getOne(category.getId());
            if (file.isEmpty()){
                category.setPicUrl(updatedCat.getPicUrl());
            } else {
                String name = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                File upImage = new File(uploadDir, name);
                file.transferTo(upImage);
                category.setPicUrl(name);
            }
        }

        categoryRepository.save(category);

    }

    public void deleteCategory(int id){
        List<Image> imagesByCat = imageRepository.findAllByCategory_Id(id);
        imageRepository.deleteAll(imagesByCat);
        categoryRepository.deleteById(id);
    }

    public List<Category> allCatList() {
        return categoryRepository.findAll();
    }

    public Category getCategory(int id) {
        return categoryRepository.getOne(id);
    }

}
