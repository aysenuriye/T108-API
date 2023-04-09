package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C04_Put_ResponseBilgileriAssertion {
    // 1-Gönderecegimiz Request için gerekli olan URLve ihtiyacimiz varsa Request Body hazırla
    // 2-Eger soruda bize verilmisse Expected Data hazirla
    // 3-Bize donen Response'i Actual Data olarak kaydet
    // 4- Expected Data ile Actual Datayi karsilastirmamiz yani Assertion yapmamiz gerek

    /*

        https://jsonplaceholder.typicode.com/posts/70 url’ine asagidaki
        Json formatindaki body ile bir PUT request gonderdigimizde
                {
                "title":"Ahmet",
                "body":"Merhaba",
                "userId":10,
                "id":70
                }
        donen Response’un,
            status code’unun 200,
            ve content type’inin application/json; charset=utf-8,
            ve Server isimli Header’in degerinin cloudflare,
            ve status Line’in HTTP/1.1 200 OK
      */

    @Test
    public void put01() {

        // 1 - Request Url ve Body hazirla

        String url="https://jsonplaceholder.typicode.com/posts/70";
            /*
            {
                "title":"Ahmet",
                "body":"Merhaba",
                "userId":10,
                "id":70
                }
             */
        JSONObject reqBody = new JSONObject();


        reqBody.put("title", "Ahmet");
        reqBody.put("body", "Merhaba");
        reqBody.put("userId", 10);
        reqBody.put("id", 70);

        System.out.println("recBody = "+reqBody);

        // 2 - Expected data hazırla

        // 3 - Response u kaydet

        Response response=given().contentType(ContentType.JSON)
                .when()
                .body(reqBody.toString()).put(url);

        response.prettyPrint();

        // 4- Assert

        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .statusLine("HTTP/1.1 200 OK")
                .header("Server","cloudflare");

    }
}