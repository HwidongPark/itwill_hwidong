package com.itwill.polyglot.model;

public class Language {
    // Fields
    private String languageCode;
    private String language;
    private String languageInKorean;
    
    // Constructors
    public Language() {}

    public Language(String languageCode, String language, String languageInKorean) {
        this.languageCode = languageCode;
        this.language = language;
        this.languageInKorean = languageInKorean;
    }
    
    
    // Getters
    public String getLanguageCode() {
        return languageCode;
    }

    public String getLanguage() {
        return language;
    }
    
    public String getLanguageInKorean() {
        return languageInKorean;
    }
    
    // toString Override
    @Override
    public String toString() {
        return "Languages [languageCode=" + languageCode + ", language=" + language + "]";
    }
    
    
    
    
}
