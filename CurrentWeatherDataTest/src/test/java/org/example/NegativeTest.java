package org.example;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.when;

public class NegativeTest extends AbstractTest {
	private static final int BAD_REQUEST_CODE = 400;
	private static final int UNAUTHORIZED_CODE = 401;
	private static final int NOT_FOUND_CODE = 404;

	@Test
	public void noAppIdTest() {
		Map<String, String> attributes = Map.of("q", "London");
		when().get(buildUrl(attributes, false)).then().statusCode(UNAUTHORIZED_CODE);
	}

	@Test
	public void wrongAppIdTest() {
		Map<String, String> attributes = Map.of("q", "London", "appid", "qwerty");
		when().get(buildUrl(attributes, false)).then().statusCode(UNAUTHORIZED_CODE);
	}

	@Test
	public void notFoundTest() {
		Map<String, String> attributes = Map.of("id", "123");
		when().get(buildUrl(attributes)).then().statusCode(NOT_FOUND_CODE);
	}

	@Test
	public void lonWithoutLatTest() {
		Map<String, String> attributes = Map.of("lat", "35");
		when().get(buildUrl(attributes)).then().statusCode(BAD_REQUEST_CODE);
	}

	@Test
	public void latWithoutLonFoundTest() {
		Map<String, String> attributes = Map.of("lon", "139");
		when().get(buildUrl(attributes)).then().statusCode(BAD_REQUEST_CODE);
	}
}
