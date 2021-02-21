package filemanager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JButton;

public class ButtonManager extends JButton {
    
    /**
     *
     */
    private static final long serialVersionUID = -3442939484423203518L;
    String text = null;

    public ButtonManager(String text) {
        if(text != null) {
            
            this.text = text;
            
            setText(text);
            
        }
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);
        setContentAreaFilled(false);
    }
    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.lightGray);
        } else {
            g.setColor(getBackground());
        }
        //g.fillOval(2, -2, getSize().height + 3, getSize().height + 3);
        
        super.paintComponent(g);
    }
    
//    protected void paintBorder(Graphics g) {
//        g.setColor(getForeground());
//        //g.drawRect(getSize().width / 2 - getSize().height / 2, 0, getSize().height - 1, getSize().height - 1);// Возможно оставлю
//        g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
//    }

    Shape shape;
    @Override
    public boolean contains(int x, int y) {
        
        if(text == null) {
            
            if (shape == null || !shape.getBounds().equals(getBounds())) {
                shape = new Ellipse2D.Float(getSize().width / 2 - getSize().height / 2, 0, getHeight(), getHeight());
            }
        
        } else {
            
            if (shape == null || !shape.getBounds().equals(getBounds())) {
                shape = new Rectangle2D.Float(0, 0, getWidth(), getHeight());
            }
            
        }
        
        return shape.contains(x, y);
        
    }
    
}
//    //Кнопка назад
//    public class BackButton extends JButton {
//    public BackButton() {
//        Dimension size = getPreferredSize();
//        size.width = size.height = Math.max(size.width, size.height);
//        setPreferredSize(size);
//        setContentAreaFilled(false);
//    }
//    protected void paintComponent(Graphics g) {
//        if (getModel().isArmed()) {
//            g.setColor(Color.lightGray);
//        } else {
//            g.setColor(getBackground());
//        }
//        g.fillOval(getSize().width / 2 - getSize().height / 2, 0, getSize().height - 1, getSize().height - 1);
//
//        super.paintComponent(g);
//    }
//    protected void paintBorder(Graphics g) {
//        g.setColor(getForeground());
//        //g.drawRect(getSize().width / 2 - getSize().height / 2, 0, getSize().height - 1, getSize().height - 1);// Возможно оставлю
//        //g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
//    }
//
//    Shape shape;
//    public boolean contains(int x, int y) {
//        if (shape == null ||
//                !shape.getBounds().equals(getBounds())) {
//            shape = new Ellipse2D.Float(getSize().width / 2 - getSize().height / 2, 0, getHeight(), getHeight());
//        }
//        return shape.contains(x, y);
//    }
//}
//    //Кнопка вперёд  
//    public class NextButton extends JButton {
//    public NextButton() {
//        Dimension size = getPreferredSize();
//        size.width = size.height = Math.max(size.width, size.height);
//        setPreferredSize(size);
//        setContentAreaFilled(false);
//    }
//    protected void paintComponent(Graphics g) {
//        if (getModel().isArmed()) {
//            g.setColor(Color.lightGray);
//        } else {
//            g.setColor(getBackground());
//        }
//        g.fillOval(getSize().width / 2 - getSize().height / 2, 0, getSize().height - 1, getSize().height - 1);
//
//        super.paintComponent(g);
//    }
//    protected void paintBorder(Graphics g) {
//        g.setColor(getForeground());
//        //g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
//    }
//
//    Shape shape;
//    public boolean contains(int x, int y) {
//        if (shape == null ||
//                !shape.getBounds().equals(getBounds())) {
//            shape = new Ellipse2D.Float(getSize().width / 2 - getSize().height / 2, 0, getHeight(), getHeight());
//        }
//        return shape.contains(x, y);
//    }
//}
//    //Кнопка вверх
//    public class UpButton extends JButton {
//    public UpButton() {
//        Dimension size = getPreferredSize();
//        size.width = size.height = Math.max(size.width, size.height);
//        setPreferredSize(size);
//        setContentAreaFilled(false);
//    }
//    protected void paintComponent(Graphics g) {
//        if (getModel().isArmed()) {
//            g.setColor(Color.lightGray);
//        } else {
//            g.setColor(getBackground());
//        }
//        g.fillOval(getSize().width / 2 - getSize().height / 2, 0, getSize().height - 1, getSize().height - 1);
//
//        super.paintComponent(g);
//    }
//    protected void paintBorder(Graphics g) {
//        g.setColor(getForeground());
//        //g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
//    }
//
//    Shape shape;
//    public boolean contains(int x, int y) {
//        if (shape == null ||
//                !shape.getBounds().equals(getBounds())) {
//            shape = new Ellipse2D.Float(getSize().width / 2 - getSize().height / 2, 0, getHeight(), getHeight());
//        }
//        return shape.contains(x, y);
//    }
//}
//    //Кнопка удалить
//    public class DeleteButton extends JButton {
//    public DeleteButton() {
//        Dimension size = getPreferredSize();
//        size.width = size.height = Math.max(size.width, size.height);
//        setPreferredSize(size);
//        setContentAreaFilled(false);
//    }
//    protected void paintComponent(Graphics g) {
//        if (getModel().isArmed()) {
//            g.setColor(Color.lightGray);
//        } else {
//            g.setColor(getBackground());
//        }
//        g.fillOval(getSize().width / 2 - getSize().height / 2, 0, getSize().height - 1, getSize().height - 1);
//
//        super.paintComponent(g);
//    }
//    protected void paintBorder(Graphics g) {
//        g.setColor(getForeground());
//        //g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
//    }
//
//    Shape shape;
//    public boolean contains(int x, int y) {
//        if (shape == null ||
//                !shape.getBounds().equals(getBounds())) {
//            shape = new Ellipse2D.Float(getSize().width / 2 - getSize().height / 2, 0, getHeight(), getHeight());
//        }
//        return shape.contains(x, y);
//    }
//}
//    
//    public class NavigatorButton extends JButton {
//    public NavigatorButton() {
//        Dimension size = getPreferredSize();
//        size.width = size.height = Math.max(size.width, size.height);
//        setPreferredSize(size);
//        setContentAreaFilled(false);
//    }
//    protected void paintComponent(Graphics g) {
//        if (getModel().isArmed()) {
//            g.setColor(Color.lightGray);
//        } else {
//            g.setColor(getBackground());
//        }
//        g.fillOval(getSize().width / 2 - getSize().height / 2, 0, getSize().height - 1, getSize().height - 1);
//
//        super.paintComponent(g);
//    }
//    protected void paintBorder(Graphics g) {
//        g.setColor(getForeground());
//        //g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
//    }
//
//    Shape shape;
//    public boolean contains(int x, int y) {
//        if (shape == null ||
//                !shape.getBounds().equals(getBounds())) {
//            shape = new Ellipse2D.Float(getSize().width / 2 - getSize().height / 2, 0, getHeight(), getHeight());
//        }
//        return shape.contains(x, y);
//    }
//}
//    
//    public class AddButton extends JButton {
//    public AddButton() {
//        Dimension size = getPreferredSize();
//        size.width = size.height = Math.max(size.width, size.height);
//        setPreferredSize(size);
//        setContentAreaFilled(false);
//    }
//    protected void paintComponent(Graphics g) {
//        if (getModel().isArmed()) {
//            g.setColor(Color.lightGray);
//            //g.drawImage(new ImageIcon("src\\Resources\\Special resources\\Directory.png").getImage(), getSize().width / 2 - getSize().height / 2, 0, null);
//        } else {
//            g.setColor(getBackground());
//        }
//        
//        //g.fillOval(getSize().width / 2 - getSize().height / 2, 0, getSize().height - 1, getSize().height - 1);
//
//        super.paintComponent(g);
//    }
//    protected void paintBorder(Graphics g) {
//        g.setColor(getForeground());
//        //g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
//    }
//
//    Shape shape;
//    public boolean contains(int x, int y) {
//        if (shape == null ||
//                !shape.getBounds().equals(getBounds())) {
//            shape = new Ellipse2D.Float(getSize().width / 2 - getSize().height / 2, 0, getHeight(), getHeight());
//        }
//        return shape.contains(x, y);
//    }
//}
//    
//    public class RenameButton extends JButton {
//    public RenameButton() {
//        Dimension size = getPreferredSize();
//        size.width = size.height = Math.max(size.width, size.height);
//        setPreferredSize(size);
//        setContentAreaFilled(false);
//    }
//    protected void paintComponent(Graphics g) {
//        if (getModel().isArmed()) {
//            g.setColor(Color.lightGray);
//            //g.drawImage(new ImageIcon("src\\Resources\\Special resources\\Directory.png").getImage(), getSize().width / 2 - getSize().height / 2, 0, null);
//        } else {
//            g.setColor(getBackground());
//        }
//        
//        //g.fillOval(getSize().width / 2 - getSize().height / 2, 0, getSize().height - 1, getSize().height - 1);
//
//        super.paintComponent(g);
//    }
//    protected void paintBorder(Graphics g) {
//        g.setColor(getForeground());
//        //g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
//    }
//
//    Shape shape;
//    public boolean contains(int x, int y) {
//        if (shape == null ||
//                !shape.getBounds().equals(getBounds())) {
//            shape = new Ellipse2D.Float(getSize().width / 2 - getSize().height / 2, 0, getHeight(), getHeight());
//        }
//        return shape.contains(x, y);
//    }
//}
//}
