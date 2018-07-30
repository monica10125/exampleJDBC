/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conectionBd;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author pc lenovo
 */
public class Conection {
   
Connection conexion;
public static final String URL="jdbc:mysql://localhost:3307/mydbjdbc?zeroDateTimeBehavior=convertToNull";
public static final String USUARIO="root";
public static final String CLAVE="boris1012";


public  String establecerConexionbd(){
   String validoOk="conexionOK";

 
    try {
       this.conexion= DriverManager.getConnection(URL, USUARIO, CLAVE);
    } catch (SQLException ex) {
       
         System.out.println("se ha producido un error en la conexion"+ex.getMessage());
         validoOk=ex.getMessage();
    }
    
    return validoOk;
 
    
}

public ResultSet consultaQuery(String consulta,String parametro) throws SQLException {

    ResultSet resultado = null;
    String validacion;
    validacion = establecerConexionbd();
    if (validacion.equals("")) {
        try {   
            /*
        en este caso preparamos una consulta, la conexion ya esta inicializada desde el primer metodo
             */
           PreparedStatement consultaPreparada = this.conexion.prepareStatement(consulta);
         //   Statement myStmt = this.conexion.createStatement();
           consultaPreparada.setString(1, parametro);

            resultado = consultaPreparada.executeQuery();

        } catch (SQLException e) {

                
            throw e;
        
        
        }

    }

    return resultado;
}

public String insertDatos(String consulta, ArrayList<String> parametros){

 String validacion;
 String MensajeBd=null;
    validacion = establecerConexionbd();
    if (validacion.equals("conexionOK")) {
        try {  
           
            if (!parametros.isEmpty()) {
               PreparedStatement consultaPreparada = this.conexion.prepareStatement(consulta); 
               consultaPreparada.setString(1, parametros.get(0));
               consultaPreparada.setString(2, parametros.get(1));
               consultaPreparada.setString(3, parametros.get(2));
               consultaPreparada.setString(4, parametros.get(3));
               consultaPreparada.setString(5, parametros.get(4));
               consultaPreparada.executeUpdate();
            }

            
            
        }catch(SQLException e){
         MensajeBd="error "+e.getMessage();
        
        }
        
        MensajeBd="El registro ha sido correcto";
    }
    
    return  MensajeBd;
}



public void  actualizarDatosProcedure(String numeroCedula,String primerN,String segundN,
                                       String primerA, String segundoA ,int accion){

    String verificaConexion= "";
    CallableStatement mystp=null;
    verificaConexion= establecerConexionbd();
    if (verificaConexion.equals("conexionOK")) {
        
        try {
             mystp= this.conexion.prepareCall("{call mydbjdbc.OperacionesUsuario(?,?,?,?,?,?)}");
             mystp.setString(1, numeroCedula);
             mystp.setString(2, primerN);
             mystp.setString(3, segundN);
             mystp.setString(4, primerA);
             mystp.setString(5, segundoA);
             mystp.setInt(6, accion);
             mystp.execute();
        } catch (Exception e) {
            System.out.println("se ha presentado una exepcion "+ e.getMessage());
        
        }
       
        
        
    }
   


}

}
            
   
    
   

   
    





   

