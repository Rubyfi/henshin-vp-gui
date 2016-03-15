/**
 */
package configuration.impl;

import configuration.Configuration;
import configuration.ConfigurationFactory;
import configuration.ConfigurationPackage;
import configuration.VariabilityPoint;
import configuration.VariabilityPointBinding;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.henshin.model.Rule;
import org.eclipse.emf.henshin.model.impl.RuleImpl;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link configuration.impl.ConfigurationImpl#getRule <em>Rule</em>}</li>
 *   <li>{@link configuration.impl.ConfigurationImpl#getVariabilityPoints <em>Variability Points</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConfigurationImpl extends MinimalEObjectImpl.Container implements Configuration {
	/**
	 * The cached value of the '{@link #getRule() <em>Rule</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRule()
	 * @generated
	 * @ordered
	 */
	protected Rule rule;

	/**
	 * The cached value of the '{@link #getVariabilityPoints() <em>Variability Points</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariabilityPoints()
	 * @generated
	 * @ordered
	 */
	protected EList<VariabilityPoint> variabilityPoints;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConfigurationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ConfigurationPackage.Literals.CONFIGURATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<VariabilityPoint> getVariabilityPoints() {
		if (variabilityPoints == null) {
			variabilityPoints = new EObjectResolvingEList<VariabilityPoint>(VariabilityPoint.class, this, ConfigurationPackage.CONFIGURATION__VARIABILITY_POINTS);
		}
		return variabilityPoints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Rule getRule() {
		if (rule != null && rule.eIsProxy()) {
			InternalEObject oldRule = (InternalEObject)rule;
			rule = (Rule)eResolveProxy(oldRule);
			if (rule != oldRule) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ConfigurationPackage.CONFIGURATION__RULE, oldRule, rule));
			}
		}
		return rule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Rule basicGetRule() {
		return rule;
	}

	protected class VariabilityPointListener extends ResourceSetListenerImpl {
		
		private ConfigurationFactory fac = ConfigurationFactory.eINSTANCE;
		
		@Override
		public void resourceSetChanged(ResourceSetChangeEvent event) {
			 super.resourceSetChanged(event);
			 for(Notification notification : event.getNotifications()) {
				 if(notification instanceof ENotificationImpl && notification.getNotifier().getClass() == RuleImpl.class && notification.getFeatureID(RuleImpl.class) == 15) {
					 if(notification.getEventType() == Notification.ADD) {
						 VariabilityPoint vp = fac.createVariabilityPoint();
						 vp.setName(notification.getNewStringValue());
						 getVariabilityPoints().add(vp);
					 } else if(notification.getEventType() == Notification.REMOVE) { 
						 for(int i = variabilityPoints.size() - 1; i >= 0; i--) {
							  if(variabilityPoints.get(i).getName().equals(notification.getOldStringValue())) {
								  variabilityPoints.remove(i);
								  break;
							  }
						 }
					 }
				 }
			 }
		}
	}

	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRule(Rule newRule) {
		Rule oldRule = rule;
		rule = newRule;
		TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(rule);
		domain.addResourceSetListener(new VariabilityPointListener());
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigurationPackage.CONFIGURATION__RULE, oldRule, rule));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ConfigurationPackage.CONFIGURATION__RULE:
				if (resolve) return getRule();
				return basicGetRule();
			case ConfigurationPackage.CONFIGURATION__VARIABILITY_POINTS:
				return getVariabilityPoints();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ConfigurationPackage.CONFIGURATION__RULE:
				setRule((Rule)newValue);
				return;
			case ConfigurationPackage.CONFIGURATION__VARIABILITY_POINTS:
				getVariabilityPoints().clear();
				getVariabilityPoints().addAll((Collection<? extends VariabilityPoint>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ConfigurationPackage.CONFIGURATION__RULE:
				setRule((Rule)null);
				return;
			case ConfigurationPackage.CONFIGURATION__VARIABILITY_POINTS:
				getVariabilityPoints().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ConfigurationPackage.CONFIGURATION__RULE:
				return rule != null;
			case ConfigurationPackage.CONFIGURATION__VARIABILITY_POINTS:
				return variabilityPoints != null && !variabilityPoints.isEmpty();
		}
		return super.eIsSet(featureID);
	}
	
	@Override
	public String toPresenceCondition() {
		String expression = rule.getFeatureModel();
		
		for(VariabilityPoint vp : variabilityPoints) {
			if(vp.getBinding() == VariabilityPointBinding.TRUE) {
				expression += " and ";
				expression += "def(" + vp.getName() + ")";
			} else if(vp.getBinding() == VariabilityPointBinding.FALSE) {
				expression += " and ";
				expression += "!(def(" + vp.getName() + "))";
			}
		}
		System.out.println(expression);
		
		return expression;
	}
} //ConfigurationImpl
