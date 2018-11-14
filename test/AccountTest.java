import org.junit.Before;
import org.junit.Test;

import model.Account;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNotEquals;


public class AccountTest {
  Account testTrade;

  /**
   * Initialization.
   */
  @Before
  public void setUp() {
    testTrade = new Account();
    testTrade.addPortfolio("Technology");
    testTrade.buyStock("apple", "2018-11-08", "open", 10, "Technology");
    testTrade.buyStock("amd", "2018-11-08", "open", 10, "Technology");
    testTrade.buyStock("apple", "2018-11-13", "open", 20, "Technology");
    testTrade.buyStock("microsoft", "2018-11-08", "open", 10, "Technology");
    testTrade.buyStock("intel", "2018-11-08", "open", 10, "Technology");

  }

  @Test
  public void testAddPortfolio() {
    testTrade.addPortfolio("Technology2");
    String early_ports = testTrade.viewAccount();
    testTrade.addPortfolio("Retail");
    String all_ports = testTrade.viewAccount();
    testTrade.removePortfolio("Retail");

    assertNotEquals(early_ports, all_ports);
    assertEquals(early_ports, testTrade.viewAccount());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddRemove1() {
    testTrade.buyStock("", "2018-11-08", "open", 10, "Technology");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddRemove2() {
    testTrade.addPortfolio("");

  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddRemove3() {
    testTrade.removePortfolio("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddRemove4() {
    testTrade.addPortfolio("Farming");
    testTrade.addPortfolio("Farming");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddRemove5() {
    testTrade.removePortfolio("Retirement");
  }
}