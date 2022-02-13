package main.java.com.xml.userbackend.config.dto;


import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "username",
        "password",
})
@XmlRootElement(name = "jwtauthenticationrequest")
public class JwtAuthenticationRequest {
    @XmlElement(required = true)
    private String username;
    @XmlElement(required = true)
    private String password;

    public JwtAuthenticationRequest() {
        super();
    }

    public JwtAuthenticationRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}