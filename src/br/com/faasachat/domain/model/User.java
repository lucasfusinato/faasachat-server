package br.com.faasachat.domain.model;

import java.util.Map;

import br.com.faasachat.domain.adapter.GsonAdapter;

/**
 * Model that represents user data.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 11/11/2019
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
    
    /**
     * Defines if user is online.
     */
    private boolean online;

    /**
     * Instantiates an empty user.
     */
    public User() {
        //Do nothing
    }

    /**
     * Instantiates an user from another user data.
     * @param user
     */
    public User(User user) {
        this();
        this.from(user);
    }
    
    /**
     * Instantiates an user with parameters
     * @param parameters
     */
    public User(Map<String, Object> parameters) {
        this();
        this.from(GsonAdapter.getInstance().fromJson(GsonAdapter.getInstance().toJson(parameters), User.class));
    }

    /**
     * Instantiates user from id.
     * @param nickname
     */
    public User(Integer id) {
        this();
        this.id = id;
    }

    /**
     * Instantiates user from nickname.
     * @param nickname
     */
    public User(String nickname) {
        this();
        this.nickname = nickname;
    }

    /**
     * Instantiates user with all fields.
     * @param id 
     * @param string
     * @param string2
     * @param string3
     * @param i
     */
    public User(long id, String nickname, String email, String password, int yearOfBirth) {
        this();
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.yearOfBirth = yearOfBirth;
    }

    /**
     * Defines this data from another user data.
     * @param user
     */
    public void from(User user) {
        this.setId(user.getId());
        this.setNickname(user.getNickname());
        this.setEmail(user.getEmail());
        this.setPassword(user.getPassword());
        this.setYearOfBirth(user.getYearOfBirth());
        this.setOnline(user.isOnline());
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
    
    /**
     * Returns if user is online.
     * @return
     */
    public boolean getOnline() {
        return online;
    }

    /**
     * Returns if user is online.
     * @return
     */
    public boolean isOnline() {
        return online;
    }
    
    /**
     * Defines if user is online.
     * @param online
     */
    public void setOnline(boolean online) {
        this.online = online;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id != other.id)
            return false;
        return true;
    }
    
}
