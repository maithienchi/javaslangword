/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slangword;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 *
 * @author ASUS
 */
public class slangwordDao {
    public static String slang_path = "slang.txt";
    public static String save_path = "save_path.txt";
    public static String search_history_path = "search_history_path.txt";
    public static String add_history_path = "add_history_path.txt";
    public static String edit_history_path = "edit_history_path.txt";
    public static String delete_history_path = "delete_history_path.txt";
    public List<slang> slaList;
    public static Scanner scanner = new Scanner(System.in);
    
    public List<slang> read(){
        List<slang> slangList = new ArrayList<slang>();
        try {
            File f = new File(slang_path);
            if(f.exists() && !f.isDirectory()) { 
            }
            else{
                System.out.println("Nhap vao lien ket den file slang.txt: ");
                slang_path= scanner.nextLine();
            }
            FileInputStream fstream = new FileInputStream(slang_path); 
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));// tham khao nguon https://stackoverflow.com/questions/14657997/reading-from-file-and-splitting-the-data-in-java
            String strLine;
            while ((strLine = br.readLine()) != null) {
                String[] splitOut = strLine.split("`");
                if (splitOut.length == 2) {
                    slangList.add(new slang(splitOut[0], splitOut[1]));
                } else {
                    System.out.println("Invalid class: " + strLine);
                }
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return slangList;
    }
    
    
    public void write(List<slang> slangList){
//        FileOutputStream fos = null;
//        ObjectOutputStream oos = null;
//        try {
//            fos = new FileOutputStream(new File("save_path.txt"));
//            oos = new ObjectOutputStream(fos);
//            oos.writeObject(slangList);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }catch (IOException e){
//            e.printStackTrace();            
//        } finally {
//            closeStream(fos);
//            closeStream(oos);
//        }
        try {
            FileOutputStream fstream = new FileOutputStream(save_path);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fstream));
            for (slang s : slangList) {
            bw.write(s.getKey()+"`"+s.getMean());
            bw.newLine();
        }
        bw.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    
    
}
