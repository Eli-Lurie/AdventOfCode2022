import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Day11 {
    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<Long>[] monkeys = new ArrayList[8];

        long mult = 5*17*2*7*3*11*13*19;

        for(int i=0; i<8; i++){
            monkeys[i] = new ArrayList<>();
        }

        long[] m0 = {77, 69, 76, 77, 50, 58};
        long[] m1 = {75, 70, 82, 83, 96, 64, 62};
        long[] m2 = {53};
        long[] m3 = {85, 64, 93, 64, 99};
        long[] m4 = {61, 92, 71};
        long[] m5 = {79, 73, 50, 90};
        long[] m6 = {50, 89};
        long[] m7 = {83, 56, 64, 58, 93, 91, 56, 65};

        addItems(monkeys[0], m0);
        addItems(monkeys[1], m1);
        addItems(monkeys[2], m2);
        addItems(monkeys[3], m3);
        addItems(monkeys[4], m4);
        addItems(monkeys[5], m5);
        addItems(monkeys[6], m6);
        addItems(monkeys[7], m7);

        long[] items = new long[8];
        for(int i=0; i<10000; i++){
            for(int n=0; n<8; n++) {
                long s = monkeys[n].size();
                items[n] += s;
                for(int j = 0; j < s; j++) {
                    long num = monkeys[n].remove(0);
                    while(num > mult) num -= mult;
                    long[] retval = new long[2];
                    switch (n) {
                        case 0 -> retval = m0op(num);
                        case 1 -> retval = m1op(num);
                        case 2 -> retval = m2op(num);
                        case 3 -> retval = m3op(num);
                        case 4 -> retval = m4op(num);
                        case 5 -> retval = m5op(num);
                        case 6 -> retval = m6op(num);
                        case 7 -> retval = m7op(num);
                    }
                    monkeys[(int)retval[1]].add(retval[0]);
                }
            }
        }

        long max1 = 0; long max2 = 0;
        for(long i : items){
            if(i > max1) {
                max2 = max1;
                max1 = i;
            }
            else if(i > max2) max2 = i;
        }
        System.out.println(max1 * max2);

    }

    public static void printMonkeys(long i, ArrayList<Long>[] monkeys){
        System.out.println(i);
        int n=0;
        for(ArrayList<Long> m : monkeys){
            System.out.println(n++ + ": " + m);
        }
        System.out.println("\n");
    }

    public static void addItems(ArrayList<Long> monkey, long[] array){
        for(long i : array){
            monkey.add(i);
        }
    }

    public static long[] m0op(long i){
        long[] retval = new long[2];
        retval[0] = (i*11);
        retval[1] = retval[0] % 5 == 0 ? 1 : 5;
        return retval;
    }
    public static long[] m1op(long i){
        long[] retval = new long[2];
        retval[0] = (i+8);
        retval[1] = retval[0] % 17 == 0 ? 5 : 6;
        return retval;
    }
    public static long[] m2op(long i){
        long[] retval = new long[2];
        retval[0] = (i*3);
        retval[1] = retval[0] % 2 == 0 ? 0 : 7;
        return retval;
    }
    public static long[] m3op(long i){
        long[] retval = new long[2];
        retval[0] = (i+4);
        retval[1] = retval[0] % 7 == 0 ? 7 : 2;
        return retval;
    }
    public static long[] m4op(long i){
        long[] retval = new long[2];
        retval[0] = (i * i);
        retval[1] = retval[0] % 3 == 0 ? 2 : 3;
        return retval;
    }
    public static long[] m5op(long i){
        long[] retval = new long[2];
        retval[0] = (i+2);
        retval[1] = retval[0] % 11 == 0 ? 4 : 6;
        return retval;
    }
    public static long[] m6op(long i){
        long[] retval = new long[2];
        retval[0] = (i+3);
        retval[1] = retval[0] % 13 == 0 ? 4 : 3;
        return retval;
    }
    public static long[] m7op(long i){
        long[] retval = new long[2];
        retval[0] = (i+5);
        retval[1] = retval[0] % 19 == 0 ? 1 : 0;
        return retval;
    }
}
