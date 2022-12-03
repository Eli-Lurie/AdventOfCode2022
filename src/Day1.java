import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day1 {
    public static void main(String[] args) throws FileNotFoundException{
        Scanner scanner = new Scanner(new File("Inputs/Input1"));

        int max = 0, max2 = 0, max3 = 0;
        int elfTotal = 0;
        while(scanner.hasNextLine()){
            String line = scanner.nextLine().trim();
            if(line.isEmpty()){
                if(elfTotal > max) max = elfTotal;
                else if(elfTotal > max2) max2 = elfTotal;
                else if(elfTotal > max3) max3 = elfTotal;
                elfTotal = 0;
            }
            else{
                int cal = Integer.parseInt(line);
                elfTotal += cal;
            }
        }

        System.out.println(max+max2+max3);
    }
}
