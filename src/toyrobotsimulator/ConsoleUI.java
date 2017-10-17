/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toyrobotsimulator;

import java.util.Scanner;

/**
 *
 * @author Jakub Rybicki
 */
public class ConsoleUI {
    // ----------------messages ------------------
    static final String HELP_MESSAGE="-PLACE will put the toy robot on the table in "
                + "position X,Y and facing NORTH, SOUTH, EAST or WEST.\n" +
"-The origin (0,0) is considered to be the SOUTH WEST most corner.\n"
                + "-MOVE will move the toy robot one unit forward in the "
                + "direction it is currently facing.\n" +
"-LEFT and RIGHT will rotate the robot 90 degrees in the specified direction "
                + "without changing the position of the robot.\n" +
"-REPORT will announce the X,Y and direction of the robot.";
    
    static final String NEW_GAME_MESSAGE="The robot suggests you type 'help'";
    static final String INVALID_INPUT="The robot doesnt understand... It suggests"
            + " using English.";
    static final String OUT_BOUNDS="The robot does not appriciate being dropped"
            + " on the floor.";
    static final String ROBOT_NOT_DOWN="The robot doesnt know where to go, you need to "
            + "place it on the table first.";
    static final String ROBOT_NO_WANT_DIE="The robot refuses to walk off the table.";
      
    //------------------end messages---------------------------
    

    
    //------ PRINT METHODS ---- SHOW INFO TO USER-----
    public static void printHelp(){
        System.out.println(HELP_MESSAGE);
    }
    
    public static void printNewGame(){
        System.out.println(NEW_GAME_MESSAGE);
    }
    
    public static void printReport(){
        int[] pos=Robot.getPos();
        String dir;
        
        //convert int direction into N/S/W/E
        if(pos[2]==0)       dir="North";
        else if(pos[2]==1)  dir="South";
        else if(pos[2]==2)  dir="West";
        else                dir="East";
        
        System.out.println("Bot placed: "+pos[0]+" - "+pos[1]+" / "+dir);
    }
    //------ END PRINT METHODS -------
    
    
    public static void readInput(){
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        
        //new game, first put down the robot in valid pos
        if(GameTable.isNewGame){
            if(input.contains("place")){
                placeSelected(input);
            }else if(input.contains("help")){
                printHelp();
            }else   System.out.println(ROBOT_NOT_DOWN);
            
            return;
        }
        menuSwitcher(input);
        
    }
    
    //menu switcher, determins what the user has entered and processes the request
    public static void menuSwitcher(String input){
        if(input.equalsIgnoreCase("help")){
            System.out.println(HELP_MESSAGE);

        }else if(input.contains("place")){
            placeSelected(input);

        }else if(input.contains("move")){
            try{
                Robot.tryMove();
            }catch(Exception ex){//robot falls of table
                System.out.println(ROBOT_NO_WANT_DIE);
            }

        }else if(input.contains("left")||input.contains("right")){
            Robot.rotateLeft(!input.contains("right"));
//            if(input.contains("right"))     Robot.rotateLeft(false);
//            else                            Robot.rotateLeft(true);
            printReport();
        }else if(input.contains("report")){
            printReport();
        }
    }
    //----end menu----
    
                    //N=0 S=1 W=2 E=3
    private static int convertDirection(String direction) throws NumberFormatException {
        if(direction.equalsIgnoreCase("north"))  return 0;
        if(direction.equalsIgnoreCase("south"))  return 1;
        if(direction.equalsIgnoreCase("west"))   return 2;
        if(direction.equalsIgnoreCase("east"))   return 3;
        throw new NumberFormatException();//incorrect direction entered
    }

    private static void placeSelected(String input) {
        try {
            //try to place the robot down at input pos
            Robot.place(processPlace(input));
            GameTable.isNewGame=false; //robot placed, no longer new game
        }catch (NumberFormatException ex){
            System.out.println(INVALID_INPUT);
        }catch (Exception ex) {
            System.out.println(OUT_BOUNDS);
        }
    }
    
    //processes input into the correct syntax
    private static int[] processPlace(String input) throws NumberFormatException{
        String[] parts=input.split(",");
        int[] items = new int[3];
        
        //remove 'PLACE 'from first split for int conversion
        items[0]=Integer.parseInt(parts[0].substring(6));
        items[1]=Integer.parseInt(parts[1]);
        items[2]=convertDirection(parts[2]);
            
        return items;
    }
 
}
