package org.gap.eclipse.themes.pm.theme;

import java.util.List;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.css.swt.theme.ITheme;
import org.eclipse.e4.ui.css.swt.theme.IThemeEngine;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.gap.eclipse.themes.pm.Activator;
import org.gap.eclipse.themes.pm.pref.Preferences;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;

/**
 * The Class ThemeHandler.
 */
@SuppressWarnings("restriction")
public class ThemeHandler {
	private final ScopedPreferenceStore pluginStore = new ScopedPreferenceStore(
			InstanceScope.INSTANCE, Activator.PLUGIN_ID);

	/**
	 * Switch to presentation mode.
	 */
	public void switchToPresentationMode() {
		final IThemeEngine themeEngine = getThemeEngine();
		final String activeThemeId = themeEngine.getActiveTheme().getId();
		pluginStore.setValue(Preferences.NORMAL_THEME_ID, activeThemeId);

		String themeId = Preferences.ALTERNATE_THEME_ID;
		final String prefValue = pluginStore.getString(Preferences.PM_THEME_ID);

		if (!Strings.isNullOrEmpty(prefValue)) {
			Optional<ITheme> result = Iterables.tryFind(
					themeEngine.getThemes(), new Predicate<ITheme>() {

						@Override
						public boolean apply(ITheme theme) {
							return theme.getId().equals(prefValue);
						}
					});

			if (result.isPresent()) {
				themeId = prefValue;
			}
		}

		themeEngine.setTheme(themeId, true);
	}

	/**
	 * Switch to normal mode.
	 */
	public void switchToNormalMode() {
		final IThemeEngine themeEngine = getThemeEngine();
		final String normalThemeId = pluginStore
				.getString(Preferences.NORMAL_THEME_ID);
		themeEngine.setTheme(normalThemeId, true);
	}

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
	
	private IThemeEngine getThemeEngine() {
		MApplication application = (MApplication) PlatformUI.getWorkbench()
				.getService(MApplication.class);
		IEclipseContext context = application.getContext();
		IThemeEngine engine = context.get(IThemeEngine.class);
		return engine;
	}

}
