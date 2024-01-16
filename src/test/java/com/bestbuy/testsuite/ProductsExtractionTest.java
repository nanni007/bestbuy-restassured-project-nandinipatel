package com.bestbuy.testsuite;

import com.bestbuy.propertyreader.PropertyReader;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest extends TestBase {
    static ValidatableResponse response;
    String products = PropertyReader.getInstance().getProperty("resourceProducts");

    @BeforeClass
    public void extractStores() {

        response = given()
                .when()
                .get(products)
                .then().statusCode(200);
    }

    //21. Extract the limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    //22. Extract the total
    @Test
    public void test002() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    //23. Extract the name of 5th product
    @Test
    public void test003() {
        String fifthStore = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5th product is : " + fifthStore);
        System.out.println("------------------End of Test---------------------------");
    }

    //24. Extract the names of all the products
    @Test
    public void test004() {
        List<String> productNames = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of products are : " + productNames);
        System.out.println("------------------End of Test---------------------------");
    }

    //25. Extract the productId of all the products
    @Test
    public void test005() {
        List<String> productId = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The ID's of products are : " + productId);
        System.out.println("------------------End of Test---------------------------");
    }

    //26. Print the size of the data list
    @Test
    public void test006() {
        List<String> dataList = response.extract().path("data");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of data list is : " + dataList.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
    @Test
    public void test007() {
        List<HashMap<String, ?>> energizer = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of Energizer - MAX Batteries AA (4-Pack) value is : " + energizer);
        System.out.println("------------------End of Test---------------------------");
    }

    //28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
    @Test
    public void test008() {
        List<HashMap<String, ?>> battery = response.extract().path("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name battery is : " + battery);
        System.out.println("------------------End of Test---------------------------");
    }

    //29. Get all the categories of 8th products
    @Test
    public void test009() {
        List<HashMap<String, ?>> eighthProduct = response.extract().path("data[7].categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 8th product is : " + eighthProduct);
        System.out.println("------------------End of Test---------------------------");
    }

    //30. Get categories of the store where product id = 150115
    @Test
    public void test010() {
        List<HashMap<String, ?>> category150115 = response.extract().path("data[3].categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The categories of products in 150115 is : " + category150115);
        System.out.println("------------------End of Test---------------------------");
    }

    //31. Get all the descriptions of all the products
    @Test
    public void test011() {
        List<HashMap<String, ?>> descriptions = response.extract().path("data.description");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The descriptions of products are : " + descriptions);
        System.out.println("------------------End of Test---------------------------");
    }

    //32. Get id of all the all categories of all the products
    @Test
    public void test012() {
        List<String> productIdCategory = response.extract().path("data.categories.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The ID's & categories of all the products are : " + productIdCategory);
        System.out.println("------------------End of Test---------------------------");
    }

    //33. Find the product names Where type = HardGood
    @Test
    public void test013() {
        List<HashMap<String, ?>> hardGood = response.extract().path("data.findAll{it.type == 'HardGood'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of HardGood value is : " + hardGood);
        System.out.println("------------------End of Test---------------------------");
    }

    //34. Find the Total number of categories for the product where product name = Duracell - AA1.5V CopperTop Batteries (4-Pack)
    @Test
    public void test014() {
        List<String> allCategories = response.extract().path("data.findAll{it.name=='Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Number of categories for Duracell - AA 1.5V CopperTop Batteries (4-Pack) is: " + (allCategories.size()));
        System.out.println("------------------End of Test---------------------------");


    }

    //35. Find the createdAt for all products whose price < 5.49
    @Test
    public void test015() {
        List<String> createdAt = response.extract().path("data.findAll{it.price<5.49}.createdAt");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of products that price is less than 5.49 are: " + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }

    //36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
    @Test
    public void test016() {
        List<HashMap<String, ?>> energizer4PackCategories = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}.categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name and categories of Energizer - MAX Batteries AA (4-Pack) value is : " + energizer4PackCategories);
        System.out.println("------------------End of Test---------------------------");
    }

    //37. Find the manufacturer of all the products
    @Test
    public void test017() {
        List<?> mgfList = response.extract().path("data.findAll{it.name}.manufacturer");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The manufacturer of products are: " + mgfList);
        System.out.println("------------------End of Test---------------------------");
    }

    //38. Find the image of products whose manufacturer is = Energizer
    @Test
    public void test018() {
        List<?> productImage = response.extract().path("data.findAll{it.manufacturer=='Energizer'}.image");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The images of product is " + productImage);
        System.out.println("------------------End of Test---------------------------");
    }

    //39. Find the createdAt for all categories products whose price > 5.99
    @Test
    public void test019() {
        List<String> greaterThan = response.extract().path("data.findAll{it.price>5.99}.createdAt");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of products that price is greater than 5.99 are: " + greaterThan);
        System.out.println("------------------End of Test---------------------------");
    }

    //40. Find the url of all the product
    @Test
    public void test020() {
        List<String> productURL = response.extract().path("data.url");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The URL of products is " + productURL);
        System.out.println("------------------End of Test---------------------------");
    }
}
