package org.gap.eclipse.themes.pm.commands;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.State;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.internal.Workbench;
import org.eclipse.ui.internal.WorkbenchWindow;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

@SuppressWarnings("restriction")
public class ToggleMenuHandler extends AbstractHandler {
	private static final String TOGGLE_STATE_ID = "org.eclipse.ui.commands.toggleState";
	private static final String COMMAND_ID = "presentation-mode-ui.toggle-menu";
	private final Multimap<String, IContributionItem> hiddenMenus = ArrayListMultimap.create();

	public ToggleMenuHandler() {
		final IWorkbenchWindow window = Workbench.getInstance().getActiveWorkbenchWindow();
		window.addPerspectiveListener(new IPerspectiveListener() {

			@Override
			public void perspectiveChanged(final IWorkbenchPage arg0, final IPerspectiveDescriptor arg1,
					final String arg2) {
				updateMenu();
			}

			@Override
			public void perspectiveActivated(final IWorkbenchPage arg0, final IPerspectiveDescriptor arg1) {
				updateMenu();
			}

			private void updateMenu() {
				final ICommandService commandService = (ICommandService) PlatformUI.getWorkbench()
						.getService(ICommandService.class);
				final State state = commandService.getCommand(COMMAND_ID).getState(TOGGLE_STATE_ID);

				if ((boolean) state.getValue()) {
					hideMenu();
				}
			}
		});

	}

	public void hideMenu() {
		final IWorkbenchWindow window = Workbench.getInstance().getActiveWorkbenchWindow();
		final MenuManager menuManager = ((WorkbenchWindow) window).getMenuManager();
		final String perpectiveId = window.getActivePage().getPerspective().getId();

		for (final IContributionItem mItem : menuManager.getItems()) {
			menuManager.remove(mItem);
			hiddenMenus.put(perpectiveId, mItem);
		}
		menuManager.update(true);
	}

	public void showMenus() {
		final IWorkbenchWindow window = Workbench.getInstance().getActiveWorkbenchWindow();
		final MenuManager menuManager = ((WorkbenchWindow) window).getMenuManager();
		final String perpectiveId = window.getActivePage().getPerspective().getId();

		final Collection<IContributionItem> menuItems = hiddenMenus.get(perpectiveId);
		for (Iterator<IContributionItem> iterator = menuItems.iterator(); iterator.hasNext();) {
			menuManager.add(iterator.next());
			iterator.remove();
		}
		menuManager.update(true);
	}

	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		final boolean state = HandlerUtil.toggleCommandState(event.getCommand());
		if (state == false) {
			hideMenu();
		} else {
			showMenus();
		}
		return null;
	}
}
