package io.secon.kadai03_st21sihb_99;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;


public class MainActivity extends ActionBarActivity {
	
	private TextView disp;
	private double num;
    private String calcState;
    private boolean calcFlg;
    private boolean equalFlg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button sumBtn, diffBtn, productBtn, quoteBtn, equalBtn, clearBtn, periodBtn,backBtn;

        Button[] numBtn = new Button[10];

        // num buttons
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

        // calcButtons
        sumBtn = (Button)findViewById(R.id.sumBtn);
        diffBtn = (Button)findViewById(R.id.diffBtn);
        productBtn = (Button)findViewById(R.id.productBtn);
        quoteBtn = (Button)findViewById(R.id.quoteBtn);
        equalBtn = (Button)findViewById(R.id.equalBtn);

        // decimal
        periodBtn = (Button)findViewById(R.id.periodBtn);

        clearBtn = (Button)findViewById(R.id.clearBtn);
        backBtn = (Button)findViewById(R.id.backBtn);
        disp = (TextView)findViewById(R.id.disp);

        // initialize
        clearDisp();	 // set 0 in display
        calcState = "+"; // first operate is 0 + inputnum
        calcFlg = false;

        // set listner on num buttons
        for(int i=0; i<numBtn.length; i++){
        	numBtn[i].setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					numBtnClicked(v);
				}
			});
        }

        // clear btn listner
        clearBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				initialize();
			}
		});

        // operat buttons listner
        // +
        sumBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				calcBtnClicked(v);
			}
		});

        // -
        diffBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                calcBtnClicked(v);
            }
        });

        // *
        productBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                calcBtnClicked(v);
            }
        });

        // /
        quoteBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                calcBtnClicked(v);
            }
        });

        // =
        equalBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				calcExec();
			}
		});

        // .
        periodBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                periodBtnClicked(v);
            }
        });
        
        // <-
        backBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				backDisp();
				if(disp.getText().length() == 0){
					clearDisp();
				}
			}
		});
    }

    // backspace
    private void backDisp() {
        disp.setText(disp.getText().toString().substring(0, disp.getText().length()-1));
    }
    
    // num button
    private void numBtnClicked(View v) {
    	Button numBtn = (Button)v;

    	// delete 0
        if(disp.getText().toString().equals("0")) {
            backDisp();
        }

        // clear display
        if(calcFlg) {
            clearDisp();
            backDisp();
        }

        // initialize
        if(equalFlg) {
            initialize();
            backDisp();
        }

    	disp.setText(disp.getText().toString() + numBtn.getText().toString());
        calcFlg = false;
    }

    // calculate btn clicked
    private void calcBtnClicked(View v) {
        Button calcBtn = (Button)v;
        
        // should not operate when pressed consecutively
        if(!calcFlg){
        	calc();
        }
        	
        num = Double.parseDouble(disp.getText().toString());
        calcState = calcBtn.getText().toString();
        calcFlg = true; // controle consecutive operator
        equalFlg = false;
    }

    // calculate
    private void calc() {
        BigDecimal big1 = BigDecimal.valueOf(num);
        BigDecimal big2 = new BigDecimal(disp.getText().toString());
    	
    	//check operator
        if(calcState.equals("+")) {
            num = big1.add(big2).doubleValue();
        }else if(calcState.equals("-")) {
            num = big1.subtract(big2).doubleValue();
        }else if(calcState.equals("×")) {
            num = big1.multiply(big2).doubleValue();
        }else if(calcState.equals("÷")) {
            num = big1.divide(big2, 10, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
        format();
    }
    
    // execute
    private void calcExec() {
    	calc();
        equalFlg = true;
        calcState = "";
    }
    
    // format
    private void format() {
    	if(num % 1 == 0) {
    		disp.setText(String.valueOf((int) num));
    	}else{
    		disp.setText(String.valueOf(num));
    	}
    }

    // set period
    private void periodBtnClicked(View v) {
        disp.setText(disp.getText().toString() + ".");
    }


    // display initialize
    private void clearDisp() {
    	disp.setText("0");
    }
    
    // status initialize
    private void initialize() {
    	clearDisp();
    	num = 0;
    	calcState = "+";
        equalFlg = false;
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
