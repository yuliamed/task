import by.iba.dto.req.order.CurrencyTypeReq;
import by.iba.dto.req.order.InspectionOrderReq;
import by.iba.dto.req.report.InspectionReportReq;
import by.iba.dto.resp.order.InspectionOrderResp;
import by.iba.dto.resp.report.InspectionReportResp;
import by.iba.dto.resp.report.SelectionReportResp;
import by.iba.entity.enam.CurrencyEnum;
import by.iba.inteface.CurrencyTypeRepository;
import by.iba.inteface.car_description.*;
import by.iba.inteface.order.InspectionOrderRepository;
import by.iba.inteface.order.OrderStatusRepository;
import by.iba.inteface.report.InspectionReportRepository;
import by.iba.inteface.user.RoleRepository;
import by.iba.inteface.user.UserRepository;
import by.iba.mapper.*;
import by.iba.service.InspectionOrderService;
import by.iba.service.StorageService;
import by.iba.service.impl.InspectionReportImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class InspectionOrderFlowTest {

    static {
        System.setProperty("spring.datasource.url", "jdbc:postgresql://localhost:5432/postgres");
    }

    @InjectMocks
    InspectionReportImpl inspectionReportService;
    @InjectMocks
    InspectionOrderService inspectionOrderService;
    @Mock
    InspectionOrderRepository inspectionOrderRepository;
    @Mock
    InspectionOrderMapper inspectionOrderMapper;
    @Mock
    OrderStatusRepository orderStatusRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    RoleRepository roleRepository;
    @Mock
    InspectionReportMapper inspectionReportMapper;
    @Mock
    StorageService storageService;
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
    InspectionReportRepository reportRepository;
    @Mock
    SalonReportMapper salonReportMapper;
    @Mock
    CarComputerErrorsMapper carComputerErrorsMapper;
    @Mock
    EngineReportMapper engineReportMapper;
    @Mock
    TransmissionReportMapper transmissionReportMapper;
    @Mock
    BodyReportMapper bodyReportMapper;
    @Mock
    ElectricalEquipmentReportMapper electricalEquipmentReportMapper;
    @Mock
    PendantReportMapper pendantReportMapper;
    @Mock
    InspectionReportRepository inspectionReportRepository;
    private InspectionOrderReq inspectionOrderReq = new InspectionOrderReq();
    private InspectionReportReq inspectionReportReq = new InspectionReportReq();
    InspectionOrderResp inspectionOrderResp;
    @BeforeEach
    public void initShit() {
        inspectionOrderReq.setAutoUrl("test_url");

        inspectionReportReq.setModel("test_model");
        inspectionReportReq.setYear(2020);
        inspectionReportReq.setEngineVolume(2.5);
        inspectionReportReq.setInspectionDate(LocalDate.now());
        inspectionReportReq.setMileage(10000);
        inspectionReportReq.setIsMileageReal(true);
        inspectionReportReq.setVinNumber("VINVINVIN");
        inspectionReportReq.setIsVinNumberReal(true);
        inspectionReportReq.setCostValue(20000.);
        inspectionReportReq.setAuctionValue(18000.);
        inspectionReportReq.setCurrencyType(new CurrencyTypeReq(CurrencyEnum.USD.name()));
    }


    @Test
    void createInspectionOrderTest() {
        inspectionOrderResp = inspectionOrderService.createInspectionOrder(inspectionOrderReq);
        assertNotNull(inspectionOrderResp);
        assertNotNull(inspectionOrderResp.getId());
        assertNotNull(inspectionOrderResp.getCreationDate());
        assertEquals(inspectionOrderResp.getAutoUrl(), inspectionOrderReq.getAutoUrl());
    }

    @Test
    public void testCreatingInspectionReport() {
        InspectionReportResp inspectionReportResp = inspectionReportService.createReport(14L, inspectionOrderResp.getId(), inspectionReportReq);
        assertNotNull(inspectionReportResp);
        assertNotNull(inspectionReportResp.getId());
        assertNotNull(inspectionReportResp.getCreationDate());
        assertEquals(inspectionReportResp.getCostValue(), inspectionReportReq.getCostValue());
    }

    @Test
    public void testReadSelectionOrderReport(){
        InspectionReportResp resp = inspectionReportService.getReportById(14L);
        assertNotNull(resp);
        assertNotNull(resp.getId());
        assertNotNull(resp.getCreationDate());
//        assertNotNull(true);
//        assertNotNull(true);
//        assertNotNull(true);
    }

}
