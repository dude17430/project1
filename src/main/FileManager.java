package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by Dude on 12/13/2015.
 */
public class FileManager {

    private File file;

    public FileManager() throws IOException {
        file = new File("C:/Users/Dude/Desktop/myprogfile.txt");
        if(!file.exists()){
            writeNewFile();
        }
    }

    public String fetchVal(String key) throws FileNotFoundException {
        Scanner s = new Scanner(file);
        s.useLocale(Locale.ENGLISH);
        String temp1 = null;
        while(s.hasNext()){
            temp1 = s.next();
//            System.out.println("fetching: "+key+" comparing with: "+temp1);
            if(temp1.equals(key)){
                s.next();
                String temp2 = s.next();
                s.close();
                return temp2;
            }
        }
        System.out.println("failed to find "+key);
        s.close();
        return null;
    }

    public void setVal(String key, String val) throws IOException {
        String temp1 = fetchVal("AgilHS");
        String temp2 = fetchVal("StratMap1HS");
        String temp3 = fetchVal("StratMap2HS");
        System.out.println(temp2);
        FileWriter fw = new FileWriter(file, false);

        switch (key){
            case "AgilHS":
                fw.write(
                        "~~~Advance Space Program Data File~~~"+System.lineSeparator()+
                                "Version = 1.4"+System.lineSeparator()+
                                "AgilHS = "     +val+System.lineSeparator()+
                                "StratMap1HS = "+temp2+System.lineSeparator()+
                                "StratMap2HS = "+temp3);
                break;
            case "StratMap1HS":
                fw.write(
                        "~~~Advance Space Program Data File~~~"+System.lineSeparator()+
                                "Version = 1.4"+System.lineSeparator()+
                                "AgilHS = "     +temp1+System.lineSeparator()+
                                "StratMap1HS = "+val+System.lineSeparator()+
                                "StratMap2HS = "+temp3);
                break;
            case "StratMap12HS":
                fw.write(
                        "~~~Advance Space Program Data File~~~"+System.lineSeparator()+
                                "Version = 1.4"+System.lineSeparator()+
                                "AgilHS = "     +temp1+System.lineSeparator()+
                                "StratMap1HS = "+temp2+System.lineSeparator()+
                                "StratMap2HS = "+val);
                break;
        }
        fw.flush();
        fw.close();
    }

    private void writeNewFile() throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.write(
                "~~~Advance Space Program Data File~~~"+System.lineSeparator()+
                "Version = 1.4"+System.lineSeparator()+
                "AgilHS = 0"+System.lineSeparator()+
                "StratMap1HS = 0"+System.lineSeparator()+
                "StratMap2HS = 0");
        fw.flush();
        fw.close();
    }
}
