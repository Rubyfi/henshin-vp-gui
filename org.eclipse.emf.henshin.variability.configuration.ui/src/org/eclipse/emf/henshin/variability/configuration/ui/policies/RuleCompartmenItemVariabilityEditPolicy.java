package org.eclipse.emf.henshin.variability.configuration.ui.policies;

import org.eclipse.emf.henshin.diagram.edit.policies.RuleCompartmentItemSemanticEditPolicy;
import org.eclipse.emf.henshin.diagram.providers.HenshinElementTypes;
import org.eclipse.emf.henshin.variability.configuration.ui.commands.VariabilityBasedNodeCreateCommand;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

import configuration.Configuration;

public class RuleCompartmenItemVariabilityEditPolicy extends RuleCompartmentItemSemanticEditPolicy {

	private final Configuration configuration;

	public RuleCompartmenItemVariabilityEditPolicy(Configuration configuration) {
		this.configuration = configuration;
	}


	public RuleCompartmenItemVariabilityEditPolicy() {
		this.configuration = null;
	}
	
	@Override
	protected Command getCreateCommand(CreateElementRequest req) {
		if (HenshinElementTypes.Node_3001 == req.getElementType()) {
			return getGEFWrapper(new VariabilityBasedNodeCreateCommand(req, configuration));
		}
		return super.getCreateCommand(req);
	}
}
