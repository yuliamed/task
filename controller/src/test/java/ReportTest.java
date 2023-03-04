import by.iba.dto.resp.report.InspectionReportResp;
import by.iba.entity.report.InspectionReport;
import by.iba.inteface.CurrencyTypeRepository;
import by.iba.inteface.car_description.*;
import by.iba.inteface.order.InspectionOrderRepository;
import by.iba.inteface.order.SelectionOrderRepository;
import by.iba.inteface.report.InspectionReportRepository;
import by.iba.inteface.report.SelectedCarRepository;
import by.iba.inteface.report.SelectionReportRepository;
import by.iba.mapper.*;
import by.iba.service.InspectionReportService;
import by.iba.service.SelectionReportService;
import by.iba.service.StorageService;
import by.iba.service.impl.InspectionReportImpl;
import by.iba.service.impl.SelectionReportServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
public class ReportTest {

    //    @InjectMocks
//    InspectionReportImpl inspectionReportService;
//    @Mock
//    StorageService storageService;
//    @Mock
//    EngineRepository engineRepository;
//    @Mock
//    TransmissionRepository transmissionRepository;
//    @Mock
//    CarBrandRepository carBrandRepository;
//    @Mock
//    DriveRepository driveRepository;
//    @Mock
//    CurrencyTypeRepository currencyTypeRepository;
//    @Mock
//    BodyRepository bodyRepository;
//    @Mock
//    InspectionReportRepository reportRepository;
//    @Mock
//    InspectionOrderRepository inspectionOrderRepository;
//    @Mock
//    InspectionReportMapper inspectionReportMapper;
//    @Mock
//    SalonReportMapper salonReportMapper;
//    @Mock
//    CarComputerErrorsMapper carComputerErrorsMapper;
//    @Mock
//    EngineReportMapper engineReportMapper;
//    @Mock
//    TransmissionReportMapper transmissionReportMapper;
//    @Mock
//    BodyReportMapper bodyReportMapper;
//    @Mock
//    ElectricalEquipmentReportMapper electricalEquipmentReportMapper;
//    @Mock
//    PendantReportMapper pendantReportMapper;
//    @Mock
//    InspectionReportRepository inspectionReportRepository;
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
    private SelectionReportServiceImpl selectionReportService;

    @Test
    public void test_get_inspection_report() {
        selectionReportService.findAll();

    }
}
