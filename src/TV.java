import java.text.DecimalFormat;

public class TV extends Product {
    // Additional instance variables
    private String productType;
    private String productResolution;
    private String productSize; // Assuming display size is in inches
    private int productRating;

    // Parameterized constructor for TV
    public TV(int productNumber, String productName, int productQuantity, double productPrice,
              String productType, String productResolution, String productSize, int productRating) {
        super(productNumber, productName, productQuantity, productPrice);
        this.productType = productType;
        this.productResolution = productResolution;
        this.productSize = productSize;
        this.productRating = productRating;
    }

    // Getter and setter methods for additional instance variables
    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductResolution() {
        return productResolution;
    }

    public void setProductResolution(String productResolution) {
        this.productResolution = productResolution;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public int getProductRating() {
        return productRating;
    }

    public void setProductRating(int productRating) {
        this.productRating = productRating;
    }

    // Method to calculate the value of the TV stock
    public double calculateStockValue() {
        return getProductPrice() * getQuantityAvailable();
    }

    // Override toString() method
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return super.toString() +
                "\nProduct Type: " + productType +
                "\nResolution: " + productResolution +
                "\nSize (in inches): " + productSize +
                "\nProduct Rating: " + productRating;
    }
}
