package org.example.tareapoo;

public class Main1 {

        public static void main (String [] args) {
            Circle circle1 = new Circle (10, 10, 20);
            Circle circle2 = new Circle (15, 15, 21);

            int resultCircles = circle1.compareTo (circle2);
            System.out.println(resultCircles);
           /* if(resultCircles<0){
                System.out.println("circulo 1 es menor que el 2: "+resultCircles);
            }else if(resultCircles>0){
                System.out.println("circulo 1 es mayor que el 2:"+resultCircles);
            }else{
                System.out.println("son iguales: "+resultCircles);
            }*/

            Rectangle rec1 = new Rectangle (10, 10, 20, 20);
            Rectangle rec2 = new Rectangle (30, 30, 50, 50);

            int resultRecs = rec1.compareTo(rec2);
            System.out.println(resultRecs);
            if(resultRecs<0){
                System.out.println("rectangulo 1 es menor que el 2: "+resultRecs);
            }else if(resultRecs>0){
                System.out.println("rectangulo 1 es mayor que el 2:"+resultRecs);
            }else{
                System.out.println("son iguales: "+resultRecs);
            }


        }

    }

