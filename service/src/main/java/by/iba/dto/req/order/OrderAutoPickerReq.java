package by.iba.dto.req.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderAutoPickerReq {
    @NotNull(message = "Auto-picker`s id can`t be null or empty")
    private Long autoPickerId;
}
