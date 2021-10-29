package com.daipi.common.bus

/**
 * author:daijs
 * time:2021/10/29 16:00
 * details:消息总线key
 *
 * 进程内发送消息 void post(T value)
 * App内发送消息，跨进程使用 void postAcrossProcess(T value)
 * App之间发送消息 void postAcrossApp(T value)
 * 进程内发送消息，延迟发送  void postDelay(T value, long delay)
 * 进程内发送消息，延迟发送，带生命周期 void postDelay(LifecycleOwner sender, T value, long delay)
 * 进程内发送消息，有序发送 void postOrderly(T value)
 *
 * 消息订阅
 * 以生命周期感知模式订阅消息，具有生命周期感知能力，LifecycleOwner销毁时自动取消订阅，不需要调用removeObserver
 * void observe(@NonNull LifecycleOwner owner, @NonNull Observer<T> observer)
 *
 * 以Forever模式订阅和取消订阅消息  Forever模式订阅消息，需要调用removeObserver取消订阅
 * void observeForever(@NonNull Observer<T> observer)
 * 取消订阅消息 void removeObserver(@NonNull Observer<T> observer)
 *
 * Sticky模式订阅消息（粘性消息）
 * Sticky模式
 * 支持在订阅消息的时候设置Sticky模式，这样订阅者可以接收到之前发送的消息。
 * 以Sticky模式订阅消息，具有生命周期感知能力，LifecycleOwner销毁时自动取消订阅，不需要调用removeObserver
 * void observeSticky(@NonNull LifecycleOwner owner, @NonNull Observer<T> observer)
 *
 * Sticky模式Forever订阅消息 Forever模式订阅消息，需要调用removeObserver取消订阅，Sticky模式
 * void observeStickyForever(@NonNull Observer<T> observer)
 *
 * 源码地址：https://github.com/JeremyLiao/LiveEventBus
 */
class BusKey {
    companion object {
        const val BUS_KEY_TEST = "testBus"
    }
}