import org.junit.Before;
import org.junit.Test;

import Model.Account;

import static org.junit.Assert.*;

public class AccountTest {
  Account testTrade;

  @Before
  public void setUp() {
    testTrade = new Account();
  }

  @Test
  public void testAddPortfolio() {
    testTrade.addPortfolio("Technology");
  }

  @Test
  public void testAddStock() {
    testTrade.addPortfolio("Technology");
    testTrade.buyStock("apple", "2018-11-08", "open", 10, "Technology");
  }
}