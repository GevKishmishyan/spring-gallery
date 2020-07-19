package am.gevorg.springgallery.controller;


import am.gevorg.springgallery.model.Category;
import am.gevorg.springgallery.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryContoller {

    private final CategoryService categoryService;

    @PostMapping("/save")
    public String addCategory(@ModelAttribute Category category,
                              @RequestParam("image") MultipartFile file) throws IOException {
        categoryService.save(category, file);
        return "redirect:/admin";
    }

    @GetMapping("/delete")
    public String deleteCategory(@RequestParam("id") int id){
        categoryService.deleteCategory(id);
        return "redirect:/admin";
    }

    @GetMapping("/get")
    public String getImage(@RequestParam("id") int id, ModelMap modelMap){
        modelMap.addAttribute("catById", categoryService.getCategory(id));
        modelMap.addAttribute("imgFolder", "E:\\Projects\\Spring\\spring-gallery\\uploads\\");
        return "updateCategory";
    }



}
