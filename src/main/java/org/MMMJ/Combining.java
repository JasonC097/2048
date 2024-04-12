/******************************************
 *CSCI 205 - Software Engineering and Design
 *Spring 2024
 *Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 *Name: Miguel Romero
 *Section: 01
 *Date: 4/11/24
 *Time: 11:23 PM
 *
 *Project: csci205_final_project
 *Package: org.MMMJ
 *Class: Combining
 *Description:
 * **************************************** */
package org.MMMJ;

public class Combining {

    private Tile block1;

    private Tile block2;

    public Combining(Tile block1, Tile block2){
        this.block1 = block1;
        this.block2 = block2;
    }

    public void combine(){
        if(block1.equals(block2)){
            int newNumber = block2.getCurrNum() * 2;
            this.block1.setCurrNum(0);
            this.block2.setCurrNum(newNumber);
        }

    }







}