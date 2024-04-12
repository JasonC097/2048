/******************************************
 *CSCI 205 - Software Engineering and Design
 *Spring 2024
 *Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 *Name: Miguel Romero
 *Section: 01
 *Date: 4/11/24
 *Time: 3:25 PM
 *
 *Project: csci205_final_project
 *Package: org.MMMJ
 *Class: Movement
 *Description:
 * **************************************** */
package org.MMMJ;

import java.util.ArrayList;
import java.util.Scanner;

public class Movement {
    /**
     * Scanner object
     */
    private Scanner scnr;
    /**
     * list of all tiles on the board
     */
    private ArrayList<Tile> listOfTiles;


    /**
     * Constructor for the movement class
     */
    public Movement() {
        this.listOfTiles = new ArrayList<Tile>();
    }

    public boolean checkCollision(Tile tile ,String key){
        switch (key){
            case "w":
                if(Board.getValueAt(tile.getXPos(),tile.getYPos()-1) != 0){
                    return true;
                }
            case "s":
                if(Board.getValueAt(tile.getXPos(),tile.getYPos()+1) != 0){
                    return true;
                }
            case "a":
                if(Board.getValueAt(tile.getXPos()-1,tile.getYPos()) != 0){
                    return true;
                }
            case "d":
                if(Board.getValueAt(tile.getXPos()+1,tile.getYPos()) != 0){
                    return true;
                }
            default:
                return false;
        }
    }

    public void moveTile(){
        scnr = new Scanner(System.in);
        switch(scnr.next()){
            case "w":
                for(Tile tile : listOfTiles){
                    if(!checkCollision(tile, "w")){
                        tile.setYPos(tile.getYPos()-1);
                    }
                }
                break;
            case "s":
                for(Tile tile : listOfTiles){
                    if(!checkCollision(tile, "s")){
                        tile.setYPos(tile.getYPos()+1);
                    }
                }
                break;
            case "a":
                for(Tile tile : listOfTiles){
                    if(!checkCollision(tile, "a")){
                        tile.setXPos(tile.getXPos()-1);
                    }
                }
                break;
            case "d":
                for(Tile tile : listOfTiles){
                    if(!checkCollision(tile, "d")){
                        tile.setXPos(tile.getXPos()+1);
                    }
                }
        }
    }

}