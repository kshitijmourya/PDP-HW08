package Model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Account implements UserAccount{
  Map<String, LinkedList<Stock>> portfolios;

  public Account() {
    this.portfolios = new HashMap<String, LinkedList<Stock>>();
  }

  /**
   * Adds a portfolio to the users account.
   */
  @Override
  public void addPortfolio(String portfolioName) {
    this.portfolios.put(portfolioName, new LinkedList<Stock>());
  }

  /**
   * Removes a portfolio from a users account.
   */
  @Override
  public void removePortfolio(String portfolioName) {
    this.portfolios.remove(portfolioName);
  }

  /**
   * Buys a particular stock and adds it to the specified portfolio at the users command. If the
   * stock does not already exist in the portfolio, it will add it. If the stock does exist in the
   * portfolio, then it will add the shares to the stock within the portfolio.
   */
  @Override
  public void buyStock(String ticker, int shares) {

  }

  /**
   * Sells a particular stock from a specified portfolio at the users command. The stock MUST exist
   * in the portfolio to be able to sell it. The user can only sell, at maximum, the total number of
   * shares owned.
   */
  @Override
  public void sellStock(String ticker, int shares) {

  }

  /**
   * Displays the total current information within the users account. Portfolio names, the stocks
   * within each portfolio, shares owned of each stock, total running cost of each stock, total
   * profit/loss from each stock.
   * <p></p>
   * It should also provide a summary of total running costs and overall profit/loss in the
   * account.
   *
   * @return String paragraph of user account information.
   */
  @Override
  public String viewAccount() {
    return null;
  }
}
