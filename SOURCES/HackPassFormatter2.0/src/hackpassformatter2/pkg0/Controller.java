package hackpassformatter2.pkg0;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;

public class Controller {
    private String[] args;
    private Interface IHM;
    private FilenameFilter OnlyTxt;
    /**
     * +Controller(String[]):void
     * @param args -D for directory, -O for the outut file.
     */
    public Controller(String[] args){
        this.args = args;
        OnlyTxt = (File dir, String name) -> {return name.toLowerCase().endsWith(".txt");};
        if(args.length>2){
            
        }
        else{
            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
                System.err.println(ex.getLocalizedMessage());
            }
            IHM = new Interface(this);
            IHM.setVisible(true);
        }
    }
    public void Stop(){
        if(IHM!=null && IHM.isVisible()){
            IHM.setVisible(false);
        }
        System.exit(0);
    }
        
    public File SelectInput(){
        File Return = null;
        JFileChooser Chooser = new JFileChooser(System.getProperty("user.home")+"/Desktop");
        Chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        if(Chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
            Return = Chooser.getSelectedFile();
        }
        return Return;
    }
    public File SelectInput(String From){
        File Return = null;
        JFileChooser Chooser = new JFileChooser(From);
        Chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        if(Chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
            Return = Chooser.getSelectedFile();
        }
        return Return;
    }
    public String GetInputPath(File F){
        String Path="";
        if(F!=null && F.exists() && F.isFile() && F.getName().toLowerCase().endsWith(".txt")){
            try {Path = F.getCanonicalPath();} catch (IOException ex) {}
        }
        else if(F!=null && F.exists() && F.isDirectory()){
            File[] Files = F.listFiles(OnlyTxt);
            if(Files.length>0){
                try {Path = F.getCanonicalPath();} catch (IOException ex) {}
                if(Path.length()>0 && !Path.endsWith("/")){Path = Path+"\\";}
            }
        }
        
        return Path;
    }
    public DefaultListModel<String> GetInputList(File F){
        DefaultListModel<String> Liste = new DefaultListModel<>();
        if(F!=null && F.exists() && F.isFile() && F.getName().toLowerCase().endsWith(".txt")){
            Liste.addElement(F.getName());
        }
        else if(F!=null && F.exists() && F.isDirectory()){
            for(File Fi : F.listFiles(OnlyTxt)){
                Liste.addElement(Fi.getName());
            }
        }
        return Liste;
    }
    
    public int GetMaxValue(File F){
        int i = 0;
         if(F!=null && F.exists() && F.isFile() && F.getName().toLowerCase().endsWith(".txt")){
            i = (int)F.length() / 1024;
        }
        else if(F!=null && F.exists() && F.isDirectory()){
            for(File Fi : F.listFiles(OnlyTxt)){
                i += (int)Fi.length() / 1024;
            }
        }
        return i;
    }
    
    public String[][] FormatFiles(File F){
        String[][] Return = null;
        if(F.exists() && F.isFile() && F.getName().toLowerCase().endsWith(".txt")){
            String Content = ReadFile(F);
            if(Content.length()>10){
                String[] Blocks = TextToBlock(Content);
                if(Blocks.length>0){
                    String[][] TempReturn = new String[Blocks.length][4];
                    int EmtyBlock=0;
                    for(int i=0;i<Blocks.length;i++){
                        TempReturn[i]=BlockToData(Blocks[i]);
                        if(TempReturn[i]==null){EmtyBlock++;}
                    }
                    if(EmtyBlock>0){
                        Return = new String[TempReturn.length-EmtyBlock][4];
                        int j=0;
                        for(int i=0;i<TempReturn.length;i++){
                            if(TempReturn[i]!=null){
                                Return[j]=TempReturn[i];
                                j++;
                            }
                        }
                    }
                    else{
                        Return=TempReturn;
                    }
                }
            }
        }
        else if(F.exists() && F.isDirectory()){
            for(File Fi : F.listFiles(OnlyTxt)){
                String[][] Temp = FormatFiles(Fi);
                if(Temp!=null){
                    if(Return==null){
                        Return = Temp;
                    }
                    else{
                        String[][] ReturnTmp = new String[Return.length+Temp.length][4];
                        for(int i=0;i<Return.length;i++){
                            ReturnTmp[i] = Return[i];
                        }
                        for(int i=Return.length;i<ReturnTmp.length;i++){
                            ReturnTmp[i] = Temp[i-Return.length];
                        }
                        Return = ReturnTmp;
                    }
                }
            }
        }
        return Return;
    }
    
    public String ReadFile(File File){
        String Content = "";
        if(File.exists() && File.isFile() && File.getName().endsWith(".txt") && File.length()>0){
            try {
                BufferedReader Br = new BufferedReader(new FileReader(File));
                String Line;
                while((Line=Br.readLine())!=null){
                    Content = Content + Line + "\n";
                }   
                Br.close();
            } catch (IOException ex) {}
        }
        if(Content.endsWith("\n")){
            Content = Content.substring(0,Content.length()-1);
        }
        if(Content.contains(""+(char)0)){
            Content = Content.replace(""+(char)0, "");
        }
        if(Content.contains("\n\n")){
            Content = Content.replace("\n\n", "\n");
        }
        return Content;
    }
    
    public String[] TextToBlock(String Content){
        String[] Blocks = null;
        if(Content.length()>0 && Content.contains("==================================================")){
            String[] BlockTemp = Content.split("==================================================");
            int Cpt=0;
            for(String T : BlockTemp){
                if(T.length()>10){Cpt++;}
            }
            Blocks = new String[Cpt];
            Cpt=0;
            for(String T : BlockTemp){
                if(T.length()>10){
                    Blocks[Cpt]=T;
                    if(Blocks[Cpt].endsWith("\n")){
                        Blocks[Cpt] = Blocks[Cpt].substring(0,Blocks[Cpt].length()-1);
                    }
                    Cpt++;
                }
            }
        }
        return Blocks;
    }
    public String[] BlockToData(String Block){
        String[] Data = new String[4];
        if(Block!=null && Block.length()>0 && Block.contains("\n")){
            String[] Splitted = Block.split("\n");
            for(String St: Splitted){
                String[] Dat = St.split(":");
                if(Dat.length>=2){
                    Dat[0] = Dat[0].toLowerCase().replace(" ", "");
                    if(Dat[0].contains("url") && Dat[1].contains("http")){
                        Data[1] = Dat[1]+":"+Dat[2].substring(0, Dat[2].substring(2).indexOf("/")+2);
                        while(Data[1].startsWith(" ")){Data[1]=Data[1].substring(1);}
                        Data[1] = Data[1].replace("http://", "").replace("https://", "").replace("www.", "").replace("auth.","").replace("espace-client.", "").replace("account.", "").replace("monespace.", "").replace("club.", "").replace("fr-fr.", "").replace("oauth2.", "").replace("connect.", "").replace("assure.", "").replace("cfspart.", "");
                    }
                    if(Dat[0].contains("url") && Dat[1].contains("android")){
                        Data[1] = Dat[1]+":"+Dat[2].substring(Dat[2].lastIndexOf("==@")+3);
                        while(Data[1].startsWith(" ")){Data[1]=Data[1].substring(1);}
                    }                
                    if(Dat[0].equals("username")){
                        Data[2] = Dat[1];
                        while(Data[2].startsWith(" ")){Data[2]=Data[2].substring(1);}
                    }
                    if(Dat[0].contains("ssid")){
                        Data[1]="WIFI";
                        Data[2] = Dat[1];
                        while(Data[2].startsWith(" ")){Data[2]=Data[2].substring(1);}
                    }
                    if(Dat[0].equals("password")){
                        if(Data[3]==null){Data[3]="";}
                        for(int i=1;i<Dat.length;i++){
                            Data[3] = Data[3]+":"+ Dat[i];
                        }
                        Data[3]=Data[3].substring(1);
                        while(Data[3].startsWith(" ")){Data[3]=Data[3].substring(1);}
                    }
                    if(Dat[0].equals("key(ascii)")){
                        if(Data[3]==null){Data[3]="";}
                        for(int i=1;i<Dat.length;i++){
                            Data[3] = Data[3]+":"+ Dat[i];
                        }
                        Data[3]=Data[3].substring(1);
                        while(Data[3].startsWith(" ")){Data[3]=Data[3].substring(1);}
                    }
               }
            }
            Data[0]=GetType(Data[1]);
        }
        if(Data[3].length()<2 || Data[2].length()<2 ||Data[1].length()<2){Data=null;}
        return Data;
    }
    public String GetType(String Url){
        String Type = "AUTRE";
        if(Url.equals("WIFI")){Type="WIFI";}
        if(Url.contains("leboncoin")){Type="MAGASIN";}
        if(Url.contains("intersport")){Type="MAGASIN";}
        if(Url.contains("edf")){Type="DATA";}
        if(Url.contains("sfr")){Type="INTERNET";}
        if(Url.contains("orange")){Type="INTERNET";}
        if(Url.contains("bouygues")){Type="INTERNET";}
        if(Url.contains("nrj")){Type="INTERNET";}
        if(Url.contains("generation")){Type="SANTE+";}
        if(Url.contains("cofidis")){Type="BANQUE";}
        if(Url.contains("facebook")){Type="SOCIAL";}
        if(Url.contains("maaf")){Type="SANTE";}
        if(Url.contains("ameli")){Type="AMELI";}
        if(Url.contains("caf")){Type="CAF";}
        if(Url.contains("doctolib")){Type="SANTE";}
        return Type;
    }
}
