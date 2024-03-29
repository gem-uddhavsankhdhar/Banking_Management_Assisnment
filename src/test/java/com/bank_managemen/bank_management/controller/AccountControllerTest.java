package com.bank_managemen.bank_management.controller;

import com.bank_managemen.bank_management.entities.Accounts;
import com.bank_managemen.bank_management.services.AccountServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class AccountControllerTest {
    @Mock
    private AccountServices accountServices;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testOpenAccount() {

        Accounts account = new Accounts();
        account.setAccountId("1");
        account.setBalance(100.0);

        when(accountServices.openAccount(any(Accounts.class))).thenReturn(account);

        ResponseEntity<Accounts> response = accountController.openAccount(account);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("1", response.getBody().getAccountId());
        assertEquals(100.0, response.getBody().getBalance());

        verify(accountServices, times(1)).openAccount(any(Accounts.class));
    }

    @Test
    void testDeposit() {
        Accounts account = new Accounts();
        account.setAccountId("1");
        account.setBalance(100.0);

        when(accountServices.deposit(eq("1"), anyDouble())).thenReturn(account);

        ResponseEntity<String> response = accountController.deposit("1", 50.0);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Deposited Successful \nAccount Balance : 100.0", response.getBody());

        verify(accountServices, times(1)).deposit(eq("1"), eq(50.0));
    }
    @Test

    void testWithdraw() {

        Accounts account = new Accounts();
        account.setAccountId("1");
        account.setBalance(100.0);

        when(accountServices.withdraw(eq("1"), anyDouble())).thenReturn(account);

        ResponseEntity<String> response = accountController.withdraw("1", 50.0);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Withdrawal Successful \nAccount Balance : 100.0", response.getBody());

        verify(accountServices, times(1)).withdraw(eq("1"), eq(50.0));
    }

    @Test
    void testBalanceEnquiry() {
        Double balance = 100.0;

        when(accountServices.balanceEnquiry(eq("1"))).thenReturn(balance);

        ResponseEntity<Double> response = accountController.balanceEnquiry("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(100.0, response.getBody());

        verify(accountServices, times(1)).balanceEnquiry(eq("1"));
    }

    @Test
    void testDeleteAccount() {

        Accounts account = new Accounts();
        account.setAccountId("1");
        account.setBalance(100.0);

        when(accountServices.deleteAccount(eq("1"))).thenReturn(account);

        ResponseEntity<String> response = accountController.deleteAccount("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Account deleted successfully!", response.getBody());

        verify(accountServices, times(1)).deleteAccount(eq("1"));
    }

    @Test
    void testAccountDetails() {

        Accounts account = new Accounts();
        account.setAccountId("1");
        account.setBalance(100.0);

        when(accountServices.accountDetails(eq("1"))).thenReturn(account);

        Accounts result = accountController.accountDetails("1");

        assertNotNull(result);
        assertEquals("1", result.getAccountId());
        assertEquals(100.0, result.getBalance());

        verify(accountServices, times(1)).accountDetails(eq("1"));
    }

    @Test
    void testGetAccountsByUserId() {

        List<Accounts> accountList = new ArrayList<>();
        Accounts account1 = new Accounts();
        account1.setAccountId("1");
        account1.setBalance(100.0);
        accountList.add(account1);

        when(accountServices.getAccountByUserId(eq(123))).thenReturn(accountList);

        ResponseEntity<List<Accounts>> response = accountController.getAccountsByUserId(123);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("1", response.getBody().get(0).getAccountId());
        assertEquals(100.0, response.getBody().get(0).getBalance());

        verify(accountServices, times(1)).getAccountByUserId(eq(123));
    }
}
