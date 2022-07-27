package by.iba.mapper;

import by.iba.dto.req.report.SelectedCarReq;
import by.iba.entity.report.SelectedCar;
import by.iba.mapper.common.SimpleAbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class SelectedCarMapper extends SimpleAbstractMapper<SelectedCar, SelectedCarReq> {
    public SelectedCarMapper() {
        super(SelectedCar.class, SelectedCarReq.class);
    }
}
