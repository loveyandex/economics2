package com.god.economics.joobin;

/**
 * created By gOD on 6/17/2020 9:44 PM
 */

public  abstract class abs  {
    abstract void dr();
    private double d(){
        return 0;
    }

    public static void main(String[] args) {
        abs ab = new abs() {
            @Override
            void dr() {
                System.out.println();

            }
        };


    }

    public abstract class S extends abs{
        private abstract class T extends S{
            private class U extends T{
                @Override
                void dr() {
                    System.out.println("fucking classs nestedign");
                }
            }
        }
    }

}
