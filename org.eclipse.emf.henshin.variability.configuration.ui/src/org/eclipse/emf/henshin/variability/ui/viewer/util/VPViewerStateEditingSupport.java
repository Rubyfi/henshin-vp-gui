package org.eclipse.emf.henshin.variability.ui.viewer.util;

import org.eclipse.emf.henshin.variability.configuration.ui.parts.ITableViewerSynchronizedPart;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;

import configuration.VariabilityPoint;
import configuration.VariabilityPointState;

public class VPViewerStateEditingSupport extends EditingSupport {

	private final TableViewer viewer;
	
	public VPViewerStateEditingSupport(TableViewer viewer) {
		super(viewer);
		this.viewer = viewer;
	}
	
	@Override
	protected CellEditor getCellEditor(Object element) {
		return new ComboBoxCellEditor(viewer.getTable(), VariabilityPointState.getNames());
	}

	@Override
	protected boolean canEdit(Object element) {
		return true;
	}

	@Override
	protected Object getValue(Object element) {
		return ((VariabilityPoint)element).getState().getValue();
	}

	@Override
	protected void setValue(Object element, Object value) {
		((VariabilityPoint)element).setState(VariabilityPointState.get((int)value));
		getViewer().update(element, null);
	}

}
