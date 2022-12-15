import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day15 {
    public static void main(String[] args) throws IOException {
        String file = Files.readString(Path.of("Inputs/Input15"));
        String[] lines = file.split("\n");

        //long row = 2000000;
        long big = 4000000;


        //Set<Long> rowPos = new HashSet<>();

        ArrayList<Sensor> sensors = new ArrayList<>();


        for(String line : lines){
            int first = line.indexOf(',');
            int sec = line.indexOf(',', first+1);
            int third = line.indexOf(',', sec+1);
            int sx = Integer.parseInt(line.substring(0, first));
            long sy = Integer.parseInt(line.substring(first+1, sec));
            long bx = Integer.parseInt(line.substring(sec+1, third));
            long by = Integer.parseInt(line.substring(third+1));

            sensors.add(new Sensor(sx, sy, bx, by));

            /*
            long distance = Math.abs(sx-bx) + Math.abs(sy-by);
            distance -= Math.abs(sy - row);
            long negDistance = distance * -1;
            distance -= sx;
            negDistance -= sx;
            distance *= -1;
            negDistance *= -1;
            for(long n=distance; n<negDistance; n++){
                rowPos.add(n);
            }
             */

        }

        for(int i=0; i<big; i++){
            List<Range> ranges = new ArrayList<>();
            for(Sensor s : sensors){
                long fx = Math.abs(s.sx - i);
                long low = fx + s.ly;
                if(low < 0) low = 0;
                long high = s.hy - fx;
                if(high > big) high = big;
                if(high >= low) {
                    ranges.add(new Range((int)low, (int)high));
                }
            }
            ranges.sort(new Comparator<Range>() {
                public int compare(Range r1, Range r2) {
                    return r1.low - r2.low;
                }
            });

            Stack<Range> stack = new Stack<>();
            stack.push(ranges.get(0));
            for(int j=1; j<ranges.size(); j++){
                Range top = stack.peek();
                if(top.high < ranges.get(j).low)
                    stack.push(ranges.get(j));
                else if(top.high < ranges.get(j).high){
                    top.high = ranges.get(j).high;
                    stack.pop();
                    stack.push(top);
                }
            }
            if(stack.size() == 2){
                Range r1 = stack.pop();
                Range r2 = stack.pop();
                int y;
                if(r1.low < r2.low){
                    y = r1.high+1;
                }
                else{
                    y = r2.high+1;
                }
                System.out.println(i*big + y);
                break;
            }
        }

        //System.out.println(rowPos.size());
    }

    public static class Range{
        int low, high;

        public Range(int low, int high) {
            this.low = low;
            this.high = high;
        }
    }

    public static class Sensor{
        long sx;
        long sy;
        long bx;
        long by;
        long distance;
        long ly;
        long hy;

        public Sensor(long sx, long sy, long bx, long by) {
            this.sx = sx;
            this.sy = sy;
            this.bx = bx;
            this.by = by;
            distance = Math.abs(sx-bx) + Math.abs(sy-by);
            ly = this.sy - distance;
            hy = this.sy + distance;
        }
    }
}