package css.cis3334.bill_pay;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sdesrocher on 4/26/2017.
 * Class will take input from Bill class and adapt it for the list shown on the main page.
 */

public class BillAdapter extends ArrayAdapter<Bill> {

    private List<Bill> billList;  //list of bills to display
    private Context context;    //original activity that displays it
    private int layoutResource; //layout used

    /**
     * Basic constructor for BillAdapter
     * @param context - Activity calling this
     * @param resource - layout used in display
     * @param billList - list of the current bills
     */
    public BillAdapter(Context context, int resource, List<Bill> billList){
        super(context, resource, billList);
        this.context = context;
        this.layoutResource = resource;
        this.billList = billList;

    }

    /**
     * set up the view for the list of bills
     * @param position - gets position from Bill class
     * @param convertView - get view from Name, Duedate and amount per and set
     * @param parent - how to view it
     * @return - return the row with name, duedate and amountper
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //get bill displaying
        Bill bill = billList.get(position);
        View view;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.bill_row_layout, null);

        TextView tvName = (TextView)view.findViewById(R.id.textViewName);
        TextView tvDueDate = (TextView) view.findViewById(R.id.textViewDueDate);
        TextView tvAmountPer = (TextView) view.findViewById(R.id.textViewAmountPer);

        tvName.setText(bill.getName());
        tvDueDate.setText(bill.getDueDate());
        tvAmountPer.setText(bill.getAmountPer());


        return(view);
    }

}
