package org.eclipse.emf.henshin.variability.configuration.ui.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.henshin.diagram.edit.commands.NodeCreateCommand;
import org.eclipse.emf.henshin.diagram.edit.helpers.RuleEditHelper;
import org.eclipse.emf.henshin.diagram.part.HenshinDiagramEditorPlugin;
import org.eclipse.emf.henshin.diagram.part.HenshinPaletteTools.EClassNodeTool;
import org.eclipse.emf.henshin.model.Action;
import org.eclipse.emf.henshin.model.Module;
import org.eclipse.emf.henshin.model.Node;
import org.eclipse.emf.henshin.model.Rule;
import org.eclipse.emf.henshin.model.util.HenshinModelCleaner;
import org.eclipse.emf.henshin.variability.configuration.ui.helpers.VariabilityModelHelper;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

import configuration.Configuration;

public class VariabilityNodeCreateCommand extends NodeCreateCommand {

	private final Configuration configuration;
	
	public VariabilityNodeCreateCommand(CreateElementRequest req, Configuration configuration) {
		super(req);
		this.configuration = configuration;
	}

	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {

		// The node is created in the context of a rule:
		Rule rule = (Rule) getElementToEdit();
		
		// Get the default action to be used:
		Action action = RuleEditHelper.getDefaultAction(rule);

		// Set the type of the nodes:
		CreateElementRequest request = (CreateElementRequest) getRequest();
		Object type = request.getParameter(EClassNodeTool.TYPE_PARAMETER_KEY);

		Module module = rule.getModule();
		SingleEClassifierSelectionDialog dialog = null;

		// if no type has been specified yet, let the user choose one
		if (type == null) {
			dialog = new SingleEClassifierSelectionDialog(module, action);
			type = dialog.openAndReturnSelection();
		}

		Node node;
		if (type instanceof EClass) {
			node = rule.createNode((EClass) type);
		} else {
			return CommandResult.newCancelledCommandResult();
		}

		// Update containment:
		updateContainment(rule, node);
		
		// Finally, we set the user-defined action:
		if (dialog != null) {
			action = dialog.getAction();
		}
		try {
			node.setAction(action);
			RuleEditHelper.setDefaultAction(rule, action);
		}
		catch (Throwable t) {
			HenshinDiagramEditorPlugin.getInstance().logError("Error setting node action", t);
		}
		
		node.setPresenceCondition(VariabilityModelHelper.getPresenceCondition(configuration));
		
		// Complete multi-rules:
		HenshinModelCleaner.completeMultiRules(rule.getRootRule());

		// Clean up:
		HenshinModelCleaner.cleanRule(rule.getRootRule());

		// Configure the new node:
		doConfigure(node, monitor, info);
		request.setNewElement(node);
		return CommandResult.newOKCommandResult(node);
	}
}
