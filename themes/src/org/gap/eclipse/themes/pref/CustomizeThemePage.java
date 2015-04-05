package org.gap.eclipse.themes.pref;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.e4.ui.css.swt.theme.ITheme;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.gap.eclipse.themes.CustomizeFileMapping;
import org.gap.eclipse.themes.ThemeId;
import org.gap.eclipse.themes.core.ThemesManager;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

@SuppressWarnings("restriction")
public class CustomizeThemePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	// TODO: Introduce DI
	private ThemesManager manager = new ThemesManager();

	public CustomizeThemePage() {
	}

	@Override
	public void init(IWorkbench workbench) {

	}

	@Override
	protected Control createContents(Composite parent) {
		final Composite content = new Composite(parent, SWT.NULL);
		final GridLayout layout = new GridLayout();

		layout.numColumns = 2;
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		content.setLayout(layout);
		content.setFont(parent.getFont());

		createEditButtons(content);

		return content;
	}

	private void createEditButtons(Composite content) {
		final ImmutableSet<String> pluginThemes = FluentIterable
				.from(Lists.newArrayList(ThemeId.values()))
				.transform(new Function<ThemeId, String>() {

					@Override
					public String apply(ThemeId input) {
						return input.getId();
					}
				}).toSet();

		final Iterable<ITheme> allThemes = Iterables.filter(
				manager.getAllThemes(), new Predicate<ITheme>() {

					@Override
					public boolean apply(ITheme input) {
						return pluginThemes.contains(input.getId());
					}
				});

		for (ITheme theme : allThemes) {
			Label label = new Label(content, SWT.NULL);
			label.setText("Custom css file for [" + theme.getLabel() + "]");

			Button button = new Button(content, SWT.PUSH);
			button.setText("Edit");
			button.addSelectionListener(new EditAction(ThemeId.forId(theme
					.getId())));
		}
	}

	private static class EditAction implements SelectionListener {

		private ThemeId themeId;

		public EditAction(ThemeId themeId) {
			this.themeId = themeId;
		}

		@Override
		public void widgetSelected(SelectionEvent e) {
			try {
				final File file = CustomizeFileMapping.customizeFile(themeId);
				final IFileStore fileStore = EFS.getLocalFileSystem().getStore(
						file.toURI());
				IDE.openEditorOnFileStore(PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getActivePage(), fileStore);
			} catch (PartInitException | URISyntaxException | IOException ex) {
				// TODO: do proper logging.
				ex.printStackTrace();
			}
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {

		}

	}
}
