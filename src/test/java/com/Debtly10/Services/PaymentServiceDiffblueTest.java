package com.Debtly10.Services;

import static org.junit.jupiter.api.Assertions.*;
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
        mortgage.setLeftAmount(10.0f);
        mortgage.setMarketValue(10.0f);
        mortgage.setPaymentList(new ArrayList<>());
        Date lastPaid = spy(new Date(1L));
        when(lastPaid.getTime()).thenReturn(86400000L);
        mortgage.setLastPaid(lastPaid);
        mortgage.setProductName("Product Name");
        Optional<Mortgage> ofResult = Optional.of(mortgage);

        Payment payment = new Payment();
        payment.setAmount(10.0f);
        payment.setDate(mock(Date.class));
        payment.setId(1L);
        payment.setMortgage(mortgage);
        when(paymentRepository.save(Mockito.<Payment>any())).thenReturn(payment);

        when(mortgageRepository.save(Mockito.<Mortgage>any())).thenReturn(mortgage);
        when(mortgageRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Date date = spy(new Date(1L));
        when(date.getTime()).thenReturn(345600000L);
        String actualAddPaymentResult = paymentService.addPayment(new PaymentRegistrationDTO(10.0f, date), 1L);

        verify(mortgageRepository).findById(Mockito.<Long>any());
        verify(mortgageRepository).save(Mockito.<Mortgage>any());
        verify(paymentRepository).save(Mockito.<Payment>any());
        assertEquals(3,mortgage.getLeftAmount());
        assertSame(date, mortgage.getLastPaid());
        assertEquals(" payment added successfully 3", actualAddPaymentResult);
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
        when(lastPaid.getTime()).thenReturn(86400000L);

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
        when(date.getTime()).thenReturn(345600000L);
        float actualSeeDueResult = paymentService.seeDue(new PaymentRegistrationDTO(10.0f, date), 1L);
        verify(lastPaid).getTime();
        verify(date).getTime();
        verify(mortgageRepository).findById(Mockito.<Long>any());
        assertEquals(13.0f, actualSeeDueResult);
    }
}
