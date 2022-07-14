package by.iba.dto.resp.order;

import by.iba.dto.resp.AbstractResp;
import by.iba.dto.resp.user.UserResp;
import by.iba.entity.order.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class AbstractOrderResp extends AbstractResp {
    // Common info
    private Long id;

    private LocalDateTime lastUpdateDate;

    private LocalDateTime creationDate;

    private UserResp autoPicker;

    private UserResp creator;

    private OrderStatus status;

    private String additionalInfo;
}
