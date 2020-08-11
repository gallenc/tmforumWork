package org.opennms.test.scriptd.scriptdtest.client.logic;

public class UrlCredential {
    private String m_url;
    private String m_username;
    private String m_password;
    private String m_query;
    
    public UrlCredential(String url, String username, String password, String query) {
        this.m_url = url;
        this.m_username = username;
        this.m_password = password;
        this.m_query=query;
    }
    
    public String getUrl() {
        return m_url;
    }
    
    public String getUsername() {
        return m_username;
    }
    
    public String getPassword() {
        return m_password;
    }

    public String getQuery() {
        return m_query;
    }
 
}
