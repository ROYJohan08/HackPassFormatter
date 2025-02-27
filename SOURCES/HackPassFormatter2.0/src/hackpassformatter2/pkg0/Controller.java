package hackpassformatter2.pkg0;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;

public class Controller {
    private String[] args;
    private Interface IHM;
    private FilenameFilter OnlyTxt;
    private String[][] Tableau =null;

    public Controller(String[] args){
        this.args = args;
        OnlyTxt = (File dir, String name) -> {return name.toLowerCase().endsWith(".txt");};
        if(args.length>2){
            String From=null;String To = null;
            for(int i=0;i<args.length;i++){
                if(args[i].toLowerCase().equals("-F")){
                    From = args[i+1];
                }
                if(args[i].toLowerCase().equals("-T")){
                    To = args[i+1];
                }
            }
            if(From!=null && To != null){
                String[][] Pass = FormatFiles(new File(From));
                Pass=RemoveDouble(Pass);
                if(Pass.length>0){
                    Save(new File(To));
                }
            }
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
            Dimension Dim = Toolkit.getDefaultToolkit().getScreenSize();
            IHM.setLocation(Dim.width/2-IHM.getSize().width/2,Dim.height/2-IHM.getSize().height/2);
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
    public File SelectOutput(){
        File Return = null;
        JFileChooser Chooser = new JFileChooser(System.getProperty("user.home")+"/Desktop");
        Chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if(Chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
            Return = Chooser.getSelectedFile();
        }
        return Return;
    }
    public File SelectOutput(String From){
        File Return = null;
        JFileChooser Chooser = new JFileChooser(From);
        Chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
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
    public String GetOutputPath(File F){
        String Path="";
        if(F!=null && !F.exists()){
            try {Path = F.getCanonicalPath();} catch (IOException ex) {}
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
        if(F!=null && F.exists() && F.isFile() && F.getName().toLowerCase().endsWith(".txt")){
            String Content = ReadFile(F);
            if(Content.length()>10){
                String[] Blocks = TextToBlock(Content);
                if(Blocks.length>0){
                    String[][] TempReturn = new String[Blocks.length][5];
                    int EmtyBlock=0;
                    for(int i=0;i<Blocks.length;i++){
                        TempReturn[i]=BlockToData(Blocks[i]);
                        if(TempReturn[i]==null){EmtyBlock++;}
                    }
                    if(EmtyBlock>0){
                        Return = new String[TempReturn.length-EmtyBlock][5];
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
            IHM.AddValue((int)F.length());
        }
        else if(F.exists() && F.isDirectory()){
            for(File Fi : F.listFiles(OnlyTxt)){
                String[][] Temp = FormatFiles(Fi);
                if(Temp!=null){
                    if(Return==null){
                        Return = Temp;
                    }
                    else{
                        String[][] ReturnTmp = new String[Return.length+Temp.length][5];
                        for(int i=0;i<Return.length;i++){
                            ReturnTmp[i] = Return[i];
                        }
                        for(int i=Return.length;i<ReturnTmp.length;i++){
                            ReturnTmp[i] = Temp[i-Return.length];
                        }
                        Return = ReturnTmp;
                    }
                }
                IHM.AddValue((int)Fi.length());
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
    public String ReadFile(InputStream File){
        String Content = "";
            try {
                BufferedReader Br = new BufferedReader(new InputStreamReader(File));
                String Line;
                while((Line=Br.readLine())!=null){
                    Content = Content + Line + "\n";
                }   
                Br.close();
            } catch (IOException ex) {}
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
        String[] Data = new String[5];
        if(Block!=null && Block.length()>0 && Block.contains("\n")){
            String[] Splitted = Block.split("\n");
            for(String St: Splitted){
                String[] Dat = St.split(":");
                if(Dat.length>=2){
                    Dat[0] = Dat[0].toLowerCase().replace(" ", "");
                    if(Dat[0].contains("url") && Dat[1].contains("http")){
                        Dat[2]=Dat[2].substring(2).replace("www.", "");
                        if(Dat[2].contains("/")){
                            Data[1] = Dat[1]+":"+Dat[2].substring(0, Dat[2].indexOf("/"));
                        }
                        else{
                            Data[1]=Dat[2];
                        }
                        while(Data[1].startsWith(" ")){Data[1]=Data[1].substring(1);}
                        Data[1] = Data[1].replace("http://", "").replace("https://", "").replace("www.", "");
                        Data[1] = Data[1].replace("http:", "").replace("https:", "");
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
            if(Data[1]!=null){
                String[] S = GetType(Data[1]);
                Data[0]=S[0];
                Data[4]=S[1];
            }
        }
        if(Data[1]==null || Data[2]==null || Data[3]==null || Data[4]==null || Data[3].length()<2 || Data[2].length()<2 ||Data[1].length()<2){Data=null;}
        return Data;
    }
    public String[] GetType(String Url){
        String Type = "AUTRE";
        String Site = "AUTRE";
        Url = Url.toLowerCase();
        
        if(Type == "AUTRE"){
            String Content = ReadFile(this.getClass().getResourceAsStream("Data.dat"));
            if(Content.contains("\n")){
                for(String S : Content.split("\n")){
                    if(S.contains(":")){
                        String[] Si = S.split(":");
                        if(Si.length>=2){
                            if(Url.contains(Si[1].toLowerCase())){
                                Type=Si[0].toUpperCase();
                                Site = Si[1].toLowerCase();
                            }
                        }
                    }
                }
            }
        }
        if(Type == "AUTRE"){
            if(Url.equals("wifi")){Type="WIFI";Site="wifi";}
            if(Url.contains("leboncoin")){Type="MAGASIN";}
            if(Url.contains("intersport")){Type="MAGASIN";}
            if(Url.contains("edf")){Type="DATA";}
            if(Url.contains("sfr")){Type="INTERNET";}
            if(Url.contains("orange")){Type="INTERNET";}
            if(Url.contains("bouygues")){Type="INTERNET";}
            if(Url.contains("nrjmobile")){Type="INTERNET";}
            if(Url.contains("generation")){Type="SANTE+";}
            if(Url.contains("cofidis")){Type="BANQUE";}
            if(Url.contains("facebook")){Type="SOCIAL";}
            if(Url.contains("maaf")){Type="SANTE";}
            if(Url.contains("ameli")){Type="AMELI";}
            if(Url.contains("caf")){Type="CAF";}
            if(Url.contains("doctolib")){Type="SANTE";}
            if(Url.contains("lassuranceretraite")){Type="SANTE";}
            if(Url.contains("moncompteformation")){Type="GOUV";}
            if(Url.contains("natixis")){Type="BANQUE";}
            if(Url.contains("impots")){Type="IMPOTS";}
            if(Url.contains("moncompteactivite")){Type="GOUV";}
            if(Url.contains("lidl")){Type="MAGASIN";}
            if(Url.contains("cofidis")){Type="BANQUE";}
            if(Url.contains("fruitz")){Type="RENCONTRES";}
            if(Url.contains("discord")){Type="SOCIAL";}
            if(Url.contains("google")){Type="MAIL+";}
            if(Url.contains("instagram")){Type="SOCIAL";}
            if(Url.contains("canalplus")){Type="TV+";}
            if(Url.contains("gmf")){Type="ASSURANCE";}
            if(Url.contains("bearwww2")){Type="RENCONTRES";}
            if(Url.contains("gaytryst")){Type="RENCONTRES";}
            if(Url.contains("grindr")){Type="RENCONTRES";}
            if(Url.contains("mongars")){Type="RENCONTRES";}
            if(Url.contains("snapchat")){Type="SOCIAL";}
            if(Url.contains("uber")){Type="MAGASIN+";}
            if(Url.contains("darty")){Type="MAGASIN";}
            if(Url.contains("leclerc")){Type="MAGASIN";}
            if(Url.contains("leroymerlin")){Type="MAGASIN";}
            if(Url.contains("bricorive")){Type="MAGASIN";}
            if(Url.contains("cdiscount")){Type="MAGASIN+";}
            if(Url.contains("amazon")){Type="MAGASIN+";}
            if(Url.contains("carrefour")){Type="MAGASIN";}
            if(Url.contains("netflix")){Type="TV+";}
            if(Url.contains("redtube")){Type="PORN";}
            if(Url.contains("xtube")){Type="PORN";}
            if(Url.contains("cam4")){Type="PORN";}
            if(Url.contains("xvideos")){Type="PORN";}
            if(Url.contains("placementdirect")){Type="BANQUE";}
            if(Url.contains("rbcdirects")){Type="BANQUE";}
            if(Url.contains("macif")){Type="ASSURANCE";}
            if(Url.contains("hellobank")){Type="BANQUE";}
            if(Url.contains("faphouse")){Type="PORN";}
            if(Url.contains("castorama")){Type="MAGASIN";}
            if(Url.contains("ikea")){Type="MAGASIN";}
            if(Url.contains("gulli")){Type="TV";}
            if(Url.contains("boursorama")){Type="MAGASIN";}
        }
        String[] S = {Type,Site};
        return S;
    }
    public String[][] RemoveDouble(String[][] Data){
        String[][] Return = Data;
        int Double = 0;
        boolean Doubleb=false;
        for(int i=0;i<Data.length-1;i++){
            if(Data[i][1]==null || Data[i][2].length()<=1){
                Double++;
            }
            else{
                Doubleb=false;
                for(int j=i+1;j<Data.length;j++){
                    if(Data[i][4].toLowerCase().equals(Data[j][4].toLowerCase())){
                        if(Data[i][2].toLowerCase().equals(Data[j][2].toLowerCase())){
                            Doubleb=true;
                        }
                    }
                }
                if(Doubleb){Double++;}
            }
            
        }
        if(Double>0){
            Return = new String[Data.length-Double][5];
            Double=0;
            Doubleb=false;
            for(int i=0;i<Data.length;i++){
                Doubleb = false;
                for(int j=i+1;j<Data.length;j++){
                    if(Data[i][4].toLowerCase().equals(Data[j][4].toLowerCase())){
                        if(Data[i][2].toLowerCase().equals(Data[j][2].toLowerCase())){
                            Doubleb=true;
                        }
                    }
                    if(Data[i][1]==null || Data[i][1].length()<1){
                        Doubleb=true;
                    }
                }
                if(!Doubleb){
                    Return[Double]=Data[i];
                    Double++;
                }
            }
        }
        this.Tableau=Return;
        return Return;
    }
    public boolean WriteFile(String Content, File F){
        boolean Validite = false;
        if(F!=null && !F.exists()){
            try {
                F.createNewFile();
                FileWriter Fw = new FileWriter(F);
                Fw.write(Content);
                Fw.close();
                Validite = true;
            } catch (IOException ex) {
                System.err.println(ex.getLocalizedMessage());
            }
        }
        return Validite;
    }
    public boolean Save(File F){
        boolean Validite = false;
        String Content = "";
        if(Tableau.length>0){
            for(String[] Line : Tableau){
                for(String S : Line){
                    Content = Content+S+";";
                }
                if(Content.endsWith(";")){
                    Content = Content.substring(0,Content.length()-1);
                }
                Content = Content+"\n";
            }
            if(Content.endsWith("\n")){
                Content = Content.substring(0,Content.length()-1);
            }
            Validite = WriteFile(Content, F);
        }
        return Validite;
    }
}
