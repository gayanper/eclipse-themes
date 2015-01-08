package org.gap.presentationmode.ui.plugin;

import java.util.Set;

/**
 * The Interface UIThemePlugin which defines the contract of a UITheme plugin
 * which is capable of handling presentation mode.
 *
 * @author gayanper
 */
public interface UIThemePlugin {

	/**
	 * Gets the themes that are supported by this plugin.
	 *
	 * @return the themes
	 */
	Set<Theme> getThemes();

	/**
	 * Switch to the given theme.
	 *
	 * @param theme
	 *            the theme
	 */
	void switchTo(Theme theme);

	/**
	 * Gets the current active theme in the workbench.
	 *
	 * @return the current
	 */
	Theme getCurrent();
}
