package org.gap.eclipse.themes.core;

import java.util.List;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.css.swt.theme.ITheme;
import org.eclipse.e4.ui.css.swt.theme.IThemeEngine;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.ui.PlatformUI;

/**
 * The Class ThemesManager which contains and manage theme related operations.
 *
 * @author gayanper
 */
@SuppressWarnings("restriction")
public class ThemesManager {

	/**
	 * Gets the current theme id.
	 *
	 * @return the current theme id
	 */
	public String getCurrentThemeId() {
		final IThemeEngine themeEngine = getThemeEngine();
		return themeEngine.getActiveTheme().getId();
	}

	/**
	 * Gets the all themes.
	 *
	 * @return the all themes
	 */
	public List<ITheme> getAllThemes() {
		return getThemeEngine().getThemes();
	}

	/**
	 * Sets the theme.
	 *
	 * @param themeId
	 *            the new theme
	 */
	public void setTheme(String themeId) {
		getThemeEngine().setTheme(themeId, true);
	}

	private IThemeEngine getThemeEngine() {
		MApplication application = (MApplication) PlatformUI.getWorkbench()
				.getService(MApplication.class);
		IEclipseContext context = application.getContext();
		IThemeEngine engine = context.get(IThemeEngine.class);
		return engine;
	}

}
