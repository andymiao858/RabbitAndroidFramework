package com.rabbit.framework.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author miaohd
 */
public class IOUtil {

	public static void closeIO(InputStream is){
		if (is != null) {
			try{
				is.close();
			} catch (IOException e) {
				e.getStackTrace();
			}
		}
	}

	public static void closeIO(OutputStream os){
		if (os != null) {
			try{
				os.close();;
			}catch (IOException e){
				e.printStackTrace();
			}
		}
	}

}
