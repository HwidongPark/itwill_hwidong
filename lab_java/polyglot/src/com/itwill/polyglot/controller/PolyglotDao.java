package com.itwill.polyglot.controller;

import static com.itwill.polyglot.jdbc.OracleJdbc.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itwill.polyglot.model.Language;
import com.itwill.polyglot.model.Note;
import com.itwill.polyglot.model.Word;

public class PolyglotDao {
    
    // Singleton
    private static PolyglotDao instance = null;
    private PolyglotDao() {}
    public static PolyglotDao getInstance() {
        if (instance == null) {
            instance = new PolyglotDao();
        }
        
        return instance;
    }
    
    
    // 유저가 학습하는 언어 가져오기 (노트이름, 언어코드, 언어이름 join으로 가져옴)
    String SQL_SELECT_LANGUAGE_TO_STUDY = "SELECT * FROM languages WHERE language_code = ? ";
    
    /**
     * 유저가 학습하는 언어를 가져옴.
     * @param type int. 유저가 학습하기 원하는 언어코드
     * @return Language 유저가 학습하기 원하는 언어의 Language객체
     */
    public Language readLanguage(String languageToStudy) {
        Language result = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = conn.prepareStatement(SQL_SELECT_LANGUAGE_TO_STUDY);
            stmt.setString(1, languageToStudy);
            rs = stmt.executeQuery();
            
            
            if (rs.next()) {
                String languageCode = rs.getString("language_code");
                String languageName = rs.getString("language");
                String languageInKorean = rs.getString("language_in_korean");
                // TODO: 테스트코드 삭제
                System.out.println(languageCode + "in get language");
                System.out.println(languageName + "in get language");
                System.out.println(languageInKorean + "in get language");
                // 테스트코드(위)
                result = new Language(languageCode, languageName, languageInKorean);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }
        
        return result;
    }
    
    
    String SQL_SELECT_NOTES_IN_LANGUAGE = "SELECT * FROM notes n JOIN languages l ON n.language_code = l.language_code "
            + "WHERE n.language_code = ?";
    
    
    // 선택된 언어의 노트 리스트를 불러오는 DAO
    /**
     * 선택된 언어를 아규먼트로 받아, 그 언어에 해당되는 노트들을 반환
     * @param language
     * @return List<Note> 그 언어에 해당되는 노트들. 노트 부재시 빈 리스트 반환
     */
    public List<Note> readNotesInLanguage(Language language) {
        List<Note> result = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = conn.prepareStatement(SQL_SELECT_NOTES_IN_LANGUAGE);
            // TODO: 채워넣기
            stmt.setString(1, language.getLanguageCode());
            rs = stmt.executeQuery();
            
            // TODO:테스트용 나중에 지우기
            System.out.println(language.getLanguageCode() + "123123");
            
            while (rs.next()) {
                int id = rs.getInt("note_id");
                String noteName = rs.getString("note_name");
                // TODO: 테스트용 나중에 지우기
//                System.out.println(id);
//                System.out.println(noteName);
                result.add(new Note(id, noteName, language));
            }
            
            // TODO: 테스트용 나중에 지우기
            System.out.println(result); // 노트 주소 리스트
            System.out.println("111");
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }
        
        return result;
    }
    
    
    String SQL_SELECT_WORDS = "SELECT * FROM words WHERE note_id = ?";
    // 해당 노트에 저장돼 있는 단어들을 가져오는 메서드
    /**
     * 해당 노트에 있는 단어 리스트들을 읽음
     * @param note
     * @return 노트의 단어리스트 List<Word>
     */
    public List<Word> readWords(Note note) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Word> words = new ArrayList<>();
        
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = conn.prepareStatement(SQL_SELECT_WORDS);
            stmt.setInt(1, note.getNoteId());
            rs = stmt.executeQuery();
            
            
            while(rs.next()) {

                int wordId = rs.getInt("WORD_ID");
                int noteId = rs.getInt("NOTE_ID");
                String word = rs.getString("WORD");
                String meaning = rs.getString("MEANING");
                
                words.add(new Word(wordId, noteId, word, meaning, note));
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }
        
        return words;
        
    }
    
    
    
    
    String SQL_SELECT_ALL_WORDS = "SELECT w.word_id, w.word, w.meaning, w.note_id, w.difficulty, n.note_name, n.language_code "
            + "FROM words w JOIN notes n ON w.note_id = n.note_id "
            + "WHERE language_code = ?";
    
    // Words DB에 있는 모든 단어 가져오기
    /**
     * 해당 언어의 모든 단어 리스트를 읽어옴
     * @param language
     * @return 해당 언어의 모든 단어를 List<Word>타입의 단어들을 반환
     */
    public List<Word> readWords(Language language) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Word> words = new ArrayList<>();
        
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = conn.prepareStatement(SQL_SELECT_ALL_WORDS);
            stmt.setString(1,  language.getLanguageCode());
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                int wordId = rs.getInt("WORD_ID");
                String word = rs.getString("word");
                String meaning = rs.getString("meaning");
                String difficulty = rs.getString("difficulty");
                
                int noteId = rs.getInt("note_id");
                String noteName = rs.getString("note_name");
                
                Note note = new Note(noteId, noteName, language);
                Word wordObject = new Word(wordId, word, meaning, difficulty, note);
                
                words.add(wordObject);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return words;
    }
    
    
    
    String SQL_INSERT_NOTE = "INSERT INTO notes (note_name, language_code) VALUES (?, ?)";
    
    // 해당 언어의 노트를 추가하는 메서드
    /**
     * 학습 언어에 노트를 추가. 추가된 노트의 개수(주로 1개)를 콘솔에 프린트
     * @param langauge
     * @param noteName
     * @return 삽입 된 행의 개수
     */
    public int addNote(Language language, String noteName) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int result = 0;
        
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = conn.prepareStatement(SQL_INSERT_NOTE);
            stmt.setString(1, noteName);
            stmt.setString(2, language.getLanguageCode());
            result = stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt);
        }
        
        return result;
    }
    
    
    String SQL_ADD_WORD = 
            "INSERT INTO words (word, meaning, note_id) VALUES (?, ?, ?)";
    
    // 해당 노트 단어 추가 메서드
    /**
     * 학습중인 노트에 단어를 추가하는 메서드
     * @param note 학습중인 노트
     * @param word 추가할 단어
     * @param meaning 단어의 뜻
     * @return 추가된 행의 개수(주로 1)를 리턴
     */
    public int addWord(Note note, String word, String meaning) {
        int result = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = conn.prepareStatement(SQL_ADD_WORD);
            stmt.setString(1, word);
            stmt.setString(2, meaning);
            stmt.setInt(3, note.getNoteId());
            
            result = stmt.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt);
        }
        
        return result;
    }
    
    
    
    String SQL_DELETE_NOTE = "DELETE FROM notes WHERE note_id = ?";
    
    // 노트 삭제 메서드
    /**
     * 선택한 노트를 삭제하는 메서드
     * @param note
     * @return 성공적으로 삭제 시 삭제된 행의 개수를 반환.
     */
    public int deleteNote(Note note) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int result = 0;
        
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = conn.prepareStatement(SQL_DELETE_NOTE);
            stmt.setInt(1, note.getNoteId());
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt);
        }
        
        return result;
    }
    
    
    String SQL_DELETE_WORD = "DELETE FROM words WHERE word_id = ?";
    
    // 단어 삭제 메서드
    public int deleteWord(Word word) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int result = 0;
        
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = conn.prepareStatement(SQL_DELETE_WORD);
            stmt.setInt(1, word.getWordId());
            result = stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt);
        }
        
        return result;
    }
    
    
    String SQL_UPDATE_WORD = "UPDATE words SET word = ?, meaning = ? where word_id = ?";
    
    // 단어 업데이트 메서드
    /**
     * 기존 단어 업데이트하는 메서드.
     * @param word
     * @param wordUpdatingTo
     * @param meaningUpdatingTo
     * @return
     */
    public int updateWord(Word word, String wordUpdatingTo, String meaningUpdatingTo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int result = 0;
        
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = conn.prepareStatement(SQL_UPDATE_WORD);
            stmt.setString(1, wordUpdatingTo);
            stmt.setString(2,  meaningUpdatingTo);
            stmt.setInt(3, word.getWordId());
            // TODO: 테스트용 나중에 삭제
            System.out.println("다오에서 wordId=" + word.getWordId());
            result = stmt.executeUpdate();
            // TODO: 테스트용 나중에 삭제하기
            System.out.println(result + "행 업데이트 완료");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt);
        }
        
        return result;
    }
    
    
    String SQL_UPDATE_DIFFICULTY = "UPDATE words SET difficulty = ? WHERE word_id = ?";
    
    // 난이도 업데이트하는 메서드
    /**
     * 단어 객체와 난이도를 입력받아 단어의 난이도 필드를 업데이트 하는 메서드
     * @param word
     * @param difficulty
     * @return 업데이트 성공하면 1 실패하면 0
     */
    public int updateDifficulty(Word word, String difficulty) {
        int result = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = conn.prepareStatement(SQL_UPDATE_DIFFICULTY);
            stmt.setString(1, difficulty);
            stmt.setInt(2, word.getWordId());
            result = stmt.executeUpdate();
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt);
        }
        
        
        return result;
    }
    
    
    // 해당 언어의 노트 검색 다오
    /**
     * 유저로부터 검색어를 받아서 해당 검색어를 포함하고 있는 현재 학습언어의 노트를 모두 반환
     * @param language
     * @param searchText
     * @return 검색어를 포함하고 있는 해당 학습 언어의 노트를 반환
     */
    public List<Note> searchNotesInLanguage(Language language, String searchText) {
        List<Note> result = new ArrayList<Note>();
        String[] trimmedSearchText = searchText.split(" ");
        
        // 해당 언어의 전체 노트 리스트
        List<Note> notesInLanguage = this.readNotesInLanguage(language);
        
        // 검색어 포함할 경우 result list에 포함
        for (Note noteInLanguage : notesInLanguage) {
            String noteName = noteInLanguage.getNoteName().toLowerCase().replace(" ", "");
            for (String searchingNote : trimmedSearchText) {
                
                if (noteName.contains(searchingNote)) {
                    if (!result.contains(noteInLanguage)) {
                        result.add(noteInLanguage);
                    }
                }
                
            }
           
        }
        
        System.out.println(result);
        
        return result;
    }
    
    
    // 해당 언어의 단어 검색 다오
    /**
     * 유저가 입력한 검색어를 포함하고 있는 해당 언어에 저장돼 있는 모든 단어의 리스트를 반환
     * @param language
     * @param searchText
     * @return 검색어를 포함하는 학습 언어의 단어를 반환
     */
    public List<Word> searchWordsInLanguage(Language language, String searchText) {
        List<Word> result = new ArrayList<>();
        String[] trimmedSearchText = searchText.split(" ");
        
        // 해당 언어의 전체 단어 리스트
        List<Word> wordsInLanguage = this.readWords(language);
        
        // 검색어 포함할 경우 result list에 포함
        for (Word wordInLanguage : wordsInLanguage) {
            String word = wordInLanguage.getWord().toLowerCase().replace(" ", "");
            for (String searchingWord : trimmedSearchText) {
                if (word.contains(searchingWord)) {
                    
                    if (!result.contains(wordInLanguage)) {
                        result.add(wordInLanguage);
                    }
                    
                }    
            }
            
        }
        
        return result;
    }
    
    
    // 해당 노트의 단어 검색 다오
    /**
     * 유저의 검색어를 포함하는 해당 노트에 저장 돼 있는 모든 단어 반환
     * @param note
     * @param searchText
     * @return 유저의 검색어를 포함하는 해당 노트에 저장 돼 있는 모든 단어 반환
     */
    public List<Word> searchWordsInLanguage(Note note, String searchText) {
        List<Word> result = new ArrayList<>();
        String[] trimmedSearchText = searchText.split(" ");
        
        // 해당 언어의 전체 단어 리스트
        List<Word> wordsInNote = this.readWords(note);
        
        // 검색어 포함할 경우 result list에 포함
        for (Word wordInNote : wordsInNote) {
            String word = wordInNote.getWord().toLowerCase().replace(" ", "");
            for (String searchWord : trimmedSearchText) {
                if (word.contains(searchWord)) {
                    if (!result.contains(wordInNote)) {
                        result.add(wordInNote);    
                    }
                    
                }
            }
            
        }
        
        return result;
    }
    
    
    
    // resource들 닫는 메서드
    /**
     * DB연결때 사용한 connection, statement, result set을 닫아주는 메서드
     * @param conn
     * @param stmt
     * @param rs
     */
    private void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) {
        try {
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * DB연결 때 사용한 connection, statement를 닫아주는 메서드
     * @param conn
     * @param stmt
     */
    private void closeResources(Connection conn, PreparedStatement stmt) {
        try {
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
}   // DAO class 끝
