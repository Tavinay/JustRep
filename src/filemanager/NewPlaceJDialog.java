package filemanager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewPlaceJDialog extends JDialog {
    
    /**
     *
     */
    private static final long serialVersionUID = 2681925289479742757L;
    private JTextField nameOfNewFolder = new JTextField(10);
    private JPanel allShtuchki = new JPanel();
    private JButton okButton = new JButton("Подтвердить");
    private JButton cancelButton = new JButton("Отмена");
    private String newFolderName;
    private JLabel newFolderWait = new JLabel("Новый путь : ");
    private boolean ready = false;

    public NewPlaceJDialog(JFrame jframe){ 
        
        super(jframe, "Переименовать", true);
        allShtuchki.setPreferredSize(new Dimension(220, 70));
        //setUndecorated(true);
        
        setResizable(false);
        
        allShtuchki.setLayout(new GridLayout(2, 2, 3, 3));
        
        okButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                newFolderName = nameOfNewFolder.getText();
                setVisible(false);
                ready = true;
            }   
        });
        
        cancelButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ready = false;
            }
        });
        
        allShtuchki.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(220, 220, 220)));
        
        okButton.setFocusable(false);
        cancelButton.setFocusable(false);
        
        allShtuchki.setBackground(Color.WHITE);
        okButton.setBackground(Color.WHITE);
        cancelButton.setBackground(Color.WHITE);
        
        allShtuchki.setOpaque(true);
        
        allShtuchki.add(newFolderWait);
        allShtuchki.add(nameOfNewFolder);
        allShtuchki.add(okButton);
        allShtuchki.add(cancelButton);
        getContentPane().add(allShtuchki);
        
        pack();
        
        setLocationRelativeTo(null);
        
        setVisible(true);
        
    }
        
        public String getNewName(){
            return newFolderName;
        }
        public boolean getReady(){
            return ready;
        }
        public void Waiting(){
            while(!ready){    
            }
        } 
}