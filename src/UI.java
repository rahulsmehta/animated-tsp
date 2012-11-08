import squint.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;


public class UI{
    public static void main(String[] args){
        
        JFileChooser chooser = new JFileChooser( new File( System.getProperty( "user.dir" ) ));
        String filename="";
        if (chooser.showOpenDialog(chooser)==JFileChooser.APPROVE_OPTION ){
            filename = chooser.getSelectedFile().getAbsolutePath(); 
        }
        DynamicGUI G=new DynamicGUI(filename);
    }
}