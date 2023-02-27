package org.example;

public class Utils {
    public static <T extends Number & Comparable<T>> T [] ordena1(T[] num){
        ordena22(num, 0,num.length - 1);
        return num;
    }
    public static<T extends Number & Comparable<T>> T[] ordena22 (T [] num, int izq, int der){
                int i, media, d;
                media = ((izq+der)/2);
                T pivote = num[media];
                 i=izq; d=der;
             while (i <= d) {
                 while (num[i].compareTo(pivote)<0) {
                     i++;
                 }
                 while (num[d].compareTo(pivote)>0) {
                     d--;
                 }
                 if (d != i) {
                     T aux = num[d];
                     num[d] = num[i];
                     num[i] = aux;
                     i++;
                     d--;
                 }
             }

             if (izq < d) {
                 ordena22(num, izq, d);
             }
             if(i< der) {
                 ordena22(num, i, der);
             }


        return num;
    }
}
