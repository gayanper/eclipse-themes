package org.gap.presentationmode.ui.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.gap.presentationmode.ui.util.FontConfig;

/**
 * The Class ToggleHandler.
 *
 * @author gayanper
 */
public class ToggleHandler extends AbstractHandler {

	private final ScopedPreferenceStore workbenchStore = new ScopedPreferenceStore(
			InstanceScope.INSTANCE, "org.eclipse.ui.workbench");

	private final ScopedPreferenceStore pluginStore = new ScopedPreferenceStore(
			InstanceScope.INSTANCE, "org.gap.presentation-mode-ui");

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		boolean state = HandlerUtil.toggleCommandState(event.getCommand());
		if (state == false) {
			final String valueString = workbenchStore
					.getString(JFaceResources.TEXT_FONT);
			final FontConfig fontConfig = FontConfig.parse(valueString);

			pluginStore.setValue(JFaceResources.TEXT_FONT, valueString);
			fontConfig.setSize(20);
			workbenchStore.setValue(JFaceResources.TEXT_FONT,
					fontConfig.toConfig());

		} else {
			workbenchStore.setValue(JFaceResources.TEXT_FONT,
					pluginStore.getString(JFaceResources.TEXT_FONT));
		}
		return null;
	}
}
