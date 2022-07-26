package by.iba.mapper;

import by.iba.dto.req.report.SelectedCarReq;
import by.iba.entity.report.SelectedCar;
import by.iba.mapper.common.SimpleAbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class SelectedCarMapper extends SimpleAbstractMapper<SelectedCar, SelectedCarReq> {
    public SelectedCarMapper(Class<SelectedCar> entity, Class<SelectedCarReq> dto) {
        super(entity, dto);
    }
}
