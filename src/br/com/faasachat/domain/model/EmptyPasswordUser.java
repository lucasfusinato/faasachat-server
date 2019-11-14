package br.com.faasachat.domain.model;

public class EmptyPasswordUser extends User {
    
    public EmptyPasswordUser(User user) {
        super(user);
    }
    
    @Override
    public void setPassword(String password) {
        return;
    }

    @Override
    public String getPassword() {
        return null;
    }

}
