package org.gap.eclipse.themes;
import org.eclipse.e4.ui.css.core.dom.properties.ICSSPropertyHandler;
import org.eclipse.e4.ui.css.core.engine.CSSEngine;
import org.eclipse.e4.ui.css.swt.properties.AbstractCSSPropertySWTHandler;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Control;
import org.w3c.dom.css.CSSValue;

@SuppressWarnings("restriction")
public class RendererCSSHandler extends AbstractCSSPropertySWTHandler implements ICSSPropertyHandler {
	private static final String CSS_PROP = "selected-border-color";

	public RendererCSSHandler() {
	}

	@Override
	protected void applyCSSProperty(Control control, String property, CSSValue value, String pseudo, CSSEngine engine)
			throws Exception {
		if (control instanceof CTabFolder) {
			if (CSS_PROP.equalsIgnoreCase(property) && value.getCssValueType() == CSSValue.CSS_PRIMITIVE_VALUE) {
				Color color = (Color) engine.convert(value, Color.class, control.getDisplay());
				control.setData(Renderer.SELECTED_BORDER_COLOR, color);
			}
		}

	}

	@Override
	protected String retrieveCSSProperty(Control control, String property, String pseudo, CSSEngine engine)
			throws Exception {
		if (control instanceof CTabFolder) {
			if (CSS_PROP.equalsIgnoreCase(property)) {
				return engine.getCSSValueConverter(String.class)
						.convert(control.getData(Renderer.SELECTED_BORDER_COLOR), engine, control.getDisplay());
			}
		}
		return null;
	}

}
