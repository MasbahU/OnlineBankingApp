/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnlineBankingApp.newpackage;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author masbahuddin
 */
public class Bank {
    
    private String name;
    private ArrayList<User>user;
    private ArrayList<Account> account;
    
    public Bank(String name)
    {
        this.name = name;
        this.user = new ArrayList<User>();
        this.account = new ArrayList<Account>();
    }
    
    public String getNewUserUid()
    {
        String uid;
        Random r = new Random();
        int len = 6;
        boolean notUnique;
        
        do
        {
            uid="";
            for(int i=0; i< len; i++)
            {
                uid+= ((Integer)r.nextInt(10)).toString();
            }
            notUnique = false;
            for(User u : this.user)
            {
                if(uid.compareTo(u.getUid()) == 0 )
                {
                    notUnique = true;
                    break;
                }
            }
        }
        while(notUnique);

        return uid;
    }
    
    public String getNewAccoundUid()
    {
        String uid;
        Random r = new Random();
        int len = 10;
        boolean notUnique;
        
        do
        {
            uid="";
            for(int i=0; i< len; i++)
            {
                uid+= ((Integer)r.nextInt(10)).toString();
            }
            notUnique = false;
            for(Account a : this.account)
            {
                if(uid.compareTo(a.getUid()) == 0 )
                {
                    notUnique = true;
                    break;
                }
            }
        }
        while(notUnique);

        return uid;
    }
    
    public void addAccount(Account acc)
    {
        this.account.add(acc);
    }
    
    public User addUser(String fName, String lName, String pin)
    {
        User newUser = new User(fName, lName, pin, this);
        this.user.add(newUser);
        
        Account newAcc = new Account("Savings", newUser, this);
        newUser.addAccount(newAcc);
        this.addAccount(newAcc);
        
        return newUser;
    }
    
    public User userlogin(String userID, String pin)
    {
        for (User u : this.user)
        {
            if(u.getUid().compareTo(userID) == 0 && u.validatePin(pin));
            return u;
        }
        
        return null;
    }
    
    public String getName()
    {
        return this.name;
    }
}

