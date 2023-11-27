package com.Debtly10.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import com.Debtly10.DTOS.MortgageFetchDTO;
import com.Debtly10.DTOS.PaymentFetchDTO;
import com.Debtly10.DTOS.PaymentRegistrationDTO;
import com.Debtly10.Repository.MortgageRepository;
import com.Debtly10.Repository.PaymentRepository;
import com.Debtly10.models.Customer;
import com.Debtly10.models.Mortgage;
import com.Debtly10.models.Payment;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PaymentService.class})
@ExtendWith(SpringExtension.class)
class PaymentServiceDiffblueTest {
    @MockBean
    private MortgageRepository mortgageRepository;

    @MockBean
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentService paymentService;

    /**
     * Method under test:
     * {@link PaymentService#addPayment(PaymentRegistrationDTO, Long)}
     */
    @Test
    void testAddPayment() {
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setContact("Contact");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        customer.setMortgageList(new ArrayList<>());

        Mortgage mortgage = new Mortgage();
        mortgage.setCustomer(customer);
        mortgage.setGivenAmount(10.0f);
        mortgage.setId(1L);
        mortgage.setInterestRate(10.0f);
        mortgage.setIssueDate(mock(Date.class));
        mortgage.setLastPaid(mock(Date.class));
        mortgage.setLeftAmount(10.0f);
        mortgage.setMarketValue(10.0f);
        mortgage.setPaymentList(new ArrayList<>());
        mortgage.setProductName("Product Name");

        Payment payment = new Payment();
        payment.setAmount(10.0f);
        payment.setDate(mock(Date.class));
        payment.setId(1L);
        payment.setMortgage(mortgage);
        when(paymentRepository.save(Mockito.<Payment>any())).thenReturn(payment);

        Customer customer2 = new Customer();
        customer2.setAddress("42 Main St");
        customer2.setContact("Contact");
        customer2.setEmail("jane.doe@example.org");
        customer2.setFirstName("Jane");
        customer2.setId(1L);
        customer2.setLastName("Doe");
        customer2.setMortgageList(new ArrayList<>());
        Date lastPaid = spy(new Date(2023,4,9));
        when(lastPaid.getTime()).thenReturn(10L);

        Mortgage mortgage2 = new Mortgage();
        mortgage2.setCustomer(customer2);
        mortgage2.setGivenAmount(10.0f);
        mortgage2.setId(1L);
        mortgage2.setInterestRate(10.0f);
        mortgage2.setIssueDate(mock(Date.class));
        mortgage2.setLastPaid(lastPaid);
        mortgage2.setLeftAmount(10.0f);
        mortgage2.setMarketValue(10.0f);
        mortgage2.setPaymentList(new ArrayList<>());
        mortgage2.setProductName("Product Name");
        Optional<Mortgage> ofResult = Optional.of(mortgage2);

        Customer customer3 = new Customer();
        customer3.setAddress("42 Main St");
        customer3.setContact("Contact");
        customer3.setEmail("jane.doe@example.org");
        customer3.setFirstName("Jane");
        customer3.setId(1L);
        customer3.setLastName("Doe");
        customer3.setMortgageList(new ArrayList<>());

        Mortgage mortgage3 = new Mortgage();
        mortgage3.setCustomer(customer3);
        mortgage3.setGivenAmount(10.0f);
        mortgage3.setId(1L);
        mortgage3.setInterestRate(10.0f);
        mortgage3.setIssueDate(mock(Date.class));
        mortgage3.setLastPaid(mock(Date.class));
        mortgage3.setLeftAmount(10.0f);
        mortgage3.setMarketValue(10.0f);
        mortgage3.setPaymentList(new ArrayList<>());
        mortgage3.setProductName("Product Name");
        when(mortgageRepository.save(Mockito.<Mortgage>any())).thenReturn(mortgage3);
        when(mortgageRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Date date = spy(new Date(2023,4,10));
        when(date.getTime()).thenReturn(12L);
        String actualAddPaymentResult = paymentService.addPayment(new PaymentRegistrationDTO(10.0f, date), 1L);
        verify(lastPaid).getTime();
        verify(date).getTime();
        verify(mortgageRepository).findById(Mockito.<Long>any());
        verify(mortgageRepository).save(Mockito.<Mortgage>any());
        verify(paymentRepository).save(Mockito.<Payment>any());
        assertEquals(" payment added successfully0", actualAddPaymentResult);
    }

    /**
     * Method under test: {@link PaymentService#getAllPayment()}
     */
    @Test
    void testGetAllPayment() {
        when(paymentRepository.findAll()).thenReturn(new ArrayList<>());
        List<PaymentFetchDTO> actualAllPayment = paymentService.getAllPayment();
        verify(paymentRepository).findAll();
        assertTrue(actualAllPayment.isEmpty());
    }

    /**
     * Method under test: {@link PaymentService#getAllPayment()}
     */
    @Test
    void testGetAllPayment2() {
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setContact("Contact");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        customer.setMortgageList(new ArrayList<>());

        Mortgage mortgage = new Mortgage();
        mortgage.setCustomer(customer);
        mortgage.setGivenAmount(10.0f);
        mortgage.setId(1L);
        mortgage.setInterestRate(10.0f);
        mortgage.setIssueDate(mock(Date.class));
        mortgage.setLastPaid(mock(Date.class));
        mortgage.setLeftAmount(10.0f);
        mortgage.setMarketValue(10.0f);
        mortgage.setPaymentList(new ArrayList<>());
        mortgage.setProductName("Product Name");

        Payment payment = new Payment();
        payment.setAmount(10.0f);
        payment.setDate(mock(Date.class));
        payment.setId(1L);
        payment.setMortgage(mortgage);

        ArrayList<Payment> paymentList = new ArrayList<>();
        paymentList.add(payment);
        when(paymentRepository.findAll()).thenReturn(paymentList);
        List<PaymentFetchDTO> actualAllPayment = paymentService.getAllPayment();
        verify(paymentRepository).findAll();
        assertEquals(1, actualAllPayment.size());
    }

    /**
     * Method under test: {@link PaymentService#getAllMortgage()}
     */
    @Test
    void testGetAllMortgage() {
        when(mortgageRepository.findAll()).thenReturn(new ArrayList<>());
        List<MortgageFetchDTO> actualAllMortgage = paymentService.getAllMortgage();
        verify(mortgageRepository).findAll();
        assertTrue(actualAllMortgage.isEmpty());
    }

    /**
     * Method under test: {@link PaymentService#getAllMortgage()}
     */
    @Test
    void testGetAllMortgage2() {
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setContact("Contact");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        customer.setMortgageList(new ArrayList<>());

        Mortgage mortgage = new Mortgage();
        mortgage.setCustomer(customer);
        mortgage.setGivenAmount(10.0f);
        mortgage.setId(1L);
        mortgage.setInterestRate(10.0f);
        mortgage.setIssueDate(mock(Date.class));
        mortgage.setLastPaid(mock(Date.class));
        mortgage.setLeftAmount(10.0f);
        mortgage.setMarketValue(10.0f);
        mortgage.setPaymentList(new ArrayList<>());
        mortgage.setProductName("Product Name");

        ArrayList<Mortgage> mortgageList = new ArrayList<>();
        mortgageList.add(mortgage);
        when(mortgageRepository.findAll()).thenReturn(mortgageList);
        List<MortgageFetchDTO> actualAllMortgage = paymentService.getAllMortgage();
        verify(mortgageRepository).findAll();
        assertEquals(1, actualAllMortgage.size());
    }

    /**
     * Method under test: {@link PaymentService#deletePayment(Long)}
     */
    @Test
    void testDeletePayment() {
        doNothing().when(paymentRepository).deleteById(Mockito.<Long>any());
        paymentService.deletePayment(1L);
        verify(paymentRepository).deleteById(Mockito.<Long>any());
        List<MortgageFetchDTO> allMortgage = paymentService.getAllMortgage();
        assertTrue(allMortgage.isEmpty());
        assertEquals(allMortgage, paymentService.getAllPayment());
    }

    /**
     * Method under test:
     * {@link PaymentService#seeDue(PaymentRegistrationDTO, Long)}
     */
    @Test
    void testSeeDue() {
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setContact("Contact");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        customer.setMortgageList(new ArrayList<>());
        Date lastPaid = mock(Date.class);
        when(lastPaid.getTime()).thenReturn(10L);

        Mortgage mortgage = new Mortgage();
        mortgage.setCustomer(customer);
        mortgage.setGivenAmount(10.0f);
        mortgage.setId(1L);
        mortgage.setInterestRate(10.0f);
        mortgage.setIssueDate(mock(Date.class));
        mortgage.setLastPaid(lastPaid);
        mortgage.setLeftAmount(10.0f);
        mortgage.setMarketValue(10.0f);
        mortgage.setPaymentList(new ArrayList<>());
        mortgage.setProductName("Product Name");
        Optional<Mortgage> ofResult = Optional.of(mortgage);
        when(mortgageRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Date date = mock(Date.class);
        when(date.getTime()).thenReturn(10L);
        float actualSeeDueResult = paymentService.seeDue(new PaymentRegistrationDTO(10.0f, date), 1L);
        verify(lastPaid).getTime();
        verify(date).getTime();
        verify(mortgageRepository).findById(Mockito.<Long>any());
        assertEquals(10.0f, actualSeeDueResult);
    }
}
