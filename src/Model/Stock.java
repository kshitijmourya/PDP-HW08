package Model;

/**
 * This class represents a single stock interest, to be stores in the users portfolio.
 */
class Stock {
  private String ticker;
  private int shares;
  private double cost;

  /**
   * Constructor for a single stock object. It will hold the total dealings with a particular
   * company in shares, cost of stocks purchased, and the ticker symbol for the company.
   *
   * @param companyName or ticker symbol that can be used to look the company up.
   * @param date that the stock is going to be bought.
   * @param type of price the shares will be bought at (i.e. open, close, high, low)
   * @param shares to be boughtfor the company.
   */
  Stock(String companyName, String date, String type, int shares) {
    this.shares = shares;

    APIData stock_data = new APIData();
    String code = stock_data.searchCode(companyName);
    double price = stock_data.getPrices(code, date, type);

    this.ticker = code;

    if (this.cost > 0) {
      this.cost = this.cost + price * shares;
    } else {
      this.cost = price * shares;
    }
  }

  /**
   * Get ticker symbol from this stock object.
   *
   * @return ticker symbol as a String.
   */
  String getTicker() {
    return this.ticker;
  }

  /**
   * Get the number of shares owned in this stock.
   *
   * @return shares owned of this stock as an int.
   */
  int getShares() {
    return this.shares;
  }

  /**
   * Set the number of shares owned in this stock.
   *
   * @param shares to be added to the current stock object.
   */
  void setShares(int shares) {
    this.shares = shares;
  }

  /**
   * Get the total running cost of all the shares in this stock.
   *
   * @return costs of buying shares of this stock as a double.
   */
  double getCost() {
    return this.cost;
  }

  /**
   * Set the total running cost of buying shares of this stock.
   *
   * @param cost to be added to the total running cost of this stock.
   */
  void setCost(double cost) {
    this.cost = cost;
  }

  /**
   * Converts all the contents of this stock into an easy to read String of information.
   *
   * @return String of stock object summary.
   */
  @Override
  public String toString() {
    String stock_information =
            "Ticker Symbol: " + this.ticker
            + "\n" + "Total Shares Owned: " + this.shares
            + "\n" + "Total Running Cost of Stock: " + this.cost + "\n";

    return stock_information;
  }
}
