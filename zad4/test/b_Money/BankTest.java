package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	@Test
	public void testGetName() {
		assertEquals(SweBank.getName(), "SweBank");
	}

	@Test
	public void testGetCurrency() {
		assertEquals(SweBank.getCurrency(), SEK);
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException { 
		SweBank.openAccount("John");
		SweBank.openAccount("Anna");
		SweBank.deposit("John", new Money(1000, DKK));
	}

	@Test(expected = AccountExistsException.class)
	public void testOpenAccountException() throws AccountExistsException {
		SweBank.openAccount("John");
		SweBank.openAccount("John");
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		SweBank.deposit("Bob", new Money(10000, SEK));
		assertEquals(SweBank.getBalance("Bob"), (Integer)10000);
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		SweBank.deposit("Bob", new Money(10000, SEK));
		SweBank.withdraw("Bob", new Money(3000, SEK));
		assertEquals(SweBank.getBalance("Bob"), (Integer)7000);
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		assertEquals(SweBank.getBalance("Bob"), (Integer)0);
	}
	
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		SweBank.deposit("Bob", new Money(10000, SEK));
		SweBank.transfer("Bob", "Ulrika", new Money(10000, SEK));
		assertEquals(SweBank.getBalance("Bob"), (Integer)0);
		assertEquals(SweBank.getBalance("Ulrika"), (Integer)10000);
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		SweBank.deposit("Bob", new Money(10000, SEK));
		SweBank.addTimedPayment("Bob", "1", 2, 3, new Money(10000, SEK), Nordea, "Bob");
		SweBank.tick();
		assertNotEquals(Nordea.getBalance("Bob"), (Integer)10000);
		SweBank.tick();
		assertNotEquals(Nordea.getBalance("Bob"), (Integer)10000);
		SweBank.tick();
		assertNotEquals(Nordea.getBalance("Bob"), (Integer)10000);
		SweBank.tick();
		assertEquals(Nordea.getBalance("Bob"), (Integer)10000);
		SweBank.tick();
		assertEquals(Nordea.getBalance("Bob"), (Integer)10000);

		
	}
}
