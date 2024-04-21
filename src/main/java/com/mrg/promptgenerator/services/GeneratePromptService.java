package com.mrg.promptgenerator.services;


import com.mrg.promptgenerator.data.SampleItem;
import com.vaadin.flow.component.combobox.ComboBox;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
public class GeneratePromptService {



    private final WebClient webClient;

    public GeneratePromptService(WebClient webClient) {
        this.webClient = webClient;
    }

    public void fillCategoryData(ComboBox<SampleItem> categoryComboBox) {
        List<SampleItem> categories = new ArrayList<>();
        categories.add(new SampleItem("SCI_FI", "Science Fiction", false));
        categories.add(new SampleItem("FANTASY", "Fantasy", false));
        categories.add(new SampleItem("MYSTERY", "Mystery", false));
        categories.add(new SampleItem("HORROR", "Horror", false));
        categories.add(new SampleItem("ROMANCE", "Romance", false));
        categories.add(new SampleItem("HISTORICAL", "Historical", false));
        categories.add(new SampleItem("ADVENTURE", "Adventure", false));
        categories.add(new SampleItem("COMEDY", "Comedy", false));
        categories.add(new SampleItem("BIOGRAPHICAL", "Biographical", false));
        categories.add(new SampleItem("DYSTOPIAN", "Dystopian", false));
        categoryComboBox.setItems(categories);
        categoryComboBox.setItemLabelGenerator(SampleItem::label);
    }

    public void fillMoodData(ComboBox<SampleItem> moodComboBox) {
        List<SampleItem> moods = new ArrayList<>();
        moods.add(new SampleItem("JOYFUL", "Joyful", false));
        moods.add(new SampleItem("MELANCHOLIC", "Melancholic", false));
        moods.add(new SampleItem("TENSE", "Tense", false));
        moods.add(new SampleItem("MYSTERIOUS", "Mysterious", false));
        moods.add(new SampleItem("WHIMSICAL", "Whimsical", false));
        moods.add(new SampleItem("SERENE", "Serene", false));
        moods.add(new SampleItem("TERRIFYING", "Terrifying", false));
        moods.add(new SampleItem("INSPIRING", "Inspiring", false));
        moods.add(new SampleItem("EUPHORIC", "Euphoric", false));
        moods.add(new SampleItem("GLOOMY", "Gloomy", false));
        moodComboBox.setItems(moods);
        moodComboBox.setItemLabelGenerator(SampleItem::label);
    }

    public void fillPurposeData(ComboBox<SampleItem> purposeComboBox) {
        List<SampleItem> purposes = new ArrayList<>();
        purposes.add(new SampleItem("BRAINSTORMING", "Brainstorming", false));
        purposes.add(new SampleItem("SKILL_DEV", "Skill Development", false));
        purposes.add(new SampleItem("EDUCATIONAL", "Educational", false));
        purposes.add(new SampleItem("PROFESSIONAL", "Professional", false));
        purposes.add(new SampleItem("THERAPEUTIC", "Therapeutic", false));
        purposes.add(new SampleItem("ENTERTAINMENT", "Entertainment", false));
        purposes.add(new SampleItem("COMPETITIVE", "Competitive", false));
        purposes.add(new SampleItem("PORTFOLIO_BUILDING", "Portfolio Building", false));
        purposes.add(new SampleItem("FORMAL", "Formal", false));
        purposes.add(new SampleItem("PRECISE", "Precise", false));
        purposes.add(new SampleItem("AUTHORITATIVE", "Authoritative", false));
        purposes.add(new SampleItem("STRUCTURED", "Structured", false));

        purposeComboBox.setItems(purposes);
        purposeComboBox.setItemLabelGenerator(SampleItem::label);

    }


    public String generatePrompt(Object value, Object value1, Object value2, String value3) {
        return "Test prompt is generated with data : " + value.toString() + " " + value1.toString() + " " + value2.toString();
        //return this.generatePrompt(value.toString(), value1.toString(), value2.toString());
    }

    private String generatePrompt(String category, String mood, String purpose) {
        String prompt = "Create a " + category + " story with a " + mood + " mood, for the purpose of " + purpose + ".";

        return Objects.requireNonNull(webClient.post()
                .uri("/chat/completions")
                .bodyValue(buildRequestBody(prompt))
                .retrieve()
                .bodyToMono(String.class)
                .block());
    }

    private Map<String, Object> buildRequestBody(String prompt) {
        Map<String, Object> data = new HashMap<>();
        data.put("prompt", prompt);
        data.put("max_tokens", 150);
        data.put("temperature", 0.7);
        return data;
    }
}
