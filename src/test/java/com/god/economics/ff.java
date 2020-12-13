package com.god.economics;

/**
 * created By gOD on 11/20/2020 2:11 PM
 */

public class ff {
    final static int[] i = {0};
    static boolean d = true;
    static boolean b = true;

    public synchronized static void main(String[] args) throws InterruptedException {

        int k=0;
         while (true) {
            k++;
            i[0] = 0;
            b = true;
            d = true;
//            Thread.currentThread().join();
            Thread thread = new Thread(() -> b());
            thread.start();


            Thread thread1 = new Thread(() -> d());
            thread1.start();


            if ((!b && !d) && i[0] == 1)
            {
                System.out.println("i is 1");
                System.out.println("k= "+k);
            }




        }


    }

    private static  void d() {
        i[0]++;
        d = false;
    }

    private static  void b() {
        i[0]++;
        b = false;
    }
}
