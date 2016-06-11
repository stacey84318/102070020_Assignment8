package com.csclab.hc.socketsample;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.CursorJoiner;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by chanhua on 16/6/1.
 */
public class ResultActivity extends Activity {
    TextView ans;
    Button re,rec;
    float result = MainActivity.result;
    float n1 = MainActivity.n1;
    float n2 = MainActivity.n2;
    String op = MainActivity.oper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_page);
        ans = (TextView) this.findViewById(R.id.answer);
        re = (Button) this.findViewById(R.id.re);
        rec = (Button)this.findViewById(R.id.button);
        ans.setText(n1 + op + n2 + " = " + result);
        MainActivity.writer.println(ans.getText());
        MainActivity.writer.flush();
        re.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.n1 = 0;
                MainActivity.n2 = 0;
                MainActivity.result = 0;
                MainActivity.oper = "";
                MainActivity.r=true;
                Intent intent = new Intent();
                intent.setClass(ResultActivity.this, MainActivity.class);
                ResultActivity.this.startActivity(intent);

            }
        });
        rec.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.n1 = 0;
                MainActivity.n2 = 0;
                MainActivity.result = 0;
                MainActivity.oper = "";
                MainActivity.r=false;
                Intent intent = new Intent();
                intent.setClass(ResultActivity.this, MainActivity.class);
                ResultActivity.this.startActivity(intent);
            }
        });

        }



}

