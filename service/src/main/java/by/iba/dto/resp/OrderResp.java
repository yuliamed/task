package by.iba.dto.resp;

import by.iba.dto.resp.AbstractResp;
import by.iba.dto.resp.UserResp;
import by.iba.entity.order.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class OrderResp extends AbstractResp {
    // Common info
    private Long id;

    private LocalDateTime lastUpdateDate;

    private LocalDateTime createdDate;

    private UserResp autoPicker;

    private UserResp creator;

    private OrderStatus status;

}
