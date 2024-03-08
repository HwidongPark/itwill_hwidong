package com.itwill.polyglot.model;

public class Word {
    // Fields
    private int wordId;
    private int noteId;
    private String word;
    private String meaning;
    private String noteName;
    private String languageCode;
    private String language;
    private String languageInKorean;
    // TODO: 난이도 필요한지 나중에 확인
    private String difficulty;
    
    
    // Constructors
    public Word() {}
    
    // TODO: 일단 여기 Id도 넣어줌. 어차피 DB읽어오는 과정에서 자연스럽게 얻는 정보라서.
    // TODO: noteID이거 중복같은데 나중에 생각해보자
    public Word(int wordId, int noteId, String word, String meaning, Note note) {
        this.wordId = wordId;
        this.noteId = noteId;
        this.word = word;
        this.meaning = meaning;
        this.noteName = note.getNoteName();
        this.languageCode = note.getLanguageCode();
        this.language = note.getLanguage();
        this.languageInKorean = note.getLanguageInKorean();
    }
    
    
    // 난이도 포함한 Word Constructor
    public Word(int wordId, String word, String meaning, String difficulty, Note note) {
        this.wordId = wordId;
        this.word = word;
        this.meaning = meaning;
        this.difficulty = difficulty;
        this.noteName = note.getNoteName();
        this.noteId = note.getNoteId();
        this.languageCode = note.getLanguageCode();
        this.language = note.getLanguage();
        this.languageInKorean = note.getLanguageInKorean();
    }


    // Getters
    public int getWordId() {
        return wordId;
    }
    
    public int getNoteId() {
        return noteId;
    }

    public String getWord() {
        return word;
    }

    public String getMeaning() {
        return meaning;
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
    
    public String getDifficulty() {
        return difficulty;
    }
    
    public String getLanguageInKorean() {
        return languageInKorean;
    }
    
    // Setters
    public void setWordId(int wordId) {
        this.wordId = wordId;
    }
    
    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
    
    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    

    // toString override
    @Override
    public String toString() {
        return "Word [wordId=" + wordId + ", noteId=" + noteId + ", word=" + word + ", meaning=" + meaning
                + ", noteName=" + noteName + ", languageCode=" + languageCode + ", language=" + language
                + ", languageInKorean=" + languageInKorean + ", difficulty=" + difficulty + "]";
    }

    
    
}
