/**
 */
package configuration;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.henshin.model.Rule;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link configuration.Configuration#getRule <em>Rule</em>}</li>
 *   <li>{@link configuration.Configuration#getVariabilityPoints <em>Variability Points</em>}</li>
 * </ul>
 *
 * @see configuration.ConfigurationPackage#getConfiguration()
 * @model
 * @generated
 */
public interface Configuration extends EObject {
	/**
	 * Returns the value of the '<em><b>Variability Points</b></em>' reference list.
	 * The list contents are of type {@link configuration.VariabilityPoint}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variability Points</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variability Points</em>' reference list.
	 * @see configuration.ConfigurationPackage#getConfiguration_VariabilityPoints()
	 * @model
	 * @generated
	 */
	EList<VariabilityPoint> getVariabilityPoints();

	/**
	 * Returns the value of the '<em><b>Rule</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rule</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rule</em>' reference.
	 * @see #setRule(Rule)
	 * @see configuration.ConfigurationPackage#getConfiguration_Rule()
	 * @model required="true"
	 * @generated
	 */
	Rule getRule();

	/**
	 * Sets the value of the '{@link configuration.Configuration#getRule <em>Rule</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rule</em>' reference.
	 * @see #getRule()
	 * @generated
	 */
	void setRule(Rule value);
	
	/**
	 * @generated NOT
	 */
	String toPresenceCondition();

} // Configuration
