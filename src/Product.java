import java.text.DecimalFormat;

// Abstract Product class
public abstract class Product {
    // Instance variables
    private int itemNumber;
    private String productName;
    private int quantityAvailable;
    private double productPrice;
    protected boolean productStatus;
    
    // Parameterized constructor
    public Product(int itemNumber, String productName, int quantityAvailable, double productPrice) {
        this.itemNumber = itemNumber;
        this.productName = productName;
        this.quantityAvailable = quantityAvailable;
        this.productPrice = productPrice;
        this.productStatus = true; // Default status is true (active)
    }

    // Getter and setter methods
    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public boolean isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
    	if(this instanceof AirConditioner) {
	        // Cast to Refrigerator and set the status
	        ((AirConditioner) this).productStatus = productStatus;
	    }
	    // Check if the instance is a Refrigerator
	    else if (this instanceof Dishwasher) {
	        // Cast to Refrigerator and set the status
	        ((Dishwasher) this).productStatus = productStatus;
	    }
	    else if (this instanceof Refrigerator) {
	        // Cast to Refrigerator and set the status
	        ((Refrigerator) this).productStatus = productStatus;
	    }
	    else if (this instanceof TV) {
	        // Cast to Refrigerator and set the status
	        ((TV) this).productStatus = productStatus;
	    }
    }

    // Method to get total inventory value
    public double getTotalInventoryValue() {
        return productPrice * quantityAvailable;
    }

    // Method to add quantity available
    public void addQuantity(int quantity) {
        if (productStatus) {
            quantityAvailable += quantity;
        } else {
            System.out.println("Cannot add stock to a discontinued product line.");
        }
    }

    // Method to deduct quantity available
    public void deductQuantity(int quantity) {
        if (productStatus && quantity <= quantityAvailable) {
            quantityAvailable -= quantity;
        } else {
            System.out.println("Invalid quantity or product is discontinued.");
        }
    }
    
    // Override toString() method
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return "Item number: " + itemNumber +
                "\nProduct name: " + productName +
                "\nQuantity available: " + quantityAvailable +
                "\nPrice (RM): " + df.format(productPrice) +
                "\nInventory value (RM): " + df.format(getTotalInventoryValue()) +
                "\nProduct status: " + (productStatus ? "Active" : "Discontinued");
    }
}
