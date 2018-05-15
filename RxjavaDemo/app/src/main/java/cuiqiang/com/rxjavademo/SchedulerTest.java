package cuiqiang.com.rxjavademo;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者： cuiqiang
 * 创建日期：2018/5/15
 * 功能描述：
 */

public class SchedulerTest {

	private static final String TAG  = "SchedulerTest";

	/**
	 * 线程通信
	 */
	void test(){
		Observable.create(new ObservableOnSubscribe<Integer>() {
			@Override
			public void subscribe(ObservableEmitter<Integer> e) throws Exception {
				Log.d(TAG,"the current thread is :"+Thread.currentThread().getName());
				e.onNext(1);
				e.onComplete();
			}
		//指定Observable(被观察者,数据源)所在的线程，或者叫做事件产生的线程。
		}).subscribeOn(Schedulers.io())
				//指定 Observer(观察者)所运行在的线程，或者叫做事件消费的线程。
				.observeOn(AndroidSchedulers.mainThread())
				//事件消费的回掉
                .subscribe(new Consumer<Integer>() {
			@Override
			public void accept(Integer integer) throws Exception {
				Log.d(TAG,"the current thread is :"+ Thread.currentThread().getName());
				Log.d(TAG,"receive data :"+ "integer:" + integer);
			}
		});
	}


}
