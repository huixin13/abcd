import java.text.DecimalFormat;

public class AirConditioner extends Product {
    // Additional instance variables
    private int productCapacity;
    private int productRating;

    // Parameterized constructor
    public AirConditioner(int productNumber, String productName, String productType, int productQuantity, double productPrice,
                          int productCapacity, int productRating) {
        super(productNumber, productName, productQuantity, productPrice);
        this.productCapacity = productCapacity;
        this.productRating = productRating;
    }

    // Getter and setter methods for additional instance variables
    public int getProductCapacity() {
        return productCapacity;
    }

    public void setProductCapacity(int productCapacity) {
        this.productCapacity = productCapacity;
    }

    public int getProductRating() {
        return productRating;
    }

    public void setProductRating(int productRating) {
        this.productRating = productRating;
    }

    // Method to calculate the value of the stock of an air conditioner
    public double calculateStockValue() {
        return getProductPrice() * getQuantityAvailable();
    }

    // Override toString() method
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return super.toString() +
                "\nProduct Capacity: " + productCapacity +
                "\nProduct Rating: " + productRating;
                
    }
}