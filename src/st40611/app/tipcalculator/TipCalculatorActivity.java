package st40611.app.tipcalculator;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.view.KeyEvent;

public class TipCalculatorActivity extends Activity {

	double totalAmount;
	int billAmount;
	int percent;
	SeekBar seekbar;
	EditText bill;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);
        totalAmount = 0;
        billAmount = 0;
        bill = (EditText) findViewById(R.id.bill);
        seekbar = (SeekBar) findViewById(R.id.seekBar);
        this.setBillListener();
        this.setSeekBarListener();
        
    }
    
    private void setBillListener() {
    	bill.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
            	getTotal();
            	return false;
            }
        });  
    }
    
    private void setSeekBarListener() {
    	seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        	@Override
        	public void onProgressChanged (SeekBar seekBar, 
        										int progress, 
        										boolean fromUser) {
        		percent = progress;
        		TextView prog = (TextView) findViewById(R.id.seekbarProgress);
            	prog.setText(percent + "%");
        		getTotal();
        	}
        	
        	@Override
        	public void onStartTrackingTouch (SeekBar seekBar) {
        	}
        	@Override
        	public void onStopTrackingTouch (SeekBar seekBar) {
        	}
        	
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tip_calculator, menu);
        return true;
    }
    
    public void getTotal() {
    	if (bill.getText().toString().equals("")) {
    		return ;
    	}
    	billAmount = Integer.parseInt(bill.getText().toString());
    	double tax = (double) billAmount * percent / 100;
    	TextView total = (TextView) findViewById(R.id.total);
    	totalAmount = tax + billAmount;
    	total.setText("$" + totalAmount);
    }
    
    public void buttonClick(View v) {
    	percent = Integer.parseInt((String)v.getTag());
    	seekbar.setProgress(percent);
    	}
}
