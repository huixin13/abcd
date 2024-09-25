import java.text.DecimalFormat;

public class Dishwasher extends Product {
    // Additional instance variables
    private int productCapacity;
    private boolean hasCutleryDrawer;
    private int productRating;

    // Parameterized constructor
    public Dishwasher(int productNumber, String productName, int productQuantity, double productPrice,
                      int productCapacity, boolean hasCutleryDrawer, int productRating) {
        super(productNumber, productName, productQuantity, productPrice);
        this.productCapacity = productCapacity;
        this.hasCutleryDrawer = hasCutleryDrawer;
        this.productRating = productRating;
    }

    // Getter and setter methods for additional instance variables
    public int getProductCapacity() {
        return productCapacity;
    }

    public void setProductCapacity(int productCapacity) {
        this.productCapacity = productCapacity;
    }

    public boolean hasCutleryDrawer() {
        return hasCutleryDrawer;
    }

    public void setHasCutleryDrawer(boolean hasCutleryDrawer) {
        this.hasCutleryDrawer = hasCutleryDrawer;
    }

    public int getProductRating() {
        return productRating;
    }

    public void setProductRating(int productRating) {
        this.productRating = productRating;
    }

    // Method to calculate the value of the stock of a dishwasher
    public double calculateStockValue() {
        return getProductPrice() * getQuantityAvailable();
    }

    // Override toString() method
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return super.toString() +
                "\nCapacity (Place Settings): " + productCapacity +
                "\nHas Cutlery Drawer: " + (hasCutleryDrawer ? "Yes" : "No") +
                "\nProduct Rating: " + productRating;
    }
}