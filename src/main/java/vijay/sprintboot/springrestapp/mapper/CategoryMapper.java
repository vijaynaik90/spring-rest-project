package vijay.sprintboot.springrestapp.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import vijay.sprintboot.springrestapp.domain.Category;
import vijay.sprintboot.springrestapp.model.CategoryDTO;
//-Amapstruct.defaultComponentModel=spring
// above line present in pom.xml will add the CategoryMapper as a Spring Compoennt in compile time.
@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE= Mappers.getMapper(CategoryMapper.class);

    @Mapping(source = "id", target = "id")
    CategoryDTO categoryToCategoryDTO(Category category);
}
