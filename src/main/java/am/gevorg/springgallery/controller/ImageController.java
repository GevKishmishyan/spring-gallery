package am.gevorg.springgallery.controller;

import am.gevorg.springgallery.model.Category;
import am.gevorg.springgallery.model.Image;
import am.gevorg.springgallery.service.CategoryService;
import am.gevorg.springgallery.service.ImageService;
import am.gevorg.springgallery.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;
    private final CategoryService categoryService;

    @PostMapping("/save")
    public String addCategory(@ModelAttribute Image image,
                              @RequestParam("image") MultipartFile file) throws IOException {
        imageService.save(image, file);
        return "redirect:/admin";
    }

    @GetMapping("/delete")
    public String deleteImage(@RequestParam("id") int id){
        imageService.deleteImage(id);
        return "redirect:/admin";
    }

    @GetMapping("/get")
    public String getImage(@RequestParam("id") int id, ModelMap modelMap){
        modelMap.addAttribute("imgById", imageService.getImage(id));
        modelMap.addAttribute("imgFolder", "E:\\Projects\\Spring\\spring-gallery\\uploads\\");
        modelMap.addAttribute("allCats", categoryService.allCatList());
        return "updateImage";
    }


}
