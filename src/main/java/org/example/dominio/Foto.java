package org.example.dominio;

import javax.swing.*;
import java.util.Arrays;

public class Foto {

    private Integer id;
    private String nome;
    private byte[] bytes;

    public Foto() {
    }

    public Foto(Integer id, String nome, byte[] bytes) {
        this.id = id;
        this.nome = nome;
        this.bytes = bytes;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public String toString() {
        return "Foto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", bytes=" + Arrays.toString(bytes) +
                '}';
    }
}
