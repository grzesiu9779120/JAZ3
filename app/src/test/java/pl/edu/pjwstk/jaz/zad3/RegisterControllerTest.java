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
public class RegisterControllerTest {

    @Test
    public void shouldReturnCode201WhenGiveUniqueUsername() {
        String json = "{ \"username\": \"karol\", \"password\": \"haslo\", \"idRole\": 2 }";
        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post("/api/third/register")
                .then()
                .statusCode(equalTo(201))
                .body(equalTo("Registered."));
    }

    @Test
    public void shouldReturnCode200WhenPassingDuplicateUser() {
        String json = "{ \"username\": \"magda\", \"password\": \"brak\", \"idRole\": 2}";
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
                .post("/api/third/register")
                .then()
                .statusCode(equalTo(200))
                .body(equalTo("A user with this name already exists."));
    }

    @Test
    public void shouldReturnCode400WhenPassingEmptyRequestBody_username() {
        String json = "{ \"username\": \"\", \"password\": \"brak\", \"idRole\": 2 }";
        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post("/api/third/register")
                .then()
                .statusCode(equalTo(400))
                .body(equalTo("You must provide a user name."));
    }

    @Test
    public void shouldReturnCode400WhenPassingEmptyRequestBody_password() {
        String json = "{ \"username\": \"emil\", \"password\": \"\", \"idRole\": 2}";
        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post("/api/third/register")
                .then()
                .statusCode(equalTo(400))
                .body(equalTo("You must provide a password."));
    }

    @Test
    public void shouldReturnCode400WhenPassingEmptyRequestBody_idRole() {
        String json = "{ \"username\": \"beata\", \"password\": \"brak\", \"idRole\": \"\" }";
        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post("/api/third/register")
                .then()
                .statusCode(equalTo(400))
                .body(equalTo("You must provide a role."));
    }
}
