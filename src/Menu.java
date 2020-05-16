
import Additionals.Texto;
import Conection.Server;
import Processing.DuchaInfo;
import Processing.Persistencia;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.*;
import java.text.DecimalFormat;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class Menu extends JFrame implements ActionListener {

    private JComboBox stratum;
    private JPanel panel, buttonPanel;
    private JButton assignStratum, showData, loadPersistence, enterPage, Exit, saveStratum, update, outTable, averages;
    private JLabel logo;
    int n = 0;

    public Menu() {
        DatosVentana();
    }

    public void DatosVentana() {

        this.setTitle(Texto.WINDOW_TITLE);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);       
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(52,152,219));
        this.getContentPane().add(panel);      
        Botones();

    }

    public void Botones() {
        
        logo = new JLabel("logo");
        logo.setBounds(225, 30, 50, 50);
        
        ImageIcon im1 = new ImageIcon(getClass().getResource("/Resources/edificios.png"));
        ImageIcon im2 = new ImageIcon(im1.getImage().getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_DEFAULT));
        logo.setIcon(im2);
        panel.add(logo);
        
        buttonPanel = new JPanel();
        buttonPanel.setBounds(163, 100, 174, 300);
        buttonPanel.setBackground(new Color(52,152,219));
        panel.add(buttonPanel);
        buttonPanel.setLayout(null);
        
        assignStratum = new JButton(Texto.BTN1);
        assignStratum.setBounds(0, 0, 174, 25);
        assignStratum.addActionListener(this);
        
        showData = new JButton(Texto.BTN2);
        showData.setBounds(0, 50, 174, 25);
        showData.addActionListener(this);
        
        loadPersistence = new JButton(Texto.BTN3);
        loadPersistence.setBounds(0, 100, 174, 25);
        loadPersistence.addActionListener(this);

        averages = new JButton(Texto.BTN9);
        averages.setBounds(0, 150, 174, 25);
        averages.addActionListener(this);

        enterPage = new JButton(Texto.BTN4);
        enterPage.setBounds(0, 200, 174, 25);
        enterPage.addActionListener(this);

        Exit = new JButton(Texto.BTN5);
        Exit.setBounds(0, 250, 174, 25);
        Exit.addActionListener(this);

        buttonPanel.add(assignStratum);
        buttonPanel.add(showData);
        buttonPanel.add(loadPersistence);
        buttonPanel.add(enterPage);
        buttonPanel.add(averages);
        buttonPanel.add(Exit);
        
        stratum = new JComboBox();
        stratum.setBounds(350, 100, 50, 25);
        for (int i = 1; i <= 6; i++) {
            stratum.addItem(i);
        }
        panel.add(stratum);
        stratum.setVisible(false);
        
        saveStratum = new JButton(Texto.BTN6);
        saveStratum.setBounds(330, 150, 100, 25);
        saveStratum.setVisible(false);
        saveStratum.addActionListener(this);
        panel.add(saveStratum);

      
        update = new JButton(Texto.BTN7);
        update.setBounds(163, 330, 174, 25);
        update.setVisible(false);
        update.addActionListener(this);
        
        outTable = new JButton(Texto.BTN8);
        outTable.setBounds(163, 370, 174, 25);
        outTable.setVisible(false);
        outTable.addActionListener(this);
        
        
    }

    public static void main(String[] args) {
        Server.recibirParametros();
        Persistencia.WriteFile();
        Texto.language();
        Menu m1 = new Menu();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == assignStratum) {
            
            buttonPanel.setLocation(103, 100);
            stratum.setVisible(true);
            saveStratum.setVisible(true);

        } else if (e.getSource() == showData) {
            
            stratum.setVisible(false);
            saveStratum.setVisible(false);
            buttonPanel.setLocation(163, 100);

            if (DuchaInfo.Estrato <= 0 || DuchaInfo.Estrato > 6) {
                JOptionPane.showMessageDialog(null, Texto.AV1);
            } else {                
                DuchaInfo.duchas.clear();
                Server.recibirParametros();
                MostrarTabla();
            }

        } else if (e.getSource() == loadPersistence) {           
            Persistencia.LoadFile();
        } else if (e.getSource() == averages) {
            Promedio();
        } else if (e.getSource() == enterPage) {
            
            //Conexión con la página
            
        } else if (e.getSource() == Exit) {
            System.exit(0);

        } else if (e.getSource() == saveStratum) {
            
            DuchaInfo.Estrato = (int) stratum.getSelectedItem();
            for (int i = 0; i < DuchaInfo.duchas.size(); i++) {
                DuchaInfo.duchas.get(i).CalcCost();
            }
            
            buttonPanel.setLocation(163, 100);
            stratum.setVisible(false);
            saveStratum.setVisible(false);

        } else if (e.getSource() == update) {
            DuchaInfo.duchas.clear();
            Server.recibirParametros();
            MostrarTabla();
            
        } else if (e.getSource() == outTable) {
            panel.removeAll();
            revalidate();
            repaint();
            Botones();
        }

    }

    public void MostrarTabla() {

        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn(Texto.DATE1);
        modelo.addColumn(Texto.DATE2+"(L)");
        modelo.addColumn(Texto.DATE3+"(m)");
        modelo.addColumn(Texto.DATE4);

        int h = DuchaInfo.duchas.size();

        String tabla[] = new String[4];

        for (int i = 0; i < h; i++) {

            for (int j = 0; j < 4; j++) {
                switch (j) {
                    case 0:
                        tabla[j] = DuchaInfo.duchas.get(i).getFecha();
                        break;
                    case 1:
                        tabla[j] = Double.toString(DuchaInfo.duchas.get(i).getGasto()) + "L";
                        ;
                        break;
                    case 2:
                        tabla[j] = Double.toString(DuchaInfo.duchas.get(i).getTiempo()) + "m";
                        break;
                    case 3:
                        tabla[j] = Double.toString(DuchaInfo.duchas.get(i).getCosto()) + "$";
                        break;

                }

            }
            modelo.addRow(tabla);
        }

        JTable t = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(t);
        scroll.setBackground(new Color(52,152,219));
        scroll.setBounds(0, 0, 500, 300);
        panel.add(scroll);
        Botones2();
        

    }

    private void Promedio() {
        int lenght = DuchaInfo.duchas.size();
        DecimalFormat format1 = new DecimalFormat("#.00");
        if (lenght > 0) {
            double average[] = new double[3];
            for (int i = 0; i < lenght; i++) {
                average[0] += DuchaInfo.duchas.get(i).getGasto();
                average[1] += DuchaInfo.duchas.get(i).getTiempo();
                average[2] += DuchaInfo.duchas.get(i).getCosto();
            }
            average[0] /= lenght;
            average[1] /= lenght;
            average[2] /= lenght;

            JOptionPane.showMessageDialog(null, Texto.DATE2+"(L): " + format1.format(average[0]) +" "+ Texto.DATE3+"(m): " + format1.format(average[1]) + " "+ Texto.DATE3+": " + format1.format(average[2]));

        } else {
            JOptionPane.showMessageDialog(null, Texto.AV3);
        }
    }
    
    public void Botones2(){   
        buttonPanel.setVisible(false);
        logo.setVisible(false);
        update.setVisible(true);
        outTable.setVisible(true);
        panel.add(update);    
        panel.add(outTable);    
    }

}
