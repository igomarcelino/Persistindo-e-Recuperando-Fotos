package org.example.DAO;

import org.example.utilitarios.imagens.ConverterArrayParaFoto;

import javax.sql.rowset.serial.SerialException;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FotoDAO {
    private Connection connection;

    public FotoDAO(Connection connection) {
        this.connection = connection;
    }

    private static String inserirFoto = """
                insert into fotos(nome, imagem) values(?, ?);
            """;

    private static String procurarFotoPeloID="SELECT *FROM FOTOS WHERE ID = ? ";

    public void setInserirFoto(String nome, byte[] bytes){

        try(PreparedStatement preparedStatement = connection.prepareStatement(inserirFoto)){

            preparedStatement.setString(1, nome);
            preparedStatement.setBytes(2,bytes);
            preparedStatement.execute();
        }
        catch (SerialException e){
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Image procurarFotoPeloId(Integer photo_id){
        byte[] byteArray = new byte[1];
        try(PreparedStatement preparedStatement = connection.prepareStatement(procurarFotoPeloID)){
            preparedStatement.setInt(1,photo_id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                byteArray= resultSet.getBytes("IMAGEM");
                return ConverterArrayParaFoto.converterByteArray(byteArray);
            }else {
                JOptionPane.showMessageDialog(null,"Id nao localizado","ERROR",JOptionPane.ERROR_MESSAGE);
                ;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
