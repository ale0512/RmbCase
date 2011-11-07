package org.ale.rmb;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RmbCase extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button buttonOk = (Button) findViewById(R.id.button_ok);
		Button buttonRst = (Button) findViewById(R.id.button_rst);
		//EditText et = (EditText) findViewById(R.id.EditText01);
		//et.setInputType(InputType.TYPE_CLASS_NUMBER);
		buttonOk.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				TextView tv = (TextView) findViewById(R.id.rmb);
				EditText et = (EditText) findViewById(R.id.EditText01);
				String rmb = (et.getText()).toString();
				if (rmb != null && rmb.trim().length() > 0) {
					TextView upAmout = (TextView) findViewById(R.id.uc_label);
					upAmout.setVisibility(View.VISIBLE);
					String chinese = Rmb.toBigAmt(rmb);
					tv.setText(chinese);
					InputMethodManager imm = ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE));
					imm.hideSoftInputFromWindow(RmbCase.this.getCurrentFocus()
							.getWindowToken(),
							InputMethodManager.HIDE_NOT_ALWAYS);
				}

			}
		});
		buttonRst.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				TextView tv = (TextView) findViewById(R.id.rmb);
				EditText et = (EditText) findViewById(R.id.EditText01);
				TextView upAmout = (TextView) findViewById(R.id.uc_label);
				upAmout.setVisibility(View.GONE);
				et.setText("");
				tv.setText("");

			}
		});
	}

	protected void dialog() {
		AlertDialog.Builder builder = new Builder(RmbCase.this);
		builder.setTitle(R.string.dialog_title);
		builder.setMessage(R.string.dialog_msg);
		builder.setPositiveButton(R.string.dialog_yes, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				RmbCase.this.finish();
				onDestroy();
			}

		});
		builder.setNegativeButton(R.string.dialog_no, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}

		});
		builder.create().show();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent evt) {
		if (keyCode == KeyEvent.KEYCODE_BACK && evt.getRepeatCount() == 0) {
			dialog();
			return false;
		}
		return false;
	}

	@Override
	protected void onDestroy() {

		super.onDestroy();

		System.exit(0);

	}
}