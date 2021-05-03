/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slangword;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 *
 * @author ASUS
 */
public class slangmanager {
    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();
    private List<slang> slangList;
    private List<String> search_History_List;
    private slangwordDao slangwordDao;
    
    public slangmanager() {
        slangwordDao = new slangwordDao();
        slangList = slangwordDao.read();
        search_History_List = slangwordDao.read_search_history_path();
    }
    public void show() {
        for (slang p: slangList){
            System.out.println(p.toString());
        }
    }
    public void show_search_history() {
        for (String p: search_History_List){
            System.out.println(p.toString());
        }
    }
    public void TimKiemTheoSlangWord(String slangword){
        int dem = 0;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//      System.out.println(formatter.format(date));
//        String tam="";
        for(slang p: slangList){
            if(p.getKey().compareTo(slangword)==0){
                System.out.println("key: "+p.getKey()+"\n"+ "mean: "+p.getMean());
                dem++;
//                tam = tam + " Tim kiem key: "+p.getKey() + "mean: "+p.getMean();
            }
        }
        search_History_List.add(formatter.format(date)+" Tim kiem key: "+slangword);
        slangwordDao.write_search_history(search_History_List);
        if(dem==0){
            System.out.println("key not found, please input another key!!!");
        }
    }
    public List<String> TimKiemTheoDefition(String str){
        int dem = 0;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//      System.out.println(formatter.format(date));0
        List<String> listWord = new ArrayList<>();
        for(slang p: slangList){
//            for(int i=0;i<str.length();i++){
//                if(p.getKey().indexOf(str.charAt(i))>=0){
//                    
//                }
//            }
            if(p.getKey().indexOf(str)>=0){
//                System.out.println("key: "+p.getKey()+"\n"+ "mean: "+p.getMean());
                listWord.add(p.getKey());
                dem++;
            }    
        }
        search_History_List.add(formatter.format(date)+ " Tim kiem key Theo Defition: " + str);
        slangwordDao.write_search_history(search_History_List);
        if(dem==0){
            System.out.println("key not found, please input another key!!!");
        }
        return listWord;
    }
    public void editSlangWord(){
    System.out.println("Nhap vao key: ");
    String key = scanner.nextLine();
    String listWord = "";
    for(int i=0;i<slangList.size();i++){
        if(slangList.get(i).getKey().compareTo(key)==0){
            System.out.println("key: "+slangList.get(i).getKey()+"\n"+ "mean: "+slangList.get(i).getMean());
            System.out.println("Nhap vao mean can thay doi: ");
            System.out.println("Nhap vao so luong mean: ");
            int soluong = Integer.parseInt(scanner.nextLine());
            for (int j=0;j<soluong;j++){
                System.out.println("Nhap vao mean thu "+((int)(j+1))+": ");
                if(j==0){
                    listWord += scanner.nextLine();
                }
                else{
                    listWord += " | "+scanner.nextLine();
                }
                
            }
            slangList.get(i).setMean(listWord);
            slangwordDao.emtyfile("edit_history_path.txt");
            slangwordDao.writeEditSlang(slangList);
            System.out.println("change done");
        }
    }
    if(key==""){
        System.out.println("key not found, please input another key!!!");
    }
    System.out.println("key: "+key+"\n"+ "mean: "+listWord);
    }
    
}
