package com.example.inhamonchallenge.domain.report.domain;

public enum ReportDescription {

    ADVERTISEMENT_AND_PROMOTION("광고 및 홍보성 내용"),
    OBSCENE_LANGUAGE("욕설 및 외설적인 언어 사용"),
    SEXUAL_VIOLENT_CONTENT("선정적 · 폭력적 내용"),
    DEFAMATION("명예회손 및 타인 비방"),
    SPAM("도배성 내용"),
    POLITICAL_SOCIAL_OPINION("정치적 사회적 의견 표출"),
    OTHERS("기타 사유");

    private final String description;

    ReportDescription(String description) {
        this.description = description;
    }

    public static ReportDescription fromString(String description) {
        for (ReportDescription value : ReportDescription.values()) {
            if (value.description.equals(description)) {
                return value;
            }
        }

        throw new IllegalArgumentException("No constant with text " + description + " found");
    }

}
