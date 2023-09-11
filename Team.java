import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.Scanner;
import java.util.Arrays;

//import java.util.Math.*;
/* 
  To do:


add aging / development - refine with randomness, display ovr diff from prev yr, add forced retirement

adjust scoring ?

add salaries

make playoffs harder than regular season (make ovr lower in POs?)



*/
public class Team {

  private String name;

  private double offOVR; // transform to doubles
  private double defOVR;
  private int OVR;
  private int offC;
  private int defC;
  private int ovrC;

  private int wins;
  private int yr = 2023;
  // players
  private Player QB;
  private Player RB;
  private Player WR;
  private Player CB;
  private Player EDGE;
  private Player LB;

  // history purposes
  private int sessionWins;
  private int sessionLosses;
  private int POappear;
  private int SBappear;
  private int SBwins;
  private ArrayList<String> History;

  private int draftPO;
  String[] stringArray = new String[] { 
    "Dorne Defenders        ", 
    "King's Landing Knights ", 
    "Westeros White Walkers ",
    "Albuquerque Blues      ", 
    "Scranton Stranglers    ", 
    "Pawnee Penguins        ", 
    "Greendale Humans       ", 
    "New York 30Rockers     ",
    "Orange County Arresters", 
    "Winterfell Wizards     ", 
    "Dunder Mifflin Devils  ", 
    "Springfield Isotopes   ", 
    "Hawkins Hawks          ",
    "Crenshaw Cavaliers     ", 
    "Quahog Quagmires       " };
  private ArrayList<String> Teams = new ArrayList<String>(Arrays.asList(stringArray));
  private ArrayList<String> teamKeep = new ArrayList<String>();
 // private ArrayList<String> POTeams = new ArrayList<String>();

  private Scanner howMany = new Scanner(System.in);

  public Team() {
//set team name
    name = Teams.remove((int) (Math.random() * 15));
    while(name.substring(name.length() - 1).equals(" ")) {
      name = name.substring(0, name.length()-1);
    }

    for (String s : Teams) {
      teamKeep.add(s);
    }
//set PO teams
    

    QB = new Player();
    RB = new Player();
    WR = new Player();
    EDGE = new Player();
    LB = new Player();
    CB = new Player();

    offOVR = ((QB.getOvr() * 0.4) + (RB.getOvr() * 0.3) + (WR.getOvr() * 0.3));
    defOVR = ((EDGE.getOvr() * 0.4) + (LB.getOvr() * 0.3) + (CB.getOvr() * 0.3));

    OVR = (int) ((offOVR + defOVR) / 2);

    wins = 0;

    draftPO = 0; // div = 1, ccfc+ = 2
    // stillGoing = true;

    History = new ArrayList<String>();

  }

  // public void setUp() {}

  public void preSeason(int wins) {
    offC = (int)offOVR;
  
    defC = (int)defOVR;
    ovrC = (int)OVR;
    QB.aging();
    RB.aging();
    WR.aging();
    EDGE.aging();
    LB.aging();
    CB.aging();
    yr++;
    lessTime();
    Draft(wins);
    lessTime();
    freeAgency();

  }

  public void freeAgency() {

    Player FAqb = new Player();
    Player FArb = new Player();
    Player FAwr = new Player();
    Player FAedge = new Player();
    Player FAlb = new Player();
    Player FAcb = new Player();

    if (Math.random() < .05) {
      FAqb.setOvr(FAqb.getOvr() + (int) (Math.random() * 30));
      FArb.setOvr(FArb.getOvr() + (int) (Math.random() * 30));
      FAwr.setOvr(FAwr.getOvr() + (int) (Math.random() * 30));
      FAedge.setOvr(FAedge.getOvr() + (int) (Math.random() * 30));
      FAlb.setOvr(FAlb.getOvr() + (int) (Math.random() * 30));
      FAcb.setOvr(FAcb.getOvr() + (int) (Math.random() * 30));
    } else if (Math.random() < .1) {
      FAqb.setOvr(FAqb.getOvr() + (int) (Math.random() * 20));
      FArb.setOvr(FArb.getOvr() + (int) (Math.random() * 20));
      FAwr.setOvr(FAwr.getOvr() + (int) (Math.random() * 20));
      FAedge.setOvr(FAedge.getOvr() + (int) (Math.random() * 20));
      FAlb.setOvr(FAlb.getOvr() + (int) (Math.random() * 20));
      FAcb.setOvr(FAcb.getOvr() + (int) (Math.random() * 20));
    } else if (Math.random() < .5) {
      FAqb.setOvr(FAqb.getOvr() + (int) (Math.random() * 10));
      FArb.setOvr(FArb.getOvr() + (int) (Math.random() * 10));
      FAwr.setOvr(FAwr.getOvr() + (int) (Math.random() * 10));
      FAedge.setOvr(FAedge.getOvr() + (int) (Math.random() * 10));
      FAlb.setOvr(FAlb.getOvr() + (int) (Math.random() * 10));
      FAcb.setOvr(FAcb.getOvr() + (int) (Math.random() * 10));
    } else if (Math.random() < .92) {
      FAqb.setOvr(FAqb.getOvr() + (int) (Math.random() * 5));
      FArb.setOvr(FArb.getOvr() + (int) (Math.random() * 5));
      FAwr.setOvr(FAwr.getOvr() + (int) (Math.random() * 5));
      FAedge.setOvr(FAedge.getOvr() + (int) (Math.random() * 5));
      FAlb.setOvr(FAlb.getOvr() + (int) (Math.random() * 5));
      FAcb.setOvr(FAcb.getOvr() + (int) (Math.random() * 5));
    }

    System.out.println("\nFree Agency is now open!");
    lessTime();
    System.out.println("Here is your current team for reference:\n" + toString());
    lessTime();
    System.out.println("\nThis season's Free Agency:\n");
    lessTime();
    System.out.println(
        "QB:   " + FAqb.toString() +
            "\nRB:   " + FArb.toString() +
            "\nWR:   " + FAwr.toString() +
            "\nEDGE: " + FAedge.toString() +
            "\nLB:   " + FAlb.toString() +
            "\nCB:   " + FAcb.toString());

    String a = "";

    lessTime();
    System.out.println("\nType in the name of the position you'd like to sign or \"ESC\" to keep your current team.");
    a = howMany.next();

    while (!(a.equals("QB") || a.equals("RB") || a.equals("WR") || a.equals("EDGE") || a.equals("LB") || a.equals("CB")
        || a.equals("qb") || a.equals("rb") || a.equals("wr") || a.equals("edge") || a.equals("lb") || a.equals("cb")
        ||a.equals("ESC") || a.equals("esc"))) {
      System.out.println("Type in the name of the position you'd like to sign or \"ESC\" to keep your current team.");
      a = howMany.next();
    }

    if (a.equals("ESC") || a.equals("esc")) {
      return;
    }
    if (a.equals("QB") || a.equals("qb")) {
      QB = FAqb;
      return;
    }
    if (a.equals("RB") || a.equals("rb")) {
      RB = FArb;
      return;
    }
    if (a.equals("WR") || a.equals("wr")) {
      WR = FAwr;
      return;
    }
    if (a.equals("EDGE") || a.equals("edge")) {
      EDGE = FAedge;
      return;
    }
    if (a.equals("LB") || a.equals("lb")) {
      LB = FAlb;
      return;
    }
    if (a.equals("CB") || a.equals("cb")) {
      CB = FAcb;
      return;
    }

  }

  public void Draft(int win) {
    Player Dqb = new Player();
    Player Drb = new Player();
    Player Dwr = new Player();
    Player Dedge = new Player();
    Player Dlb = new Player();
    Player Dcb = new Player();

    Dqb.setAge(21 + (int) (Math.random() * 3));
    Drb.setAge(21 + (int) (Math.random() * 3));
    Dwr.setAge(21 + (int) (Math.random() * 3));
    Dedge.setAge(21 + (int) (Math.random() * 3));
    Dlb.setAge(21 + (int) (Math.random() * 3));
    Dcb.setAge(21 + (int) (Math.random() * 3));

    // decides how good ur draft class should be (based on how u performed last
    // season)

    double draftStock = (100.0 / (win / 2 + 8));

    int tank = 65;
    int bad = 62;
    int ok = 61;
    int good = 60;
    int great = 59;
    
    if (wins < 3) {
      Dqb.setOvr(tank);
      Drb.setOvr(tank);
      Dwr.setOvr(tank);
      Dedge.setOvr(tank);
      Dlb.setOvr(tank);
      Dcb.setOvr(tank);
    } else if (wins < 7) {
      Dqb.setOvr(bad);
      Drb.setOvr(bad);
      Dwr.setOvr(bad);
      Dedge.setOvr(bad);
      Dlb.setOvr(bad);
      Dcb.setOvr(bad);
    } else if (wins < 10 && draftPO < 1) {
      Dqb.setOvr(ok);
      Drb.setOvr(ok);
      Dwr.setOvr(ok);
      Dedge.setOvr(ok);
      Dlb.setOvr(ok);
      Dcb.setOvr(ok);
    } else if (wins < 13 && draftPO < 2) {
      Dqb.setOvr(good);
      Drb.setOvr(good);
      Dwr.setOvr(good);
      Dedge.setOvr(good);
      Dlb.setOvr(good);
      Dcb.setOvr(good);
    } else {
      Dqb.setOvr(great);
      Drb.setOvr(great);
      Dwr.setOvr(great);
      Dedge.setOvr(great);
      Dlb.setOvr(great);
      Dcb.setOvr(great);
    }

    Dqb.setOvr(Dqb.getOvr() + (int) (Math.random() * draftStock));
    Drb.setOvr(Drb.getOvr() + (int) (Math.random() * draftStock));
    Dwr.setOvr(Dwr.getOvr() + (int) (Math.random() * draftStock));
    Dedge.setOvr(Dedge.getOvr() + (int) (Math.random() * draftStock));
    Dlb.setOvr(Dlb.getOvr() + (int) (Math.random() * draftStock));
    Dcb.setOvr(Dcb.getOvr() + (int) (Math.random() * draftStock));

    // start draft
    System.out.println("\nWelcome to the " + yr + " ISFL Pre-season. The " + yr + " Draft is now open!");
    lessTime();
    System.out.println("Here is your current team for reference:\n" + toString());
    lessTime();
    System.out.println("\nThis season's Draft:\n");
    lessTime();
    System.out.println(
        "QB:   " + Dqb.toString() +
            "\nRB:   " + Drb.toString() +
            "\nWR:   " + Dwr.toString() +
            "\nEDGE: " + Dedge.toString() +
            "\nLB:   " + Dlb.toString() +
            "\nCB:   " + Dcb.toString());

    String a = "";

    lessTime();
    System.out.println("\nType in the name of the position you'd like to sign or \"ESC\" to keep your current team.");
    a = howMany.next();

    while (!(a.equals("QB") || a.equals("RB") || a.equals("WR") || a.equals("EDGE") || a.equals("LB") || a.equals("CB")
        || a.equals("qb") || a.equals("rb") || a.equals("wr") || a.equals("edge") || a.equals("lb") || a.equals("cb")
        ||a.equals("ESC") || a.equals("esc"))) {
      System.out.println("Type in the name of the position you'd like to draft or \"ESC\" to keep your current team.");
      a = howMany.next();
    }

    if (a.equals("ESC") || a.equals("esc")) {
      return;
    }
    if (a.equals("QB") || a.equals("qb")) {
      QB = Dqb;
      return;
    }
    if (a.equals("RB") || a.equals("rb")) {
      RB = Drb;
      return;
    }
    if (a.equals("WR") || a.equals("wr")) {
      WR = Dwr;
      return;
    }
    if (a.equals("EDGE") || a.equals("edge")) {
      EDGE = Dedge;
      return;
    }
    if (a.equals("LB") || a.equals("lb")) {
      LB = Dlb;
      return;
    }
    if (a.equals("CB") || a.equals("cb")) {
      CB = Dcb;
      return;
    }
  }

  public int regularSeason() {
    // sets up ovrs for games
    update();
    System.out.println(toString2());
    //refresh team list
    for (int i = Teams.size() - 1; i > -1; i--) {
      Teams.remove(Teams.get(i));
    }
    for (String s : teamKeep) {
        Teams.add(s);
    }
    // System.out.println(offOVR + " " + defOVR + "\n");
    // wins, what's gonna be returned
    int wins = 0;
    int simGames = 0;
    int count = 1;

    // start Reg Season
    lessTime();
    String sim;
    System.out.println(
        "Welcome to the Regular Season of the ISFL! This will be a 17 game season.\n");
   
    while (simGames < 17) {

      while (simGames < 8) {
        time();
        sim = "";

        System.out.print(
            "It is currently week " + (1 + simGames) + ". Type the corresponding letter to how many games you want to sim:");

        System.out.println("\nA: One Week\nB: One Month\nC: To Trade Deadline\nD: To Post Season");

        sim = howMany.next();

        while (!(sim.equals("A") || sim.equals("a") || sim.equals("B") || sim.equals("b") || sim.equals("C") || sim.equals("c") || sim.equals("D") || sim.equals("d"))) {
          System.out.println("Type the corresponding letter to how many games you want to sim:");
          sim = howMany.next();
        }

        if (sim.equals("A") || sim.equals("a")) {
          simGames += 1;
        } else if (sim.equals("B") || sim.equals("b")) {
          simGames += 4;
        } else if (sim.equals("C") || sim.equals("c")) {
          simGames = 8;
        } else {
          simGames = 17;
        }

        System.out.println("\nSimming game(s)...\n");

        for (int i = count; i < simGames + 1; i++) {

          String gameString;
          if (count < 10) {
          gameString = "Week  " + (count) + ": " + playGame(offOVR, defOVR);
          }
          else {
          gameString = "Week " + (count) + ": " + playGame(offOVR, defOVR);
          }
          if (gameString.substring(gameString.length() - 3).equals("WIN")) {
            wins++;
          }
          System.out.println(gameString + " " + wins + "-" + Math.abs(wins-count));
          System.out.println("");
          count++;

        }

      }

     if (simGames < 17) {
         time();
        sim = "";
        System.out.print(
            "It is now week " + (simGames) + ". Type the corresponding letter to how many games you want to sim:");
        System.out.println("\nA: One Week\nB: One Month\nC: To Post Season");
        sim = howMany.next();
        while (!(sim.equals("A") || sim.equals("a") || sim.equals("B") || sim.equals("b") || sim.equals("C") || sim.equals("c"))) {
          System.out.println("Type the corresponding letter to how many games you want to sim:");
          sim = howMany.next();
        }
        if (sim.equals("A") || sim.equals("a")) {
          simGames++;
        } else if (sim.equals("B") || sim.equals("b")) {
          if (simGames < 14) {
            simGames += 4;
          } else {
            simGames = 17;
          }
        } else {
          simGames = 17;
        }

        System.out.println("\nSimming game(s)...\n");

        for (int i = count; i < (simGames + 1); i++) {

          String gameString;
          if (count < 10) {
          gameString = "Week  " + (count) + ": " + playGame(offOVR, defOVR);
          }
          else {
          gameString = "Week " + (count) + ": " + playGame(offOVR, defOVR);
          }
          
          if (gameString.substring(gameString.length() - 3).equals("WIN")) {
            wins++;
          }
          System.out.println(gameString + " " + wins + "-" + Math.abs(wins-count));
          System.out.println("");
          count++;
        }
        }

    }
    lessTime();
    System.out.println("You went " + wins + "-" + (17 - wins) + " this year!\n");
    sessionWins += wins;
    sessionLosses += (17 - wins);
    return wins;
  }

  public void postSeason(int e) {
    boolean playoffs = false;
    boolean bye = false;

    draftPO = 0;

    System.out.println("Checking if you made playoffs this year...");

    time();

    // decide PO
    if (e > -1) {
      if (e < 9) {
        System.out.println("You didn't make the playoffs, sorry :/");
      } else if (e == 9) {
        if (Math.random() < .666) {
          System.out.println("You made playoffs!");
          playoffs = true;
        } else {
          System.out.println("You didn't make the playoffs, sorry :/");
        }
      } else if (e < 13) {
        System.out.println("You made playoffs!");
        playoffs = true;
      } else if (e == 13) {
        if (Math.random() < .600) {
          System.out.println("You made playoffs and got a first round bye!");
          playoffs = true;
          bye = true;
        } else {
          System.out.println("You made playoffs!");
          playoffs = true;
        }
      } else {
        System.out.println("You made playoffs and got a first round bye!");
        playoffs = true;
        bye = true;
      }
      lessTime();
    }

    // if no PO, add to History and ret
    if (!playoffs) {
      History.add(yr + " ISFL Season: " + e + "-" + (17 - e) + "  Playoffs: NO\n");
      return;
    }
    // if PO but no bye, Wilcard; if loss, add to history and ret else keep going
    POappear++;
    String iamuse = "";
    boolean thruPO = false;
    String sim;
    if (!bye) {
      System.out.println("\nWelcome to the Wildcard round of the " + yr + " ISFL season.");


      lessTime();
      if (!thruPO){
      System.out.print(
         "\nType the corresponding letter to how many games you want to sim:");

        System.out.println("\nA: One Week\nB: Through Playoffs");

         sim = howMany.next();

        while (!(sim.equals("A") || sim.equals("a") || sim.equals("B") || sim.equals("b"))) {
          System.out.println("Type the corresponding letter to how many games you want to sim:");
          sim = howMany.next();
        }

        if (sim.equals("B") || sim.equals("b")) {
          thruPO = true;
        }
      }

      System.out.println("\nSimming game(s)...\n");
      update();

      String wildcard = playGame(offOVR, defOVR);
      System.out.println(wildcard);
      time();
      lessTime();
      {

        if (wildcard.substring(wildcard.length() - 3).equals("WIN")) {
          System.out.println("\nCongrats, you won in the Wildcard Round!");
          draftPO = 1;
          // go to div
        } else {

          System.out.println("\nSorry you lost in the Wildcard Round.");
          History.add(yr + " ISFL Season: " + e + "-" + (17 - e) + "  Playoffs: YES - Lost in Wildcard\n");
          return;
        }
      }

    }
    // Div, same as WC
    iamuse = "";
    lessTime();
    System.out.println("\nWelcome to the Divisional round of the " + yr + " ISFL season.");

    lessTime();
    if (!thruPO){
      System.out.print(
         "\nType the corresponding letter to how many games you want to sim:");

        System.out.println("\nA: One Week\nB: Through Playoffs");

         sim = howMany.next();

        while (!(sim.equals("A") || sim.equals("a") || sim.equals("B") || sim.equals("b"))) {
          System.out.println("Type the corresponding letter to how many games you want to sim:");
          sim = howMany.next();
        }

        if (sim.equals("B") || sim.equals("b")) {
          thruPO = true;
        }
      }

    System.out.println("\nSimming game(s)...\n");
    update();

    String divisional = playGame(offOVR, defOVR);
    System.out.println(divisional);
    time();
    lessTime();
    {

      if (divisional.substring(divisional.length() - 3).equals("WIN")) {
        // go to div
        System.out.println("\nCongrats, you won in the Divisional Round!");
        draftPO = 2;
      } else {

        System.out.println("\nSorry, you lost in the Divisional Round.");
        History.add(yr + " ISFL Season: " + e + "-" + (17 - e) + "  Playoffs: YES - Lost in Divisional\n");
        return;
      }
    }
    // CCFC, same as DIV and WC
    iamuse = "";
    lessTime();
    System.out.println("\nWelcome to the Conference Championship round of the " + yr + " ISFL season.");

    lessTime();
    if (!thruPO){
      System.out.print(
         "\nType the corresponding letter to how many games you want to sim:");

        System.out.println("\nA: One Week\nB: Through Playoffs");

         sim = howMany.next();

        while (!(sim.equals("A") || sim.equals("a") || sim.equals("B") || sim.equals("b"))) {
          System.out.println("Type the corresponding letter to how many games you want to sim:");
          sim = howMany.next();
        }

        if (sim.equals("B") || sim.equals("b")) {
          thruPO = true;
        }
      }

    System.out.println("\nSimming game(s)...\n");
    update();

    String CCFC = playGame(offOVR, defOVR);
    System.out.println(CCFC);
    time();
    lessTime();
    if (CCFC.substring(CCFC.length() - 3).equals("WIN")) {

      System.out.println("\nCongrats, you won the Conference Championship!\n");
    } else {

      System.out.println("\nSorry, you lost the Conference Championship.");
      History.add(yr + " ISFL Season: " + e + "-" + (17 - e) + "  Playoffs: YES - Lost in Conference Championship\n");
      return;
    }

    // SB, if lose, add to history and ret, if win, add to history and ret
    iamuse = "";
    lessTime();
    SBappear++;
    System.out.println("\nWelcome to the Superbowl of the " + yr + " ISFL season.");

    lessTime();
    if (!thruPO){
      System.out.print(
         "\nType the corresponding letter to how many games you want to sim:");

        System.out.println("\nA: One Week\nB: Through Playoffs");

         sim = howMany.next();

        while (!(sim.equals("A") || sim.equals("a") || sim.equals("B") || sim.equals("b"))) {
          System.out.println("Type the corresponding letter to how many games you want to sim:");
          sim = howMany.next();
        }

        if (sim.equals("B") || sim.equals("b")) {
          thruPO = true;
        }
      }

    System.out.println("\nSimming game(s)...\n");
    update();

    String SB = playGame(offOVR, defOVR);
    System.out.println(SB);
    time();
    lessTime();
    if (SB.substring(SB.length() - 3).equals("WIN")) {
      SBwins++;
      System.out.println("\nCongrats, you won the Superbowl!");
      History.add(yr + " ISFL Season: " + e + "-" + (17 - e) + "  Playoffs: YES - Won the Superbowl!\n");
      return;
    } else {

      System.out.println("\nSorry, you lost in the Superbowl.");
      History.add(yr + " ISFL Season: " + e + "-" + (17 - e) + "  Playoffs: YES - Lost in Superbowl\n");
      return;
    }
  }

  public void time() {
    long elapsedTime, timeStart;
    long milliseconds = 2000;
    timeStart = System.currentTimeMillis(); // new Date().getTime();
    while (true) {
      elapsedTime = System.currentTimeMillis() - timeStart;
      if (elapsedTime > milliseconds) {
        break;
      }
    }
  }

  public void lessTime() {
    long elapsedTime, timeStart;
    long milliseconds = 1000;
    timeStart = System.currentTimeMillis(); // new Date().getTime();
    while (true) {
      elapsedTime = System.currentTimeMillis() - timeStart;
      if (elapsedTime > milliseconds) {
        break;
      }
    }
  }

  public String playGame(double x, double y) {
    //opp name
    String curName;
    if(Teams.isEmpty()) {
      for (String s : teamKeep) {
        Teams.add(s);
      }
    }
      curName = Teams.remove((int)(Math.random() * Teams.size()));
     


    
    int yourScore = 0;
    int oppScore = 0;
    double xran = (x / 160.0);
    double yran = (y / 160.0);
    String result = "";

    int addOn = 0;
    int subFrom = 0;

    time();

    if (x > 0) {
      if (Math.random() < xran) {
        addOn += 3;
      }
      if (Math.random() < xran) {
        addOn += 7;
      }
      if (Math.random() < xran) {
        addOn += 3;
      }
      if (Math.random() < xran) {
        addOn += 6;
      }
      if (Math.random() < xran) {
        addOn += 3;
      }
      if (Math.random() < xran) {
        addOn += 7;
      }
      if (Math.random() > xran) {
        addOn -= 3;
      }
      if (Math.random() > xran) {
        addOn -= 3;
      }
      if (Math.random() > xran) {
        addOn -= 7;
      }

      if (x < 50) {
        yourScore = (13 + addOn);
      } else if (x < 60) {
        yourScore = (13 + addOn);
      } else if (x < 70) {
        yourScore = (17 + addOn);
      } else if (x < 80) {
        yourScore = (20 + addOn);
      } else if (x < 90) {
        yourScore = (23 + addOn);
      } else {
        yourScore = (24 + addOn);
      }
    }

    if (y > 0) {
      if (Math.random() > yran) {
        subFrom += 3;
      }
      if (Math.random() > yran) {
        subFrom += 7;
      }
      if (Math.random() > yran) {
        subFrom += 3;
      }
      if (Math.random() > yran) {
        subFrom += 6;
      }
      if (Math.random() > yran) {
        subFrom += 3;
      }
      if (Math.random() > yran) {
        subFrom += 7;
      }
      if (Math.random() < yran) {
        subFrom -= 3;
      }
      if (Math.random() < yran) {
        subFrom -= 7;
      }
      if (Math.random() < yran) {
        subFrom -= 7;
      }

      if (y < 50) {
        oppScore = (14 + subFrom);
      } else if (y < 60) {
        oppScore = (14 + subFrom);
      } else if (y < 70) {
        oppScore = (14 + subFrom);
      } else if (y < 80) {
        oppScore = (17 + subFrom);
      } else if (y < 90) {
        oppScore = (17 + subFrom);
      } else {
        oppScore = (17 + subFrom);
      }
    }

    if (yourScore > oppScore) {
      result = " WIN";
    } else if (yourScore == oppScore) {
      if (Math.random() < .5) {
        if (Math.random() < .35) {
          yourScore += 3;
        } else {
          yourScore += 6;
        }
        result = "OT WIN";
      } else {
        if (Math.random() < .35) {
          oppScore += 3;
        } else {
          oppScore += 6;
        }
        result = "OT LOSS";
      }
    } else {
      result = "LOSS";
    }

  if (yourScore < 10) {
    return name + "  " + yourScore + " - " + oppScore + " " + curName + "  | Result: " + result;
  } 
  if (oppScore < 10) {
    return name + " " + yourScore + " - " + oppScore + "  " + curName + "  | Result: " + result;
  } 
    return name + " " + yourScore + " - " + oppScore + " " + curName + "  | Result: " + result;
  }

  public String getName() {
    return name;
  }

  public int getYr() {
    return yr;
  }

  public void update() {
    offOVR = ((QB.getOvr() * 0.4) + (RB.getOvr() * 0.3) + (WR.getOvr() * 0.3));
    defOVR = ((EDGE.getOvr() * 0.4) + (LB.getOvr() * 0.3) + (CB.getOvr() * 0.3));
    OVR = (int) ((offOVR + defOVR) / 2);
  }

  public ArrayList<String> getHistory() {
    return History;
  }

  public String sessionNotables() {
    return ("Session Win/Loss Record: " + sessionWins + "-" + sessionLosses + "  Playoff Appearances: " + POappear
        + "  SB Appearances: " + SBappear + "  SB Wins: " + SBwins);
  }

  public String toString() {
    update();
    lessTime();
    return ("QB:   " + QB.toString2() +
        "\nRB:   " + RB.toString2() +
        "\nWR:   " + WR.toString2() +
        "\nEDGE: " + EDGE.toString2() +
        "\nLB:   " + LB.toString2() +
        "\nCB:   " + CB.toString2() +

        "\n\nTeam: " + OVR + " ovr  Offense: " + (int) offOVR + " ovr  Defense: " + (int) defOVR + " ovr\n"

    );

  }
  
   public String toString2() {
    offC = (offC - (int)offOVR) * -1;
    defC = (defC - (int)defOVR) * -1;
    ovrC = (ovrC - OVR) * -1;

    String STRoffC = "";
    String STRovrC = "";
    String STRdefC = "";
    if (offC >= 0) {
  
      STRoffC = "(+" + offC + ")";
    }
    else {
      STRoffC = "(" + offC + ")";
    }

    if (defC >= 0) {
  
      STRdefC = "(+" + defC + ")";
    }
    else {
      STRdefC = "(" + defC + ")";
    }

    if (ovrC >= 0) {
  
      STRovrC = "(+" + ovrC + ")";
    }
    else {
      STRovrC = "(" + ovrC + ")";
    }

     
     
    if (ovrC > 25) {
     STRovrC =  "";
    }
    if (offC > 25) {
     STRoffC =  "";
    }
    if (defC > 25) {
     STRdefC =  "";
    }
 

     
    update();
    lessTime();
    return (
        "QB:   " + QB.toString() + QB.devStr() +
        "\nRB:   " + RB.toString() + RB.devStr() +
        "\nWR:   " + WR.toString() + WR.devStr() +
        "\nEDGE: " + EDGE.toString() + EDGE.devStr() +
        "\nLB:   " + LB.toString() + LB.devStr() +
        "\nCB:   " + CB.toString() + CB.devStr() +

        "\n\nTeam: " + 
        OVR + " ovr " + STRovrC + 
        "   Offense: " + (int) offOVR + " ovr " + STRoffC + 
        "   Defense: " + (int) defOVR + " ovr" + STRdefC + "\n"

    );

  }

}