import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;

public class Day13 {
    public static void main(String[] args) throws IOException {
        String file = Files.readString(Path.of("Inputs/Input13"));
        String[] lines = file.split("\n\n");

        /* Part 1
        int total = 0;
        int i = 1;
        for(String l : lines){
            String[] packets = l.split("\n");
            if(compPackets(packets[0], packets[1]) == Comp.LEFT){
                total += i;
            }
            i++;
        }
        System.out.println(total);
         */

        ArrayList<String> list = new ArrayList<>();
        for(String l : lines){
            String[] packets = l.split("\n");
            list.add(packets[0]);
            list.add(packets[1]);
        }
        list.add("[[2]]");
        list.add("[[6]]");

        list.sort(new SortPackets());

        System.out.println((list.indexOf("[[2]]")+1)*(list.indexOf("[[6]]")+1));
    }

    enum Comp{LEFT, RIGHT, EQUAL}

    public static class SortPackets implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            Comp retval = compPackets(o1, o2);
            if(retval == Comp.LEFT) return -1;
            if(retval == Comp.RIGHT) return 1;
            return 0;
        }
    }

    public static Comp compPackets(String a, String b){
        if(a.isEmpty() && b.isEmpty()) return Comp.EQUAL;
        if(!a.isEmpty() && b.isEmpty()) return Comp.RIGHT;
        if(a.isEmpty()) return Comp.LEFT;

        if(a.charAt(0) == ',' && b.charAt(0) == ',') return compPackets(a.substring(1), b.substring(1));
        if(a.charAt(0) == ',') return compPackets(a.substring(1), b);
        if(b.charAt(0) == ',') return compPackets(a, b.substring(1));


        if(a.charAt(0) == '[' && b.charAt(0) == '['){
            int aEnd = getNextItem(a);
            int bEnd = getNextItem(b);
            Comp retval = compPackets(a.substring(1,aEnd), b.substring(1,bEnd));
            if(retval == Comp.LEFT || retval == Comp.RIGHT) return retval;
            else {
                retval = compPackets(a.substring(aEnd + 1), b.substring(bEnd + 1));
                return retval;
            }
        }

        if(a.charAt(0) == '['){
            int aEnd = getNextItem(a);
            int bEnd = getNextNum(b);
            Comp retval = compPackets(a.substring(1,aEnd), b.substring(0,bEnd));
            if(retval == Comp.LEFT || retval == Comp.RIGHT) return retval;
            else {
                retval = compPackets(a.substring(aEnd + 1), b.substring(bEnd));
                return retval;
            }
        }

        if(b.charAt(0) == '['){
            int aEnd = getNextNum(a);
            int bEnd = getNextItem(b);
            Comp retval = compPackets(a.substring(0, aEnd), b.substring(1, bEnd));
            if(retval == Comp.LEFT || retval == Comp.RIGHT) return retval;
            else{
                retval = compPackets(a.substring(aEnd), b.substring(bEnd+1));
                return retval;
            }
        }

        int aEnd = getNextNum(a);
        int bEnd = getNextNum(b);
        int aInt = Integer.parseInt(a.substring(0, aEnd));
        int bInt = Integer.parseInt(b.substring(0, bEnd));
        if(aInt < bInt) return Comp.LEFT;
        if(bInt < aInt) return Comp.RIGHT;
        return compPackets(a.substring(aEnd), b.substring(bEnd));
    }

    public static int getNextNum(String s){
        int end = s.indexOf(',');
        if(end < 0) end = s.length();
        return end;
    }

    public static int getNextItem(String s){
        int num = 0, end = 0;
        for(; end<s.length(); end++){
            if(s.charAt(end) == '[') num++;
            else if(s.charAt(end) == ']') num--;
            if(num == 0) break;
        }
        return end;
    }
}
