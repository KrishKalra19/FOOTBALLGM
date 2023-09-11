import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.Scanner;
import java.lang.Math.*;

public class Player {

  private String name;
  private int ovr;
  private int age;
  private int ovrC;
  private String developmentTrait;

  public Player() {
    // name = "";
    ovr = 59;
    age = 21;

    if (Math.random() < .5) {
      ovr += (int) (Math.random() * 12);
    } else {
      ovr -= (int) (Math.random() * 6);
    }

    age += (int) (Math.random() * 16);

    if (age < 25) {
      developmentTrait = "young";
    } else if (age < 31) {
      developmentTrait = "prime";
    } else if (age < 34) {
      developmentTrait = "maintain";
    } else {
      developmentTrait = "washed";
    }

    try {
      name = makeName();
    } catch (Exception e) {
      this.name = "ERROR";
    }
  }

  public String makeName() throws Exception {
    Random rand = new Random();

    Scanner nameSc = new Scanner(new File("Baby Names.csv"));
    nameSc.useDelimiter("\n");
    List<String> firstNames = new ArrayList<String>();
    while (nameSc.hasNextLine()) {
      name = nameSc.next();
      firstNames.add(name);
    }
    nameSc = new Scanner(new File("Surnames.csv"));
    nameSc.useDelimiter("\n");
    List<String> lastNames = new ArrayList<String>();
    while (nameSc.hasNextLine()) {
      String name = nameSc.next();
      lastNames.add(name);
    }
    nameSc.close();
    String firstName = firstNames.get(rand.nextInt(firstNames.size() - 1));

    name = firstName + " " + lastNames.get(rand.nextInt(lastNames.size() - 1));

     name = name.substring(0,firstName.length() - 1) +
     name.substring(firstName.length(), name.length() - 1);
     //These two lines allow names on VSC, but break? on repl
    return name;
  }

  public void checkTrait() {
    if (age < 25) {
      developmentTrait = "young";
    } else if (age < 30) {
      developmentTrait = "prime";
    } else if (age < 33) {
      developmentTrait = "maintain";
    } else if (age < 36) {
      developmentTrait = "washed";
    }
    else {
      developmentTrait = "rlywashed";
    }
  }

  public int getOvr() {
    return ovr;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public void setOvr(int i) {
    ovr = i;
    if (ovr > 99) {
      ovr = 99;
    }
  }

  public void setAge(int i) {
    age = i;
  }

  public void aging() {
    age++;
    checkTrait();
    development();
  }

  public void development() {
  ovrC = ovr;
    if (developmentTrait.equals("young")) {
      ovr++;
      if (Math.random() < .5) {
        ovr++;
      }
      if (Math.random() < .25) {
        ovr++;
      }
    
    }
    else if (developmentTrait.equals("prime")) {
      ovr++;
      if (Math.random() < .25) {
        ovr++;
      }
      
    }
    else if (developmentTrait.equals("maintain")) {
      if (Math.random() < .25) {
        ovr--;
      }
      else if (Math.random() < .5) {
        ovr++;
      }
      return;
    }
    else if (developmentTrait.equals("washed")) {
      if (Math.random() < .5) {
        ovr--;
      }
      ovr--;
    }
    else if (developmentTrait.equals("rlywashed")) {
      if (Math.random() < .5) {
        ovr--;
      }
      if (Math.random() < .5) {
        ovr--;
      }
      ovr--;
      ovr--;
      ovr--;
    }
  }

  public String devStr() {
    String strOVRCH = "";
    if (ovrC - ovr == 0) {
      ovrC = (ovrC - ovr) * -1;
      strOVRCH = "+" + ovrC;
    }
    else if (ovrC - ovr < 0) {
      ovrC = (ovrC - ovr) * -1;
      strOVRCH = "+" + ovrC;
    }
    else {
      ovrC = (ovrC - ovr) * -1;
      strOVRCH = "" + ovrC;
    }
    if (ovrC > 3) {
     return "";
    }
    return " (" + strOVRCH + ")";
  }

  public String toString() {
    String antiName = "";

    for (int i = (20 - name.length()); i > 0; i--) {
      antiName += " ";
    }

    
    return (name + antiName + age + "y/o  " + ovr + " ovr" );
  }

  public String toString2() {
    String antiName = "";

    for (int i = (20 - name.length()); i > 0; i--) {
      antiName += " ";
    }

    if (ovrC != 0) {
    return (name + antiName + age + "y/o  " + ovrC + " ovr" );
    }
    else {
      return (name + antiName + age + "y/o  " + ovr + " ovr" );
    }
  }

  

}