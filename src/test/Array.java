package test;

public class Array {
    static int[] bitmap = new int[3];
    public void set() {


        try {
            bitmap[0] = 4;
        } catch (Exception e) {
            System.out.println(e);
        }


    }
    public static void main(String[] args){
        Array array = new Array();
        array.set();
        System.out.println(bitmap[0]);
        System.out.println(bitmap[1]);

//        System.out.println(bitmap[1]);
    }
}
