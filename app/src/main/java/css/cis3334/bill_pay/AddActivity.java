package css.cis3334.bill_pay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

/**
 * Created by sdesrocher on 4/26/2017.
 * Page displayed when new bill is to be added into firebase
 */

public class AddActivity extends AppCompatActivity {

    EditText etName, etDueDate, etAmountPer, etGetName, etGetDueDate, etGetAmount;
    Button btnSave, btnReturn;
    BillFirebaseData billDataSource;


    /**
     * Basic onCreate format. Connects the widgets for getting the strings and then converts them to etName, etDueDate and etAmount. Working asynch with settings to get the amount due per person.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //link each variable to xml layout from numbers entered
        etGetName = (EditText) findViewById(R.id.editTextGetName);
        etGetDueDate = (EditText) findViewById(R.id.editTextGetDueDate);
        etGetAmount = (EditText) findViewById(R.id.editTextGetAmount);

        //link variables to display numbers at bottom of page
        etName = (EditText) findViewById(R.id.editTextBillName);
        etDueDate = (EditText) findViewById(R.id.editTextDueDate);
        etAmountPer = (EditText) findViewById(R.id.editTextAmountPer);

        //connect with FireBase
        billDataSource = new BillFirebaseData();
        DatabaseReference myBillDbRef = billDataSource.open(this);


        //set onclick for the save button after info entered
        btnSave = (Button) findViewById(R.id.buttonSave);
        btnSave.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                //String getname = etGetName.getText().toString();


                //link variables to display numbers
                etName = (EditText) findViewById(R.id.editTextBillName);
                etDueDate = (EditText) findViewById(R.id.editTextDueDate);
                etAmountPer = (EditText) findViewById(R.id.editTextAmountPer);


                //setting strings from Bill in AddActivity
                etName.setText(etGetName.getText().toString());
                etDueDate.setText(etGetDueDate.getText().toString());
                etAmountPer.setText(etAmountPer.getText().toString());

                //set name, duedate and amount to be passes to DataSource and create bill.
                String name = etName.getText().toString();
                String duedate = etDueDate.getText().toString();
                //retrieve math from settings activity for amount per roommate.

                String amountper = etAmountPer.getText().toString();

                billDataSource.createBill(name, duedate, amountper);
                //set intent to send info entered to main activity screen
                Intent mainActIntent = new Intent(view.getContext(), MainActivity.class);
                finish();
                startActivity(mainActIntent);


            }

        });

        btnReturn = (Button) findViewById(R.id.buttonReturn);
        btnReturn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                //return to main activity when button click
                Intent mainActIntent = new Intent(view.getContext(), MainActivity.class);
                finish();
                startActivity(mainActIntent);

                Toast.makeText(getApplicationContext(), "Bill added, click on bill and hit 'Details' button to view all information.", Toast.LENGTH_LONG)
                        .show();

            }
        });

    }
}
