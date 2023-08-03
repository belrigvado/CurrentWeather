package org.example;

import io.restassured.http.ContentType;
import org.example.model.SuccessfulResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasXPath;

public class PositiveTest extends AbstractTest {
	private static final int OK_CODE = 200;

	@Test
	public void qTest1() {
		Map<String, String> attributes = Map.of("q", "London");
		when().get(buildUrl(attributes))
				.then().statusCode(OK_CODE)
				.assertThat()
				.body("cod", equalTo(OK_CODE),
						"name", equalTo("London"),
						"sys.country", equalTo("GB"));
	}

	@Test
	public void qTest2() {
		Map<String, String> attributes = Map.of("q", "Moscow, RU");
		when().get(buildUrl(attributes))
				.then().statusCode(OK_CODE)
				.assertThat()
				.body("cod", equalTo(OK_CODE),
						"name", equalTo("Moscow"),
						"sys.country", equalTo("RU"));
	}

	@Test
	public void idTest() {
		Map<String, String> attributes = Map.of("id", "2172797");
		when().get(buildUrl(attributes))
				.then().statusCode(OK_CODE)
				.assertThat()
				.body("cod", equalTo(OK_CODE),
						"name", equalTo("Cairns"),
						"sys.country", equalTo("AU"));
	}

	@Test
	public void latLonTest() {
		Map<String, String> attributes = Map.of("lat", "35", "lon", "139");
		when().get(buildUrl(attributes))
				.then().statusCode(OK_CODE)
				.assertThat()
				.body("cod", equalTo(OK_CODE),
						"name", equalTo("Shuzenji"),
						"sys.country", equalTo("JP"),
						"coord.lon", equalTo(139),
						"coord.lat", equalTo(35));
	}

	@Test
	public void zipTest1() {
		Map<String, String> attributes = Map.of("zip", "95050");
		when().get(buildUrl(attributes))
				.then().statusCode(OK_CODE)
				.assertThat()
				.body("cod", equalTo(OK_CODE),
						"name", equalTo("Santa Clara"),
						"sys.country", equalTo("US"));
	}

	@Test
	public void zipTest2() {
		Map<String, String> attributes = Map.of("zip", "95050,US");
		when().get(buildUrl(attributes))
				.then().statusCode(OK_CODE)
				.assertThat()
				.body("cod", equalTo(OK_CODE),
						"name", equalTo("Santa Clara"),
						"sys.country", equalTo("US"));
	}

	@Test
	public void unitsMetricTest() {
		Map<String, String> attributes = Map.of("q", "London", "units", "metric");
		when().get(buildUrl(attributes))
				.then().statusCode(OK_CODE)
				.assertThat()
				.body("cod", equalTo(OK_CODE),
						"name", equalTo("London"),
						"sys.country", equalTo("GB"));
	}

	@Test
	public void imperialMetricTest() {
		Map<String, String> attributes = Map.of("q", "London", "units", "imperial");
		when().get(buildUrl(attributes))
				.then().statusCode(OK_CODE)
				.assertThat()
				.body("cod", equalTo(OK_CODE),
						"name", equalTo("London"),
						"sys.country", equalTo("GB"));
	}

	@Test
	public void langTest() {
		Map<String, String> attributes = Map.of("q", "Moscow", "lang", "ru");
		when().get(buildUrl(attributes))
				.then().statusCode(OK_CODE)
				.assertThat()
				.body("cod", equalTo(OK_CODE),
						"name", equalTo("Москва"),
						"sys.country", equalTo("RU"));
	}

	@Test
	public void xmlTest() {
		Map<String, String> attributes = Map.of("q", "London", "mode", "xml");
		when().get(buildUrl(attributes))
				.then().statusCode(OK_CODE)
				.assertThat()
				.body(hasXPath("/current/city/@name", equalTo("London")),
						hasXPath("/current/city/country", equalTo("GB")));
	}
}
