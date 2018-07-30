/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pruebas;

import com.conectionBd.Conection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author pc lenovo
 */
public class pruebas {
    
    static Conection pruebaconexion= new Conection();
     public static void main(String[] args) throws SQLException{
        
         llamarProcedimiento("1030456789", "Maria", "Mulata", "Vazques", "Mendoza", 1);
    
     
     }
     
     public static void llamarProcedimiento(String numeroCedula,String primerN,String segundN,
                                       String primerA, String segundoA ,int accion){
     
        pruebas.pruebaconexion.actualizarDatosProcedure(numeroCedula, primerN, segundN, primerA, segundoA, accion);
     
     
     }
     
     public static void hacerQuery() throws SQLException{
     
      
     String consulta="SELECT * FROM  dueño where cedula= ?";
     String parametro="1012421860";
     String consulta2="insert into dueño (cedula,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido)"+
             "values(?,?,?,?,?)";
         ArrayList<String> arregloP = new ArrayList();
         arregloP.add(0, "10176890");
           arregloP.add(1, "Monic");
             arregloP.add(2, "perez");
               arregloP.add(3, "lugan");
                 arregloP.add(4, "store");
     

     
     ResultSet resultado;
     /*
     El primer metodo verifica la conexion, en caso que el objeto string sea null 
     significa que la conexion es ok
     */
      resultado=pruebaconexion.consultaQuery(consulta, parametro);
             if (resultado.first()) {
                 
                 System.out.println(resultado.getString("primer_nombre")
                         +resultado.getString("segundo_nombre"));
             } else{
             
              System.out.println("la consulta no retorna resultados");
             
             }
             
        pruebaconexion.insertDatos(consulta2, arregloP);
             
     }
     
      
     
     
     
}

