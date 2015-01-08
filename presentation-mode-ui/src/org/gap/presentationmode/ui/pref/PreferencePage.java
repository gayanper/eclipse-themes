package org.gap.presentationmode.ui.pref;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.gap.presentationmode.ui.Activator;

/**
 * The Class PreferencePage.
 *
 * @author gayanper
 */
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
		addField(new IntegerFieldEditor(Preferences.PRESENTATION_FONT_SIZE, "Presentation Font Size", getFieldEditorParent()));
	}
}
