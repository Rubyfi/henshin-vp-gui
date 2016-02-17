/**
 */
package configuration;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variability Point</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link configuration.VariabilityPoint#getName <em>Name</em>}</li>
 *   <li>{@link configuration.VariabilityPoint#getState <em>State</em>}</li>
 * </ul>
 *
 * @see configuration.ConfigurationPackage#getVariabilityPoint()
 * @model
 * @generated
 */
public interface VariabilityPoint extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see configuration.ConfigurationPackage#getVariabilityPoint_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link configuration.VariabilityPoint#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>State</b></em>' attribute.
	 * The literals are from the enumeration {@link configuration.VariabilityPointState}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>State</em>' attribute.
	 * @see configuration.VariabilityPointState
	 * @see #setState(VariabilityPointState)
	 * @see configuration.ConfigurationPackage#getVariabilityPoint_State()
	 * @model required="true"
	 * @generated
	 */
	VariabilityPointState getState();

	/**
	 * Sets the value of the '{@link configuration.VariabilityPoint#getState <em>State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>State</em>' attribute.
	 * @see configuration.VariabilityPointState
	 * @see #getState()
	 * @generated
	 */
	void setState(VariabilityPointState value);

} // VariabilityPoint
