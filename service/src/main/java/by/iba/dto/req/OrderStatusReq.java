package by.iba.dto.req;

import by.iba.entity.enam.OrderStatusEnum;
import by.iba.exception.validation.EnumPattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderStatusReq extends AbstractReq {
    @EnumPattern(enumClass = OrderStatusEnum.class, message = "Input correct name of order status")
    private String newOrderStatus;
}
