package org.eclipse.emf.henshin.variability.ui.viewer.util;

import org.eclipse.emf.henshin.variability.configuration.ui.helpers.VariabilityModelHelper;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;

import configuration.Configuration;
import configuration.VariabilityPoint;
import configuration.VariabilityPointBinding;

public class VPViewerBindingEditingSupport extends EditingSupport {

	private final TableViewer viewer;
	private String[] nonContradictingOptions = VariabilityPointBinding.getNames();
	
	public VPViewerBindingEditingSupport(TableViewer viewer) {
		super(viewer);
		this.viewer = viewer;
	}
	
	@Override
	protected CellEditor getCellEditor(Object element) {
		Configuration configuration = (Configuration) viewer.getInput();
		VariabilityPoint vp = (VariabilityPoint) element;
		nonContradictingOptions = VariabilityModelHelper.getNonContradictingBindingOptions(configuration, vp);
		return new ComboBoxCellEditor(viewer.getTable(), nonContradictingOptions);
	}

	@Override
	protected boolean canEdit(Object element) {
		return true;
	}

	@Override
	protected Object getValue(Object element) {
		VariabilityPoint vp = ((VariabilityPoint)element);
		String bindingname = vp.getBinding().getName();
		
		int index = -1;
		for(int i = nonContradictingOptions.length - 1; i >= 0; i--) {
			if(nonContradictingOptions[i].equals(bindingname)) {
				index = i;
			}
		}
		return index;
	}

	@Override
	protected void setValue(Object element, Object value) {
		VariabilityPoint vp = (VariabilityPoint)element;
		VariabilityPointBinding oldBinding = vp.getBinding();
		String bindingString = nonContradictingOptions[(int)value];
		VariabilityPointBinding newBinding = VariabilityPointBinding.getByName(bindingString);
		
		if(oldBinding != newBinding) {
			vp.setBinding(newBinding);
			getViewer().update(element, new String[]{"binding"});
		}
	}

}
