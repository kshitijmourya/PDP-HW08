package Model;

public class Stock implements IStock{
  private String open;
  private String timestamp;
  private String high;
  private String low;
  private String ticker;
  private int shares;
  private double cost;

  Stock(String tickerSymbol, int shares) {
    String stock_data = APIData.main(tickerSymbol);

    String[] temp_holder = stock_data.split(",");

    this.ticker = tickerSymbol;
    this.timestamp = temp_holder[0];
    this.open = temp_holder[0];
    this.high = temp_holder[0];
    this.low = temp_holder[0];

    this.shares = shares;
    this.cost = cost; // one of the open | high | low
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
