package vijay.sprintboot.springrestapp.controllers.v1;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import vijay.sprintboot.springrestapp.domain.Category;
import vijay.sprintboot.springrestapp.model.CategoryDTO;
import vijay.sprintboot.springrestapp.services.CategoryService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CategoryControllerTest {

    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController underTest;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
    }

    @Test
    public void getAllCategories() throws Exception {
        CategoryDTO category1 = new CategoryDTO();
        category1.setName("Vijay");
        category1.setId(1L);

        CategoryDTO category2 = new CategoryDTO();
        category2.setName("Ajay");
        category2.setId(2L);

        List<CategoryDTO> categoryDTOList = Arrays.asList(category1,category2);

        when(categoryService.getAllCategories()).thenReturn(categoryDTOList);

        mockMvc.perform(get("/api/v1/categories")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.categories",hasSize(2)));

    }

    @Test
    public void getCategoryByName() throws Exception {

        CategoryDTO category1 = new CategoryDTO();
        category1.setId(1l);
        category1.setName("Vijay");

        when(categoryService.getCategoryByName(anyString())).thenReturn(category1);

        mockMvc.perform(get("/api/v1/categories/Vijay")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.name",equalTo("Vijay")));
    }


}
