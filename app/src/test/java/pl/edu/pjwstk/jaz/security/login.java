package pl.edu.pjwstk.jaz.security;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class login {

    @Test
    public void shouldReturnCode202WhenPassCorrectData() {
        String json = "{ \"username\": \"filip\", \"password\": \"haslo\"}";
        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post("/api/register")
                .then()
                .statusCode(equalTo(201))
                .body(equalTo("Registered."));
        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post("/api/login")
                .then()
                .statusCode(equalTo(202))
                .body(equalTo("Login."));

    }

}
