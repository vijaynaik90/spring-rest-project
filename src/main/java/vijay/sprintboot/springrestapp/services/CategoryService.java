package vijay.sprintboot.springrestapp.services;

import vijay.sprintboot.springrestapp.model.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryByName(String name);
}
