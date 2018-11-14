package controller;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import model.Account;
import model.UserAccount;


/**
 * This class represents the controller for How to Invest for Dummies Application.
 */
public class AppController implements IAppController {
  private final Readable in;
  private final Appendable out;

  /**
   * This method scans for user inputs, one key stroke at a time.
   *
   * @param scan key stroke input.
   * @return String of key stroke inputs from the user.
   * @throws IllegalStateException if anything else is pressed in by the user.
   */
  private String input(Scanner scan) throws IllegalStateException {
    String string = "";
    try {
      string = scan.next();
    } catch (NoSuchElementException e) {
      throw new IllegalStateException();
    }
    return string;
  }

  /**
   * Builds the output message that is supplied to the user.
   *
   * @param st string that is being supplied to the method, one at a time to build output message.
   * @throws IllegalStateException if the operation is interrupted.
   */
  private void output(String st) throws IllegalStateException {
    try {
      this.out.append(st);
    } catch (IOException e) {
      throw new IllegalStateException("Output fails.");
    }
  }

  /**
   * Constructor for the controller. It takes in Readable object and appendable object. If both of
   * them are null, it throws illegal argument exception.
   *
   * @param rd Readable object
   * @param ap Appendable object
   */
  public AppController(Readable rd, Appendable ap) throws IllegalArgumentException {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException("Input and output should not be null.");
    }
    this.in = rd;
    this.out = ap;
  }

  /**
   * It asks user for date, time, then it asks user for various inputs which directs the input to
   * the appropriate model methods. Shows the output reulsts to the user.
   */
  public void runApp() throws IllegalArgumentException, IllegalStateException {
    UserAccount model = new Account();

    Scanner sc = new Scanner(this.in);
    output("Please Enter the date for trading\n"
            + "YYYY-MM-DD \n\n"
            + "Please do not enter dates of holidays as the market is CLOSED on holidays.\n");
    String date = input(sc);
    Scanner date_check = new Scanner(date).useDelimiter("-");

    int count = 0;
    while (date_check.hasNext()) {
      String check = date_check.next();
      if (count > 2) {
        throw new IllegalArgumentException("Invalid Date.");
      } else if (count == 0 && check.length() != 4) {
        throw new IllegalArgumentException("Invalid Date.");
      } else if (count == 1 && (check.length() != 2
              || Integer.valueOf(check) < 1
              || Integer.valueOf(check) > 12)) {
        throw new IllegalArgumentException("Invalid Date.");
      } else if (count == 2 && (check.length() != 2
              || Integer.valueOf(check) < 1
              || Integer.valueOf(check) > 31)) {
        throw new IllegalArgumentException("Invalid Date.");
      }

      count++;
    }

    //java.time.LocalTime.now().getHour();
    //Time could be compared with above method which gives current time.
    //But it would be inefficient for development and testing purposes.

    output("\nPlease Enter current hour in 24 hours format. " +
            "It should be in between 8-14 to trade\n");
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
                  "2) for specific portfolio \n" +
                  "3) for specific stock in specific portfolio\n");
          String option = input(sc);
          try {
            model.viewAccount();
          } catch (IllegalArgumentException e) {
            output(e.getMessage());
          }
          output("Showing Account Details\n");
          break;

        case "q":
        case "Q":
          output("Exiting the program");
          System.exit(0);
          break;

        default:
          output("Please Enter valid response");
          break;
      }

    }
  }
}
