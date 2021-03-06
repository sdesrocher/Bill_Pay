package css.cis3334.bill_pay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
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

    EditText   etAmountPer, etGetName, etGetDueDate, etGetAmount, etPeople;
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
        etPeople = (EditText) findViewById(R.id.editTextPeople);
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

                //get int from amount to be divided by number of people entered
               int  amount = Integer.parseInt(etGetAmount.getText().toString());
                people = Integer.parseInt(etPeople.getText().toString());

                int amountperroom = (amount / people);

                String amountper = Integer.toString(amountperroom);

                //return result at bottom of page
                etAmountPer.setText("$" +  amountper.toString());




                billDataSource.createBill(name, duedate, "$" + amountper);
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

