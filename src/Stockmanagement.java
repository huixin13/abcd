import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Stockmanagement extends Application {
	
	private static ArrayList<Product>product = new ArrayList<Product>();
    private static BorderPane rightpane = new BorderPane();// Create RightPane which can be access by other function
    private static GridPane gridpane_add_deduce = new GridPane(); //create GridPane for add and deduce to store inside
    private static int index = -1;
	private static Text rightPaneText = new Text();
	private static Button rightPaneButton = new Button();
	private UserInfo userInfo; // Declare an Instance Variable of UserInfo
    static UserInfo userinfo = new UserInfo();
    boolean hasCutleryDrawer = false;
    
    @Override
    public void start(Stage primaryStage) {
    	
    	// Initialize the UserInfo Instance
    	userInfo = new UserInfo(); 
    	
    	UserMenu(primaryStage);
       
    } 
    
    private static void DisplayMenu(Stage primaryStage) {
    	
    	 // Display Menu Message
         Text Menutxt = new Text("MENU");
         Menutxt.setFont(Font.font("Courier New", FontWeight.BOLD, 60));
         Menutxt.setLayoutX(80);
         Menutxt.setLayoutY(100);
         
         // Display Menu
         VBox MenuPane = MenuPane(primaryStage);
     	 MenuPane.setLayoutX(30);
 		 MenuPane.setLayoutY(140);

 	   	  // Create left pane 
         Pane leftpane = new Pane();
         leftpane.getChildren().addAll(MenuPane,Menutxt);
         leftpane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
         leftpane.setPadding(new Insets(20));
         
         // Create a parent Pane layout to hold left and right panes
         HBox root = new HBox(leftpane, rightpane);

         rightpane.setPrefWidth(600); 
         
         // Create scene
         Scene scene = new Scene(root, 1000, 600);
         primaryStage.setTitle("Stock Management");
         primaryStage.setScene(scene);
         primaryStage.show();
         
         // Pop Up Add Quantity Window
         
         Stockmanagement stockManagement = new Stockmanagement();
         stockManagement.ProductSelection();
         
    }
    
    private static VBox MenuPane(Stage primaryStage) {
    	
        VBox MenuPane = new VBox(30);
        MenuPane.setPadding(new Insets(5));
        MenuPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        // View Product Button
        Button Viewbtn = new Button("View Product");
        Viewbtn.setFont(Font.font("Courier New", FontWeight.BOLD, 19));
        Viewbtn.setPrefHeight(40);
        Viewbtn.setPrefWidth(225);
        
        // View Product Event Handler
        Viewbtn.setOnAction(e -> { 
        gridpane_add_deduce.getChildren().clear();
    	rightpane.getChildren().clear();
    	displayViewFunction(primaryStage);});

        // Add Stock Button
        Button Addbtn = new Button("Add Stock");
        Addbtn.setFont(Font.font("Courier New", FontWeight.BOLD, 19));
        Addbtn.setPrefHeight(40);
        Addbtn.setPrefWidth(225);
        
        // Add Stock Event Handler
        Addbtn.setOnAction(e -> {
        gridpane_add_deduce.getChildren().clear();
        rightpane.getChildren().clear();
        displayAddFunction(primaryStage);});

        // Deduct Stock Button
        Button Deductbtn = new Button("Deduct Stock");
        Deductbtn.setFont(Font.font("Courier New", FontWeight.BOLD, 19));
        Deductbtn.setPrefHeight(40);
        Deductbtn.setPrefWidth(225);
        
        // Deduct Stock Event Handler
        Deductbtn.setOnAction(e -> {
        gridpane_add_deduce.getChildren().clear();
        rightpane.getChildren().clear();
        displayDeductFunction(primaryStage);});

        // Discontinue Stock Button
        Button Discontinuebtn = new Button("Discontinue Stock");
        Discontinuebtn.setFont(Font.font("Courier New", FontWeight.BOLD, 19));
        Discontinuebtn.setPrefHeight(40);
        Discontinuebtn.setPrefWidth(225);
        
        // Discontinue Stock Event Handler
        Discontinuebtn.setOnAction(e -> {
        gridpane_add_deduce.getChildren().clear();
        rightpane.getChildren().clear();
        displayDiscontinueFunction(primaryStage);});

        // Exit Button
        Button Exitbtn = new Button("Exit");
        Exitbtn.setFont(Font.font("Courier New", FontWeight.BOLD, 19));
        Exitbtn.setPrefHeight(40);
        Exitbtn.setPrefWidth(225);
        
        // Exit Event Handler
        Exitbtn.setOnAction(e -> {
        	Text TYtxt = new Text("Thank you for using our system!");
        	TYtxt.setFont(Font.font("Arial", 16));
        	TYtxt.setTextAlignment(TextAlignment.CENTER);
            
            Text UserIdtxt = new Text("User ID: " + userinfo.getUserId());
            UserIdtxt.setFont(Font.font("Arial", 16));
            UserIdtxt.setTextAlignment(TextAlignment.CENTER);
            
            VBox vBox = new VBox(10); // Set spacing between the text nodes
            vBox.setAlignment(Pos.CENTER);
            vBox.getChildren().addAll( UserIdtxt, TYtxt);
            
            Scene scene = new Scene(new StackPane(vBox), 400, 150);
	        
	        primaryStage.setScene(scene);
	        primaryStage.setTitle("Exiting Program...");
	        primaryStage.show();
	        primaryStage.centerOnScreen();
	        
        });
        
        // Add the buttons to the MenuPane
        MenuPane.getChildren().addAll(Viewbtn, Addbtn, Deductbtn, Discontinuebtn, Exitbtn);
        
        // Move MenuPane behind
        MenuPane.toBack();
        return MenuPane;  
    }
    
    private void ProductSelection() {
    	
    	Stage ProductSelectionStage = new Stage();
        ProductSelectionStage.setTitle("Add Product Form");
        
        // Add content to the form 
        Text SelectProductTxt = new Text("PLEASE SELECT PRODUCT TYPE:");
        SelectProductTxt.setFont(Font.font("Courier New", FontWeight.BOLD, 25));
        SelectProductTxt.setLayoutX(45);
        SelectProductTxt.setLayoutY(40);
        
        // AirConditioner Button
        Button AirConditionBtn = new Button("Air Conditioner");
        AirConditionBtn.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        AirConditionBtn.setPrefWidth(200);
        AirConditionBtn.setOnAction(e -> {
        Form1();
        ProductSelectionStage.close();
        });
        
        // Dishwasher Button
        Button DishwasherBtn = new Button("Dishwasher");
        DishwasherBtn.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        DishwasherBtn.setPrefWidth(200);
        DishwasherBtn.setOnAction(e -> {
        Form2();
        ProductSelectionStage.close();
        });
        
        // Refrigerator Button
        Button RefrigeratorBtn = new Button("Refrigerator");
        RefrigeratorBtn.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        RefrigeratorBtn.setPrefWidth(200);
        RefrigeratorBtn.setOnAction(e -> {
        Form3();
        ProductSelectionStage.close();
        });
        
        // Television Button
        Button TelevisionBtn = new Button("Television");
        TelevisionBtn.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        TelevisionBtn.setPrefWidth(200);
        TelevisionBtn.setOnAction(e -> {
        Form4();
        ProductSelectionStage.close();
        });
        
        // Create close button 
        Button CloseBtn = new Button("Close");
        CloseBtn.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        CloseBtn.setPrefWidth(200);
        CloseBtn.setOnAction(e -> ProductSelectionStage.close());
        
        // Create form layout 
        VBox ProductMenuLayout = new VBox(30);
        ProductMenuLayout.setLayoutX(150);
        ProductMenuLayout.setLayoutY(80);
        ProductMenuLayout.getChildren().addAll(AirConditionBtn, DishwasherBtn, RefrigeratorBtn, TelevisionBtn, CloseBtn);

        Pane AddProductFormpane = new Pane();
        AddProductFormpane.getChildren().addAll(SelectProductTxt, ProductMenuLayout);
        AddProductFormpane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        AddProductFormpane.setPadding(new Insets(20));
        
        // Create the scene 
        Scene AddProductFormScene = new Scene(AddProductFormpane, 500, 400);
        ProductSelectionStage.setScene(AddProductFormScene);
        
        // Display the popup window
        ProductSelectionStage.show();
    }
    
    // Form of the AirConditioner
    private void Form1() {
    	Stage FormStage = new Stage();
    	FormStage.setTitle("Add Product Form: Air Conditioner");
        
        // Add content to the form 
        Text AddProductText = new Text("PLEASE ENTER NEW PRODUCT DETAIL:");
        AddProductText.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
        
        // Label for Product Number
        Label lblProductNumber = new Label("Product Number:");
        // Adjust TextFields for tfFirstName.
        TextField tfProductNumber= new TextField();
        tfProductNumber.setPromptText("0000");
        tfProductNumber.setPrefWidth(340);
        
        // Label for Product Name
        Label lblProductName = new Label("Name:");
        
        // Adjust TextFields for tfFirstName.
        TextField tfProductName= new TextField();
        tfProductName.setPromptText("Name");
        tfProductName.setPrefWidth(340);
        
        // Label for Product Price
        Label lblProductPrice = new Label("Price:");
        
        // Adjust TextFields for tfFirstName.
        TextField tfProductPrice= new TextField();
        tfProductPrice.setPromptText("0.00");
        tfProductPrice.setPrefWidth(340);
        
        // Label for Product Price
        Label lblCoolingCapacity = new Label("Cooling Capacity (BTU):");
        
        // Adjust TextFields for tfFirstName.
        TextField tfCoolingCapacity= new TextField();
        tfCoolingCapacity.setPromptText("00");
        tfCoolingCapacity.setPrefWidth(30);
        
        // Add product quantity label
        Label Quantitylbl = new Label("Quantity:");
        
        // Quantity Text Field
        TextField tfQuantity = new TextField("0");
        tfQuantity.setAlignment(Pos.CENTER);
        tfQuantity.setPrefWidth(35);
        
        // Decrease Quantity Button
        Button DecreaseBtn = new Button("-");
        DecreaseBtn.setPrefWidth(33);
        DecreaseBtn.setOnAction(e -> {
            int currentQuantity = Integer.parseInt(tfQuantity.getText());
            if (currentQuantity > 0) {
            	tfQuantity.setText(String.valueOf(currentQuantity - 1));
            }
        });
        
        //Increase Quantity Button
        Button IncreaseBtn = new Button("+");
        IncreaseBtn.setPrefWidth(33);
        IncreaseBtn.setOnAction(e -> {
            int currentQuantity = Integer.parseInt(tfQuantity.getText());
            if(currentQuantity <= 100) {
            tfQuantity.setText(String.valueOf(currentQuantity + 1));
            }
        });

        // HBox add QuantityButtonBox
        HBox QuantityButtonBox = new HBox(20);
        QuantityButtonBox.getChildren().addAll(DecreaseBtn, tfQuantity, IncreaseBtn);
        
        // Create Quantitybox
        VBox Quantitybox = new VBox(5);
        Quantitybox.getChildren().addAll(Quantitylbl ,QuantityButtonBox);
     
        // Create label for Energy Efficiency Rating
        Label lblEnergyEfficiencyRating = new Label("Energy Efficiency Rating:");
        
        // Create HBox For Togglegroup
        HBox EnergyEfficiencyRatingBox = new HBox(10);
        
        // Create a toggle group 
        ToggleGroup EERatingGroup = new ToggleGroup();
        for (int i = 1; i <= 5; i++) {
            ToggleButton toggleButton = new ToggleButton(Integer.toString(i));
            toggleButton.setToggleGroup(EERatingGroup);
            EnergyEfficiencyRatingBox.getChildren().add(toggleButton);
        }

        VBox EERatingbox = new VBox(5);
        EERatingbox.getChildren().addAll(lblEnergyEfficiencyRating, EnergyEfficiencyRatingBox);
        
        HBox QuantityEERatingBox = new HBox(180);
        QuantityEERatingBox.getChildren().addAll(EERatingbox, Quantitybox);
        
        // Add ACType Label
        Label ACTypelbl = new Label("Type:");
        
        // ComboBox for ACType
        ComboBox<String> ACTypeComboBox = new ComboBox<>();
        ACTypeComboBox.setPromptText("Select Product Type");
        
        // Add options based on type of air conditioner
        ACTypeComboBox.setItems(FXCollections.observableArrayList(
            "Window Air Conditioner",
            "Split System Air Conditioner",
            "Portable Air Conditioner",
            "Ductless Mini-Split Air Conditioner"
        ));

        // Create form layout 
        VBox FormLayout = new VBox(5);
        FormLayout.setPadding(new Insets(20));
        FormLayout.getChildren().addAll(AddProductText, lblProductNumber, tfProductNumber, lblProductName, tfProductName, lblProductPrice, tfProductPrice, lblCoolingCapacity, tfCoolingCapacity, QuantityEERatingBox, ACTypelbl, ACTypeComboBox);
        
        // Create HBox for buttons
        HBox ButtonLayout = new HBox(20);
        ButtonLayout.setLayoutX(420);
        ButtonLayout.setLayoutY(500);
        
        // Add save button 
        Button SaveBtn = new Button("Save");
        SaveBtn.setOnAction(e -> {
        	try {
        		int productNumber = Integer.parseInt(tfProductNumber.getText());
            	String productName = tfProductName.getText();
            	Double productPrice = Double.parseDouble(tfProductPrice.getText());
            	int productCapacity = Integer.parseInt(tfCoolingCapacity.getText());
            	int productQuantity = Integer.parseInt(tfQuantity.getText());
            	String productType = ACTypeComboBox.getValue();
            	 int productRating = 0;
            	
            	    ToggleButton selectedToggleButton = (ToggleButton) EERatingGroup.getSelectedToggle();
            	    if (selectedToggleButton != null) {
            	        productRating = Integer.parseInt(selectedToggleButton.getText());
            	    }
            	AirConditioner airconditioner = new AirConditioner(
            			productNumber, productName, productType, productQuantity, productPrice, productCapacity, productRating
                        );
            	
            	// Add the new Refrigerator object to the productList
            	product.add(airconditioner);
            	
            	//product added successful
            	System.out.println("Product added: " + productName);
            	FormStage.close();
            	ContinueAdd();
            	showUpdateBox(FormStage, "Product Added!");
        	}catch(NumberFormatException invalid) {
 				showAlertBox( FormStage, "Invalid Input detected.Please try again!");
        	}
        	
            });	
        	        	
        // Add Back button 
        Button BackBtn = new Button("Back");
        BackBtn.setOnAction(e -> {
        	ProductSelection();
        	FormStage.close();
        	});
        ButtonLayout.getChildren().addAll(BackBtn, SaveBtn);
        
        Pane AddProductFormpane = new Pane();
        AddProductFormpane.getChildren().addAll(FormLayout,ButtonLayout);
        AddProductFormpane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        AddProductFormpane.setPadding(new Insets(20));
        
        // Create the scene 
        Scene AddProductFormScene = new Scene( AddProductFormpane,560, 550);
        FormStage.setScene(AddProductFormScene);
        
        // Display the popup window
        FormStage.show();
    }
    
    // Form of the Dishwasher
    private void Form2() {
    	Stage FormStage = new Stage();
    	FormStage.setTitle("Add Product Form: Dishwasher");
        
        // Add content to the form 
        Text AddProductText = new Text("PLEASE ENTER NEW PRODUCT DETAIL:");
        AddProductText.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
        
        // Label for Product Number
        Label lblProductNumber = new Label("Product Number:");
        // Adjust TextFields for tfFirstName.
        TextField tfProductNumber= new TextField();
        tfProductNumber.setPromptText("0000");
        tfProductNumber.setPrefWidth(340);
        
        // Label for Product Name
        Label lblProductName = new Label("Name:");
        // Adjust TextFields for tfFirstName.
        TextField tfProductName= new TextField();
        tfProductName.setPromptText("Name");
        tfProductName.setPrefWidth(340);
        
        // Label for Product Price
        Label lblProductPrice = new Label("Price:");
        // Adjust TextFields for tfProductPrice.
        TextField tfProductPrice= new TextField();
        tfProductPrice.setPromptText("0.00");
        tfProductPrice.setPrefWidth(340);
        
        // Label for Capacity
        Label lblCoolingCapacity = new Label("Capacity (Settings):");
        // Adjust TextFields for tfCoolingCapacity
        TextField tfCoolingCapacity= new TextField();
        tfCoolingCapacity.setPromptText("00");
        tfCoolingCapacity.setPrefWidth(30);
        
        // Add product quantity label
        Label Quantitylbl = new Label("Quantity:");
        
        // Quantity Text Field
        TextField tfQuantity = new TextField("0");
        tfQuantity.setAlignment(Pos.CENTER);
        tfQuantity.setPrefWidth(35);
        
        // Decrease Quantity Button
        Button DecreaseBtn = new Button("-");
        DecreaseBtn.setPrefWidth(33);
        DecreaseBtn.setOnAction(e -> {
            int currentQuantity = Integer.parseInt(tfQuantity.getText());
            if (currentQuantity > 0) {
            	tfQuantity.setText(String.valueOf(currentQuantity - 1));
            }
        });
        
        //Increase Quantity Button
        Button IncreaseBtn = new Button("+");
        IncreaseBtn.setPrefWidth(33);
        IncreaseBtn.setOnAction(e -> {
            int currentQuantity = Integer.parseInt(tfQuantity.getText());
            if(currentQuantity <= 100) {
            tfQuantity.setText(String.valueOf(currentQuantity + 1));
            }
        });

        //HBox add QuantityButtonBox
        HBox QuantityButtonBox = new HBox(20);
        QuantityButtonBox.getChildren().addAll(DecreaseBtn, tfQuantity, IncreaseBtn);
        
        //Create Quantitybox
        VBox Quantitybox = new VBox(5);
        Quantitybox.getChildren().addAll(Quantitylbl ,QuantityButtonBox);
     
        //Create label for Energy Efficiency Rating
        Label lblEnergyEfficiencyRating = new Label("Energy Efficiency Rating:");
        
        // Create HBox For Togglegroup
        HBox EnergyEfficiencyRatingBox = new HBox(10);
        
        // Create a toggle group 
        ToggleGroup EERatingGroup = new ToggleGroup();
        for (int i = 1; i <= 5; i++) {
            ToggleButton toggleButton = new ToggleButton(Integer.toString(i));
            toggleButton.setToggleGroup(EERatingGroup);
            EnergyEfficiencyRatingBox.getChildren().add(toggleButton);
        }

        VBox EERatingbox = new VBox(5);
        EERatingbox.getChildren().addAll( lblEnergyEfficiencyRating, EnergyEfficiencyRatingBox);
        
        HBox QuantityEERatingBox = new HBox(180);
        QuantityEERatingBox.getChildren().addAll(EERatingbox, Quantitybox);
        
        //Create label for Cultery Drawer
        Label lblCulteryDrawer = new Label("Cultery Drawer:");
        
        // Create HBox For CulteryDrawerBox
        HBox CulteryDrawerBox = new HBox(10);
        
        // Create a ToggleGroup for the RadioButtons
        ToggleGroup drawerSelectGroup = new ToggleGroup();
        
        // Create "Yes" RadioButton
        RadioButton YesRadioBtn = new RadioButton("Yes");
        YesRadioBtn.setToggleGroup(drawerSelectGroup);
        YesRadioBtn.setOnAction(e -> {
            hasCutleryDrawer = true;
        });
        CulteryDrawerBox.getChildren().add(YesRadioBtn);
        
        // Create "No" RadioButton
        RadioButton NoRadioBtn = new RadioButton("No");
        NoRadioBtn.setToggleGroup(drawerSelectGroup);
        NoRadioBtn.setOnAction(e -> {
            hasCutleryDrawer = false;
        });
        CulteryDrawerBox.getChildren().add(NoRadioBtn);
        
        // Create form layout 
        VBox FormLayout = new VBox(5);
        FormLayout.setPadding(new Insets(20));
        FormLayout.getChildren().addAll(AddProductText, lblProductNumber, tfProductNumber, lblProductName, tfProductName, lblProductPrice, tfProductPrice, lblCoolingCapacity, tfCoolingCapacity, QuantityEERatingBox, lblCulteryDrawer, CulteryDrawerBox );
        
        //Create HBox for buttons
        HBox ButtonLayout = new HBox(20);
        ButtonLayout.setLayoutX(420);
        ButtonLayout.setLayoutY(500);
        
        // Add save button 
        Button SaveBtn = new Button("Save");
        SaveBtn.setOnAction(e -> {
        try{
        	int productNumber = Integer.parseInt(tfProductNumber.getText());
        	String productName = tfProductName.getText();
        	int productCapacity = Integer.parseInt(tfCoolingCapacity.getText());
        	Double productPrice = Double.parseDouble(tfProductPrice.getText());
        	int productQuantity = Integer.parseInt(tfQuantity.getText());
        	int productRating = 0;
        	ToggleButton selectedToggleButton = (ToggleButton) EERatingGroup.getSelectedToggle();
        	   if (selectedToggleButton != null) {
        	        productRating = Integer.parseInt(selectedToggleButton.getText());
        	   }

        	Dishwasher dishwasher = new Dishwasher(
        			productNumber, productName, productQuantity, productPrice, productCapacity, hasCutleryDrawer, productRating
        	            );
        	// Add the new Refrigerator object to the productList
        	product.add(dishwasher);

        	// Optionally, display a confirmation message
        	System.out.println("Product added: " + productName);
        	FormStage.close();
        	ContinueAdd();
        	showUpdateBox(FormStage, "Product Added!");
    	}catch(NumberFormatException invalid) {
				showAlertBox( FormStage, "Invalid Input detected.Please try again!");
    	}
        	
        });

        // Add Back button 
        Button BackBtn = new Button("Back");
        BackBtn.setOnAction(e -> {
        	ProductSelection();
        	FormStage.close();
        	});
        ButtonLayout.getChildren().addAll(BackBtn, SaveBtn);
        
        Pane AddProductFormpane = new Pane();
        AddProductFormpane.getChildren().addAll(FormLayout,ButtonLayout);
        AddProductFormpane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        AddProductFormpane.setPadding(new Insets(20));
        
        // Create the scene 
        Scene AddProductFormScene = new Scene( AddProductFormpane, 560, 550);
        FormStage.setScene(AddProductFormScene);
        
        // Display the popup window
        FormStage.show();
    }
    
    // Form of the Refrigerator
    private void Form3() {

    	Stage FormStage = new Stage();
    	FormStage.setTitle("Add Product Form: Refrigerator");
        
        // Add content to the form 
        Text AddProductText = new Text("PLEASE ENTER NEW PRODUCT DETAIL:");
        AddProductText.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
        
        // Label for Product Number
        Label lblProductNumber = new Label("Product Number:");
        // Adjust TextFields for tfFirstName.
        TextField tfProductNumber= new TextField();
        tfProductNumber.setPromptText("00000000");
        tfProductNumber.setPrefWidth(340);
        
        // Label for Product Name
        Label lblProductName = new Label("Name:");
        // Adjust TextFields for tfFirstName.
        TextField tfProductName= new TextField();
        tfProductName.setPromptText("Name");
        tfProductName.setPrefWidth(340);
        
        // Label for Product Price
        Label lblProductPrice = new Label("Price:");
        // Adjust TextFields for tfFirstName.
        TextField tfProductPrice= new TextField();
        tfProductPrice.setPromptText("0.00");
        tfProductPrice.setPrefWidth(340);
        
        // Label for Product Price
        Label lblProductColour = new Label("Colour:");
        // Adjust TextFields for tfFirstName.
        TextField tfProductColour= new TextField();
        tfProductColour.setPromptText("White");
        tfProductColour.setPrefWidth(30);
        
        // Add product quantity label
        Label Quantitylbl = new Label("Quantity:");
        
        // Quantity Text Field
        TextField tfQuantity = new TextField("0");
        tfQuantity.setAlignment(Pos.CENTER);
        tfQuantity.setPrefWidth(35);
        
        // Decrease Quantity Button
        Button DecreaseBtn = new Button("-");
        DecreaseBtn.setPrefWidth(33);
        DecreaseBtn.setOnAction(e -> {
            int currentQuantity = Integer.parseInt(tfQuantity.getText());
            if (currentQuantity > 0) {
            	tfQuantity.setText(String.valueOf(currentQuantity - 1));
            }
        });
        
        // Increase Quantity Button
        Button IncreaseBtn = new Button("+");
        IncreaseBtn.setPrefWidth(33);
        IncreaseBtn.setOnAction(e -> {
            int currentQuantity = Integer.parseInt(tfQuantity.getText());
            if(currentQuantity <= 100) {
            tfQuantity.setText(String.valueOf(currentQuantity + 1));
            }
        });

        // HBox add QuantityButtonBox
        HBox QuantityButtonBox = new HBox(20);
        QuantityButtonBox.getChildren().addAll(DecreaseBtn, tfQuantity, IncreaseBtn);
        
        // Create Quantitybox
        VBox Quantitybox = new VBox(5);
        Quantitybox.getChildren().addAll(Quantitylbl ,QuantityButtonBox);
     
        // Create label for Energy Efficiency Rating
        Label lblEnergyEfficiencyRating = new Label("Energy Efficiency Rating:");
        
        // Create HBox For Togglegroup
        HBox EnergyEfficiencyRatingBox = new HBox(10);
        
        // Create a toggle group 
        ToggleGroup EERatingGroup = new ToggleGroup();
        for (int i = 1; i <= 5; i++) {
            ToggleButton toggleButton = new ToggleButton(Integer.toString(i));
            toggleButton.setToggleGroup(EERatingGroup);
            EnergyEfficiencyRatingBox.getChildren().add(toggleButton);
        }

        VBox EERatingbox = new VBox(5);
        EERatingbox.getChildren().addAll( lblEnergyEfficiencyRating, EnergyEfficiencyRatingBox);
        
        HBox QuantityEERatingBox = new HBox(180);
        QuantityEERatingBox.getChildren().addAll(EERatingbox, Quantitybox);

        // Create a label for the ComboBox
        Label lbldoorDesign = new Label("Door Design:");
        // Create a ComboBox for door designs
        ComboBox<String> doorDesignComboBox = new ComboBox<>();
        doorDesignComboBox.setPromptText("Door Design");
        doorDesignComboBox.setItems(FXCollections.observableArrayList(
        		"Single door", "Double door", "French door", "Side-by-side door"
        ));
        
        VBox doordesignselectionbox = new VBox();
        doordesignselectionbox.getChildren().addAll(lbldoorDesign, doorDesignComboBox);
        
        //Label Capacity
        Label capacitylbl = new Label("Capacity:");
        //ToggleGroup capacitytogglegroup
        ToggleGroup capacityToggleGroup = new ToggleGroup(); 
        // Create RadioButtons for capacity
        RadioButton smallRadioBtn = new RadioButton("Small: 100-250 liters");
        smallRadioBtn.setToggleGroup(capacityToggleGroup);
        RadioButton mediumRadioBtn = new RadioButton("Medium: 250-400 liters");
        mediumRadioBtn.setToggleGroup(capacityToggleGroup);
        RadioButton largeRadioBtn = new RadioButton("Large: 400-600 liters");
        largeRadioBtn.setToggleGroup(capacityToggleGroup);
        
        VBox CapacityBtnBox = new VBox(10);
        CapacityBtnBox.getChildren().addAll(capacitylbl, smallRadioBtn, mediumRadioBtn, largeRadioBtn);
        
        HBox Capacitydoordesignbox = new HBox(170);
        Capacitydoordesignbox.getChildren().addAll( CapacityBtnBox, doordesignselectionbox);
        
        // Create form layout 
        VBox FormLayout = new VBox(5);
        FormLayout.setPadding(new Insets(20));
        FormLayout.getChildren().addAll(AddProductText, lblProductNumber, tfProductNumber, lblProductName, tfProductName, lblProductPrice, tfProductPrice, lblProductColour, tfProductColour, QuantityEERatingBox, Capacitydoordesignbox);
        
        // Create HBox for buttons
        HBox ButtonLayout = new HBox(20);
        ButtonLayout.setLayoutX(420);
        ButtonLayout.setLayoutY(500);
        
        // Add save button 
        Button SaveBtn = new Button("Save");
        SaveBtn.setOnAction(e -> {
        try {
        	int productNumber = Integer.parseInt(tfProductNumber.getText());
        	String productName = tfProductName.getText();
        	Double productPrice = Double.parseDouble(tfProductPrice.getText());
        	int productQuantity = Integer.parseInt(tfQuantity.getText());
        	String productColour = tfProductColour.getText();
        	String productDesign = doorDesignComboBox.getValue();
        	 int productRating = 0;
        	    ToggleButton selectedToggleButton = (ToggleButton) EERatingGroup.getSelectedToggle();
        	    if (selectedToggleButton != null) {
        	        productRating = Integer.parseInt(selectedToggleButton.getText());
        	    }
        	String productCapacity = "";
        	if(smallRadioBtn.isSelected()) {
        		productCapacity = "Small";
        	}else if(mediumRadioBtn.isSelected()) {
        		productCapacity = "Medium";
        	}else if(largeRadioBtn.isSelected()) {
        		productCapacity = "Large";
        	}
        	
        	Refrigerator refrigerator = new Refrigerator(
        			productNumber, productName, productQuantity, productPrice, productDesign, productColour, productCapacity, productRating
        			);
        	// Add the new Refrigerator object to the productList
        	product.add(refrigerator);

        	// Optionally, display a confirmation message
        	System.out.println("Product added: " + productName);
        	FormStage.close();
        	ContinueAdd();
        	showUpdateBox(FormStage, "Product Added!");
    			}catch(NumberFormatException invalid) {
				showAlertBox( FormStage, "Invalid Input detected. Please try again!");
    			}

        });

        // Add Back button 
        Button BackBtn = new Button("Back");
        BackBtn.setOnAction(e -> {
        	ProductSelection();
        	FormStage.close();
        	});
        ButtonLayout.getChildren().addAll(BackBtn, SaveBtn);
        
        Pane AddProductFormpane = new Pane();
        AddProductFormpane.getChildren().addAll(FormLayout,ButtonLayout);
        AddProductFormpane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        AddProductFormpane.setPadding(new Insets(20));
        
        // Create the scene 
        Scene AddProductFormScene = new Scene( AddProductFormpane, 560, 550);
        FormStage.setScene(AddProductFormScene);
        
        // Display the popup window
        FormStage.show();
    }
    
    // Form of the Television
    private void Form4() {
    	Stage FormStage = new Stage();
    	FormStage.setTitle("Add Product Form: Television");
        
        // Add content to the form 
        Text AddProductText = new Text("PLEASE ENTER NEW PRODUCT DETAIL:");
        AddProductText.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
        
        // Label for Product Number
        Label lblProductNumber = new Label("Product Number:");
        // Adjust TextFields for tfFirstName.
        TextField tfProductNumber= new TextField();
        tfProductNumber.setPromptText("0000");
        tfProductNumber.setPrefWidth(340);
        
        // Label for Product Name
        Label lblProductName = new Label("Name:");
        // Adjust TextFields for tfFirstName.
        TextField tfProductName= new TextField();
        tfProductName.setPromptText("Name");
        tfProductName.setPrefWidth(340);
        
        // Label for Product Price
        Label lblProductPrice = new Label("Price:");
        // Adjust TextFields for tfFirstName.
        TextField tfProductPrice= new TextField();
        tfProductPrice.setPromptText("0.00");
        tfProductPrice.setPrefWidth(340);
        
        // Add product quantity label
        Label Quantitylbl = new Label("Quantity:");
        // Quantity Text Field
        TextField tfQuantity = new TextField("0");
        tfQuantity.setAlignment(Pos.CENTER);
        tfQuantity.setPrefWidth(35);
        
        // Decrease Quantity Button
        Button DecreaseBtn = new Button("-");
        DecreaseBtn.setPrefWidth(33);
        DecreaseBtn.setOnAction(e -> {
            int currentQuantity = Integer.parseInt(tfQuantity.getText());
            if (currentQuantity > 0) {
            	tfQuantity.setText(String.valueOf(currentQuantity - 1));
            }
        });
        
        //Increase Quantity Button
        Button IncreaseBtn = new Button("+");
        IncreaseBtn.setPrefWidth(33);
        IncreaseBtn.setOnAction(e -> {
            int currentQuantity = Integer.parseInt(tfQuantity.getText());
            if(currentQuantity <= 100) {
            tfQuantity.setText(String.valueOf(currentQuantity + 1));
            }
        });

        // HBox add QuantityButtonBox
        HBox QuantityButtonBox = new HBox(20);
        QuantityButtonBox.getChildren().addAll(DecreaseBtn, tfQuantity, IncreaseBtn);
        
        // Create Quantitybox
        VBox Quantitybox = new VBox(5);
        Quantitybox.getChildren().addAll(Quantitylbl ,QuantityButtonBox);
     
        // Create label for Energy Efficiency Rating
        Label lblEnergyEfficiencyRating = new Label("Energy Efficiency Rating:");
        
        // Create HBox For Togglegroup
        HBox EnergyEfficiencyRatingBox = new HBox(10);
        
        // Create a toggle group 
        ToggleGroup EERatingGroup = new ToggleGroup();
        for (int i = 1; i <= 5; i++) {
            ToggleButton toggleButton = new ToggleButton(Integer.toString(i));
            toggleButton.setToggleGroup(EERatingGroup);
            EnergyEfficiencyRatingBox.getChildren().add(toggleButton);
        }

        VBox EERatingbox = new VBox(5);
        EERatingbox.getChildren().addAll( lblEnergyEfficiencyRating, EnergyEfficiencyRatingBox);
        
        HBox QuantityEERatingBox = new HBox(170);
        QuantityEERatingBox.getChildren().addAll(EERatingbox, Quantitybox);
        
        // Label for resolution
        Label resolutionLabel = new Label("Resolution:");
        
        // ComboBox for resolution
        ComboBox<String> resolutionComboBox = new ComboBox<>();
        resolutionComboBox.setPromptText("Resolution Type");
        resolutionComboBox.setItems(FXCollections.observableArrayList(
            "HD: 720p", "Full HD: 1080p", "Ultra HD: 2160p"
        ));
        
        // HBox for screentype selection
        VBox resolutionSelectionBox = new VBox(5);
        resolutionSelectionBox.setPrefHeight(5);
        resolutionSelectionBox.getChildren().addAll(resolutionLabel, resolutionComboBox);
        
        //Label for Displaysize
        Label displaySizeLabel = new Label("Display Size:");
        
        //ComboBox for displaysize
        ComboBox<String> displaySizeComboBox = new ComboBox<>();
        displaySizeComboBox.setPromptText("Display Size");
        displaySizeComboBox.setItems(FXCollections.observableArrayList(
            "Small: <34 Inches", "Medium: <55 Inches", "Large: <75 Inches"
        ));
        
        //HBox for Display Size selection
        VBox DisplaySizeSelectionBox = new VBox(5);
        DisplaySizeSelectionBox.getChildren().addAll(displaySizeLabel, displaySizeComboBox);
        
        HBox ResolutionDisplaySizeBox = new HBox(170);
        ResolutionDisplaySizeBox.getChildren().addAll(DisplaySizeSelectionBox, resolutionSelectionBox);
        
        // Label for screen types
        Label screenTypeLabel = new Label("Screen Type:");
        
        //ComboBox for Screentype
        ComboBox<String> screenTypeComboBox = new ComboBox<>();
        screenTypeComboBox.setPromptText("Screen Type");
        screenTypeComboBox.setItems(FXCollections.observableArrayList(
            "LED", "OLED", "QLED"
        ));
        
        // Create form layout 
        VBox FormLayout = new VBox(8);
        FormLayout.setPadding(new Insets(20));
        FormLayout.getChildren().addAll(AddProductText, lblProductNumber, tfProductNumber, lblProductName, tfProductName, lblProductPrice, tfProductPrice, QuantityEERatingBox, ResolutionDisplaySizeBox, screenTypeLabel, screenTypeComboBox);
        
        // Create HBox for buttons
        HBox ButtonLayout = new HBox(20);
        ButtonLayout.setLayoutX(420);
        ButtonLayout.setLayoutY(500);
        
        // Add save button 
        Button SaveBtn = new Button("Save");
        SaveBtn.setOnAction(e -> {
        try {
        	int productNumber = Integer.parseInt(tfProductNumber.getText());
        	String productName = tfProductName.getText();
        	Double productPrice = Double.parseDouble(tfProductPrice.getText());
        	String productSize = displaySizeComboBox.getValue();
        	int productQuantity = Integer.parseInt(tfQuantity.getText());
        	String productResolution = resolutionComboBox.getValue();
        	String productType = screenTypeComboBox.getValue();
        	 int productRating = 0;
        	    ToggleButton selectedToggleButton = (ToggleButton) EERatingGroup.getSelectedToggle();
        	    if (selectedToggleButton != null) {
        	        productRating = Integer.parseInt(selectedToggleButton.getText());
        	    }

        	TV tv = new TV(
        			productNumber, productName, productQuantity, productPrice, productType, productResolution, productSize, productRating
        			);
        	// Add the new tv object to the productList
        	product.add(tv);

        	// Optionally, display a confirmation message
        	System.out.println("Product added: " + productName);
        	FormStage.close();
        	ContinueAdd();
        	showUpdateBox(FormStage, "Product Added!");
    	}catch(NumberFormatException invalid) {
				showAlertBox( FormStage, "Invalid Input detected. Please try again!");
    	}

        });

        // Add Back button 
        Button BackBtn = new Button("Back");
        BackBtn.setOnAction(e -> {
        	ProductSelection();
        	FormStage.close();
        	});
        ButtonLayout.getChildren().addAll(BackBtn, SaveBtn);
        
        Pane AddProductFormpane = new Pane();
        AddProductFormpane.getChildren().addAll(FormLayout,ButtonLayout);
        AddProductFormpane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        AddProductFormpane.setPadding(new Insets(20));
        
        // Create the scene 
        Scene AddProductFormScene = new Scene( AddProductFormpane, 560, 550);
        FormStage.setScene(AddProductFormScene);
        
        // Display the popup window
        FormStage.show();
    }
    
    private void ContinueAdd() {
    	
    	Stage ContinueAddStage = new Stage();
        ContinueAddStage.initModality(Modality.APPLICATION_MODAL);
        ContinueAddStage.setTitle("Save Confirmation");
        
        // Add content to the form 
        Text Confirmationtxt = new Text("Do You Wish To Add More Products?");
        Confirmationtxt.setFont(Font.font("Arial", 16));
        Confirmationtxt.setLayoutX(25);
        Confirmationtxt.setLayoutY(60);
    	
        // Create HBox for buttons
        HBox ButtonLayout = new HBox(10);
        ButtonLayout.setLayoutX(88);
        ButtonLayout.setLayoutY(100);
        
        // Add Back button 
        Button NoBtn = new Button("No");
        NoBtn.setPrefWidth(60);
        NoBtn.setOnAction(e -> {
        	ContinueAddStage.close();
        	});
        
        // Add save button 
        Button YesBtn = new Button("Yes");
        YesBtn.setPrefWidth(60);
        YesBtn.setOnAction(e -> {
        	ProductSelection();
        	ContinueAddStage.close();
        });

        ButtonLayout.getChildren().addAll(YesBtn, NoBtn);
        
        Pane AddProductFormpane = new Pane();
        AddProductFormpane.getChildren().addAll(Confirmationtxt, ButtonLayout);
        AddProductFormpane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        AddProductFormpane.setPadding(new Insets(20));
        
        // Create the scene 
        Scene AddProductFormScene = new Scene( AddProductFormpane,300, 150);
        ContinueAddStage.setScene(AddProductFormScene);
        
        // Display the popup window
        ContinueAddStage.show();
    }
   
    // Method to View for the product
    private static void displayViewFunction(Stage primaryStage) {
    	VBox vbox_view = new VBox();
        vbox_view.setLayoutX(100);
        vbox_view.setLayoutY(100);
        vbox_view.setAlignment(Pos.CENTER);
    	displayProducts();
    		
    	if (product.isEmpty()) {
    	showAlertBox( primaryStage, "List Is Empty!");
  	   }else{
  		 // Create the Text node
  		 Text text_get_info = new Text("Please fill in the product item number want to edit:");
  		 text_get_info.setWrappingWidth(300);

  		 // Create the TextField
  		 TextField text_get_view = new TextField();
  		 text_get_view.setPrefWidth(100);
  		 text_get_view.setAlignment(Pos.CENTER_LEFT);

  		 // Create an HBox to hold the Text and TextField
  		 HBox hbox = new HBox(5); // 10 pixels of spacing between elements
  		 hbox.getChildren().addAll(text_get_info, text_get_view);

  		 // Add the HBox to the VBox
  		 vbox_view.getChildren().add(hbox);
    	
    	HBox hbox_view1 = new HBox();
    	HBox hbox_view2 = new HBox();
    	HBox hbox_view3 = new HBox();
    	HBox hbox_view4 = new HBox();
    	HBox hbox_view5 = new HBox();
    	HBox hbox_view6 = new HBox();
    	HBox hbox_view7 = new HBox();
    	HBox hbox_view8 = new HBox();
    	HBox hbox_view9 = new HBox();
    	HBox hbox_view10 = new HBox();
    	HBox hbox_view11 = new HBox();
    	HBox hbox_view12 = new HBox();
    	HBox hbox_view13 = new HBox();
    	HBox hbox_view14 = new HBox();
    	HBox hbox_view15 = new HBox();
    	HBox hbox_view16 = new HBox();
    	HBox hbox_view17 = new HBox();
    	HBox hbox_view18 = new HBox();
    	
    	//set the alignment of each hbox_view
    	hbox_view1.setAlignment(Pos.CENTER_LEFT);
    	hbox_view2.setAlignment(Pos.CENTER_LEFT);
    	hbox_view3.setAlignment(Pos.CENTER_LEFT);
    	hbox_view4.setAlignment(Pos.CENTER_LEFT);
    	hbox_view5.setAlignment(Pos.CENTER_LEFT);
    	hbox_view6.setAlignment(Pos.CENTER_LEFT);
    	hbox_view7.setAlignment(Pos.CENTER_LEFT);
    	hbox_view8.setAlignment(Pos.CENTER_LEFT);
    	hbox_view9.setAlignment(Pos.CENTER_LEFT);
    	hbox_view10.setAlignment(Pos.CENTER_LEFT);
    	hbox_view11.setAlignment(Pos.CENTER_LEFT);
    	hbox_view12.setAlignment(Pos.CENTER_LEFT);
    	hbox_view13.setAlignment(Pos.CENTER_LEFT);
    	hbox_view14.setAlignment(Pos.CENTER_LEFT);
    	hbox_view15.setAlignment(Pos.CENTER_LEFT);
    	hbox_view16.setAlignment(Pos.CENTER_LEFT);
    	hbox_view17.setAlignment(Pos.CENTER_LEFT);
    	hbox_view18.setAlignment(Pos.CENTER_LEFT);
    	
    	//check either user key in or not
    	text_get_view.setOnAction(e->{
    		if(!CheckTextFieldAndShowAlertInt(primaryStage,text_get_view,"Please type in integer!")) {
    			displayViewFunction(primaryStage);
    			return;
    		}
    		
    		//get integer
    		int index_view = Integer.parseInt(text_get_view.getText());
    		text_get_view.setEditable(false);
    		
    		//search for the product
    		for(int m=0;m<product.size();m++) {
    			if(index_view == product.get(m).getItemNumber()) {
    				int size = m;
    				
    				Text text_view_edit1 = new Text("Item number:");
    				text_view_edit1.setWrappingWidth(100);
    				TextField text_view_item = new TextField(Integer.toString(product.get(size).getItemNumber()));
    				text_view_item.setPrefWidth(100);
    				
    				hbox_view1.getChildren().addAll(text_view_edit1,text_view_item);
    				vbox_view.getChildren().add(hbox_view1);
    				
    				Text text_view_edit2 = new Text("Product Name:");
    				text_view_edit2.setWrappingWidth(100);
    				TextField text_view_product = new TextField(product.get(size).getProductName());
    				text_view_product.setPrefWidth(100);
    				hbox_view2.getChildren().addAll(text_view_edit2,text_view_product);
    				vbox_view.getChildren().add(hbox_view2);
    				
    				Text text_view_edit3 = new Text("Quantity Available:");
    				text_view_edit3.setWrappingWidth(100);
    				TextField text_view_quantity = new TextField(Integer.toString(product.get(size).getQuantityAvailable()));
    				text_view_quantity.setPrefWidth(100);
    				hbox_view3.getChildren().addAll(text_view_edit3,text_view_quantity);
    				vbox_view.getChildren().add(hbox_view3);
    				
    				Text text_view_edit4 = new Text("Product Price:");
    				text_view_edit4.setWrappingWidth(100);
    				TextField text_view_price = new TextField(Double.toString(product.get(size).getProductPrice()));
    				text_view_price.setPrefWidth(100);
    				hbox_view4.getChildren().addAll(text_view_edit4,text_view_price);
    				vbox_view.getChildren().add(hbox_view4);
    				
    				Text text_view_edit5 = new Text("Product Status:");
    				text_view_edit5.setWrappingWidth(100);
    				TextField text_view_product_status = new TextField(Boolean.toString(product.get(size).isProductStatus()));
    				text_view_product_status.setPrefWidth(100);
    				hbox_view5.getChildren().addAll(text_view_edit5,text_view_product_status);
    				vbox_view.getChildren().add(hbox_view5);
    				
    				//variable for product which is TV
    				TextField text_view_product_screen_type = new TextField();
    				TextField text_view_product_resolution = new TextField();
    				TextField text_view_product_display_size = new TextField();
    				TextField text_view_product_rating_TV = new TextField();
    				//set the width of the text field
    				text_view_product_screen_type.setPrefWidth(100);
    				text_view_product_resolution.setPrefWidth(100);
    				text_view_product_display_size.setPrefWidth(100);
    				text_view_product_rating_TV.setPrefWidth(100);
    				
    				if(product.get(size) instanceof TV) {
        				
    					TV TV_item = (TV)product.get(size);
    					Text text_view_edit6 = new Text("Product Screen Type:");
    					text_view_edit6.setWrappingWidth(100);
    					text_view_product_screen_type.setText(TV_item.getProductType());
    					hbox_view6.getChildren().addAll(text_view_edit6,text_view_product_screen_type);
    					vbox_view.getChildren().add(hbox_view6);
    	
    					Text text_view_edit7 = new Text("Product Resolution:");
    					text_view_edit7.setWrappingWidth(100);
    					text_view_product_resolution.setText(TV_item.getProductResolution());
    					hbox_view7.getChildren().addAll(text_view_edit7,text_view_product_resolution);
    					vbox_view.getChildren().add(hbox_view7);
    	
    					Text text_view_edit8 = new Text("Product Display Size:");
    					text_view_edit8.setWrappingWidth(100);
    					text_view_product_display_size.setText(TV_item.getProductSize());
    					hbox_view8.getChildren().addAll(text_view_edit8,text_view_product_display_size);
    					vbox_view.getChildren().add(hbox_view8);
    					
    					Text text_view_edit9 = new Text("Product Rating:");
    					text_view_edit9.setWrappingWidth(100);
    					text_view_product_rating_TV.setText(Integer.toString(TV_item.getProductRating()));
    					hbox_view9.getChildren().addAll(text_view_edit9,text_view_product_rating_TV);
    					vbox_view.getChildren().add(hbox_view9);
    				}

					//variable for product which is refrigerator
    				TextField text_view_product_design = new TextField();
    				TextField text_view_product_color = new TextField();
    				TextField text_view_product_capacity = new TextField();
    				TextField text_view_product_rating_ref = new TextField();
    				//set the width of the text field
    				text_view_product_design.setPrefWidth(100);
    				text_view_product_color.setPrefWidth(100);
    				text_view_product_capacity.setPrefWidth(100);
    				text_view_product_rating_ref.setPrefWidth(100);
    				
    				if(product.get(size) instanceof Refrigerator) {
        			
    					Refrigerator refrigerator_item = (Refrigerator)product.get(m);
    					Text text_view_edit10 = new Text("Product Door Design:");
    					text_view_edit10.setWrappingWidth(100);
    					text_view_product_design.setText(refrigerator_item.getproductDesign());
    					hbox_view10.getChildren().addAll(text_view_edit10,text_view_product_design);
    					vbox_view.getChildren().add(hbox_view10);
    	
    					Text text_view_edit11 = new Text("Product Color:");
    					text_view_edit11.setWrappingWidth(100);
    					text_view_product_color.setText(refrigerator_item.getColour());
    					hbox_view11.getChildren().addAll(text_view_edit11,text_view_product_color);
    					vbox_view.getChildren().add(hbox_view11);
    					
    					Text text_view_edit12 = new Text("Product Capacity:");
    					text_view_edit12.setWrappingWidth(100);
    					text_view_product_capacity.setText(refrigerator_item.getCapacity());
    					hbox_view12.getChildren().addAll(text_view_edit12,text_view_product_capacity);
    					vbox_view.getChildren().add(hbox_view12);
    					
    					Text text_view_edit13 = new Text("Product Rating:");
    					text_view_edit13.setWrappingWidth(100);
    					text_view_product_rating_ref.setText(Integer.toString(refrigerator_item.getProductRating()));
    					hbox_view13.getChildren().addAll(text_view_edit13,text_view_product_rating_ref);
    					vbox_view.getChildren().add(hbox_view13);
    				}
    				
					
					//variable for product which is dishwasher
    				TextField text_view_product_capacity2 = new TextField();
    				TextField text_view_product_has_cutlery_drawer = new TextField();
    				TextField text_view_product_rating_dish = new TextField();
    				//set the width of the text field
    				text_view_product_capacity2.setPrefWidth(100);
    				text_view_product_has_cutlery_drawer.setPrefWidth(100);
    				text_view_product_rating_dish.setPrefWidth(100);
    				
    				if(product.get(size) instanceof Dishwasher) {
        				
    					Dishwasher dishwasher_item = (Dishwasher)product.get(m);
    					Text text_view_edit14 = new Text("Product Capacity:");
    					text_view_edit14.setWrappingWidth(100);
    					text_view_product_capacity2.setText(Integer.toString(dishwasher_item.getProductCapacity()));
    					hbox_view14.getChildren().addAll(text_view_edit14,text_view_product_capacity2);
    					vbox_view.getChildren().add(hbox_view14);
    	
    					Text text_view_edit15 = new Text("Product Has or Not Has Cutlery Drawer:");
    					text_view_edit15.setWrappingWidth(100);
    					text_view_product_has_cutlery_drawer.setText(Boolean.toString(dishwasher_item.hasCutleryDrawer()));
    					hbox_view15.getChildren().addAll(text_view_edit15,text_view_product_has_cutlery_drawer);
    					vbox_view.getChildren().add(hbox_view15);
    					
    					Text text_view_edit16 = new Text("Product Rating:");
    					text_view_edit16.setWrappingWidth(100);
    					text_view_product_rating_dish.setText(Integer.toString(dishwasher_item.getProductRating()));
    					hbox_view16.getChildren().addAll(text_view_edit16,text_view_product_rating_dish);
    					vbox_view.getChildren().add(hbox_view16);
    				}
    				
					//variable for product which is air conditioner
    				TextField text_view_product_capacity3 = new TextField();
    				TextField text_view_product_rating_air = new TextField();
    				//set the width of text field
    				text_view_product_capacity3.setPrefWidth(100);
    				text_view_product_rating_air.setPrefWidth(100);
    				
    				if(product.get(size) instanceof AirConditioner) {

    					AirConditioner dishwasher_item = (AirConditioner)product.get(m);
    					Text text_view_edit17 = new Text("Product Capacity:");
    					text_view_edit17.setWrappingWidth(100);
    					text_view_product_capacity3.setText(Integer.toString(dishwasher_item.getProductCapacity()));
    					hbox_view17.getChildren().addAll(text_view_edit17,text_view_product_capacity3);
    					vbox_view.getChildren().add(hbox_view17);
    	
    					Text text_view_edit18 = new Text("Product Rating:");
    					text_view_edit18.setWrappingWidth(100);
    					text_view_product_rating_air.setText(Integer.toString(dishwasher_item.getProductRating()));
    					hbox_view18.getChildren().addAll(text_view_edit18,text_view_product_rating_air);
    					vbox_view.getChildren().add(hbox_view18);
    					
    				}
    	
    				Button btn_save = new Button("Save");
    				vbox_view.getChildren().add(btn_save);
    				btn_save.setOnAction(ex->{
    					
					//make sure every text field is in correct format and no empty
					if(!CheckTextFieldAndShowAlertInt(primaryStage,text_view_item,"Please key in Item Number!")
							||!CheckTextFieldAndShowAlertString(primaryStage,text_view_product,"Please key in Product Name!")
							||!CheckTextFieldAndShowAlertInt(primaryStage,text_view_quantity,"Please key in Quantity!")
							||!CheckTextFieldAndShowAlertDouble(primaryStage,text_view_price,"Please key in Price!")
							||!CheckTextFieldAndShowAlertBoolean(primaryStage,text_view_product_status,"Please key in Product Status! (True or False)")){
								return;
					}
					else {
						if(product.get(size) instanceof TV) {
							if(!CheckTextFieldAndShowAlertString(primaryStage,text_view_product_screen_type,"Please key in Screen Type!")
	    							||!CheckTextFieldAndShowAlertString(primaryStage,text_view_product_resolution,"Please key in Resolution!")
	    							||!CheckTextFieldAndShowAlertString(primaryStage,text_view_product_display_size,"Please key in Product Display Size!")
	    							||!CheckTextFieldAndShowAlertString(primaryStage,text_view_product_rating_TV,"Please key in Product Rating!")) {
								return;
							}
						}
						if(product.get(size) instanceof Refrigerator) {
							if(!CheckTextFieldAndShowAlertString(primaryStage,text_view_product_color, "Please key in Product Color!")
							||!CheckTextFieldAndShowAlertString(primaryStage,text_view_product_capacity, "Please key in Product Capacity!")
							||!CheckTextFieldAndShowAlertString(primaryStage,text_view_product_design, "Please key in Product Capacity!")
							||!CheckTextFieldAndShowAlertInt(primaryStage,text_view_product_rating_ref, "Please key in Product Rating!")) {
								return;
							}
						}
						if(product.get(size) instanceof AirConditioner) {
							if(!CheckTextFieldAndShowAlertInt(primaryStage,text_view_product_capacity3,"Please key in Product Capacity!")
							||!CheckTextFieldAndShowAlertInt(primaryStage,text_view_product_rating_air,"Please key in Product Rating!")) {
								return;
							}	
						}	
						if(product.get(size) instanceof Dishwasher) {
							if(!CheckTextFieldAndShowAlertInt(primaryStage,text_view_product_capacity2, "Please key in Product Capacity!")
							||!CheckTextFieldAndShowAlertBoolean(primaryStage,text_view_product_has_cutlery_drawer, "Please key in Product Has or Not Cutlery Drawer!")
							||!CheckTextFieldAndShowAlertInt(primaryStage,text_view_product_rating_dish, "Please key in Product Rating!")) {
								return;
							}		
					}
    					
					//update the new value
					int new_item = Integer.parseInt(text_view_item.getText());//integer
					product.get(size).setItemNumber(new_item);
					
					String new_product = text_view_product.getText();//string
					product.get(size).setProductName(new_product);
					
					int new_quantity = Integer.parseInt(text_view_quantity.getText());//integer
					product.get(size).setQuantityAvailable(new_quantity);
					
					double new_price = Double.parseDouble(text_view_price.getText());//double
					product.get(size).setProductPrice(new_price);
					
					boolean new_product_status = Boolean.parseBoolean(text_view_product_status.getText());//boolean
					product.get(size).setProductStatus(new_product_status);
					
	
					if(product.get(size) instanceof TV) {
				        TV TV_item = (TV) product.get(size);

						((TV) product.get(size)).setProductType(text_view_product_screen_type.getText());//string
						((TV) product.get(size)).setProductResolution(text_view_product_resolution.getText());//string
						((TV) product.get(size)).setProductSize(text_view_product_display_size.getText());//string
						((TV) product.get(size)).setProductRating(Integer.parseInt(text_view_product_rating_TV.getText()));//integer
					}

					if(product.get(size) instanceof Refrigerator) {
						Refrigerator refrigerator_item = (Refrigerator)product.get(size);
						
						((Refrigerator) product.get(size)).setproductDesign(text_view_product_design.getText());//string
						((Refrigerator) product.get(size)).setColour(text_view_product_color.getText());//string
						((Refrigerator) product.get(size)).setCapacity(text_view_product_capacity.getText());//string	
						((Refrigerator) product.get(size)).setProductRating(Integer.parseInt(text_view_product_rating_ref.getText()));//integer
					}
					
					if(product.get(size) instanceof AirConditioner) {
						AirConditioner air_item = (AirConditioner)product.get(size);
						
						((AirConditioner) product.get(size)).setProductCapacity(Integer.parseInt(text_view_product_capacity3.getText()));//integer
						((AirConditioner) product.get(size)).setProductRating(Integer.parseInt(text_view_product_rating_air.getText()));//integer
					}
					
					if(product.get(size) instanceof Dishwasher) {
						Dishwasher dish_item = (Dishwasher)product.get(size);
						
						((Dishwasher) product.get(size)).setProductCapacity(Integer.parseInt(text_view_product_capacity2.getText()));//int
						((Dishwasher) product.get(size)).setHasCutleryDrawer(Boolean.parseBoolean(text_view_product_has_cutlery_drawer.getText()));//bool
						((Dishwasher) product.get(size)).setProductRating(Integer.parseInt(text_view_product_rating_dish.getText()));//int
					}
    						
					showUpdateBox(primaryStage, "Changes Saved!");
					rightpane.getChildren().clear();
					MenuPane(primaryStage);
    				}
    					
    			});
    			break;
    		}
    			
    			//cannot find the product index number
				if((m == (product.size()-1))&& index_view != product.get(m).getItemNumber()) {
					showAlertBox(primaryStage,"No Index Number Found!");
					text_get_view.setEditable(true);
				}
    	}
    		});
    	
    	}
    	gridpane_add_deduce.add(vbox_view, 0, 1);
        gridpane_add_deduce.setAlignment(Pos.CENTER);
    	rightpane.setCenter(gridpane_add_deduce);
    }

	// Method to display an Alert dialog with the specified content_check,alert type, title, and content
    private static boolean CheckTextFieldAndShowAlertInt(Stage primaryStage,TextField content_check, String content) {
    	String content_string = content_check.getText();

    	if(!content_string.isEmpty()) {
    		
    		// Check it is integer or not
        	try {
        		int value = Integer.parseInt(content_string);
        		return true;
        	}catch(NumberFormatException ex) {
        		showAlertBox(primaryStage, content);
        		return false;
        	}
        }
    	else {
    		showAlertBox(primaryStage, content);
            return false;
    	}
    }
    
    private static boolean CheckTextFieldAndShowAlertString(Stage primaryStage, TextField content_check, String content) {

    	String content_string = content_check.getText();

    	if(!content_string.isEmpty()) {
    		// Check is integer or not
        	try {
        		String value = content_string;
        		return true;
        	}catch(NumberFormatException ex) {
        		showAlertBox(primaryStage, content);
        		return false;
        	}
        }
    	else {
    		showAlertBox(primaryStage, content);
            return false;
    	}

    }
    
    private static boolean CheckTextFieldAndShowAlertBoolean(Stage primaryStage, TextField content_check, String content) {
        String content_string = content_check.getText().trim();

        if (!content_string.isEmpty()) {
            // Check if the input is either "true" or "false"
            if (content_string.equalsIgnoreCase("true") || content_string.equalsIgnoreCase("false")) {
                return true;
            } else {
                showAlertBox(primaryStage, content); // Show alert for invalid input
                return false;
            }
        } else {
            showAlertBox(primaryStage, content); // Show alert for empty input
            return false;
        }
    }

    private static boolean CheckTextFieldAndShowAlertDouble(Stage primaryStage, TextField content_check, String content) {

    	String content_string = content_check.getText().trim();

    	if(!content_string.isEmpty()) {
    		// Check is integer or not
        	try {
        		Double value = Double.parseDouble(content_string);
        		return true;
        	}catch(NumberFormatException ex) {
        		showAlertBox(primaryStage, content);
        		return false;
        	}
        }
    	else {
    		showAlertBox(primaryStage, content);
            return false;
    	}
    }
	
	// Method to Add Stock for the selected product
    private static void displayAddFunction(Stage primaryStage) {
    	Alert alert_add = new Alert(AlertType.ERROR);
        alert_add.setTitle("NULL");
        alert_add.setHeaderText(null);

    	VBox vbox_add = new VBox();
    	
    	  if (product.isEmpty()) {
    		  showAlertBox( primaryStage, "List Is Empty!");
    	  }else{
		    	// Create a TextInputDialog to get product index
		    	Text text_add = new Text("Enter the index of the product to add stock from:");
		    	TextField textfield_add = new TextField();
		    	vbox_add.getChildren().addAll(text_add,textfield_add);
		    	
		    	textfield_add.setOnAction(e->{
		    		
		    		if(!CheckTextFieldAndShowAlertInt(primaryStage,textfield_add,"Invalid Index Number!")) {
		    			return;
		    		}
    		
		    		//change string to integer
		    		int product_add = Integer.parseInt(textfield_add.getText());
		    		
					//find the product
					for(int i=0;i<product.size();i++) {
						if(product_add == product.get(i).getItemNumber()) {
							textfield_add.setEditable(false);
			        		Text text_add2 = new Text("Enter the quantity to add:");
			        		TextField textfield_add2 = new TextField();
			        		vbox_add.getChildren().addAll(text_add2,textfield_add2);
			        		int product_list_add = i;
			        		textfield_add2.setOnAction(ex->{
			        			//error handling
			        			if(!CheckTextFieldAndShowAlertInt(primaryStage,textfield_add2,"Invalid Quantity!")) {
		    	        			return;
		    	        		}
			        			int quantity = Integer.parseInt(textfield_add2.getText());
			        			if(quantity>0) {
			        				product.get(product_list_add).addQuantity(quantity);
		    						rightpane.getChildren().clear();
		    						gridpane_add_deduce.getChildren().clear();
		    						MenuPane(primaryStage);
			        			}
			        			else {
			        				alert_add.setContentText("Invalid Quantity!");
			        				alert_add.showAndWait();
			        				return;
			        			}
		
			        		});
			        		}}});
    	    }
	    	gridpane_add_deduce.add(vbox_add, 0, 0);
	    	GridPane.setHalignment(vbox_add, HPos.CENTER);
	    	GridPane.setValignment(vbox_add, VPos.CENTER);
	    	rightpane.setCenter(gridpane_add_deduce);
    }
 
    // Method to deduct stock for a selected product
    private static void displayDeductFunction(Stage primaryStage) {
    	
    	Alert alert_deduct = new Alert(AlertType.ERROR);
        alert_deduct.setTitle("NULL");
        alert_deduct.setHeaderText(null);

    	VBox vbox_deduct = new VBox();
    	
    	  if (product.isEmpty()) {
    		  showAlertBox( primaryStage, "List Is Empty!");
    	    }else
    	    {
    	
    	// Create a TextInputDialog to get product index
    	Text text_deduct = new Text("Enter the index of the product to deduct stock from:");
    	TextField textfield_deduct = new TextField();
    	//Optional<String> result = dialog.showAndWait();
    	vbox_deduct.getChildren().addAll(text_deduct,textfield_deduct);
    	
    	textfield_deduct.setOnAction(e->{
    		//error handling
    		if(!CheckTextFieldAndShowAlertInt(primaryStage,textfield_deduct,"Invalid Index Number!")) {
    			return;
    		}
		
			int product_deduct = Integer.parseInt(textfield_deduct.getText());
			//find product
			for(int i=0;i<product.size();i++) {
	        	//if product found
	        	if(product_deduct == product.get(i).getItemNumber()) {
	        		
	        		Text text_deduct2 = new Text("Enter the quantity to deduct:");
	        		TextField textfield_deduct2 = new TextField();
	        		vbox_deduct.getChildren().addAll(text_deduct2,textfield_deduct2);
	        		int product_list_deduct = i;
	        		textfield_deduct2.setOnAction(ex->{
	        			//error handling
    	        		if(!CheckTextFieldAndShowAlertInt(primaryStage,textfield_deduct2,"Invalid Quantity!")) {
    	        			return;
    	        		}
    	        		
	        			int quantity = Integer.parseInt(textfield_deduct2.getText());
	        			//if correct quantity
	        			if(quantity > 0 && quantity < product.get(product_list_deduct).getQuantityAvailable()) {
	        				product.get(product_list_deduct).deductQuantity(quantity);
	    					rightpane.getChildren().clear();
	    					gridpane_add_deduce.getChildren().clear();	
	        				showUpdateBox(primaryStage, "Changes Saved!");
	    					MenuPane(primaryStage);
	        				}
	        			else {
	        				alert_deduct.setContentText("Invalid Quantity!");
	        				alert_deduct.showAndWait();
	        				return;}
    	        		});}

	        	//if loop until last array list and still cannot match item number
	        	if(product_deduct != product.get(i).getItemNumber()&&(i == product.size()-1)) {
	    	        //error handling for no product found
	                alert_deduct.setContentText("No Item Number Is Match!");
	                alert_deduct.showAndWait();
	    	        return;
	        	}	
			}
		
	});
    	    }
    	gridpane_add_deduce.add(vbox_deduct,0,0);
    	GridPane.setHalignment(vbox_deduct, HPos.CENTER);
    	GridPane.setValignment(vbox_deduct, VPos.CENTER);
    	rightpane.setCenter(gridpane_add_deduce);
    }
    
    // Method to set the status of a product to discontinued
    private static void displayDiscontinueFunction(Stage primaryStage) {
    	VBox vbox_discontinue = new VBox();
    	  if (product.isEmpty()) {
    		  showAlertBox( primaryStage, "List Is Empty!");
    	    }else
    	    {
    	// Create a TextInputDialog to get product index
    	Text text_discontinue = new Text("Discontinue Product");
    	Text text_discontinue2 = new Text("Please enter the index of the product to discontinue:");
    	TextField textfield_discontinue = new TextField();
    	vbox_discontinue.getChildren().addAll(text_discontinue,text_discontinue2,textfield_discontinue);
    	
    	textfield_discontinue.setOnAction(e->{
    		if(!CheckTextFieldAndShowAlertInt(primaryStage,textfield_discontinue,"Invalid Index!")) {
    			return;
    		}
    		int item_discontinue = Integer.parseInt(textfield_discontinue.getText());
    		Product productToDiscontinue = null;
    		for (Product p : product) {
                if (p.getItemNumber() == item_discontinue) {
                    productToDiscontinue = p;
                    break;
                }
    		}
    		if(productToDiscontinue != null) {
    			// Set product status to discontinued
                productToDiscontinue.setProductStatus(false);
                //displayProducts(); // Update displayed products
                rightpane.getChildren().clear();
				gridpane_add_deduce.getChildren().clear();
                showUpdateBox(primaryStage, "Changes Saved!");
                MenuPane(primaryStage);
    		}
    		else {
                showAlertInRightPane(AlertType.ERROR, "Error", "Product is not found with the given item number.");
            }


    	});
    	    }
    	gridpane_add_deduce.add(vbox_discontinue,0,1);
    	GridPane.setHalignment(vbox_discontinue, HPos.CENTER);
    	GridPane.setValignment(vbox_discontinue, VPos.CENTER);
    	rightpane.getChildren().add(gridpane_add_deduce);
    }

    // Method to display a list of products in an Alert dialog
    private static void displayProducts() {
    	//set properties
    	Alert alert_display = new Alert(AlertType.ERROR);
    	alert_display.setTitle("Error");
    	alert_display.setHeaderText(null);
    	//set properties
    	VBox vbox_display = new VBox();
    	TextArea[] textArea = new TextArea[product.size()];
    	//loop for each product in array list
    	for(int i=0;i<product.size();i++) {
    		//check got product or not
    	    	if(product.isEmpty()) {

    	            alert_display.setContentText("No Stock Detect!");
    	            alert_display.showAndWait();
    	        	break;
    	    	}
    	    	String info_display = product.get(i).toString();
    	    	textArea[i] = new TextArea();
        	    textArea[i].setText(info_display);
        	    vbox_display.setPrefSize(400, 500);
        	    vbox_display.getChildren().add(textArea[i]);
    		}
    		//call function toString and then put into text box
    	
    	gridpane_add_deduce.add(vbox_display, 0, 0);	
    	GridPane.setHalignment(vbox_display, HPos.CENTER);
    	GridPane.setValignment(vbox_display, VPos.CENTER);
    	return;
    }

    // Method to display an Alert dialog with the specified type, title, and content
    private static void showAlertInRightPane(AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private static void UserMenu(Stage primaryStage) {
   
    	// Background image
        Image Logoimg=new Image("resource/SMS_Logo.png");
        
        // Create an ImageView for the image
        ImageView SMSLogoimage = new ImageView(Logoimg);
        SMSLogoimage.setFitWidth(490); 
        SMSLogoimage.setPreserveRatio(true);
        
        // Display Welcome Message
        Text welcometext=new Text("Welcome to SMS!");
        welcometext.setFont(Font.font("Courier New", FontWeight.BOLD, 50)); 
        welcometext.setLayoutX(35);
        welcometext.setLayoutY(80);
        
        // Display Time
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedTime = currentTime.format(timeFormatter);
        Label timeLabel = new Label("Time: " + formattedTime);
        timeLabel.setFont(Font.font("Times New Roman", 18)); // Set font size to 16
        timeLabel.setLayoutX(346);
        timeLabel.setLayoutY(100);
        
        // Display Date
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = now.format(formatter);
        Label dateLabel = new Label("Date: " + formattedDate);
        dateLabel.setFont(Font.font("Times New Roman", 18)); // Set font size to 16
        dateLabel.setLayoutX(90);
        dateLabel.setLayoutY(100);
        
        // Display Group Member Name
        ArrayList<String> groupMembers = new ArrayList<>();
        groupMembers.add("Koay Gaik Hen");
        groupMembers.add("Lau Kar Siong");
        groupMembers.add("Lennon Tan Yong Hwa");
        groupMembers.add("Lee Hui Xin");
        Collections.sort(groupMembers);

        Text MemberNametext = new Text("Group Members:\n");
        MemberNametext.setFont(Font.font("Times New Roman", 20));
        for (String member : groupMembers) {
            MemberNametext.setText(MemberNametext.getText() + member + "\n");
        }
        MemberNametext.setLayoutX(90);
        MemberNametext.setLayoutY(150);
        
        // Display Group Member ID
        ArrayList<Integer> MemberID = new ArrayList<>();
        MemberID.add(2105483);
        MemberID.add(2003517);
        MemberID.add(2207524);
        MemberID.add(2207342);
        Text MemberIDtext = new Text("ID:\n");
        MemberIDtext.setFont(Font.font("Times New Roman", 20));
        for (Integer member : MemberID) {
            MemberIDtext.setText(MemberIDtext.getText() + member + "\n");
        }
        MemberIDtext.setLayoutX(350);
        MemberIDtext.setLayoutY(150);
        
        // Label for Info
        Label lblInfo = new Label("Please Enter Your Information:");
        lblInfo.setLayoutX(90);
        lblInfo.setLayoutY(275);
        
        // Label for FirstName
        Label lblFirstName = new Label("First Name:");
        lblFirstName.setLayoutX(90);
        lblFirstName.setLayoutY(300);
        
        // Adjust TextFields for tfFirstName.
        TextField tfFirstName= new TextField();
        tfFirstName.setPromptText("Enter First Name");
        tfFirstName.setPrefWidth(340);
        tfFirstName.setLayoutX(90);
        tfFirstName.setLayoutY(325);
        
        // Display ErrorTxt
        Text ErrorTxt = new Text();
        ErrorTxt.setFont(Font.font("Times New Roman", 15));
        ErrorTxt.setFill(Color.RED); 
        ErrorTxt.setLayoutX(90);
        ErrorTxt.setLayoutY(450);
        
        // Next Button
        Button Nextbtn = new Button("Next");
        Nextbtn.setLayoutX(330);
        Nextbtn.setLayoutY(500);
        Nextbtn.setDisable(true); // Initially disable the Next button
        Nextbtn.setOnAction(e -> {
        	DisplayMenu(primaryStage);
        });
        
        // Label for Surname
        Label lblSurname = new Label("Surname:");
        lblSurname.setLayoutX(90);
        lblSurname.setLayoutY(360);
                
        // Adjust TextFields for tfSurame.
        TextField tfSurname = new TextField();
        tfSurname.setPromptText("Enter Surname");
        tfSurname.setPrefWidth(340);
        tfSurname.setLayoutX(90);
        tfSurname.setLayoutY(385);
        
        // Add Event Listener to tfFirstName
        tfFirstName.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
            	String fName = tfFirstName.getText().trim();
                String sName = tfSurname.getText().trim();
                boolean inputsNotEmpty = !fName.isEmpty() && !sName.isEmpty();
                if (inputsNotEmpty) {
                	userinfo.setName(fName,sName); 
                    userinfo.generateID();
                    ErrorTxt.setText(""); // Clear the error message
                    Nextbtn.setDisable(false); // Enable the Next button
                } else {
                    ErrorTxt.setText("Please Fill In All Details."); // Show an error message if any field is empty
                    Nextbtn.setDisable(true); // Disable the Next button
                }
            }
        });
        
        // Add EventListener for tfSurname
        tfSurname.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
            	 String fName = tfFirstName.getText().trim();
                 String sName = tfSurname.getText().trim();
                 boolean inputsNotEmpty = !fName.isEmpty() && !sName.isEmpty();
                 if (inputsNotEmpty) {
                	 userinfo.setName(fName, sName); 
                	 //userinfo.setName(tfFirstName.getText().trim(), tfSurname.getText().trim());
                	 userinfo.generateID();
                	 ErrorTxt.setText(""); // Clear the error message
                     Nextbtn.setDisable(false); // Enable the Next button
                 } else {
                     ErrorTxt.setText("Please Fill In All Details !!!"); // Show an error message if any field is empty
                     Nextbtn.setDisable(true); // Disable the Next button
                 }
            }
        });
        
        // Display User ID
        Label UserID = new Label();
        
        // Close Button
        Button Closebtn = new Button("Close");
        Closebtn.setLayoutX(400);
        Closebtn.setLayoutY(500);
        Closebtn.setOnAction(e -> primaryStage.close());
        
        // Create leftPane 
        Pane leftPane = new Pane();
        leftPane.getChildren().addAll(SMSLogoimage, UserID);
        
        // Create rightPane
        Pane rightPane=new Pane();
        rightPane.getChildren().addAll(welcometext,dateLabel, timeLabel, MemberNametext, MemberIDtext, lblInfo, lblFirstName, tfFirstName, lblSurname, tfSurname, ErrorTxt, Nextbtn, Closebtn);
        
        // Create a parent Pane layout to hold left and right panes
        HBox root = new HBox(leftPane, rightPane);
        root.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        
        // Create scene
        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setTitle("User Information");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private static void showAlertBox(Stage primaryStage, String textDisplay) {
		
	    Stage UpdateBoxStage = new Stage();
	    // Set window as modal dialog box
	    UpdateBoxStage.initModality(Modality.APPLICATION_MODAL);
	    // Set Title
	    UpdateBoxStage.setTitle("Error");
	
	    //create vertical box to get display message and button
	    VBox vbox_update = new VBox(10);
	    vbox_update.setAlignment(Pos.CENTER);
	    
	    //create text box to show the display message
	    Text UpdateText = new Text(textDisplay);
	    // Set Font
	    UpdateText.setFont(Font.font("Arial", 16));
	
	    //create button to close the dialog
	    Button CloseButton = new Button("Close");
	    CloseButton.setAlignment(Pos.CENTER);
	    CloseButton.setLayoutX(130);
	    CloseButton.setLayoutY(100);
	    CloseButton.setPrefHeight(30);
	    CloseButton.setPrefWidth(60);

	    // Event Handler on Close Button
	    CloseButton.setOnAction(e -> {
	    	UpdateBoxStage.close();
	    });
	    
	    //Add UpdateText and Close Button to the vertical box
	    vbox_update.getChildren().addAll(UpdateText,CloseButton);

	    Scene alertBoxScene = new Scene(vbox_update, 320, 150);
	    UpdateBoxStage.setScene(alertBoxScene);
	
	    UpdateBoxStage.show();
	}
    
    private static void showUpdateBox(Stage primaryStage, String textDisplay) {
		
	    Stage UpdateBoxStage = new Stage();
	    // Set window as modal dialog box
	    UpdateBoxStage.initModality(Modality.APPLICATION_MODAL);
	    // Set Title
	    UpdateBoxStage.setTitle("System");
	
	    //create vertical box to get display message and button
	    VBox vbox_update = new VBox(10);
	    vbox_update.setAlignment(Pos.CENTER);
	    
	    //create text box to show the display message
	    Text UpdateText = new Text(textDisplay);
	    // Set Font
	    UpdateText.setFont(Font.font("Arial", 16));
	
	    //create button to close the dialog
	    Button CloseButton = new Button("Close");
	    CloseButton.setAlignment(Pos.CENTER);
	    CloseButton.setLayoutX(130);
	    CloseButton.setLayoutY(100);
	    CloseButton.setPrefHeight(30);
	    CloseButton.setPrefWidth(60);

	    // Event Handler on Close Button
	    CloseButton.setOnAction(e -> {
	    	UpdateBoxStage.close();
	    });
	    
	    //Add UpdateText and Close Button to the vertical box
	    vbox_update.getChildren().addAll(UpdateText,CloseButton);

	    Scene alertBoxScene = new Scene(vbox_update, 320, 150);
	    UpdateBoxStage.setScene(alertBoxScene);
	
	    UpdateBoxStage.show();
	}

    public static void main(String[] args) {
        launch(args);
    }
}