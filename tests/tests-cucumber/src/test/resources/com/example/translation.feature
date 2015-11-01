Feature: Try word translation

  Scenario Outline: Translate some words
    Given I have a word <word>
    When I try to translate
    Then I get <size> translations
    And First translation is <firstTranslation>
    Examples:
      | word        | size | firstTranslation   |
      | "domek"     | 24   | "lodge"            |
      | "asdfghj"   | 0    | null               |

