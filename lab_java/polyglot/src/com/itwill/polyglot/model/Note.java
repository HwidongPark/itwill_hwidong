package com.itwill.polyglot.model;

public class Note {
    // Fields
    private int noteId;
    private String noteName;
    private String languageCode;
    private String language;
    private String languageInKorean;
    
    
    // Constructor
    public Note() {}
    
    
    // TODO: 일단 Id넣어줌. DB읽는 과정에서 자연스럽게 얻는 정보라서.
    public Note(int noteId, String noteName, Language language) {
        this.noteId = noteId;
        this.noteName = noteName;
        this.languageCode = language.getLanguageCode();
        this.language = language.getLanguage();
        this.languageInKorean = language.getLanguageInKorean();
    }
    
    
    // Getters
    public int getNoteId() {
        return noteId;
    }

    public String getNoteName() {
        return noteName;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public String getLanguage() {
        return language;
    }
    
    public String getLanguageInKorean() {
        return languageInKorean;
    }


    @Override
    public String toString() {
        return "Note [noteId=" + noteId + ", noteName=" + noteName + ", languageCode=" + languageCode + ", language="
                + language + ", languageInKorean=" + languageInKorean + "]";
    }

    
    
}
