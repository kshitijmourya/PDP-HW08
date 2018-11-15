package Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Account implements UserAccount {
  Map<String, LinkedList<Stock>> portfolios;

  public Account() {
    this.portfolios = new HashMap<String, LinkedList<Stock>>();
  }

  /**
   * Adds a portfolio to the users account.
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
   * Removes a portfolio from a users account.
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
   * Buys a particular stock and adds it to the specified portfolio at the users command. If the
   * stock does not already exist in the portfolio, it will add it. If the stock does exist in the
   * portfolio, then it will add the shares to the stock within the portfolio.
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
   * Sells a particular stock from a specified portfolio at the users command. The stock MUST exist
   * in the portfolio to be able to sell it. The user can only sell, at maximum, the total number of
   * shares owned.
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
