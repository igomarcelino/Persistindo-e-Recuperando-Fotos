package org.example.apresentacao;

import org.example.DAO.FotoDAO;
import org.example.dados.Conexao;
import org.example.utilitarios.classes.ConverterFotoParaArray;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Tela extends JFrame {

    private JLabel labelImagem;
    private JLabel labelFotoBanco;
    private JButton btnEnviar;
    private JButton btnBuscaFotoBanco;
    private JButton procurarFoto;
    private Icon guardaFoto;
    private JTextField textNomeFoto;
    private JTextField textIdFotoBanco;
    private JLabel labelNomeFoto;

    public Tela(){
        construirTela();
    }


    public void construirTela(){
        setSize(800,400);
        setTitle("Salvar Foto");
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        adicionarCampos();
        adicionarBotoes();
    }

    private void adicionarCampos() {
        labelImagem = new JLabel();
        labelImagem.setBounds(100,30,270,200);
        labelImagem.setIcon(adicionarImagemPadrao());
        getContentPane().add(labelImagem);



        labelNomeFoto = new JLabel("Nome da foto: ");
        labelNomeFoto.setBounds(83,250,150,25);
        getContentPane().add(labelNomeFoto);


        textNomeFoto = new JTextField();
        textNomeFoto.setBounds(190,250,150,25);
        getContentPane().add(textNomeFoto);

    }



    public void adicionarBotoes(){
        btnEnviar = new JButton("Enviar");
        btnEnviar.setBounds(270,300,90,30);
        btnEnviar.addActionListener(persistirFoto());
        getContentPane().add(btnEnviar);

        procurarFoto = new JButton("procurar foto");
        procurarFoto.setBounds(100,300,150,30);
        procurarFoto.addActionListener(selecionarFoto());
        getContentPane().add(procurarFoto);

        btnBuscaFotoBanco = new JButton("Album");
        btnBuscaFotoBanco.setFont(new Font("Arial", Font.BOLD, 14));
        btnBuscaFotoBanco.setBounds(440,300,90,30);
        btnBuscaFotoBanco.addActionListener(mostrarAlbum());
        getContentPane().add(btnBuscaFotoBanco);
    }

    public Icon adicionarImagemPadrao(){
        Image preIcon = new ImageIcon("/home/igo/Dev/git/study/gitStudy/Languages/Java/Spring/PersistindoFoto/src/main/java/org/example/utilitarios/imagens/images.png").getImage().getScaledInstance(200,200,Image.SCALE_SMOOTH);
        ImageIcon iconPadrao = new ImageIcon(preIcon);

        return iconPadrao;
    }



    // Listener para selecionar a foto a salvar
    private ActionListener selecionarFoto(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                int opcao = jFileChooser.showOpenDialog(null);
                if (opcao == JFileChooser.APPROVE_OPTION){
                    File novaFoto = jFileChooser.getSelectedFile();
                    String caminho = novaFoto.getAbsolutePath();
                    Image fotoParaSalvar = renderizadorDeFotos(caminho);
                    guardaFoto = new ImageIcon(fotoParaSalvar);

                    labelImagem.setIcon(guardaFoto);
                }
            }
        };
    }

    private ActionListener persistirFoto(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FotoDAO fotoDAO = new FotoDAO(Conexao.conexaoSQL());
                fotoDAO.setInserirFoto(textNomeFoto.getText(), ConverterFotoParaArray.converFotoArray(guardaFoto));
                System.out.println(ConverterFotoParaArray.converFotoArray(guardaFoto).toString());
                textNomeFoto.setText("");
                labelImagem.setIcon(adicionarImagemPadrao());
                JOptionPane.showMessageDialog(null,"Foto gravada com sucesso","Banco de dados",JOptionPane.INFORMATION_MESSAGE);
            }
        };
    }

    private ActionListener buscarFotoBanco(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FotoDAO fotoDAO = new FotoDAO(Conexao.conexaoSQL());
                Image foto = fotoDAO.procurarFotoPeloId(Integer.valueOf(textIdFotoBanco.getText()));
                    if (foto != null){
                        labelFotoBanco.setIcon(new ImageIcon(foto));
                    }else {
                        labelFotoBanco.setIcon(adicionarImagemPadrao());
                    }


            }
        };
    }

    private ActionListener mostrarAlbum(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Album album = new Album();
                album.setVisible(true);


            }
        };
    }

    /**
     * Renderizador de imagens
     * */
    public Image renderizadorDeFotos(String imagePath){
        return new ImageIcon(imagePath).getImage().getScaledInstance(400,400,Image.SCALE_SMOOTH);
    }

}
