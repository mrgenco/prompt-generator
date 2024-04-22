package com.mrg.promptgenerator.views.generateprompt;

import com.mrg.promptgenerator.data.SampleItem;
import com.mrg.promptgenerator.services.GeneratePromptService;
import com.mrg.promptgenerator.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.ComboBoxVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Generate Prompt")
@Route(value = "generate-prompt", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@AnonymousAllowed
@Uses(Icon.class)
public class GeneratePromptView extends Composite<VerticalLayout> {

    public GeneratePromptView(GeneratePromptService promptService) {

        ComboBox categoryComboBox = addCategoryComboBox(promptService);

        ComboBox moodComboBox = addMoodComboBox(promptService);

        ComboBox purposeComboBox = addPurposeComboBox(promptService);

        FormLayout formLayout = createFormLayout(categoryComboBox, moodComboBox, purposeComboBox);

        TextArea customizePromptTextArea = addCustomizePrompt();


        Paragraph textMedium = new Paragraph();
        textMedium.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        textMedium.setWidth("100%");
        textMedium.getStyle().set("font-size", "var(--lumo-font-size-m)");

        Button generatePromptButton = new Button();
        generatePromptButton.setText("Generate Prompt");
        generatePromptButton.setWidth("min-content");
        generatePromptButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        generatePromptButton.addThemeVariants(ButtonVariant.LUMO_LARGE);
        generatePromptButton.setDisableOnClick(true);
        generatePromptButton.addClickListener(clickEvent -> {
            textMedium.setText(promptService.generatePrompt((SampleItem) categoryComboBox.getValue(), (SampleItem) moodComboBox.getValue(), (SampleItem) purposeComboBox.getValue(), customizePromptTextArea.getValue()));
            generatePromptButton.setEnabled(true);
        });

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().add(formLayout);
        getContent().add(customizePromptTextArea);
        getContent().add(generatePromptButton);
        getContent().add(textMedium);





    }

    private static TextArea addCustomizePrompt() {
        TextArea customizePromptTextArea = new TextArea();
        customizePromptTextArea.setLabel("Customize your prompts by providing a context.");
        customizePromptTextArea.setWidth("100%");
        return customizePromptTextArea;
    }

    private static FormLayout createFormLayout(ComboBox categoryComboBox, ComboBox moodComboBox, ComboBox purposeComboBox) {
        FormLayout formLayout3Col = new FormLayout();
        formLayout3Col.setWidth("100%");
        formLayout3Col.setResponsiveSteps(
                new ResponsiveStep("0", 1),
                new ResponsiveStep("250px", 2),
                new ResponsiveStep("500px", 3)
        );
        formLayout3Col.add(categoryComboBox);
        formLayout3Col.add(moodComboBox);
        formLayout3Col.add(purposeComboBox);
        return formLayout3Col;
    }

    private static ComboBox addPurposeComboBox(GeneratePromptService promptService) {
        ComboBox purposeComboBox = new ComboBox();
        purposeComboBox.setLabel("Purpose");
        purposeComboBox.setWidth("min-content");
        purposeComboBox.setHelperText("Define the purpose of your writing prompt. This will guide your focus and intent.");
        promptService.fillPurposeData(purposeComboBox);
        return purposeComboBox;
    }

    private static ComboBox addMoodComboBox(GeneratePromptService promptService) {
        ComboBox moodComboBox = new ComboBox();
        moodComboBox.setLabel("Mood");
        moodComboBox.setWidth("min-content");
        moodComboBox.setHelperText("Choose the mood that best sets the emotional tone for your prompt.");
        promptService.fillMoodData(moodComboBox);
        return moodComboBox;
    }

    private static ComboBox addCategoryComboBox(GeneratePromptService promptService) {
        ComboBox categoryComboBox = new ComboBox();
        categoryComboBox.setLabel("Category");
        categoryComboBox.setWidth("min-content");
        categoryComboBox.setHelperText("Select a genre for your writing prompt. Each genre has distinct elements and settings.");
        promptService.fillCategoryData(categoryComboBox);
        return categoryComboBox;
    }

}
