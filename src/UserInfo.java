public class UserInfo {
   
	private String FName;
	private String SName;
	private String userId;
	
	public void setName(String FName, String SName) {
		this.FName = FName;
	    this.SName = SName;
	}
	
    // Function to generate id
    public void generateID() {
        String FName = this.FName;
        String SName = this.SName;
      
        if (!FName.isEmpty() && !SName.isEmpty()) {
            if (SName.contains(" ")) {
                // Extract the first initial from the first name
                char firstInitial = FName.charAt(0);
                // Check if there is a space in the surname
                if (SName.contains(" ")) {
                    // If there is a space, take the last word as the surname
                    String[] sNameParts = SName.split(" ");
                    String surname = sNameParts[sNameParts.length - 1];
                    SName = surname;
                }
                // Concatenate first initial with the surname
                this.userId = firstInitial + SName;
            } else {
                this.userId = "Guest"; // Set default value if no space in name
            }
            	System.out.println(this.userId);
          }
        
       }
    
    public void setUserId(String userId) {
    	this.userId = userId;
    }
    
    public String getUserId() {
		return this.userId;
    	
    }
}