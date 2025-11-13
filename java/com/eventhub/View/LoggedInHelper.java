// package com.eventhub.View;

// public class LoggedInHelper {
//     private static String userDocId;
//     private static String email;

//     public static void setUserDocId(String id) {
//         userDocId = id;
//     }

//     public static String getUserDocId() {
//         return userDocId;
//     }

//     public static void setEmail(String e) {
//         email = e;
//     }

//     public static String getEmail() {
//         return email;
//     }
// }
package com.eventhub.View;

public class LoggedInHelper {
    private static String userDocId;
    private static String organizerDocId;
    private static String email;

    public static void setUserDocId(String id) {
        userDocId = id;
    }

    public static String getUserDocId() {
        return userDocId;
    }

    public static void setOrganizerDocId(String id) {
        organizerDocId = id;
    }

    public static String getOrganizerDocId() {
        return organizerDocId;
    }

    public static void setEmail(String e) {
        email = e;
    }

    public static String getEmail() {
        return email;
    }
}

