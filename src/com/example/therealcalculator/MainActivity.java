package com.example.therealcalculator;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener,
		OnDragListener {
	public static final String CHEATCODE = "737362";
	public InfixToPostfix equation;
	public String cheatCode;
	private Button zero, one, two, three, four, five, six, seven, eight, nine,
			mul, sub, div, add, clear, equals;
	public TextView result, in, post;
	private int number;
	int thisNum;
	public Operation operation;

	public enum Operation {
		ADD, SUBTRACT, DIVIDE, MULTIPLY;

	public static String getOp(Operation o) {
			switch (o) {
			case ADD:
				return "Add";
			case SUBTRACT:
				return "Subtract";
			case DIVIDE:
				return "Divide";
			case MULTIPLY:
				return "Multiply";
			default:
				return "nothing";
			}
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		equation = new InfixToPostfix();
		Log.d("Calc", "ApplicationStarted");
		LongClicker longClickListener = new LongClicker();
		
		cheatCode = "123321";
		number = 0;
		thisNum = 0;

		in = (TextView) findViewById(R.id.infix_box);
		post = (TextView) findViewById(R.id.postfix_box);

		zero = (Button) findViewById(R.id.zero);
		one = (Button) findViewById(R.id.one);
		two = (Button) findViewById(R.id.two);
		three = (Button) findViewById(R.id.three);
		four = (Button) findViewById(R.id.four);
		five = (Button) findViewById(R.id.five);
		six = (Button) findViewById(R.id.six);
		seven = (Button) findViewById(R.id.seven);
		eight = (Button) findViewById(R.id.eight);
		nine = (Button) findViewById(R.id.nine);
		clear = (Button) findViewById(R.id.clear);
		mul = (Button) findViewById(R.id.mul);
		sub = (Button) findViewById(R.id.sub);
		div = (Button) findViewById(R.id.div);
		add = (Button) findViewById(R.id.add);
		result = (TextView) findViewById(R.id.result);

		try {
			zero.setOnClickListener(this);
			one.setOnClickListener(this);
			two.setOnClickListener(this);
			three.setOnClickListener(this);
			four.setOnClickListener(this);
			five.setOnClickListener(this);
			six.setOnClickListener(this);
			seven.setOnClickListener(this);
			eight.setOnClickListener(this);
			nine.setOnClickListener(this);
			clear.setOnClickListener(this);
			mul.setOnClickListener(this);
			equals.setOnClickListener(this);
			div.setOnClickListener(this);
			sub.setOnClickListener(this);
			add.setOnClickListener(this);

		} catch (Exception e) {

		}

		result.setOnLongClickListener(longClickListener);
		result.setOnDragListener(this);
		findViewById(R.id.save_number1).setOnDragListener(this);
		findViewById(R.id.save_number2).setOnDragListener(this);
		findViewById(R.id.save_number3).setOnDragListener(this);
		findViewById(R.id.save_number1).setOnLongClickListener(longClickListener);
		findViewById(R.id.save_number2).setOnLongClickListener(longClickListener);
		findViewById(R.id.save_number3).setOnLongClickListener(longClickListener);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;

	}

	private void performOperation(int i, int j, Operation operation2) {
		switch (operation2) {
		case ADD:
			result.setText(Integer.toString(i + j));
			return;
		case SUBTRACT:
			result.setText(Integer.toString(i - j));
			return;
		case DIVIDE:
			result.setText(Float.toString((float) i / (float) j));
			return;
		case MULTIPLY:
			result.setText(Integer.toString(i * j));
			return;
		default:
			result.setText("Keep coding nigga!");
			return;
		}
	}
	
	private void updateTextBox(String arg){
		equation.push(arg);
		in.setText(equation.getInfixString());
		post.setText(equation.getPostfixString());
	}
	
	private void updateTextBox(){
		in.setText(equation.getInfixString());
		post.setText(equation.getPostfixString());
	}
	
	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.zero:
			System.out.println("zero was pressed");
			if (!result.getText().toString().equals("0"))
				result.setText(result.getText().toString() + "0");
			break;

		case R.id.one:
			if (result.getText().toString().equals("0"))
				result.setText("1");
			else
				result.setText(result.getText().toString() + "1");
			break;

		case R.id.two:
			if (result.getText().toString().equals("0"))
				result.setText("2");
			else
				result.setText(result.getText().toString() + "2");
			break;

		case R.id.three:
			if (result.getText().toString().equals("0"))
				result.setText("3");
			else
				result.setText(result.getText().toString() + "3");
			break;

		case R.id.four:
			if (result.getText().toString().equals("0"))
				result.setText("4");
			else
				result.setText(result.getText().toString() + "4");
			break;

		case R.id.five:
			if (result.getText().toString().equals("0"))
				result.setText("5");
			else
				result.setText(result.getText().toString() + "5");
			break;

		case R.id.six:
			if (result.getText().toString().equals("0"))
				result.setText("6");
			else
				result.setText(result.getText().toString() + "6");
			break;

		case R.id.seven:
			if (result.getText().toString().equals("0"))
				result.setText("7");
			else {
				result.setText(result.getText().toString() + "7");
			}
			break;

		case R.id.eight:
			if (result.getText().toString().equals("0"))
				result.setText("8");
			else
				result.setText(result.getText().toString() + "8");
			break;

		case R.id.nine:
			if (result.getText().toString().equals("0"))
				result.setText("9");
			else
				result.setText(result.getText().toString() + "9");
			break;

		case R.id.add:
			updateTextBox((String)result.getText());
			updateTextBox("+");
			operation = Operation.ADD;
			number = Integer.parseInt((String) result.getText());
			thisNum++;
			thisNum %= 2;
			result.setText("0");
			break;

		case R.id.sub:
			updateTextBox((String)result.getText());
			updateTextBox("-");
			operation = Operation.SUBTRACT;
			number = Integer.parseInt((String) result.getText());
			thisNum++;
			thisNum %= 2;
			result.setText("0");
			break;
			
		case R.id.mul:
			updateTextBox((String)result.getText());
			updateTextBox("x");
			operation = Operation.MULTIPLY;
			number = Integer.parseInt((String) result.getText());
			thisNum++;
			thisNum %= 2;
			result.setText("0");
			break;

		case R.id.div:
			updateTextBox((String)result.getText());
			updateTextBox("/");
			operation = Operation.DIVIDE;
			number = Integer.parseInt((String) result.getText());
			thisNum++;
			thisNum %= 2;
			result.setText("0");
			break;

		case R.id.clear:
			result.setText("0");
			in.setText("infix");
			post.setText("postfix");
			equation = new InfixToPostfix();
			
			break;

		case R.id.eq:
			updateTextBox((String)result.getText());
			equation.finalizePostfix();
			updateTextBox();
			int _number = Integer.parseInt((String) result.getText());
			performOperation(number, _number, operation);
			break;

		default:
			result.setText("idk what was pressed");
			break;
		}

		if (result.getText().toString().equals(cheatCode))
			result.setText("Heeeeeey Noslen!");
	}

	@Override
	public boolean onDrag(View layoutview, DragEvent event){
		int action = event.getAction();
		
		switch(action){
		case DragEvent.ACTION_DRAG_STARTED:
			Log.d("Calc", "Drag event started");
			break;
			
		case DragEvent.ACTION_DRAG_ENTERED:
			Log.d("Calc", "Drag event entered into " + layoutview.toString());
			break;
			
		case DragEvent.ACTION_DRAG_EXITED:
			Log.d("Calc", "Drag event exited from " + layoutview.toString());
			break;
			
		case DragEvent.ACTION_DROP:

			View parent = (View) layoutview.getParent();
			if(!(layoutview instanceof TextView)){
				Log.d("parent id", Integer.toString(parent.getId()));
				break;
			}
			
			else{
				TextView originator = (TextView) event.getLocalState();
				
				Log.d("inside action drop", "true");
				TextView textView = (TextView) layoutview;
				textView.setText((String) originator.getText());
				Log.d("result text", (String) originator.getText());
			}
			break;
			
		case DragEvent.ACTION_DRAG_ENDED:
			Log.d("Calc", "Drag ended");
			break;
			
		default:
				break;
		}
		return true;
	}

	public class LongClicker implements OnLongClickListener {

		@Override
		public boolean onLongClick(View v) {
			Log.d("Calc", "Long Clicked!");
			DragShadowBuilder shadowBuilder = new Shadow(v);
			v.startDrag(null, shadowBuilder, v, 0);
			//v.setVisibility(View.INVISIBLE);
			return true;
			
		}

	}
	
	public class Shadow extends DragShadowBuilder {
		
		ColorDrawable purpleBox;
		
		public Shadow(View v) {
			super(v);			
			purpleBox = new ColorDrawable(Color.MAGENTA);
		}

		@Override
		public void onDrawShadow(Canvas canvas) {
			purpleBox.draw(canvas);
		}

		@Override
		public void onProvideShadowMetrics(Point shadowSize, Point shadowTouchPoint) {
			
			int height = findViewById(R.id.save_number1).getHeight();
			int width = (int) (findViewById(R.id.save_number1).getWidth() * .8);
			
			purpleBox.setBounds(0, 0, height, width);
			shadowSize.set(width, height);
			shadowTouchPoint.set(0, 0);
		}

	}
}
