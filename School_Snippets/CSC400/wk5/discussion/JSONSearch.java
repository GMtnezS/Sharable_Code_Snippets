package wk5.discussion;

import java.util.Map;

public class JSONSearch {
    public static Object findKey(Map<String, Object> json, String targetKey) {
        for (Map.Entry<String, Object> entry : json.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (key.equals(targetKey)) {
                return value;
            }

            if (value instanceof Map) {
                @SuppressWarnings("unchecked") // Suppresses the unchecked cast warning
                Map<String, Object> nestedJson = (Map<String, Object>) value;
                Object result = findKey(nestedJson, targetKey);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }


    public static void main(String[] args) {
      // Read JSON from file
      String jsonString = JSONScanner.readJsonFile("data.json");

      if (jsonString != null) {
          // Parse JSON string into a nested Map
          Map<String, Object> jsonData = JSONParser.parseJson(jsonString);

          // Search for product details by searches for keys in the nested JSON structure
          String productName = (String) findKey(jsonData, "name");
          Object productPrice = findKey(jsonData, "price");
          Object productCategories = findKey(jsonData, "categories");
          Object productSKU = findKey(jsonData, "sku");

          // Print results
          System.out.println("Product Name: " + (productName != null ? productName : "Not found"));
          System.out.println("Product Price: " + (productPrice != null ? productPrice : "Not found"));
          System.out.println("Product Categories: " + (productCategories != null ? productCategories : "Not found"));
          System.out.println("Product First SKU: " + (productSKU != null ? productSKU : "Not found"));
      }
  }

}
