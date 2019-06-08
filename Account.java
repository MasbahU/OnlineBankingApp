/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnlineBankingApp.newpackage;

import java.util.ArrayList;

/**
 *
 * @author masbahuddin
 */
public class Account {
    
    private String name;
    private String uid;
    private User holder;
    
    private ArrayList<Transaction> trans;
    
    public Account(String name, User user, Bank bank)
    {
        this.name = name;
        this.holder = holder;
        
        this.uid = bank.getNewAccoundUid();
        
        this.trans = new ArrayList<Transaction>();
                
    } 
    
    public String getUid()
    {
        return this.uid;
    }
    
    public String getSummary()
    {
        double balance = this.getBalance();
        
        if(balance >= 0)
            return String.format("%s : $%.02f : %s", this.uid, balance, this.name);
        else
            return String.format("%s : $(%.02f) : %s", this.uid, balance, this.name);
    }
    
    public double getBalance()
    {
        double bal = 0;
        
        for(Transaction  t : this.trans)
            bal += t.getTotalBalance();
        
        return bal;
    }
    
    public void printHistory()
    {
        System.out.printf("\nTransaction History For an Account &s\n", this.uid);
        
        for(int i = this.trans.size()-1; i >= 0; i--)
        {
            System.out.println(this.trans.get(i).getSummaryLine());
        }
    }
    
    public void addTransactions(double amount, String memo)
    {
        Transaction newTransA = new Transaction(amount, memo, this);
        this.trans.add(newTransA);
        
    }
    
}
