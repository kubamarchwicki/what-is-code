package com.example

import com.jayway.restassured.RestAssured
import com.jayway.restassured.path.json.JsonPath
import spock.lang.Specification
import spock.lang.Unroll

@Unroll("Translations for word: [#word]")
class WordTranslationsSpec extends Specification {

    def host = "http://localhost:18080"

    def "Should translate given words"() {
        when: "Translation service is called"
            def response = RestAssured.given().get("${host}/translate/${word}")

        then: "Status code 200 is returned"
            response.statusCode() == 200
        and: "Array of translated words is returned"
            def translations = JsonPath.from(response.asString()).get("englishWord")
            translations.size() == responseSize
            translations[0] == firstTranslation

        where:
            word        | responseSize  | firstTranslation
            "domek"     | 24            | "lodge"
            "asdfghj"   | 0             | null
    }
}
