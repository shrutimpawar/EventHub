package com.eventhub;

import javax.management.Notification;

import com.eventhub.View.About;
import com.eventhub.View.AdminDashboard;
import com.eventhub.View.AdminLogin;
import com.eventhub.View.ChatPage;
import com.eventhub.View.EventDetailPage;
import com.eventhub.View.Homepage;
import com.eventhub.View.MultipleSelection;
//import com.eventhub.View.NotificationPage;
import com.eventhub.View.NotificationUI;
import com.eventhub.View.OrganizerDashboard;
import com.eventhub.View.PaymentPage;
import com.eventhub.View.SignUpLogin;
import com.eventhub.View.UserDashboard;
import com.eventhub.View.UserProfilePage;

import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    Application.launch(SignUpLogin.class,args);
   //  Application.launch(AdminDashboard.class,args);
       
     }
}