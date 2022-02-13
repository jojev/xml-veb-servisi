package main.java.com.xml.userbackend.config.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "accessToken",
        "expiresIn",
        "name",
        "roles"
})
@XmlRootElement(name = "usertokenstate")
public class UserTokenStateDTO {
    private String accessToken;
    private List<String> roles;
    private Long expiresIn;
    private String name;

    public UserTokenStateDTO() {
        this.accessToken = null;
        this.expiresIn = null;
    }

    public UserTokenStateDTO(String accessToken, long expiresIn, List<String> roles, String name) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.roles = roles;
        this.name = name;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}