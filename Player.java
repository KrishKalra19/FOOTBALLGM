import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Player {

  private String name;
  private int ovr;
  private int age;
  private int ovrC;
  private int developmentTrait;

  private int salary;
  private Random ran = new Random();


  public Player() {
    //makes name for player
    try {
      name = makeName();
    } catch (Exception e) {
      this.name = "ERROR";
    }

    // initial stats
    ovr = 59;
    age = 21;



    //some variability in ovrs
    if (Math.random() < .5) {
      ovr += ran.nextInt(10);
    } else {
      ovr -= ran.nextInt(6);
    }

    //some variability in age
    age += ran.nextInt(16);

    checkTrait();


  }


  @edu.umd.cs.findbugs.annotations.SuppressFBWarnings("DM_DEFAULT_ENCODING")
  public String makeName() throws Exception {
    ;

    Scanner nameSc = new Scanner(new File("Baby_Names.csv"));

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
    String firstName = firstNames.get(ran.nextInt(firstNames.size() - 1));

    name = firstName + " " + lastNames.get(ran.nextInt(lastNames.size() - 1));

     name = name.substring(0,firstName.length() - 1) +
     name.substring(firstName.length(), name.length() - 1);
     //These two lines allow names on VSC, but break? on repl
    return name;
  }

  public void checkTrait() {
    //if young
    if (age < 25) {
      developmentTrait = 0;
    }
    //if in prime
    else if (age < 30) {
      developmentTrait = 1;
    }
    //if in maintain
    else if (age < 33) {
      developmentTrait = 2;
    }
    //if old
    else if (age < 36) {
      developmentTrait = 3;
    }
    //if really old
    else {
      developmentTrait = 4;
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

  public void newContract(double d) {

    double ovrMult = 0;
    if (ovr < 65) {
      ovrMult += 25;
    }
    else if (ovr < 70) {
      ovrMult += 35;
    }
    else if (ovr < 76) {
      ovrMult += 50;
    }
    else if (ovr < 83) {
      ovrMult += 68;
    }
    else if (ovr < 91) {
      ovrMult += 85;
    }
    else{
      ovrMult += 100;
    }

    ovrMult /= 100.0;




    if (developmentTrait == 0) {
      salary = (int) ((d * 9) * (ovrMult));
      salary++;
    }
    else if (developmentTrait == 1) {
      salary = (int) ((d * 50) * ovrMult);
    }
    else if (developmentTrait == 2) {
      salary = (int) ((d * 48) * ovrMult);
    }
    else if (developmentTrait == 3) {
      salary = (int) ((d * 45) * ovrMult);
    }
    else {
      salary = (int) ((d * 40) * ovrMult);
    }


  }

  public int getSalary() {
    return salary;
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
    if (developmentTrait == 0) {
      ovr++;
      if (Math.random() < .5) {
        ovr++;
      }
      if (Math.random() < .25) {
        ovr++;
      }

    }
    else if (developmentTrait == 1) {
      ovr++;
      if (Math.random() < .25) {
        ovr++;
      }

    }
    else if (developmentTrait == 2) {
      if (Math.random() < .25) {
        ovr--;
      }
      else if (Math.random() < .5) {
        ovr++;
      }

    }
    else if (developmentTrait == 3) {
      if (Math.random() < .5) {
        ovr--;
      }
      ovr--;
    }
    else if (developmentTrait == 4) {
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
    String strOVRCH;
    if (ovrC - ovr == 0) {

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


    return (name + antiName + age + "y/o  " + ovr + " ovr"  + "    Salary: $" + salary + " million");
  }

  public String toString2() {
    String antiName = "";

    for (int i = (20 - name.length()); i > 0; i--) {
      antiName += " ";
    }

    if (ovrC != 0) {
    return (name + antiName + age + "y/o  " + ovrC + " ovr" + "    Salary: $" + salary + " million");
    }
    else {
      return (name + antiName + age + "y/o  " + ovr + " ovr" + "    Salary: $" + salary + " million");
    }
  }



}
