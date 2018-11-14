package Model;

public class MockModel implements UserAccount {
  private StringBuilder log;

  private MockModel(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void addPortfolio(String portfolioName) {
    log.append("Add");
    log.append(portfolioName);
    log.append("\n");
  }


  @Override
  public void removePortfolio(String portfolioName) {
    log.append("remove");
    log.append(portfolioName);
    log.append("\n");

  }


  @Override
  public void buyStock(String ticker, String date, String type, int shares, String portfolio) {
    log.append("Buy");
    log.append(" ");
    log.append(ticker);
    log.append(" ");
    log.append(date);
    log.append(" ");
    log.append(type);
    log.append(" ");
    log.append(shares);
    log.append(" ");
    log.append(portfolio);
    log.append("\n");

  }

  @Override
  public void sellStock(String ticker, int shares, String portfolio) {

    log.append("Sell");
    log.append(" ");
    log.append(ticker);
    log.append(" ");
    log.append(shares);
    log.append(" ");
    log.append(portfolio);
  }


  @Override
  public String viewAccount() {
    log.append("View Account");
    log.append("\n");
    return null;
  }
}
