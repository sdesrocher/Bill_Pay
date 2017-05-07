package css.cis3334.bill_pay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import static android.R.attr.data;

/**
 * Created by sdesrocher on 4/26/2017.
 * Page displayed when new bill is to be added into firebase
 */

public class AddActivity extends AppCompatActivity {

    EditText etName, etDueDate, etAmountPer, etGetName, etGetDueDate, etGetAmount;
    Button btnSave, btnReturn;
    BillFirebaseData billDataSource;

    private static final int REQUEST_CODE = 1001;

    /**
     * Basic onCreate format. Connects the widgets for getting the strings and then converts them to etName, etDueDate and etAmount.
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

        //link variable to display number at bottom of page
        etAmountPer = (EditText) findViewById(R.id.editTextAmountPer);

        //connect with FireBase
        billDataSource = new BillFirebaseData();
        DatabaseReference myBillDbRef = billDataSource.open(this);

        setupReturnButton();
        setupSaveButton();


    }//end create
        //set onclick for the save button after info entered

    public void setupSaveButton() {
        btnSave = (Button) findViewById(R.id.buttonSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //String getname = etGetName.getText().toString();

                //set name, duedate and amountper to be passes to DataSource and create bill.
                String name = etName.getText().toString();
                String duedate = etDueDate.getText().toString();
                //retrieve math from settings activity for amount per roommate.
                String amountper = etAmountPer.getText().toString();

                billDataSource.createBill(name, duedate, amountper);


            }
        });
    }



    public void onButtonClick(View view) {
        //call settings activity
        Double amount = Double.parseDouble(etGetAmount.getText().toString());
        Intent secActIntent = new Intent(this, SettingsActivity.class);
        secActIntent.putExtra("Amount", amount);

        startActivityForResult(secActIntent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            if (data.hasExtra("Amount Per Roommate")) {
                String result = data.getExtras().getString("Amount Per Roommate");
                if (result != null && result.length() > 0) {
                    etAmountPer.setText(result);
                    Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
                }
            }
        }
        etAmountPer.setText(etAmountPer.getText().toString());
    }




    //return to main screen
    private void setupReturnButton() {
        btnReturn = (Button) findViewById(R.id.buttonReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                //return to main activity when button click
                Intent mainActIntent = new Intent(view.getContext(), MainActivity.class);
                finish();
                startActivity(mainActIntent);

                Toast.makeText(getApplicationContext(), "Bill added, click on bill and hit 'Details' button to view all information.", Toast.LENGTH_LONG)
                        .show();

            }
        });


    }}

