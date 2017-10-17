/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toyrobotsimulator;

import java.util.Arrays;

/**
 *
 * @author Jakub Rybicki
 */
public class GameTable{
    
    public static void GameTable(){
        isNewGame=true;
    }
    
    public static boolean isNewGame;
    
    public static boolean isValidMove(){
        int[] currPos=Robot.getPos();
        //check to ensure we are not standing at an edge, and looking at it
    //NORTH CHECK
        if(currPos[2]==0)
            switch(currPos[1]){
                case 4:     return false;
                default:    return true;
            }
    // SOUTH CHECK
        if(currPos[2]==1)
            switch(currPos[1]){
                case 0:     return false;
                default:    return true;
            }
    //WEST CHECK
        if(currPos[2]==2)
            switch(currPos[0]){
                case 0:     return false;
                default:    return true;
            }
    //EAST CHECK
        if(currPos[2]==3)
            switch(currPos[0]){
                case 4:     return false;
                default:    return true;
            }
        //Robot not on edge, can move.
        return true;
    }
    
    //robot cannot be placed off the board
    public static boolean isValidPlace(int[] setPos){
        if(setPos[0]<=4 && setPos[0]>=0)
            return (setPos[1]<=4 && setPos[1]>=0);
        return false;
    }
}
