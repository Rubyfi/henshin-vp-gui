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
		Rule result = null;
		
		if(ruleEditPart != null) {
			result = (Rule)((ShapeImpl)ruleEditPart.getModel()).getElement();
		}
		
		return result;
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
	
	private static FeatureExpr getFeatureExpression(Configuration configuration, VariabilityPoint variabilityPoint) {
		FeatureExpr expr = FeatureExpression.getExpr(configuration.getRule().getFeatureModel());
		for(VariabilityPoint vp : configuration.getVariabilityPoints()) {
			if(vp.getName() != variabilityPoint.getName() && vp.getBinding() == VariabilityPointBinding.TRUE) {
				expr = FeatureExpression.and(expr, FeatureExpression.getExpr("def(" + vp.getName() + ")"));
			} else if (vp.getName() != variabilityPoint.getName() && vp.getBinding() == VariabilityPointBinding.FALSE) {
				expr = FeatureExpression.andNot(expr, FeatureExpression.getExpr("def(" + vp.getName() + ")"));
			}
		}
		return expr;
	}
	
	public static String[] getNonContradictingBindingOptions(Configuration configuration, VariabilityPoint vp) {
		ArrayList<String> options = new ArrayList<String>();
		FeatureExpr configurationExpr = getFeatureExpression(configuration, vp);
		
		options.add(VariabilityPointBinding.UNBOUND.getName());
		if(!FeatureExpression.contradicts(configurationExpr, FeatureExpression.getExpr("def(" + vp.getName() + ")"))) {
			options.add(VariabilityPointBinding.TRUE.getName());
		}
		
		if(!FeatureExpression.contradicts(configurationExpr, FeatureExpression.getExpr("!(def(" + vp.getName() + "))"))) {
			options.add(VariabilityPointBinding.FALSE.getName());
		}
		
		String[] result = new String[options.size()];
		for(int i=result.length-1; i >=0; i--) {
			result[i] = options.get(i);
		}
		
		return result;
	}
}
