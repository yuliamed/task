package by.iba.service.impl;

import by.iba.dto.req.report.InspectionReportReq;
import by.iba.dto.resp.report.InspectionReportResp;
import by.iba.entity.order.*;
import by.iba.entity.report.InspectionReport;
import by.iba.exception.ResourceNotFoundException;
import by.iba.exception.ServiceException;
import by.iba.inteface.CurrencyTypeRepository;
import by.iba.inteface.car_description.*;
import by.iba.inteface.order.InspectionOrderRepository;
import by.iba.inteface.report.InspectionReportRepository;
import by.iba.mapper.InspectionReportMapper;
import by.iba.service.InspectionReportService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class InspectionReportImpl implements InspectionReportService {
    private final EngineRepository engineRepository;
    private final TransmissionRepository transmissionRepository;
    private final CarBrandRepository carBrandRepository;
    private final DriveRepository driveRepository;
    private final CurrencyTypeRepository currencyTypeRepository;
    private final BodyRepository bodyRepository;
    private final InspectionReportRepository reportRepository;
    private final InspectionReportMapper inspectionReportMapper;
    private final InspectionOrderRepository inspectionOrderRepository;

    @Override
    public InspectionReportResp createReport(Long orderId, InspectionReportReq req) {
        InspectionReport report = inspectionReportMapper.toEntityFromReq(req);
        report.setCurrencyType(getCurrencyTypeByName(req.getCurrencyType()));
        report.setDrive(getDriveByName(req.getDrive().getName()));
        report.setTransmission(getTransmissionByName(req.getTransmission().getName()));
        report.setEngine(getEngineByName(req.getEngine().getName()));
        report.setBrand(getBrandByName(req.getBrand().getName()));
        report.setBody(getBodyByName(req.getBody().getName()));

        report = reportRepository.save(report);
        // join report and order
        InspectionOrder editingOrder = findOrderById(orderId);
        editingOrder.setInspectionReport(report);
        inspectionOrderRepository.save(editingOrder);

        InspectionReportResp resp = inspectionReportMapper.toDto(report);
        return resp;
    }

    @Override
    public List<InspectionReport> findAll() {
        return reportRepository.findAll();
    }

    private CarBrand getBrandByName(String name) {
        return carBrandRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("There is no brand with name = "
                        + name));
    }

    private Transmission getTransmissionByName(String name) {
        return transmissionRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("There is no Transmission with name = "
                        + name));
    }

    private Drive getDriveByName(String drive) {
        return driveRepository.findByName(drive)
                .orElseThrow(() -> new ResourceNotFoundException("There is no drive with name = "
                        + drive));
    }

    private Body getBodyByName(String body) {
        return bodyRepository.findByName(body)
                .orElseThrow(() -> new ResourceNotFoundException("There is no body with name = "
                        + body));
    }

    private Engine getEngineByName(String engine) {
        return engineRepository.findByName(engine)
                .orElseThrow(() -> new ResourceNotFoundException("There is no engine with name = "
                        + engine));
    }

    private CurrencyType getCurrencyTypeByName(String currencyType) {
        CurrencyType type = currencyTypeRepository.findByName(currencyType)
                .orElseThrow(() -> new ServiceException("There is no currency type with name " + currencyType));
        return type;
    }

    protected InspectionOrder findOrderById(Long id) {
        return inspectionOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no order with id = " + id));
    }
}
