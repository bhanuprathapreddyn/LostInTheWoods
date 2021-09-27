
/*
###########################################################################################################
Title:Lost In The Woods
Name OF the File :Lost In The Woods/src/
External Files:N/A
Names:Bhanu Prathap NallapaReddy
Email:bhanuprathapreddyn@lewisu.edu
Course Number:CPSC-60500
Section NUmber:002
Finished Date :09/22/2021
Submitted Date:09/23/2021
Explanation: Given the dimension of  a rectangular shaped forest with an impossible barrier to cross,
the goal is to find how much time 2 people would take to meet when they are randomly walking one step at a time.
Here, we have 2 people Pat and Chris - Pat starting from upper left corner of the grid and Chris from lower right corner of the grid.
Assuming that the axis starts at (0,0), pat would start at (0,h) and Chris would start at (l,0)
where l and h are length and height respectively. the user would be able to choose the dimensions of the forest.
However, the limitation is that the length/height should not be less than 2 or more than 50.
Both Pat and Chris starts at the same time, each taking one step at a time. The direction each of them choose is
randomly picked from the 8 directions viz., North, South, East, West, Northeast, Northwest, Southeast, or Southwest.
If the step takes the person beyond the barrier, he would stop and try again. We iterate for 1000000 times or until both of them meet.

Resources:N/A


###########################################################################################################
 */

package com.se.cpsc60500;

import java.util.Random;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        int len=10,  height =10;
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.print("Enter length: ");
        len = reader.nextInt();
        // validate length
        while(len < 2 || len > 50)
        {
            System.out.print("Length needs to be between 2 and 50. Enter a valid length: ");
            len = reader.nextInt();
        }

        System.out.print("Enter Height: ");
        height = reader.nextInt();
        // ValidateHeight
        while(height < 2 || height > 50)
        {
            System.out.print("Height needs to be between 2 and 50. Enter a valid Height: ");
            height = reader.nextInt();
        }
        reader.close();


        Person p1 = new Person();
        p1.name = "Pat";
        p1.pos_x = 0;
        p1.pos_y = height;

        Person p2 = new Person();
        p2.name = "Chris";
        p2.pos_x = len;
        p2.pos_y = 0;

        System.out.println("time: " + 0 + "::" +p1.name + " is at ("+  p1.pos_x + "," + p1.pos_y  +") and " + p2.name + " is at (" + p2.pos_x + "," + p2.pos_y + ")");
        System.out.println("\nPat and Chris starts moving...\n");

        // Iterate for 1,000,000 times to see  if both the points meet
        for (int i =1; i <  1000000; i++) {
            move(p1, len, height);
            move(p2, len, height);
            System.out.println("time: " + i + "::" +p1.name + " is at ("+  p1.pos_x + "," + p1.pos_y  +") and " + p2.name + " is at (" + p2.pos_x + "," + p2.pos_y + ")");

            if( p1.pos_x == p2.pos_x && p1.pos_y == p2.pos_y) {

                System.out.println("Pat and Chris meet at point ("+  p1.pos_x + "," + p1.pos_y  + ") at time " + i);
                break;
            }
            if (i ==  1000000)
                System.out.println("Pat and Chris could not meet");

        }
    }


    public static Person move(Person p, int len, int height) {
        String[] directions = {"north","south","east","west","northeast","southeast","southwest","northwest"};
        Random random = new Random();
        //randomly pick a direction from the 8 directions listed
        int select = random.nextInt(directions.length);

// define what the next block would given a direction
        int x =  p.pos_x, y = p.pos_y;
        switch(directions[select]){
            case "north":
                y++;
                break;

            case "south":
                y--;
                break;

            case "east":
                x++;
                break;

            case "west":
                x--;
                break;

            case "northeast":
                x++;y++;
                break;

            case "southeast":
                x++;y--;
                break;

            case "southwest":
                x--;y--;
                break;

            case "northwest":
                x--;y++;
                break;

            default:
                System.out.println("In corner");

        }
//do not  move if the next  step in the diretion chosen breaches the barrier
        if (x < 0 || y < 0 || x > len || y > height) {
            System.out.println(p.name  +  " reached barrier");
        }
        else {
            p.pos_x = x;
            p.pos_y = y;
        }

        return p;


    }
}