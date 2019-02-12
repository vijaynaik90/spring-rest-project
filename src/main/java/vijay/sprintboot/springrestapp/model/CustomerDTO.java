package vijay.sprintboot.springrestapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    @ApiModelProperty(value = "First Name of Customer",required = true)
    @JsonProperty("first_name")
    private String firstName;
    @ApiModelProperty(value = "Last Name of Customer",required = true)
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("customer_url")
    private String customerUrl;
}
