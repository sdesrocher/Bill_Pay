package css.cis3334.bill_pay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class SettingsActivity extends AppCompatActivity {

    Spinner spinRoommate;
    Button btnUpdate, btnReturn;
    //et Amount from DetailsActivity where receiving info
    EditText etAddAmount, etAmount, etPriceResult, etAmountPer;
    Double amount, amountper;

    /**
     *  Basic create and sets names for the widgets
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        //set connections with xml for settings
        spinRoommate = (Spinner) findViewById(R.id.spinnerRoommates);
        btnUpdate = (Button) findViewById(R.id.buttonUpdate);
        etPriceResult = (EditText) findViewById(R.id.editTextPriceResult);
        //etAmountPer = (EditText) findViewById(R.id.editTextAmountPer);

        Bundle extras = getIntent().getExtras();
        amount = extras.getDouble("Amount");

    }//end onCreate


        /**
         * Set up on click for spinner data after button clicked. Set the new price as etAmountPer
         * @param view - how the items are viewed
         */
        private void setupUpdateButton() {
        btnUpdate = (Button) findViewById(R.id.buttonUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                etAmount = (EditText) findViewById(R.id.editTextAmount);

                //if statement to read spinner results and make division of total price by number of roommates. Set as etPriceResult and pass that to etAmount to be sent back.
                if (spinRoommate.getSelectedItem().toString() == "1") {
                    amountper = amount;


                } else if (spinRoommate.getSelectedItem().toString() == "2") {
                    amountper = (amount / 2);

                } else if (spinRoommate.getSelectedItem().toString() == "3") {
                    amountper = (amount / 3);

                } else if (spinRoommate.getSelectedItem().toString() == "4") {
                    amountper = (amount / 4);

                } else if (spinRoommate.getSelectedItem().toString() == "5") {
                    amountper = (amount / 5);

                } else if (spinRoommate.getSelectedItem().toString() == "6") {
                    amountper = (amount / 6);

                }

            }
        });
            etAmountPer.setText("$" + amountper);
        }
        /**
         * Set up button to return to main page when clicked
         */
        private void setupReturnButton() {
            btnReturn = (Button) findViewById(R.id.buttonReturnSet);
            btnReturn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent mainActIntent = new Intent(view.getContext(), MainActivity.class);
                    finish();
                    startActivity(mainActIntent);
                }
            });
        }




    @Override
    public void finish() {
        Intent intent = new Intent();
        intent.putExtra("Amount Per Roommate", amountper);
        setResult(RESULT_OK, intent);
        super.finish();
    }
}//end main

