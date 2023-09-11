import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.Scanner;

class Main {
  public static void main(String[] args) {

    boolean go = false;
    Team team1 = new Team();
    // team1.setUp();
    Scanner user = new Scanner(System.in);

    System.out
        .println("\nWelcome to the Iconic Shows Football League (ISFL)! You have become the General Manager of the "
            + team1.getName() + ".\nType \"play\" to get started or \"help\" to learn how the game works (highly recommended for new players):");

    String input = user.next();
    while (!input.equals("play") && !input.equals("help")) {
      System.out.println("Type \"play\" to get started or \"help\" to learn how the game works:");
      input = user.next();
    }

    if (input.equals("help")) {
      System.out.println("\nYou are about to become the General Manager of a television-themed football theme. As the GM, you will manage the roster.");
      team1.time();
      team1.lessTime();
      System.out.println("\nThis roster includes 3 offensive positions (QB, RB, WR) and 3 defensive positions (EDGE, LB, CB). Each player has an ovr (skill rating) that contributes to their side's ovr and the team ovr. The QB and EDGE positions are weighted slighly heavier in their respective sides. Younger players tend to improve in ovr while older players tend to regress in ovr"); 
      team1.time();
      team1.time();
      team1.lessTime();
      System.out.println("\nYou will also simulate games and seasons. The goal is to win as many superbowls as possible during your tenure as GM. After each season, there will be a draft and a free agency period. These events are opportunities to improve your team and set yourself up to win more in the following season. At the end of your session, you will get an overview of each season and your notable statistics, have fun!");
      team1.time();
      team1.time();
      team1.lessTime();
      System.out.println("\nHope that helped! Type \"play\" to get started:");
    

    while (!user.next().equals("play")) {
      System.out.println("Type \"play\" to get started:");
    }
      
    }

    go = true;

    while (go) {
      System.out.println("\nThe " + team1.getYr() + " " + team1.getName() + "\n");
      int use = team1.regularSeason();
      team1.postSeason(use);

      team1.lessTime();
      System.out
          .println("\nTime for a new season! Type either \"STOP\" to stop playing or anything else to keep playing.");
      if (user.next().equals("STOP")) {
        team1.lessTime();
        System.out.println("\nHere are the results from your session:\n");

        ArrayList<String> alret = new ArrayList<String>();

        alret = team1.getHistory();
        for (String s : alret) {
          System.out.println(s);
        }
        System.out.println("\n" + team1.sessionNotables());
        System.out.print("\n\nThanks for playing!\nCredits:\nDeveloper: Krish\nSpecial Thanks to Jackson for helping w/ player naming");
        go = false;
      }
      if (go) {
        team1.preSeason(use);
      }
    }
  }
}