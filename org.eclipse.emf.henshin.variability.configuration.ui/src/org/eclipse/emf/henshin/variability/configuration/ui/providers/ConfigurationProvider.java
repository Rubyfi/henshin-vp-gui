package org.eclipse.emf.henshin.variability.configuration.ui.providers;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.henshin.model.Rule;

import configuration.Configuration;
import configuration.Favorite;
import configuration.ConfigurationFactory;
import configuration.VariabilityPoint;
import configuration.impl.ConfigurationFactoryImpl;

public class ConfigurationProvider {

	private final static String REGEX = "def\\((.*?)\\)";

	private Map<Rule, Configuration> currentConfigurations = new HashMap<Rule, Configuration>();
	private Map<Rule, Set<Favorite>> favoriteConfigurations = new HashMap<Rule, Set<Favorite>>();
	private static ConfigurationProvider vpProvider = new ConfigurationProvider();
	ConfigurationFactory fac = ConfigurationFactoryImpl.init();

	public static ConfigurationProvider getInstance() {
		return vpProvider;
	}

	public Configuration getConfiguration(Rule rule) {
		Configuration result = null;

		if (currentConfigurations.containsKey(rule)) {
			result = currentConfigurations.get(rule);
		} else {
			result = createConfiguration(rule);
		}

		return result;
	}
	
	public Configuration getConfiguration(Favorite favorite) {
		Configuration result = null;
		
		result = fac.createConfiguration(favorite);
		result.setRule(favorite.getRule());
		currentConfigurations.put(result.getRule(), result);

		return result;
	}

	public Favorite addConfigurationToFavorites(Rule rule, String name, Configuration configuration) {
		if (favoriteConfigurations.get(rule) == null) {
			favoriteConfigurations.put(rule, new LinkedHashSet<Favorite>());
		}
		Favorite favorite = fac.createFavorite(configuration);
		favorite.setRule(rule);
		favorite.setName(name);

		favoriteConfigurations.get(rule).add(favorite);
		return favorite;
	}
	
	private Favorite findMatchingFavorite(Configuration configuration) {
		Favorite result = null;
		Set<Favorite> ruleFavorites = favoriteConfigurations.get(configuration.getRule());

		if (ruleFavorites != null) {
			for (Favorite favorite : ruleFavorites) {
				if (favorite.getVariabilityPoints().size() == configuration.getVariabilityPoints().size()) {
					int matches = 0;
					 /* 
					  * TODO: This needs to be improved
					  */
					for (int i = favorite.getVariabilityPoints().size() - 1; i >= 0; i--) {
						VariabilityPoint favVP = favorite.getVariabilityPoints().get(i);
						VariabilityPoint conVP = configuration.getVariabilityPoints().get(i);

						if (conVP.getName().equals(favVP.getName()) && conVP.getState() == favVP.getState()) {
							matches++;
						}
					}
					if (matches == configuration.getVariabilityPoints().size()) {
						return favorite;
					}
				}
			}
		}
		return null;
	}
	
	public void removeConfigurationFromFavorites(Configuration configuration) {
		Favorite favorite = findMatchingFavorite(configuration);
		
		if(favorite != null) {
			favoriteConfigurations.get(configuration.getRule()).remove(favorite);
		}
	}

	public boolean isFavorite(Configuration configuration) {
		return findMatchingFavorite(configuration) != null;
	}

	public void addFavorites(Rule rule, Set<Favorite> favoritesSet) {
		favoriteConfigurations.put(rule, favoritesSet);
	}

	public Set<Favorite> getFavorites(Rule rule) {
		return favoriteConfigurations.get(rule);
	}

	private Configuration createConfiguration(Rule rule) {
		Configuration result = null;

		if (rule != null) {
			ConfigurationFactory fac = ConfigurationFactoryImpl.init();
			result = fac.createConfiguration();
			result.setRule(rule);
			String featureModel = rule.getFeatureModel();
			EList<VariabilityPoint> variabilityPoints = result.getVariabilityPoints();

			if (featureModel != null && !featureModel.isEmpty()) {
				Matcher match = Pattern.compile(REGEX).matcher(featureModel);
				while (match.find()) {
					for (int i = 1; i <= match.groupCount(); i++) {
						VariabilityPoint vp = fac.createVariabilityPoint();
						vp.setName(match.group(i));
						variabilityPoints.add(vp);
					}
				}
			}
			currentConfigurations.put(rule, result);
		}

		return result;
	}
}
