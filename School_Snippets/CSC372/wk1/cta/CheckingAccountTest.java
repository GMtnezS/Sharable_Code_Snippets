// package wk8.cta;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

import classes.CheckingAccount;

public class CheckingAccountTest {
    private CheckingAccount account;

    @Before
    public void setUp() {
        account = new CheckingAccount("John", "Doe", 101, 1.5);
    }

    @Test
    public void testFirstNameSetterGetter() {
        account.setFirstName("Jane");
        assertEquals("Jane", account.getFirstName());
    }

    @Test
    public void testLastNameSetterGetter() {
        account.setLastName("Smith");
        assertEquals("Smith", account.getLastName());
    }

    @Test
    public void testAccountIDSetterGetter() {
        account.setAccountID(202);
        assertEquals(202, account.getAccountID());
    }

    @Test
    public void testInitialBalance() {
        assertEquals(0.0, account.getBalance(), 0.001);
    }

    @Test
    public void testDeposit() {
        account.deposit(500);
        assertEquals(500.0, account.getBalance(), 0.001);
    }

    @Test
    public void testValidWithdrawal() {
        account.deposit(500);
        account.withdrawal(200);
        assertEquals(300.0, account.getBalance(), 0.001);
    }

    @Test
    public void testOverdraftWithdrawal() {
        account.processWithdrawal(50); // Overdraft fee applies
        assertEquals(-80.0, account.getBalance(), 0.001);
    }

    @Test
    public void testInterestRate() {
        assertEquals(1.5, account.getInterestRate(), 0.001);
    }

    @Test
    public void testAccountSummary() {
        account.deposit(200);
        account.withdrawal(50);
        account.accountSummary(); 
        assertEquals(150.0, account.getBalance(), 0.001);
    }

    @Test
    public void testDisplayAccount() {
        account.deposit(100);
        account.processWithdrawal(200);
        account.displayAccount(); 
        assertEquals(-130.0, account.getBalance(), 0.001);
    }
}