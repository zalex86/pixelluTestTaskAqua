package com.pixellu.helpers;


import com.pixellu.api.requests.Credentials;
import com.pixellu.objects.User;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import jdk.jfr.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.params.CoreConnectionPNames.CONNECTION_TIMEOUT;
import static org.apache.http.params.CoreConnectionPNames.SO_TIMEOUT;

public class RestRequest {

    protected static final Logger log = LoggerFactory.getLogger(RestRequest.class);
    //protected String HOST;
    protected Credentials credentials;
    private final RestAssuredConfig config = RestAssured.config()
            .httpClient(HttpClientConfig.httpClientConfig()
                    .setParam(CONNECTION_TIMEOUT, 200000)
                    .setParam(SO_TIMEOUT, 200000));

    private static final ConcurrentMap<String, String> tokenMap = new ConcurrentHashMap<>();
    protected final String TESTER_LOGIN = "tester/login";
    public static final String HOST = System.getProperty("host") + "api/";

    public RestRequest(Credentials credentials) {

        this.credentials = credentials;
    }

    protected RequestSpecification givenWithAuth(String baseUrl) {
        return givenWithRequestSpecBuilder(baseUrl)
                .header("Authorization", "Bearer " + getToken());
    }

    @Description("Get token for user")
    private String getToken() {
        String token = tokenMap.get(credentials.getEmail());
        if (token == null) {
            token = givenWithRequestSpecBuilder(HOST + TESTER_LOGIN)
                    .when()
                    .body(new User()
                            .setEmail(credentials.getEmail())
                            .setPassword(credentials.getPassword()))
                    .post()
                    .then()
                    .log().all()
                    .statusCode(SC_CREATED)
                    .extract().body().jsonPath().getString("accessToken");
            tokenMap.putIfAbsent(credentials.getEmail(), token);
            log.info("got token for {}: {}", credentials.getEmail(), token);
        }
        return token;
    }

    protected RequestSpecification givenWithRequestSpecBuilder(String baseUrl) {
        return given().spec(new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setContentType(ContentType.JSON.withCharset("UTF-8"))
                .setConfig(config)
                .log(LogDetail.ALL)
                .addFilter(new AllureRestAssured()).build());//.request().log().all();
    }

    protected Response logResponse(Response response) {
        return response.then()
                .log().all()
                .extract().response();
    }
}
