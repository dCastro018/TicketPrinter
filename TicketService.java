package com.lasn;

public class TicketService{
    private int ticketNumber = 1;
    public int restartNumber;
    public int specificNumber;

    public void SetTicketNumber(int ticketNum){
       ticketNumber = ticketNum;
    }
    public int GetTicketNumber(){
        return ticketNumber;
    }

    public void IncreasingTicketNumber(){
        if (ticketNumber >= 100) {
            ticketNumber = 0;
        }
        ticketNumber++;
    }
}