package vijay.sprintboot.springrestapp.services;

import org.springframework.stereotype.Service;
import vijay.sprintboot.springrestapp.mapper.CategoryMapper;
import vijay.sprintboot.springrestapp.model.CategoryDTO;
import vijay.sprintboot.springrestapp.repositories.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;
    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::categoryToCategoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        return categoryMapper.categoryToCategoryDTO(categoryRepository.findByName(name));
    }
}
