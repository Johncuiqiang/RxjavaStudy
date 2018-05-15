package cuiqiang.com.rxjavademo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import io.reactivex.Observer;

public class MainActivity extends AppCompatActivity {

	private Button mBtnTest;
	private Button mBtnScheduler;
	private Button mBtnMap;
	private BaseTest mBaseTest;
	private SchedulerTest mSchedulerTest;
	private OperatorTest mOperatorTest;
	private Button mBtnConcat;
	private Button mBtnFlatmap;
	private Button mBtnConcatmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initData();
	}

	private void initView() {
		mBtnTest = findViewById(R.id.btn_test);
		mBtnScheduler = findViewById(R.id.btn_scheduler);
		mBtnMap = findViewById(R.id.btn_map);
		mBtnConcat = findViewById(R.id.btn_concat);
		mBtnFlatmap = findViewById(R.id.btn_flatmap);
		mBtnConcatmap = findViewById(R.id.btn_concatmap);
		mBaseTest = new BaseTest();
		mSchedulerTest = new SchedulerTest();
		mOperatorTest = new OperatorTest();
	}

	private void initData() {
		mBtnTest.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Observer observer = mBaseTest.createObserver();
				mBaseTest.createObservable(observer);
				mBaseTest.createObservableByJust(observer);
			}
		});
		mBtnScheduler.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mSchedulerTest.test();
			}
		});
		mBtnMap.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mOperatorTest.testMap();
			}
		});
		mBtnConcat.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mOperatorTest.testConcatmap();
			}
		});
		mBtnFlatmap.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mOperatorTest.testFlatmap();
			}
		});
		mBtnConcatmap.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mOperatorTest.testConcatmap();
			}
		});

	}


}
