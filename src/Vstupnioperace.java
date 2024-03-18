import java.util.InputMismatchException;
import java.util.Scanner;

public class Vstupnioperace {

    /**
     * Method used to retrieve and validate an integer from user input.
     *
     * @param zobrazovanytext The text displayed to the user before the number is read.
     * @param maxcislo The maximum value allowed for the entered number.
     * @return Loaded and validated integer.
     */
    public static int nacticelekladneCislo(String zobrazovanytext, int maxcislo) {
        Scanner sc = new Scanner(System.in);
        int nacteneCislo = 0;
        boolean cislo = true;
        while (cislo) {
            try {
                System.out.println(zobrazovanytext);
                nacteneCislo = sc.nextInt();
                cislo = false;
                if (nacteneCislo < 0) {
                    System.out.println("Zadane cislo je mensi jak 0.");
                    cislo = true;
                }
                if (nacteneCislo > maxcislo) {
                    System.out.println("Zadane cislo je vesi jak " + maxcislo);
                    cislo = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Nebylo zadane cislo");
                sc.next();
            }
        }
        return nacteneCislo;
    }
}
