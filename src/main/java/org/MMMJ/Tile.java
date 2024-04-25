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

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;

public class Tile {
    private int currNum;

    private Color currColor;

    private int xPos;

    private int yPos;

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
            return "    ";
        }else{
            return  String.format("%4d",currNum);
        }
    }

    /**
     * Getter and Setter methods to get and set the properties of the tile's position and value
     */
    public int getCurrNum() {
        return currNum;
    }

    public void setCurrNum(int newNum){
        this.currNum = newNum;
    }

    public int getXPos(){return this.xPos;}


    public int getYPos(){return this.yPos;}


    public void setXPos(int xPos){
        this.xPos = xPos;
    }

    public void setYPos(int yPos){
        this.yPos = yPos;
    }





}