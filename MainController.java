package com.lasn;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class MainController {
    private TicketService ticketService = new TicketService();
    private PrinterService printerService;

    public MainController() {
        this.printerService = new PrinterService(); // Initialize the PrinterService
    }

    @FXML
    private Button printButton;

    @FXML
    private Button restartButton;

    @FXML
    private TextField restartTextField;

    @FXML
    private Button specificNumberButton;

    @FXML
    private TextField specificNumberTextField;

    @FXML
    private Text ticketNumberText;

    @FXML
    void PrintButtonOnClick(ActionEvent event) {
        //prints number
        NumToPrinter(ticketService.GetTicketNumber());
        //increases number
        ticketService.IncreasingTicketNumber();
        if (ticketNumberText == null) {
            System.out.println("ticketNumberText is null");
        } else {
            ticketNumberText.setText("" + ticketService.GetTicketNumber());
        }
    }

    @FXML
    void restartOnClick(ActionEvent event) {
        String inputText = restartTextField.getText();
        try{
            int number = Integer.parseInt(inputText);
            ticketService.SetTicketNumber(number);
            ticketNumberText.setText("" + ticketService.GetTicketNumber() );
        }
        catch(NumberFormatException e){
            System.out.print("invalid number");
        }
    }

    @FXML
    void specificNumberOnClick(ActionEvent event) {
        NumToPrinter(Integer.parseInt(specificNumberTextField.getText()));
    }
    
    
    void NumToPrinter(int num) {    
        try {
            printerService.printLargeBoldNumber(String.valueOf(num));
            System.out.print("Printing number: " + num);
        } catch (Exception e) {
            System.out.print("Failed to print: " + e.getMessage());
            e.printStackTrace();
        }
    }

}