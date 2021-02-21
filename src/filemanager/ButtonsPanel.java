package filemanager;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ButtonsPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 3768463798130583664L;
    
    private JButton upButton =  new ButtonManager("Переместить вверх");
    private JButton deleteButton = new ButtonManager("Удалить");
    private JButton addButton = new ButtonManager("Добавить папку");
    private JButton renameButton = new ButtonManager("Переименовать");
    private JButton copyInButton = new ButtonManager("Копировать");
    private JButton newPlaceInButton = new ButtonManager("Переместить");
    private JButton addMarkedFilesButton = new ButtonManager("Добавить в важные");
    private JButton removeMarkedFilesButton = new ButtonManager("Убрать из важных");
    private JButton makeShortcutButton = new ButtonManager("Создать ярлык");
    
    public ButtonsPanel(UI ui) {
        
        setLayout(new GridLayout(8, 1, 0, 0));
        
        setBackground(new Color(52, 112, 255));
        
        setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        
        upButton.setHorizontalAlignment(SwingConstants.LEFT);
        deleteButton.setHorizontalAlignment(SwingConstants.LEFT);
        addButton.setHorizontalAlignment(SwingConstants.LEFT);
        renameButton.setHorizontalAlignment(SwingConstants.LEFT);
        makeShortcutButton.setHorizontalAlignment(SwingConstants.LEFT);
        copyInButton.setHorizontalAlignment(SwingConstants.LEFT);
        newPlaceInButton.setHorizontalAlignment(SwingConstants.LEFT);
        addMarkedFilesButton.setHorizontalAlignment(SwingConstants.LEFT);
        removeMarkedFilesButton.setHorizontalAlignment(SwingConstants.LEFT);
        
        upButton.setBorderPainted(false);
        deleteButton.setBorderPainted(false);
        addMarkedFilesButton.setBorderPainted(false);
        removeMarkedFilesButton.setBorderPainted(false);
        addButton.setBorderPainted(false);
        renameButton.setBorderPainted(false);
        makeShortcutButton.setBorderPainted(false);
        copyInButton.setBorderPainted(false);
        newPlaceInButton.setBorderPainted(false);
        
        upButton.setFocusable(false);
        deleteButton.setFocusable(false);
        addMarkedFilesButton.setFocusable(false);
        removeMarkedFilesButton.setFocusable(false);
        addButton.setFocusable(false);
        renameButton.setFocusable(false);
        makeShortcutButton.setFocusable(false);
        copyInButton.setFocusable(false);
        newPlaceInButton.setFocusable(false);
        
        int dimension = (int)(33.6);
        
        Image upImage = new ImageIcon(new File("src\\Resources\\Button resources\\UpButton.png").getAbsolutePath()).getImage().getScaledInstance(dimension, dimension, Image.SCALE_SMOOTH);
        Image deleteImage = new ImageIcon(new File("src\\Resources\\Button resources\\DeleteButton.png").getAbsolutePath()).getImage().getScaledInstance(dimension, dimension, Image.SCALE_SMOOTH);
        Image navigatorOffImage = new ImageIcon(new File("src\\Resources\\Button resources\\NavigatorButtonOff.png").getAbsolutePath()).getImage().getScaledInstance(dimension, dimension, Image.SCALE_SMOOTH);
        Image createDirectiryImage = new ImageIcon(new File("src\\Resources\\Button resources\\CreateDirectoryButton.png").getAbsolutePath()).getImage().getScaledInstance(dimension, dimension, Image.SCALE_SMOOTH);
        Image renameImage = new ImageIcon(new File("src\\Resources\\Button resources\\RenameButton.png").getAbsolutePath()).getImage().getScaledInstance(dimension, dimension, Image.SCALE_SMOOTH);
        Image copyInImage = new ImageIcon(new File("src\\Resources\\Button resources\\CopyInButton.png").getAbsolutePath()).getImage().getScaledInstance(dimension, dimension, Image.SCALE_SMOOTH);
        Image moveImage = new ImageIcon(new File("src\\Resources\\Button resources\\MoveButton.png").getAbsolutePath()).getImage().getScaledInstance(dimension, dimension, Image.SCALE_SMOOTH);
        Image addMarkedFilesImage = new ImageIcon(new File("src\\Resources\\Button resources\\MoveButton.png").getAbsolutePath()).getImage().getScaledInstance(dimension, dimension, Image.SCALE_SMOOTH);
        Image removeMarkedFilesImage = new ImageIcon(new File("src\\Resources\\Button resources\\MoveButton.png").getAbsolutePath()).getImage().getScaledInstance(dimension, dimension, Image.SCALE_SMOOTH);
        Image makeShortcutImage = new ImageIcon(new File("src\\Resources\\Button resources\\RenameButton.png").getAbsolutePath()).getImage().getScaledInstance(dimension, dimension, Image.SCALE_SMOOTH);
        
        upButton.setIcon(new ImageIcon(upImage));
        deleteButton.setIcon(new ImageIcon(deleteImage));
        addButton.setIcon(new ImageIcon(createDirectiryImage));
        renameButton.setIcon(new ImageIcon(renameImage));
        copyInButton.setIcon(new ImageIcon(copyInImage));
        newPlaceInButton.setIcon(new ImageIcon(moveImage));
        addMarkedFilesButton.setIcon(new ImageIcon(addMarkedFilesImage));
        removeMarkedFilesButton.setIcon(new ImageIcon(removeMarkedFilesImage));
        makeShortcutButton.setIcon(new ImageIcon(makeShortcutImage));
        
        upButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() > 1){
                    if(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).size() > 0) {
                        if(!ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).isEmpty() && ui.getMainFilesPanel().getPrivateButton() != null) {
                            String currentPath = ui.getPathMaster().pathBuilder(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0));
                            String selectedObject = ui.getMainFilesPanel().getPrivateButton().getName().toString();
                    
                            File renameFile = new File(currentPath, selectedObject);
                            ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).remove(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() - 1);
                            String currentPath2 = ui.getPathMaster().pathBuilder(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0));
                            renameFile.renameTo(new File(currentPath2, selectedObject));
                        
                            File updateDir = new File(currentPath2);
                            String updateMas[] = updateDir.list();
                            ArrayList<String> updateModel = new ArrayList<>();
                            for (String str : updateMas){
                                File check = new File(updateDir.getPath(), str);
                                if(!check.isHidden()){
                                    if(check.isDirectory()){
                                        updateModel.add(str);
                                    } else {
                                        updateModel.add(str);
                                    }
                                }
                            }
                            
                            ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).removeAll(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0));
                            ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1).removeAll(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1));
                            
                            ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).addAll(updateModel);

                            for(int i = 0; i < ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).size(); i++) {

                                ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1).add("ForPath");

                            }
                            ui.getMainFilesPanel().getFileSheetItem().set(ui.getPathMaster().getLastTab(), 0);
                            if(ui.getMainFilesPanel().getPrivateButton() != null){
                                ui.getMainFilesPanel().getPrivateButton().remove(ui.getMainFilesPanel().getPrivateButton());
                            }
                            ArrayList<ArrayList<String>> almostEmptyList = new ArrayList<>();
                            almostEmptyList.add(new ArrayList<>(Arrays.asList(new String[]{"\\/:*?\"<>|"})));
                            ui.getMainFilesPanel().iniclizButton(almostEmptyList);
                        }
                    }
                }
                
                System.out.println("1qaz");
                
                if(ui.getSearchTextField().getText().equals("") || ui.getSearchTextField().getText() == null) {
                    
                    ui.getSearchTextField().setText("Поиск");
                    ui.getSearchTextField().setForeground(new Color(37, 83, 185));
                    
                }
                
                if(ui.getMainFilesPanel().getPrivateButton() != null) {
                    ui.getMainFilesPanel().getPrivateButton().remove(ui.getMainFilesPanel().getPrivateButton());
                }
                
                for(int i = 0; i < (ui.getMainFilesPanel().getTopFiles().length - 1); i++){
                    
                    if(ui.getMainFilesPanel().getTopFiles()[i] != null) {
                        
                        ui.getMainFilesPanel().getTopFiles()[i].setBorderPainted(false);
                        
                    }
                    
                }
                
                int cpWidth = ui.getContentPane().getWidth();
                int cpHeight = ui.getContentPane().getHeight();
                
                ui.getBasicPanel().removeAll();
                
                ui.getNavigationButtonsPanel().setBounds(0, 0, 336, 45);
                ui.getTabbedPanel().setBounds(336, 0, (cpWidth - 336 - 168), 45);
                ui.getSearchTextField().setBounds((336 + (cpWidth - 336 - 168)), 0, 168, 45);
                ui.getSort().setBounds(10, 50, 316, 45);
                ui.getMainFilesPanel().setBounds(0, 45, cpWidth, (cpHeight - 45));
                setBounds((int)(cpWidth - cpWidth / 5 * 1), 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 1 - (int)(cpWidth - cpWidth / 5 * 1))), (int)(cpHeight));
                ui.getMorePanel().setBounds(0, 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 5 - (int)(cpWidth - cpWidth / 5 * 5))), (int)(cpHeight));
                
                ui.getBasicPanel().add(ui.getNavigationButtonsPanel());
                ui.getBasicPanel().add(ui.getTabbedPanel());
                ui.getBasicPanel().add(ui.getSearchTextField());
                ui.getBasicPanel().add(ui.getSort());
                ui.getBasicPanel().add(ui.getMainFilesPanel());
                
                ui.getBasicPanel().moveToFront(ui.getSort());
                
                revalidate();
                repaint();
                
            }
        });
        
        addButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).isEmpty()){
                    String currentPath;
                    File newFolder;
                    CreateNewFolderJDialog newFolderJDialog = new CreateNewFolderJDialog(ui);
                    
                    if(newFolderJDialog.getReady()){
                        currentPath = ui.getPathMaster().pathBuilder(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0));
                        newFolder = new File(currentPath, newFolderJDialog.getNewName());
                        if(!newFolder.exists())
                        newFolder.mkdir();
                        
                        File updateDir = new File(currentPath);
                        String updateMas[] = updateDir.list();
                        ArrayList<String> updateModel = new ArrayList<>();
                        for (String str : updateMas){
                            File check = new File(updateDir.getPath(), str);
                            if(!check.isHidden()){
                                if(check.isDirectory()){
                                    updateModel.add(str);
                                } else {
                                    updateModel.add(/*"файл-" + */str);
                                }
                            }
                        }
                        
                        ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).removeAll(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0));
                        ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1).removeAll(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1));
                        
                        ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).addAll(updateModel);

                        for(int i = 0; i < ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).size(); i++) {

                            ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1).add("ForPath");

                        }
                        
                        ArrayList<ArrayList<String>> almostEmptyList = new ArrayList<>();
                            almostEmptyList.add(new ArrayList<>(Arrays.asList(new String[]{"\\/:*?\"<>|"})));
                            ui.getMainFilesPanel().iniclizButton(almostEmptyList);
                        
                    }
                }
                
                System.out.println("1qaz");
                
                if(ui.getSearchTextField().getText().equals("") || ui.getSearchTextField().getText() == null) {
                    
                    ui.getSearchTextField().setText("Поиск");
                    ui.getSearchTextField().setForeground(new Color(37, 83, 185));
                    
                }
                
                if(ui.getMainFilesPanel().getPrivateButton() != null) {
                    ui.getMainFilesPanel().getPrivateButton().remove(ui.getMainFilesPanel().getPrivateButton());
                }
                
                for(int i = 0; i < (ui.getMainFilesPanel().getTopFiles().length - 1); i++){
                    
                    if(ui.getMainFilesPanel().getTopFiles()[i] != null) {
                        
                        ui.getMainFilesPanel().getTopFiles()[i].setBorderPainted(false);
                        
                    }
                    
                }
                
                int cpWidth = ui.getContentPane().getWidth();
                int cpHeight = ui.getContentPane().getHeight();
                
                ui.getBasicPanel().removeAll();
                
                ui.getNavigationButtonsPanel().setBounds(0, 0, 336, 45);
                ui.getTabbedPanel().setBounds(336, 0, (cpWidth - 336 - 168), 45);
                ui.getSearchTextField().setBounds((336 + (cpWidth - 336 - 168)), 0, 168, 45);
                ui.getSort().setBounds(10, 50, 316, 45);
                ui.getMainFilesPanel().setBounds(0, 45, cpWidth, (cpHeight - 45));
                setBounds((int)(cpWidth - cpWidth / 5 * 1), 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 1 - (int)(cpWidth - cpWidth / 5 * 1))), (int)(cpHeight));
                ui.getMorePanel().setBounds(0, 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 5 - (int)(cpWidth - cpWidth / 5 * 5))), (int)(cpHeight));
                
                ui.getBasicPanel().add(ui.getNavigationButtonsPanel());
                ui.getBasicPanel().add(ui.getTabbedPanel());
                ui.getBasicPanel().add(ui.getSearchTextField());
                ui.getBasicPanel().add(ui.getSort());
                ui.getBasicPanel().add(ui.getMainFilesPanel());
                
                ui.getBasicPanel().moveToFront(ui.getSort());
                
                revalidate();
                repaint();
                
            }
        });
        
        deleteButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).isEmpty() && ui.getMainFilesPanel().getPrivateButton() != null){
                    String selectedObject = ui.getMainFilesPanel().getPrivateButton().getName().toString();
                    String currentPath = ui.getPathMaster().pathBuilder(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0));
                    if(!selectedObject.isEmpty()){
                        
                        DeleteJDialog DJD = new DeleteJDialog(ui);

                        if(DJD.getKodeUdaleniya() == 1){
                        File file = new File(currentPath, selectedObject);
                        File[] objects = file.listFiles();
                        if(objects != null){
                          File fileForDir4 = new File("C:\\$Recycle.Bin");
                          File fileForDir3 = new File(fileForDir4 + "\\" + selectedObject);
                          file.renameTo(fileForDir3);
                        }

                        }   

                        if(DJD.getKodeUdaleniya() == 2){

                        File file = new File(currentPath, selectedObject);
                        File[] objects = file.listFiles();
                        if(objects != null){
                          File fileForDir3 = new File("C:\\RecycleFor" + "\\" + selectedObject);
                          file.renameTo(fileForDir3);
                        }

                        }
                        if(DJD.getKodeUdaleniya() == 3){

                        ui.getPathMaster().deleteDir(new File(currentPath, selectedObject));

                        }   
                        File updateDir = new File(currentPath);
                        String updateMas[] = updateDir.list();
                        ArrayList<String> updateModel = new ArrayList<>();

                        for (String str : updateMas){
                            File check = new File(updateDir.getPath(), str);
                            if(!check.isHidden()){
                                if(check.isDirectory()){
                                    updateModel.add(str);
                                } else {
                                    updateModel.add(/*"файл-" + */str);
                                }
                            }
                        }
                        
                        ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).removeAll(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0));
                        ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1).removeAll(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1));
                        
                        ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).addAll(updateModel);

                        for(int i = 0; i < ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).size(); i++) {

                            ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1).add("ForPath");

                        }
                        if(ui.getMainFilesPanel().getPrivateButton() != null){
                          ui.getMainFilesPanel().getPrivateButton().remove(ui.getMainFilesPanel().getPrivateButton());
                        }
                        ArrayList<ArrayList<String>> almostEmptyList = new ArrayList<>();
                            almostEmptyList.add(new ArrayList<>(Arrays.asList(new String[]{"\\/:*?\"<>|"})));
                            ui.getMainFilesPanel().iniclizButton(almostEmptyList);
                    }
                }
                
                System.out.println("1qaz");
                
                if(ui.getSearchTextField().getText().equals("") || ui.getSearchTextField().getText() == null) {
                    
                    ui.getSearchTextField().setText("Поиск");
                    ui.getSearchTextField().setForeground(new Color(37, 83, 185));
                    
                }
                
                if(ui.getMainFilesPanel().getPrivateButton() != null) {
                    ui.getMainFilesPanel().getPrivateButton().remove(ui.getMainFilesPanel().getPrivateButton());
                }
                
                for(int i = 0; i < (ui.getMainFilesPanel().getTopFiles().length - 1); i++){
                    
                    if(ui.getMainFilesPanel().getTopFiles()[i] != null) {
                        
                        ui.getMainFilesPanel().getTopFiles()[i].setBorderPainted(false);
                        
                    }
                    
                }
                
                int cpWidth = ui.getContentPane().getWidth();
                int cpHeight = ui.getContentPane().getHeight();
                
                ui.getBasicPanel().removeAll();
                
                ui.getNavigationButtonsPanel().setBounds(0, 0, 336, 45);
                ui.getTabbedPanel().setBounds(336, 0, (cpWidth - 336 - 168), 45);
                ui.getSearchTextField().setBounds((336 + (cpWidth - 336 - 168)), 0, 168, 45);
                ui.getSort().setBounds(10, 50, 316, 45);
                ui.getMainFilesPanel().setBounds(0, 45, cpWidth, (cpHeight - 45));
                setBounds((int)(cpWidth - cpWidth / 5 * 1), 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 1 - (int)(cpWidth - cpWidth / 5 * 1))), (int)(cpHeight));
                ui.getMorePanel().setBounds(0, 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 5 - (int)(cpWidth - cpWidth / 5 * 5))), (int)(cpHeight));
                
                ui.getBasicPanel().add(ui.getNavigationButtonsPanel());
                ui.getBasicPanel().add(ui.getTabbedPanel());
                ui.getBasicPanel().add(ui.getSearchTextField());
                ui.getBasicPanel().add(ui.getSort());
                ui.getBasicPanel().add(ui.getMainFilesPanel());
                
                ui.getBasicPanel().moveToFront(ui.getSort());
                
                revalidate();
                repaint();
                
            }
        });
        
        renameButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).isEmpty() &&  ui.getMainFilesPanel().getPrivateButton() != null){
                    String currentPath = ui.getPathMaster().pathBuilder(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0));
                    String selectedObject =  ui.getMainFilesPanel().getPrivateButton().getName().toString();
                    RenameJDialog renamer = new RenameJDialog(ui);
                    
                    if(renamer.getReady()){
                        File renameFile = new File(currentPath, selectedObject);
                        renameFile.renameTo(new File(currentPath, renamer.getNewName()));
                        
                    File updateDir = new File(currentPath);
                    String updateMas[] = updateDir.list();
                    ArrayList<String> updateModel = new ArrayList<>();
                    for (String str : updateMas){
                        File check = new File(updateDir.getPath(), str);
                        if(!check.isHidden()){
                            if(check.isDirectory()){
                                updateModel.add(str);
                            } else {
                                updateModel.add(str);
                            }
                        }
                    }
                    
                    ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).removeAll(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0));
                    ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1).removeAll(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1));
                    
                    ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).addAll(updateModel);

                    for(int i = 0; i < ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).size(); i++) {

                        ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1).add("ForPath");

                    }
                    if(ui.getMainFilesPanel().getPrivateButton() != null){
                      ui.getMainFilesPanel().getPrivateButton().remove(ui.getMainFilesPanel().getPrivateButton());
                    }
                    ArrayList<ArrayList<String>> almostEmptyList = new ArrayList<>();
                            almostEmptyList.add(new ArrayList<>(Arrays.asList(new String[]{"\\/:*?\"<>|"})));
                            ui.getMainFilesPanel().iniclizButton(almostEmptyList);
                  }
                }
                
                System.out.println("1qaz");
                
                if(ui.getSearchTextField().getText().equals("") || ui.getSearchTextField().getText() == null) {
                    
                    ui.getSearchTextField().setText("Поиск");
                    ui.getSearchTextField().setForeground(new Color(37, 83, 185));
                    
                }
                
                if(ui.getMainFilesPanel().getPrivateButton() != null) {
                    ui.getMainFilesPanel().getPrivateButton().remove(ui.getMainFilesPanel().getPrivateButton());
                }
                
                for(int i = 0; i < (ui.getMainFilesPanel().getTopFiles().length - 1); i++){
                    
                    if(ui.getMainFilesPanel().getTopFiles()[i] != null) {
                        
                        ui.getMainFilesPanel().getTopFiles()[i].setBorderPainted(false);
                        
                    }
                    
                }
                
                int cpWidth = ui.getContentPane().getWidth();
                int cpHeight = ui.getContentPane().getHeight();
                
                ui.getBasicPanel().removeAll();
                
                ui.getNavigationButtonsPanel().setBounds(0, 0, 336, 45);
                ui.getTabbedPanel().setBounds(336, 0, (cpWidth - 336 - 168), 45);
                ui.getSearchTextField().setBounds((336 + (cpWidth - 336 - 168)), 0, 168, 45);
                ui.getSort().setBounds(10, 50, 316, 45);
                ui.getMainFilesPanel().setBounds(0, 45, cpWidth, (cpHeight - 45));
                setBounds((int)(cpWidth - cpWidth / 5 * 1), 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 1 - (int)(cpWidth - cpWidth / 5 * 1))), (int)(cpHeight));
                ui.getMorePanel().setBounds(0, 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 5 - (int)(cpWidth - cpWidth / 5 * 5))), (int)(cpHeight));
                
                ui.getBasicPanel().add(ui.getNavigationButtonsPanel());
                ui.getBasicPanel().add(ui.getTabbedPanel());
                ui.getBasicPanel().add(ui.getSearchTextField());
                ui.getBasicPanel().add(ui.getSort());
                ui.getBasicPanel().add(ui.getMainFilesPanel());
                
                ui.getBasicPanel().moveToFront(ui.getSort());
                
                revalidate();
                repaint();
                
            }
        });
        
        copyInButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() > 0){
                    if(!ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).isEmpty() && ui.getMainFilesPanel().getPrivateButton() != null){
                    String selectedObject = ui.getMainFilesPanel().getPrivateButton().getName().toString();
                    String currentPath = ui.getPathMaster().pathBuilder(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0));
                    if(!selectedObject.isEmpty()){
                        
                    CopyJDialog renamer = new CopyJDialog(ui);
                    
                    if(renamer.getReady()){
                    File selectedFile;
                    selectedFile = new File(currentPath + selectedObject);
                    
//                    if(selectedFile.isDirectory()){
                    ui.getPathMaster().setSrc(new File (currentPath + selectedObject));
                    ui.getPathMaster().setSrc2(new File (renamer.getNewName() + "\\" + selectedObject));
                    
                    ui.getPathMaster().addCDThread();
                    
                    ui.getPathMaster().startCD();
                    
                    ui.getPathMaster().setCheck321(true);
                    ui.getPathMaster().setByteCopy(0);
                    
                    if(ui.getMainFilesPanel().getPrivateButton() != null){
                        
                        ui.getMainFilesPanel().getPrivateButton().remove(ui.getMainFilesPanel().getPrivateButton());
                        
                    }
                    ui.getMainFilesPanel().getFileSheetItem().set(ui.getPathMaster().getLastTab(), 0);
            ArrayList<ArrayList<String>> almostEmptyList = new ArrayList<>();
                            almostEmptyList.add(new ArrayList<>(Arrays.asList(new String[]{"\\/:*?\"<>|"})));
                            ui.getMainFilesPanel().iniclizButton(almostEmptyList);
            }
            }
            }
          }
                
                System.out.println("1qaz");
                
                if(ui.getSearchTextField().getText().equals("") || ui.getSearchTextField().getText() == null) {
                    
                    ui.getSearchTextField().setText("Поиск");
                    ui.getSearchTextField().setForeground(new Color(37, 83, 185));
                    
                }
                
                if(ui.getMainFilesPanel().getPrivateButton() != null) {
                    ui.getMainFilesPanel().getPrivateButton().remove(ui.getMainFilesPanel().getPrivateButton());
                }
                
                for(int i = 0; i < (ui.getMainFilesPanel().getTopFiles().length - 1); i++){
                    
                    if(ui.getMainFilesPanel().getTopFiles()[i] != null) {
                        
                        ui.getMainFilesPanel().getTopFiles()[i].setBorderPainted(false);
                        
                    }
                    
                }
                
                int cpWidth = ui.getContentPane().getWidth();
                int cpHeight = ui.getContentPane().getHeight();
                
                ui.getBasicPanel().removeAll();
                
                ui.getNavigationButtonsPanel().setBounds(0, 0, 336, 45);
                ui.getTabbedPanel().setBounds(336, 0, (cpWidth - 336 - 168), 45);
                ui.getSearchTextField().setBounds((336 + (cpWidth - 336 - 168)), 0, 168, 45);
                ui.getSort().setBounds(10, 50, 316, 45);
                ui.getMainFilesPanel().setBounds(0, 45, cpWidth, (cpHeight - 45));
                setBounds((int)(cpWidth - cpWidth / 5 * 1), 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 1 - (int)(cpWidth - cpWidth / 5 * 1))), (int)(cpHeight));
                ui.getMorePanel().setBounds(0, 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 5 - (int)(cpWidth - cpWidth / 5 * 5))), (int)(cpHeight));
                
                ui.getBasicPanel().add(ui.getNavigationButtonsPanel());
                ui.getBasicPanel().add(ui.getTabbedPanel());
                ui.getBasicPanel().add(ui.getSearchTextField());
                ui.getBasicPanel().add(ui.getSort());
                ui.getBasicPanel().add(ui.getMainFilesPanel());
                
                ui.getBasicPanel().moveToFront(ui.getSort());
                
                revalidate();
                repaint();
                
        }
        });
        
        newPlaceInButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() > 0){
                    if(!ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).isEmpty() && ui.getMainFilesPanel().getPrivateButton() != null){
                    String selectedObject = ui.getMainFilesPanel().getPrivateButton().getName().toString();
                    String currentPath = ui.getPathMaster().pathBuilder(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0));
                    if(!selectedObject.isEmpty()){
                            
                    NewPlaceJDialog renamer = new NewPlaceJDialog(ui);
                    
                    if(renamer.getReady()){
                    File selectedFile;
                    selectedFile = new File(currentPath + selectedObject);
                    
                    ui.getPathMaster().setSrc(new File (currentPath + selectedObject));
                    ui.getPathMaster().setSrc2(new File (renamer.getNewName() + "\\" + selectedObject));
                    
                    boolean haveOrNot = Files.exists(ui.getPathMaster().getSrc2().toPath());
                    
                    ui.getPathMaster().addCDThread();
                    
                    ui.getPathMaster().startCD();
                            
                        ui.getPathMaster().setCheck321(true);
                        ui.getPathMaster().setByteCopy(0);
                        
                        if(!haveOrNot){

                            ui.getPathMaster().deleteDir(new File(currentPath, selectedObject));

                        }
                        File updateDir = new File(currentPath);
                    String updateMas[] = updateDir.list();
                    ArrayList<String> updateModel = new ArrayList<>();
                    for (String str : updateMas){
                        File check = new File(updateDir.getPath(), str);
                        if(!check.isHidden()){
                            if(check.isDirectory()){
                                updateModel.add(str);
                            } else {
                                updateModel.add(str);
                            }
                        }
                    }
                    ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).addAll(updateModel);
                        
                    for(int i = 0; i < ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).size(); i++) {
                            
                        ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1).add("ForPath");
                            
                    }
                    if(ui.getMainFilesPanel().getPrivateButton() != null){
                      ui.getMainFilesPanel().getPrivateButton().remove(ui.getMainFilesPanel().getPrivateButton());
                    }
                    ui.getMainFilesPanel().getFileSheetItem().set(ui.getPathMaster().getLastTab(), 0);
                    ArrayList<ArrayList<String>> almostEmptyList = new ArrayList<>();
                            almostEmptyList.add(new ArrayList<>(Arrays.asList(new String[]{"\\/:*?\"<>|"})));
                            ui.getMainFilesPanel().iniclizButton(almostEmptyList);
                    
                    }
                }
                }
            }
                
                System.out.println("1qaz");
                
                if(ui.getSearchTextField().getText().equals("") || ui.getSearchTextField().getText() == null) {
                    
                    ui.getSearchTextField().setText("Поиск");
                    ui.getSearchTextField().setForeground(new Color(37, 83, 185));
                    
                }
                
                if(ui.getMainFilesPanel().getPrivateButton() != null) {
                    ui.getMainFilesPanel().getPrivateButton().remove(ui.getMainFilesPanel().getPrivateButton());
                }
                
                for(int i = 0; i < (ui.getMainFilesPanel().getTopFiles().length - 1); i++){
                    
                    if(ui.getMainFilesPanel().getTopFiles()[i] != null) {
                        
                        ui.getMainFilesPanel().getTopFiles()[i].setBorderPainted(false);
                        
                    }
                    
                }
                
                int cpWidth = ui.getContentPane().getWidth();
                int cpHeight = ui.getContentPane().getHeight();
                
                ui.getBasicPanel().removeAll();
                
                ui.getNavigationButtonsPanel().setBounds(0, 0, 336, 45);
                ui.getTabbedPanel().setBounds(336, 0, (cpWidth - 336 - 168), 45);
                ui.getSearchTextField().setBounds((336 + (cpWidth - 336 - 168)), 0, 168, 45);
                ui.getSort().setBounds(10, 50, 316, 45);
                ui.getMainFilesPanel().setBounds(0, 45, cpWidth, (cpHeight - 45));
                setBounds((int)(cpWidth - cpWidth / 5 * 1), 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 1 - (int)(cpWidth - cpWidth / 5 * 1))), (int)(cpHeight));
                ui.getMorePanel().setBounds(0, 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 5 - (int)(cpWidth - cpWidth / 5 * 5))), (int)(cpHeight));
                
                ui.getBasicPanel().add(ui.getNavigationButtonsPanel());
                ui.getBasicPanel().add(ui.getTabbedPanel());
                ui.getBasicPanel().add(ui.getSearchTextField());
                ui.getBasicPanel().add(ui.getSort());
                ui.getBasicPanel().add(ui.getMainFilesPanel());
                
                ui.getBasicPanel().moveToFront(ui.getSort());
                
                revalidate();
                repaint();
                
            }
        });
        
        addMarkedFilesButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
                    if(ui.getMainFilesPanel().getPrivateButton() != null) {
                        
                        String selectedObject = ui.getMainFilesPanel().getPrivateButton().getName();
                        
                        ArrayList <String> realCashC = new ArrayList<>();
                        String fullPathC = "";

                        if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() > 0) {

                                        if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("StartCash") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("Cash")){
                                            for(int j = 0; j < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size(); j++){
                                                if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("StartCash") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("Cash")){
                                                    realCashC.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(j));
                                                }
                                            }
                                            fullPathC = ui.getPathMaster().pathBuilder(realCashC);
                                        } else if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("StartPath") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("PathCash")){
                                            for(int j = 0; j < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size(); j++){
                                                if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("StartPath") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("PathCash")){
                                                    realCashC.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(j));
                                                }
                                            }
                                            fullPathC = ui.getPathMaster().pathBuilder(realCashC);
                                        } else if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("StartSearch") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("SearchCash")){
                                            for(int j = 0; j < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size(); j++){
                                                if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("StartSearch") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("SearchCash")){
                                                    realCashC.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(j));
                                                }
                                            }

                                            fullPathC = ui.getPathMaster().pathBuilder(realCashC);

                                        }

                        }


                        File selectedFileC;
                        selectedFileC = new File(fullPathC + selectedObject);
                        
                        String path = new File("src\\data\\MarkedFiles.txt").getAbsolutePath();
        
                        StringBuffer p = new StringBuffer();
                        File file = new File(path);
                        BufferedReader bReader = null;
                        InputStreamReader iReader = null;
                        FileInputStream fStream = null;

                        try {

                            fStream = new FileInputStream(file);
                            iReader = new InputStreamReader(fStream, "UTF-8");
                            bReader = new BufferedReader(iReader);

                            String fileLine = bReader.readLine();

                            int countData = 0;
                            
                            while (fileLine != null) {
                                
                                boolean addPath = false;
                                
                                p.append(fileLine);
                                
                                addPath = true;
                                
                                fileLine = bReader.readLine();
                                
                                if(addPath && fileLine != null) {
                                    
                                    p.append("\r\n");
                                    
                                }
                                
                            }
                            
                            p.append("\r\n");
                            p.append(selectedFileC.toString());
                            
                            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));
                            bw.write(p.toString());
                            bw.close();
                            
                            fStream.close();
                            iReader.close();
                            bReader.close();
                            
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        } catch (UnsupportedEncodingException ex) {
                            Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        }
                        
                        ArrayList<ArrayList<String>> almostEmptyList = new ArrayList<>();
                            almostEmptyList.add(new ArrayList<>(Arrays.asList(new String[]{"\\/:*?\"<>|"})));
                            ui.getMainFilesPanel().iniclizButton(almostEmptyList);
                        
                        remove(addMarkedFilesButton);
                        add(removeMarkedFilesButton);
                        
                        revalidate();
                        repaint();
                    
                    }
                    
                    System.out.println("1qaz");
                
                if(ui.getSearchTextField().getText().equals("") || ui.getSearchTextField().getText() == null) {
                    
                    ui.getSearchTextField().setText("Поиск");
                    ui.getSearchTextField().setForeground(new Color(37, 83, 185));
                    
                }
                
                if(ui.getMainFilesPanel().getPrivateButton() != null) {
                    ui.getMainFilesPanel().getPrivateButton().remove(ui.getMainFilesPanel().getPrivateButton());
                }
                
                for(int i = 0; i < (ui.getMainFilesPanel().getTopFiles().length - 1); i++){
                    
                    if(ui.getMainFilesPanel().getTopFiles()[i] != null) {
                        
                        ui.getMainFilesPanel().getTopFiles()[i].setBorderPainted(false);
                        
                    }
                    
                }
                
                int cpWidth = ui.getContentPane().getWidth();
                int cpHeight = ui.getContentPane().getHeight();
                
                ui.getBasicPanel().removeAll();
                
                ui.getNavigationButtonsPanel().setBounds(0, 0, 336, 45);
                ui.getTabbedPanel().setBounds(336, 0, (cpWidth - 336 - 168), 45);
                ui.getSearchTextField().setBounds((336 + (cpWidth - 336 - 168)), 0, 168, 45);
                ui.getSort().setBounds(10, 50, 316, 45);
                ui.getMainFilesPanel().setBounds(0, 45, cpWidth, (cpHeight - 45));
                setBounds((int)(cpWidth - cpWidth / 5 * 1), 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 1 - (int)(cpWidth - cpWidth / 5 * 1))), (int)(cpHeight));
                ui.getMorePanel().setBounds(0, 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 5 - (int)(cpWidth - cpWidth / 5 * 5))), (int)(cpHeight));
                
                ui.getBasicPanel().add(ui.getNavigationButtonsPanel());
                ui.getBasicPanel().add(ui.getTabbedPanel());
                ui.getBasicPanel().add(ui.getSearchTextField());
                ui.getBasicPanel().add(ui.getSort());
                ui.getBasicPanel().add(ui.getMainFilesPanel());
                
                ui.getBasicPanel().moveToFront(ui.getSort());
                
                revalidate();
                repaint();
                    
            }
        });
        
        removeMarkedFilesButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
                    if(ui.getMainFilesPanel().getPrivateButton() != null){
                        
                        String selectedObject = ui.getMainFilesPanel().getPrivateButton().getName();
                        
                        ArrayList <String> realCashC = new ArrayList<>();
                        String fullPathC = "";

                        if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() > 0) {

                                        if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("StartCash") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("Cash")){
                                            for(int j = 0; j < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size(); j++){
                                                if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("StartCash") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("Cash")){
                                                    realCashC.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(j));
                                                }
                                            }
                                            fullPathC = ui.getPathMaster().pathBuilder(realCashC);
                                        } else if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("StartPath") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("PathCash")){
                                            for(int j = 0; j < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size(); j++){
                                                if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("StartPath") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("PathCash")){
                                                    realCashC.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(j));
                                                }
                                            }
                                            fullPathC = ui.getPathMaster().pathBuilder(realCashC);
                                        } else if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("StartSearch") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("SearchCash")){
                                            for(int j = 0; j < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size(); j++){
                                                if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("StartSearch") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("SearchCash")){
                                                    realCashC.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(j));
                                                }
                                            }

                                            fullPathC = ui.getPathMaster().pathBuilder(realCashC);

                                        }

                        }


                        File selectedFileC;
                        selectedFileC = new File(fullPathC + selectedObject);
                        
                        String path = new File("src\\data\\MarkedFiles.txt").getAbsolutePath();
        
                        StringBuffer p = new StringBuffer();
                        File file = new File(path);
                        BufferedReader bReader = null;
                        InputStreamReader iReader = null;
                        FileInputStream fStream = null;

                        try {

                            fStream = new FileInputStream(file);
                            iReader = new InputStreamReader(fStream, "UTF-8");
                            bReader = new BufferedReader(iReader);

                            String fileLine = bReader.readLine();

                            int countData = 0;
                            
                            while (fileLine != null) {
                                
                                boolean addPath = false;
                                
                                if(!fileLine.equals(selectedFileC.toString())) {
                                
                                    p.append(fileLine);

                                    addPath = true;
                                
                                }
                                
                                fileLine = bReader.readLine();
                                
                                if(addPath && fileLine != null) {
                                    
                                    p.append("\r\n");
                                    
                                }
                                
                            }
                            
                            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));
                            bw.write(p.toString());
                            bw.close();
                            
                            fStream.close();
                            iReader.close();
                            bReader.close();
                            
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        } catch (UnsupportedEncodingException ex) {
                            Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        }
                        
                        ArrayList<ArrayList<String>> almostEmptyList = new ArrayList<>();
                            almostEmptyList.add(new ArrayList<>(Arrays.asList(new String[]{"\\/:*?\"<>|"})));
                            ui.getMainFilesPanel().iniclizButton(almostEmptyList);
                        
                        remove(removeMarkedFilesButton);
                        add(addMarkedFilesButton);
                        
                        revalidate();
                        repaint();
                    
                    }
                    
                    System.out.println("1qaz");
                
                if(ui.getSearchTextField().getText().equals("") || ui.getSearchTextField().getText() == null) {
                    
                    ui.getSearchTextField().setText("Поиск");
                    ui.getSearchTextField().setForeground(new Color(37, 83, 185));
                    
                }
                
                if(ui.getMainFilesPanel().getPrivateButton() != null) {
                    ui.getMainFilesPanel().getPrivateButton().remove(ui.getMainFilesPanel().getPrivateButton());
                }
                
                for(int i = 0; i < (ui.getMainFilesPanel().getTopFiles().length - 1); i++){
                    
                    if(ui.getMainFilesPanel().getTopFiles()[i] != null) {
                        
                        ui.getMainFilesPanel().getTopFiles()[i].setBorderPainted(false);
                        
                    }
                    
                }
                
                int cpWidth = ui.getContentPane().getWidth();
                int cpHeight = ui.getContentPane().getHeight();
                
                ui.getBasicPanel().removeAll();
                
                ui.getNavigationButtonsPanel().setBounds(0, 0, 336, 45);
                ui.getTabbedPanel().setBounds(336, 0, (cpWidth - 336 - 168), 45);
                ui.getSearchTextField().setBounds((336 + (cpWidth - 336 - 168)), 0, 168, 45);
                ui.getSort().setBounds(10, 50, 316, 45);
                ui.getMainFilesPanel().setBounds(0, 45, cpWidth, (cpHeight - 45));
                setBounds((int)(cpWidth - cpWidth / 5 * 1), 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 1 - (int)(cpWidth - cpWidth / 5 * 1))), (int)(cpHeight));
                ui.getMorePanel().setBounds(0, 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 5 - (int)(cpWidth - cpWidth / 5 * 5))), (int)(cpHeight));
                
                ui.getBasicPanel().add(ui.getNavigationButtonsPanel());
                ui.getBasicPanel().add(ui.getTabbedPanel());
                ui.getBasicPanel().add(ui.getSearchTextField());
                ui.getBasicPanel().add(ui.getSort());
                ui.getBasicPanel().add(ui.getMainFilesPanel());
                
                ui.getBasicPanel().moveToFront(ui.getSort());
                
                revalidate();
                repaint();
                    
            }
        });
        
        makeShortcutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).isEmpty()){
                    String currentPath;
                    File newShortcut;
                    CreateNewShortcutJDialog newFolderJDialog = new CreateNewShortcutJDialog(ui);
                    
                    if(newFolderJDialog.getReady()){
                        currentPath = ui.getPathMaster().pathBuilder(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0));
                        newShortcut = new File(currentPath, newFolderJDialog.getNewName());
                        if(!newShortcut.exists()) {
                            
                            FileOutputStream fo = null;

                            try {
                                
                                String selectedObject =  ui.getMainFilesPanel().getPrivateButton().getName();
                                
                                File shortcutFile = new File(currentPath, selectedObject);

                                String script = "Call Shortcut(\"" + shortcutFile.toString() + "\",\"" + newFolderJDialog.getNewName() + "\")\n" +
                                                "'*********************************************************************************\n" +
                                                "Sub Shortcut(PathApplication,Name)\n" +
                                                "    Dim objShell,DesktopPath,objShortCut,MyTab\n" +
                                                "    Set objShell = CreateObject(\"WScript.Shell\")\n" +
                                                "    MyTab = Split(PathApplication,\"\\\")\n" +
                                                "    If Name = \"\" Then\n" +
                                                "        Name = MyTab(UBound(MyTab))\n" +
                                                "    End if\n" +
                                                "    DesktopPath = objShell.SpecialFolders(\"Desktop\")\n" +
                                                "    Set objShortCut = objShell.CreateShortcut(\"" + currentPath + "/\" & \"\\\" & Name & \".lnk\")\n" +
                                                "    objShortCut.TargetPath = Dblquote(PathApplication)\n" +
                                                "    ObjShortCut.IconLocation = \"%SystemRoot%\\system32\\SHELL32.dll,-25\"\n" +
                                                "    objShortCut.Save\n" +
                                                "End Sub\n" +
                                                "'*********************************************************************************\n" +
                                                "Function DblQuote(Str)\n" +
                                                "    DblQuote = Chr(34) & Str & Chr(34)\n" +
                                                "End Function\n" +
                                                "'*********************************************************************************";
                                
                                File file = new File("src\\temp.vbs").getAbsoluteFile();
                                fo = new FileOutputStream(file);
                                fo.write(script.getBytes());
                                fo.close();
                                //Runtime.getRuntime().exec("wscript.exe D:/temp/crear-acceso-directo.vbs");
                                Runtime.getRuntime().exec("wscript.exe " + file);
                                
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
                        } finally {
                                try {
                                    fo.close();
                                } catch (IOException ex) {
                                    Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            
                        }
                        
                        File file = new File("src\\temp.vbs").getAbsoluteFile();
                        
                        file.delete();
                        
                        File updateDir = new File(currentPath);
                        String updateMas[] = updateDir.list();
                        ArrayList<String> updateModel = new ArrayList<>();
                        for (String str : updateMas){
                            File check = new File(updateDir.getPath(), str);
                            if(!check.isHidden()){
                                if(check.isDirectory()){
                                    updateModel.add(str);
                                } else {
                                    updateModel.add(/*"файл-" + */str);
                                }
                            }
                        }
                        
                        ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).removeAll(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0));
                        ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1).removeAll(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1));
                        
                        ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).addAll(updateModel);

                        for(int i = 0; i < ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).size(); i++) {

                            ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1).add("ForPath");

                        }
                        
                        ArrayList<ArrayList<String>> almostEmptyList = new ArrayList<>();
                        almostEmptyList.add(new ArrayList<>(Arrays.asList(new String[]{"\\/:*?\"<>|"})));
                        ui.getMainFilesPanel().iniclizButton(almostEmptyList);
                        
                    }
                }
                
                System.out.println("1qaz");
                
                if(ui.getSearchTextField().getText().equals("") || ui.getSearchTextField().getText() == null) {
                    
                    ui.getSearchTextField().setText("Поиск");
                    ui.getSearchTextField().setForeground(new Color(37, 83, 185));
                    
                }
                
                if(ui.getMainFilesPanel().getPrivateButton() != null) {
                    ui.getMainFilesPanel().getPrivateButton().remove(ui.getMainFilesPanel().getPrivateButton());
                }
                
                for(int i = 0; i < (ui.getMainFilesPanel().getTopFiles().length - 1); i++){
                    
                    if(ui.getMainFilesPanel().getTopFiles()[i] != null) {
                        
                        ui.getMainFilesPanel().getTopFiles()[i].setBorderPainted(false);
                        
                    }
                    
                }
                
                int cpWidth = ui.getContentPane().getWidth();
                int cpHeight = ui.getContentPane().getHeight();
                
                ui.getBasicPanel().removeAll();
                
                ui.getNavigationButtonsPanel().setBounds(0, 0, 336, 45);
                ui.getTabbedPanel().setBounds(336, 0, (cpWidth - 336 - 168), 45);
                ui.getSearchTextField().setBounds((336 + (cpWidth - 336 - 168)), 0, 168, 45);
                ui.getSort().setBounds(10, 50, 316, 45);
                ui.getMainFilesPanel().setBounds(0, 45, cpWidth, (cpHeight - 45));
                setBounds((int)(cpWidth - cpWidth / 5 * 1), 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 1 - (int)(cpWidth - cpWidth / 5 * 1))), (int)(cpHeight));
                ui.getMorePanel().setBounds(0, 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 5 - (int)(cpWidth - cpWidth / 5 * 5))), (int)(cpHeight));
                
                ui.getBasicPanel().add(ui.getNavigationButtonsPanel());
                ui.getBasicPanel().add(ui.getTabbedPanel());
                ui.getBasicPanel().add(ui.getSearchTextField());
                ui.getBasicPanel().add(ui.getSort());
                ui.getBasicPanel().add(ui.getMainFilesPanel());
                
                ui.getBasicPanel().moveToFront(ui.getSort());
                
                revalidate();
                repaint();
                
            }
        });
        
        add(upButton);
        add(deleteButton);
        add(addButton);
        add(renameButton);
        add(copyInButton);
        add(newPlaceInButton);
        add(addMarkedFilesButton);
        add(makeShortcutButton);
        
    }
    
    public JButton getAddMarkedFilesButton() {
        
        return(addMarkedFilesButton);
        
    }
    
    public void setAddMarkedFilesButton(JButton addMarkedFilesButton) {
        
        this.addMarkedFilesButton = addMarkedFilesButton;
        
    }
    
    public JButton getRemoveMarkedFilesButton() {
        
        return(removeMarkedFilesButton);
        
    }
    
    public void setRemoveMarkedFilesButton(JButton removeMarkedFilesButton) {
        
        this.removeMarkedFilesButton = removeMarkedFilesButton;
        
    }
    
}
