

import java.util.Scanner;

public class Validate {

    Scanner sc = new Scanner(System.in);

    public int checkchoice(String message) {
        int a = 0;
        while (true) {
            try {
                System.out.print(message);
                a = Integer.parseInt(sc.nextLine());
                if (a>= 1 && a<= 34) {
                    break;
                } else {
                    System.out.println("Re-input");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please input number");
            } catch (NullPointerException e) {
                System.out.println("Please don't input space!");
            }
        }
        return a;
    }

    public int checkInt(String message) {
        int a = 0;
        while (true) {
            try {
                System.out.print(message);
                a = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please input number");
            } catch (NullPointerException e) {
                System.out.println("Please don't input space!");
            } catch (ArithmeticException e) {
                System.out.println("Please don't division for 0!");
            }
        }
        return a;
    }
    
    
    public double checkDouble(String message) {
        double a = 0;
        while (true) {
            try {
                System.out.print(message);
                a = Double.parseDouble(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please input number");
            } catch (NullPointerException e) {
                System.out.println("Please don't input space!");
            } catch (ArithmeticException e) {
                System.out.println("Please don't division for 0!");
            }
        }
        return a;
    }
    
     public String checkString(String message) {
        String a;
        while (true) {
            try {
                System.out.print(message);
                a = sc.nextLine();
                if(a.trim().equals("")|| a == null) System.out.println("Please input");
                else break;
            } catch (NullPointerException e) {
                System.out.println("Please don't input space!");
            }
        }
        return a;
    }
    
    

}
