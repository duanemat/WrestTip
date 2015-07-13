package matt.com.wresttip;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements Button.OnClickListener {

    private TextView mTextView;
    private String bill_string = "";
    private Double bill_value = 0.00;
    private WatchViewStub stub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.bill);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        /*Button btn = new Button(this.getApplicationContext());
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) btn.getLayoutParams();
        if (lp == null){
            lp = new LinearLayout.LayoutParams(25, 25);
        }else {
            lp.width = 25;
            lp.height = 25;
        }
        lp.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
        btn.setLayoutParams(lp);
        btn.setBackgroundResource(R.drawable.round_button);
        btn.setText("2");
        btn.setTextColor(getResources().getColor(android.R.color.white));

        LinearLayout ll = (LinearLayout) this.findViewById(R.id.wear_layout);
        ll.addView(btn);*/
    }

    @Override
    public void onClick(View view) {
        Button btn = (Button)view;
        try{
            if (btn.getId() == R.id.btnClear){
                bill_string = "";
            }
            else if (btn.getId() == R.id.btnBcksp){
                bill_string = bill_string.substring(0, bill_string.length()-1);
            }
            else {
                bill_string += (String) btn.getText();
            }
            mTextView.setText(formatCurrencyString(bill_string));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @param str - String to format
     * @return string formatted to '$x.yz'
     */
    private String formatCurrencyString(String str){
        Double val;
        try {
            if (str.length() == 0)
                str = "0.00";
            val = Double.valueOf(str);
            str = String.format("%.2f", val/100);
        }catch(Exception e){
            e.printStackTrace();
            return "0.00";
        }
        return str;
    }
}
