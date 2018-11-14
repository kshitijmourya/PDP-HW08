package Model;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class APIData {
  private Map<String, String> nameToCode;
  private Map<String, Map<String, Map<String, Double>>> prices;
  private String apiKey = "SSO4MPHRUSM6YMEB";
  private URL url = null;


  public APIData() {
    this.nameToCode = new HashMap<>();
    this.prices = new HashMap<>();
  }


  private void callAPIToGetCode(String companyName) {
    try {
      url = new URL("https://www.alphavantage.co/query?function=SYMBOL_SEARCH&"
              + "keywords=" + companyName
              + "&apikey=" + apiKey
              + "&datatype=csv");
    } catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or "
              + "no longer works");
    }

    InputStream in = null;
    StringBuilder output = new StringBuilder();

    try {
      in = url.openStream();
      int b;

      while ((b = in.read()) != -1) {
        output.append((char) b);
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("No code data found for " + companyName);
    }

    String[] value = output.toString().split("\n");
    String[] row = value[1].split(",");
    this.nameToCode.put(companyName, row[0]);
  }

  public String searchCode(String companyName) {
    if (!this.nameToCode.containsKey(companyName)) {
      callAPIToGetCode(companyName);
    }
    return this.nameToCode.get(companyName);
  }


  private void callAPIToGetPrices(String code) {
    try {
      url = new URL("https://www.alphavantage"
              + ".co/query?function=TIME_SERIES_DAILY"
              + "&outputsize=full"
              + "&symbol"
              + "=" + code + "&apikey=" + apiKey + "&datatype=csv");
    } catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or "
              + "no longer works");
    }

    InputStream in = null;
    StringBuilder output = new StringBuilder();

    try {
      in = url.openStream();
      int b;
      while ((b = in.read()) != -1) {
        output.append((char) b);
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + code);
    }


    Map<String, Map<String, Double>> res = new HashMap<String, Map<String, Double>>();
    String[] value = output.toString().split("\n");
    String[] type = {"open", "high", "low", "close", "volume"};

    for (int i = 1; i < value.length; i++) {
      String[] row = value[i].split(",");
      res.put(row[0], new HashMap<String, Double>());
      for (int j = 1; j < row.length; j++) {
        res.get(row[0]).put(type[j - 1], Double.parseDouble(row[j]));
      }
    }
    this.prices.put(code, res);
  }


  public Double getPrices(String tickrCode, String date, String type) throws IllegalArgumentException {
    if (!prices.containsKey(tickrCode)) {
      callAPIToGetPrices(tickrCode);
    }
    Map<String, Map<String, Double>> res = this.prices.get(tickrCode);
    Double result = 0.0;
    try {
      result = res.get(date).get(type);
    } catch (Exception e) {
      e.printStackTrace();
      throw new IllegalArgumentException("No Info");

    }
    return result;
  }
}
