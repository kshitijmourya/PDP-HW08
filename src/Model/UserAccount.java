package model;

/**
 * This interface represents an account for transing and selling stock.
 */
public interface UserAccount {

  /**
   * Adds a portfolio to the users account. The initial portfolio is clear, with no stocks held
   * within it. To add stocks to the portfolio, the user must add them in one by one with the
   * buyStock() method.
   *
   * @param portfolioName string that will be used as the label for the portfolio. The user will use
   *                      this name to call the portfolio in other methods.
   */
  void addPortfolio(String portfolioName);

  /**
   * Removes a portfolio from a users account. All data in the portfolio will be lost. The user will
   * not have access to the stocks stored in this portfolio after removal. In the next version
   * update, we plan to include a feature to move stock from one portfolio to another and another
   * feature to automatically sell the stocks in the portfolio before removal. This will ensure the
   * user is able to keep track of running costs and profits even after removal of portfolio.
   *
   * @param portfolioName that will be removed from the useres account.
   */
  void removePortfolio(String portfolioName);

  /**
   * Buys a particular stock and adds it to the specified portfolio at the users command. If the
   * stock does not already exist in the portfolio, it will add it. If the stock does exist in the
   * portfolio, then it will add the shares to the stock within the portfolio.
   *
   * @param ticker    identifier for company to buy stock from. Can be company name or ticker
   *                  symbol.
   * @param date      the user wants to buy the stock in YYYY-MM-dd format.
   * @param type      of buy price the user wants to obtain shares at (i.e. open, close, low,
   *                  high).
   * @param shares    number of shares the user wants to buy.
   * @param portfolio to add the acquired stock to.
   */
  void buyStock(String ticker, String date, String type, int shares, String portfolio);

  /**
   * A future feature for the next version update. This method is incomplete and not ready for use
   * by the user.
   * <p></p>
   * Sells a particular stock from a specified portfolio at the users command. The stock MUST exist
   * in the portfolio to be able to sell it. The user can only sell, at maximum, the total number of
   * shares owned.
   *
   * @param ticker    code for the company to sell the stock.
   * @param shares    number of shares to sell.
   * @param portfolio portfolio ehich contains the stock the user wants to sell.
   */
  void sellStock(String ticker, int shares, String portfolio);

  /**
   * Displays the total current information within the users account. Portfolio names, the stocks
   * within each portfolio, shares owned of each stock, total running cost of each stock, total
   * profit/loss from each stock.
   *
   * @return String paragraph of user account information.
   */
  String viewAccount();
}
