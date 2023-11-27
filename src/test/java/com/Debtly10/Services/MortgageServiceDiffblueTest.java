package com.Debtly10.Services;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.Debtly10.DTOS.MortgageFetchDTO;
import com.Debtly10.DTOS.MortgageRegistrationDTO;
import com.Debtly10.DTOS.MortgageUpdateDto;
import com.Debtly10.Repository.CustomerRepository;
import com.Debtly10.Repository.MortgageRepository;
import com.Debtly10.models.Customer;
import com.Debtly10.models.Mortgage;

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

@ContextConfiguration(classes = {MortgageService.class})
@ExtendWith(SpringExtension.class)
class MortgageServiceDiffblueTest {
    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private MortgageRepository mortgageRepository;

    @Autowired
    private MortgageService mortgageService;

    /**
     * Method under test:
     * {@link MortgageService#addMortgage(MortgageRegistrationDTO, Long)}
     */
    @Test
    void testAddMortgage() {
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
        when(mortgageRepository.save(Mockito.<Mortgage>any())).thenReturn(mortgage);

        Customer customer2 = new Customer();
        customer2.setAddress("42 Main St");
        customer2.setContact("Contact");
        customer2.setEmail("jane.doe@example.org");
        customer2.setFirstName("Jane");
        customer2.setId(1L);
        customer2.setLastName("Doe");
        customer2.setMortgageList(new ArrayList<>());
        Optional<Customer> ofResult = Optional.of(customer2);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        String actualAddMortgageResult = mortgageService.addMortgage(
                new MortgageRegistrationDTO("Product Name", 10.0f, 10.0f, 10.0f, mock(Date.class), mock(Date.class), 10.0f),
                1L);
        verify(customerRepository).findById(Mockito.<Long>any());
        verify(mortgageRepository).save(Mockito.<Mortgage>any());
        assertEquals("mortgage added successfully", actualAddMortgageResult);
    }

    /**
     * Method under test:
     * {@link MortgageService#addMortgage(MortgageRegistrationDTO, Long)}
     */
    @Test
    void testAddMortgage2() {
        when(mortgageRepository.save(Mockito.<Mortgage>any()))
                .thenThrow(new IllegalStateException("mortgage added successfully"));

        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setContact("Contact");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        customer.setMortgageList(new ArrayList<>());
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(IllegalStateException.class,
                () -> mortgageService.addMortgage(
                        new MortgageRegistrationDTO("Product Name", 10.0f, 10.0f, 10.0f, mock(Date.class), mock(Date.class), 10.0f),
                        1L));
        verify(customerRepository).findById(Mockito.<Long>any());
        verify(mortgageRepository).save(Mockito.<Mortgage>any());
    }

    /**
     * Method under test:
     * {@link MortgageService#addMortgage(MortgageRegistrationDTO, Long)}
     */
    @Test
    void testAddMortgage3() {
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setContact("Contact");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        customer.setMortgageList(new ArrayList<>());
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(IllegalStateException.class, () -> mortgageService.addMortgage(
                new MortgageRegistrationDTO(null, 10.0f, 10.0f, 10.0f, mock(Date.class), mock(Date.class), 10.0f), 1L));
        verify(customerRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test:
     * {@link MortgageService#addMortgage(MortgageRegistrationDTO, Long)}
     */
    @Test
    void testAddMortgage4() {
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setContact("Contact");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        customer.setMortgageList(new ArrayList<>());
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(IllegalStateException.class,
                () -> mortgageService.addMortgage(
                        new MortgageRegistrationDTO("Product Name", -1.0f, 10.0f, 10.0f, mock(Date.class), mock(Date.class), 10.0f),
                        1L));
        verify(customerRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test:
     * {@link MortgageService#addMortgage(MortgageRegistrationDTO, Long)}
     */
    @Test
    void testAddMortgage5() {
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setContact("Contact");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        customer.setMortgageList(new ArrayList<>());
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(IllegalStateException.class,
                () -> mortgageService.addMortgage(
                        new MortgageRegistrationDTO("Product Name", 10.0f, -1.0f, 10.0f, mock(Date.class), mock(Date.class), 10.0f),
                        1L));
        verify(customerRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test:
     * {@link MortgageService#addMortgage(MortgageRegistrationDTO, Long)}
     */
    @Test
    void testAddMortgage6() {
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setContact("Contact");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        customer.setMortgageList(new ArrayList<>());
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(IllegalStateException.class,
                () -> mortgageService.addMortgage(
                        new MortgageRegistrationDTO("Product Name", 10.0f, 10.0f, -1.0f, mock(Date.class), mock(Date.class), 10.0f),
                        1L));
        verify(customerRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test:
     * {@link MortgageService#addMortgage(MortgageRegistrationDTO, Long)}
     */
    @Test
    void testAddMortgage7() {
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setContact("Contact");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        customer.setMortgageList(new ArrayList<>());
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(IllegalStateException.class,
                () -> mortgageService.addMortgage(
                        new MortgageRegistrationDTO("Product Name", 10.0f, 10.0f, 10.0f, mock(Date.class), mock(Date.class), -1.0f),
                        1L));
        verify(customerRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link MortgageService#getMortgageByCustomer(Long)}
     */
    @Test
    void testGetMortgageByCustomer() {
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setContact("Contact");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        ArrayList<Mortgage> mortgageList = new ArrayList<>();
        customer.setMortgageList(mortgageList);
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        List<Mortgage> actualMortgageByCustomer = mortgageService.getMortgageByCustomer(1L);
        verify(customerRepository).findById(Mockito.<Long>any());
        assertTrue(actualMortgageByCustomer.isEmpty());
        assertSame(mortgageList, actualMortgageByCustomer);
    }

    /**
     * Method under test: {@link MortgageService#getMortgageByCustomer(Long)}
     */
    @Test
    void testGetMortgageByCustomer2() {
        when(customerRepository.findById(Mockito.<Long>any())).thenThrow(new IllegalStateException("foo"));
        assertThrows(IllegalStateException.class, () -> mortgageService.getMortgageByCustomer(1L));
        verify(customerRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link MortgageService#deleteMortgage(Long)}
     */
    @Test
    void testDeleteMortgage() {
        doNothing().when(mortgageRepository).deleteById(Mockito.<Long>any());
        String actualDeleteMortgageResult = mortgageService.deleteMortgage(1L);
        verify(mortgageRepository).deleteById(Mockito.<Long>any());
        assertEquals("mortgage deleted successfully", actualDeleteMortgageResult);
    }

    /**
     * Method under test: {@link MortgageService#deleteMortgage(Long)}
     */
    @Test
    void testDeleteMortgage2() {
        doThrow(new IllegalStateException("mortgage deleted successfully")).when(mortgageRepository)
                .deleteById(Mockito.<Long>any());
        assertThrows(IllegalStateException.class, () -> mortgageService.deleteMortgage(1L));
        verify(mortgageRepository).deleteById(Mockito.<Long>any());
    }

    /**
     * Method under test:
     * {@link MortgageService#updateMortgage(MortgageUpdateDto, Long)}
     */
    @Test
    void testUpdateMortgage() {
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
        Optional<Mortgage> ofResult = Optional.of(mortgage);
        when(mortgageRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        String actualUpdateMortgageResult = mortgageService.updateMortgage(
                new MortgageUpdateDto("Product Name", 10.0f, 10.0f, 10.0f, mock(Date.class), mock(Date.class), 10.0f), 1L);
        verify(mortgageRepository).findById(Mockito.<Long>any());
        assertEquals("mortgage updated successfully", actualUpdateMortgageResult);
    }

    /**
     * Method under test:
     * {@link MortgageService#updateMortgage(MortgageUpdateDto, Long)}
     */
    @Test
    void testUpdateMortgage2() {
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
        Optional<Mortgage> ofResult = Optional.of(mortgage);
        when(mortgageRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        String actualUpdateMortgageResult = mortgageService.updateMortgage(
                new MortgageUpdateDto("Product Name", 0.0f, 10.0f, 10.0f, mock(Date.class), mock(Date.class), 10.0f), 1L);
        verify(mortgageRepository).findById(Mockito.<Long>any());
        assertEquals("mortgage updated successfully", actualUpdateMortgageResult);
    }

    /**
     * Method under test:
     * {@link MortgageService#updateMortgage(MortgageUpdateDto, Long)}
     */
    @Test
    void testUpdateMortgage3() {
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
        Optional<Mortgage> ofResult = Optional.of(mortgage);
        when(mortgageRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        String actualUpdateMortgageResult = mortgageService.updateMortgage(
                new MortgageUpdateDto("Product Name", 10.0f, 0.0f, 10.0f, mock(Date.class), mock(Date.class), 10.0f), 1L);
        verify(mortgageRepository).findById(Mockito.<Long>any());
        assertEquals("mortgage updated successfully", actualUpdateMortgageResult);
    }

    /**
     * Method under test:
     * {@link MortgageService#updateMortgage(MortgageUpdateDto, Long)}
     */
    @Test
    void testUpdateMortgage4() {
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
        Optional<Mortgage> ofResult = Optional.of(mortgage);
        when(mortgageRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        String actualUpdateMortgageResult = mortgageService.updateMortgage(
                new MortgageUpdateDto("Product Name", 10.0f, 10.0f, 0.0f, mock(Date.class), mock(Date.class), 10.0f), 1L);
        verify(mortgageRepository).findById(Mockito.<Long>any());
        assertEquals("mortgage updated successfully", actualUpdateMortgageResult);
    }

    /**
     * Method under test:
     * {@link MortgageService#updateMortgage(MortgageUpdateDto, Long)}
     */
    @Test
    void testUpdateMortgage5() {
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
        Optional<Mortgage> ofResult = Optional.of(mortgage);
        when(mortgageRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        String actualUpdateMortgageResult = mortgageService.updateMortgage(
                new MortgageUpdateDto("Product Name", 10.0f, 10.0f, 10.0f, mock(Date.class), mock(Date.class), 0.0f), 1L);
        verify(mortgageRepository).findById(Mockito.<Long>any());
        assertEquals("mortgage updated successfully", actualUpdateMortgageResult);
    }

    /**
     * Method under test: {@link MortgageService#getAllMortgage()}
     */
    @Test
    void testGetAllMortgage() {
        when(mortgageRepository.findAll()).thenReturn(new ArrayList<>());
        List<MortgageFetchDTO> actualAllMortgage = mortgageService.getAllMortgage();
        verify(mortgageRepository).findAll();
        assertTrue(actualAllMortgage.isEmpty());
    }

    /**
     * Method under test: {@link MortgageService#getAllMortgage()}
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
        List<MortgageFetchDTO> actualAllMortgage = mortgageService.getAllMortgage();
        verify(mortgageRepository).findAll();
        assertEquals(1, actualAllMortgage.size());
    }

    /**
     * Method under test: {@link MortgageService#getAllMortgage()}
     */
    @Test
    void testGetAllMortgage3() {
        when(mortgageRepository.findAll()).thenThrow(new IllegalStateException("foo"));
        assertThrows(IllegalStateException.class, () -> mortgageService.getAllMortgage());
        verify(mortgageRepository).findAll();
    }

    @Test
    public void testAddMortgageException() {
        Customer customer2 = new Customer();
        customer2.setAddress("42 Main St");
        customer2.setContact("Contact");
        customer2.setEmail("jane.doe@example.org");
        customer2.setFirstName("Jane");
        customer2.setId(1L);
        customer2.setLastName("Doe");
        customer2.setMortgageList(new ArrayList<>());
        Optional<Customer> ofResult = Optional.of(customer2);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        try {
            String actualAddMortgageResult = mortgageService
                    .addMortgage(new MortgageRegistrationDTO(null, -1, -1, -1, null, null, -1), 1L);
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            assertEquals("value not set", e.getMessage());
        }
    }
}
