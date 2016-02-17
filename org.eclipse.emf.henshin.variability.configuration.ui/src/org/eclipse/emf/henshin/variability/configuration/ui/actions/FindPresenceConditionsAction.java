package org.eclipse.emf.henshin.variability.configuration.ui.actions;

import org.eclipse.emf.henshin.diagram.edit.parts.RuleEditPart;
import org.eclipse.emf.henshin.model.Rule;
import org.eclipse.emf.henshin.variability.configuration.ui.helpers.VariabilityModelHelper;
import org.eclipse.emf.henshin.variability.configuration.ui.providers.ConfigurationProvider;
import org.eclipse.emf.henshin.variability.ui.views.VariabilityPointsView;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import configuration.Configuration;

public class FindPresenceConditionsAction implements IActionDelegate {

	public final static String ID = "org.eclipse.emf.henshin.variability.ui.FindPresenceConditionsActionID";
	private RuleEditPart selectedRuleEditPart;
	
	@Override
	public void run(IAction action) {
		if(selectedRuleEditPart != null) {
			VariabilityPointsView vpView = openAndGetVariabilityPointsView();
			vpView.showBusy(true);
			Rule rule = VariabilityModelHelper.getRuleForEditPart(selectedRuleEditPart);
			ConfigurationProvider configProvider = ConfigurationProvider.getInstance();
			Configuration config = configProvider.getConfiguration(rule);
			vpView.setSelectedRuleEditPart(selectedRuleEditPart);
			vpView.setContent(config);
			vpView.showBusy(false);
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		selectedRuleEditPart = null;
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.getFirstElement() instanceof RuleEditPart) {
				selectedRuleEditPart = (RuleEditPart) structuredSelection.getFirstElement();
			}
		}
	}

	private VariabilityPointsView openAndGetVariabilityPointsView() {
		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getActivePage().showView(VariabilityPointsView.ID);
			return 
					(VariabilityPointsView) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
			.getActivePage().findView(VariabilityPointsView.ID);
		} catch (PartInitException e) {
			e.printStackTrace();
			return null;
		}
	}
}
