package org.example.utilitarios.classes;

import javax.swing.*;
import java.awt.*;

public class ConverterArrayParaFoto {
    public static Image converterByteArray(byte[] array){
        return new ImageIcon(array).getImage();
    }
}
