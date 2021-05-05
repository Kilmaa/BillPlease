package sg.edu.rp.c346.id20022404.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    EditText amtInput;
    EditText numInput;
    EditText discount;
    TextView bill;
    TextView pays;
    ToggleButton SVS;
    ToggleButton GST;
    Button split;
    Button reset;
    RadioGroup payMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amtInput = findViewById(R.id.amtValue);
        numInput = findViewById(R.id.numValue);
        discount = findViewById(R.id.discountValue);
        SVS = findViewById(R.id.SVS);
        GST = findViewById(R.id.GST);
        split = findViewById(R.id.split);
        reset = findViewById(R.id.reset);
        bill = findViewById(R.id.totalBill);
        pays = findViewById(R.id.eachPays);
        payMethod = findViewById(R.id.paymentMethod);

        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double dAmt = (Double.parseDouble(discount.getText().toString()))/100;
                double payment = Double.parseDouble(amtInput.getText().toString());
                double num = Double.parseDouble(numInput.getText().toString());
                String dPay = "Each pays: $";

                if(GST.isChecked()) {
                    if(SVS.isChecked()) {
                        payment = (payment*(1.17))*(1-dAmt);
                        double each = payment/num;
                        dPay = dPay + each;
                    } else {
                        payment = (payment*(1.07))*(1-dAmt);
                        double each = payment/num;
                        dPay = dPay + each;
                    }
                } else {
                    payment = (Double.parseDouble(amtInput.getText().toString()))*(1-dAmt);
                    double each = payment/num;
                    dPay = dPay + each;
                }

                String dBill = "Total bill: $" + payment;
                bill.setText(dBill);

                if(payMethod.getCheckedRadioButtonId() == R.id.cash){
                    dPay = dPay + " in cash";
                } else {
                    dPay = dPay + " via PayNow to 912345678";
                }
                pays.setText(dPay);

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String blank = "";
                amtInput.setText(blank);
                numInput.setText(blank);
                discount.setText(blank);
                SVS.setChecked(false);
                GST.setChecked(false);
                bill.setText(blank);
                pays.setText(blank);
                payMethod.check(R.id.cash);
            }
        });
    }
}