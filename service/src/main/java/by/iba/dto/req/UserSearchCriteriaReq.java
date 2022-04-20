package by.iba.dto.req;

import by.iba.dto.page.PagingCriteriaReq;
import by.iba.entity.TypeOfRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchCriteriaReq extends PagingCriteriaReq {

    private TypeOfRole typeOfRole = null;
    private String name = "";
    private String surname = "";
    private Boolean isActive = null;
}
