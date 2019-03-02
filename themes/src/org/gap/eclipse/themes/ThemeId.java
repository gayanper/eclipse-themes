package org.gap.eclipse.themes;

/**
 * The Enum ThemeId.
 *
 * @author gayanper
 */
public enum ThemeId {
	DARK("org.eclipse.e4.ui.css.theme.e4_dark"), 
	LIGHT("org.eclipse.e4.ui.css.theme.e4_default");

	private String themeId;

	private ThemeId(String themeId) {
		this.themeId = themeId;
	}

	/**
	 * Gets the string fully qualified theme id.
	 *
	 * @return the id
	 */
	public String getId() {
		return themeId;
	}

	public static ThemeId forId(String id) {
		switch (id) {
		case "org.eclipse.e4.ui.css.theme.e4_dark":
			return DARK;
		case "org.eclipse.e4.ui.css.theme.e4_default":
			return LIGHT;
		default:
			return null;
		}
	}
}
