package Model;

public class Stock {
  private String ticker;
  private int shares;
  private double cost;

  Stock(String companyName, String date, String type, int shares) {
    this.shares = shares;

    APIData stock_data = new APIData();
    String code = stock_data.searchCode(companyName);
    double price = stock_data.getPrices(code, date, type);

    this.ticker = companyName;

    if (this.cost > 0) {
      this.cost = this.cost + price * shares;
    } else {
      this.cost = price * shares;
    }
  }

  String getTicker() {
    return this.ticker;
  }

  int getShares() {
    return this.shares;
  }

  void setShares(int shares) {
    this.shares = shares;
  }

  double getCost() {
    return this.cost;
  }

  void setCost(double cost) {
    this.cost = cost;
  }
}
