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
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");//get time now  tham khao https://stackabuse.com/how-to-get-current-date-and-time-in-java/
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
            if(p.getKey().indexOf(str)>=0){ // so sanh gia tri co trong chuoi
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
    public void AddNewSlangWord(){
        System.out.println("Nhap vao 1 slangword moi: ");
        System.out.println("Nhap vao key: ");
        String key = scanner.nextLine();
        String mean = "";
        int dem=0;
        for (int i = 0; i<slangList.size();i++){
            if (slangList.get(i).getKey().compareTo(key)==0){
                System.out.println("trong list Slang hien da co "+key);
                System.out.println("\n Ban muon ghi de hay nhan ban no len");
                System.out.println("\n1. duplicate(nhan ban) \n2.overwrite(ghi de) ");
                System.out.println("\n Nhap vao lua chon: ");
                int chon = Integer.parseInt(scanner.nextLine());
                if(chon==1){
                    System.out.println("Nhap vao mean: : ");
                    System.out.println("Nhap vao so luong mean: ");
                    int soluong = Integer.parseInt(scanner.nextLine());
                    for (int j=0;j<soluong;j++){
                        System.out.println("Nhap vao mean thu "+((int)(j+1))+": ");
                        if(j==0){
                            mean += scanner.nextLine();
                        }
                        else{
                            mean += " | "+scanner.nextLine();
                        }

                    }
                    slang tam = new slang(key, mean);
                    slangList.add(tam);
                    slangwordDao.emtyfile("add_history_path.txt");
                    slangwordDao.writeAddSlang(slangList);
                    dem++;
                }
                else if(chon==2){
                    System.out.println("Nhap vao mean: : ");
                    System.out.println("Nhap vao so luong mean: ");
                    int soluong = Integer.parseInt(scanner.nextLine());
                    for (int k=0;k<soluong;k++){
                        System.out.println("Nhap vao mean thu "+((int)(k+1))+": ");
                        if(k==0){
                            mean += scanner.nextLine();
                        }
                        else{
                            mean += " | "+scanner.nextLine();
                        }

                    }
                    slangList.get(i).setMean(mean);
                    slangwordDao.emtyfile("add_history_path.txt");
                    slangwordDao.writeAddSlang(slangList);
                    dem++;
                }
                break;
            }
        }
        if(dem==0){
            System.out.println("Nhap vao mean: : ");
            System.out.println("Nhap vao so luong mean: ");
            int soluong = Integer.parseInt(scanner.nextLine());
            for (int i=0;i<soluong;i++){
                System.out.println("Nhap vao mean thu "+((int)(i+1))+": ");
                if(i==0){
                    mean += scanner.nextLine();
                }
                else{
                    mean += " | "+scanner.nextLine();
                }

            }
            slang tam = new slang(key, mean);
            slangList.add(tam);
            slangwordDao.emtyfile("add_history_path.txt");
            slangwordDao.writeAddSlang(slangList);
        }
        System.out.println("Add done!!!");
        
    }
    public void DeleteSlangWord(){
        System.out.println("Nhap vao key cua mot slang can xoa: ");
        String key = scanner.nextLine();
        System.out.println("Xac nhan: y , tu choi: n nhap answer(y/n)");
        String chon = scanner.nextLine();
        if (chon.compareTo("y")==0){
            for(int i=0;i<slangList.size();i++){
                if(slangList.get(i).getKey().compareTo(key)==0){
                    slangList.remove(i);
                    slangwordDao.emtyfile("delete_history_path.txt");
                    slangwordDao.writeDeleteSlang(slangList);
                    System.out.println("Done");
                }
            }  
        }
        
    }
    public void ResetRootSlangWord(){
        slangList.clear();
        slangList = slangwordDao.read();
    }
    public void RandomSlangWord(){
//        int x;
//        List<Integer> listInt = new ArrayList<Integer>();
//        int size = slangList.size();
//        for (int i=slangList.size()-1;i>=0;i--){
//            if(i)
//            x = random.nextInt(size);
//            slang y = slangList.get(x);
//            slangList.get(x).setKey(slangList.get(i).getKey());
//            slangList.get(x).setMean(slangList.get(i).getMean());
//            slangList.get(i).setKey(y.getKey());
//            slangList.get(i).setMean(y.getMean());
//            size--;
//         
//        }
        slang[] arr = new slang[slangList.size()];//tam mang moi chua danh sach slangword
            for (int i = 0; i < arr.length; i++) {
                arr[i] = slangList.get(i);
            }
        Collections.shuffle(Arrays.asList(arr));//tao random slang word nguon https://stackoverflow.com/questions/1519736/random-shuffling-of-an-array
        slangList.clear();
        for(int i = 0 ;i < arr.length ;i++){
            slangList.add(arr[i]);
        }
//        for (int i=0;i<slangList.size();i++){
//            slang y = slangList.get(i); //tam = a
//            slangList.get(i).setKey(slangList.get(arr[i]).getKey()); // a= b
//            slangList.get(i).setMean(slangList.get(arr[i]).getMean());
//            slangList.get(arr[i]).setKey(y.getKey());// b= tam
//            slangList.get(arr[i]).setMean(y.getMean());
//        }
        System.out.println("Done");
    }
    public void Cau9(){
        System.out.println("chon dap an dung trong 4 dap an: ");
        slang tam = slangList.get(random.nextInt(slangList.size()));
        List<String> listAns = new ArrayList<String>();
        int dem = 0;
        for (slang p: slangList){
            if(p.getKey().compareTo(tam.getKey())!=0){
                listAns.add(p.getMean());
                dem++;
            }
            if(listAns.size()==3){
                break;
            }
        }
        
        List<String> listDapAn = new ArrayList<>();
        listDapAn.add("a");
        listDapAn.add("b");
        listDapAn.add("c");
        listDapAn.add("d");
        System.out.println("Cho key nhu sau: "+tam.getKey());
        System.out.println("\n Co 4 dap an: ");
        String dapan = listDapAn.get(random.nextInt(listDapAn.size()));
        
        listDapAn.remove(dapan);
        String tam2="";
        System.out.println("\n"+dapan+") "+tam.getMean());
        tam2=listDapAn.get(random.nextInt(listDapAn.size()));
        System.out.println("\n"+tam2+") "+listAns.get(0));
        listDapAn.remove(tam2);
        tam2=listDapAn.get(random.nextInt(listDapAn.size()));
        System.out.println("\n"+tam2+") "+listAns.get(1));
        listDapAn.remove(tam2);
        tam2=listDapAn.get(random.nextInt(listDapAn.size()));
        System.out.println("\n"+tam2+") "+listAns.get(2));
        listDapAn.remove(tam2);
        
        System.out.println("\n Nhap vap dap an: ");
        String chon = scanner.nextLine();
        if(chon.compareTo(dapan)==0){
            System.out.println("Ban da chien thang Congratulation");
        }
        else
            System.out.println("Ban da thua cuoc :(((");
    }
    public  void Cau10(){
        System.out.println("chon dap an dung trong 4 dap an: ");
        slang tam = slangList.get(random.nextInt(slangList.size()));
        List<String> listAns = new ArrayList<String>();
        int dem = 0;
        for (slang p: slangList){
            if(p.getKey().compareTo(tam.getKey())!=0){
                listAns.add(p.getKey());
                dem++;
            }
            if(listAns.size()==3){
                break;
            }
        }
        
        List<String> listDapAn = new ArrayList<>();
        listDapAn.add("a");
        listDapAn.add("b");
        listDapAn.add("c");
        listDapAn.add("d");
        System.out.println("Cho definetion nhu sau: "+tam.getMean());
        System.out.println("\n Co 4 dap an: ");
        String dapan = listDapAn.get(random.nextInt(listDapAn.size()));
        listDapAn.remove(dapan);
        String tam2="";
        System.out.println("\n"+dapan+") "+tam.getKey());
        tam2=listDapAn.get(random.nextInt(listDapAn.size()));
        System.out.println("\n"+tam2+") "+listAns.get(0));
        listDapAn.remove(tam2);
        tam2=listDapAn.get(random.nextInt(listDapAn.size()));
        System.out.println("\n"+tam2+") "+listAns.get(1));
        listDapAn.remove(tam2);
        tam2=listDapAn.get(random.nextInt(listDapAn.size()));
        System.out.println("\n"+tam2+") "+listAns.get(2));
        listDapAn.remove(tam2);
        System.out.println("\n Nhap vap dap an: ");
        String chon = scanner.nextLine();
        if(chon.compareTo(dapan)==0){
            System.out.println("Ban da chien thang Congratulation");
        }
        else
            System.out.println("Ban da thua cuoc :(((");
    }
}
