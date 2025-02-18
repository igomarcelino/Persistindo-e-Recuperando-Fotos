package org.example.dados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static String url = "jdbc:postgresql://localhost:5432/fotodata";
    private static String user = "postgres";
    private static String password = "postgres";
    public static Connection conexaoSQL(){
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(url,user,password);
        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException.getMessage());
        }catch (ClassNotFoundException classNotFoundException){
            throw new ClassCastException(classNotFoundException.getMessage());
        }

    }
}

/**
 * Esse metodo ira tentar realizar a conexao com o banco de dados , procurando
 * no pacote postgres pela classe Driver, caso o endereco do banco e as
 * credenciais estejam corretas retornara a conexao
 * */