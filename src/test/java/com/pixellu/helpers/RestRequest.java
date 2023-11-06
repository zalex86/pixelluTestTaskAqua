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

import static com.pixellu.tests.BaseSetup.SERVER;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.params.CoreConnectionPNames.CONNECTION_TIMEOUT;
import static org.apache.http.params.CoreConnectionPNames.SO_TIMEOUT;

public class RestRequest {
    protected String host;
//    public final static String PASSWORD = CONSTANT_PASSWORD;
//    public final static String ADMIN = ADMIN_LOGIN;
    private final static ConcurrentMap<String, String> tokenMap = new ConcurrentHashMap<>();


    public RestRequest() {
        this.host = SERVER + "/api/";
    }

    protected static final Logger log = LoggerFactory.getLogger(RestRequest.class);
    //protected String HOST;
    protected Credentials credentials;
    private final RestAssuredConfig config = RestAssured.config()
            .httpClient(HttpClientConfig.httpClientConfig()
                    .setParam(CONNECTION_TIMEOUT, 200000)
                    .setParam(SO_TIMEOUT, 200000));

//    private static final ConcurrentMap<String, String> tokenMap = new ConcurrentHashMap<>();
//    protected final String TESTER_LOGIN = "tester/login";
//    public static final String HOST = System.getProperty("host") + "api/";

    public RestRequest(Credentials credentials) {

        this.credentials = credentials;
    }

    protected RequestSpecification givenWithAuth(String baseUrl, User user) {
        return givenWithRequestSpecBuilder(baseUrl)
                .header("Authorization", "Bearer " + getToken(user));
    }

    @Description("Get token for user")
    private String getToken(User user) {
        String token = tokenMap.get(credentials.getEmail());
        if (token == null) {
            token = givenWithRequestSpecBuilder(host)
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

    public static Response verifyCode(Response response, Integer statusCode) {
        return response.then().statusCode(statusCode).extract().response();
    }

    public Response postAuth(String url, User user) {
        return givenWithRequestSpecBuilder(host + url)
                .header("x-auth-token", getToken(user))
                .body("{}")
                .post();
    }

    public Response postAuth(String url, Object body, User user) {
        String token = getToken(user);
        return givenWithRequestSpecBuilder(host + url)
                .header("x-auth-token", token)
                .body(body)
                .post();
    }

//    private RequestSpecification getRequestSpecification(String url) {
//        return given()
//                //.spec(logSpecFilter(url))
//                .spec(getRequestSpecBuilder(url).build())
//                .contentType(ContentType.JSON.withCharset("UTF-8"));
//    }

//    protected RequestSpecBuilder getRequestSpecBuilder(String baseUrl) {
//        return new RequestSpecBuilder()
//                .setBaseUri(baseUrl)
//                .setContentType(ContentType.JSON.withCharset("UTF-8"))
//                .addFilter(requestLoggingFilter)
//                .addFilter(responseLoggingFilter);
//    }
}
