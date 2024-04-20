package com.mrg.promptgenerator.views.generateprompt;

import com.mrg.promptgenerator.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import java.util.ArrayList;
import java.util.List;

@PageTitle("Generate Prompt")
@Route(value = "generate-prompt", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@AnonymousAllowed
@Uses(Icon.class)
public class GeneratePromptView extends Composite<VerticalLayout> {

    public GeneratePromptView() {

        FormLayout formLayout3Col = new FormLayout();
        formLayout3Col.setWidth("100%");
        formLayout3Col.setResponsiveSteps(
                new ResponsiveStep("0", 1),
                new ResponsiveStep("250px", 2),
                new ResponsiveStep("500px", 3)
        );

        ComboBox resourceComboBox = new ComboBox();
        resourceComboBox.setLabel("Resource");
        resourceComboBox.setWidth("min-content");
        setComboBoxSampleData(resourceComboBox);

        ComboBox categoryComboBox = new ComboBox();
        categoryComboBox.setLabel("Category");
        categoryComboBox.setWidth("min-content");
        setComboBoxSampleData(categoryComboBox);

        formLayout3Col.add(categoryComboBox);
        formLayout3Col.add(resourceComboBox);

        TextArea customizePromptTextArea = new TextArea();
        customizePromptTextArea.setLabel("Customize your prompts by providing a context.");
        customizePromptTextArea.setWidth("100%");

        Button generatePromptButton = new Button();
        generatePromptButton.setText("Generate Prompt");
        generatePromptButton.setWidth("min-content");
        generatePromptButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Paragraph textMedium = new Paragraph();
        textMedium.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        textMedium.setWidth("100%");
        textMedium.getStyle().set("font-size", "var(--lumo-font-size-m)");

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().add(formLayout3Col);
        getContent().add(customizePromptTextArea);
        getContent().add(generatePromptButton);
        getContent().add(textMedium);
    }

    record SampleItem(String value, String label, Boolean disabled) {
    }

    private void setComboBoxSampleData(ComboBox comboBox) {
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem("first", "First", null));
        sampleItems.add(new SampleItem("second", "Second", null));
        sampleItems.add(new SampleItem("third", "Third", Boolean.TRUE));
        sampleItems.add(new SampleItem("fourth", "Fourth", null));
        comboBox.setItems(sampleItems);
        comboBox.setItemLabelGenerator(item -> ((SampleItem) item).label());
    }
}
