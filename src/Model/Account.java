package Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * This class represents an entire user account for the buying and selling of stocks. The Class
 * stores the information of these transactions in a hashmap to be called upon when needed.
 */
public class Account implements UserAccount {
  Map<String, LinkedList<Stock>> portfolios;

  /**
   * Constructor for user account. No parameters should be given.
   */
  public Account() {
    this.portfolios = new HashMap<String, LinkedList<Stock>>();
  }

  /**
   * Adds a portfolio to the users account. The initial portfolio is clear, with no stocks held
   * within it. To add stocks to the portfolio, the user must add them in one by one with the
   * buyStock() method.
   *
   * @param portfolioName string that will be used as the label for the portfolio. The user will
   *                      use this name to call the portfolio in other methods.
   */
  @Override
  public void addPortfolio(String portfolioName) throws IllegalArgumentException {
    if (this.portfolios.containsKey(portfolioName)) {
      throw new IllegalArgumentException("Portfolio already exists.");
    }
    if (portfolioName.equals("")) {
      throw new IllegalArgumentException("Please name the portfolio");
    }
    this.portfolios.put(portfolioName, new LinkedList<Stock>());
  }

  /**
   * Removes a portfolio from a users account. All data in the portfolio will be lost. The user
   * will not have access to the stocks stored in this portfolio after removal. In the next version
   * update, we plan to include a feature to move stock from one portfolio to another and another
   * feature to automatically sell the stocks in the portfolio before removal. This will ensure the
   * user is able to keep track of running costs and profits even after removal of portfolio.
   *
   * @param portfolioName that will be removed from the useres account.
   */
  @Override
  public void removePortfolio(String portfolioName) throws IllegalArgumentException {
    if (!this.portfolios.containsKey(portfolioName)) {
      throw new IllegalArgumentException("Portfolio does not exist.");
    }
    if (portfolioName.equals("")) {
      throw new IllegalArgumentException("Please name the portfolio.");
    }
    this.portfolios.remove(portfolioName);
  }

  /**
   * Buys a particular stock and adds it to the specified portfolio at the users command.
   * If the stock does not already exist in the portfolio, it will add it. If the stock does exist
   * in the portfolio, then it will add the shares to the stock within the portfolio.
   *
   * @param ticker identifier for company to buy stock from. Can be company name or ticker symbol.
   * @param date the user wants to buy the stock in YYYY-MM-dd format.
   * @param  type of buy price the user wants to obtain shares at (i.e. open, close, low, high).
   * @param shares number of shares the user wants to buy.
   * @param portfolio to add the acquired stock to.
   */
  @Override
  public void buyStock(String ticker, String date, String type, int shares, String portfolio) {


    boolean exists = false;

    Stock stock_bought = new Stock(ticker, date, type, shares);
    Stock stock_owned;

    for (Stock s : this.portfolios.get(portfolio)) {
      if (s.getTicker().equals(ticker)) {
        stock_owned = s;

        int new_shares = stock_bought.getShares() + stock_owned.getShares();
        double running_cost = s.getCost() + stock_bought.getCost();
        s.setShares(new_shares);
        s.setCost(running_cost);
        exists = true;
      }
    }

    if (!exists) {
      this.portfolios.get(portfolio).add(stock_bought);
    }
  }

  /**
   * A future feature for the next version update. This method is incomplete and not ready
   * for use by the user.
   * <p></p>
   * Sells a particular stock from a specified portfolio at the users command. The stock MUST exist
   * in the portfolio to be able to sell it. The user can only sell, at maximum, the total number
   * of shares owned.
   *
   * @param ticker code for the company to sell the stock.
   * @param shares number of shares to sell.
   * @param portfolio portfolio ehich contains the stock the user wants to sell.
   */
  @Override
  public void sellStock(String ticker, int shares, String portfolio) {
    boolean exists = false;

    for (Stock s : this.portfolios.get(portfolio)) {
      if (s.getTicker().equals(ticker)) {
        int remaining_shares = s.getShares() - shares;

        if (remaining_shares > 0) {
          s.setShares(remaining_shares);
        } else if (remaining_shares < 0) {
          System.out.println("Not enough shares owned to make this sale.");
        } else {
          this.portfolios.get(portfolio).remove(s);
        }

        exists = true;
      }
    }

    if (!exists) {
      System.out.println("This stock does not exist in this portfolio.");
    }
  }

  /**
   * Displays the total current information within the users account.
   * Portfolio names, the stocks within each portfolio, shares owned of each stock, total running
   * cost of each stock, total profit/loss from each stock.
   *
   * @return String paragraph of user account information.
   */
  @Override
  public String viewAccount() {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM");
    Date today = new Date();

    if (this.portfolios.isEmpty()) {
      return "User has no active portfolios.";
    } else {
      String account_information = "";
      Set<String> keys = this.portfolios.keySet();
      int total_value = 0;
      int total_costs = 0;
          for (String k : keys) {
            account_information += "Portfolio: " + k + "\n";
            for (Stock s : this.portfolios.get(k)) {
              account_information +=  s.toString();

              APIData stock_data = new APIData();
              String code = stock_data.searchCode(s.getTicker());
              double price = stock_data.getPrices(code, formatter.format(today), "low");
              double value = price * s.getShares();
              total_costs += s.getCost();
              total_value += value;
              account_information += "Current Value: " + value + "\n\n";
            }
          }
          int profit = total_value - total_costs;
          account_information += "Total Account Profit: " + profit;

      return account_information;
    }
  }
}
