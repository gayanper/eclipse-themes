package org.gap.eclipse.themes.pm.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.handlers.HandlerUtil;
import org.gap.eclipse.themes.pm.Activator;

public class ToggleMenuHandler extends AbstractHandler {

	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		final boolean state = HandlerUtil.toggleCommandState(event.getCommand());
		if (state == false) {
			Activator.getDefault().getMenuBarManager().hideMenu();
		} else {
			Activator.getDefault().getMenuBarManager().showMenus();
		}
		return null;
	}
}
