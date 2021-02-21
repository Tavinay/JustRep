package filemanager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TabButton extends JPanel {
    
    /**
     *
     */
    private static final long serialVersionUID = 5305702118550490340L;
    private JLabel textLabel = new JLabel();
    private JButton closeButton = new JButton();
    
    public TabButton(String text, int width, int height) {
        
        setLayout(null);
        
        if(text != null) {
            
            textLabel.setText(text);
            
        }
        
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);
        setOpaque(false);
        
        closeButton.setContentAreaFilled(false);
        closeButton.setBorderPainted(false);
        closeButton.setFocusable(false);
        
        Image closeImage = new ImageIcon(new File("src\\Resources\\Button resources\\CloseButton.png").getAbsolutePath()).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        closeButton.setIcon(new ImageIcon(closeImage));
        
        textLabel.setBounds(5, 0, (width - 25 - 5), height);
        
        closeButton.setBounds((width - 25 - 5 + 5), height / 2 - 10, 20, 20);
        
        add(closeButton);
        
        add(textLabel);
        
    }
    
    public String getText() {
        
        return textLabel.getText();
        
    }
    
    public void setText(String text) {
        
        textLabel.setText(text);
        
    }
    
    public JLabel getTextLabel() {
        
        return textLabel;
        
    }
    
    public void setTextLabel(JLabel textLabel) {
        
        this.textLabel = textLabel;
        
    }
    
    public JButton getCloseButton() {
        
        return closeButton;
        
    }
    
    public void setCloseButton(JButton closeButton) {
        
        this.closeButton = closeButton;
        
    }
    
    public void setCloseButtonAL(ActionListener al) {
        
        for(ActionListener actionListener : this.closeButton.getActionListeners()) {
        
            this.closeButton.removeActionListener(actionListener);
        
        }
        
        this.closeButton.addActionListener(al);
        
    }
    
    protected void paintComponent(Graphics g) {
        
        Graphics2D g2d = (Graphics2D)g;
            
        super.paintComponent(g2d);
        
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);
            
        Toolkit.getDefaultToolkit().sync();
        
//        if (getModel().isArmed()) {
//            g2d.setColor(new Color(210, 210, 210));
//        } else {
            g2d.setColor(getBackground());
//        }
        
        g2d.fillRoundRect(0, 0, (int)((double)getSize().width), (int)((double)getSize().height), 25, 25);
        
    }
    
    protected void paintBorder(Graphics g) {
        
        Graphics2D g2d = (Graphics2D)g;
            
        super.paintComponent(g2d);
        
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);
            
        Toolkit.getDefaultToolkit().sync();
       
        g2d.setColor(Color.lightGray);
        
        g2d.drawRoundRect(0, 0, (int)((double)getSize().width), (int)((double)getSize().height), 25, 25);
        
    }

    Shape shape;
    public boolean contains(int x, int y) {
        if (shape == null ||
                !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, (int)((double)getSize().width), (int)((double)getSize().height), 25, 25);
        }
        return shape.contains(x, y);
    }
    
}
