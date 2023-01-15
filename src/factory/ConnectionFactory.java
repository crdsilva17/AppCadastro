/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;
//faz as importações de classes necessárias para o funcionamento do programa
import java.sql.Connection;
//conexão SQL para Java
import java.sql.DriverManager;
//Driver de conexão SQL para Java
import java.sql.SQLException;
//classe para tratamento de exceções
/**
 *
 * @author crist
 */
public class ConnectionFactory {
    
    public Connection getConnection(){
        try{
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/projetojava?serverTimezone=UTC"
                    ,"root"
                    ,""
            );
            return conn;
        }
        catch(SQLException e){
            System.out.println("Erro ao Conectar!!" + e.getMessage());
            return null;
        }
    }
    
}
