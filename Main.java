import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static ArrayList<Polynomial> polynomials = new ArrayList<>();

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        String termChoice;
        int choice;



        do {
            Polynomial p = new Polynomial();
            boolean newTerm = true;

            mainMenu();
            choice = keyboard.nextInt();
            keyboard.nextLine();


            switch (choice) {

                case 1:
                    System.out.println("Please enter terms in the format:  +/-(coefficient)x^(exponent)");
                    System.out.println("(ex: +5x^2, -7x)");
                    do {
                        System.out.println("\nWould you like to enter a new term? (Y/N) ");
                        termChoice = keyboard.nextLine();
                        if (termChoice.equalsIgnoreCase("Y")) {
                            newTerm = true;
                            System.out.print("New Term: ");
                            p.addTerm(new Term(keyboard.nextLine()));
                        } else if (termChoice.equalsIgnoreCase("N")) {
                            newTerm = false;
                        } else {
                            System.out.println("Error: Please enter a valid choice.");
                        }

                    } while (newTerm);

                    System.out.println("\n\nYour polynomial: ");
                    System.out.println(p);
                    polynomials.add(p);
                    break;

                case 2:
                    displayPolynomials();
                    int pChoice;
                    System.out.println("Which polynomial would you like to edit?");
                    pChoice = keyboard.nextInt();
                    while (pChoice < 1 || pChoice > polynomials.size()){
                        System.out.println("Enter a valid choice.");
                        System.out.println("Which polynomial would you like to edit? (1 - " + polynomials.size());
                        pChoice = keyboard.nextInt();
                    }

                    Polynomial thisPoly = polynomials.get(pChoice - 1);
                    int editChoice;

                    do {
                        editMenu();
                        editChoice = keyboard.nextInt();
                        keyboard.nextLine();

                        switch (editChoice) {

                            case 1:
                                System.out.print("Enter new term: ");
                                String s = keyboard.nextLine();
                                thisPoly.addTerm(new Term(s));
                                System.out.println("Polynomial now reads:  " + thisPoly);

                                break;

                            case 2:
                                System.out.println();
                                System.out.println("Choose a term to delete: ");
                                int i = 1;
                                for (Term t : thisPoly.terms){
                                    System.out.print(" " + i + "  ");
                                    i++;
                                }
                                System.out.println("\n" + thisPoly);
                                thisPoly.terms.remove(keyboard.nextInt() - 1);
                                System.out.print("Polynomial now reads:  " + thisPoly);

                                break;

                            case 3:
                                thisPoly.clear();
                                System.out.println("Successfully cleared");
                                break;

                            case 4:
                                break;

                            default:
                                System.out.println("Not a valid choice.");
                        }
                    } while (editChoice != 4);
                    polynomials.set(pChoice - 1, thisPoly);




                break;

                case 3:
                    displayPolynomials();
                    System.out.println("\nChoose 2 polynomials to add ");
                    System.out.print("1st Polynomial: ");
                    int i = keyboard.nextInt();
                    Polynomial p1 = polynomials.get(i -1);
                    Polynomial copy = new Polynomial(p1);

                    System.out.print("2nd Polynomial: ");
                    Polynomial p2 = polynomials.get(keyboard.nextInt() -1);

                    polynomials.set(i - 1, copy);
                    System.out.println("\n" + p1 + "  +  " + p2);
                    p1.add(p2);

                    System.out.println("= " + p1);



                break;

                case 4:
                break;

                default:
                    System.out.println("ERROR: Enter a valid choice.");
            }
        } while (choice != 4);
    }




    public static void mainMenu(){
        System.out.println("\nPlease choose an option from the menu:");
        System.out.println("\t1) Create a new polynomial");
        System.out.println("\t2) Edit saved polynomials");
        System.out.println("\t3) Add two polynomials");
        System.out.println("\t4) Exit");
    }



    public static void displayPolynomials(){
        int i = 1;
        System.out.println("\n~~~ List Of Polynomials ~~~");
        for (Polynomial p : polynomials){
            System.out.println(i + ") " + p);
            i++;
        }
    }

    public static void editMenu(){
        System.out.println("\nChoose one of the following options:");
        System.out.println("\t1) Add a term");
        System.out.println("\t2) Delete a term");
        System.out.println("\t3) Clear polynomial");
        System.out.println("\t4) Back to main menu");
    }
}
