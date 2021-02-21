package filemanager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import javax.swing.JTextField;

public class SearchTextField extends JTextField {
    
    /**
     *
     */
    private static final long serialVersionUID = 5605717522717788767L;

    UI ui;
    
    public SearchTextField(UI ui, String text) {
        
        this.ui = ui;
        
        setText(text);
        
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);
        
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                
                char symvol = e.getKeyChar();
                
                if(symvol == KeyEvent.VK_ENTER) {
                    
                    String wordFromText = getText();
                    
                    makeSearch(wordFromText);
                    
                }
            }
            
        });
        
        addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                
                if(getText().equals("Поиск")) {
                    
                    setText("");
                
                }
                
                setForeground(Color.BLACK);
                
                revalidate();
                repaint();
                
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                
                if(getText().equals("Поиск")) {
                    
                    setText("");
                
                }
                
                setForeground(Color.BLACK);
                
                revalidate();
                repaint();
                
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                
                if(getText().equals("Поиск")) {
                    
                    setText("");
                
                }
                
                setForeground(Color.BLACK);
                
                revalidate();
                repaint();
                
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                
            }
            
        });
        
    }
    
    public void makeSearch(String wordFromText) {
        
        ArrayList<ArrayList<String>> pathListNormal = new ArrayList<>();
        
        pathListNormal.add(new ArrayList<String>());
        pathListNormal.add(new ArrayList<String>());
        
        ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).add(wordFromText);
        ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).add("StartSearch");
                 
        ArrayList <String> realCash = new ArrayList<>();
                    
        if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() > 1){
                        
            if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 2).equals("StartCash") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 2).equals("Cash")) {
                            
                int who = ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).lastIndexOf("StartCash");
                realCash.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(who));
                            
                    for(int i = who + 1; i < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size(); i++) {
                                
                        if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(i).equals("StartSearch")) {
                                    
                            break;
                                    
                        }
                                
                        if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(i).equals("Cash")) {
                                    
                            realCash.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(i));
                                    
                        }
                                
                    }
                            
            } else if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 2).equals("StartPath") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 2).equals("PathCash")) {
                            
                int who = ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).lastIndexOf("StartPath");
                realCash.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(who));
                            
                    for(int i = who + 1; i < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size(); i++) {
                                
                        if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(i).equals("StartSearch")){
                                    
                            break;
                                    
                        }
                                
                        if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(i).equals("PathCash")) {
                                    
                            realCash.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(i));
                                    
                        }
                                
                    }
                            
            } else if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 2).equals("StartSearch") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(1).size() - 2).equals("SearchCash")) {
                            
                            int who = ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).lastIndexOf("StartSearch");
                            
                            ArrayList<Integer> intList = new ArrayList<>();
                            
                            for(int i = 0; i < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size(); i++) {
                                
                                if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(i).equals("StartSearch")) {
                                    
                                    intList.add(i);
                                    
                                }
                                
                            }
                            
                            if(intList.size() > 1) {
                                
                                who = intList.get(intList.size() - 2);
                                
                            }
                            
                            //realCash.add(dirsCash.get(0).get(who));
                            
                            for(int i = who + 1; i < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size(); i++) {
                                
                                if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(i).equals("StartSearch")) {
                                    
                                    break;
                                    
                                }
                                
                                if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(i).equals("SearchCash")) {
                                    
                                    realCash.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(i));
                                    
                                }
                                
                            }
                            
                            if(realCash.size() < 1) {
                                
                                if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() > 1) {

                                        int who2 = ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).lastIndexOf("StartCash");
                                        realCash.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(who2));

                                        for(int i = who2 + 1; i < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size(); i++) {

                                            if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(i).equals("StartSearch")) {

                                                break;

                                            }

                                            if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(i).equals("Cash")) {

                                                realCash.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(i));

                                            }
                                        }
                                    
                                } else {
                                    
                                    realCash.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 2));
                                    
                                }
                            }
                                
                            
                        } else {
                            
                            realCash.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 2));
                            
                        }
                        
                        String tempFullPath = ui.getPathMaster().pathBuilder(realCash);
                        
                        String[] acceptSimvols = {"{", "}", "|", "[", "]", "^", ":", ";", "?", ".", "-", ",", "+", "*", "(", ")", "&", "%", "$", "#", "!", "@", "№", " "};
                        ArrayList<String> wordsListFromText = new ArrayList<>();
                        
                        for(int i = 0; i < acceptSimvols.length; i++) {
                            
                            if(wordFromText.contains(acceptSimvols[i])) {
                                
                                wordFromText = wordFromText.replace(acceptSimvols[i], "/");
                                
                            } else {
                                
                                continue;
                                
                            }
                            
                        }
                        
                        String wordsFromText[] = wordFromText.split("/");
                        
                        for(String str: wordsFromText) {
                            System.out.println("wxc: " + str);
                        }
                        
                        pathListNormal.get(0).addAll(getPathFromName(new File(tempFullPath), wordsFromText));
                        
                        for(int i = 0; i < pathListNormal.get(0).size(); i++) {

                            pathListNormal.get(1).add("ForSearch");

                        }
                        
                    } else {
                        
                        String[] acceptSimvols = {"{", "}", "|", "[", "]", "^", ":", ";", "?", ".", "-", ",", "+", "*", "(", ")", "&", "%", "$", "#", "!", "@", "№", " "};
                        ArrayList<String> wordsListFromText = new ArrayList<>();
                        
                        for(int i = 0; i < acceptSimvols.length; i++) {
                            
                            if(wordFromText.contains(acceptSimvols[i])) {
                                
                                wordFromText = wordFromText.replaceAll(acceptSimvols[i], " ");
                                
                            } else {
                                
                                continue;
                                
                            }
                            
                        }
                        
                        String wordsFromText[] = wordFromText.split(" ");
                        
                        for(String str: wordsFromText) {
                            System.out.println("rtx: " + str);
                        }
                        
                        for(int i = 0; i < ui.getPathMaster().getDiscs().length; i++) {
                            
                            pathListNormal.get(0).addAll(getPathFromName(ui.getPathMaster().getDiscs()[i], wordsFromText));
                            
                        }
                        
                        for(int i = 0; i < pathListNormal.get(0).size(); i++) {

                            pathListNormal.get(1).add("ForSearch");

                        }
                        
                    }
                    
                    System.out.println("All");
                    System.out.println(pathListNormal.get(0));
                    ui.getMainFilesPanel().iniclizButton(pathListNormal);

    }
    
    public ArrayList <String> getPathFromName(File dir, String[] fileNames) {
        
        ArrayList<String> pathList = new ArrayList<>();
        
        try {
            
            Files.walkFileTree(dir.toPath(), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    
                    //System.out.println(fileName.equals(file.toFile().getName()));
                    for(String fileName: fileNames) {
                        
                        if(file.toFile().getName().contains(fileName)){
                            
                            pathList.add(file.toString());
                            
                        }
                        
                    }
                    //fileCount.addAndGet(1);
                    return FileVisitResult.CONTINUE;
                }
                
                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) {
                    
                    System.out.println("skipped: " + file + " (" + exc + ")");
                    // Skip folders that can't be traversed
                    
                    for(String fileName: fileNames) {
                        
                        if(file.toFile().getName().contains(fileName)){
                            
                            pathList.add(file.toString());
                            
                        }
                        
                    }
                    
                    return FileVisitResult.CONTINUE;
                }
                
                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
                    
                    if (exc != null)
                        System.out.println("had trouble traversing: " + dir + " (" + exc + ")");
                    // Ignore errors traversing a folder
                    for(String fileName: fileNames) {
                        
                        if(dir.toFile().getName().contains(fileName)){
                            
                            pathList.add(dir.toString());
                            
                        }
                        
                    }
                    
                    return FileVisitResult.CONTINUE;
                }
            });
            
        } catch (IOException e) {
            throw new AssertionError("walkFileTree will not throw IOException if the FileVisitor does not");
        }
        
        return pathList;
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        
        Graphics2D g2d = (Graphics2D)g;
            
        super.paintComponent(g2d);
        
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);
            
        Toolkit.getDefaultToolkit().sync();
        
        g2d.setColor(new Color(180, 180, 180));
        
        g2d.drawRoundRect(2, 5, (int)((double)getSize().width - 4), (int)((double)getSize().height - 10), 25, 25);
        
    }
    
    @Override
    protected void paintBorder(Graphics g) {
        
        Graphics2D g2d = (Graphics2D)g;
            
        super.paintComponent(g2d);
        
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);
        
        Toolkit.getDefaultToolkit().sync();
        
        g2d.drawRoundRect(2, 5, (int)((double)getSize().width - 4), (int)((double)getSize().height - 10), 25, 25);
        
    }
    
    Shape shape;
    public boolean contains(int x, int y) {
        if (shape == null ||
                !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(2, 5, (int)((double)getSize().width - 4), (int)((double)getSize().height - 10), 25, 25);
        }
        return shape.contains(x, y);
    }
    
}
