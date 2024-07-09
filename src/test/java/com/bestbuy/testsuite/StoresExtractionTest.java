package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {
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
    //Extract the limit
    public void extractLimit() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is  : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    //Extract the total
    public void extractTotal() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is  : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    //Extract the name of 5th store
    public void extractNameOfFifthStore() {
        String fifthStoreName = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of the fifth store is  : " + fifthStoreName);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    //Extract the names of all the store
    public void extractNameOfAllStore() {
        List<String> listOfStoreName = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("list of the store name is :" + listOfStoreName);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    //Extract the storeId of all the store
    public void extractIdOfAllStore() {
        List<String> listOfStoreId = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("list of the store id is :" + listOfStoreId);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    //Print the size of the data list
    public void printSize() {
        List<String> data = response.extract().path("data");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of the data is : " + data.size());
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    //Get all the value of the store where store name = St Cloud
    public void extractNameStCloud() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'St Cloud'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All values of store Name St Cloud: " + values);
        System.out.println("------------------End of Test---------------------------");
    }

    //8. Get the address of the store where store name = Rochester
    @Test
    public void extractGetAllAddressOfStoreByName() {
        List<String> address = response.extract().path("data.findAll{it.name == 'Rochester'}.address");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Address of store Name Rochester: " + address);
        System.out.println("------------------End of Test---------------------------");
    }

    //9. Get all the services of 8th store
    @Test
    public void extractGetAllServicesOfEighthStore() {
        List<Map<String, ?>> services = response.extract().path("data[7].services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Services of 8th store: " + services);
        System.out.println("------------------End of Test---------------------------");
    }

    //10. Get storeservices of the store where service name = Windows Store
    @Test
    public void extractGetStoreServicesByName() {
        List<Map<String, ?>> storeServices = response.extract().path("data.findAll{it.name == 'Windows Store'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Store services of service name windows store: " + storeServices);
        System.out.println("------------------End of Test---------------------------");
    }

    //11. Get all the storeId of all the store
    @Test
    public void extractGetAllStoreId() {

        List<Integer> storeIdOfAllStore = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of all store is : " + storeIdOfAllStore);
        System.out.println("------------------End of Test---------------------------");
    }

    //12. Get id of all the store
    @Test
    public void extractGetAllStoreIdFromService() {
        List<Integer> idOfAllStore = response.extract().path("data.services.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of all store is : " + idOfAllStore);
        System.out.println("------------------End of Test---------------------------");
    }

    //13. Find the store names Where state = ND
    @Test
    public void extractStoreNameFromStateND() {
        List<String> storeNamesList = response.extract().path("data.findAll{it.state == 'MN'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of all store Where state = MN is : " + storeNamesList);
        System.out.println("------------------End of Test---------------------------");
    }

    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void extractTotalNoOfServicesFromStore() {
        List<String> services = response.extract().path("data.findAll{it.name == 'Rochester'}.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total number of services: " + services.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void extractFindCreatedAtForAllServices() {
        List<String> data = response.extract().path("data.services*.findAll{it.name == 'Windows Store'}.storeservices.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The getExact All Services With 'Windows Store' are: " + data);
        System.out.println("------------------End of Test---------------------------");
    }

    //16. Find the name of all services Where store name = “Fargo”
    @Test
    public void extractFindAllServicesByStoreName() {
        List<?> menuList = response.extract().path("data.findAll{it.name == 'Fargo'}.services.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all services where store name is Fargo are: " + menuList);
        System.out.println("------------------End of Test---------------------------");
    }

    //17. Find the zip of all the store
    @Test
    public void extractFindAllZipFromAllStore() {
        List<String> zip = response.extract().path("data.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Zip of all stores are : " + zip);
        System.out.println("------------------End of Test---------------------------");
    }

    //18. Find the zip of store name = Roseville
    @Test
    public void extractFindZipFromStoreName() {
        List<String> zip = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Zip of Roseville store is : " + zip.get(0));
        System.out.println("------------------End of Test---------------------------");
    }

    //19. Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void extractFindServicesDetailsFromServiceName() {
        List<String> data = response.extract().path("data.services*.find{it.name == 'Magnolia Home Theater'}.storeservices");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The store Services Details Of The Service Name = MagnoliaHomeTheater: " + data);
        System.out.println("------------------End of Test---------------------------");
    }

    //20. Find the lat of all the stores
    @Test
    public void extractFindLatOfAllStores() {
        List<Double> lat = response.extract().path("data.lat");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("lat of all stores are " + lat);
        System.out.println("------------------End of Test---------------------------");

    }
}

