import java.text.DecimalFormat;

public class Refrigerator extends Product {
    // Additional instance variables
    private String productDesign;
    private String productColour;
    private String productCapacity;
    private int productRating;

    // Parameterized constructor
    public Refrigerator(int productNumber, String productName, int productQuantity, double productPrice,
                        String productDesign, String productColour, String productCapacity, int productRating) {
        super(productNumber, productName, productQuantity, productPrice);
        this.productDesign = productDesign;
        this.productColour = productColour;
        this.productCapacity = productCapacity;
        this.productRating = productRating;
    }

    // Getter and setter methods for additional instance variables
    public String getproductDesign() {
        return productDesign;
    }

    public void setproductDesign(String productDesign) {
        this.productDesign = productDesign;
    }

    public String getColour() {
        return productColour;
    }

    public void setColour(String productColour) {
        this.productColour = productColour;
    }

    public String getCapacity() {
        return productCapacity;
    }

    public void setCapacity(String productCapacity) {
        this.productCapacity = productCapacity;
    }

    public int getProductRating() {
        return productRating;
    }

    public void setProductRating(int productRating) {
        this.productRating = productRating;
    }

    // Method to calculate the value of the stock of a refrigerator
    public double calculateStockValue() {
        return getProductPrice() * getQuantityAvailable();
    }

    // Override toString() method
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return super.toString() +
                "\nDoor design: " + productDesign +
                "\nColor: " + productColour +
                "\nCapacity (in Litres): " + productCapacity +
                "\nProduct Rating: " + productRating;
    }
}