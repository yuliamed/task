import by.iba.dto.req.order.CurrencyTypeReq;
import by.iba.dto.req.order.SelectionOrderReq;
import by.iba.dto.req.report.SelectedCarReq;
import by.iba.dto.req.report.SelectionReportReq;
import by.iba.dto.resp.order.SelectionOrderResp;
import by.iba.dto.resp.report.SelectionReportResp;
import by.iba.entity.enam.CurrencyEnum;
import by.iba.inteface.CurrencyTypeRepository;
import by.iba.inteface.car_description.*;
import by.iba.inteface.order.OrderStatusRepository;
import by.iba.inteface.order.SelectionOrderRepository;
import by.iba.inteface.report.SelectedCarRepository;
import by.iba.inteface.report.SelectionReportRepository;
import by.iba.inteface.user.RoleRepository;
import by.iba.inteface.user.UserRepository;
import by.iba.mapper.SelectedCarMapper;
import by.iba.mapper.SelectionOrderMapper;
import by.iba.mapper.SelectionReportMapper;
import by.iba.service.impl.SelectionOrderServiceImpl;
import by.iba.service.impl.SelectionReportServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class SelectionOrderFlowTest {

    static {
        System.setProperty("spring.datasource.url", "jdbc:postgresql://localhost:5432/postgres");
    }
    @Mock
    SelectionReportRepository reportRepository;
    @Mock
    SelectionReportMapper reportMapper;
    @Mock
    SelectedCarRepository selectedCarRepository;
    @Mock
    SelectionOrderRepository selectionOrderRepository;
    @Mock
    SelectedCarMapper selectedCarMapper;
    @InjectMocks
    SelectionReportServiceImpl selectionReportService;
    @InjectMocks
    SelectionOrderServiceImpl selectionOrderService;
    @Mock
    EngineRepository engineRepository;
    @Mock
    TransmissionRepository transmissionRepository;
    @Mock
    CarBrandRepository carBrandRepository;
    @Mock
    DriveRepository driveRepository;
    @Mock
    CurrencyTypeRepository currencyTypeRepository;
    @Mock
    BodyRepository bodyRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    RoleRepository roleRepository;
    @Mock
    OrderStatusRepository orderStatusRepository;
    @Mock
    SelectionOrderMapper selectionOrderMapper;
    @Mock
    SelectionReportMapper selectionReportMapper;

    SelectionOrderReq testSelectionOrderReq = new SelectionOrderReq();
    SelectionOrderResp testSelectionOrderResp;
    SelectionReportReq selectionReportReq = new SelectionReportReq();
    SelectionReportResp selectionReportResp;

    @BeforeEach
    public void initShit() {
        testSelectionOrderReq.setMileage(100);
        testSelectionOrderReq.setMinYear(2010);
        testSelectionOrderReq.setRangeFrom(10000.0);
        testSelectionOrderReq.setRangeTo(20000.0);
        CurrencyTypeReq currencyTypeReq = new CurrencyTypeReq();
        currencyTypeReq.setName(CurrencyEnum.USD.name());
        testSelectionOrderReq.setCurrencyType(currencyTypeReq);

        Set<SelectedCarReq> listSelectedCar = new HashSet<>();
        SelectedCarReq selectedCar = new SelectedCarReq("test_string", "good car for low price");
        listSelectedCar.add(selectedCar);
        selectionReportReq.setSelectedCarSet(listSelectedCar);
    }

    @Test
    public void testCreatingSelectionOrder() {
        testSelectionOrderResp = selectionOrderService.createOrder(testSelectionOrderReq);
        assertNotNull(testSelectionOrderResp);
        assertNotNull(testSelectionOrderResp.getId());
        assertNotNull(testSelectionOrderResp.getCreationDate());
        assertEquals(testSelectionOrderResp.getRangeFrom(), testSelectionOrderReq.getRangeFrom());
    }

    @Test
    public void testCreatingSelectionReport() {
        selectionReportResp = selectionReportService.createReport(14L, testSelectionOrderResp.getId(), selectionReportReq);
        assertNotNull(selectionReportResp);
        assertNotNull(selectionReportResp.getId());
        assertNotNull(selectionReportResp.getCreationDate());
        assertEquals(selectionReportResp.getSelectedCarSet(), selectionReportReq.getSelectedCarSet());
    }

    @Test
    public void testReadSelectionOrderReport(){
        SelectionReportResp resp = selectionOrderService.getOrderReport(14L);
        assertNotNull(resp);
        assertNotNull(resp.getId());
        assertNotNull(resp.getCreationDate());
//        assertNotNull(true);
//        assertNotNull(true);
//        assertNotNull(true);
    }


}
