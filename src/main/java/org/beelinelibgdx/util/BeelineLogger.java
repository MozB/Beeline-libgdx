package org.beelinelibgdx.util;

import com.badlogic.gdx.Gdx;

public class BeelineLogger {

	static boolean enabled = true;

	public static void log(String tag, String message) {
		if (enabled) {
			if (Gdx.app != null) {
				Gdx.app.log(tag, message);
			} else {
				System.out.println("INFO: " + tag + ": " + message);
			}
		}
	}

	public static void log(Object object, String message) {
		log(object.getClass().getSimpleName(), message);
	}

	public static void setEnabled(boolean b) {
		enabled = b;
	}

	public static boolean isEnabled() {
		return enabled;
	}
}
