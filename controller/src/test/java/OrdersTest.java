import by.iba.dto.page.PageWrapper;
import by.iba.dto.req.order.*;
import by.iba.dto.req.report.InspectionReportReq;
import by.iba.dto.req.report.SelectedCarReq;
import by.iba.dto.req.report.SelectionReportReq;
import by.iba.dto.req.user.SignUpReq;
import by.iba.dto.resp.order.InspectionOrderResp;
import by.iba.dto.resp.order.OrderResp;
import by.iba.dto.resp.order.SelectionOrderResp;
import by.iba.dto.resp.report.InspectionReportResp;
import by.iba.dto.resp.report.SelectionReportResp;
import by.iba.dto.resp.user.RoleResp;
import by.iba.dto.resp.user.UserResp;
import by.iba.entity.enam.CurrencyEnum;
import by.iba.entity.enam.RoleEnum;
import by.iba.inteface.CurrencyTypeRepository;
import by.iba.inteface.car_description.*;
import by.iba.inteface.order.InspectionOrderRepository;
import by.iba.inteface.order.OrderStatusRepository;
import by.iba.inteface.order.SelectionOrderRepository;
import by.iba.inteface.report.InspectionReportRepository;
import by.iba.inteface.report.SelectedCarRepository;
import by.iba.inteface.report.SelectionReportRepository;
import by.iba.inteface.user.RoleRepository;
import by.iba.inteface.user.UserRepository;
import by.iba.mapper.*;
import by.iba.security.jwt.JwtTokenProvider;
import by.iba.service.InspectionOrderService;
import by.iba.service.MailTrueService;
import by.iba.service.OrderService;
import by.iba.service.StorageService;
import by.iba.service.impl.*;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class OrdersTest {
    @InjectMocks
    AdminServiceImpl adminService;
    @Mock
    UserRepository userRepository;
    @Mock
    UserMapper userMapper;
    @Mock
    RoleRepository roleRepository;
    @Mock
    OrderService orderService;
    @InjectMocks
    UserServiceImpl userService;
    @Mock
    BCryptPasswordEncoder passwordEncoder;
    @Mock
    JwtTokenProvider jwtTokenProvider;
    @Mock
    AuthenticationManager authenticationManager;
    @Mock
    MailTrueService mailTrueService;
    @Mock
    StorageService storageService;

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
    OrderStatusRepository orderStatusRepository;
    @Mock
    SelectionOrderMapper selectionOrderMapper;
    @Mock
    SelectionReportMapper selectionReportMapper;

    SignUpReq autoPickerReq = new SignUpReq("name", "surname", "testEmail@email.com", "pass1234", "pass1234", true);
    final Long ADMIN_ID = 1L;
    UserResp autoPickerResp = new UserResp();

    @Order(1)
    @Test
    public void testSignUp() {
        autoPickerResp = userService.signUp(autoPickerReq);
        assertNotNull(autoPickerResp);
        assertNotNull(autoPickerResp.getId());
        assertEquals(autoPickerResp.getName(), autoPickerReq.getName());
        assertEquals(autoPickerResp.getSurname(), autoPickerReq.getSurname());
        assertEquals(autoPickerResp.getEmail(), autoPickerReq.getEmail());
        assertEquals(autoPickerResp.getIsActive(), false);
        assertTrue(autoPickerResp.getRoles().contains(new RoleResp(RoleEnum.AUTO_PICKER.name())));
    }

    @Order(2)
    @Test
    public void testApproveAutoPicker() {
        autoPickerResp = adminService.approveUser(autoPickerResp.getId());
        assertEquals(autoPickerResp.getIsActive(), true);
    }

    SelectionOrderReq testSelectionOrderReq = new SelectionOrderReq();
    SelectionOrderResp testSelectionOrderResp;
    SelectionReportReq selectionReportReq = new SelectionReportReq();
    SelectionReportResp selectionReportResp;

    @Before("testCreatingSelectionOrder")
    public void initShitCreatingSelectionOrder() {
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
    @Order(3)
    public void testCreatingSelectionOrder() {
        testSelectionOrderResp = selectionOrderService.createOrder(testSelectionOrderReq);
        assertNotNull(testSelectionOrderResp);
        assertNotNull(testSelectionOrderResp.getId());
        assertNotNull(testSelectionOrderResp.getCreationDate());
        assertEquals(testSelectionOrderReq.getRangeFrom(), testSelectionOrderResp.getRangeFrom());
        assertEquals(testSelectionOrderReq.getRangeTo(), testSelectionOrderResp.getRangeTo());
        assertEquals(testSelectionOrderReq.getMinYear(), testSelectionOrderResp.getMinYear());
        assertEquals(testSelectionOrderReq.getMileage(), testSelectionOrderResp.getMileage());
        assertEquals(testSelectionOrderReq.getCurrencyType().getName(), testSelectionOrderResp.getCurrencyType().getName());
    }

    @Test
    @Order(4)
    public void testSettingAutoPickerToOrder() {
        OrderResp orderResp = orderService.setAutoPicker(testSelectionOrderResp.getId(), new OrderAutoPickerReq(autoPickerResp.getId()));
        assertNotNull(orderResp);
        assertNotNull(orderResp.getAutoPicker());
        assertEquals(autoPickerResp.getId(), orderResp.getAutoPicker().getId());
    }

    @Test
    @Order(5)
    public void testCreatingSelectionReport() {
        selectionReportResp = selectionReportService.createReport(autoPickerResp.getId(), testSelectionOrderResp.getId(), selectionReportReq);
        assertNotNull(selectionReportResp);
        assertNotNull(selectionReportResp.getId());
        assertNotNull(selectionReportResp.getCreationDate());
        assertEquals(selectionReportReq.getSelectedCarSet().size(), selectionReportResp.getSelectedCarSet().size());
    }

    @Test
    @Order(6)
    public void testReadSelectionOrderReport() {
        SelectionReportResp resp = selectionOrderService.getOrderReport(14L);
        assertNotNull(resp);
        assertNotNull(resp.getId());
        assertNotNull(resp.getCreationDate());
    }

    @Test
    @Order(7)
    public void testReadAllOrders() {
        PageWrapper<OrderResp> page = orderService.findAll(new OrderSearchCriteriaReq(""));
        assertEquals(page.getPage(), 0);
        assertEquals(page.getElementsCount(), 20);
        assertNotNull(page.getObjects());
    }

    @Test
    @Order(8)
    public void testReadOrderOfAutoPicker() {
        List<OrderResp> list = orderService.getOrdersByAutoPickerId(autoPickerResp.getId());
        assertNotNull(list);
        assertEquals(1, list.size());
        assertEquals(list.get(0).getAutoPicker().getId(), autoPickerResp.getId());
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
    InspectionReportMapper inspectionReportMapper;
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

    @Before("createInspectionOrderTest")
    public void initShit() {
        inspectionOrderReq.setAutoUrl("test_url");
        inspectionOrderReq.setAutoPickerId(autoPickerResp.getId());

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
    @Order(9)
    void createInspectionOrderTest() {
        inspectionOrderResp = inspectionOrderService.createInspectionOrder(inspectionOrderReq);
        assertNotNull(inspectionOrderResp);
        assertNotNull(inspectionOrderResp.getId());
        assertNotNull(inspectionOrderResp.getCreationDate());
        assertEquals(inspectionOrderReq.getAutoUrl(), inspectionOrderResp.getAutoUrl());
    }

    @Test
    @Order(10)
    public void testCreatingInspectionReport() {
        InspectionReportResp inspectionReportResp = inspectionReportService.createReport(autoPickerResp.getId(), inspectionOrderResp.getId(), inspectionReportReq);
        assertNotNull(inspectionReportResp);
        assertNotNull(inspectionReportResp.getId());
        assertNotNull(inspectionReportResp.getCreationDate());
        assertEquals(inspectionReportReq.getCostValue(), inspectionReportResp.getCostValue());
    }

    @Test
    @Order(11)
    public void testReadInspectionOrderReport() {
        InspectionReportResp resp = inspectionReportService.getReportById(autoPickerResp.getId());
        assertNotNull(resp);
        assertNotNull(resp.getId());
        assertNotNull(resp.getCreationDate());
        assertEquals(inspectionReportReq.getModel(), resp.getModel());
        assertEquals(inspectionReportReq.getYear(), resp.getYear());
        assertEquals(inspectionReportReq.getEngineVolume(), resp.getEngineVolume());
        assertEquals(inspectionReportReq.getInspectionDate(), resp.getInspectionDate());
        assertEquals(inspectionReportReq.getMileage(), resp.getMileage());
        assertEquals(inspectionReportReq.getIsMileageReal(), resp.getIsMileageReal());
        assertEquals(inspectionReportReq.getCostValue(), resp.getCostValue());
        assertEquals(inspectionReportReq.getAuctionValue(), resp.getAuctionValue());
        assertEquals(inspectionReportReq.getCurrencyType().getName(), resp.getCurrencyType().getName());

    }

}
