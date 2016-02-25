/**
 */
package configuration;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see configuration.ConfigurationFactory
 * @model kind="package"
 * @generated
 */
public interface ConfigurationPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "configuration";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/emf/2011/Henshin/configuration";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "configuration";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ConfigurationPackage eINSTANCE = configuration.impl.ConfigurationPackageImpl.init();

	/**
	 * The meta object id for the '{@link configuration.impl.VariabilityPointImpl <em>Variability Point</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see configuration.impl.VariabilityPointImpl
	 * @see configuration.impl.ConfigurationPackageImpl#getVariabilityPoint()
	 * @generated
	 */
	int VARIABILITY_POINT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABILITY_POINT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Binding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABILITY_POINT__BINDING = 1;

	/**
	 * The number of structural features of the '<em>Variability Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABILITY_POINT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Variability Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABILITY_POINT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link configuration.impl.ConfigurationImpl <em>Configuration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see configuration.impl.ConfigurationImpl
	 * @see configuration.impl.ConfigurationPackageImpl#getConfiguration()
	 * @generated
	 */
	int CONFIGURATION = 1;

	/**
	 * The feature id for the '<em><b>Rule</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION__RULE = 0;

	/**
	 * The feature id for the '<em><b>Variability Points</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION__VARIABILITY_POINTS = 1;

	/**
	 * The number of structural features of the '<em>Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link configuration.impl.FavoriteImpl <em>Favorite</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see configuration.impl.FavoriteImpl
	 * @see configuration.impl.ConfigurationPackageImpl#getFavorite()
	 * @generated
	 */
	int FAVORITE = 2;

	/**
	 * The feature id for the '<em><b>Rule</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAVORITE__RULE = CONFIGURATION__RULE;

	/**
	 * The feature id for the '<em><b>Variability Points</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAVORITE__VARIABILITY_POINTS = CONFIGURATION__VARIABILITY_POINTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAVORITE__NAME = CONFIGURATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Favorite</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAVORITE_FEATURE_COUNT = CONFIGURATION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Favorite</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAVORITE_OPERATION_COUNT = CONFIGURATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link configuration.VariabilityPointBinding <em>Variability Point Binding</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see configuration.VariabilityPointBinding
	 * @see configuration.impl.ConfigurationPackageImpl#getVariabilityPointBinding()
	 * @generated
	 */
	int VARIABILITY_POINT_BINDING = 3;

	/**
	 * Returns the meta object for class '{@link configuration.VariabilityPoint <em>Variability Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variability Point</em>'.
	 * @see configuration.VariabilityPoint
	 * @generated
	 */
	EClass getVariabilityPoint();

	/**
	 * Returns the meta object for the attribute '{@link configuration.VariabilityPoint#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see configuration.VariabilityPoint#getName()
	 * @see #getVariabilityPoint()
	 * @generated
	 */
	EAttribute getVariabilityPoint_Name();

	/**
	 * Returns the meta object for the attribute '{@link configuration.VariabilityPoint#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Binding</em>'.
	 * @see configuration.VariabilityPoint#getBinding()
	 * @see #getVariabilityPoint()
	 * @generated
	 */
	EAttribute getVariabilityPoint_Binding();

	/**
	 * Returns the meta object for class '{@link configuration.Configuration <em>Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Configuration</em>'.
	 * @see configuration.Configuration
	 * @generated
	 */
	EClass getConfiguration();

	/**
	 * Returns the meta object for the reference list '{@link configuration.Configuration#getVariabilityPoints <em>Variability Points</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Variability Points</em>'.
	 * @see configuration.Configuration#getVariabilityPoints()
	 * @see #getConfiguration()
	 * @generated
	 */
	EReference getConfiguration_VariabilityPoints();

	/**
	 * Returns the meta object for class '{@link configuration.Favorite <em>Favorite</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Favorite</em>'.
	 * @see configuration.Favorite
	 * @generated
	 */
	EClass getFavorite();

	/**
	 * Returns the meta object for the attribute '{@link configuration.Favorite#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see configuration.Favorite#getName()
	 * @see #getFavorite()
	 * @generated
	 */
	EAttribute getFavorite_Name();

	/**
	 * Returns the meta object for enum '{@link configuration.VariabilityPointBinding <em>Variability Point Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Variability Point Binding</em>'.
	 * @see configuration.VariabilityPointBinding
	 * @generated
	 */
	EEnum getVariabilityPointBinding();

	/**
	 * Returns the meta object for the reference '{@link configuration.Configuration#getRule <em>Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Rule</em>'.
	 * @see configuration.Configuration#getRule()
	 * @see #getConfiguration()
	 * @generated
	 */
	EReference getConfiguration_Rule();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ConfigurationFactory getConfigurationFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link configuration.impl.VariabilityPointImpl <em>Variability Point</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see configuration.impl.VariabilityPointImpl
		 * @see configuration.impl.ConfigurationPackageImpl#getVariabilityPoint()
		 * @generated
		 */
		EClass VARIABILITY_POINT = eINSTANCE.getVariabilityPoint();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABILITY_POINT__NAME = eINSTANCE.getVariabilityPoint_Name();

		/**
		 * The meta object literal for the '<em><b>Binding</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABILITY_POINT__BINDING = eINSTANCE.getVariabilityPoint_Binding();

		/**
		 * The meta object literal for the '{@link configuration.impl.ConfigurationImpl <em>Configuration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see configuration.impl.ConfigurationImpl
		 * @see configuration.impl.ConfigurationPackageImpl#getConfiguration()
		 * @generated
		 */
		EClass CONFIGURATION = eINSTANCE.getConfiguration();

		/**
		 * The meta object literal for the '<em><b>Variability Points</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONFIGURATION__VARIABILITY_POINTS = eINSTANCE.getConfiguration_VariabilityPoints();

		/**
		 * The meta object literal for the '{@link configuration.impl.FavoriteImpl <em>Favorite</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see configuration.impl.FavoriteImpl
		 * @see configuration.impl.ConfigurationPackageImpl#getFavorite()
		 * @generated
		 */
		EClass FAVORITE = eINSTANCE.getFavorite();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FAVORITE__NAME = eINSTANCE.getFavorite_Name();

		/**
		 * The meta object literal for the '{@link configuration.VariabilityPointBinding <em>Variability Point Binding</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see configuration.VariabilityPointBinding
		 * @see configuration.impl.ConfigurationPackageImpl#getVariabilityPointBinding()
		 * @generated
		 */
		EEnum VARIABILITY_POINT_BINDING = eINSTANCE.getVariabilityPointBinding();

		/**
		 * The meta object literal for the '<em><b>Rule</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONFIGURATION__RULE = eINSTANCE.getConfiguration_Rule();

	}

} //ConfigurationPackage
