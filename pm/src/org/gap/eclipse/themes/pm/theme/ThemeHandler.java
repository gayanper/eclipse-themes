package org.gap.eclipse.themes.pm.theme;

import java.util.List;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.e4.ui.css.swt.theme.ITheme;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.gap.eclipse.themes.core.ThemesManager;
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
	private final ThemesManager themesManager = new ThemesManager();

	private final ScopedPreferenceStore pluginStore = new ScopedPreferenceStore(
			InstanceScope.INSTANCE, Activator.PLUGIN_ID);

	/**
	 * Switch to presentation mode.
	 */
	public void switchToPresentationMode() {
		final String activeThemeId = themesManager.getCurrentThemeId();
		pluginStore.setValue(Preferences.NORMAL_THEME_ID, activeThemeId);

		String themeId = Preferences.ALTERNATE_THEME_ID;
		final String prefValue = pluginStore.getString(Preferences.PM_THEME_ID);

		if (!Strings.isNullOrEmpty(prefValue)) {
			Optional<ITheme> result = Iterables.tryFind(
					themesManager.getAllThemes(), new Predicate<ITheme>() {

						@Override
						public boolean apply(ITheme theme) {
							return theme.getId().equals(prefValue);
						}
					});

			if (result.isPresent()) {
				themeId = prefValue;
			}
		}

		themesManager.setTheme(themeId);
	}

	/**
	 * Switch to normal mode.
	 */
	public void switchToNormalMode() {
		final String normalThemeId = pluginStore
				.getString(Preferences.NORMAL_THEME_ID);
		themesManager.setTheme(normalThemeId);
	}

	/**
	 * Gets the current theme id.
	 *
	 * @return the current theme id
	 */
	public String getCurrentThemeId() {
		return themesManager.getCurrentThemeId();
	}

	/**
	 * Gets the all themes.
	 *
	 * @return the all themes
	 */
	public List<ITheme> getAllThemes() {
		return themesManager.getAllThemes();
	}
}
