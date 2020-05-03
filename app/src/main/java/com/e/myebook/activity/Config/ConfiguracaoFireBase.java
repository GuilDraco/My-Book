package com.e.myebook.activity.Config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfiguracaoFireBase {

    private static FirebaseAuth firebaseAuth;
    private static DatabaseReference dataBaseReference;

    public static DatabaseReference getFirebaseDatabase(){
        if(dataBaseReference == null){
            dataBaseReference = FirebaseDatabase.getInstance().getReference();
        }
        return dataBaseReference;
    }

    public static FirebaseAuth getFirebaseAuth(){
        if(firebaseAuth == null){
            firebaseAuth = FirebaseAuth.getInstance();
        }
        return firebaseAuth;
    }
}
