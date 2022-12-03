import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("Inputs/Input2"));

        int total = 0;
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            char a = line.charAt(0);
            char b = line.charAt(2);

            if(b == 'X'){
                if(a == 'A') total += 3;//4;
                else if(a == 'B') total += 1;//1;
                else total += 2;//7;
            }
            else if(b == 'Y'){
                if(a == 'A') total += 4;//8;
                else if(a == 'B') total += 5;//5;
                else total += 6;//2;
            }
            else {
                if (a == 'A') total += 8;//3;
                else if (a == 'B') total += 9;//9;
                else total += 7;//6;
            }
        }
        System.out.println(total);
    }
}
