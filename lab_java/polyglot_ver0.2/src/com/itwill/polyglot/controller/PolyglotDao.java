package com.itwill.polyglot.controller;

import static com.itwill.polyglot.jdbc.OracleJdbc.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itwill.polyglot.model.Account;
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
    
    
    // TODO: 아이디, 비밀번호를 입력받아서 매칭되는 계정 돌려주기
    String SQL_SELECT_MATCHING_ACCOUNT = "SELECT * FROM account WHERE account = ? AND password = ?";
    /**
     * 유저가 입력한 아이디, 비밀번호에 해당하는 계정이 있는지 확인하고
     * 있을 시 해당 계정의 객체를 반환, 없을 시 null반환
     * @param userInputAccount
     * @param userInputPassword
     * @return 매칭되는 아이디가 있으면 아이디 객체, 없을 시 null을 반환
     */
    public Account hasMatchintAccount(String userInputAccount, String userInputPassword) {
        Account result = null;
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = conn.prepareStatement(SQL_SELECT_MATCHING_ACCOUNT);
            stmt.setString(1, userInputAccount);
            stmt.setString(2, userInputPassword);
            rs = stmt.executeQuery();
            if (rs.next()) {
                String account = rs.getString("account");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                
                result = new Account(account, password, phone, email);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }
        
        return result;
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
            + "WHERE n.account = ? AND n.language_code = ?";
    
    
    // 해당 유저의 선택된 언어의 노트 리스트를 불러오는 DAO
    /**
     * 선택된 언어를 아규먼트로 받아, 그 언어에 해당되는 노트들을 반환
     * @param language
     * @return List<Note> 해당 유저, 언어의 노트들. 노트 부재시 빈 리스트 반환
     */
    public List<Note> readNotesInLanguage(String account, Language language) {
        List<Note> result = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = conn.prepareStatement(SQL_SELECT_NOTES_IN_LANGUAGE);
            // TODO: 채워넣기
            stmt.setString(1, account);
            stmt.setString(2, language.getLanguageCode());
            rs = stmt.executeQuery();
            
            
            
            while (rs.next()) {
                int id = rs.getInt("note_id");
                String noteName = rs.getString("note_name");

                result.add(new Note(id, noteName, language, account));
            }
            
            // TODO: 테스트용 나중에 지우기
            System.out.println(result); // 노트 주소 리스트
            System.out.println(account);
            System.out.println("111");
            
        } catch (Exception e) {
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
    
    
    
    
    String SQL_SELECT_ALL_WORDS = "SELECT w.word_id, w.word, w.meaning, w.note_id, w.difficulty, n.note_name, n.language_code, n.account "
            + "FROM words w JOIN notes n ON w.note_id = n.note_id "
            + "WHERE language_code = ? AND n.account = ?";
    
    // 해당 유저의 학습언어 Words DB에 있는 모든 단어 가져오기
    /**
     * 해당 유저의 학습언어 모든 단어 리스트를 읽어옴
     * @param language
     * @return 유저의 학습언어 모든 단어를 List<Word>타입으로 반환
     */
    public List<Word> readWords(String accountSignedIn, Language language) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Word> words = new ArrayList<>();
        
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = conn.prepareStatement(SQL_SELECT_ALL_WORDS);
            stmt.setString(1,  language.getLanguageCode());
            stmt.setString(2, accountSignedIn);
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                int wordId = rs.getInt("WORD_ID");
                String word = rs.getString("word");
                String meaning = rs.getString("meaning");
                String difficulty = rs.getString("difficulty");
                String account = rs.getString("account");
                
                int noteId = rs.getInt("note_id");
                String noteName = rs.getString("note_name");
                
                Note note = new Note(noteId, noteName, language, account);
                Word wordObject = new Word(wordId, word, meaning, difficulty, note);
                
                words.add(wordObject);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }
        
        return words;
    }
    
    
    String SQL_SELECT_ALL_USERS = "SELECT * FROM account";
    
    // 모든 회원의 객체를 가져오는 메서드
    /**
     * 모든 회원들의 객체를 리스트로 반환
     * @return 모든회원 리스트, 회원 부재시 빈 리스트 반환
     */
    public List<Account> readAllAccounts() {
        List<Account> result = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = conn.prepareStatement(SQL_SELECT_ALL_USERS);
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                String account = rs.getString("account");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                
                Account accountAddedToList = new Account(account, password, phone, email);
                result.add(accountAddedToList);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    
    String SQL_INSERT_NEW_USER = "INSERT INTO account (account, password, phone, email) VALUES (?, ?, ?, ?)";
    
    // 회원가입 메서드
    /**
     * 회원가입 할 어카운트 객체를 받아서 회원가입
     * @param accountToSignUp
     * @return 성공 시 1, 실패 시 0
     */
    public int signUp (Account accountToSignUp) {
        int result = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = conn.prepareStatement(SQL_INSERT_NEW_USER);
            stmt.setString(1, accountToSignUp.getAccount());
            stmt.setString(2, accountToSignUp.getPasssword());
            stmt.setString(3, accountToSignUp.getPhone());
            stmt.setString(4, accountToSignUp.getEmail());
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt);
        }
        
        return result;
    }
    
    
    // 아이디 찾는 메서드들
    String SQL_SELECT_FIND_ACCOUNT_WITH_PHONE =
            "SELECT * FROM account WHERE phone = ?";
    
    String SQL_SELECT_FIND_ACCOUNT_WITH_EMAIL =
            "SELECT * FROM account WHERE email = ?";
    
    /**
     * 유저가 입력한 전화번호와 매치되는 Account를 반환.
     * @param phone
     * @return 매칭되는 계정 존재 시 Account, 아닐 시 null
     */
    public Account findAccountWithPhone(String phone) {
        Account result = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = conn.prepareStatement(SQL_SELECT_FIND_ACCOUNT_WITH_PHONE);
            stmt.setString(1, phone);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                String account = rs.getString("account");
                String password = rs.getString("password");
                String phoneNumber = rs.getString("phone");
                String email = rs.getString("email");
                
                result = new Account(account, password, phoneNumber, email);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }
        
        
        return result;
    }
    
    /**
     * 유저가 입력한 이메일과 매칭되는 Account를 반환
     * @param email
     * @return 매칭되는 계정 존재 시 Account, 아닐 시 null
     */
    public Account findAccountWithEmail(String email) {
        Account result = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = conn.prepareStatement(SQL_SELECT_FIND_ACCOUNT_WITH_EMAIL);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                String account = rs.getString("account");
                String password = rs.getString("password");
                String phoneNumber = rs.getString("phone");
                String emailOfAccount = rs.getString("email");
                
                result = new Account(account, password, phoneNumber, emailOfAccount);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }
        
        
        return result;
    }
    
    String SQL_SELECT_ACCOUNT_WITH_ID = "SELECT * FROM account WHERE account = ?";
    // 유저 입력 아이디와 매칭되는 계정을 리턴
    /**
     * 유저로부터 아이디를 아규먼트로 입력받고, 매칭되는 계정을 반환
     * @param idUserInput
     * @return 매칭되는 계정 존재 시 Account, 아닐 시 null
     */
    public Account findAccountWithId(String idUserInput) {
        Account result = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = conn.prepareStatement(SQL_SELECT_ACCOUNT_WITH_ID);
            stmt.setString(1, idUserInput);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                String account = rs.getString("account");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                
                result = new Account(account, password, phone, email);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }
        
        return result;
    }
    
    
    String SQL_UPDATE_PASSWORD_TO_0000 = "UPDATE account SET password = '0000' WHERE account = ?";
    
    // 유저의 비밀번호를 0000으로 초기화
    /**
     * 비밀번호를 0000으로 초기화 할 계정을 아규먼트로 받아 비밀번호 초기화
     * @param accountToUpdate
     * @return 비밀번호 초기화 성공 시 1, 실패 시 0
     */
    public int updatePasswordTo0000(Account accountToUpdate) {
        int result = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = conn.prepareStatement(SQL_UPDATE_PASSWORD_TO_0000);
            stmt.setString(1, accountToUpdate.getAccount());
            result = stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt);
        }
        
        return result;
    }
    
    
    String SQL_UPDATE_PASSWORD = "UPDATE account SET password = ? WHERE account = ?";
    // 유저 비밀번호 업데이트
    /**
     * 비밀번호 변경할 계정과 새 비밀번호를 아규먼트로 받아 비밀번호 업데이트
     * @param accountToUpdate
     * @param passwordToUpdate
     * @return 성공 시 1, 실패 시 0
     */
    public int updatePassword(Account accountToUpdate, String passwordToUpdate) {
        int result = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = conn.prepareStatement(SQL_UPDATE_PASSWORD);
            stmt.setString(1, passwordToUpdate);
            stmt.setString(2, accountToUpdate.getAccount());
            result = stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt);
        }

        return result;
    }
    
    
    String SQL_UPDATE_PHONE_EMAIL = "UPDATE account SET phone = ?, email = ? WHERE account = ?";
    // 유저 이메일, 전화번호 업데이트
    /**
     * 유저가 입력한 전화번호, 이메일로 업데이트. 공백을 입력 시 null로 업데이트 됨
     * @param accountToUpdate
     * @param phoneToUpdate
     * @param emailToUpdate
     * @return 업데이트 성공 시 1, 실패 시 0
     */
    public int updatePhoneEmail(Account accountToUpdate, String phoneToUpdate, String emailToUpdate) {
        int result = 0;
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = conn.prepareStatement(SQL_UPDATE_PHONE_EMAIL);
            stmt.setString(1, phoneToUpdate);
            stmt.setString(2, emailToUpdate);
            stmt.setString(3, accountToUpdate.getAccount());
            result  = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt);
        }
        
        return result;
    }
    
    
    String SQL_INSERT_NOTE = "INSERT INTO notes (note_name, language_code, account) VALUES (?, ?, ?)";
    
    // 해당 유저의 계정의 언어 노트를 추가하는 메서드
    /**
     * 유저 계정에 학습 언어 노트를 추가. 추가된 노트의 개수(주로 1개)를 콘솔에 프린트
     * @param langauge
     * @param noteName
     * @return 삽입 된 행의 개수
     */
    public int addNote(Language language, String noteName, String accountSignedIn) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int result = 0;
        
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = conn.prepareStatement(SQL_INSERT_NOTE);
            stmt.setString(1, noteName);
            stmt.setString(2, language.getLanguageCode());
            stmt.setString(3, accountSignedIn);
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
    public List<Note> searchNotesInLanguage(Language language, String searchText, String accountSignedIn) {
        List<Note> result = new ArrayList<Note>();
        String[] trimmedSearchText = searchText.split(" ");
        
        // 해당 유저, 언어의 전체 노트 리스트
        List<Note> notesInLanguage = this.readNotesInLanguage(accountSignedIn, language);
        
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
    
    
    // 해당 유저, 언어의 단어 검색 다오
    /**
     * 유저가 입력한 검색어를 포함하고 있는 해당 유저의 언어에 저장돼 있는 모든 단어의 리스트를 반환
     * @param language
     * @param searchText
     * @return 검색어를 포함하는 학습 언어의 단어를 반환
     */
    public List<Word> searchWordsInLanguage(Language language, String searchText, String accountSignedIn) {
        List<Word> result = new ArrayList<>();
        String[] trimmedSearchText = searchText.split(" ");
        
        // 해당 언어의 전체 단어 리스트
        List<Word> wordsInLanguage = this.readWords(accountSignedIn, language);
        
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