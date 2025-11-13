// package com.eventhub.dao;

// import java.util.UUID;
// import java.util.concurrent.ExecutionException;

// import com.eventhub.Model.User;
// import com.google.api.core.ApiFuture;
// import com.google.cloud.firestore.*;
// import com.google.cloud.firestore.QuerySnapshot;

// import java.util.*;
// import java.util.concurrent.*;

// public class UserDao {

//     public static Firestore eventhub_db;

//     public void addUserDetail(User user)throws ExecutionException,InterruptedException{
//         String id=UUID.randomUUID().toString();
//         user.setUserId(id);
//         eventhub_db.collection("user").document(id).set(user).get();
//     }

//     public List<User>getAllUsers()throws ExecutionException,InterruptedException{
//         List<User> list=new ArrayList();
//         ApiFuture<QuerySnapshot>future=eventhub_db.collection("user").get();
//         for(DocumentSnapshot doc : future.get().getDocuments()){
//             list.add(doc.toObject(User.class));
//         }
//         return list;
//     }

//     public User getUserById(String userId)throws ExecutionException,InterruptedException{
//         DocumentSnapshot snapshot=eventhub_db.collection("user").document(userId).get().get();
//         return snapshot.exists()? snapshot.toObject(User.class) : null;
//     }
    
// }
