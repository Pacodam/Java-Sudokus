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
import model.historials.Historial.Resuelto;
import model.sudokus.Sudokus;
import model.usuarios.Usuarios;
import model.usuarios.Usuarios.Usuario;

/**
 *
 * @author usuario
 */
public class IOMethodsTest {
    
      //la list donde iremos guardando los sudokus leidos en el txt
    static Sudokus sudokus;
    static Usuarios usuarios;
    static Historial historial;
    
        
    public static void loadSudokus(){
        
        File rutaXmlFile = new File("." + File.separator + "xml-files" + File.separator + "sudokus.xml");  //nueva File con el nombre de la carpeta
        FileReader fr = null;
        if (!rutaXmlFile.exists()) {
            File sudokusXml =  new File("."+ File.separator + "sudokus.txt");
            if(sudokusXml.exists()){
                try {  
                    sudokus = new Sudokus();
                    fr = new FileReader(sudokusXml);
                    BufferedReader br = new BufferedReader(fr);
                    //lectura del fichero
                    String linea;
                    int numero = 1;
                    Sudokus.Sudoku sudoku = new Sudokus.Sudoku();
                    while ((linea = br.readLine()) != null) {
                        switch(numero){
                            case 1: 
                                 String[] linea1 = linea.split(" ");
                                 sudoku.setLevel(new BigInteger(linea1[1]));
                                 sudoku.setDescription(linea1[2]);
                                 numero++;
                                 break;
                            case 2:
                                 sudoku.setProblem(linea);
                                 numero++;
                                 break;
                            case 3:
                                 sudoku.setSolved(linea);
                                 numero = 1;
                                 sudokus.getSudoku().add(sudoku);
                                 int size = sudokus.getSudoku().size()-1;
                                     
                        }    
                    } 
                    createSudokuXml();  
                }catch(IOException e){
                    e.printStackTrace(System.out);
                }      
        }
     
    }
    }
    
    public static void createSudokuXml() {
      
        
        try{
            //creating the JAXB context
            JAXBContext jContext = JAXBContext.newInstance(Sudokus.class);
            //creating the marshaller object
            Marshaller marshallObj = jContext.createMarshaller();
            //setting the property to show xml format output
            marshallObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //Marshal the employees list in file
            marshallObj.marshal(sudokus, new File("./sudokus.xml"));
            //Marshal the employees list in console
            //marshallObj.marshal(sudokus, System.out);
              
        } catch(JAXBException e) {
            e.printStackTrace(System.out);
        }
    }
    
    public static void loadUsuarios() {
        try {
            JAXBContext contexto = JAXBContext.newInstance(Usuarios.class);
            Unmarshaller u = contexto.createUnmarshaller();
            File usuariosFile =  new File("."+ File.separator + "usuarios.xml");
            if(usuariosFile.exists()){
              usuarios = (Usuarios) u.unmarshal(usuariosFile);
              for (Usuario user : usuarios.getUsuario()){
                  System.out.println(u);
              }
            }
            else usuarios = new Usuarios();
        } catch (JAXBException ex) {
            Logger.getLogger(IOMethodsTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void loadHistorial() {
         try {
            JAXBContext contexto = JAXBContext.newInstance(Usuarios.class);
            Unmarshaller u = contexto.createUnmarshaller();
            File historialFile =  new File("."+ File.separator + "historial.xml");
            if(historialFile.exists()){
              historial = (Historial) u.unmarshal(new File("historial.xml"));
              for (Resuelto solved : historial.getResuelto()){
                 System.out.println(solved);
              }
            }
            else historial = new Historial();
        } catch (JAXBException ex) {
            Logger.getLogger(IOMethodsTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static boolean userPersistence(String name, String username, String password){
        
        for(Usuario us: usuarios.getUsuario()){
            if(username.equals(us.getNombreUsuario())){
                return false;
            }
        }
        Usuario user = new Usuarios.Usuario();
        user.setNombreCompleto(name);
        user.setNombreUsuario(username);
        user.setPassword(password);
        usuarios.getUsuario().add(user);
        try{
            //creating the JAXB context
            JAXBContext jContext = JAXBContext.newInstance(Usuarios.class);
            //creating the marshaller object
            Marshaller marshallObj = jContext.createMarshaller();
            //setting the property to show xml format output
            marshallObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //Marshal the employees list in file
            marshallObj.marshal(usuarios, new File("./usuarios.xml"));
            //Marshal the employees list in console
            marshallObj.marshal(usuarios, System.out);
            return true;
              
        } catch(JAXBException e) {
            e.printStackTrace(System.out);
            return false;
        }
    }
}
