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
public class GetUserControllerTest {

    @Test
    public void shouldReturnCode204WhenPassUserWhichDoesMotExist() {
        String json = "{ \"username\": \"dawid\", \"password\": \"haslo\", \"idRole\": 2 }";
        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post("/api/third/register")
                .then()
                .statusCode(equalTo(201))
                .body(equalTo("Registered."));

        given()
                .get("/api/third/getUser/david")
                .then()
                .statusCode(equalTo(204));
    }

    @Test
    public void shouldReturnCode200AmdContentWithBaseInformationWhenUsername() {
        String json = "{ \"username\": \"krystian\", \"password\": \"brak123\", \"idRole\": 2 }";
        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post("/api/third/register")
                .then()
                .statusCode(equalTo(201))
                .body(equalTo("Registered."));

        given()
                .get("/api/third/getUser/krystian")
                .then()
                .statusCode(equalTo(200))
                .body(equalTo("UserEntity{id=2, username='ala', idRole=2}"));
    }
}
