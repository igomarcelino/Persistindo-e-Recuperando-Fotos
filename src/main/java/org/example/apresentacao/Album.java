package org.example.apresentacao;
import org.example.DAO.FotoDAO;
import org.example.dados.Conexao;
import org.example.dominio.Foto;
import org.example.utilitarios.classes.ConverterArrayParaFoto;

import java.util.List;
import javax.swing.*;
import java.awt.*;

public class Album extends JFrame {
    JFrame frame = new JFrame("Album de Fotos");
    public Album() {
        contruirTela();
        frame.setVisible(true);
    }

    private void contruirTela() {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 400);
        criarAlbum();
    }


        public void criarAlbum() {
            FotoDAO fotoDAO = new FotoDAO(Conexao.conexaoSQL());

            List<Foto> album = fotoDAO.albumFoto();

            ImageIcon[] icon = new ImageIcon[album.size()];


            for (int i = 0; i < album.size(); i++){
                byte[] fotoArray = album.get(i).getBytes();
                icon[i] = new ImageIcon(ConverterArrayParaFoto.converterByteArray(fotoArray));
            }
            Image[] scaledImage = new Image[album.size()];
            for (int i = 0; i < album.size(); i++){
                scaledImage[i] = icon[i].getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
            }
            ImageIcon[] scaledIcon = new ImageIcon[album.size()];
            for (int i = 0; i< album.size(); i++){
                scaledIcon[i] = new ImageIcon(scaledImage[i]);
            }
            // Criar JComboBox com imagens
            JComboBox<ImageIcon> comboBox = new JComboBox<>();
            for (int i =0; i < album.size(); i++) {

                comboBox.addItem(scaledIcon[i]);

            }
            comboBox.setRenderer(new ModernGalleryRenderer());

            frame.add(comboBox, BorderLayout.CENTER);

        }



    }



    // Renderer moderno para exibir imagens em uma galeria com bordas e seleção
     class ModernGalleryRenderer extends JPanel implements ListCellRenderer<ImageIcon> {
        private JLabel label = new JLabel();

        public ModernGalleryRenderer() {
            setLayout(new BorderLayout());
            label.setHorizontalAlignment(JLabel.CENTER);
            add(label, BorderLayout.CENTER);
            setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends ImageIcon> list, ImageIcon value, int index, boolean isSelected, boolean cellHasFocus) {
            label.setIcon(value);
            if (isSelected) {
                setBackground(new Color(135, 206, 250));  // Azul claro para destaque
                setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
            } else {
                setBackground(Color.WHITE);
                setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            }
            setOpaque(true);
            return this;
        }
    }

