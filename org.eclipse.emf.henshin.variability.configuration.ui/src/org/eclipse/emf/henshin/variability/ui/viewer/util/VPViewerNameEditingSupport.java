package org.eclipse.emf.henshin.variability.ui.viewer.util;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;

import configuration.VariabilityPoint;

public class VPViewerNameEditingSupport extends EditingSupport {

	private final TableViewer viewer;
	
	public VPViewerNameEditingSupport(TableViewer viewer) {
		super(viewer);
		this.viewer = viewer;
	}
	
	@Override
	protected CellEditor getCellEditor(Object element) {
		return new TextCellEditor(viewer.getTable());
	}

	@Override
	protected boolean canEdit(Object element) {
		return true;
	}

	@Override
	protected Object getValue(Object element) {
		return ((VariabilityPoint)element).getName();
	}

	@Override
	protected void setValue(Object element, Object value) {
		((VariabilityPoint)element).setName((String)value);
		getViewer().update(element, null);
	}

}