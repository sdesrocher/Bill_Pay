package css.cis3334.bill_pay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import static android.R.attr.data;

/**
 * Created by sdesrocher on 4/26/2017.
 * Page displayed when new bill is to be added into firebase.
 */

public class AddActivity extends AppCompatActivity {

    EditText   etAmountPer, etGetName, etGetDueDate, etGetAmount;
    Button btnSave, btnReturn;
    BillFirebaseData billDataSource;
    Spinner spnPeople;
    int people = 1;


    /**
     * Basic onCreate format. Connects the widgets for getting the strings and int for amount. Receives selection from spinner.
     * Does math dividing bill amount by number of people selected in spinner. Sets up button to save information into database and do math.
     * Sets up return button to return to main screen when clicked.
     *
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
        spnPeople = (Spinner) findViewById(R.id.spinnerRoommates);
        //variable to display number at bottom of page
        etAmountPer = (EditText) findViewById(R.id.editTextAmountPer);

        //connect with FireBase
        billDataSource = new BillFirebaseData();
        DatabaseReference myBillDbRef = billDataSource.open(this);


        //set button listener for the save button after info entered.
        btnSave = (Button) findViewById(R.id.buttonSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //add bills to database
                String name = etGetName.getText().toString();
                String duedate = etGetDueDate.getText().toString();
                int amount = Integer.parseInt(etGetAmount.getText().toString());
                int people;


                //if statement to read spinner results and make division of total price by number of roommates. Set as etPriceResult and pass that to etAmount to be sent back.
                if (spnPeople.getSelectedItem().toString() == "1") {

                    people = 1;

                } else if (spnPeople.getSelectedItem().toString() == "2") {
                    people = 2;

                } else if (spnPeople.getSelectedItem().toString() == "3") {
                    people = 3;

                } else if (spnPeople.getSelectedItem().toString() == "4") {
                    people = 4;

                } else if (spnPeople.getSelectedItem().toString() == "5") {
                    people = 5;

                } else if (spnPeople.getSelectedItem().toString() == "6") {
                    people = 6;

                }

                int amountperroom = (amount / people);
                String amountper = Integer.toString(amountperroom);

                etAmountPer.setText(amountper.toString());

                billDataSource.createBill(name, duedate, amountper);

            }
        });


        //return to main screen on click
        btnReturn = (Button) findViewById(R.id.buttonReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //return to main
                Intent mainActIntent = new Intent(view.getContext(), MainActivity.class);
                finish();
                startActivity(mainActIntent);

                Toast.makeText(getApplicationContext(), "Bill added, click on bill and hit 'Details' button to view all information.", Toast.LENGTH_LONG)
                        .show();

            }
        });



}
}

