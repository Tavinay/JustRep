package filemanager;

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
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileManager {
    
    static int freeThreads = Runtime.getRuntime().availableProcessors();
    
    public static void main(String[] args) {
        
        freeThreads--;
        
        System.out.println("Потоков: " + Runtime.getRuntime().availableProcessors());
        System.out.println("???: " + Runtime.getRuntime().freeMemory());
        System.out.println("???: " + Runtime.getRuntime().maxMemory());
        System.out.println("???: " + Runtime.getRuntime().totalMemory());
        try {
            System.out.println("???: " + InetAddress.getLocalHost().getHostName());
        } catch (UnknownHostException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("???: ");
        int i = 0;
        Enumeration en = System.getProperties().elements();
        while(en.hasMoreElements()) {
            
            System.out.println("?: " + i++ + " " + en.nextElement());
            
        }
        System.out.println("???: ");
        int j = 0;
        Enumeration en2 = System.getProperties().propertyNames();
        while(en2.hasMoreElements()) {
            
            System.out.println("?: " + j++ + " " + en2.nextElement());
            
        }
        System.out.println("???: " + System.getProperties().values());
        System.out.println("???: " + System.getProperties().stringPropertyNames());
        System.out.println("???: " + System.getenv());
        
        //Concurrency (параллельности) и Multithreading (многопоточности), Serialization (сериализация)
        
        //FileOutputStream fo = null;
            checkPaths();
            //File f = new File("E:\\123qwerty1232");
            
            //f.setLastModified(3376663201860L);
            
            //System.out.println(f.getParent());
            
//            try {
//                
//                String script = "Call Shortcut(\"E:\\123qwerty123\\2\\edc.rar\",\"Hackoo\")\n" +
//"'*********************************************************************************\n" +
//"Sub Shortcut(PathApplication,Name)\n" +
//"    Dim objShell,DesktopPath,objShortCut,MyTab\n" +
//"    Set objShell = CreateObject(\"WScript.Shell\")\n" +
//"    MyTab = Split(PathApplication,\"\\\")\n" +
//"    If Name = \"\" Then\n" +
//"        Name = MyTab(UBound(MyTab))\n" +
//"    End if\n" +
//"    DesktopPath = objShell.SpecialFolders(\"Desktop\")\n" +
//"    Set objShortCut = objShell.CreateShortcut(\"E:/123qwerty123/\" & \"\\\" & Name & \".lnk\")\n" +
//"    objShortCut.TargetPath = Dblquote(PathApplication)\n" +
//"    ObjShortCut.IconLocation = \"%SystemRoot%\\system32\\SHELL32.dll,-25\"\n" +
//"    objShortCut.Save\n" +
//"End Sub\n" +
//"'*********************************************************************************\n" +
//"Function DblQuote(Str)\n" +
//"    DblQuote = Chr(34) & Str & Chr(34)\n" +
//"End Function\n" +
//"'*********************************************************************************";
//                File file = new File("E:/123qwerty123/temp.vbs");
//                fo = new FileOutputStream(file);
//                fo.write(script.getBytes());
//                fo.close();
//                //Runtime.getRuntime().exec("wscript.exe D:/temp/crear-acceso-directo.vbs");
//                Runtime.getRuntime().exec("wscript.exe " + file);
//            
//            } catch (FileNotFoundException ex) {
//                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (IOException ex) {
//            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//                try {
//                    fo.close();
//                } catch (IOException ex) {
//                    Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
            
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new UI();
                }
            });
            
    }
    
    public static void checkPaths() {
        
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
                
                ArrayList<String> fileLinesList = new ArrayList<>();
                
            String fileLine = bReader.readLine();
            
                while (fileLine != null) {
                    
                    fileLinesList.add(fileLine);
                    
                    boolean havePath = false;
                    boolean addPath = false;
                    
                    if(new File(fileLine).exists()) {
                        
                        for(int i = 0; i < (fileLinesList.size() - 1); i++) {
                            
                            if(fileLine.equals(fileLinesList.get(i))) {
                                
                                havePath = true;
                                
                            }
                            
                        }
                        
                        if(!havePath) {
                            
                            p.append(fileLine);
                            
                            addPath = true;
                        
                        }
                        
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
        
    }
    
}
