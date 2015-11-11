package com.example;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.path.json.JsonPath.from;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;

@RunWith(Arquillian.class)
public class TranslationTest {

    private static final Logger log = Logger.getLogger(TranslationTest.class.getName());

//  https://docs.jboss.org/author/display/ARQGRA2/Framework+Integration+Options
//    Standalone
//    * runs tests without container integration, only lifecycle of extensions is managed
//    * allows to use Graphene independently of Arquillian containers and deployment management
//    Container
//    * runs tests with container, managed lifecycle of container including deployment
//    * you can still use Graphene without managing lifecycle - just do not provide @Deployment in your test case

    @Deployment(testable = false)
    public static GenericArchive createDeployment() {
        return ShrinkWrap.create(GenericArchive.class, "app.tar");
    }

    @Test
    @RunAsClient
    public void shouldReturnMessage() throws IOException, InterruptedException {
        log.log(Level.INFO, "Foo");

        String json = get(new URL("http://localhost:18080/translate/domek")).asString();
        List<String> translations = from(json).get("englishWord");

        assertThat(translations, hasSize(24));
        assertThat(from(json).get("[1].englishWord").toString(), is("chateau"));
    }

}
