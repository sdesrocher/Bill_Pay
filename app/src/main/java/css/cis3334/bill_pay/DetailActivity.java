package css.cis3334.bill_pay;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static css.cis3334.bill_pay.R.string.details;

/**
 * Created by sdesrocher on 4/26/2017.
 * Shows the list of details first entered in when new bill added. Contains button to return to main activity.
 */

public class DetailActivity extends AppCompatActivity{

    Button btnReturn;
    EditText etName, etDueDate, etAmount, etAmountPer;


    /**
     * Basic create to set widgets. Then set intent when button clicked to return to main activity. It gets the information from Bill class.
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundle = getIntent().getExtras();
        Bill bill = (Bill) bundle.getSerializable("Bill");

        //link variables to xml
        btnReturn = (Button) findViewById(R.id.buttonReturnList);
        etName = (EditText) findViewById(R.id.editTextBillName);
        etDueDate = (EditText) findViewById(R.id.editTextDueDate);
        etAmount = (EditText) findViewById(R.id.editTextAmount);
        etAmountPer = (EditText) findViewById(R.id.editTextAmountPer);

        //take entry from each editText
        etName.setText(bill.getName());
        etDueDate.setText(bill.getDueDate());
        etAmountPer.setText(bill.getAmountPer());

        //set button intent to return to main page on button click
        btnReturn.setOnClickListener(new View.OnClickListener(){
            public void onClick (View view){
                Intent mainActIntent = new Intent(view.getContext(), MainActivity.class);
                finish();
                startActivity(mainActIntent);
            }
        });
    }


    }//end main
