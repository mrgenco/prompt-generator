package com.mrg.promptgenerator.services;


import com.mrg.promptgenerator.data.SampleItem;
import com.vaadin.flow.component.combobox.ComboBox;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GeneratePromptService {

    private final ChatClient chatClient;

    public GeneratePromptService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public void fillCategoryData(ComboBox<SampleItem> categoryComboBox) {
        List<SampleItem> categories = new ArrayList<>();
        categories.add(new SampleItem("SCIENCE", "Science", false));
        categories.add(new SampleItem("TECHNOLOGY", "Technology", false));
        categories.add(new SampleItem("ENGINEERING", "Engineering", false));
        categories.add(new SampleItem("SOCIAL_MEDIA", "Social Media", false));
        categories.add(new SampleItem("INTERNET", "Internet", false));
        categories.add(new SampleItem("LITERATURE", "Literature", false));
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
        moods.add(new SampleItem("ACADEMIC", "Academic", false));
        moods.add(new SampleItem("MELANCHOLIC", "Melancholic", false));
        moods.add(new SampleItem("FUNNY", "Funny", false));
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


    public String generatePrompt(SampleItem category, SampleItem mood, SampleItem purpose, String context) {
        StringBuilder promptBuilder = new StringBuilder();
        promptBuilder.append("Create a great example prompt for a Large Language Model in ")
                    .append(category.value())
                    .append(" with a ")
                    .append(mood.value())
                    .append(" mood, for the purpose of ").append(purpose.value()).append(".");
        if(StringUtils.isNotEmpty(context)){
            promptBuilder.append("If a context is provided after ### delimeter, build your prompt around that context as well.")
                    .append("Here is the context ### ").append(context);
        }
        System.out.println("Prompt : " + promptBuilder.toString());
        return chatClient.call(new Prompt(promptBuilder.toString())).getResult().getOutput().getContent();

    }

}
