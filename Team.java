import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//import java.util.Math.*;
/*
  To do:


add forced retirement

adjust scoring ?

add salaries

make playoffs harder than regular season (make ovr lower in POs?)



*/
public class Team {

  private String name;

  private double offOVR;
  private double defOVR;
  private int OVR;
  private int offC;
  private int defC;
  private int ovrC;
  private int salaryCap;

  private int wins;
  private int yr = 2024;

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

  private Scanner userInput = new Scanner(System.in);

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

    QB.newContract(1);
    RB.newContract(.262);
    WR.newContract(.554);
    EDGE.newContract(.615);
    LB.newContract(.477);
    CB.newContract(.523);

    offOVR = ((QB.getOvr() * 0.55) + (RB.getOvr() * 0.14) + (WR.getOvr() * 0.31));
    defOVR = ((EDGE.getOvr() * 0.37) + (LB.getOvr() * 0.31) + (CB.getOvr() * 0.32));

    OVR = (int) ((offOVR + defOVR) / 2);

    wins = 0;

    draftPO = 0; // div = 1, ccfc+ = 2
    // stillGoing = true;

    History = new ArrayList<String>();
    salaryUpdate();


  }

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
    reSigning();
    lessTime();
    Draft(wins);
    lessTime();
    freeAgency();

  }

  public void reSigning() {

    boolean QBcon = true;
    boolean RBcon = true;
    boolean WRcon = true;
    boolean EDGEcon = true;
    boolean LBcon = true;
    boolean CBcon = true;


    salaryUpdate();


    System.out.println(
            "\nWelcome to the Re-Signing Period of the " +yr + " Season");
    time();
    System.out.println(
            "\nThe following players' contracts have expired. They will re-sign for the amount specified. " +
                    "\nIf they are not replaced, a benchwarmer will take their place and can be replaced later in the draft or in free agency.");
    time();


    if (QB.getAge()%4 == 0  && QB.getAge() != 24 || QB.getAge() == 25) {
      salaryCap += QB.getSalary();
      QB.newContract(1);
      QBcon = false;
      System.out.println("\nQB: " + QB.toString());
    }

    if (RB.getAge()%4 == 0  && RB.getAge() != 24 || RB.getAge() == 25) {
      salaryCap += RB.getSalary();
      RB.newContract(.262);
      RBcon = false;
      System.out.println("\nRB: " + RB.toString());
    }

    if (WR.getAge()%4 == 0  && WR.getAge() != 24 || WR.getAge() == 25) {
      salaryCap += WR.getSalary();
      WR.newContract(.554);
      WRcon = false;
      System.out.println("\nWR: " + WR.toString());
    }

    if (EDGE.getAge()%4 == 0  && EDGE.getAge() != 24 || EDGE.getAge() == 25) {
      salaryCap += EDGE.getSalary();
      EDGE.newContract(.615);
      EDGEcon = false;
      System.out.println("\nEDGE: " + EDGE.toString());
    }

    if (LB.getAge()%4 == 0  && LB.getAge() != 24 || LB.getAge() == 25) {
      salaryCap += LB.getSalary();
      LB.newContract(.477);
      LBcon = false;
      System.out.println("\nLB: " + LB.toString());
    }

    if (CB.getAge()%4 == 0  && CB.getAge() != 24 || CB.getAge() == 25) {
      salaryCap += CB.getSalary();
      CB.newContract(.523);
      CBcon = false;
      System.out.println("\nCB: " + CB.toString());
    }

    System.out.println("\nYour current salary spending is $" + (110 - salaryCap) + " million. You can afford to spend $" + salaryCap + " million more");

  time();

  String a = "";
  if ((QBcon && RBcon && WRcon && EDGEcon && LBcon && CBcon)) {
    System.out.println("All players on your team remain under contract. Onto the Draft!");
    }
while(!(QBcon && RBcon && WRcon && EDGEcon && LBcon && CBcon)) {

  System.out.println("\nType in the name of the position you'd like to re-sign or \"ESC\" to move on without these players.");
  a = userInput.next();

  while (!(a.equals("QB") || a.equals("RB") || a.equals("WR") || a.equals("EDGE") || a.equals("LB") || a.equals("CB")
          || a.equals("qb") || a.equals("rb") || a.equals("wr") || a.equals("edge") || a.equals("lb") || a.equals("cb")
          || a.equals("ESC") || a.equals("esc"))) {
    System.out.println("\nType in the name of the position you'd like to re-sign or \"ESC\" to move on without these players.");
    a = userInput.next();
  }


  if (a.equals("QB") || a.equals("qb")) {
    //check if current player needs to be re-signed
    if (!QBcon) {
      //check to see if enough money to resign
      if (QB.getSalary() < salaryCap) {
        //adjust salary and make so QB is back under contract
        salaryCap -= QB.getSalary();
        QBcon = true;
        //give info to player about how this changes salary
        System.out.println("\nQB:   " + QB.toString() + " re-signed.");
        lessTime();
        System.out.println("\nYour updated spending is $" + (110 - salaryCap) + " million / $110 million salary cap.");
      } else {
        //if not enough money, let player know they cannot afford to re-sign
        System.out.println("You cannot re-sign this player because their new contract would put you over the salary cap");
      }
    } else {
      //if player doesn't need to be re-signed, let player know
      System.out.println("This player is still under contract, no need to re-sign them right now.");
    }
  }

  if (a.equals("RB") || a.equals("rb")) {
    if (!RBcon) {
      if (RB.getSalary() < salaryCap) {
        salaryCap -= RB.getSalary();
        RBcon = true;
        System.out.println("\nRB:   " + RB.toString() + " re-signed.");
        lessTime();
        System.out.println("\nYour updated spending is $" + (110 - salaryCap) + " million / $110 million salary cap.");
      } else {
        System.out.println("You cannot re-sign this player because their new contract would put you over the salary cap");
      }
    } else {
      System.out.println("This player is still under contract, no need to re-sign them right now.");
    }
  }
  if (a.equals("WR") || a.equals("wr")) {
    if (!WRcon) {
      if (WR.getSalary() < salaryCap) {
        salaryCap -= WR.getSalary();
        WRcon = true;
        System.out.println("\nWR:   " + WR.toString() + " re-signed for $" + WR.getSalary() + " million");
        lessTime();
        System.out.println("\nYour updated spending is $" + (110 - salaryCap) + " million / $110 million salary cap.");
      } else {
        System.out.println("You cannot re-sign this player because their new contract would put you over the salary cap");
      }
    } else {
      System.out.println("This player is still under contract, no need to re-sign them right now.");
    }
  }
  if (a.equals("EDGE") || a.equals("edge")) {
    if (!EDGEcon) {
      if (EDGE.getSalary() < salaryCap) {
        salaryCap -= EDGE.getSalary();
        EDGEcon = true;
        System.out.println("\nEDGE:   " + EDGE.toString() + " re-signed.");
        lessTime();
        System.out.println("\nYour updated spending is $" + (110 - salaryCap) + " million / $110 million salary cap.");
      } else {
        System.out.println("You cannot re-sign this player because their new contract would put you over the salary cap");
      }
    } else {
      System.out.println("This player is still under contract, no need to re-sign them right now.");
    }
  }
  if (a.equals("LB") || a.equals("lb")) {
    if (!LBcon) {
      if (LB.getSalary() < salaryCap) {
        salaryCap -= LB.getSalary();
        LBcon = true;
        System.out.println("\nLB:   " + LB.toString() + " re-signed.");
        lessTime();
        System.out.println("\nYour updated spending is $" + (110 - salaryCap) + " million / $110 million salary cap.");
      } else {
        System.out.println("You cannot re-sign this player because their new contract would put you over the salary cap");
      }
    } else {
      System.out.println("This player is still under contract, no need to re-sign them right now.");
    }
  }
  if (a.equals("CB") || a.equals("cb")) {
    if (!CBcon) {
      if (CB.getSalary() < salaryCap) {
        salaryCap -= CB.getSalary();
        CBcon = true;
        System.out.println("\nCB:   " + CB.toString() + " re-signed.");
        lessTime();
        System.out.println("\nYour updated spending is $" + (110 - salaryCap) + " million / $110 million salary cap.");
      } else {
        System.out.println("You cannot re-sign this player because their new contract would put you over the salary cap");
      }
    } else {
      System.out.println("This player is still under contract, no need to re-sign them right now.");
    }
  }
  if (a.equals("ESC") || a.equals("esc")) {
    break;

  }
}

    /*
    TO DO IN TERMS OF SALARIES :

    1)
    *BUG - RE-SIGN SHOWS UPDATED OVRS FOR PLAYERS BEFORE ITS SUPPOSED TO


    MAKE CONTRACT LENGTH PART OF PLAYER CLASS INSTEAD OF SET AGES FOR CONTRACT DETERMINATION - CLOSER TO REAL NFL AND BETTER

    PENALIZE SIGNING A LOT OF THE SAME POSITION - YOU SHOULDNT BE ABLE TO RE-SIGN SOMEONE AND THEN REPLACE THEM ISNTANTLY IN FREE AGENCY
    LIKE NOTHING HAPPENED --
    -uses contract length var that will be added to player
    -maybe also needs contractBurden variable to keep track of all penalizations -
     available spending would then be salaryCap - contractBurden
     -set backup/non user added player contract length to 1
    */


    if (!QBcon) {
      Player backupQB = new Player();backupQB.newContract(1);QB = backupQB;

    }
    if (!RBcon) {
      Player backupRB = new Player();backupRB.newContract(.262);RB = backupRB;
    }
    if (!WRcon) {
      Player backupWR = new Player();backupWR.newContract(.554);WR = backupWR;
    }
    if (!EDGEcon) {
      Player backupEDGE = new Player();backupEDGE.newContract(.615);EDGE = backupEDGE;

    }
    if (!LBcon) {
      Player backupLB = new Player();backupLB.newContract(.477);LB = backupLB;

    }
    if (!CBcon) {
      Player backupCB = new Player();backupCB.newContract(.523);CB = backupCB;

    }


    salaryUpdate();
  }

  public void freeAgency() {

    //free agent players
    Player FAqb = new Player();
    Player FArb = new Player();
    Player FAwr = new Player();
    Player FAedge = new Player();
    Player FAlb = new Player();
    Player FAcb = new Player();

    //Free Agent array used to manage free agent players
    Player[] faArray = {FAqb, FArb, FAwr, FAedge, FAlb, FAcb};


    //makes 2 good free agent pos, 2 mid, and 2 bad
    int goodLeft = 2;
    int midLeft = 2;
    int badLeft = 2;
    boolean playerChange = true;


    //for each free agent player, randomly decide if they are good, mid, or bad, making sure 2 of each determinant
    for (Player p: faArray) {
      while (playerChange) {

        if (Math.random() < .33) {
          if (goodLeft > 0) {
            p.setOvr(p.getOvr() + 10 + (int) (Math.random() * 20));
            goodLeft--;
            playerChange = false;
          }
        else if(Math.random() < .67) {
          if (midLeft > 0) {
            p.setOvr(p.getOvr() + 5 + (int) (Math.random() * 12));
            midLeft--;
            playerChange = false;
          }
          }
        else {
          if (badLeft > 0) {
            p.setOvr(p.getOvr() + (int) (Math.random() * 7));
            badLeft--;
            playerChange = false;
          }
        }

        }
      }
      playerChange = true;
    }



    //makes sure free agents are not younger than 25 (no rookie contracts)
    for (Player p: faArray){
      if (p.getAge() < 25) {
        p.setAge(25);
        p.checkTrait();
      }
    }

    //sets contract values based on positional value (QB - large gap - EDGE - WR - CB - LB - gap -  RB
    FAqb.newContract(1);
    FArb.newContract(.262);
    FAwr.newContract(.554);
    FAedge.newContract(.615);
    FAlb.newContract(.477);
    FAcb.newContract(.523);

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
    System.out.println("\nYour current spending is $" + (110 - salaryCap) + " million. You can afford to spend $" + (salaryCap) +" million more of the $110 million salary cap. ");
    System.out.println(
            "\nType in the name of a position you'd like to sign or \"ESC\" to keep your current team. " +
            "\nYou can sign as many players as you want in Free Agency, as long as you have enough money.");
    a = userInput.next();

    //makes sure input is valid
    while (!(a.equals("QB") || a.equals("RB") || a.equals("WR") || a.equals("EDGE") || a.equals("LB") || a.equals("CB")
        || a.equals("qb") || a.equals("rb") || a.equals("wr") || a.equals("edge") || a.equals("lb") || a.equals("cb")
        ||a.equals("ESC") || a.equals("esc"))) {
      System.out.println("Type in the name of a position you'd like to sign or \"ESC\" to keep your current team.");
      a = userInput.next();
    }

    //allows re-signing of players until user ends free agency with 'esc'
    while(!(a.equals("ESC") || a.equals("esc"))) {
      //if user can afford QB, sign, else, tell user they cannot afford to sign
      if (a.equals("QB") || a.equals("qb")) {
        if (salaryCap + QB.getSalary() > FAqb.getSalary()) {
          salaryCap = salaryCap + QB.getSalary() - FAqb.getSalary();
          QB = FAqb;
          System.out.println("QB:   " + FAqb.toString() + " has been added to your team.");
          System.out.println("\nYour updated spending is $" + (110 - salaryCap) + " million / $110 million salary cap.");

        } else {
          System.out.println("You cannot afford to sign this player");
        }
      }
      if (a.equals("RB") || a.equals("rb")) {
        if (salaryCap + RB.getSalary() > FArb.getSalary()) {
          salaryCap = salaryCap + RB.getSalary() - FArb.getSalary();
          RB = FArb;
          System.out.println("RB:   " + FArb.toString() + " has been added to your team.");
          System.out.println("\nYour updated spending is $" + (110 - salaryCap) + " million / $110 million salary cap.");
        } else {
          System.out.println("You cannot afford to sign this player");
        }
      }
      if (a.equals("WR") || a.equals("wr")) {
        if (salaryCap + WR.getSalary() > FAwr.getSalary()) {
          salaryCap = salaryCap + WR.getSalary() - FAwr.getSalary();
          WR = FAwr;
          System.out.println("WR:   " + FAwr.toString() + " has been added to your team.");
          System.out.println("\nYour updated spending is $" + (110 - salaryCap) + " million / $110 million salary cap.");

        } else {
          System.out.println("You cannot afford to sign this player");
        }
      }
      if (a.equals("EDGE") || a.equals("edge")) {
        if (salaryCap + EDGE.getSalary() > FAedge.getSalary()) {
          salaryCap = salaryCap + EDGE.getSalary() - FAedge.getSalary();
          EDGE = FAedge;
          System.out.println("EDGE:   " + FAedge.toString() + " has been added to your team.");
          System.out.println("\nYour updated spending is $" + (110 - salaryCap) + " million / $110 million salary cap.");

        } else {
          System.out.println("You cannot afford to sign this player");
        }
      }
      if (a.equals("LB") || a.equals("lb")) {
        if (salaryCap + LB.getSalary() > FAlb.getSalary()) {
          salaryCap = salaryCap + LB.getSalary() - FAlb.getSalary();
          LB = FAlb;
          System.out.println("LB:   " + FAlb.toString() + " has been added to your team.");
          System.out.println("\nYour updated spending is $" + (110 - salaryCap) + " million / $110 million salary cap.");

        } else {
          System.out.println("You cannot afford to sign this player");
        }
      }
      if (a.equals("CB") || a.equals("cb")) {
        if (salaryCap + CB.getSalary() > FAcb.getSalary()) {
          salaryCap = salaryCap + CB.getSalary() - FAcb.getSalary();
          CB = FAcb;
          System.out.println("CB:   " + FAcb.toString() + " has been added to your team.");
          System.out.println("\nYour updated spending is $" + (110 - salaryCap) + " million / $110 million salary cap.");

        } else {
          System.out.println("You cannot afford to sign this player");
        }
      }
      lessTime();
      System.out.println("\nType in the name of another position you'd like to sign or \"ESC\" to end Free Agency.");
      a = userInput.next();
    }

  }

  public void Draft(int win) {

    //draft players / array
    Player Dqb = new Player();
    Player Drb = new Player();
    Player Dwr = new Player();
    Player Dedge = new Player();
    Player Dlb = new Player();
    Player Dcb = new Player();

    Player[] dArray = {Dqb, Drb, Dwr, Dedge, Dlb, Dcb};


    //set draft age - young
    for (Player p: dArray){
      p.setAge(21 + (int) (Math.random() * 3));
    }



    // decides how good ur draft class should be (based on how u performed last
    // season)

    double draftStock = (100.0 / (win/.6 + 8));

    //base ovrs for performance
    int tank = 67;
    int bad = 64;
    int ok = 61;
    int good = 59;
    int great = 58;

    if (wins < 3) {
      for (Player p: dArray){
        p.setOvr(tank);
      }
    } else if (wins < 7) {
      for (Player p: dArray){
        p.setOvr(bad);
      }
    } else if (wins < 10 && draftPO < 1) {
      for (Player p: dArray){
        p.setOvr(ok);
      }
    } else if (wins < 13 && draftPO < 2) {
      for (Player p: dArray){
        p.setOvr(good);
      }
    } else {
      for (Player p: dArray){
        p.setOvr(great);
      }

    }

    for (Player p: dArray){
      p.setOvr(p.getOvr() + (int) (Math.random() * draftStock));
      p.checkTrait();
    }


    Dqb.newContract(1);
    Drb.newContract(.262);
    Dwr.newContract(.554);
    Dedge.newContract(.615);
    Dlb.newContract(.477);
    Dcb.newContract(.523);


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

    System.out.println(
            "\nYour current spending is $" + (110 - salaryCap) + " million. You can afford to spend $" + (salaryCap) +" million more of the $110 million salary cap. " +
            "\nHowever, you are allowed to exceed the salary cap with draft pick salaries.");

    String a = "";

    lessTime();
    System.out.println("\nType in the name of the position you'd like to sign or \"ESC\" to keep your current team.");
    a = userInput.next();

    while (!(a.equals("QB") || a.equals("RB") || a.equals("WR") || a.equals("EDGE") || a.equals("LB") || a.equals("CB")
        || a.equals("qb") || a.equals("rb") || a.equals("wr") || a.equals("edge") || a.equals("lb") || a.equals("cb")
        ||a.equals("ESC") || a.equals("esc"))) {
      System.out.println("Type in the name of the position you'd like to draft or \"ESC\" to keep your current team.");
      a = userInput.next();
    }

    if (a.equals("ESC") || a.equals("esc")) {
      return;
    }
    if (a.equals("QB") || a.equals("qb")) {
      QB = Dqb;
      System.out.println("QB:   " + Dqb.toString() + " has been added to your team.");
      return;
    }
    if (a.equals("RB") || a.equals("rb")) {
      RB = Drb;
      System.out.println("RB:   " + Drb.toString() + " has been added to your team.");
      return;
    }
    if (a.equals("WR") || a.equals("wr")) {
      WR = Dwr;
      System.out.println("WR:   " + Dwr.toString() + " has been added to your team.");
      return;
    }
    if (a.equals("EDGE") || a.equals("edge")) {
      EDGE = Dedge;
      System.out.println("EDGE:   " + Dedge.toString() + " has been added to your team.");
      return;
    }
    if (a.equals("LB") || a.equals("lb")) {
      LB = Dlb;
      System.out.println("LB:   " + Dlb.toString() + " has been added to your team.");
      return;
    }
    if (a.equals("CB") || a.equals("cb")) {
      CB = Dcb;
      System.out.println("CB:   " + Dcb.toString() + " has been added to your team.");
    }
  }

  public int regularSeason() {
    // sets up ovrs for games
    update();

    System.out.println(toString2());
    System.out.print("Current Salary Spending: $" + (110 - salaryCap) + " million out of $110 million salary cap being spent.\n\n");
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

        sim = userInput.next();

        while (!(sim.equals("A") || sim.equals("a") || sim.equals("B") || sim.equals("b") || sim.equals("C") || sim.equals("c") || sim.equals("D") || sim.equals("d"))) {
          System.out.println("Type the corresponding letter to how many games you want to sim:");
          sim = userInput.next();
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
        sim = userInput.next();
        while (!(sim.equals("A") || sim.equals("a") || sim.equals("B") || sim.equals("b") || sim.equals("C") || sim.equals("c"))) {
          System.out.println("Type the corresponding letter to how many games you want to sim:");
          sim = userInput.next();
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

         sim = userInput.next();

        while (!(sim.equals("A") || sim.equals("a") || sim.equals("B") || sim.equals("b"))) {
          System.out.println("Type the corresponding letter to how many games you want to sim:");
          sim = userInput.next();
        }

        if (sim.equals("B") || sim.equals("b")) {
          thruPO = true;
        }
      }

      System.out.println("\nSimming game(s)...\n");
      update();

      String wildcard = playGame(offOVR - 2, defOVR - 2);
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

         sim = userInput.next();

        while (!(sim.equals("A") || sim.equals("a") || sim.equals("B") || sim.equals("b"))) {
          System.out.println("Type the corresponding letter to how many games you want to sim:");
          sim = userInput.next();
        }

        if (sim.equals("B") || sim.equals("b")) {
          thruPO = true;
        }
      }

    System.out.println("\nSimming game(s)...\n");
    update();

    String divisional = playGame(offOVR - 4, defOVR - 4);
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

         sim = userInput.next();

        while (!(sim.equals("A") || sim.equals("a") || sim.equals("B") || sim.equals("b"))) {
          System.out.println("Type the corresponding letter to how many games you want to sim:");
          sim = userInput.next();
        }

        if (sim.equals("B") || sim.equals("b")) {
          thruPO = true;
        }
      }

    System.out.println("\nSimming game(s)...\n");
    update();

    String CCFC = playGame(offOVR - 6, defOVR - 6);
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

         sim = userInput.next();

        while (!(sim.equals("A") || sim.equals("a") || sim.equals("B") || sim.equals("b"))) {
          System.out.println("Type the corresponding letter to how many games you want to sim:");
          sim = userInput.next();
        }

        if (sim.equals("B") || sim.equals("b")) {
          thruPO = true;
        }
      }

    System.out.println("\nSimming game(s)...\n");
    update();

    String SB = playGame(offOVR - 8, defOVR - 8);
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
      }
      else if (yourScore == oppScore) {
        if (Math.random() < .5) {
          if (Math.random() < .35) {
            yourScore += 3;
          }
          else {
            yourScore += 6;
          }
          result = "OT WIN";
        }
        else {
          if (Math.random() < .35) {
            oppScore += 3;
          } else {
            oppScore += 6;
          }
          result = "OT LOSS";
        }
      }
    else {
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

  public void salaryUpdate() {
    salaryCap = 110 - QB.getSalary() - RB.getSalary() - WR.getSalary() - EDGE.getSalary() - LB.getSalary() - CB.getSalary();
  }

  public void update() {
    offOVR = ((QB.getOvr() * 0.525) + (RB.getOvr() * 0.15) + (WR.getOvr() * 0.325));
    defOVR = ((EDGE.getOvr() * 0.4) + (LB.getOvr() * 0.3) + (CB.getOvr() * 0.3));
    OVR = (int) ((offOVR + defOVR) / 2);
    salaryCap = 110 - QB.getSalary() - RB.getSalary() - WR.getSalary() - EDGE.getSalary() - LB.getSalary() - CB.getSalary();
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
    //This toString is used for offseason events, used to show differences in this season's team from the previous season
    offC = (offC - (int)offOVR) * -1;
    defC = (defC - (int)defOVR) * -1;
    ovrC = (ovrC - OVR) * -1;
    //offense, defense, and ovr CHANGES

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
