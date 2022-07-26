package by.iba.service.impl;

import by.iba.dto.req.order.*;
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
import java.util.Objects;

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
    public InspectionReportResp createReport(Long orderId, Long autoPickerId, InspectionReportReq req) {
        InspectionOrder editingOrder = getReportingOrder(orderId);
        isAutoPickerAllowedToReport(autoPickerId, editingOrder);

        InspectionReport report = inspectionReportMapper.toEntityFromReq(req);
        setJoinedValues(report, req.getCurrencyType(), req.getDrive(), req.getTransmission(),
                req.getEngine(), req.getBrand(), req.getBody());

        report = reportRepository.save(report);
        // join report and order
        editingOrder.setReport(report);
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

        setJoinedValues(report, reqData.getCurrencyType(), reqData.getDrive(), reqData.getTransmission(),
                reqData.getEngine(), reqData.getBrand(), reqData.getBody());

        reportRepository.save(report);
        return inspectionReportMapper.toDto(report);
    }

    private void setJoinedValues(InspectionReport report, String currencyType, DriveReq drive, TransmissionReq transmission, EngineReq engine, CarBrandReq brand, BodyReq body) {
        report.setCurrencyType(getCurrencyTypeByName(currencyType));
        report.setDrive(getDriveByName(drive.getName()));
        report.setTransmission(getTransmissionByName(transmission.getName()));
        report.setEngine(getEngineByName(engine.getName()));
        report.setBrand(getBrandByName(brand.getName()));
        report.setBody(getBodyByName(body.getName()));
    }

    private InspectionOrder getReportingOrder(Long orderId) {
        InspectionOrder order = findOrderById(orderId);
        if (Objects.nonNull(order.getReport())) {
            throw new ServiceException("Order with id = " + orderId + " already has report!");
        }
        return order;
    }

    private void isAutoPickerAllowedToReport(Long autoPickerId, InspectionOrder editingOrder) {
        if (Objects.isNull(editingOrder.getAutoPicker())) {
            throw new ServiceException("AutoPicker does not set! Report can`t be created");
        }
        if (!editingOrder.getAutoPicker().getId().equals(autoPickerId)) {
            throw new ServiceException("Your account does not allowed to report to this order");
        }
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

    private InspectionOrder findOrderById(Long id) {
        return inspectionOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no order with id = " + id));
    }

    private InspectionReport findReportById(Long id) {
        return reportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no report with id = " + id));
    }
}
