package com.example.jyothymariamiranda.calculatorapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String expr ="";






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = (EditText)findViewById(R.id.Result);
        editText.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.onTouchEvent(event);
                InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
                return true;
            }
        });


        Button btn1 = (Button)findViewById(R.id.one);
        Button btn2 = (Button)findViewById(R.id.two);
        Button btn3 = (Button)findViewById(R.id.three);
        Button btn4 = (Button)findViewById(R.id.four);
        Button btn5 = (Button)findViewById(R.id.five);
        Button btn6 = (Button)findViewById(R.id.six);
        Button btn7 = (Button)findViewById(R.id.seven);
        Button btn8 = (Button)findViewById(R.id.eight);
        Button btn9 = (Button)findViewById(R.id.nien);
        Button btn0 = (Button)findViewById(R.id.zero);

        Button add = (Button)findViewById(R.id.Add);
        Button sub = (Button)findViewById(R.id.Sub);
        Button div = (Button)findViewById(R.id.Div);
        Button mul = (Button)findViewById(R.id.Mul);

        Button point = (Button)findViewById(R.id.Point);
        Button eq = (Button)findViewById(R.id.Eq);

        Button clr = (Button)findViewById(R.id.clear);

        Button rp = (Button)findViewById(R.id.rightP);
        Button lp = (Button)findViewById(R.id.leftP);
        Button sqrt = (Button)findViewById(R.id.sqrt);
        Button inr = (Button)findViewById(R.id.inr);

        Button bck = (Button)findViewById(R.id.bckspc);



        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn0.setOnClickListener(this);

        add.setOnClickListener(this);
        sub.setOnClickListener(this);
        mul.setOnClickListener(this);
        div.setOnClickListener(this);

        clr.setOnClickListener(this);
        point.setOnClickListener(this);
        eq.setOnClickListener(this);

        rp.setOnClickListener(this);
        lp.setOnClickListener(this);
        sqrt.setOnClickListener(this);
        inr.setOnClickListener(this);

        bck.setOnClickListener(this);

    }


    public static String appendAt(String expr, int pos, String c)
    {
       return expr.substring(0,pos).concat(c).concat(expr.substring(pos,expr.length()));
    }

    public static String backSpace(String expr, int noOfChars, int pos)
    {
        return expr.substring(0,pos).concat(expr.substring(pos+1,expr.length()));
    }

    @Override
    public void onClick(View view)
    {
        EditText tv = (EditText)findViewById(R.id.Result);
        int pos;
        if(expr.length()>18)
            return;

        switch(view.getId())
        {
            case R.id.one: expr = appendAt(expr,tv.getSelectionStart(),"1");
                break;

            case R.id.two:  expr = appendAt(expr,tv.getSelectionStart(),"2");
                break;

            case R.id.three:  expr = appendAt(expr,tv.getSelectionStart(),"3");
                break;

            case R.id.four:  expr = appendAt(expr,tv.getSelectionStart(),"4");
                break;

            case R.id.five:  expr = appendAt(expr,tv.getSelectionStart(),"5");
                break;

            case R.id.six:  expr = appendAt(expr,tv.getSelectionStart(),"6");
                break;

            case R.id.seven:  expr = appendAt(expr,tv.getSelectionStart(),"7");
                break;

            case R.id.eight:  expr = appendAt(expr,tv.getSelectionStart(),"8");
                break;

            case R.id.nien:  expr = appendAt(expr,tv.getSelectionStart(),"9");
                break;

            case R.id.zero:  expr = appendAt(expr,tv.getSelectionStart(),"0");
                break;

            case R.id.Add: expr = appendAt(expr,tv.getSelectionStart(),"+");
                break;

            case R.id.Sub: expr = appendAt(expr,tv.getSelectionStart(),"-");
                break;

            case R.id.Mul: expr = appendAt(expr,tv.getSelectionStart(),"*");
                break;

            case R.id.Div: expr = appendAt(expr,tv.getSelectionStart(),"/");
                break;

            case R.id.clear: expr="";
                tv.setText(expr, TextView.BufferType.EDITABLE);
                tv.setSelection(0);
                return;

            case R.id.Point: expr = appendAt(expr,tv.getSelectionStart(),".");
                break;

            case R.id.leftP: expr = appendAt(expr, tv.getSelectionStart(),"(");
                    break;

            case R.id.rightP: expr = appendAt(expr, tv.getSelectionStart(),")");
                break;

            case R.id.inr: expr = appendAt(expr, tv.getSelectionStart(),"^-1");
                pos = tv.getSelectionStart();
                tv.setText(expr, TextView.BufferType.EDITABLE);
                tv.setSelection(pos+3);
                return;


            case R.id.sqrt: expr = appendAt(expr, tv.getSelectionStart(),"sqrt(");
                pos = tv.getSelectionStart();
                tv.setText(expr, TextView.BufferType.EDITABLE);
                tv.setSelection(pos+5);
                return;


            case R.id.Eq:
            {
                try {
                    if (expr == "")
                        tv.setText("", TextView.BufferType.EDITABLE);
                    else {

                        MathEval me = new MathEval();
                        double ans = me.evaluate(expr);
                        expr = String.valueOf(ans);
                        tv.setText(expr, TextView.BufferType.EDITABLE);
                        tv.setSelection(expr.length());
                        return;
                    }
                }
                catch(Exception e)
                    {
                        Toast.makeText(this, "Invalid Expression", Toast.LENGTH_SHORT).show();
                    }
                }


            case R.id.bckspc :
            {
                if(expr=="")
                    return;
                if(expr.length()==1)
                {
                    expr=" ";
                    tv.setText(expr, TextView.BufferType.EDITABLE);
                }
                //if(expr.charAt(tv.getSelectionStart())=='(' && expr.charAt(tv.getSelectionStart()-1)=='t')
                if(tv.getSelectionStart()==expr.length())
                {
                    expr = expr.substring(0, expr.length() - 1);
                    tv.setText(expr, TextView.BufferType.EDITABLE);
                    tv.setSelection(expr.length());
                }

                else
                {
                    int tempPos = tv.getSelectionStart();
                        if(tempPos==0)
                            return;
                        else
                            {
                            expr = expr.substring(0, tempPos-1).concat(expr.substring(tempPos, expr.length()));
                            tv.setText(expr, TextView.BufferType.EDITABLE);
                            tv.setSelection(tempPos-1);
                            }
                }

                return;

            }}


        pos = tv.getSelectionStart();
        tv.setText(expr, TextView.BufferType.EDITABLE);
        tv.setSelection(pos+1);


    }
}
