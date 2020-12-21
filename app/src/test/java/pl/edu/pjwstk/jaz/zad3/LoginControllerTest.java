package pl.edu.pjwstk.jaz.zad3;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.IntegrationTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@IntegrationTest
public class LoginControllerTest {
    @Test
    public void shouldReturnCode202WhenPassCorrectData() {
        String json = "{ \"username\": \"filip\", \"password\": \"haslo\", \"idRole\": 2 }";
        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post("/api/third/register")
                .then()
                .statusCode(equalTo(201))
                .body(equalTo("Registered."));
        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post("/api/third/login")
                .then()
                .statusCode(equalTo(202))
                .body(equalTo("Login."));
    }

    @Test
    public void shouldReturnCode401WhenPassIncorrectPassword() {
        String json = "{ \"username\": \"bartek\", \"password\": \"haslo\", \"idRole\": 2 }";
        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post("/api/third/register")
                .then()
                .statusCode(equalTo(201))
                .body(equalTo("Registered."));
        json = "{ \"username\": \"bartek\", \"password\": \"hasllo\"}";
        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post("/api/third/login")
                .then()
                .statusCode(equalTo(401))
                .body(equalTo("Wrong password."));

    }

    @Test
    public void shouldReturnCode202WhenPassIncorrectOrNotExistUsername() {
        String json = "{ \"username\": \"szymon\", \"password\":\"brak\"}";
        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post("/api/third/login")
                .then()
                .statusCode(equalTo(401))
                .body(equalTo("The user does not exist."));
    }
}
