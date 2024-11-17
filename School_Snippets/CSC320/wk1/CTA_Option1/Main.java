package CTA_Option1;

public class Main {
    public static void main(String[] args) {
        // Create Individual object
        Individual person = new Individual(
            "Giselle", 
            "Martinez-Sanchez", 
            "123 Blvd Rd", 
            "Pensacola", 
            "Florida",
            "12345"
        );

        // Print individual's information
        person.printInfo();
    }
}

// Start

//     Define a class named Individual:
//       CLASS Individual
//         Add attributes: firstName, lastName, streetAddress, city, state and zipCode
//           ATTRIBUTES:
//               firstName, lastName, streetAddress, city, state, zipCode
//         Add a constructor to initialize these attributes
//           CONSTRUCTOR(firstName, lastName, streetAddress, city, state, zipCode)
//               SET this.firstName = firstName
//               SET this.lastName = lastName
//               SET this.streetAddress = streetAddress
//               SET this.city = city
//               SET this.state = state
//               SET this.zipCode = zipCode
//         Add a method named printInfo() to print all attributes. 
//           Each in a separate print to ensure they each go to a separate line
//           METHOD printInfo()
//               PRINT firstName
//               PRINT lastName
//               PRINT streetAddress
//               PRINT city
//               PRINT state
//               PRINT zipCode

//     In the main program:
//         1. Create an instance of the Individual class we created above with sample data
//         2. Call the printInfo() method to display the individual's details

// End
