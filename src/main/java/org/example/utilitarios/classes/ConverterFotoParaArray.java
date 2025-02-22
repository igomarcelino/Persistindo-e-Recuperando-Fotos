package org.example.utilitarios.classes;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ConverterFotoParaArray {

    public static byte[] converFotoArray(Icon icon){

        if (icon == null){
            return null;
        }

        BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        icon.paintIcon(null, graphics2D,0,0);
        graphics2D.dispose();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try{
            ImageIO.write(bufferedImage,"jpg",byteArrayOutputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }
}

/**
 * Detalhando o metodo
 *
 * Esse método `converFotoArray` tem a função de converter uma imagem representada por um objeto `Icon` em um array de bytes (`byte[]`), que pode ser usado para armazenar a imagem, por exemplo, em um banco de dados ou enviá-la por rede. Vamos detalhar passo a passo:
 *
 * ---
 *
 * ### 1. **Verifica se o ícone é nulo**
 *    ```java
 *    if (icon == null){
 *        return null;
 *    }
 *    ```
 *    - Se o ícone passado for `null`, o método retorna `null`. Isso evita erros ao tentar processar um ícone inexistente.
 *
 * ---
 *
 * ### 2. **Criação de um `BufferedImage`**
 *    ```java
 *    BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
 *    ```
 *    - Um `BufferedImage` é criado para armazenar os dados da imagem.
 *    - A largura e a altura são obtidas do ícone (`icon.getIconWidth()` e `icon.getIconHeight()`).
 *    - O tipo da imagem é `BufferedImage.TYPE_INT_RGB`, que representa uma imagem em cores (sem transparência).
 *
 * ---
 *
 * ### 3. **Desenhar o ícone no `BufferedImage`**
 *    ```java
 *    Graphics2D graphics2D = bufferedImage.createGraphics();
 *    icon.paintIcon(null, graphics2D, 0, 0);
 *    graphics2D.dispose();
 *    ```
 *    - `createGraphics()` cria um contexto gráfico para desenhar a imagem.
 *    - `icon.paintIcon(null, graphics2D, 0, 0)` desenha o ícone no `BufferedImage`, com o canto superior esquerdo posicionado em `(0,0)`.
 *    - `graphics2D.dispose()` libera os recursos gráficos após o desenho.
 *
 * ---
 *
 * ### 4. **Criação de um `ByteArrayOutputStream`**
 *    ```java
 *    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
 *    ```
 *    - Um fluxo de saída em memória é criado para armazenar os bytes da imagem.
 *
 * ---
 *
 * ### 5. **Escrevendo a imagem em bytes**
 *    ```java
 *    try {
 *        ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
 *    } catch (IOException e) {
 *        e.printStackTrace();
 *    }
 *    ```
 *    - `ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream)` converte a imagem em formato JPEG e a grava no fluxo de bytes.
 *    - Se ocorrer uma exceção `IOException`, a pilha de erros será impressa no console (idealmente, seria melhor lançar ou registrar o erro).
 *
 * ---
 *
 * ### 6. **Retornar os bytes da imagem**
 *    ```java
 *    return byteArrayOutputStream.toByteArray();
 *    ```
 *    - Converte o conteúdo do `ByteArrayOutputStream` em um array de bytes e o retorna.
 *
 * ---
 * */
