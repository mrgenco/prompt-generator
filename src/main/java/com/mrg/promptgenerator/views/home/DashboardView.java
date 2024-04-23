package com.mrg.promptgenerator.views.home;

import com.mrg.promptgenerator.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Dashboard")
@Route(value = "dashboard", layout = MainLayout.class)
@AnonymousAllowed
@Uses(Icon.class)
public class DashboardView extends Composite<VerticalLayout> {

    public DashboardView() {
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
    }
}
