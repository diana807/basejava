package com.urise.webapp.model;

public enum SectionType {

    OBJECTIVE("Позиция"),
    PERSONAL("Личные качества"),
    ACHIEVEMENT("Достижения"),
    QUALIFICATIONS("Квалификация"),
    EXPERIENCE("Опыт работы"),
    EDUCATION("Образование");

    private String title;

    SectionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

//    protected Se toHtml0(String section) {
//        return title + ": " + section;
//    }
//
//    public Section toHtml(String section) {
//        return (section == null) ? "" : toHtml0(section);
//    }
}
