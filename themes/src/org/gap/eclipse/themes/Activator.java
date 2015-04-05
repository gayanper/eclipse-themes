package org.gap.eclipse.themes;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.eclipse.ui.IStartup;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The Class Activator.
 *
 * @author gayanper
 */
public class Activator extends AbstractUIPlugin implements IStartup {
	private static Activator instance;

	public Activator() {
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		instance = this;

		// Creates the custom css files.
		createIfNotExist(CustomizeFileMapping
				.customizeFile(ThemeId.MATERIAL_DARK));
		createIfNotExist(CustomizeFileMapping
				.customizeFile(ThemeId.MATERIAL_LIGHT));
	}

	private void createIfNotExist(File file) throws IOException,
			MalformedURLException, URISyntaxException {
		if (!file.exists()) {
			if (!file.getParentFile().exists()) {
				if (!file.getParentFile().mkdirs()) {
					throw new IOException(
							"Failed to create the missing dirs for " + file);
				}
			}

			file.createNewFile();
		}
	}

	@Override
	public void earlyStartup() {

	}

	@Override
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		instance = null;
	}

	public static Activator getDefault() {
		return instance;
	}
}
