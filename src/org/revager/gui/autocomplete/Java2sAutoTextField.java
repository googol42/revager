package org.revager.gui.autocomplete;

import java.util.ArrayList;

/*
 * Copyright (c) 2006 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * -Redistribution of source code must retain the above copyright notice, this
 *  list of conditions and the following disclaimer.
 *
 * -Redistribution in binary form must reproduce the above copyright notice,
 *  this list of conditions and the following disclaimer in the documentation
 *  and/or other materials provided with the distribution.
 *
 * Neither the name of Sun Microsystems, Inc. or the names of contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING
 * ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN MIDROSYSTEMS, INC. ("SUN")
 * AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE
 * AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE FOR ANY LOST
 * REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL,
 * INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY
 * OF LIABILITY, ARISING OUT OF THE USE OF OR INABILITY TO USE THIS SOFTWARE,
 * EVEN IF SUN HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *
 * You acknowledge that this software is not designed, licensed or intended
 * for use in the design, construction, operation or maintenance of any
 * nuclear facility.
 */

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import org.apache.commons.lang3.StringUtils;

/**
 * Field, based on {@link JTextField}, which remembers all entered values. Based
 * on these values autocompletion is suggested.
 * 
 * Based on
 * http://www.java2s.com/Code/Java/Swing-Components/AutocompleteComboBox.htm
 */
public abstract class Java2sAutoTextField extends JTextField {
	private static final long serialVersionUID = 9061181066044572705L;

	class AutoDocument extends PlainDocument {

		private static final long serialVersionUID = 2276955569199309661L;

		@Override
		public void insertString(int i, String s, AttributeSet attributeset) throws BadLocationException {
			if (StringUtils.isEmpty(s)) {
				return;
			}
			if (s.length() != 1) {
				super.insertString(i, s, attributeset);
				return;
			}
			if (getLength() != i) {
				super.insertString(i, s, attributeset);
				return;
			}
			String text = getText(0, getLength()) + s;
			String match = getMatch(text);
			if (match == null) {
				super.insertString(i, s, attributeset);
				return;
			}
			super.remove(0, getLength());
			super.insertString(0, match, attributeset);
			setSelectionStart(i + 1);
			setSelectionEnd(getLength());
		}

		@Override
		public void remove(int i, int j) throws BadLocationException {
			if (getSelectedText() != null) {
				super.remove(i, j);
				return;
			}
			if (i + j == getLength()) {
				super.remove(i, j);
				return;
			}
			int k = getSelectionStart();
			if (k > 0) {
				k--;
			}
			String s = getMatch(getText(0, k));
			if (StringUtils.equals(s, getText(0, k))) {
				super.remove(i, j);
			} else {
				super.remove(0, getLength());
				super.insertString(0, s, null);
				try {
					setSelectionStart(k);
					setSelectionEnd(getLength());
				} catch (Exception exception) {
				}
			}
		}
	}

	private static final Map<Object, List<String>> map = Collections.synchronizedMap(new HashMap<>());

	private transient final Object object;

	/**
	 * Initialize the auto suggestion text field.
	 * 
	 * @param type
	 *            All {@link Java2sAutoTextField} instances of the same type
	 *            share the same suggestions. This allows to suggest a word
	 *            already typed in another field.
	 */
	public Java2sAutoTextField(Object type) {
		this.object = type;
		map.put(type, new ArrayList<>());
		setDocument(new AutoDocument());
		loadData();
		sortList();
	}

	protected abstract void loadData();

	protected void addSuggestion(String suggestion) {
		if (!getList().contains(suggestion.trim())) {
			directAdd(suggestion.trim());
		}
	}

	private void directAdd(String suggestion) {
		if (!StringUtils.isBlank(suggestion)) {
			getList().add(suggestion.trim());
		}
	}

	private List<String> getList() {
		return map.get(object);
	}

	private void sortList() {
		getList().sort((o1, o2) -> o2.length() - o1.length());
	}

	private String getMatch(String s) {
		if (StringUtils.isBlank(s)) {
			return s;
		}
		for (int i = 0; i < getList().size(); i++) {
			String s1 = getList().get(i);
			if (s1 != null && s1.startsWith(s))
				return s1;
		}
		directAdd(s);
		sortList();
		return null;
	}

	@Override
	public void replaceSelection(String s) {
		AutoDocument _lb = (AutoDocument) getDocument();
		if (_lb != null)
			try {
				int i = Math.min(getCaret().getDot(), getCaret().getMark());
				int j = Math.max(getCaret().getDot(), getCaret().getMark());
				_lb.replace(i, j - i, s, null);
			} catch (Exception exception) {
			}
	}

}
