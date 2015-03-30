package org.gap.eclipse.themes;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.URIUtil;
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
		createIfNotExist("platform:/config/org.gap.eclipse.themes/custom-dark.css");
		createIfNotExist("platform:/config/org.gap.eclipse.themes/custom-light.css");
	}

	private void createIfNotExist(String uri) throws IOException,
			MalformedURLException, URISyntaxException {
		final URL fileURL = FileLocator.toFileURL(URIUtil.toURL(new URI(uri)));
		final File file = new File(URIUtil.toURI(fileURL));
		if (!file.exists()) {
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
