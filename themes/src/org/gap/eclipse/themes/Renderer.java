package org.gap.eclipse.themes;

import org.eclipse.e4.ui.workbench.renderers.swt.CTabRendering;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

@SuppressWarnings("restriction")
public class Renderer extends CTabRendering {

	private CTabFolder tabFolder;

	public Renderer(CTabFolder parent) {
		super(parent);
		this.tabFolder = parent;
	}

	@Override
	protected void draw(int part, int state, Rectangle bounds, GC gc) {
		super.draw(part, state, bounds, gc);

		if ((part >= 0) && (part == tabFolder.getSelectionIndex())) {
			int x = bounds.x;
			int y = bounds.y;
			int height = bounds.height;
			int width = bounds.width;

			gc.setBackground(gc.getDevice().getSystemColor(SWT.COLOR_CYAN));
			gc.fillRectangle(x + 1, y + height - 2, width - 2, 2);
		}
	}
}
