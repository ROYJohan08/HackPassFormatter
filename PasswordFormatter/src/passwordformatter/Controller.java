/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package passwordformatter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author FixeJohan
 */
public class Controller {
    public String[] ListTxtFiles(String Path){
        String[] FilesList = null;
        if(!Path.endsWith("/") && !Path.endsWith("\\")){
            Path = Path + "\\";
        }
        File Directory = new File(Path);
        if(Directory.exists() && Directory.isDirectory()){
            int Cpt = 0;
            File[] Files = Directory.listFiles();
            for(File File : Files){
                if(File.getName().endsWith(".txt")){
                    Cpt++;
                }
            }
            FilesList = new String[Cpt];
            Cpt=0;
            for(File File : Files){
                if(File.getName().endsWith(".txt")){
                    FilesList[Cpt]=File.getName();
                    Cpt++;
                }
            }
        }
        return FilesList;
    }
    public String ReadFile(String Path) throws FileNotFoundException, IOException{
        String Content = "";
        File File = new File(Path);
        if(File.exists() && File.isFile() && File.getName().endsWith(".txt")){
            BufferedReader Br = new BufferedReader(new FileReader(File));
            String Line;
            while((Line=Br.readLine())!=null){
                Content = Content + Line + "\n";
            }
            Br.close();
        }
        
        if(Content.endsWith("\n")){
            Content = Content.substring(0,Content.length()-1);
        }
        if(Content.contains(""+(char)0)){
            Content = Content.replace(""+(char)0, "");
        }
        
        return Content;
    }
    public String[] TextToBlock(String Content){
        String[] Blocks = null;
        Content = Content.replace("\n\n", "\n");
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
        return Blocks;
    }
    public String[] BlockToData(String Block){
        String[] Data = new String[4];
        String[] Splitted = Block.split("\n");
        for(String St: Splitted){
            String[] Dat = St.split(":");
            if(Dat.length>=2){
                Dat[0] = Dat[0].toLowerCase().replace(" ", "");
                
                if(Dat[0].contains("url") && Dat[1].contains("http")){
                    Data[1] = Dat[1]+":"+Dat[2].substring(0, Dat[2].substring(2).indexOf("/")+2);
                    while(Data[1].startsWith(" ")){Data[1]=Data[1].substring(1);}
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
        if(Data[3].length()<2){Data=null;}
        return Data;
    }
    public String GetType(String Url){
        String Type = "AUTRE";
        if(Url.equals("WIFI")){Type="WIFI";}
        if(Url.contains("leboncoin")){Type="MAGASIN";}
        if(Url.contains("intersport")){Type="MAGASIN";}
        if(Url.contains("edf")){Type="DATA";}
        return Type;
    }
    public boolean WriteFile(String Path, String Content) throws IOException{
        boolean Validite = false;
        File F = new File(Path);
        if(!F.exists()){
            F.createNewFile();
            PrintWriter Pw = new PrintWriter(F);
            Pw.write(Content);
            Pw.close();
            Validite=true;
        }
        return Validite;
    }
}
