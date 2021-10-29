/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formula1.modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author 57305
 */
public class ConsultasEscuderia extends ModeloBD {
    
    private PreparedStatement consultaSQL;
    private ResultSet resultadoSQL;

    public ConsultasEscuderia() {
    }
    
    public boolean insertarEscuderia(Escuderia escuderia) {
        
        //almacenar la conexion con la BD
        Connection conexion=conectar();
        
        //armamos la consulta SQL
        String query="INSERT INTO escuderias(id,nombre,presupuesto,tipo_motor) "
                + "VALUES (?,?,?,?)";
        
        //intento ejecutar la consulta BD
        try{
            //preparo la consulta en la BD
            consultaSQL=conexion.prepareStatement(query);
            
            //organizo los datos en los que voy a ingresar en los ?
            consultaSQL.setString(1, escuderia.getId());
            consultaSQL.setString(2, escuderia.getNombre());
            consultaSQL.setInt(3, escuderia.getPresupuesto());
            consultaSQL.setString(4, escuderia.getTipoMotor());
            
            //ejecuto la consulta preparada
            int resultado=consultaSQL.executeUpdate();
            
            //valido el resultado
            if(resultado>0){
                return true;
                
            }else{
                return false;
            }
            
        }catch(Exception error){
        
            System.out.println("upss..." +error);
            return false;
        }
        
    }
    
    
    public Escuderia buscarEscuderia(String id){
        //creamos un objeto de tipo escuderia
        Escuderia escuderia=new Escuderia();
        
        //almacenar la conexion con la BD
        Connection conexion=conectar();
        
        //armamos la query de SQL
        String query="SELECT * FROM escuderias WHERE id=?";
        
         try{
             
            //preparo la consulta en la BD
            consultaSQL=conexion.prepareStatement(query);
            
             //organizo los datos en los que voy a ingresar en los ?
             consultaSQL.setString(1, id);
             
             //ejecuto la consulta
             resultadoSQL=consultaSQL.executeQuery();
             
             //organizamos los datos de llegada
             if(resultadoSQL.next()){
                 
                 escuderia.setId(resultadoSQL.getString("id"));
                 escuderia.setNombre(resultadoSQL.getString("nombre"));
                 escuderia.setPresupuesto(resultadoSQL.getInt("presupuesto"));
                 escuderia.setTipoMotor(resultadoSQL.getString("tipo_motor"));
                 return escuderia;
                 
             }else{
                 return null;
             }
                     
         }catch(Exception error){
             System.out.println("upsss.." +error);
             return null;
         }
         
         
        
    }
}
