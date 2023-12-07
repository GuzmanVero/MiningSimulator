/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mined;

import Entity.User;
import java.io.Serializable;
import java.util.Date;

public class Transaction  implements Serializable {
    
    private int id;
    private long timeStamp;
    private String sender;
    private String receiver;
    private double amount;
    private  User user;
    
    public Transaction(int pId, String pSender, String pReceiver, double pAmount)
    {
      this.id=pId;  
      this.timeStamp= new Date().getTime();
      this.sender=pSender;
      this.receiver=pReceiver;
      this.amount=pAmount;
    }

      @Override
    public String toString() {
        return Integer.toString(id)+ Long.toString(timeStamp)+ sender+receiver+Double.toString(amount);
    }

    public int getId() {
        return id;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public double getAmount() {
        return amount;
    }
}
