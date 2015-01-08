package org.gap.presentationmode.ui.util;

import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public final class FontConfig {
	private float size;

	private String config;

	private FontConfig(String config) {
		this.config = config;
		final List<String> elems = Splitter.on('|').splitToList(config);

		if (elems.size() < 3) {
			throw new IllegalArgumentException("Invalid font config string : "
					+ config);
		}
		size = Float.parseFloat(elems.get(2));
	}

	public static FontConfig parse(String config) {
		return new FontConfig(config);
	}

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public String toConfig() {
		final List<String> elems = Lists.newArrayList(Splitter.on('|').splitToList(config));
		elems.set(2, String.valueOf(size));
		return Joiner.on('|').join(elems);
	}
}
