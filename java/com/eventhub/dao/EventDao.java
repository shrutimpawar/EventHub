
// package com.eventhub.dao;

// import java.util.UUID;
// import java.util.concurrent.ExecutionException;

// import com.eventhub.Model.Event;
// import com.eventhub.Model.User;
// import com.google.api.core.ApiFuture;
// import com.google.cloud.firestore.*;
// import com.google.cloud.firestore.QuerySnapshot;

// import java.util.*;
// import java.util.concurrent.*;

// public class EventDao {

//     public static Firestore eventhub_db;

//     public void addUserDetail(Event event)throws ExecutionException,InterruptedException{
//         String id=UUID.randomUUID().toString();
//         event.setEventId(id);
//         eventhub_db.collection("event").document(id).set(event).get();
//     }

//     public List<Event>getAllUsers()throws ExecutionException,InterruptedException{
//         List<Event> list=new ArrayList();
//         ApiFuture<QuerySnapshot>future=eventhub_db.collection("event").get();
//         for(DocumentSnapshot doc : future.get().getDocuments()){
//             list.add(doc.toObject(Event.class));
//         }
//         return list;
//     }

//     public Event getEventById(String eventId)throws ExecutionException,InterruptedException{
//         DocumentSnapshot snapshot=eventhub_db.collection("event").document(eventId).get().get();
//         return snapshot.exists()? snapshot.toObject(Event.class) : null;
//     }
    
// }
