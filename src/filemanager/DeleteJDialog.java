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
import javax.swing.JPanel;

public class DeleteJDialog extends JDialog {
    
    /**
     *
     */
    private static final long serialVersionUID = 2811959495871702130L;
    private int kodeUdaleniya = 4;
    private JPanel allShtuchki = new JPanel();
    // private JButton originalRecycle = new JButton("Удаление в оригинальную
    // корзину");
    //private JButton customRecycle = new JButton("Удаление в корзину программы");
    private JButton fullDelete = new JButton("Удаление безвозвратно");
    private JButton cancelButton = new JButton("Отмена");
    private boolean ready = false;

    public DeleteJDialog(JFrame jframe){ 
        
        super(jframe, "Удаление", true);
        allShtuchki.setPreferredSize(new Dimension(220, 70));
        //setUndecorated(true);
        
        setResizable(false);
        
        allShtuchki.setLayout(new GridLayout(2, 1, 3, 3));
        
        fullDelete.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                kodeUdaleniya = 3;
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
        
        fullDelete.setFocusable(false);
        cancelButton.setFocusable(false);
        
        allShtuchki.setBackground(Color.WHITE);
        fullDelete.setBackground(Color.WHITE);
        cancelButton.setBackground(Color.WHITE);
        
        allShtuchki.setOpaque(true);
        
        allShtuchki.add(fullDelete);
        allShtuchki.add(cancelButton);
        getContentPane().add(allShtuchki);
        
        pack();
        
        setLocationRelativeTo(null);
        
        setVisible(true);
        
    }
        
        public boolean getReady(){
            return ready;
        }
        public int getKodeUdaleniya(){
            return kodeUdaleniya;
        }
        public void Waiting(){
            while(!ready){    
            }
        } 
}