package org.eclipse.emf.henshin.variability.ui.viewer.util;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;

import configuration.VariabilityPoint;
import configuration.VariabilityPointBinding;

public class VPViewerBindingEditingSupport extends EditingSupport {

	private final TableViewer viewer;
	
	public VPViewerBindingEditingSupport(TableViewer viewer) {
		super(viewer);
		this.viewer = viewer;
	}
	
	@Override
	protected CellEditor getCellEditor(Object element) {
		return new ComboBoxCellEditor(viewer.getTable(), VariabilityPointBinding.getNames());
	}

	@Override
	protected boolean canEdit(Object element) {
		return true;
	}

	@Override
	protected Object getValue(Object element) {
		return ((VariabilityPoint)element).getBinding().getValue();
	}

	@Override
	protected void setValue(Object element, Object value) {
		((VariabilityPoint)element).setBinding(VariabilityPointBinding.get((int)value));
		getViewer().update(element, null);
	}

}
