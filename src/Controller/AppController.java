package Controller;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import Model.UserAccount;


public class AppController implements IAppController {
  private final Readable in;
  private final Appendable out;

  private String input(Scanner scan) throws IllegalStateException {
    String st = "";
    try {
      st = scan.next();
    } catch (NoSuchElementException e) {
      throw new IllegalStateException();
    }
    return st;
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

  public void go(UserAccount model) throws IllegalArgumentException, IllegalStateException {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }

    Scanner sc = new Scanner(this.in);
    output("You can input: 1 for creating portfolio," +
            " 2 for buying stocks, 3 for viewing account..etc etc... " +
            "Enter Q for quiting\n");
    String command = input(sc);


    if (command.equals("1")) {
      output("Please input the portfolio's name.\n");
      String portfolioName = input(sc);
      try {
        model.addPortfolio(portfolioName);
      } catch (IllegalArgumentException e) {
        output(e.getMessage());
      }
      output("Created a portfolio successfully.\n");
    }

    if (command.equals("2")) {
      output("Please input the Stocks's name.\n");
      String stockName = input(sc);
      output("Please input the number of shares.\n");
      int shares = Integer.parseInt(input(sc));
      output("Please input the Portfolio's name.\n");
      String portfolioName = input(sc);


      try {
        model.buyStock(stockName, "2017-11-8", "open", shares, portfolioName);
      } catch (IllegalArgumentException e) {
        output(e.getMessage());
      }
      output("Bought Stock successfully\n");
    }

    if (command.equals("3")) {
      output("Showing Account Details\n");
      try {
        model.viewAccount();
      } catch (IllegalArgumentException e) {
        output(e.getMessage());
      }
      output("Showing Account Details\n");
    }

  }

}
