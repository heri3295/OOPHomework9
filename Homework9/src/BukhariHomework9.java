import java.util.concurrent.Semaphore;

/**
 * Created by mexic on 11/30/2017.
 */
public class BukhariHomework9 {
    public static void main(String[] args) {
        AlphabetPrinter alprint = new AlphabetPrinter();

        printone pone = new printone(alprint);
        printtwo ptwo = new printtwo(alprint);
        printthree pthree = new printthree(alprint);
        printfour pfour = new printfour(alprint);

        Thread t1 = new Thread(pone);
        Thread t2 = new Thread(ptwo);
        Thread t3 = new Thread(pthree);
        Thread t4 = new Thread(pfour);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

class AlphabetPrinter {
    Semaphore lock;
    int index;
    int BOUND = 6;
    boolean[] signals = new boolean[4];
    char[] arrone = new char[]{'A', 'E', 'I', 'M', 'Q', 'U', 'Y'};
    char[] arrtwo = new char[]{'B', 'F', 'J', 'N', 'R', 'V', 'Z'};
    char[] arrthr = new char[]{'C', 'G', 'K', 'O', 'S', 'W'};
    char[] arrfou = new char[]{'D', 'H', 'L', 'P', 'T', 'X'};

    public AlphabetPrinter() {
        lock = new Semaphore(1) ;
        index = 0;
        signals[0] = true;
        signals[1] = false;
        signals[2] = false;
        signals[3] = false;
    }
    public void printArrayOne() {
        if (signals[0]) {
            try {
                lock.acquire();
                System.out.println(arrone[index] + " ");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                signals[0] = false;
                signals[1] = true;
                lock.release();
            }
        }
    }
    public void printArrayTwo() {
        if (signals[1]) {
            try {
                lock.acquire();
                System.out.println(arrtwo[index] + " ");
                if (index == BOUND) { System.exit(0); }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                signals[1] = false;
                signals[2] = true;
                lock.release();
            }
        }
    }
    public void printArrayThree() {
        if (signals[2]) {
            try {
                lock.acquire();
                System.out.println(arrthr[index] + " ");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                signals[2] = false;
                signals[3] = true;
                lock.release();
            }
        }
    }
    public void printArrayFour() {
        if (signals[3]) {
            try {
                lock.acquire();
                System.out.println(arrfou[index] + " ");
                index++;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                signals[3] = false;
                signals[0] = true;
                lock.release();
            }
        }
    }
}

class printone implements Runnable {
    AlphabetPrinter printer;
    public printone(AlphabetPrinter printer) { this.printer = printer; }
    @Override
    public void run() { while (true) {printer.printArrayOne(); } }
}

class printtwo implements Runnable {
    AlphabetPrinter printer;
    public printtwo(AlphabetPrinter printer) { this.printer = printer; }
    @Override
    public void run() { while (true) {printer.printArrayTwo(); } }
}

class printthree implements Runnable {
    AlphabetPrinter printer;
    public printthree(AlphabetPrinter printer) { this.printer = printer; }
    @Override
    public void run() { while (true) {printer.printArrayThree(); } }
}

class printfour implements Runnable {
    AlphabetPrinter printer;
    public printfour(AlphabetPrinter printer) { this.printer = printer; }
    @Override
    public void run() { while (true) {printer.printArrayFour(); } }
}
