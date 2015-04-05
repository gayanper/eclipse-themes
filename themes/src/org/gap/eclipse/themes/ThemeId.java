package org.gap.eclipse.themes;

/**
 * The Enum ThemeId.
 *
 * @author gayanper
 */
public enum ThemeId {
	MATERIAL_LIGHT("org.gap.eclipse.themes.material-light"), MATERIAL_DARK(
			"org.gap.eclipse.themes.material-dark");

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
		case "org.gap.eclipse.themes.material-light":
			return MATERIAL_LIGHT;
		case "org.gap.eclipse.themes.material-dark":
			return MATERIAL_DARK;
		default:
			return null;
		}
	}
}
