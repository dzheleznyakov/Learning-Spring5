package zh.learn.spring5.recipeapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import zh.learn.spring5.recipeapp.domain.Category;
import zh.learn.spring5.recipeapp.domain.UnitOfMeasure;
import zh.learn.spring5.recipeapp.repositories.CategoryRepository;
import zh.learn.spring5.recipeapp.repositories.UnitOfMeasureRepository;

import java.util.Optional;

@Controller
public class IndexController {
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String getIndexPage() {
        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Pinch");

        System.out.println("Category Id is " + categoryOptional.get().getId());
        System.out.println("Unit of measure id is " + unitOfMeasureOptional.get().getId());

        return "index";
    }
}
