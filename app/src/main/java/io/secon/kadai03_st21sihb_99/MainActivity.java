package io.secon.kadai03_st21sihb_99;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
	
	private TextView disp;
	private double num;
    private String calcState;
    private boolean calcFlg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button sumBtn, diffBtn, productBtn, quoteBtn, equalBtn, clearBtn, periodBtn;

        Button[] numBtn = new Button[10];

        numBtn[0] = (Button)findViewById(R.id.num0);
        numBtn[1] = (Button)findViewById(R.id.num1);
        numBtn[2] = (Button)findViewById(R.id.num2);
        numBtn[3] = (Button)findViewById(R.id.num3);
        numBtn[4] = (Button)findViewById(R.id.num4);
        numBtn[5] = (Button)findViewById(R.id.num5);
        numBtn[6] = (Button)findViewById(R.id.num6);
        numBtn[7] = (Button)findViewById(R.id.num7);
        numBtn[8] = (Button)findViewById(R.id.num8);
        numBtn[9] = (Button)findViewById(R.id.num9);

        sumBtn = (Button)findViewById(R.id.sumBtn);
        diffBtn = (Button)findViewById(R.id.diffBtn);
        productBtn = (Button)findViewById(R.id.productBtn);
        quoteBtn = (Button)findViewById(R.id.quoteBtn);
        equalBtn = (Button)findViewById(R.id.equalBtn);
        periodBtn = (Button)findViewById(R.id.periodBtn);

        clearBtn = (Button)findViewById(R.id.clearBtn);
        disp = (TextView)findViewById(R.id.disp);

        clearDisp();
        calcState = "";
        calcFlg = false;

        for(int i=0; i<numBtn.length; i++){
        	numBtn[i].setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					numBtnClicked(v);
				}
			});
        }

        clearBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				clearDisp();
			}
		});

        sumBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				calcBtnClicked(v);
			}
		});

        diffBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                calcBtnClicked(v);
            }
        });

        productBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                calcBtnClicked(v);
            }
        });

        quoteBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                calcBtnClicked(v);
            }
        });

        equalBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				calcExec();
			}
		});

        periodBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                periodBtnClicked(v);
            }
        });
    }

    private void backDisp() {
        disp.setText(disp.getText().toString().substring(0, disp.getText().length()-1));
    }
    
    private void numBtnClicked(View v) {
    	Button numBtn = (Button)v;

        if(disp.getText().toString() == "0") {
            backDisp();
        }

        if(calcFlg == true) {
            clearDisp();
            backDisp();
        }

    	disp.setText(disp.getText().toString() + numBtn.getText().toString());
        calcFlg = false;
    }

    private void calcBtnClicked(View v) {
        Button calcBtn = (Button)v;
        calcState = calcBtn.getText().toString();
        if(!calcFlg) {
            num = Double.parseDouble(disp.getText().toString());
        }else{
            backDisp();
        }
        disp.setText(disp.getText().toString() + calcBtn.getText().toString());
        calcFlg = true;
    }

    private void periodBtnClicked(View v) {
        disp.setText(disp.getText().toString() + ".");
    }

    private void calcExec() {
        if(calcState.equals("+")) {
            num += Double.parseDouble(disp.getText().toString());
        }else if(calcState.equals("-")) {
            num -= Double.parseDouble(disp.getText().toString());
        }else if(calcState.equals("×")) {
            num *= Double.parseDouble(disp.getText().toString());
        }else if(calcState.equals("÷")) {
            num /= Double.parseDouble(disp.getText().toString());
        }
        if(num % 1 == 0) {
            disp.setText(String.valueOf((int) num));
        }else{
            disp.setText(String.valueOf(num));
        }
    }

    private void clearDisp() {
    	disp.setText("0");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
