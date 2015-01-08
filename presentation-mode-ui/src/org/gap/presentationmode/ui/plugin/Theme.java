package org.gap.presentationmode.ui.plugin;

/**
 * The Class Theme represents a theme object.
 *
 * @author gayanper
 */
public final class Theme {
	private String id;

	private String label;

	/**
	 * Instantiates a new theme.
	 *
	 * @param id
	 *            the id
	 * @param label
	 *            the label
	 */
	public Theme(String id, String label) {
		this.id = id;
		this.label = label;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Theme other = (Theme) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
