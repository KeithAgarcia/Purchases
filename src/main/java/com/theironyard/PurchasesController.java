package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Keith on 5/10/17.
 */

@Controller
public class PurchasesController {
    @Autowired
    PurchaseRepository purchases;

    @Autowired
    CustomerRepository customers;

    @PostConstruct
    public void init(){
        if(customers.count() == 0){
            File customerFile = new File("customers.csv");
            try {
                Scanner fileScanner = new Scanner(customerFile);
                fileScanner.nextLine();

                while(fileScanner.hasNext()){
                    String line = fileScanner.nextLine();
                    String[] columns = line.split("\\,");
                    Customer customer = new Customer(columns[0], columns[1]);
                    customers.save(customer);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            File purchaseFile = new File("purchases.csv");
            try {
                Scanner fileScanner = new Scanner(purchaseFile);
                fileScanner.nextLine();

                while (fileScanner.hasNext()){
                    String line = fileScanner.nextLine();
                    String[] columns = line.split("\\,");
                    int id = Integer.valueOf(columns[0]);

                    Customer customer = customers.findOne(id);

                    Purchase purchase = new Purchase(columns[1], columns[2], Integer.valueOf(columns[3]), columns[4], customer);
                    purchases.save(purchase);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(HttpSession session, Model model, String category){
        List<Purchase> purchaseList;

        if (category != null){
            purchaseList = purchases.findByCategory(category);
        } else {
            purchaseList = (List)purchases.findAll(); //findAll returns a iterable which is why we have (list) in front.
        }
        model.addAttribute("purchases", purchaseList);
        return "home";
    }


}
