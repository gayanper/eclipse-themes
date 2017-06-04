package org.gap.eclipse.themes.pm;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveListener;
import org.eclipse.ui.IWindowListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class WorkbenchWindowManager {

	private Set<IWorkbenchWindow> registered = new HashSet<>();
	private PerspectiveListener perspectiveListener = new PerspectiveListener();

	public WorkbenchWindowManager() {
		PlatformUI.getWorkbench().addWindowListener(new WindowListener());
	}

	private class PerspectiveListener implements IPerspectiveListener {

		@Override
		public void perspectiveActivated(IWorkbenchPage page, IPerspectiveDescriptor perspective) {
			Activator.getDefault().getMenuBarManager().updateMenuToCommandState();
		}

		@Override
		public void perspectiveChanged(IWorkbenchPage page, IPerspectiveDescriptor perspective, String changeId) {
			Activator.getDefault().getMenuBarManager().updateMenuToCommandState();
		}

	}

	private class WindowListener implements IWindowListener {

		@Override
		public void windowActivated(IWorkbenchWindow window) {
			if (!registered.contains(window)) {
				window.addPerspectiveListener(perspectiveListener);
				Activator.getDefault().getMenuBarManager().updateMenuToCommandState();
				registered.add(window);
			}
		}

		@Override
		public void windowDeactivated(IWorkbenchWindow window) {
		}

		@Override
		public void windowClosed(IWorkbenchWindow window) {
			registered.remove(window);
		}

		@Override
		public void windowOpened(IWorkbenchWindow window) {

		}

	}

	public void dispose() {
		registered.clear();
	}
}
