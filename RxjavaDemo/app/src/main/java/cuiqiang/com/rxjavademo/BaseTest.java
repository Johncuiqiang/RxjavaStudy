package cuiqiang.com.rxjavademo;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 作者： cuiqiang
 * 创建日期：2018/5/15
 * 功能描述：
 */

public class BaseTest {

	private static final String TAG  = "BaseTest";

	/**
	 * 创建数据源，被观察者
	 *
	 * @param observer  观察者，接收数据源的数据
	 */
	 void createObservable(Observer observer){
		Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
			@Override
			public void subscribe(ObservableEmitter<String> e) throws Exception {
				//执行一些其他操作
				//.............
				//执行完毕，触发回调，通知观察者
				e.onNext("被观察者，数据源send data");
			}
		});
		observable.subscribe(observer);
	}

	/**
	 * 创建观察者
	 *
	 * @return 返回观察者
	 */
	Observer createObserver(){
		Observer<String> observer = new Observer<String>() {
			@Override
			public void onSubscribe(Disposable d) {

			}

			@Override
			//观察者接收到通知,进行相关操作
			public void onNext(String value) {
				Log.d(TAG,"receive data is "+ value);
			}

			@Override
			public void onError(Throwable e) {

			}

			@Override
			public void onComplete() {

			}
		};
		return observer;
	}

	/**
	 * 通过just方式创建数据源，被观察者
	 *
	 * @param observer  观察者，接收数据源的数据
	 */
	void createObservableByJust(Observer observer){
		Observable<String> observable = Observable.just("Hello");
		observable.subscribe(observer);
	}

}
