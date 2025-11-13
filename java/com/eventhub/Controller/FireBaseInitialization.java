// package com.eventhub.Controller;

// import java.io.FileInputStream;
// import java.io.IOException;

// import com.eventhub.dao.EventDao;
// import com.eventhub.dao.OrganizerDao;
// import com.eventhub.dao.UserDao;
// import com.google.auth.oauth2.GoogleCredentials;
// import com.google.firebase.FirebaseApp;
// import com.google.firebase.FirebaseOptions;
// import com.google.firebase.cloud.FirestoreClient;

// public class FireBaseInitialization {

//     public static void init() {
//         try {
//             FileInputStream serviceAccount = new FileInputStream(
//                     "authentication\\src\\main\\resources\\eventhub-6ff68-firebase-adminsdk-fbsvc-2ed8e24f61.json");

//             FirebaseOptions options = FirebaseOptions.builder()
//                     .setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

//             if (FirebaseApp.getApps().isEmpty()) {
//                 FirebaseApp.initializeApp(options);
//             }
//             EventDao.eventhub_db = FirestoreClient.getFirestore();
//             UserDao.eventhub_db = FirestoreClient.getFirestore();
//             OrganizerDao.eventhub_db = FirestoreClient.getFirestore();
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

// }
