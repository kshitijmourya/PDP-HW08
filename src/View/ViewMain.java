package view;

import java.io.InputStreamReader;

import controller.AppController;

public class ViewMain {

  /**
   * main method to run the application.
   * @param args args.
   */
  public static void main(String[] args) {

    //System.out.println(java.time.LocalTime.now().getHour());
    new AppController(new InputStreamReader(System.in), System.out).runApp();


  }
}