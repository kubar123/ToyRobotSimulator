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
public class Robot {
    
    //N=0 S=1 W=2 E=3
    private static int facingDirection=0;
    private static int xPos;
    private static int yPos;
    
    
    //
    public static int[] getPos(){
        int[] main=new int[3];
        main[0]=xPos;
        main[1]=yPos;
        main[2]=facingDirection;
        return main;
    }
    
    public void setDirection(int direction){
        facingDirection=direction;
    }
    
    //ensures the robot does not fall of the table before it is moved
    public static void tryMove() throws Exception{
        if(!GameTable.isValidMove())    throw new Exception("invalid move");
        move();
    }
    
    //moved the robot 1 sq. in the way it is facing
    private static void move() {
        if(facingDirection==0)  yPos++;
        if(facingDirection==1)  yPos--;
        if(facingDirection==2)  xPos--;
        if(facingDirection==3)  xPos++;
        ConsoleUI.printReport();
    }
    
    public static void rotateLeft(boolean isLeftTurn){
        if(isLeftTurn){
            if(facingDirection==0)          facingDirection=2;
            else if(facingDirection==1)     facingDirection=3;
            else if(facingDirection==2)     facingDirection=1;
            else                            facingDirection=0;
        }else{
            if(facingDirection==0)          facingDirection=3;
            else if(facingDirection==1)     facingDirection=2;
            else if(facingDirection==2)     facingDirection=0;
            else                            facingDirection=1;
        }
    }
    
    public static void place(int[] pos) throws Exception{
        if(!GameTable.isValidPlace(pos))
            throw new Exception("invalid move");
        xPos=pos[0];
        yPos=pos[1];
        facingDirection=pos[2];

        ConsoleUI.printReport();
        
    }
    
}
