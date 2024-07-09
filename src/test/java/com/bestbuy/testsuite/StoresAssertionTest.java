package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class StoresAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }

    @Test
    //Verify the if the total is equal to 1561
    public void verifyTotal() {
        response.body("total", equalTo(1561));
    }
    @Test
    //Verify the if the stores of limit is equal to 10
    public void verifyLimit(){
        response.body("limit", equalTo(10));
    }
    @Test
    //Check the single ‘Name’ in the Array list (Inver Grove Heights)
    public void verifyTheSingleName(){
        response.body("data.name",hasItems("Inver Grove Heights"));
    }
    @Test
    //Check the multiple ‘Names’ in the ArrayList (Roseville, Burnsville, Maplewood)
    public void verifyTheMultipleName(){
        response.body("data.name",hasItems("Roseville", "Burnsville", "Maplewood"));
    }
    @Test
    //Verify the storied=7 inside storeservices of the third store of second services
    public void verifyStoreId(){
        response.body("data[2].services[1].storeservices.storeId",equalTo(7));
    }
    @Test
    //Check hash map values ‘createdAt’ inside storeservices map where store name = Roseville
    public void verifyHashMapValue(){
        response.body("data.findAll{it.name=='Roseville'}", hasItem(hasEntry("createdAt", "2016-11-17T17:57:05.853Z")));
    }
    @Test
    //Verify the state = MN of forth store
    public void verifyTheState(){
        response.body("data[3].state",equalTo("MN"));
    }
    @Test
    //Verify the store name = Rochester of 9th store
    public void verifyStoreName(){
        response.body("data[8].name",equalTo("Rochester"));
    }
    @Test
    //Verify the storeId = 11 for the 6th store
    public void verifyStoreIdOfSixthStore(){
        response.body("data[5].id",equalTo(11));
    }
    @Test
    //Verify the serviceId = 4 for the 7th store of forth service
    public void verifyServiceIdOfForthService(){
        response.body("data[6].services[3].storeservices.serviceId",equalTo(4));
    }

}
