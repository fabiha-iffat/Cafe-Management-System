package entity;

import actionHandlers.*;
import javax.swing.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderProcessor {
    private File file;
    private FileWriter fwriter;

    public OrderProcessor() {
    }

    public void saveOrder(String name, String address, MenuHandler[] handlers) {
        try {
            file = new File("./data/order_summary.txt");
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm a, dd/MM/yyyy");
            String timeAndDate = currentTime.format(format);

            fwriter = new FileWriter(file, true);
            fwriter.write("\n");
            fwriter.write("Date and Time: " + timeAndDate + "\n");
            fwriter.write("=========================\n");
            fwriter.write("Customer Name: " + name + "\n");
           
            fwriter.write("Address: " + address + "\n\n");
            fwriter.write("Ordered items \n");
            fwriter.write("-------------------------------\n");

            int total = 0;
            for (int i = 0; i < handlers.length; i++) {
                String itemSummary = handlers[i].getItemSummary();
                if (!itemSummary.equals("")) {
                    fwriter.write(itemSummary);
                    total += handlers[i].getAmount();
                }
            }

            fwriter.write("-------------------------------\n");
            fwriter.write("Total Amount: ৳" + total + "\n");
            fwriter.flush();
            fwriter.close();

        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "Error saving order!");
        }
    }

    public String readOrder() {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("./data/order_summary.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            return "No order found or error reading order.";
        }
        return content.toString();
    }
}