package View;

import java.io.InputStreamReader;


import Controller.AppController;
import Model.Account;

public class ViewMain {

  public static void main(String[] args) {

    //System.out.println(java.time.LocalTime.now().getHour());
    new AppController(new InputStreamReader(System.in), System.out).go();



  }
}