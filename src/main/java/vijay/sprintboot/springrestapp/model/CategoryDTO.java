package vijay.sprintboot.springrestapp.model;


import lombok.Data;
//need lombok annotation here else category mapper wont map the properties from Category domain correctly
@Data
public class CategoryDTO {
    private Long id;
    private String name;
}
