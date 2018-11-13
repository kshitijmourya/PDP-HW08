package Model;

public class Stock implements IStock{
  private String ticker;
  private int shares;
  private double cost;

  Stock(String tickerSymbol, String date, String type, int shares) {
    this.ticker = tickerSymbol;
    this.shares = shares;

    APIData stock_data = new APIData();
    String code = stock_data.searchCode(tickerSymbol);
    double price = stock_data.getPrices(code, date, type);

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
