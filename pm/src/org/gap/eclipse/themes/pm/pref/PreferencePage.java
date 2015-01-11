package org.gap.eclipse.themes.pm.pref;

import java.util.List;

import org.eclipse.e4.ui.css.swt.theme.ITheme;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.gap.eclipse.themes.pm.Activator;
import org.gap.eclipse.themes.pm.theme.ThemeHandler;

/**
 * The Class PreferencePage.
 *
 * @author gayanper
 */
@SuppressWarnings("restriction")
public class PreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {
	public PreferencePage() {
		super(GRID);
	}

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

	@Override
	protected void createFieldEditors() {
		addField(new IntegerFieldEditor(Preferences.PRESENTATION_FONT_SIZE,
				"Presentation Font Size", getFieldEditorParent()));
		addField(new ComboFieldEditor(Preferences.PM_THEME_ID,
				"Presentation Mode Theme", allThemeValues(),
				getFieldEditorParent()));
	}

	private String[][] allThemeValues() {
		final ThemeHandler themeHandler = new ThemeHandler();
		final List<ITheme> themes = themeHandler.getAllThemes();
		final String[][] themeValues = new String[themes.size()][2];

		for (int i = 0; i < themes.size(); i++) {
			final ITheme theme = themes.get(i);
			themeValues[i][0] = theme.getLabel();
			themeValues[i][1] = theme.getId();
		}

		return themeValues;
	}

}
