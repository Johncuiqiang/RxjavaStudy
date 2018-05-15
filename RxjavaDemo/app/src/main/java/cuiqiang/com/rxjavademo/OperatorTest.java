package cuiqiang.com.rxjavademo;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者： cuiqiang
 * 创建日期：2018/5/15
 * 功能描述：
 */

public class OperatorTest {

	private static final String TAG = "OperatorTest";

	/**
	 * map操作符 主要用来做对象转换
	 */
	void testMap() {
		Observable.create(new ObservableOnSubscribe<Integer>() {
			@Override
			public void subscribe(ObservableEmitter<Integer> e) throws Exception {
				for (int i = 0; i < 4; i++) {
					e.onNext(i);
				}
			}
		}).subscribeOn(Schedulers.io())
				//map操作符，Function<Object,Object>，只要类型为Object的子类就可以进行转换
				.map(new Function<Integer, String>() {
					//这个就是转换的函数，返回的是转换结果
					@Override
					public String apply(Integer integer) throws Exception {
						return "This is a String Type:" + integer;
					}
				})
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Observer<String>() {
					@Override
					public void onSubscribe(Disposable d) {

					}

					@Override
					public void onNext(String s) {
						//这里接收的就是一个String类型了
						Log.d(TAG, "receive:" + s);
					}

					@Override
					public void onError(Throwable e) {

					}

					@Override
					public void onComplete() {

					}
				});
	}

	/**
	 * 合并操作符，主要用来做发射器的合并,按顺序
	 */
	void testConcat() {
		Observable.concat(Observable.just(1, 2, 3),
				Observable.just(4, 5, 6))
				.subscribe(new Consumer<Integer>() {
					@Override
					public void accept(Integer integer) throws Exception {
						Log.e(TAG, "concat : " + integer + "\n");
					}
				});
	}

	/**
	 *  发射器的转换，接收对象，返回一个发射器
	 */
	void testFlatmap() {
		Observable.fromArray(1,2,3,4,5)
			.flatMap(new Function<Integer, ObservableSource<String>>() {
			@Override
			public ObservableSource<String> apply(Integer integer) throws Exception {
				//延迟返回多个发射器
				int delay = 0;
				if(integer == 3){
					delay = 500;//延迟500ms发射
				}
				return Observable.just(integer+"").delay(delay, TimeUnit.MILLISECONDS);
			}
		}).subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Consumer<String>() {
					@Override
					public void accept(String s) throws Exception {
						Log.e(TAG, "flatmap : " + s + "\n");
					}
		});
	}

	/**
	 * 与flatmap类似，但是有顺序
	 */
	void testConcatmap(){
		Observable.fromArray(1,2,3,4,5)
				.concatMap(new Function<Integer, ObservableSource<String>>() {
			@Override
			public ObservableSource<String> apply(Integer integer) throws Exception {
				//延迟返回多个发射器
				int delay = 0;
				if(integer == 3){
					delay = 500;//延迟500ms发射
				}
				return Observable.just(integer+"").delay(delay, TimeUnit.MILLISECONDS);
			}
		}).subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Consumer<String>() {
					@Override
					public void accept(String s) throws Exception {
						Log.e(TAG, "concatmap : " + s + "\n");
					}
				});
	}
}
