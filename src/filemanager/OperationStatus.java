package filemanager;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class OperationStatus extends JFrame {
    
    /**
     *
     */
    private static final long serialVersionUID = 7160498596997715614L;
    int min = 0;
    int max = 1;
    
    int procentMinus = 1;
    
    private JPanel allShtuchki = new JPanel();
    private JLabel procentString = new JLabel();
    private JButton miniStop = new JButton("||");
    private JButton unMiniStop = new JButton(">|");
    private JButton stop = new JButton("X");
    private JProgressBar status = new JProgressBar();
    
    public OperationStatus(long min, long max) {
        
        setTitle("Копирование");
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        setResizable(false);
        
        allShtuchki.setPreferredSize(new Dimension(220, 70));
        
        allShtuchki.setLayout(null);
        
        allShtuchki.setOpaque(true);
        
        int procentMinusMin = 1;
        int procentMinusMax = 1;
        
        if(min <= Integer.MAX_VALUE) {
            
            this.min = (int)(min);
            
        } else {
            
            long min2 = min;
            
            while(min2 > Integer.MAX_VALUE) {
                
                min2 = min2 / 10;
                
                procentMinusMin *= 10;
                
            }
            
        }
        
        if(max <= Integer.MAX_VALUE) {
            
            this.max = (int)(max);
            
        } else {
            
            long max2 = max;
            
            while(max2 > Integer.MAX_VALUE) {
                
                max2 = max2 / 10;
                
                procentMinusMax *= 10;
                
            }
            
        }
        
        if(procentMinusMin > 1 && procentMinusMax == 1) {
            
            procentMinus = procentMinusMin;
            
        } else if(procentMinusMax > 1 && procentMinusMin == 1) {
            
            procentMinus = procentMinusMax;
            
        } else if(procentMinusMin > 1 && procentMinusMin > 1) {
            
            procentMinus = Math.max(procentMinusMin, procentMinusMax);
            
        }
        
        if(min > 0) {
            
            this.min = (int)(min / procentMinus);
            
        } else {
            
            this.min = 0;
            
        }
        
        if(max > 0) {
            
            this.max = (int)(max / procentMinus);
            
        } else {
            
            this.max = 1;
            
        }
        
        status.setMinimum(this.min);
        status.setMaximum(this.max);
        
        procentString.setText("Выполнено: " + 0 + "%");
        
        allShtuchki.setBackground(Color.WHITE);
        status.setBackground(Color.WHITE);
        procentString.setBackground(Color.WHITE);
        miniStop.setBackground(Color.WHITE);
        stop.setBackground(Color.WHITE);
        
        status.setBounds(0, 40, 220, 30);
        procentString.setBounds(0, 0, 140, 40);
        miniStop.setBounds(140, 0, 40, 40);
        stop.setBounds(180, 0, 40, 40);
        
        allShtuchki.add(status);
        allShtuchki.add(procentString);
        allShtuchki.add(miniStop);
        allShtuchki.add(stop);
        
        getContentPane().add(allShtuchki);
        
        pack();
        
        setLocationRelativeTo(null);
        
        setVisible(true);
        System.out.println(getSize());
    }
    
    public void changeProcent(long value) {
        
        if((int)(value / procentMinus) <= this.max) {
            
            status.setValue((int)(value / procentMinus));
            
        }
        
        procentString.setText("Выполнено: " + (int)((double)value / (double)procentMinus / (double)status.getMaximum() * (double)100) + "%");
        
        revalidate();
        repaint();
        
    }
    
}
