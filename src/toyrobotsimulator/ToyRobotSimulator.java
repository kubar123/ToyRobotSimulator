/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toyrobotsimulator;

/**
 *
 * @author Jakub Rybicki
 */
public class ToyRobotSimulator {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        newGame();
    }
    
    public static void newGame(){
        GameTable.isNewGame=true;
        ConsoleUI.printNewGame();
        
        do{
            ConsoleUI.readInput();
        }while(true);
    }
    
}
