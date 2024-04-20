package com.mrg.promptgenerator.views.authorprompt;

import com.mrg.promptgenerator.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PageTitle("Author Prompt")
@Route(value = "author-prompt", layout = MainLayout.class)
@PermitAll
@Uses(Icon.class)
public class AuthorPromptView extends Composite<VerticalLayout> {

    public AuthorPromptView() {
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
    }
}
