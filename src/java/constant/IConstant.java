/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package constant;

/**
 *
 * @author nvg
 */
public class IConstant {
    private static final String GOOGLE_CLIENT_ID = "709737879497-ga296lhh44fac2ti4n07qdpjb56cpjko.apps.googleusercontent.com";

    private static final String GOOGLE_CLIENT_SECRET = "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";

    private static final String GOOGLE_REDIRECT_URI = "http://localhost:9999/wines/login";

    private static final String GOOGLE_GRANT_TYPE = "authorization_code";

    private static final String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";

    private static final String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";

    public static String getGOOGLE_CLIENT_ID() {
        return GOOGLE_CLIENT_ID;
    }

    public static String getGOOGLE_CLIENT_SECRET() {
        return GOOGLE_CLIENT_SECRET;
    }

    public static String getGOOGLE_REDIRECT_URI() {
        return GOOGLE_REDIRECT_URI;
    }

    public static String getGOOGLE_GRANT_TYPE() {
        return GOOGLE_GRANT_TYPE;
    }

    public static String getGOOGLE_LINK_GET_TOKEN() {
        return GOOGLE_LINK_GET_TOKEN;
    }

    public static String getGOOGLE_LINK_GET_USER_INFO() {
        return GOOGLE_LINK_GET_USER_INFO;
    }
    
    
}
