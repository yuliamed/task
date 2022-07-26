package by.iba.service.impl;

import by.iba.dto.req.report.DateReq;
import by.iba.dto.req.report.SelectionReportReq;
import by.iba.dto.req.report.SelectionReportUpdateReq;
import by.iba.dto.resp.report.SelectionReportResp;
import by.iba.entity.order.Order;
import by.iba.entity.order.SelectionOrder;
import by.iba.entity.report.SelectedCar;
import by.iba.entity.report.SelectionReport;
import by.iba.exception.ResourceNotFoundException;
import by.iba.exception.ServiceException;
import by.iba.inteface.order.SelectionOrderRepository;
import by.iba.inteface.report.SelectedCarRepository;
import by.iba.inteface.report.SelectionReportRepository;
import by.iba.mapper.SelectedCarMapper;
import by.iba.mapper.SelectionReportMapper;
import by.iba.service.SelectionReportService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@Service
public class SelectionReportServiceImpl implements SelectionReportService {

    private final SelectionReportRepository reportRepository;
    private final SelectionReportMapper reportMapper;
    private final SelectedCarRepository selectedCarRepository;
    private final SelectionOrderRepository selectionOrderRepository;
    private final SelectedCarMapper selectedCarMapper;

    @Transactional
    @Override
    public SelectionReportResp createReport(Long autoPickerId, Long orderId, SelectionReportReq req) {
        SelectionOrder order = getReportingOrder(orderId);
        isAutoPickerAllowedToReport(autoPickerId, order);

        SelectionReport newReport = reportMapper.toEntityFromReq(req);
        newReport = reportRepository.save(newReport);

        order.setReport(newReport);
        selectionOrderRepository.save(order);

        return reportMapper.toDto(newReport);
    }

    @Override
    public SelectionReportResp setDate(Long reportID, DateReq date) {
        return null;
    }

    @Override
    public SelectionReportResp editReport(Long orderId, SelectionReportUpdateReq req) {
        SelectionReport report = findReportByOrderId(orderId);
        Set<SelectedCar> selectedCarSet = selectedCarMapper.toEntitySet(req.getSelectedCarSet());
        report.setSelectedCarSet(selectedCarSet);
        report = reportRepository.save(report);
        return reportMapper.toDto(report);
    }

    @Override
    public SelectionReportResp getReportByOrderId(Long orderId) {
        return null;
    }

    @Override
    public List<SelectionReportResp> findAll() {
        return reportMapper.toDtoList(reportRepository.findAll());
    }

    private void isAutoPickerAllowedToReport(Long autoPickerId, SelectionOrder editingOrder) {
        if (Objects.isNull(editingOrder.getAutoPicker())) {
            throw new ServiceException("AutoPicker does not set! Report can`t be created");
        }
        if (!editingOrder.getAutoPicker().getId().equals(autoPickerId)) {
            throw new ServiceException("Your account does not allowed to report to this order");
        }
    }

    private SelectionOrder getReportingOrder(Long orderId) {
        SelectionOrder order = findOrderById(orderId);
        if (Objects.nonNull(order.getReport())) {
            throw new ServiceException("Order with id = " + orderId + " already has report!");
        }
        return order;
    }

    private SelectedCar findSelectedCarById(Long selectedId) {
        return selectedCarRepository.findById(selectedId)
                .orElseThrow(() -> new ResourceNotFoundException("There is no selected car with id = "
                        + selectedId));
    }

    private SelectionOrder findOrderById(Long orderID) {
        return selectionOrderRepository.findById(orderID)
                .orElseThrow(() -> new ResourceNotFoundException("There is no selection order with id = "
                        + orderID));
    }

    private SelectionReport findReportByOrderId(Long orderId) {
        Order order = selectionOrderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order with id = "
                        + orderId + " does not exist"));
        SelectionReport report = (SelectionReport) order.getReport();
        if (Objects.isNull(order.getReport())) {
            throw new ServiceException("Order with id = " + orderId + " has not a report!");
        }
        return report;
    }
}
