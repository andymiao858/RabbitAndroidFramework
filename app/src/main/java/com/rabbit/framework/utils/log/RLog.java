package com.rabbit.framework.utils.log;

import android.util.Log;

import com.rabbit.framework.BuildConfig;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author miaohd
 */
public class RLog {

	private final static String TAG = "Rabbit";
	private final static int LOG_TEXT_MAX_LENGTH = 4000;
	private final static boolean VERBOSE_ENABLED = BuildConfig.LOG_LEVEL >= Log.VERBOSE;
	private final static boolean DEBUG_ENABLED = BuildConfig.LOG_LEVEL >= Log.DEBUG;
	private final static boolean INFO_ENABLED = BuildConfig.LOG_LEVEL >= Log.INFO;
	private final static boolean WARN_ENABLED = BuildConfig.LOG_LEVEL >= Log.WARN;
	private final static boolean ERROR_ENABLED = BuildConfig.LOG_LEVEL >= Log.ERROR;


	public static void v(Object message) {
		v(TAG, message);
	}

	public static void d(Object message) {
		d(TAG, message);
	}

	public static void i(Object message) {
		i(TAG, message);
	}

	public static void w(Object message) {
		w(TAG, message);
	}

	public static void e(Object message) {
		e(TAG, message);
	}

	public static void v(String tag, Object message) {
		print(Log.VERBOSE, tag, message);
	}

	public static void d(String tag, Object message) {
		print(Log.DEBUG, tag, message);
	}

	public static void i(String tag, Object message) {
		print(Log.INFO, tag, message);
	}

	public static void w(String tag, Object message) {
		print(Log.WARN, tag, message);
	}

	public static void e(String tag, Object message) {

		if (message instanceof Exception) {
			Exception e = (Exception) message;
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			message = sw.toString();
		}

		print(Log.ERROR, tag, message);
	}

	private static void print(int logLevel, String tag, Object message) {

		checkMessageLength(logLevel, tag, String.valueOf(message));
	}

	private static void checkMessageLength(int logLevel, String tag, String msg) {

		if (msg.length() < LOG_TEXT_MAX_LENGTH) {
			printMessage(logLevel, tag, msg);
			return;
		}

		printMessage(logLevel, tag, msg.substring(0, LOG_TEXT_MAX_LENGTH));

		checkMessageLength(logLevel, tag, msg.substring(LOG_TEXT_MAX_LENGTH));
	}

	private static void printMessage(int logLevel, String tag, String message) {

		switch (logLevel) {
			case Log.VERBOSE:
				if (VERBOSE_ENABLED) Log.v(tag, message);
				break;
			case Log.DEBUG:
				if (DEBUG_ENABLED) Log.d(tag, message);
				break;
			case Log.INFO:
				if (INFO_ENABLED) Log.i(tag, message);
				break;
			case Log.WARN:
				if (WARN_ENABLED) Log.w(tag, message);
				break;
			case Log.ERROR:
				if (ERROR_ENABLED) Log.e(tag, message);
				break;
			default:
				break;
		}
	}

}
