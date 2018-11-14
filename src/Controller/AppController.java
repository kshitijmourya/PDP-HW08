package Controller;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import Model.Account;
import Model.UserAccount;


public class AppController implements IAppController {
  private final Readable in;
  private final Appendable out;

  private String input(Scanner scan) throws IllegalStateException {
    String string = "";
    try {
      string = scan.next();
    } catch (NoSuchElementException e) {
      throw new IllegalStateException();
    }
    return string;
  }

  private void output(String st) throws IllegalStateException {
    try {
      this.out.append(st);
    } catch (IOException e) {
      throw new IllegalStateException("Output fails.");
    }
  }

  public AppController(Readable rd, Appendable ap) throws IllegalArgumentException {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException("Input and output should not be null.");
    }
    this.in = rd;
    this.out = ap;
  }

  public void go() throws IllegalArgumentException, IllegalStateException {
    UserAccount model = new Account();

    Scanner sc = new Scanner(this.in);
    output("Please Enter the date for trading\n" +
            "YYYY-MM-DD \n");
    String date = input(sc);


    //java.time.LocalTime.now().getHour();
    //Time could be compared with above method which gives current time.
    //But it would be inefficient for development and testing purposes.

    output("Please Enter current hour in 24 hours format. It should be in between 8-14 to trade");
    String time = input(sc);
    int t = Integer.parseInt(time);
    if (t < 8 || t > 14) {
      output("Stock market is closed");
      throw new IllegalArgumentException("Stock Market is closed");

    }

    while (true) {

      output("You can input:\n " +
              "1 for creating portfolio,\n" +
              " 2 for buying stocks\n," +
              " 3 for viewing account\n" +
              "Enter Q for quiting\n");
      String command = input(sc);

      switch (command) {

        case "1":
          output("Please input the portfolio's name.\n");
          String portfolioName = input(sc);
          try {
            model.addPortfolio(portfolioName);
            output("Created a portfolio successfully.\n");
          } catch (IllegalArgumentException e) {
            output(e.getMessage());
          }
          break;


        case "2":
          output("Please input the Stocks's name.\n");
          String stockName = input(sc);
          output("Please input the number of shares.\n");
          int shares = Integer.parseInt(input(sc));
          output("Please input the Portfolio's name.\n");
          String portfolio = input(sc);

          try {
            model.buyStock(stockName, date, "open", shares, portfolio);
            output("Bought Stock successfully\n");
          } catch (IllegalArgumentException e) {
            output(e.getMessage());
          } catch (NullPointerException e) {
            output("Create a portfolio first\n");
          }
          break;

        case "3":
          output("Showing Account Details\n" +
                  "Please Enter: 1) For all portfolios \n" +
                  "2) for specific stock \n" +
                  "3) for specific stock in specific portfolio\n");
          String option = input(sc);
          try {
            model.viewAccount(option);
          } catch (IllegalArgumentException e) {
            output(e.getMessage());
          }
          output("Showing Account Details\n");
          break;

        case "q":
        case "Q":
          output("Exiting the program");
          System.exit(0);


        default:
          output("Please Enter valid response");
          break;
      }

    }
  }
}
