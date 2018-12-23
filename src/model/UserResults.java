/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author usuario
 */

//Class created to make use of compareTo() method in order to show the rankings ordered
//by increasing average time. Yes, we could use the JAXB generated source just adding
//the methods, bur for now we used this way of do it
public class UserResults implements Comparable<UserResults>{
    
    private String username;
    private int totalSolved;
    private double totalSecs;
    private int averageTime;
    private String averageTimeFormatted;
    
    
    public UserResults(String username){
        this.username = username;
        this.totalSolved = 0;
        this.totalSecs = 0;
    }
    
    /**
     * 
     */
    public void addSolved(){
        this.totalSolved++;
    }
    
    /**
     * 
     * @param secs 
     */
    public void addSecs(String secs) {
        double seconds = Integer.parseInt(secs);
        this.totalSecs += seconds;
    }
    
    public void calcAverage() {
        this.averageTime = (int) totalSecs/totalSolved;
        this.averageTimeFormatted = secondsToStringFormat(averageTime);
    }
    
    public static String secondsToStringFormat(int totalSecs){
        int hours = totalSecs / 3600;
        int minutes = (totalSecs % 3600) / 60;
        int seconds = totalSecs % 60;
        String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);  
        return timeString;
    }

    public String getUsername() {
        return username;
    }

    public int getAverageTime() {
        return averageTime;
    }

    public int getTotalSolved() {
        return totalSolved;
    }

    public double getTotalSecs() {
        return totalSecs;
    }

    public String getAverageTimeFormatted() {
        return averageTimeFormatted;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTotalSolved(int totalSolved) {
        this.totalSolved = totalSolved;
    }

    public void setTotalSecs(double totalSecs) {
        this.totalSecs = totalSecs;
    }

    public void setAverageTime(int averageTime) {
        this.averageTime = averageTime;
    }

    public void setAverageTimeFormatted(String averageTimeFormatted) {
        this.averageTimeFormatted = averageTimeFormatted;
    }
    
    

    
    
    
    @Override
    public String toString() {
        return  "username=" + username + ", totalSolved=" + totalSolved + ", AverageSecs=" + averageTime + ", averageSecsFormatted=" + averageTimeFormatted ;
    }

    
    @Override
    public int compareTo(UserResults o) {
        int compareSecs = o.getAverageTime();
        return this.averageTime-compareSecs;
    }
    
    
    /*
     public int compareTo(Student comparestu) {
        int compareage=((Student)comparestu).getStudentage();
         For Ascending order
        return this.studentage-compareage

   */
    
   

   


    
    
}
