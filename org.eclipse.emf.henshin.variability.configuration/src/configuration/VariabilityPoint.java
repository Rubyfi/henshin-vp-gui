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
 *   <li>{@link configuration.VariabilityPoint#getBinding <em>Binding</em>}</li>
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
	 * Returns the value of the '<em><b>Binding</b></em>' attribute.
	 * The literals are from the enumeration {@link configuration.VariabilityPointBinding}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Binding</em>' attribute.
	 * @see configuration.VariabilityPointBinding
	 * @see #setBinding(VariabilityPointBinding)
	 * @see configuration.ConfigurationPackage#getVariabilityPoint_Binding()
	 * @model required="true"
	 * @generated
	 */
	VariabilityPointBinding getBinding();

	/**
	 * Sets the value of the '{@link configuration.VariabilityPoint#getBinding <em>Binding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Binding</em>' attribute.
	 * @see configuration.VariabilityPointBinding
	 * @see #getBinding()
	 * @generated
	 */
	void setBinding(VariabilityPointBinding value);

} // VariabilityPoint
