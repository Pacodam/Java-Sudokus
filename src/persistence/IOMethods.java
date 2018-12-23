/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import model.historials.Historial;
import model.sudokus.Sudokus;
import model.sudokus.Sudokus.Sudoku;
import model.usuarios.Usuarios;


/**
 *
 * @author alu2017454
 */
public class IOMethods {
   
   private static final String SEPARATOR = File.separator;
   private static final File TXT_DATA_FOLDER = new File("txt-files");
   private static final File SUDOKUS_FILE = new File(TXT_DATA_FOLDER + SEPARATOR + "sudokus.txt");
   private static final File XML_DATA_FOLDER = new File("xml-files");
   private static final File SUDOKUS_XML_FILE = new File(XML_DATA_FOLDER + SEPARATOR + "sudokus.xml");
   private static final File USUARIOS_XML_FILE = new File(XML_DATA_FOLDER + SEPARATOR + "usuarios.xml");
   private static final File HISTORIAL_XML_FILE = new File(XML_DATA_FOLDER + SEPARATOR + "historial.xml");
   
   private static Sudokus sudokus;
 
   
   
    /**
     * Return true if sudokus.txt file exists.
     *
     * @return boolean
     */
    public static boolean existsSudokTxtFile() {
        return SUDOKUS_FILE.exists();
    }

    /**
     * Return true if usuarios.xml exists.
     *
     * @return boolean
     */
    public static boolean existsUsuariosXmlFile() {
        return USUARIOS_XML_FILE.exists();
    }
    
    /**
     * Return true if historial.xml exists.
     *
     * @return boolean
     */
    public static boolean existsHistorialXmlFile() {
        return HISTORIAL_XML_FILE.exists();
    }
    
    /**
     * Return true if sudokus.xml exists.
     *
     * @return boolean
     */
    public static boolean existsSudokusXmlFile() {
        return SUDOKUS_XML_FILE.exists();
    }
    
    /**
     * Returns list of Sudoku after load from sudokus.txt file
     * @return Sudokus JAXB list object
     */
    public static Sudokus loadSudokusFromTxt(){
        sudokus = new Sudokus();
        FileReader fr = null;
            try {  
                fr = new FileReader(SUDOKUS_FILE);
                BufferedReader br = new BufferedReader(fr);
                //lectura del fichero
                String linea;
                int numero = 1;
                BigInteger level = null;
                String description = null;
                String problem = null;
                String solved = null;
                while ((linea = br.readLine()) != null) {
                    switch(numero){
                        case 1: 
                             String[] linea1 = linea.split(" ");
                             level = new BigInteger(linea1[1]);
                             description = linea1[2];
                             numero++;
                             break;
                        case 2:
                             problem = linea;
                             numero++;
                             break;
                        case 3:
                             solved = linea;
                             numero = 1;
                             Sudoku sudoku = new Sudoku();
                             sudoku.setDescription(description);
                             sudoku.setLevel(level);
                             sudoku.setProblem(problem);
                             sudoku.setSolved(solved);
                             sudokus.getSudoku().add(sudoku);  
                    }    
                } 
                createSudokuXml();  
            }catch(IOException e){
                e.printStackTrace(System.out);
            } 
            finally{
                try {
                   if(fr != null){
                      fr.close();
                   }
                } catch (IOException ex) {
                   Logger.getLogger(IOMethods.class.getName()).log(Level.SEVERE, null, ex);
                }
            }         
          return sudokus;  
        }
    
    /**
     * Marshalling of Sudoku list (sudokus) into sudokus.xml file
     */
    public static void createSudokuXml() {
        try{
            JAXBContext jContext = JAXBContext.newInstance(Sudokus.class);
            Marshaller marshallObj = jContext.createMarshaller();
            if(!XML_DATA_FOLDER.exists()){
                XML_DATA_FOLDER.mkdir();
            }
            marshallObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshallObj.marshal(sudokus, SUDOKUS_XML_FILE);      
        } catch(JAXBException e) {
            e.printStackTrace(System.out);
        }
    }
    
    /**
     * Unmarshalling of sudokus.xml
     * @return Sudokus
     */
    public static Sudokus loadSudokus() {
         sudokus = new Sudokus();
         try {
            JAXBContext contexto = JAXBContext.newInstance(Sudokus.class);
            Unmarshaller u = contexto.createUnmarshaller();
            sudokus = (Sudokus) u.unmarshal(SUDOKUS_XML_FILE);
        } catch (JAXBException ex) {
            Logger.getLogger(IOMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sudokus;
    }
    
    /**
     * Unmarshalling of usuarios.xml
     * @return Usuarios
     */
    public static Usuarios loadUsuarios() {
        Usuarios usuarios = new Usuarios();
        try {
            JAXBContext contexto = JAXBContext.newInstance(Usuarios.class);
            Unmarshaller u = contexto.createUnmarshaller();
            usuarios = (Usuarios) u.unmarshal(USUARIOS_XML_FILE);
        } catch (JAXBException ex) {
            Logger.getLogger(IOMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }
    
    /**
     * Unmarshalling of historial.xml
     * @return Historial
     */
    public static Historial loadHistorial() {
         Historial historial = new Historial();
         try {
            JAXBContext contexto = JAXBContext.newInstance(Historial.class);
            Unmarshaller u = contexto.createUnmarshaller();
            historial = (Historial) u.unmarshal(HISTORIAL_XML_FILE);
        } catch (JAXBException ex) {
            Logger.getLogger(IOMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return historial;
    }
    
    /**
     * Marshalling to usuarios.xml after changes on object Usuarios
     * @param usuarios Usuarios
     */
     public static void userPersistence(Usuarios usuarios){
        try{
            JAXBContext jContext = JAXBContext.newInstance(Usuarios.class);
            Marshaller marshallObj = jContext.createMarshaller();
            marshallObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshallObj.marshal(usuarios, USUARIOS_XML_FILE);
        } catch(JAXBException e) {
            e.printStackTrace(System.out);
        }
    }
     
    /**
     * Marshalling to historial.xml after changes on object Historial
     * @param historial Historial
     */
    public static void historialPersistence(Historial historial){
        try{
            JAXBContext jContext = JAXBContext.newInstance(Historial.class);
            Marshaller marshallObj = jContext.createMarshaller();
            marshallObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshallObj.marshal(historial, HISTORIAL_XML_FILE);
        } catch(JAXBException e) {
            e.printStackTrace(System.out);
        }
    }

    
}