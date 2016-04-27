package org.gap.eclipse.themes.pm.commands;

import java.io.IOException;
import java.util.Set;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationStatus;
import org.eclipse.core.runtime.IStatus;
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

import com.google.common.collect.Sets;

/**
 * The Class ToggleHandler.
 *
 * @author gayanper
 */
public class ToggleHandler extends AbstractHandler {

	private final ScopedPreferenceStore workbenchStore = new ScopedPreferenceStore(InstanceScope.INSTANCE,
			"org.eclipse.ui.workbench");

	private final ScopedPreferenceStore pluginStore = new ScopedPreferenceStore(InstanceScope.INSTANCE,
			Activator.PLUGIN_ID);

	// TODO: probably we need to check this in updates to make sure its synced
	// with preferences.
	private static final Set<String> ADDITIONAL_TEXT_FONT_KEYS = Sets.newHashSet("org.eclipse.jdt.ui.editors.textfont",
			"org.eclipse.jdt.ui.PropertiesFileEditor.textfont", "org.eclipse.wst.sse.ui.textfont");

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		boolean state = HandlerUtil.toggleCommandState(event.getCommand());
		if (state == false) {
			final String valueString = workbenchStore.getString(JFaceResources.TEXT_FONT);
			final FontData[] data = PreferenceConverter.basicGetFontData(valueString);
			pluginStore.setValue(JFaceResources.TEXT_FONT, PreferenceConverter.getStoredRepresentation(data));

			try {
				pluginStore.save();
			} catch (IOException e) {
				Activator.getDefault().getLog().log(new OperationStatus(IStatus.ERROR, Activator.PLUGIN_ID,
						OperationStatus.OPERATION_INVALID, "Error persisting default font size to plugin store", e));
				return null; // Lets get out of here
			}

			final FontDescriptor descriptor = FontDescriptor.createFrom(data)
					.setHeight(pluginStore.getInt(Preferences.PRESENTATION_FONT_SIZE));
			final String value = PreferenceConverter.getStoredRepresentation(descriptor.getFontData());

			workbenchStore.setValue(JFaceResources.TEXT_FONT, value);
			changeAdditionalFontSizes(workbenchStore, value);

			(new ThemeHandler()).switchToPresentationMode();
		} else {
			final String value = pluginStore.getString(JFaceResources.TEXT_FONT);

			workbenchStore.setValue(JFaceResources.TEXT_FONT, value);
			changeAdditionalFontSizes(workbenchStore, value);

			(new ThemeHandler()).switchToNormalMode();
		}
		return null;
	}

	private void changeAdditionalFontSizes(ScopedPreferenceStore store, String fontValue) {
		for (String key : ADDITIONAL_TEXT_FONT_KEYS) {
			store.setValue(key, fontValue);
		}
	}

}
