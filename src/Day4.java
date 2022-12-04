import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day4 {
    public static void main(String[] args) throws FileNotFoundException{
        Scanner scanner = new Scanner(new File("Inputs/Input4"));

        int total = 0;
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            int p11 = Integer.parseInt(line.substring(0, line.indexOf('-')));
            int p12 = Integer.parseInt(line.substring(line.indexOf('-')+1, line.indexOf(',')));
            int p21 = Integer.parseInt(line.substring(line.indexOf(',')+1, line.lastIndexOf('-')));
            int p22 = Integer.parseInt(line.substring(line.lastIndexOf('-')+1));

            /* Part 1
            if((p21 >= p11 && p22 <= p12) || (p11 >= p21 && p12 <= p22)){
                total++;
            }
             */

            if(p12 >= p21 && p11 <= p22){
                total ++;
            }

        }
        System.out.println(total);
    }
}
