package org.gap.eclipse.themes.pm.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.gap.eclipse.themes.pm.Activator;
import org.gap.eclipse.themes.pm.pref.Preferences;
import org.gap.eclipse.themes.pm.theme.ThemeHandler;

/**
 * The Class ToggleHandler.
 *
 * @author gayanper
 */
public class ToggleHandler extends AbstractHandler {

	private final ScopedPreferenceStore workbenchStore = new ScopedPreferenceStore(
			InstanceScope.INSTANCE, "org.eclipse.ui.workbench");

	private final ScopedPreferenceStore pluginStore = new ScopedPreferenceStore(
			InstanceScope.INSTANCE, Activator.PLUGIN_ID);

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		boolean state = HandlerUtil.toggleCommandState(event.getCommand());
		if (state == false) {
			final String valueString = workbenchStore
					.getString(JFaceResources.TEXT_FONT);
			final FontData[] data = PreferenceConverter
					.basicGetFontData(valueString);
			pluginStore.setValue(JFaceResources.TEXT_FONT,
					PreferenceConverter.getStoredRepresentation(data));

			final FontDescriptor descriptor = FontDescriptor.createFrom(data).setHeight(pluginStore
					.getInt(Preferences.PRESENTATION_FONT_SIZE));
			workbenchStore.setValue(JFaceResources.TEXT_FONT,
					PreferenceConverter.getStoredRepresentation(descriptor
							.getFontData()));
			(new ThemeHandler()).switchToPresentationMode();
		} else {
			workbenchStore.setValue(JFaceResources.TEXT_FONT,
					pluginStore.getString(JFaceResources.TEXT_FONT));
			(new ThemeHandler()).switchToNormalMode();
		}

		return null;
	}
}
