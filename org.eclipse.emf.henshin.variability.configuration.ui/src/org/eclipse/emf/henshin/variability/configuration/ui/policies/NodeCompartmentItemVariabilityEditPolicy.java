package org.eclipse.emf.henshin.variability.configuration.ui.policies;

import org.eclipse.emf.henshin.diagram.edit.policies.NodeCompartmentItemSemanticEditPolicy;
import org.eclipse.emf.henshin.diagram.providers.HenshinElementTypes;
import org.eclipse.emf.henshin.variability.configuration.ui.commands.VariabilityBasedAttributeCreateCommand;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

import configuration.Configuration;

public class NodeCompartmentItemVariabilityEditPolicy extends NodeCompartmentItemSemanticEditPolicy {
	private final Configuration configuration;

	public NodeCompartmentItemVariabilityEditPolicy(Configuration configuration) {
		this.configuration = configuration;
	}

	public NodeCompartmentItemVariabilityEditPolicy() {
		this.configuration = null;
	}
	
	@Override
	protected Command getCreateCommand(CreateElementRequest req) {
		if (HenshinElementTypes.Node_3001 == req.getElementType()) {
			return getGEFWrapper(new VariabilityBasedAttributeCreateCommand(req, getHost().getViewer().getControl().getShell(), configuration));
		}
		return super.getCreateCommand(req);
	}
}