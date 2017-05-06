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
    EditText etAmount, etPriceResult, etAmountPer;

    /**
     *  Basic create and sets names for the widgets
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);



        spinRoommate = (Spinner) findViewById(R.id.spinnerRoommates);
        btnUpdate = (Button) findViewById(R.id.buttonUpdate);
        etPriceResult = (EditText) findViewById(R.id.editTextPriceResult);
        //etAmountPer = (EditText) findViewById(R.id.editTextAmountPer);



        /**
         * Set up on click for spinner data after button clicked. Set the new price as etAmountPer
         * @param view - how the items are viewed
         */
        btnUpdate = (Button) findViewById(R.id.buttonUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                //get info from Bill class
                Bundle bundle = getIntent().getExtras();
                Bill bill = (Bill) bundle.getSerializable("Bill");
                etAmount = (EditText) findViewById(R.id.editTextAmount);


                // int roommates = Integer.parseInt(spinRoommate.getSelectedItem().toString());

                //if statement to read spinner results and make division of total price by number of roommates. Set as etPriceResult and pass that to etAmount to be sent back.
                if (spinRoommate.getSelectedItem().toString() == "1") {
                    etPriceResult.setText(bill.getAmount);


                } else if (spinRoommate.getSelectedItem().toString() == "2") {
                    etPriceResult = (bill.getAmount / 2);

                } else if (spinRoommate.getSelectedItem().toString() == "3") {
                    etPriceResult = (bill.getAmount / 3);

                } else if (spinRoommate.getSelectedItem().toString() == "4") {
                    etPriceResult = (bill.getAmount / 4);

                } else if (spinRoommate.getSelectedItem().toString() == "5") {
                    etPriceResult = (bill.getAmount / 5);

                } else if (spinRoommate.getSelectedItem().toString() == "6") {
                    etPriceResult = (bill.getAmount / 6);

                }


                //set the result to etAmount Result to be passed back
                etAmountPer = etPriceResult;


            }
        });

        /**
         * Set up button to return to main page when clicked
         */
        btnReturn = (Button) findViewById(R.id.buttonReturnSet);
        btnReturn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent mainActIntent = new Intent(view.getContext(), MainActivity.class);
                finish();
                startActivity(mainActIntent);
            }
        });



    }//end onCreate
}//end main

