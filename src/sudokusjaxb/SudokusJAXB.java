/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokusjaxb;

import controller.Manager;
import java.util.Date;
import java.util.List;


public class SudokusJAXB {

   /*A lo largo de toda la aplicación, la clase main ( o sea el usuario de la aplicación)
    solamente tendrá contacto con el manager, el cual le pasará los datos que el usuario
    necesite*/
   static Manager manager;
    
    public static void main(String[] args) {
         //instancia del nuevo Manager
         manager = new Manager();
         //the menu is showed only if sudokus.txt was found inside txt-files folder
         if(!Manager.existsTxt()){
             System.out.println("No sudokus.txt detected. Be sure that the file is on ./txt-files/sudokus.txt");
         }
         else{
             int option;
             do{
                 showMenu();
                 option = inputMethods.askInt("Select an option:");
                 switch(option){
                     case 1:
                         newUser();
                         break;
                     case 2:
                         login();
                         break;
                     case 3:
                         rankings();
                         break;
                     case 0:
                         System.out.println("Leaving application");
                         break;
                     default:
                         System.out.println("Option not allowed");
                 }
             }while(option != 0);
         }
    }
    
    /**
     * New user option
     */
    public static void newUser(){
        String name = inputMethods.askString("Enter name:");
        String username = inputMethods.askString("Enter username:");
        String pass = inputMethods.askString("Enter password:");
        String pass2 = "";
        do{
            pass2 = inputMethods.askString("Repeat password:");
            if(!pass.equals(pass2)){
                System.out.println("Passwords don't match! Try again (enter "+ pass + ")");
            }
        }while(!pass.equals(pass2)); 
 
        System.out.println(manager.addUser(name, username, pass2));
    }
    
    /**
     * User login option
     */
    public static void login(){
        if(manager.getUsuariosSize() == 0){
            System.out.println("No users registered yet");
        }
        else{
            String username = inputMethods.askString("Enter your username:");
            String password = inputMethods.askString("Enter your password:");
            String loginResult = manager.verifyLogin(username, password);
            if(loginResult.equals("Successful login")){
                int option;
                do{
                   showMenu2();
                   option = inputMethods.askInt("Select an option:");
                   switch(option){
                       case 1:
                           modifyPassword(password);
                           break;
                       case 2:
                           newSudokuGame();
                           break;
                       case 3:
                           endGameBruteForce();
                           break;
                       case 4:
                           averageTime();
                           break;
                       case 0:
                           System.out.println("Returning to menu");
                           break;
                       default:
                           System.out.println("Option not allowed");         
                   }
               }while(option != 0);
           }
           else{
                System.out.println(loginResult);
            }
       }
    }
    
    /**
     * rankings option. Ordered by average solving time
     */
    public static void rankings() {
        System.out.println("*** Rankings ***");
        List<String> ranking = manager.getRanking();
        if(ranking.isEmpty()){
            System.out.println("There are no history data to show");
        }
        else{
            for(String r: ranking){
               System.out.println(r);
            }
        }
    }
    
    /**
     * If a sudoku play was started on option 2 and solution wasn't right, endGameBruteForce allows to
     * finish the sudoku. Requires entering manually total seconds and the data is stored on history.xml
     */
    public static void endGameBruteForce() {
        if(manager.isSudokuOnPlay()){
            System.out.println("You will add a solved sudoku on historial manually.");
            int totalSecs = inputMethods.askInt("Please, enter number of seconds:");
            manager.addResuelto(totalSecs);
            System.out.println("Result added to history.");  
        }
        else{
            System.out.println("No sudokus on play on that moment. Load sudoku on option 2 (get random sudoku)");
        }
    }
    
    /**
     * Average time on sudoku solving for current user logged
     */
    public static void averageTime(){
        double averageTime = manager.getAverageTime();
        if(averageTime == 0){
            System.out.println("There is no solved sudokus for this player yet");
        }
        else{
           int totalSecs = (int) averageTime;
           System.out.println("Average time sudoku solving for actual player: " + secondsToStringFormat(totalSecs) + " seconds");
       }
    }
    
    /**
     * User password verification
     * @param password String
     */
    
    public static void modifyPassword(String password){
        String actualPassword = inputMethods.askString("Enter actual password");
        if(password.equals(actualPassword)){
          String newPassword = inputMethods.askString("Enter new password");
          String newPassword2 = "";
          do{
              newPassword2 = inputMethods.askString("Confirm new password");
              if(!newPassword.equals(newPassword2)){
                  System.out.println("New passwords don't match. Try again");
              }
              else{
                  System.out.println(manager.newPassword(newPassword));
              }
          }while(!newPassword.equals(newPassword2));
        }
        else{
            System.out.println("Sorry, the actual password is not right");
        }
        
    }
    
    /**
     * SUDOKU GAME option
     */
    public static void newSudokuGame() {
        char[][] sudoku;
        int availableSudokus = 0;
        String level = "";
        boolean b = true;
        //ask for difficulty level of sudoku
        do{
          b = true;
          level = inputMethods.askString("Enter level of sudoku to play (EASY-MEDIUM-HARD-FIENDISH");
          if(!(level.equalsIgnoreCase("EASY") || !level.equalsIgnoreCase("MEDIUM") || !level.equalsIgnoreCase("HARD") ||
                  !level.equalsIgnoreCase("FIENDISH")) ){
              b = false;
              System.out.println("Enter a valid level description");
          } 
          else{
              availableSudokus = manager.availableSudokus(level);
          }
        }while(!b);
       
        //after detect the ones already played, availableSudokus shows the available sudokus for play
        System.out.println("There are "+ availableSudokus + " sudokus for level " + level);
        if(availableSudokus == 0){
            System.out.println("You should try another level");
        }
        else{
            //getSudoku() returns a new sudoku
            System.out.println("Generating new sudoku...");
            sudoku = manager.getSudoku(level); 
            for(int i = 0; i < 9; i++){
                for(int j = 0; j < 9; j++){
                    System.out.print(sudoku[i][j] + "  ");
                    if(j == 8){
                        System.out.println("\n");
                    }
                }
            }
            //show on console the level, the solution (for testing purposes), start time, finish time and total time if result is successful
            System.out.println("Sudoku of level " + manager.getSudokuLevel());
            System.out.println("Solution of sudoku (testing purposes): " + manager.getSolution());
            Date startDate = new Date();
            System.out.println("Time starts now!" + startDate );
            String solution = inputMethods.askString("Enter the solution (ALL numbers in horizontal position) and press return to evaluate");
            if(manager.testSolution(solution)){
                Date endDate = new Date();
                System.out.println("Time starts now!" + endDate );
                int totalSecs = (int)((endDate.getTime() - startDate.getTime()) / 1000);
                System.out.println("Congrats. It has taken " + secondsToStringFormat(totalSecs) + " to solve");
                System.out.println("Result added to history.");
                manager.addResuelto(totalSecs);
            }
            else{
                System.out.println("Sorry. You failed. Try another one");
            }
            
        }
    }
    
    /**
     * Method to transform int of seconds into  hours:minutes:seconds String format
     * @param totalSecs int
     * @return String
     */
    public static String secondsToStringFormat(int totalSecs){
        int hours = totalSecs / 3600;
        int minutes = (totalSecs % 3600) / 60;
        int seconds = totalSecs % 60;
        String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);  
        return timeString;
    }
    
   
    
    
    
    /**
     * option menu at the begining
     */
    public static void showMenu(){
        System.out.println("\n*** JAXB. SUDOKUS ***");
        System.out.println("1. New user registry ");
        System.out.println("2. User sign in");
        System.out.println("3. Users rankings");
        System.out.println("0. Exit");
    }
    
    /**
     * option menu afte user login
     */
     public static void showMenu2(){
        System.out.println("\n*** USER OPTIONS ***");
        System.out.println("1. Modify password ");
        System.out.println("2. Get random sudoku (not played yet)");
        System.out.println("3. Finish manually the sudoku loaded on option 2");
        System.out.println("4. Show average time from player");
        System.out.println("0. Return to menu");
    }
    
}
    

