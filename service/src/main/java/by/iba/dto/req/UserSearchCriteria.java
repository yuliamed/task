package by.iba.dto.req;

import by.iba.service.search.SearchOperation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchCriteria {
    private String key;
    private Object value;
    private SearchOperation operation;

    @Override
    public String toString() {
        return "key='" + key + '\'' +
                ", value=" + value +
                ", operation=" + operation;
    }
}
