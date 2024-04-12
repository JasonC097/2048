/******************************************
 *CSCI 205 - Software Engineering and Design
 *Spring 2024
 *Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 *Name: Miguel Romero
 *Section: 01
 *Date: 4/11/24
 *Time: 3:34 PM
 *
 *Project: csci205_final_project
 *Package: org.MMMJ
 *Class: Tile
 *Description:
 * **************************************** */
package org.MMMJ;

import javafx.scene.paint.Color;

public class Tile {
    private int currNum;

    private Color currColor;

    public Tile(){
        this.currNum = 0;
    }

    public Tile(int tileNumber){
        this.currNum = tileNumber;
    }


    /**
     * Equals method which comes to true if the two Tile objects have the same current number
     *
     * @param tile2 the tile its being compared to
     * @return whether it comes out to true
     * @Author Miguel Romero
     */
    public boolean equals(Tile tile2) {
        if (this.currNum == tile2.getCurrNum()){
            return true;
        }else {
            return false;
        }
    }




    @Override
    public String toString() {
        if(this.currNum == 0){
            return " ";
        }else{
            return Integer.toString(currNum);
        }


    }

    /**
     * @return the tile objects current number
     */
    public int getCurrNum() {
        return currNum;
    }


}