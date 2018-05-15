## rxjava的学习项目
一直想搞rxjava，没想到一看都是2.0了，项目持续更新中，我目前对rxjava的理解主要还是简化异步，解决嵌套回调问题以及一些线程调度问题，不过个人感觉rxjava算是功能强大，包括操作符什么的，但是觉得异步调用代码，线程控制还是eventBus更简洁一点
<a href = "https://www.jianshu.com/p/310726a75045">参考文章</a>
<a href = "https://www.jianshu.com/p/0cd258eecf60">参考文章</a>

### 基本概念
- Observable：在观察者模式中称为“被观察者”，数据源
- Observer：观察者模式中的“观察者”，可接收Observable发送的数据；
- subscribe：订阅，观察者与被观察者，通过subscribe()方法进行订阅；
- Subscriber：也是一种观察者，在2.0中 它与Observer没什么实质的区别，不同的是Subscriber要与Flowable(也是一种被观察者)联合使用

### just创建方式
将为你创建一个Observable并自动为你调用onNext( )发射数据
```
Observable<String> observable = Observable.just("Hello");
```

### Scheduler简介
Scheduler，相当于线程控制器

- Schedulers.newThread(): 总是启用新线程，并在新线程执行操作。
- Schedulers.io()：读写文件、读写数据库、网络信息交互等区别在于 io() 的内部实现是用一个无数量上限的线程池，可以重用空闲的线程，因此多数情况下 io() 比 newThread() 更有效率
- AndroidSchedulers.mainThread()，它指定的操作将在 Android 主线程运行。

### 操作符
rxjava网上资料非常多，所以以下的操作符概念主要是为了简洁，更多的是方便自己记忆

#### map操作符
主要用来做对象转换

#### concat操作符
合并操作符，主要用来做发射器的合并,按顺序

#### flatmap操作符
可以把一个发射器，转换成多个发射器发送出去，不过没有顺序

#### concatmap操作符
与flatmap类似，不过有顺序

### 取消订阅
rxjava虽然好用，但是容易遭层内存泄漏。也就说在订阅了事件后没有及时取阅，导致在activity或者fragment销毁后仍然占用着内存，无法释放。而disposable便是这个订阅事件，可以用来取消订阅


创建容器，用来管理被观察者
```
private CompositeDisposable mDisposables = new CompositeDisposable();
```
创建观察者
```
DisposableObserver<Boolean> mObserver = getDisposableObserver();
  private DisposableObserver<Boolean> getDisposableObserver(){
    return new DisposableObserver<Boolean>() {...};
  }
```
管理订阅
```
getObservable()//被观察者
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(mObserver);
        
mDisposables.add(mObserver);
```
取消订阅
```
mDisposables.clear()
```