package one.digitalinnovation.parking.controller;

import io.restassured.RestAssured;
import one.digitalinnovation.parking.controller.dto.ParkingCreateDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerIT {

    @LocalServerPort
    private int randomPort;
    @BeforeEach
    public void setUpTest() {
        RestAssured.port = randomPort;
    }

    @Test
    void findAll() {

        RestAssured.given()
                .when()
                .get("/parking")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("licence[0]", Matchers.equalTo("WAS-1234"));
                //.extract().response().body().prettyPrint();  para verificar como está vindo os dados
    }

    @Test
    void whenCreateThenCheckIsCreated() {

        var createDTO = new ParkingCreateDTO();
        createDTO.setColor("AMARELO");
        createDTO.setLicence("WRT-5555");
        createDTO.setModel("BRASILIA");
        createDTO.setState("SP");

        RestAssured.given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(createDTO)
                .post("parking/")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("licence", Matchers.equalTo("WRT-5555"))
                .body("color", Matchers.equalTo("AMARELO"))
                .body("model", Matchers.equalTo("BRASILIA"))
                .body("state", Matchers.equalTo("SP"));
    }
}