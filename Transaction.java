/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnlineBankingApp.newpackage;

/**
 *
 * @author masbahuddin
 */
import java.util.Date;
public class Transaction {
    
    private double amount;
    private Date timestamp;  
    private String memo;
    private Account acc;
    
    public Transaction(double amount, Account acc)
    {
        this.amount = amount;
        this.acc = acc;
        this.timestamp = new Date();
        this.memo = "";
    }
    
    public Transaction(double amount,String memo, Account acc)
    {
        this(amount, acc);
        this.memo = memo;
    }
    
    public double getTotalBalance()
    {
        return this.amount;
    }
    
    public String getSummaryLine()
    {
        if(this.amount >= 0)
            return String.format("%s : $%.02f : %s", this.timestamp.toString(), this.amount, this.memo);
        else
            return String.format("%s : $(%.02f) : %s", this.timestamp.toString(), -this.amount, this.memo);
    }
    
    
}
