package com.mrg.promptgenerator.views.generateprompt;

import com.mrg.promptgenerator.data.SampleItem;
import com.mrg.promptgenerator.services.GeneratePromptService;
import com.mrg.promptgenerator.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.springframework.ai.chat.prompt.Prompt;

@PageTitle("Generate Prompt")
@Route(value = "generate-prompt", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@AnonymousAllowed
@Uses(Icon.class)
public class GeneratePromptView extends Composite<VerticalLayout> {

    public GeneratePromptView(GeneratePromptService promptService) {

        VerticalLayout verticalLayout = new VerticalLayout();

        H4 generatePromptFormHeader = new H4("Design Your Prompt");
        // Create ComboBoxes for category, mood, and purpose
        ComboBox categoryComboBox = addCategoryComboBox(promptService);
        ComboBox moodComboBox = addMoodComboBox(promptService);
        ComboBox purposeComboBox = addPurposeComboBox(promptService);

        // Create FormLayout
        FormLayout formLayout = createFormLayout(categoryComboBox, moodComboBox, purposeComboBox);

        // TextArea for custom prompt
        TextArea customizePromptTextArea = addCustomizePrompt();

        // Other components
        Paragraph textMedium = new Paragraph("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        textMedium.getStyle().set("font-size", "var(--lumo-font-size-m)");

        H4 examplePrompts = new H4("Example Prompts");
        MultiSelectListBox<String> listBox = new MultiSelectListBox<>();

        Button generatePromptButton = new Button();
        generatePromptButton.setText("Generate Prompt");
        generatePromptButton.setWidth("min-content");
        generatePromptButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        generatePromptButton.setDisableOnClick(true);

        verticalLayout.add(generatePromptFormHeader, formLayout, customizePromptTextArea, generatePromptButton);
        verticalLayout.addClassNames(LumoUtility.BoxShadow.MEDIUM, LumoUtility.Background.BASE);

        VerticalLayout verticalLayout2 = new VerticalLayout();
        verticalLayout2.addClassName("generate-prompt-view-vertical-layout-1");
        verticalLayout2.add(examplePrompts);
        verticalLayout2.add(listBox);
        verticalLayout2.addClassNames(LumoUtility.BoxShadow.MEDIUM, LumoUtility.Background.BASE);

        generatePromptButton.addClickListener(clickEvent -> {
            listBox.setItems(promptService.generatePrompt((SampleItem) categoryComboBox.getValue(), (SampleItem) moodComboBox.getValue(), (SampleItem) purposeComboBox.getValue(), customizePromptTextArea.getValue()));
            generatePromptButton.setEnabled(true);
        });
        // Create HorizontalLayout to hold form and text area
        HorizontalLayout formAndCustomTextLayout = new HorizontalLayout();
        formAndCustomTextLayout.setWidth("100%");
        formAndCustomTextLayout.add(verticalLayout, verticalLayout2);
        formAndCustomTextLayout.setFlexGrow(2, verticalLayout);
        formAndCustomTextLayout.setFlexGrow(1, verticalLayout2);

        // Adding components to the main layout
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().add(formAndCustomTextLayout);
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
