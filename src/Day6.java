import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day6 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("Inputs/Input6"));

        String line = scanner.nextLine();
        /* Part 1
        for(int i=3; i<line.length(); i++){
            char a = line.charAt(i-3);
            char b = line.charAt(i-2);
            char c = line.charAt(i-1);
            char d = line.charAt(i);
            if(a != b && a != c && a != d && b != c && b != d && c != d){
                System.out.println(i+1);
                break;
            }
        }
         */

        Set<Character> set = new HashSet<>();
        for(int i=13; i<line.length(); i++){
            set.clear();
            for(int j=i-13; j<=i; j++){
                set.add(line.charAt(j));
            }
            if(set.size() == 14){
                System.out.println(i+1);
                break;
            }
        }
    }
}
