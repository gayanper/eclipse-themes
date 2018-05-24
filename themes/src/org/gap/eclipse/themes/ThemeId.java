package org.gap.eclipse.themes;

/**
 * The Enum ThemeId.
 *
 * @author gayanper
 */
public enum ThemeId {
	MATERIAL_LIGHT("org.gap.eclipse.themes.material-l"), MATERIAL_DARK("org.gap.eclipse.themes.material-d"),
	MATERIAL_DARK_BW("org.gap.eclipse.themes.material-d-bw"), PAPER_WHITE("org.gap.eclipse.themes.paper-white"),
	PAPER_DARK("org.gap.eclipse.themes.paper-d");

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
		case "org.gap.eclipse.themes.material-l":
			return MATERIAL_LIGHT;
		case "org.gap.eclipse.themes.material-d":
			return MATERIAL_DARK;
		case "org.gap.eclipse.themes.material-d-bw":
			return MATERIAL_DARK_BW;
		case "org.gap.eclipse.themes.paper-white":
			return PAPER_WHITE;
		case "org.gap.eclipse.themes.paper-d":
			return PAPER_DARK;
		default:
			return null;
		}
	}
}
