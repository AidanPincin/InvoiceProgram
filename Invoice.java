import java.util.*;
import java.text.DecimalFormat;  

class Invoice
{
    // Keeps track of how many class instances has been created
    private static int invoices;
    // Where all the class instance variables will be stored
    Map<String, Object> variableMap = new HashMap<>();

    Invoice(){
        variableMap.put("invoiceNumber", 1000+invoices);
        invoices++;
    }

    void setVariable(String variableName, Object value){
        variableMap.put(variableName, value);
    }

    Object getVariable(String variableName){
        return variableMap.get(variableName);
    }

    public static void main(String[] args){
        // Decfor is used to format tax and total to 2 decimal places 
        final DecimalFormat decfor = new DecimalFormat("0.00");  
        double taxRate = 5.017;
        // set invoices array to size of 1000
        Invoice[] invoices = new Invoice[1000];
        int num = 0;
        Scanner input = new Scanner(System.in);
        while(true){
            if(num>0){
                // bug fix: consume new line character to prevent customer name input skip
                input.nextLine();
            }
            // Get user to input name, appointment charge, and parts&labor charge
            Invoice invoice = new Invoice();
            System.out.print("\n\nEnter customer's name : ");
            invoice.setVariable("customerName", input.nextLine());
            System.out.print("Enter service appointment charge : ");
            invoice.setVariable("serviceAppointmentCharge", input.nextDouble());
            System.out.print("Enter parts and labor charge : ");
            invoice.setVariable("partsAndLaborCharge", input.nextDouble());
            // Calculate and set values to subtotal, tax, and total
            invoice.setVariable("subtotal", (double)invoice.getVariable("serviceAppointmentCharge")+(double)invoice.getVariable("serviceAppointmentCharge"));
            invoice.setVariable("tax", (double)invoice.getVariable("subtotal")*(taxRate/100));
            invoice.setVariable("total", (double)invoice.getVariable("subtotal")+(double)invoice.getVariable("tax"));
            // Print invoice
            System.out.println("Invoice Number: "+invoice.getVariable("invoiceNumber"));
            System.out.println("Customer Name: "+invoice.getVariable("customerName"));
            System.out.println("Service Appointment Charge: "+invoice.getVariable("serviceAppointmentCharge"));
            System.out.println("Parts and Labor Charge: "+invoice.getVariable("partsAndLaborCharge"));
            System.out.println("Subtotal: "+invoice.getVariable("subtotal"));
            System.out.println("Tax Rate: "+taxRate);
            System.out.println("Tax: "+decfor.format(invoice.getVariable("tax")));
            System.out.println("Total Due: "+decfor.format(invoice.getVariable("total")));
            // add new invoice to invoices array
            invoices[num] = invoice;
            // checks to make sure invoices are not exceeding array length
            if(num != 999){
                System.out.print("Press 1 to to create one more invoice for next customer ");
                // breaks loop if user does not want anymore invoices
                if(input.nextInt() != 1){
                    break;
                }
                num++;
            }
            else{
                System.out.println("Invoice limit reached");
                break;
            }
        }
        input.close();
    }

}
