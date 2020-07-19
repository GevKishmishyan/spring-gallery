package am.gevorg.springgallery.controller;

import am.gevorg.springgallery.model.Image;
import am.gevorg.springgallery.service.CategoryService;
import am.gevorg.springgallery.service.ImageService;
import am.gevorg.springgallery.service.MainService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    @Value("${file.upload.dir}")
    private String uploadDir;

    private final CategoryService categoryService;
    private final ImageService imageService;

    @GetMapping("/")
    public String home(ModelMap modelMap){
        modelMap.addAttribute("allCats", categoryService.allCatList());
        return "index";
    }

    @GetMapping("/admin")
    public String admin(ModelMap modelMap){
        modelMap.addAttribute("allCats", categoryService.allCatList());
        modelMap.addAttribute("allImgs", imageService.allImgList());
        return "admin";
    }

    @GetMapping("/images")
    public String imagesByCat(@RequestParam("id") int id, ModelMap modelMap){
        List<Image> imgByCategories =  imageService.imagesByCategory(id);
        modelMap.addAttribute("imgByCat", imgByCategories);
        return "images";
    }

    @GetMapping(
            value = "/image",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody
    byte[] getImageWithMediaType(@RequestParam("name") String imageName) throws IOException {

        InputStream in = new FileInputStream(uploadDir + File.separator + imageName);
        return IOUtils.toByteArray(in);
    }

}
