/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnlineBankingApp.newpackage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 *
 * @author masbahuddin
 */
public class User {
 
    private String fName;
    private String lName;
    private String uid;
    private byte pinhash[]; 
    
    private ArrayList<Account> accounts;
    
    public User(String fName, String lName, String pin, Bank bank)
    {
        this.fName = fName;
        this.lName = lName;
        
        try
        {    
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinhash = md.digest(pin.getBytes());
        }
        catch(NoSuchAlgorithmException e)
        {
            System.out.println("Error");
            e.printStackTrace();
            System.exit(1);
        }
        
        this.uid = bank.getNewUserUid();
        
        this.accounts = new ArrayList<Account>();
        
        System.out.printf("New User %s, %s with ID %s created. \n", fName, lName,this.uid);
    }
    
    public void addAccount(Account acc)
    {
        this.accounts.add(acc);
    }
    
    public String getUid()
    {
        return this.uid;
    }
    
    public boolean validatePin(String pin)
    {
        try
        {    
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(pin.getBytes()), this.pinhash);
        }
        catch(NoSuchAlgorithmException e)
        {
            System.out.println("Error");
            e.printStackTrace();
            System.exit(1);
        }
        
        return false;
    }
    
    public String getFirstName()
    {
        return this.fName;
    }
    
    public  void printAccountSummary()
    {
        System.out.printf("\n\n%s's Account Summary\n", this.fName);
        
        for(int i = 0; i< this.accounts.size(); i++)
        {
            System.out.printf("%d) %s\n", i+1, this.accounts.get(i).getSummary());
        }
        
        System.out.println("");
    }
    
    public int numAccounts()
    {
        return this.accounts.size();
    }
    
    public void printTransActionHistory(int index)
    {
        this.accounts.get(index).printHistory();
    }
    
    public double getAccountBalance(int index)
    {
        return this.accounts.get(index).getBalance();
    }
    
    public String getAccUID(int index)
    {
        return this.accounts.get(index).getUid();
    }
    
    public void addAccountTransaction(int index, double amount, String memo)
    {
        this.accounts.get(index).addTransactions(amount , memo);
    }
    
}
