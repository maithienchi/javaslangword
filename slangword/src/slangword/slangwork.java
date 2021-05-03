/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slangword;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class slangwork {
    
    public static Scanner scanner = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String choose = null;
        boolean exit = false;
        slangmanager slangManager = new slangmanager();
        Scanner scanner = new Scanner(System.in);
        int slangId;
 
        // show menu
        showMenu();
        while (true) {
            choose = scanner.nextLine();
            switch (choose) {
            case "1":
                System.out.println("Nhap vao key can tim: ");
                String key = scanner.nextLine();
                slangManager.TimKiemTheoSlangWord(key);
                break;
            case "2":
                System.out.println("Nhap vao key can tim: ");
                key = scanner.nextLine();
                List<String> tam = new ArrayList<>();
                tam = slangManager.TimKiemTheoDefition(key);
                System.out.println("Ket qua: ");
                System.out.println(tam);
                break;
            case "3":
                System.out.println("Lich su tim kiem: ");
                slangManager.show_search_history();
                break;
            case "4":
                slangManager.AddNewSlangWord();
                break;
            case "5":
                
            break;
            case "6":
//                
                break;
            case "7":
//                
                break;
            case "8":
//                
                break;
            case "9":
                
                break;
            case "10":
                
                break;
            case "11":
                slangManager.show();
                break;
            case "0":
                System.out.println("Thoat!!");
                exit = true;
                break;
            default:
                System.out.println("nhap sai! vui long chon dung hanh dong duoi menu:");
                break;
            }
            if (exit) {
                break;
            }
            // show menu
            showMenu();
        }
    }
    /**
     * create menu
     */
    public static void showMenu() {
        System.out.println("-----------menu------------");
        System.out.println("1. Tim kiem theo slang word.");
        System.out.println("2. tim kiem theo definition, hien thi tat ca slang word ma trong defintion co chua keyword go vao..");
        System.out.println("3. Hien thi lich su tim kiem.");
        System.out.println("4.  add 1 slang words moi. Neu slang words trung thi thong bao cho nguoi \n" +
"dung, confirm co overwrite hay duplicate ra 1 slang word moi..");
        System.out.println("5. edit 1 slang word.");
        System.out.println("6. delete 1 slang word. Confirm truoc khi xoa.");
        System.out.println("7. reset danh sach slang words goc..");
        System.out.println("8. random 1 slang word (On this day slang word).");
        System.out.println("9. Chuc nang do vui, chuong trinh hien thi 1 random slang word, voi 4 dap an cho \n" +
"nguoi dung chon.");
        System.out.println("10. Chuc nang do vui, chuong trinh hien thi 1 definition, với 4 slang words dap an cho \n" +
"nguoi dung chon.");
        System.out.println("11. Show all slang..");
        System.out.println("0. Thoat.");
        System.out.println("---------------------------");
        System.out.print("Nhap Lua chon: ");
    }
}
