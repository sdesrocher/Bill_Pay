package css.cis3334.bill_pay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sdesrocher on 4/26/2017.
 * Creates database and connects throughout app activities
 */

public class BillFirebaseData {
    DatabaseReference myBillDbRef;
    //adding authentication for user to get their own bill list
    FirebaseAuth firebaseAuth;
    public static final String BillDataTag = "Bill User Data";
    private String userId;  //Firebase auth ID for current logged in user

    public DatabaseReference open(AppCompatActivity mainActivity){
        //message to database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myBillDbRef = database.getReference(BillDataTag);
        //set user id
        userId = getUserId(mainActivity);
        return myBillDbRef;
    }

    /**
     * Close the database
     */
    public void close(){
    }

    /**
     * retrieve the user id for authentication so the user can only see their own bills
     * @param activity - use the login activity
     * @return return the user id or nothing
     */
    private String getUserId(AppCompatActivity activity){
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user == null){
            //user is signed out
            Log.d("CSS3334", "onAuthStateChanged - User is not signed in");
            Intent signInIntent = new Intent (activity, LoginActivity.class);
            activity.startActivity(signInIntent);

        } else{
            return user.getUid();
        }
        return null;
    }

    /**
     * Creating a new bill with the user id
     * @param name -name of bill
     * @param duedate -date bill due
     * @param amountper -price of the bill per roommate
     * @return the bill created
     */
    public Bill createBill(String name, String duedate, String amountper){
                    //get new database key
        String key = myBillDbRef.child(BillDataTag).push().getKey();
                     //set up object
        Bill newBill = new Bill(key, name, duedate, amountper);
                 //write vote to Firebase
        myBillDbRef.child("users").child(userId).child(key).setValue(newBill);
        return newBill;

    }

    /**
     * Deleting a bill when it is paid from the use account
     * @param bill - the bill being deleted
     */
    public void deleteBill (Bill bill){
        String key = bill.getKey();
        myBillDbRef.child("users").child(userId).child(key).removeValue();
    }

    /**
     * get the list of bill for that user
     * @param dataSnapshot -retrieve data
     * @return - return list
     */
    public List<Bill> getAllBill(DataSnapshot dataSnapshot){
        List<Bill> billList = new ArrayList<Bill>();
        //only loop those bills tied to user id

        for (DataSnapshot data : dataSnapshot.child("users").child(userId).getChildren()){
            Log.d("CIS3334", "=== getAllBills === "+data.toString());
            Bill bill = data.getValue(Bill.class);
            billList.add(bill);
        }
        return billList;
    }
}

