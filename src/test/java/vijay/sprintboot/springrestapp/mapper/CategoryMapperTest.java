package vijay.sprintboot.springrestapp.mapper;

import org.junit.Test;
import vijay.sprintboot.springrestapp.domain.Category;
import vijay.sprintboot.springrestapp.model.CategoryDTO;

import static org.junit.Assert.assertEquals;

public class CategoryMapperTest {

    public static final String NAME = "Fruits";
    public static final long ID = 1L;
    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    public void categoryToCategoryDTO() throws Exception {

        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);

        //when
        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        assertEquals(Long.valueOf(ID),categoryDTO.getId());
        assertEquals(NAME,categoryDTO.getName());
    }


}
