package Model;

/**
 *
 */
public class Stock {
  private String ticker;
  private int shares;
  private double cost;

  /**
   *
   * @param companyName
   * @param date
   * @param type
   * @param shares
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
   *
   * @return
   */
  String getTicker() {
    return this.ticker;
  }

  /**
   *
   * @return
   */
  int getShares() {
    return this.shares;
  }

  /**
   *
   * @param shares
   */
  void setShares(int shares) {
    this.shares = shares;
  }

  /**
   *
   * @return
   */
  double getCost() {
    return this.cost;
  }

  void setCost(double cost) {
    this.cost = cost;
  }

  @Override
  public String toString() {
    String stock_information =
            "Ticker Symbol: " + this.ticker
            + "\n" + "Total Shares Owned: " + this.shares
            + "\n" + "Total Running Cost of Stock: " + this.cost + "\n";

    return stock_information;
  }
}
