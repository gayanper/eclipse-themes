package org.gap.eclipse.themes;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.URIUtil;

/**
 * The Class CustomizeMapping which keeps mapping for customization file against
 * each theme id.
 *
 * @author gayanper
 */
public final class CustomizeFileMapping {

	private static final String BASE_PATH = "platform:/config/org.gap.eclipse.themes/";

	private static final Map<ThemeId, String> MAPPINGS = new HashMap<>();

	static {
		MAPPINGS.put(ThemeId.MATERIAL_DARK, "custom-dark.css");
		MAPPINGS.put(ThemeId.MATERIAL_DARK_BW, "custom-dark-bw.css");
		MAPPINGS.put(ThemeId.MATERIAL_LIGHT, "custom-light.css");
		MAPPINGS.put(ThemeId.PAPER_DARK, "custom-paper-dark.css");
		MAPPINGS.put(ThemeId.PAPER_WHITE, "custom-paper-white.css");
	}

	private CustomizeFileMapping() {
	}

	/**
	 * Customize file for given theme id.
	 *
	 * @param themeId
	 *            the theme id
	 * @return the file
	 */
	public static File customizeFile(ThemeId themeId) throws IOException,
			URISyntaxException {
		final String fileName = MAPPINGS.get(themeId);
		final URL fileURL = FileLocator.toFileURL(URIUtil.toURL(new URI(
				BASE_PATH + fileName)));
		return new File(URIUtil.toURI(fileURL));
	}
}
