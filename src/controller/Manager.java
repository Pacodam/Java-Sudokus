/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import model.UserResults;
import model.historials.Historial;
import model.historials.Historial.Resuelto;
import model.sudokus.Sudokus;
import model.sudokus.Sudokus.Sudoku;
import model.usuarios.Usuarios;
import model.usuarios.Usuarios.Usuario;
import persistence.IOMethods;


public class Manager {
    
    //JAXB objects lists
    private static Sudokus sudokus;
    private static Usuarios usuarios;
    private static Historial historial;
    
    //boolean to detect if sudokus.txt exists (used on application init)
    private static boolean existsTxt = true;
    
    //vars of logged user
    private static Usuario userLogged;
    private static List<String> userLoggedSudokusSolved;
    private static int[] sudokusForRand;
    
    //vars of current sudoku at work
    private static Sudoku sudokuOnPlay;
    
    
    /**
     * On instantiation, Sudokus are loaded from sudokus.txt and Usuarios and Historial are loaded,
     * if exists txt and xml related files
     */
    public Manager() {
        if(!IOMethods.existsSudokusXmlFile()){
            if(IOMethods.existsSudokTxtFile()){
              existsTxt = true;
              sudokus = IOMethods.loadSudokusFromTxt();
                System.out.println(sudokus.getSudoku().size());
             
            } 
            else{
                existsTxt = false;
            }
        }
        else{
            sudokus = IOMethods.loadSudokus();
        }
        if(IOMethods.existsUsuariosXmlFile()){
             usuarios = IOMethods.loadUsuarios();
        }
        else{
             usuarios = new Usuarios();
        }
        if(IOMethods.existsHistorialXmlFile()){
             historial = IOMethods.loadHistorial();
        }   
        else{
            historial = new Historial();
        }
   }
    
    
    /**
     * Returns true if ./txt-files/sudokus.txt exists, false elsewhere
     * @return boolean
     */
    public static boolean existsTxt() {
        return existsTxt;
    }
    
    /**
     * Returns size of JAXB list usuarios (the ones registered in the xml file)
     * @return int
     */
    public int getUsuariosSize(){
        return usuarios.getUsuario().size();
    }
    
    /**
     * Returns true if there is sudoku loaded in memory, false elsewhere
     * @return boolean
     */
    public boolean isSudokuOnPlay(){
        if(sudokuOnPlay == null){
            return false;
        }
        return true;
    }
    
    /**
     * Returns actual player average seconds for sudoku solving (the ones in history.xml)
     * @return double
     */
    public double getAverageTime() {
        double timeSum = 0;
        int totalSolved = 0;
        for(Resuelto h: historial.getResuelto()){
            if(h.getNombreUsuario().equals(userLogged.getNombreUsuario())){
                timeSum += Integer.parseInt(h.getTiempo());
                totalSolved++;
            }
        }
        if(totalSolved == 0){
            return 0;
        }
        return timeSum/totalSolved;
    }
    
    /**
     * Creates new Resuelto object and sends to history.xml persistence
     * @param totalSecs int
     */
    public void addResuelto(int totalSecs) {
        Resuelto resuelto = new Resuelto();
        resuelto.setNombreUsuario(userLogged.getNombreUsuario());
        resuelto.setSudokuNum(sudokuOnPlay.toString());
        resuelto.setTiempo(Integer.toString(totalSecs));
        historial.getResuelto().add(resuelto);
        IOMethods.historialPersistence(historial);
        sudokuOnPlay = null;
    }
    
    /**
     * Creates Usuario object and sends to usuarios.xml persistence
     * @param name String
     * @param username String
     * @param password String
     * @return String
     */
    public String addUser(String name, String username, String password){
        
        for(Usuario us: usuarios.getUsuario()){
            if(username.equals(us.getNombreUsuario())){
                return "User " + username + " is already registered";
            }
        }
        Usuarios.Usuario user = new Usuarios.Usuario();
        user.setNombreCompleto(name);
        user.setNombreUsuario(username);
        user.setPassword(password);
        usuarios.getUsuario().add(user);
        IOMethods.userPersistence(usuarios);
        return "New user added";
    }
    
    /**
     * Verification of login. Returns String with the type of successful/failed message
     * @param username String
     * @param password String
     * @return String
     */
    public String verifyLogin(String username, String password){
         boolean existUsername = false;
         for(Usuario us: usuarios.getUsuario()){
            if(username.equals(us.getNombreUsuario())){
                existUsername = true;
                if(password.equals(us.getPassword())){
                    userLogged = us;
                    return "Successful login";
                }
                else return "Wrong password";
            }
        }
        if(!existUsername){
            return "There is nobody with username "+ username;
        }
        return "";
    }
    
    /**
     * Modifies password of actual user logged and sends to usuarios.xml persistence
     * @param newPassword String
     * @return String
     */
    public String newPassword(String newPassword){
        userLogged.setPassword(newPassword);
        IOMethods.userPersistence(usuarios);
        return "password modified";
    }
    
    /**
     * Returns true if the solution for sudoku is right; false elsewhere
     * @param solution String
     * @return boolean
     */
    public boolean testSolution(String solution){
        if(solution.equals(sudokuOnPlay.getSolved())){
            return true;
        }
        return false;
    }
    
    /**
     * Returns the solution of sudoku on play. It's used for testing purposes
     * @return String
     */
    public String getSolution() {
        return sudokuOnPlay.getSolved();
    }
    
    /**
     * Returns sudoku on play level
     * @return BigInteger
     */
    public BigInteger getSudokuLevel() {
        return sudokuOnPlay.getLevel();
    }
    
    
    /**
     * Method finds sudokus of received level, look on historial which ones gamer already played
     * and returns a random one from the ones the gamer didn't played yet. The return format is
     * a char[][]
     * @param level String
     * @return char[][]
     */
    public char[][] getSudoku(String level) {
        //the char matrix to store the new sudoku
        char[][] newSudoku = new char[9][9];
        //sudokusForRand returns the available sudokus of level specified and not played yet
        int newSudokuIndex = sudokusForRand[new Random().nextInt(sudokusForRand.length)];
        //sudokuOnPlay stores the sudoku already loaded
        sudokuOnPlay = sudokus.getSudoku().get(newSudokuIndex);
        //sudokuProblem stores actual sudoku problem, and then is loaded into the char[][]
        String sudokuProblem = sudokuOnPlay.getProblem();
        int charat = 0;
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                  char num = sudokuProblem.charAt(charat);
                  newSudoku[i][j] = num;
                  charat++;   
           }
        }
        return newSudoku;
    } 
    
    /**
     * Available sudokus for player (the ones already solved, at specified level, don't appear)
     * @param level String
     * @return int
     */
    public int availableSudokus(String level) {
        //TODO it works, but algorithm can be improved
        //on first iteration we count the available sudokus (not solved) for actual player
        int availableSudokus = 0;
        sudokusSolved();
        int totalSudokus = sudokus.getSudoku().size();
        for(int i = 0; i < totalSudokus; i++){
            String description = sudokus.getSudoku().get(i).getDescription();
            if(description.equalsIgnoreCase(level)){
              if(!userLoggedSudokusSolved.contains(sudokus.getSudoku().get(i).toString())){
                availableSudokus++;
              }
            }
        }
        sudokusForRand = new int[availableSudokus];
        for(int i = 0, j = 0; i < totalSudokus; i++){
            String description = sudokus.getSudoku().get(i).getDescription();
            if(description.equalsIgnoreCase(level)){
              if(!userLoggedSudokusSolved.contains(sudokus.getSudoku().get(i).toString())){
                sudokusForRand[j] = i;
                j++;  
              }
            }
        }
        return availableSudokus;
    }
    
    /**
     * stores all sudokus solved, at any level, for current player logged
     */
    public static void sudokusSolved(){
        userLoggedSudokusSolved = new ArrayList<>();
        for(Resuelto h: historial.getResuelto()){
            if(h.getNombreUsuario().equals(userLogged.getNombreUsuario())){
                userLoggedSudokusSolved.add(h.getSudokuNum());
            }
        }
    } 
    
    /**
     * Returns List with the ranking, ordered by increasing average solving time
     * @return List String
     */
    public List<String> getRanking() {
        //TODO too long method. Can be improved
        List<String> resultsRanking = new ArrayList<>();
        
        //first we get all users in history
        List<UserResults> usersHistory = new ArrayList<>();
        List<String> usernames = new ArrayList<>();
        //now, data is stored
        for(Resuelto h: historial.getResuelto()){
            if(!usernames.contains(h.getNombreUsuario())){
                usernames.add(h.getNombreUsuario());
                UserResults user = new UserResults(h.getNombreUsuario());
                user.addSecs(h.getTiempo());
                user.addSolved();
                user.calcAverage();
                usersHistory.add(user);
            }
            else{
                for(int i = 0; i < usersHistory.size(); i++){
                    if(usersHistory.get(i).getUsername().equals(h.getNombreUsuario())){
                        usersHistory.get(i).addSecs(h.getTiempo());
                        usersHistory.get(i).addSolved();
                        usersHistory.get(i).calcAverage();
                    }
                }
            }
        }
        //finally, the data is sorted by average seconds
        if(!usersHistory.isEmpty()){
           Collections.sort(usersHistory);
             for(UserResults ur: usersHistory){
                resultsRanking.add(ur.toString());
             } 
         }
        
        return resultsRanking;
    }
    
}
