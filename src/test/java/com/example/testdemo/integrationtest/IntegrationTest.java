package com.example.testdemo.integrationtest;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@SpringBootTest
class IntegrationTest {

    @Autowired
    private WebApplicationContext wac;

    private WebClient webClient;

    @BeforeEach
    void setup() {
        webClient = MockMvcWebClientBuilder
                .webAppContextSetup(wac).build();
    }

    @Test
    void integrationTest() throws Exception{
        HtmlPage page;

        String url = "http://localhost:8080/";
        page = webClient.getPage(url);

        //Check that there is no recipe
        String messageText = page.getHtmlElementById("title").getTextContent();
        Assertions.assertEquals("My Recipes!", messageText);
        List<DomElement> recipesRows = page.getElementsById("recipe-row");
        Assertions.assertEquals(0, recipesRows.size());

        //Add a recipe
        HtmlElement button = page.getHtmlElementById("add-button");
        page = button.click();
        messageText = page.getHtmlElementById("title").getTextContent();
        Assertions.assertEquals("Recipe Information", messageText);

        HtmlTextInput name = page.getHtmlElementById("recipe-name");
        name.setValueAttribute("Cake");

        HtmlTextInput prepTime = page.getHtmlElementById("recipe-prep-time");
        prepTime.setValueAttribute("2 Hours");

        HtmlTextInput cookTime = page.getHtmlElementById("recipe-cook-time");
        cookTime.setValueAttribute("30 minutes");

        HtmlTextInput servings = page.getHtmlElementById("recipe-servings");
        servings.setValueAttribute("4 persons");

        HtmlTextArea directions = page.getHtmlElementById("recipe-directions");
        directions.setText("Take the ingredients and prepare the cake.");

        HtmlElement saveButton = page.getHtmlElementById("save-button");
        page = saveButton.click();

        //Check that we have a recipe

        messageText = page.getHtmlElementById("title").getTextContent();
        Assertions.assertEquals("My Recipes!", messageText);
        recipesRows = page.getElementsById("recipe-row");
        Assertions.assertEquals(1, recipesRows.size());

        //Open it
        HtmlElement openButton = page.getHtmlElementById("open-link");
        page = openButton.click();
        HtmlTextInput nameOpened = page.getHtmlElementById("recipe-name");
        Assertions.assertEquals("Cake", nameOpened.getValueAttribute());

        //Delete it
        page = webClient.getPage(url);
        HtmlElement deleteButton = page.getHtmlElementById("delete-link");
        page = deleteButton.click();

        //Check that we have no recipe, again
        recipesRows = page.getElementsById("recipe-row");
        Assertions.assertEquals(0, recipesRows.size());
    }
}
