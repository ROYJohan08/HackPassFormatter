/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package passwordformatter;

import java.io.File;
import java.io.IOException;
public class PasswordFormatter {
    public static void main(String[] args) {
        if(args.length<1){
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
            Interface IHM = new Interface();
            IHM.setVisible(true);            
            
        }
        else{
            String TextFinal="";
            Controller Controll = new Controller();
            String[] Stt = Controll.ListTxtFiles(args[0]);
            for(String St : Stt){
                if(!args[0].endsWith("/") && !args[0].endsWith("\\")){args[0] = args[0] + "/";}
                String[] Files = Controll.ListTxtFiles(args[0]);
                for(String S : Files){
                    try {
                        String Contenu = Controll.ReadFile(args[0]+S);
                        if(Contenu.length()>0){
                            String[] Block = Controll.TextToBlock(Contenu);
                            if(Block.length>0){
                                for(String Blo:Block){
                                    String[] Data = Controll.BlockToData(Blo);
                                    if(Data!=null){
                                        TextFinal = TextFinal + Data[0]+";"+Data[1]+";"+Data[2]+";"+Data[3]+"\n";
                                    }
                                }
                            }
                        }
                    } catch (IOException ex) {
                        System.err.println(ex.getLocalizedMessage());
                    }
                }
            }
            
            File F = null;
            if(args.length>1){
                F = new File(args[1]);
            }
            else{
              F = new File(args[0]+"Data.txt");  
            }
            if(!F.exists()){
                try {
                    if(Controll.WriteFile(F.getCanonicalPath(), TextFinal)){
                        System.exit(0);
                    }
                    else{
                        System.exit(1);
                    }
                } catch (IOException ex) {
                    System.err.println(ex.getLocalizedMessage());
                }
            }
        }
    }
    
}
