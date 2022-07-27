package by.iba.mapper;

import by.iba.dto.req.report.CarComputerErrorReq;
import by.iba.entity.report.CarComputerError;
import by.iba.mapper.common.SimpleAbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class CarComputerErrorsMapper extends SimpleAbstractMapper<CarComputerError, CarComputerErrorReq> {
    public CarComputerErrorsMapper(Class<CarComputerError> entity, Class<CarComputerErrorReq> dto) {
        super(entity, dto);
    }
}