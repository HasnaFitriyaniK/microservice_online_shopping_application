package com.techie.microservices.product;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;
import com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.ContentType;

import io.restassured.RestAssured;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");
	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		mongoDBContainer.start();

	}

	@Test
	void shouldCreateProduct() {
		String requestBody = """
				{
					"id": 1,
					"name": "iPhone 12",
					"description": "Apple iPhone 12 with 5G",
					"price": 10000
				}
				""";

		RestAssured.given()
				.contentType(ContentType.APPLICATION_JSON.getMimeType())
				.body(requestBody)
				.when()
				.post("/api/product")
				.then()
				.statusCode(201)
				.body("id", Matchers.notNullValue())
				.body("name", Matchers.equalTo("iPhone 12"))
				.body("description", Matchers.equalTo("Apple iPhone 12 with 5G"))
				.body("price", Matchers.equalTo(10000));
	}

}
