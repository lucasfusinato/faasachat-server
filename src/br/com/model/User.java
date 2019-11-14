package br.com.model;

/**
 * Model that represents user data.
 * @author João Victor Arruda
 * @since 12/11/2019
 * @version 1.0
 */
public class User {
    
    /**
     * User id.
     */
    private long id;
    
    /**
     * User nickname.
     */
    private String nickname;
    
    /**
     * User email.
     */
    private String email;
    
    /**
     * User password.
     */
    private String password;
    
    /**
     * User year of birth.
     */
    private int yearOfBirth;

    public User(long id, String nickname, String email, String password, int yearOfBirth) {
        if(id > 0){
            this.id = id;
        }
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.yearOfBirth = yearOfBirth;
    }

    /**
     * Returns user id.
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     * Defines user id.
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Returns user nickname.
     * @return
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Defines user nickname.
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Returns user email.
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * Defines user email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns user password.
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Defines user password.
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns user year of birth.
     * @return
     */
    public int getYearOfBirth() {
        return yearOfBirth;
    }

    /**
     * Defines user year of birth.
     * @param yearOfBirth
     */
    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
    
}
