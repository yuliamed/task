package by.iba.dto.req;

import by.iba.dto.page.PagingCriteriaReq;
import by.iba.entity.enam.TypeOfRole;
import by.iba.exception.validation.EnumPattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
public class UserSearchCriteriaReq extends PagingCriteriaReq {
    //@TypeOfRolePattern(enumClass = TypeOfRole.class)
    @EnumPattern(enumClass = TypeOfRole.class, message = "Input correct name of role")
    private String typeOfRole;
    private String name = "";
    private String surname = "";
    private Boolean isActive;
}
