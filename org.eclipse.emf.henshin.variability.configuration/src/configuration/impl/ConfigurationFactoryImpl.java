/**
 */
package configuration.impl;

import configuration.*;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ConfigurationFactoryImpl extends EFactoryImpl implements ConfigurationFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ConfigurationFactory init() {
		try {
			ConfigurationFactory theConfigurationFactory = (ConfigurationFactory)EPackage.Registry.INSTANCE.getEFactory(ConfigurationPackage.eNS_URI);
			if (theConfigurationFactory != null) {
				return theConfigurationFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ConfigurationFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConfigurationFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ConfigurationPackage.VARIABILITY_POINT: return createVariabilityPoint();
			case ConfigurationPackage.CONFIGURATION: return createConfiguration();
			case ConfigurationPackage.FAVORITE: return createFavorite();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case ConfigurationPackage.VARIABILITY_POINT_STATE:
				return createVariabilityPointStateFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case ConfigurationPackage.VARIABILITY_POINT_STATE:
				return convertVariabilityPointStateToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariabilityPoint createVariabilityPoint() {
		VariabilityPointImpl variabilityPoint = new VariabilityPointImpl();
		return variabilityPoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Configuration createConfiguration() {
		ConfigurationImpl configuration = new ConfigurationImpl();
		return configuration;
	}
	
	/**
	 * @generated NOT
	 */
	public Configuration createConfiguration(Configuration configuration) {
		Configuration result = new ConfigurationImpl();
		
		result.setRule(configuration.getRule());
		EList<VariabilityPoint> variabilityPoints = result.getVariabilityPoints();

		for (VariabilityPoint vp : configuration.getVariabilityPoints()) {
			VariabilityPoint variabilityPoint = createVariabilityPoint();
			variabilityPoint.setName(new String(vp.getName()));
			variabilityPoint.setState(vp.getState());
			variabilityPoints.add(variabilityPoint);
		}
		
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Favorite createFavorite() {
		FavoriteImpl favorite = new FavoriteImpl();
		return favorite;
	}
	
	/**
	 * @generated NOT
	 */
	public Favorite createFavorite(Configuration configuration) {
		Favorite result = new FavoriteImpl();
		
		result.setRule(configuration.getRule());
		EList<VariabilityPoint> variabilityPoints = result.getVariabilityPoints();

		for (VariabilityPoint vp : configuration.getVariabilityPoints()) {
			VariabilityPoint variabilityPoint = createVariabilityPoint();
			variabilityPoint.setName(new String(vp.getName()));
			variabilityPoint.setState(vp.getState());
			variabilityPoints.add(variabilityPoint);
		}
		
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariabilityPointState createVariabilityPointStateFromString(EDataType eDataType, String initialValue) {
		VariabilityPointState result = VariabilityPointState.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertVariabilityPointStateToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConfigurationPackage getConfigurationPackage() {
		return (ConfigurationPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ConfigurationPackage getPackage() {
		return ConfigurationPackage.eINSTANCE;
	}

} //ConfigurationFactoryImpl
