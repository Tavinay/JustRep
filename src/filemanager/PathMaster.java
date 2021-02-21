package filemanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.filechooser.FileSystemView;

public class PathMaster {
    
    private int discii2 = 0;
    private int ui7 = 0;
    private int lastTab = 0;
    
    private boolean check321 = true;
    private boolean lastSearch = false;
    
    private long byteCopy = 0;
    private long length56 = 0;
    
    private String pathSep = File.pathSeparator;
    private String sep = File.separator;
    
    private File src;
    private File src2;
    
    private File[] discs;
    
    private FileSystemView fsv;
    
    private ArrayList<ArrayList<ArrayList<String>>> dirsCash = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> antiDirsCash = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> filesList = new ArrayList<>();
    
    private Thread cd = new CopyDirectory(this);
    private Thread cd2 = new CopyDirectory2(this);
    
    private OperationStatus os;
    
    private UI ui;
    
    public PathMaster(UI ui) {
        
        this.ui = ui;
        
        File discs2[] = File.listRoots();
        
        for (int discii = 0; discii < discs2.length; discii++) {  
            
	  long normalDisk = discs2[discii].getTotalSpace();
          if(normalDisk > 0 || discs2[discii].exists()){
            discii2++;
          }
        }
        
        discs = new File[discii2];
        
        for (int discii = 0; discii < discs2.length; discii++) {  
            
	  long normalDisk = discs2[discii].getTotalSpace();
          if(normalDisk > 0 || discs2[discii].exists()){
            discs[ui7] = discs2[discii];
            ui7++;
          }
        }
        
        fsv = FileSystemView.getFileSystemView();
        
        for(File path:discs) {
            
            System.out.println("Drive Name: " + path);
            System.out.println("Description: " + fsv.getSystemTypeDescription(path));
            
        }
        
        ui.setTabbedPanel(new TabbedPanel(ui, this));
        
        List<String> discsList = new ArrayList<>(Arrays.asList(discs)).stream().map(d -> d.toString()).collect(Collectors.toList());
        filesList.get(0).get(0).addAll(new ArrayList<>(discsList));
        for(int i = 0; i < filesList.get(0).get(0).size(); i++) {
            
            filesList.get(0).get(1).add("ForPath");
            
        }
        
    }
    
    public void copyDirectory(File sourceLocation , File targetLocation) {
        
        if(check321) {
            
            length56 = 0;
            File dir = new File(targetLocation.getParent());
            ui.getInformationAboutObjectsPanel().setDir1(new File(sourceLocation.toString()));
            ui.getInformationAboutObjectsPanel().startThread1("length56");
            while(length56 == 0) {
                
                System.out.println("Wait th1's job");
                
            }
            System.out.println("size1: " + os);
            os = new OperationStatus(0, length56);
            System.out.println("size2: " + os.getSize());
            cd2.start();
            check321 = false;
            
        }
        
        //os.changeProcent(Integer.parseInt(Long.toString(byteCopy)));
        
        if (sourceLocation.isDirectory()) {
            
            if (!targetLocation.exists()) {
                
                targetLocation.mkdir();
                
            }
            
            String[] children = sourceLocation.list();
            
            for (int i = 0; i < children.length; i++) {
                
                copyDirectory(new File(sourceLocation, children[i]), new File(targetLocation, children[i]));
                
            }
            
            //os.changeProcent(Integer.parseInt(Long.toString(byteCopy)));
            
        } else {

//            try {
//                Files.copy(sourceLocation.toPath(), targetLocation.toPath(), StandardCopyOption.REPLACE_EXISTING);
//            } catch (IOException ex) {
//                Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//                    byteCopy += sourceLocation.length();
//                    
//                    System.out.println("Скопировано : " + byteCopy + " из " + length56);
//                    
//                    if(byteCopy == length56){
//                        
//                        System.out.println("Копирование завершено.");
//                        
//                    }
            
            InputStream in = null;
            try {
                
                in = new FileInputStream(sourceLocation);
                OutputStream out = new FileOutputStream(targetLocation);
                byte[] buf = new byte[4096];
                int len;
                
                while ((len = in.read(buf)) > 0) {
                    
                    byteCopy += len;
                    
                    //os.changeProcent(Integer.parseInt(Long.toString(byteCopy)));
                    
                    //System.out.println("Скопировано : " + byteCopy + " из " + length56);
                    
                    if(byteCopy == length56){
                        
                        System.out.println("Копирование завершено.");
                        
                    }
                    
                    out.write(buf, 0, len);
                    
                }
                
                in.close();
                out.close();
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    in.close();
                } catch (IOException ex) {
                    Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        
    }
    
    public void addCDThread() {
        
        if(!cd.isInterrupted()) {
            
            cd.interrupt();
            
        }
        
        if(!cd2.isInterrupted()) {
            
            cd2.interrupt();
            
        }
        
        cd = null;
        cd2 = null;
        
        cd = new CopyDirectory(this);
        cd2 = new CopyDirectory2(this);
        
    }

    public void startCD() {

        cd.start();

    }

    public void startCD2() {

        cd2.start();
        
    }
    
    public void deleteDir(File file){
        
        File[] objects = file.listFiles();
        
        if(objects != null){
            
          for(File fileForMethod : objects){
              
            deleteDir(fileForMethod);
            
          }
          
        }
        
        file.delete();
        
    }
    
    public String pathBuilder (List<String> file) {
        
        String listPart = "";
        int checkForExtraSlash = 1;
        
        for(String str : file){
            
            if(checkForExtraSlash == 1 && str.length() > 0 && str.substring(str.length() - 1).equals("\\")){
                
                checkForExtraSlash = 0;
                listPart = listPart + str;
                
            } else {
                
                listPart = listPart + str + "\\";
                
            }
            
        }
        
        return listPart;
        
    }

    public String getSep() {
        
        return(sep);
        
    }
    
    public void setSep(String sep) {
        
        this.sep = sep;
        
    }

    public String getPathSep() {
        
        return(pathSep);
        
    }
    
    public void setPathSep(String pathSep) {
        
        this.pathSep = pathSep;
        
    }

    public int getDiscii2() {
        
        return(discii2);
        
    }
    
    public void setDiscii2(int discii2) {
        
        this.discii2 = discii2;
        
    }

    public int getUi7() {
        
        return(ui7);
        
    }
    
    public void setUi7(int ui7) {
        
        this.ui7 = ui7;
        
    }
    
    public int getLastTab() {
        
        return(lastTab);
        
    }
    
    public void setLastTab(int lastTab) {
        
        this.lastTab = lastTab;
        
    }

    public boolean getCheck321() {
        
        return(check321);
        
    }
    
    public void setCheck321(boolean check321) {
        
        this.check321 = check321;
        
    }
    
    public boolean getLastSearch() {
        
        return(lastSearch);
        
    }
    
    public void setLastSearch(boolean lastSearch) {
        
        this.lastSearch = lastSearch;
        
    }

    public long getByteCopy() {
        
        return(byteCopy);
        
    }
    
    public void setByteCopy(long byteCopy) {
        
        this.byteCopy = byteCopy;
        
    }
    
    public long getLength56() {
        
        return(length56);
        
    }
    
    public void setLength56(long length56) {
        
        this.length56 = length56;
        
    }

    public File getSrc() {
        
        return(src);
        
    }
    
    public void setSrc(File src) {
        
        this.src = src;
        
    }

    public File getSrc2() {
        
        return(src2);
        
    }
    
    public void setSrc2(File src2) {
        
        this.src2 = src2;
        
    }

    public FileSystemView getFsv() {
        
        return(fsv);
        
    }
    
    public void setFsv(FileSystemView fsv) {
        
        this.fsv = fsv;
        
    }
    
    public File[] getDiscs() {
        
        return(discs);
        
    }
    
    public void setDiscs(File[] discs) {
        
        this.discs = discs;
        
    }
    
    public ArrayList<ArrayList<ArrayList<String>>> getDirsCash() {
        
        return(dirsCash);
        
    }
    
    public void setDirsCash(ArrayList<ArrayList<ArrayList<String>>> dirsCash) {
        
        this.dirsCash = dirsCash;
        
    }
    
    public ArrayList<ArrayList<ArrayList<String>>> getAntiDirsCash() {
        
        return(antiDirsCash);
        
    }
    
    public void setAntiDirsCash(ArrayList<ArrayList<ArrayList<String>>> antiDirsCash) {
        
        this.antiDirsCash = antiDirsCash;
        
    }
    
    public ArrayList<ArrayList<ArrayList<String>>> getFilesList() {
        
        return(filesList);
        
    }
    
    public void setFilesList(ArrayList<ArrayList<ArrayList<String>>> filesList) {
        
        this.filesList = filesList;
        
    }
    
    private class CopyDirectory extends Thread {
        
        PathMaster pm;
        
        File sourceLocation = src;
        File targetLocation = src2;
        
        CopyDirectory(PathMaster pm) {
            
            super("Thread for PathMaster");
            
            this.pm = pm;
        
        }

        public void run() {
            
            copyDirectory(sourceLocation, targetLocation);
            
            if(os != null) {
                        
                os.dispose();
                
                os = null;
                        
            }
            
        }
        
    }
    
    private class CopyDirectory2 extends Thread {
        
        PathMaster pm;
        
        CopyDirectory2(PathMaster pm) {
            
            super("Thread for PathMaster");
            
            this.pm = pm;
        
        }

        public void run() {
            
            while(os != null) {
            
                os.changeProcent(byteCopy);
                
                System.out.println("Скопировано : " + byteCopy + " из " + length56);
                
            }
            
        }
        
    }
    
}
