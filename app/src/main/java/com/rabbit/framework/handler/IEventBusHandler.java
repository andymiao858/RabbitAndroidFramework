package com.rabbit.framework.handler;

import com.rabbit.framework.models.event.MessageEvent;

/**
 * @author miaohd
 */
public interface IEventBusHandler {

	public void onEvent(MessageEvent messageEvent);

//	/**
//	 * PostThread
//	 * <p/>
//	 * Subscriber will be called in the same thread, which is posting the event. This is the default.
//	 * Event delivery implies the least overhead because it avoids thread switching completely.
//	 * Thus this is the recommended mode for simple tasks that are known to complete is a very short time without requiring the main thread.
//	 * Event handlers using this mode should return quickly to avoid blocking the posting thread, which may be the main thread
//	 */
//	public interface IPostThreadHandler {
//		public void onEvent(MessageEvent messageEvent);
//	}
//
//	/**
//	 * MainThread
//	 * <p/>
//	 * Subscriber will be called in Android's main thread (sometimes referred to as UI thread).
//	 * If the posting thread is the main thread, event handler methods will be called directly.
//	 * Event handlers using this mode must return quickly to avoid blocking the main thread.
//	 */
//	public interface IMainThreadHandler {
//		public void onEventMainThread(MessageEvent messageEvent);
//	}
//
//
//	/**
//	 * BackgroundThread
//	 * <p/>
//	 * Subscriber will be called in a background thread. If posting thread is not the main thread,
//	 * event handler methods will be called directly in the posting thread. If the posting thread is the main thread,
//	 * EventBus uses a single background thread that will deliver all its events sequentially.
//	 * Event handlers using this mode should try to return quickly to avoid blocking the background thread.
//	 */
//	public interface IBackgroundThreadHandler {
//		public void onEventBackgroundThread(MessageEvent messageEvent);
//	}
//
//	/**
//	 * Async
//	 * <p/>
//	 * Event handler methods are called in a separate thread.
//	 * This is always independent from the posting thread and the main thread.
//	 * Posting events never wait for event handler methods using this mode.
//	 * Event handler methods should use this mode if their execution might take some time, e.g. for network access.
//	 * Avoid triggering a large number of long running asynchronous handler methods at the same time to limit the number of concurrent threads.
//	 * EventBus uses a thread pool to efficiently reuse threads from completed asynchronous event handler notifications.
//	 */
//	public interface IAsyncHandler {
//		public void onEventAsync(MessageEvent messageEvent);
//	}

}
