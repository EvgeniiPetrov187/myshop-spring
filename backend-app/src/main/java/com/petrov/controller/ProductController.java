package com.petrov.controller;

import com.petrov.controller.dto.ProductDto;
import com.petrov.service.CategoryService;
import com.petrov.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private final ProductService productService;

    @Autowired
    private final CategoryService categoryService;


    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public String findAll(Model model) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        return "products";
    }

    @GetMapping("/new")
    public String newProductForm(Model model) {
        model.addAttribute("productDto", new ProductDto());
        model.addAttribute("categories", categoryService.findAll());
        return "add";
    }

    @GetMapping("/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model) {
        model.addAttribute("productDto", productService.findById(id));
        model.addAttribute("categories", categoryService.findAll());
        return "add";
    }

    @PostMapping
    public String update(ProductDto productDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            return "add";
        }
        productService.saveOrUpdate(productDto);
        return "redirect:/products/list";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        model.addAttribute("productDto", productService.findById(id));
        productService.deleteById(id);
        return "redirect:/products/list";
    }
}
