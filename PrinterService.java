package com.lasn;

import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.SimpleDoc;

public class PrinterService {

    private PrintService printer;

    public PrinterService() {
        // Initialize the printer (replace "Your Printer Name" with your actual printer name)
        this.printer = findPrinter("EPSON TM-T88V Receipt");
    }

    private PrintService findPrinter(String printerName) {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService ps : printServices) {
            if (ps.getName().equalsIgnoreCase(printerName)) {
                return ps;
            }
        }
        System.out.println("Printer not found!");
        return null;
    }

    public void printLargeBoldNumber(String number) throws PrintException {
        if (printer == null) {
            System.out.println("Printer not initialized.");
            return;
        }

        // ESC/POS commands to print a large, bold number
        byte[] data = createEscPosCommand(number);

        // Create a document and send it to the printer
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        Doc doc = new SimpleDoc(data, flavor, null);
        DocPrintJob job = printer.createPrintJob();
        job.print(doc, null);
    }

    private byte[] createEscPosCommand(String number) {
        // ESC/POS command sequence to print in extra large size
        byte[] command = new byte[]{
                0x1B, 0x40, // Initialize printer
                0x1B, 0x61, 0x01, // Center alignment
                0x1B, 0x45, 0x01, // Turn on bold
                0x1D, 0x21, 0x77, // Maximum width and height (0x77 = 01110111b, if supported by the printer)
        };
    
        byte[] numberBytes = number.getBytes(); // Convert the number string to bytes
        byte[] lineFeed = new byte[]{0x0A}; // Line feed
        byte[] cutCommand = new byte[]{0x1D, 0x56, 0x41, 0x10}; // Cut paper
    
        // Combine the command and the number
        byte[] data = new byte[command.length + numberBytes.length + lineFeed.length + cutCommand.length];
        System.arraycopy(command, 0, data, 0, command.length);
        System.arraycopy(numberBytes, 0, data, command.length, numberBytes.length);
        System.arraycopy(lineFeed, 0, data, command.length + numberBytes.length, lineFeed.length);
        System.arraycopy(cutCommand, 0, data, command.length + numberBytes.length + lineFeed.length, cutCommand.length);
    
        return data;
    }
    
    
    
}
