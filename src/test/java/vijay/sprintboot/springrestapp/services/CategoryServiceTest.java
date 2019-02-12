package vijay.sprintboot.springrestapp.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import vijay.sprintboot.springrestapp.domain.Category;
import vijay.sprintboot.springrestapp.mapper.CategoryMapper;
import vijay.sprintboot.springrestapp.model.CategoryDTO;
import vijay.sprintboot.springrestapp.repositories.CategoryRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CategoryServiceTest {

    public static final Long ID= 2L;
    public static final String NAME="Vijay";

    CategoryService underTest;

    @Mock
    CategoryRepository categoryRepository;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        underTest = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
    }

    @Test
    public void listCategories() {
        //given
        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());

        when(categoryRepository.findAll()).thenReturn(categories);

        //when
        List<CategoryDTO> categoryDTOS = underTest.getAllCategories();

        //then
        assertEquals(3, categoryDTOS.size());

    }

    @Test
    public void getCategoryByName() {

        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);

        when(categoryRepository.findByName(anyString())).thenReturn(category);

        CategoryDTO categoryDTO = underTest.getCategoryByName(anyString());

        assertEquals(ID,categoryDTO.getId());
        assertEquals(NAME,categoryDTO.getName());

    }
}
