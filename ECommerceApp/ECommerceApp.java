//package ecommerceapp;

import java.util.Scanner;

public class ECommerceApp {

    public static void bannerPrinter() {
        System.out.println("******************************************");
        System.out.println("====== Welcome to my eCommerce app! ======");
        System.out.println("******************************************");
}
    public static String productsBuilder() {
        String productsCatalog = "Microphone " + "Computer " + "Keyboard ";
        return productsCatalog;    
    }
    
    public static boolean getOrder(String productsCatalog) {
        System.out.print("Search catalog for : ");
        Scanner scnr = new Scanner(System.in);
        String search = scnr.nextLine();
        
        if (productsCatalog.contains(search)) {
            return true;
        }
        else {
            System.out.println("The product was not found.");
            System.exit(0);
            return false;
        }
    }
    
    public static double getPrice() {
      double price = (double) (Math.random(100) * 100) + 1;
      return price;
    }
    
    public static double getTax(double price) {
        double tax = .10 * price;
        return tax;
    }
    
    public static double getTotal(double price, double tax) {
        double saleTotal = price + tax;
        return saleTotal;
    }
    
    public static void printTotal(double saleTotal) {
        System.out.print("Your sale total is: $");
        System.out.printf("%.2f", saleTotal);
        System.out.println("");
    }
    

    public static void main(String[] args) {
        bannerPrinter();
        getOrder(productsBuilder());
        printTotal(getTotal(getTax(getPrice()), getPrice()));
    }
    
}