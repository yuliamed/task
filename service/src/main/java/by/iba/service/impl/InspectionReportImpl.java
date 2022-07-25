package by.iba.service.impl;

import by.iba.dto.req.report.InspectionReportReq;
import by.iba.dto.req.report.InspectionReportUpdateReq;
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
        //editingOrder.setReport(report);
        inspectionOrderRepository.save(editingOrder);

        InspectionReportResp resp = inspectionReportMapper.toDto(report);
        return resp;
    }

    @Override
    public List<InspectionReport> findAll() {
        return reportRepository.findAll();
    }

    @Override
    public InspectionReportResp editReport(Long reportId, InspectionReportReq req) {
        //report.setBodyReport();
        return null;
    }

    @Override
    public InspectionReportResp getReportById(Long reportId) {
        return inspectionReportMapper.toDto(findReportById(reportId));

    }

    @Override
    public InspectionReportResp getReportByOrderId(Long orderId) {
        return null;
        // return inspectionReportMapper.toDto(findOrderById(orderId).getReport());
    }

    @Override
    public InspectionReportResp editReportData(Long reportId, InspectionReportUpdateReq reqData) {
        InspectionReport report = findReportById(reportId);
        report.setModel(reqData.getModel());
        report.setYear(report.getYear());
        report.setEngineVolume(reqData.getEngineVolume());
        report.setInspectionDate(reqData.getInspectionDate());
        report.setMileage(report.getMileage());
        report.setIsMileageReal(reqData.getIsMileageReal());
        report.setVinNumber(reqData.getVinNumber());
        report.setIsVinNumberReal(reqData.getIsVinNumberReal());
        report.setCostValue(reqData.getCostValue());
        report.setAuctionValue(report.getAuctionValue());

        report.setCurrencyType(getCurrencyTypeByName(reqData.getCurrencyType()));
        report.setDrive(getDriveByName(reqData.getDrive().getName()));
        report.setTransmission(getTransmissionByName(reqData.getTransmission().getName()));
        report.setEngine(getEngineByName(reqData.getEngine().getName()));
        report.setBrand(getBrandByName(reqData.getBrand().getName()));
        report.setBody(getBodyByName(reqData.getBody().getName()));

        reportRepository.save(report);
        return inspectionReportMapper.toDto(report);
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

    protected InspectionReport findReportById(Long id) {
        return reportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no report with id = " + id));
    }
}
