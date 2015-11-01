package com.example;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import cucumber.api.java8.En;
import org.hamcrest.CoreMatchers;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;

public class TranslationStepdefs implements En {

    private String host = "http://localhost:18080";
    private String word_to_translate;
    List<String> translations;

    public TranslationStepdefs() {
        Given("^I have a word \"([^\"]*)\"$", (String word) -> {
            word_to_translate = word;
        });

        When("^I try to translate$", () -> {
            Response response = RestAssured.given().get(host + "/translate/" + word_to_translate);
            translations = JsonPath.from(response.asString()).get("englishWord");
        });

        Then("^I get (\\d+) translations$", (Integer size) -> {
            assertThat(translations, hasSize(size));
        });

        And("^First translation is \"([^\"]*)\"$", (String firstresult) -> {
            assertThat(translations.get(0), is(equalTo(firstresult)));
        });

        And("^First translation is null$", () -> {
            assertThat(translations, hasSize(0));
        });
    }
}
