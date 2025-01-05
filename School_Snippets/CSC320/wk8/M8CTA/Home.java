package wk8.M8CTA;

import java.io.FileWriter;
import java.io.IOException;

public class Home {
  // fields describing a home and its location:  
    private String modelName;
    private String saleStatus;
    private String address;
    private String city;
    private String state;
    private int zipCode;
    private int squareFeet;

  // the requirements in the rubric mention add and list inventory, 
  // However this is the "HOME" class so it's scope should be limited to the HOME Object that focuses on 1 specific Home at a time  

  // Within this scope, the "ADD" logic of the Home class that is in charge of creating the home, should be its constructor. 
  // I think it wouldn't make much sense to add a separate method for it.  
  public Home(int squareFeet, String address, String city, String state, int zipCode, String modelName, String saleStatus) {
        // Run validation for all required fields prior to setting the home information (it turned out longer than expected, so I abstracted it out to a new method)
        Boolean validationCheck = validateHomeFields(squareFeet, address, city, state, zipCode, modelName, saleStatus);
        if (validationCheck){
          this.squareFeet = squareFeet;
          this.address = address;
          this.city = city;
          this.state = state;
          this.zipCode = zipCode;
          this.modelName = modelName;
          this.saleStatus = saleStatus;
        } else {
          throw new IllegalArgumentException("Invalid home information, home could not be created.");
        }
  }

  private boolean validateHomeFields(int squareFeet, String address, String city, String state, int zipCode, String modelName, String saleStatus) {
      // Check if square feet is a valid and positive number
    if (squareFeet <= 0) {
        return false;
    }
    
    // Check if address is not null or empty
    if (address == null || address.isEmpty()) {
        return false;
    }
    
    // Check if city is not null or empty
    if (city == null || city.isEmpty()) {
        return false;
    }
    
    // Check if state is not null or empty
    if (state == null || state.isEmpty()) {
        return false;
    }
    
    // Check if zip code is within the valid 5-digit range
    if (zipCode <= 9999 || zipCode > 99999) {
        return false;
    }
    
    // Check if model name is not null or empty
    if (modelName == null || modelName.isEmpty()) {
        return false;
    }
    
    // Check if sale status is valid - I feel like status could have been a list of options but I didn't want to overcomplicate it
    if (!(saleStatus.equalsIgnoreCase("sold") || 
          saleStatus.equalsIgnoreCase("available") || 
          saleStatus.equalsIgnoreCase("under contract"))) {
        return false;
    }
    
    // If all checks pass, just return true
    return true;
  }

  // Similarly, the HOME class should not be able to "List Home Inventory" since it should only have access to its own information.
  // Within the scope of HOME, the logic associated with listing the home inventory will be for the class to Display/Get its own information. 
  public void GetHomeDetails(){
    // Print Home Information. I'm thinking of: [Home Status] - HomeModel - Home Address as address, city, state, zipcode - SqrtFT labeled as ft^2
    String details = String.format("[%s] - %s - %s, %s, %s %d - %d ft^2",
      saleStatus,
      modelName,
      address,
      city,
      state,
      zipCode,
      squareFeet
    );
    
    System.out.println(details);
  }

  // For Delete and Update, my understanding of the scope of Home in these inventory actions would be for it to handle checking if the home is the one being looked for, 
  // if it is we would then send confirmation to remove it (since home can't "detele itself") or to update its fields with the given data

  public boolean RemoveHome(String homeModel, String homeAddress, int homeZipCode, Boolean logIfNotFound){
      // If values entered match values stored in private variables
          if (modelName.equals(homeModel) && address.equals(homeAddress) && zipCode == homeZipCode) {
      // RETURN CONFIRMATION TO remove home information (TRUE) FROM HOME INVENTORY CLASS (wk8)
              return true;
      // else
          } else {
             if ((logIfNotFound == null) ? true : logIfNotFound){
                // it's confusing to print this for every home when looping through several homes
                System.out.println("Error: Home not found or mismatch in information."); 
            }
             // return message indicating mismatch TO PREVENT REVOMAL FROM HOME INVENTORY (wk8)
              return false;
          }
  }

  public boolean UpdateHome(String homeModel, String homeAddress, int homeZipCode, Home newHomeData, Boolean logIfNotFound ){
      // If values entered match values stored in private variables
          if (modelName.equals(homeModel) && address.equals(homeAddress) && zipCode == homeZipCode) {
      // update home information with newHomeData's given valid fields
            if (newHomeData.squareFeet > 0) {
                this.squareFeet = newHomeData.squareFeet;
            }
            if (newHomeData.address != null && !newHomeData.address.isEmpty()) {
                this.address = newHomeData.address;
            }
            if (newHomeData.city != null && !newHomeData.city.isEmpty()) {
                this.city = newHomeData.city;
            }
            if (newHomeData.state != null && !newHomeData.state.isEmpty()) {
                this.state = newHomeData.state;
            }
            if (newHomeData.zipCode > 9999 && newHomeData.zipCode <= 99999) {
                this.zipCode = newHomeData.zipCode;
            }
            if (newHomeData.saleStatus != null && !newHomeData.saleStatus.isEmpty()) {
                this.saleStatus = newHomeData.saleStatus;
            }
            if (newHomeData.modelName != null && !newHomeData.modelName.isEmpty()) {
                this.modelName = newHomeData.modelName;
            }

            System.out.println("Home updated successfully.");
            return true;
      // else
          } else {
            if ((logIfNotFound == null) ? true : logIfNotFound){
                // it's confusing to print this for every home when looping through several homes
                System.out.println("Error: Home not found or mismatch in information."); 
            }
              return false;
          }
      // return message indicating mismatch
  }
        
  // Last but not least, as part of the wk8 requirements we optionally save the home information to a file, 
  // here HOME's scope should allow it to save its own information in the file. 
  
  // I'm starting by making a separate method for creating the content, that we can leverage to keep the export function more readable and DRY
  public String formatHomeDetails() {
    // return home information in a multi-line string format
          String content = String.format(
                "Model: %s%n" +
                "Square Feet: %d%n" +
                "Address: %s%n" +
                "City: %s%n" +
                "State: %s%n" +
                "Zip Code: %d%n" +
                "Sale Status: %s%n" +
                "--------------------------%n",
                modelName,
                squareFeet,
                address,
                city,
                state,
                zipCode,
                saleStatus
            );
            return content;
  }

  public void ExportToFile(String filePath){
    // GetHomeDetails() to display the home information in the terminal too. 
        GetHomeDetails();
        String content = formatHomeDetails();

    // Instantiate the Writer: Fun Fact! there's a try catch format in lanuages like Java and C# called "try-with-resources"
    // It can simply the process since it will take care of closing the instance on its own, just wish I'd figured it out sooner!
    // try (FileWriter writer instantiated){
        try (FileWriter writer = new FileWriter(filePath, true)) {
    //    writer.write(content)
              writer.write(content);
    //    print confirmation, something like "Home information exported successfully to FilePath."
              System.out.println("Home information exported successfully to " + filePath);
    //} catch (exception) {
          } catch (IOException e) {
    //    print exception
              System.out.println("Error exporting to file: " + e.getMessage());
          }
  }
}
