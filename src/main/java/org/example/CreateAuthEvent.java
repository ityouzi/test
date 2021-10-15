package org.example;

import java.io.Serializable;

/**
 * @author lizhen created on 2021-10-11 18:31
 */
public class CreateAuthEvent extends HomeSchoolEvent implements Serializable {

    private static final long serialVersionUID = -6488906109046830617L;
    String authCode;

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public CreateAuthEvent(String authCode) {
        this.authCode = authCode;
    }

    public CreateAuthEvent() {
    }
}
