package Model;

public interface UserAccount {

  /**
   * Adds a portfolio to the users account.
   */
  void addPortfolio(String portfolioName);

  /**
   * Removes a portfolio from a users account.
   */
  void removePortfolio(String portfolioName);

  /**
   * Buys a particular stock and adds it to the specified portfolio at the users command.
   * If the stock does not already exist in the portfolio, it will add it.
   * If the stock does exist in the portfolio, then it will add the shares to the stock within the
   * portfolio.
   */
  void buyStock(String ticker, String date, String type, int shares, String portfolio);

  /**
   * Sells a particular stock from a specified portfolio at the users command. The stock MUST exist
   * in the portfolio to be able to sell it. The user can only sell, at maximum, the total number
   * of shares owned.
   */
  void sellStock(String ticker, int shares, String portfolio);

  /**
   * Displays the total current information within the users account.
   * Portfolio names, the stocks within each portfolio, shares owned of each stock, total running
   * cost of each stock, total profit/loss from each stock.
   * <p></p>
   * It should also provide a summary of total running costs and overall profit/loss in the account.
   *
   * @return String paragraph of user account information.
   */
  String viewAccount();
}
