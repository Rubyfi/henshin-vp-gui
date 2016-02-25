package org.eclipse.emf.henshin.variability.configuration.ui.helpers;

import java.util.ArrayList;

import org.eclipse.emf.henshin.diagram.edit.parts.RuleEditPart;
import org.eclipse.emf.henshin.model.Rule;
import org.eclipse.emf.henshin.variability.matcher.FeatureExpression;
import org.eclipse.gmf.runtime.notation.impl.ShapeImpl;

import configuration.Configuration;
import configuration.VariabilityPoint;
import configuration.VariabilityPointBinding;
import de.fosd.typechef.featureexpr.FeatureExpr;

public class VariabilityModelHelper {
	
	public static Rule getRuleForEditPart(RuleEditPart ruleEditPart) {
		return (Rule)((ShapeImpl)ruleEditPart.getModel()).getElement();
	}
	
	public static FeatureExpr getFeatureExpression(Configuration configuration) {
		FeatureExpr expr = FeatureExpression.getExpr(configuration.getRule().getFeatureModel());
		for(VariabilityPoint vp : configuration.getVariabilityPoints()) {
			if(vp.getBinding() == VariabilityPointBinding.TRUE) {
				expr = FeatureExpression.and(expr, FeatureExpression.getExpr("def(" + vp.getName() + ")"));
			} else if (vp.getBinding() == VariabilityPointBinding.FALSE) {
				expr = FeatureExpression.andNot(expr, FeatureExpression.getExpr("def(" + vp.getName() + ")"));
			}
		}
		return expr;
	}
	
	
	public static String getPresenceCondition(Configuration configuration) {
		StringBuilder result = new StringBuilder();
		String delimiter = "";
		
		for(VariabilityPoint vp : configuration.getVariabilityPoints()) {
			if(vp.getBinding() != VariabilityPointBinding.UNBOUND) {
				result.append(delimiter);

				if(vp.getBinding() == VariabilityPointBinding.FALSE) {
					result.append("!");
				}
				result.append("def(" + vp.getName() + ")");
				delimiter = " and ";
			}			
		}
		
		return result.toString();
	}
	
	
	public static String[] getNonContradictingBindingOptions(Configuration configuration, VariabilityPoint vp) {
		ArrayList<String> options = new ArrayList<String>();
		FeatureExpr expr = getFeatureExpression(configuration);
		
		options.add(VariabilityPointBinding.UNBOUND.getName());
		if(!FeatureExpression.contradicts(expr, FeatureExpression.getExpr("def(" + vp + ")"))) {
			options.add(VariabilityPointBinding.TRUE.getName());
		}
		
		if(!FeatureExpression.contradicts(expr, FeatureExpression.getExpr("!(def(" + vp + "))"))) {
			options.add(VariabilityPointBinding.FALSE.getName());
		}
		
		return (String[]) options.toArray();
	}
}
