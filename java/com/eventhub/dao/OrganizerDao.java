
// package com.eventhub.dao;

// import java.util.*;
// import java.util.UUID;
// import java.util.concurrent.ExecutionException;

// import com.eventhub.Model.Organizer;
// import com.google.api.core.ApiFuture;
// import com.google.cloud.firestore.Firestore;
// import com.google.cloud.firestore.*;
// import java.util.concurrent.*;

// public class OrganizerDao {

//     public static Firestore eventhub_db;

//     public void addUserDetail(Organizer organizer) throws ExecutionException, InterruptedException {
//         String id = UUID.randomUUID().toString();
//         organizer.setOrganizerId(id);
//         eventhub_db.collection("organizer").document(id).set(organizer).get();
//     }

//     public List<Organizer> getAllUsers() throws ExecutionException, InterruptedException {
//         List<Organizer> list = new ArrayList();
//         ApiFuture<QuerySnapshot> future = eventhub_db.collection("organizer").get();
//         for (DocumentSnapshot doc : future.get().getDocuments()) {
//             list.add(doc.toObject(Organizer.class));
//         }
//         return list;
//     }

//     public Organizer getUserById(String organizerId) throws ExecutionException, InterruptedException {
//         DocumentSnapshot snapshot = eventhub_db.collection("organizer").document(organizerId).get().get();
//         return snapshot.exists() ? snapshot.toObject(Organizer.class) : null;
//     } 

// }
