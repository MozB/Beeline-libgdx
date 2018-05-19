package org.moz.beelinelibgdx.core.util;

import com.badlogic.gdx.Gdx;

public class BeelineLogger {

	public static void log(String tag, String message) {
		if (Gdx.app != null) {
			Gdx.app.log(tag, message);
		} else {
			System.out.println("INFO: " + tag + ": " + message);
		}
	}

	public static void log(Object object, String message) {
		log(object.getClass().getName(), message);
	}

}
