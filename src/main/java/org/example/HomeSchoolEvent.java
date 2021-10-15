package org.example;

import java.io.Serializable;

/**
 * @author lizhen created on 2021-10-11 18:31
 */
public class HomeSchoolEvent implements Serializable {

    private static final long serialVersionUID = -4703002633972211542L;
    /**
     * 第三方应用ID
     */
    String suiteId;
    /**
     * 授权企业的CorpID
     */
    protected String authCorpId;
    /**
     * 时间戳
     */
    String timeStamp;
    /**
     * id  不同事件类型下 id表示的意义也不同
     */
    String id;

    public String getSuiteId() {
        return suiteId;
    }

    public void setSuiteId(String suiteId) {
        this.suiteId = suiteId;
    }

    public String getAuthCorpId() {
        return authCorpId;
    }

    public void setAuthCorpId(String authCorpId) {
        this.authCorpId = authCorpId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HomeSchoolEvent() {
    }

    public HomeSchoolEvent(String suiteId, String authCorpId, String timeStamp, String id) {
        this.suiteId = suiteId;
        this.authCorpId = authCorpId;
        this.timeStamp = timeStamp;
        this.id = id;
    }
}
