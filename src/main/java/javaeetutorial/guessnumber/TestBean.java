/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.guessnumber;

import java.io.Serializable;
//import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author salih
 */
@Named
@SessionScoped
public class TestBean implements Serializable {

    private boolean loggedIn;
    private String username;
    private String password;
    private NavigationBean navigationBean = new NavigationBean();
    private static final String[] users = {"salih:123456", "real:madrid"};

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String doLogin() {
        // Get every user from our sample database :)

        for (String user : users) {
            String dbUsername = user.split(":")[0];
            String dbPassword = user.split(":")[1];
        // Successful login 
            if (dbUsername.equals(username) && dbPassword.equals(password)) {
                loggedIn = true;
                return navigationBean.redirectToWelcome();
            }
        }




        // Set login ERROR

        //FacesMessage msg = new FacesMessage("Login error!", "ERROR MSG");
        //msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        //FacesContext.getCurrentInstance().addMessage(null, msg);

        // To to login page
        return navigationBean.toLogin();


    }

    public String doLogout() {
        loggedIn = false;

        return navigationBean.redirectToLogin();
    }
}
