package org.eclipse.emf.henshin.variability.ui.viewer.util;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;

import configuration.VariabilityPoint;

public class VPViewerComparator extends ViewerComparator {
	private int propertyIndex;
	private static final int DESCENDING = 1;
	private int direction = DESCENDING;

	public VPViewerComparator() {
		this.propertyIndex = 0;
		direction = DESCENDING;
	}

	public int getDirection() {
		return direction == 1 ? SWT.DOWN : SWT.UP;
	}

	public void setColumn(int column) {
		if (column == this.propertyIndex) {
			direction = 1 - direction;
		} else {
			this.propertyIndex = column;
			direction = DESCENDING;
		}
	}

	@Override
	public int compare(Viewer viewer, Object o1, Object o2) {
		VariabilityPoint vp1 = (VariabilityPoint) o1;
		VariabilityPoint vp2 = (VariabilityPoint) o2;
		int result = 0;

		switch (propertyIndex) {
		case 0:
			result = vp1.getName().compareTo(vp2.getName());
			break;
		case 1:
			result = vp1.getBinding().compareTo(vp2.getBinding());
			break;
		default:
			break;
		}

		if (direction == DESCENDING) {
			result = -result;
		}

		return result;
	}
}
