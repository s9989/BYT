package b_Money;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));

		SweBank.deposit("Alice", new Money(1000000, SEK));
	}
	
	@Test
	public void testAddRemoveTimedPayment() {
		testAccount.addTimedPayment("1", 2, 3, new Money(1000000, SEK), SweBank, "test");
		assertTrue("testAddRemoveTimedPayment addition",  testAccount.timedPaymentExists("1"));
		assertFalse("testAddRemoveTimedPayment addition",  testAccount.timedPaymentExists("2"));

		testAccount.removeTimedPayment("1");
		assertFalse("testAddRemoveTimedPayment addition",  testAccount.timedPaymentExists("1"));
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		Money money = new Money(1000, SEK);
		Money base = testAccount.getBalance();
		testAccount.addTimedPayment("1", 2, 3, money, SweBank, "Alice");
		
		testAccount.tick();
		assertNotEquals(testAccount.getBalance().getAmount(), base.sub(money).getAmount());
		testAccount.tick();
		assertNotEquals(testAccount.getBalance().getAmount(), base.sub(money).getAmount());
		testAccount.tick();
		assertNotEquals(testAccount.getBalance().getAmount(), base.sub(money).getAmount());
		testAccount.tick();
		assertEquals(testAccount.getBalance().getAmount(), base.sub(money).getAmount());
		testAccount.tick();
		assertEquals(testAccount.getBalance().getAmount(), base.sub(money).getAmount());
	}

	@Test
	public void testAddWithdraw() {
		Money startMoney = testAccount.getBalance();
		Money deposit = new Money(150000, SEK);
		testAccount.withdraw(deposit);
		Money endMoney = testAccount.getBalance();
		assertNotEquals("testAddWithdraw", startMoney, endMoney);
		assertEquals("testAddWithdraw", endMoney, startMoney.sub(deposit));
	}
	
	@Test
	public void testGetBalance() {
		assertEquals(testAccount.getBalance(), new Money(10000000, SEK));
	}
}
