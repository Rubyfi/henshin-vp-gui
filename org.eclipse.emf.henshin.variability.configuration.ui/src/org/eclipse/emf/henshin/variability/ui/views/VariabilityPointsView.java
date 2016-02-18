package org.eclipse.emf.henshin.variability.ui.views;

import java.util.Set;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.henshin.diagram.edit.parts.NodeCompartmentEditPart;
import org.eclipse.emf.henshin.diagram.edit.parts.RuleEditPart;
import org.eclipse.emf.henshin.diagram.edit.policies.NodeCompartmentItemSemanticEditPolicy;
import org.eclipse.emf.henshin.diagram.edit.policies.RuleCompartmentItemSemanticEditPolicy;
import org.eclipse.emf.henshin.model.HenshinPackage;
import org.eclipse.emf.henshin.model.Rule;
import org.eclipse.emf.henshin.variability.configuration.ui.actions.LoadFavoriteConfigurationAction;
import org.eclipse.emf.henshin.variability.configuration.ui.controls.DropDownMenuAction;
import org.eclipse.emf.henshin.variability.configuration.ui.dialogs.FavoriteConfigurationNameDialog;
import org.eclipse.emf.henshin.variability.configuration.ui.helpers.CreationMode;
import org.eclipse.emf.henshin.variability.configuration.ui.helpers.FigureVisibilityConcealingStrategy;
import org.eclipse.emf.henshin.variability.configuration.ui.helpers.ImageHelper;
import org.eclipse.emf.henshin.variability.configuration.ui.helpers.RuleEditPartVisibilityHelper;
import org.eclipse.emf.henshin.variability.configuration.ui.helpers.ShapeAlphaConcealingStrategy;
import org.eclipse.emf.henshin.variability.configuration.ui.helpers.VariabilityModelHelper;
import org.eclipse.emf.henshin.variability.configuration.ui.parts.IContentView;
import org.eclipse.emf.henshin.variability.configuration.ui.parts.ILinkedWithEditorView;
import org.eclipse.emf.henshin.variability.configuration.ui.parts.ITableViewerSynchronizedPart;
import org.eclipse.emf.henshin.variability.configuration.ui.parts.LinkWithEditorSelectionListener;
import org.eclipse.emf.henshin.variability.configuration.ui.parts.SynchronizedTableViewer;
import org.eclipse.emf.henshin.variability.configuration.ui.policies.NodeCompartmentItemVariabilityEditPolicy;
import org.eclipse.emf.henshin.variability.configuration.ui.policies.RuleCompartmenItemVariabilityEditPolicy;
import org.eclipse.emf.henshin.variability.configuration.ui.providers.ConfigurationProvider;
import org.eclipse.emf.henshin.variability.ui.viewer.util.VPViewerComparator;
import org.eclipse.emf.henshin.variability.ui.viewer.util.VPViewerContentProvider;
import org.eclipse.emf.henshin.variability.ui.viewer.util.VPViewerNameEditingSupport;
import org.eclipse.emf.henshin.variability.ui.viewer.util.VPViewerStateEditingSupport;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import configuration.Configuration;
import configuration.Favorite;
import configuration.VariabilityPoint;

public class VariabilityPointsView extends ViewPart
		implements ILinkedWithEditorView, IContentView<Configuration>, ITableViewerSynchronizedPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.eclipse.emf.henshin.variability.ui.views.VariabilityPointsView";

	private SynchronizedTableViewer viewer;
	private Action showBaseRuleAction, showConfiguredRuleAction, showFullRuleAction, linkWithEditorAction,
			fadeConcealingAction, visibilityConcealingAction, linkToViewingMode, createInBase, createInConfiguration;
	private DropDownMenuAction loadFavoritesMenu, elementCreationMenu;
	private LinkWithEditorSelectionListener linkWithEditorSelectionListener = new LinkWithEditorSelectionListener(this);
	private boolean linkingActive;
	private Label ruleNameLabel;
	private Text variabilityModelText;
	private VPViewerComparator comparator;
	private ConfigurationProvider configurationProvider = ConfigurationProvider.getInstance();
	private WritableValue writableValue;
	private CreationMode creationMode = CreationMode.BASE;
	private Configuration config;

	private RuleEditPart selectedRuleEditPart;

	public RuleEditPart getSelectedRuleEditPart() {
		return selectedRuleEditPart;
	}

	public void setSelectedRuleEditPart(RuleEditPart selectedRuleEditPart) {
		this.selectedRuleEditPart = selectedRuleEditPart;
	}

	public VariabilityPointsView() {
		super();
	}

	/**
	 * @see IViewPart.init(IViewSite)
	 */
	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
	}

	private Composite createViewer(Composite parent) {
		Composite tableComposite = new Composite(parent, SWT.NONE);
		TableColumnLayout tableColumnLayout = new TableColumnLayout();
		tableComposite.setLayout(tableColumnLayout);

		viewer = new SynchronizedTableViewer(tableComposite,
				SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER, this);
		createColumns(tableComposite, tableColumnLayout, viewer);
		final Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		viewer.setContentProvider(new VPViewerContentProvider());
		viewer.setInput(config);

		getSite().setSelectionProvider(viewer);

		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 2;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		viewer.getControl().setLayoutData(gridData);

		comparator = new VPViewerComparator();
		viewer.setComparator(comparator);

		return tableComposite;
	}

	private void createColumns(final Composite parent, final TableColumnLayout tableColumnLayout,
			final TableViewer viewer) {
		String[] titles = { "Variability Point", "State" };

		TableViewerColumn col = createTableViewerColumn(titles[0], 0);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				VariabilityPoint vp = (VariabilityPoint) element;
				return vp.getName();
			}
		});
		col.setEditingSupport(new VPViewerNameEditingSupport(viewer));
		tableColumnLayout.setColumnData(col.getColumn(), new ColumnWeightData(60, false));

		col = createTableViewerColumn(titles[1], 1);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				VariabilityPoint vp = (VariabilityPoint) element;
				return vp.getState().getName();
			}
		});
		col.setEditingSupport(new VPViewerStateEditingSupport(viewer));
		tableColumnLayout.setColumnData(col.getColumn(), new ColumnWeightData(40, false));
	}

	private TableViewerColumn createTableViewerColumn(String title, int index) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE, index);
		final TableColumn column = viewerColumn.getColumn();

		column.setText(title);
		column.setResizable(false);
		column.setMoveable(false);

		column.addSelectionListener(getSelectionAdapter(column, index));

		return viewerColumn;
	}

	@Override
	public void createPartControl(Composite parent) {
		GridLayout layout = new GridLayout(2, false);
		parent.setLayout(layout);

		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		gridData.horizontalSpan = 2;

		ruleNameLabel = new Label(parent, SWT.FILL);
		ruleNameLabel.setLayoutData(gridData);

		Label variabilityModelLabel = new Label(parent, SWT.NONE);
		variabilityModelLabel.setImage(ImageHelper.getImage("/icons/variability.gif"));
		variabilityModelText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		variabilityModelText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		DataBindingContext bindingContext = new DataBindingContext();
		IObservableValue target = WidgetProperties.text(SWT.Modify).observe(variabilityModelText);
		writableValue = new WritableValue();
		IObservableValue model = EMFProperties.value(HenshinPackage.Literals.RULE__FEATURE_MODEL)
				.observeDetail(writableValue);
		bindingContext.bindValue(target, model);

		Label separator = new Label(parent, SWT.HORIZONTAL | SWT.SEPARATOR);
		separator.setLayoutData(gridData);

		GridData tableCompositeGridData = new GridData();
		tableCompositeGridData.grabExcessHorizontalSpace = true;
		tableCompositeGridData.grabExcessVerticalSpace = true;
		tableCompositeGridData.horizontalAlignment = GridData.FILL;
		tableCompositeGridData.verticalAlignment = GridData.FILL;
		tableCompositeGridData.horizontalSpan = 2;

		Composite tableComposite = createViewer(parent);
		tableComposite.setLayoutData(tableCompositeGridData);

		// Create the help context id for the viewer's control
		PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(),
				"org.eclipse.emf.henshin.variability.ui.viewer");
		createActions(parent);
		createMenu();
		createToolbar();
	}
		
	private void updateEditPolicy(RuleEditPart ruleEditPart) {
		if(ruleEditPart == null) {
			return;
		}
		
		AbstractEditPart parent = (AbstractEditPart) ruleEditPart.getChildren().get(1);
		
		if(creationMode == CreationMode.CONFIGURATION || 
				(creationMode == CreationMode.SELECTION && !showBaseRuleAction.isChecked())) {
			installConfigurationEditPolicy(parent);
		} else {
			installBasePolicy(parent);
		}
	}
	
	protected void installBasePolicy(AbstractEditPart editPart) {
		editPart.installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new RuleCompartmentItemSemanticEditPolicy());

		for(Object child : editPart.getChildren()) {
			if(child instanceof NodeEditPart) {
				NodeEditPart nodeEditPart = (NodeEditPart) child;
				NodeCompartmentEditPart nodeCompartmentEditPart = (NodeCompartmentEditPart) nodeEditPart.getChildren().get(2);
				nodeCompartmentEditPart.installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new NodeCompartmentItemSemanticEditPolicy());
			}
		}
	}

	private void installConfigurationEditPolicy(AbstractEditPart editPart) {
		editPart.installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new RuleCompartmenItemVariabilityEditPolicy(config));
		
		for(Object child : editPart.getChildren()) {
			if(child instanceof NodeEditPart) {
				NodeEditPart nodeEditPart = (NodeEditPart) child;
				NodeCompartmentEditPart nodeCompartmentEditPart = (NodeCompartmentEditPart) nodeEditPart.getChildren().get(2);
				nodeCompartmentEditPart.installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new NodeCompartmentItemVariabilityEditPolicy(config));
			}
		}
	}
	
	private void createActions(Composite parent) {
		elementCreationMenu = new DropDownMenuAction("Element creation mode", parent);
		elementCreationMenu.setImageDescriptor(ImageHelper.getImageDescriptor("icons/creation_mode.gif"));
		
		linkToViewingMode = new Action("Link to viewing mode", IAction.AS_RADIO_BUTTON) {
			@Override
			public void run() {
				creationMode = CreationMode.SELECTION;
				updateEditPolicy(selectedRuleEditPart);
			}
		};
		linkToViewingMode.setImageDescriptor(ImageHelper.getImageDescriptor("icons/add_to_selection.gif"));
		
		createInBase = new Action("Create in rulebase", IAction.AS_RADIO_BUTTON) {
			@Override
			public void run() {
				creationMode = CreationMode.BASE;
				updateEditPolicy(selectedRuleEditPart);
			}
		};
		createInBase.setImageDescriptor(ImageHelper.getImageDescriptor("icons/add_to_base.gif"));
		
		createInConfiguration = new Action("Create in configuration", IAction.AS_RADIO_BUTTON) {
			@Override
			public void run() {
				creationMode = CreationMode.CONFIGURATION;
				updateEditPolicy(selectedRuleEditPart);
			}
		};
		createInConfiguration.setImageDescriptor(ImageHelper.getImageDescriptor("icons/add_to_configuration.gif"));
		
		elementCreationMenu.addActionToMenu(linkToViewingMode);
		elementCreationMenu.addActionToMenu(createInBase);
		elementCreationMenu.addActionToMenu(createInConfiguration);

		visibilityConcealingAction = new Action("Visibility", IAction.AS_RADIO_BUTTON) {
			@Override
			public void run() {
				RuleEditPartVisibilityHelper.showFullRule(selectedRuleEditPart);
				RuleEditPartVisibilityHelper.setFadingStrategy(new FigureVisibilityConcealingStrategy());
				runSelectedVisibilityAction();
			};
		};
		visibilityConcealingAction.setChecked(true);
		fadeConcealingAction = new Action("Fading", IAction.AS_RADIO_BUTTON) {
			@Override
			public void run() {
				RuleEditPartVisibilityHelper.showFullRule(selectedRuleEditPart);
				RuleEditPartVisibilityHelper.setFadingStrategy(new ShapeAlphaConcealingStrategy());
				runSelectedVisibilityAction();
			};
		};

		showBaseRuleAction = new Action("Show Base Rule", IAction.AS_RADIO_BUTTON) {
			@Override
			public void run() {
				if (isChecked()) {
					super.run();
					RuleEditPartVisibilityHelper.showBaseRule(selectedRuleEditPart);
					if(creationMode == CreationMode.SELECTION) {
						updateEditPolicy(selectedRuleEditPart);
					}
				}
			}
		};
		showBaseRuleAction.setImageDescriptor(ImageHelper.getImageDescriptor("icons/rule_base.gif"));

		showConfiguredRuleAction = new Action("Show current Configuration", IAction.AS_RADIO_BUTTON) {
			@Override
			public void run() {
				if (isChecked() && selectedRuleEditPart != null) {
					super.run();
					RuleEditPartVisibilityHelper.showConfiguredRule(selectedRuleEditPart, config,
							config.getRule().getFeatureModel());
					if(creationMode == CreationMode.SELECTION) {
						updateEditPolicy(selectedRuleEditPart);
					}
				}
			}
		};
		showConfiguredRuleAction.setImageDescriptor(ImageHelper.getImageDescriptor("icons/rule_configured.gif"));

		showFullRuleAction = new Action("Show full rule", IAction.AS_RADIO_BUTTON) {
			@Override
			public void run() {
				if (isChecked()) {
					super.run();
					RuleEditPartVisibilityHelper.showFullRule(selectedRuleEditPart);
				}
			}
		};
		showFullRuleAction.setImageDescriptor(ImageHelper.getImageDescriptor("icons/rule_full.gif"));
		showFullRuleAction.setChecked(true);

		loadFavoritesMenu = new DropDownMenuAction("Manage favorites", parent) {
			@Override
			public void runWithEvent(Event event) {
				if (config == null) {
					return;
				}

				// Star button was clicked
				if (event.detail == 0) {
					if (!configurationProvider.isFavorite(config)) {
						FavoriteConfigurationNameDialog dialog = new FavoriteConfigurationNameDialog(
								getViewSite().getShell());
						if (dialog.open() == Window.OK) {
							String name = dialog.getName();
							Favorite favorite = configurationProvider.addConfigurationToFavorites(config.getRule(),
									name, config);
							LoadFavoriteConfigurationAction loadConfigurationAction = new LoadFavoriteConfigurationAction(
									favorite, VariabilityPointsView.this);
							this.addActionToMenu(loadConfigurationAction);
							this.uncheckAll();
							loadConfigurationAction.setChecked(true);
						} else {
							return;
						}
					} else {
						configurationProvider.removeConfigurationFromFavorites(config);
						populateFavoritesDropDown(config.getRule());
					}

					setChecked(configurationProvider.isFavorite(config));
				}
			}

			@Override
			public void setChecked(boolean favorite) {
				String imagePath = favorite ? "icons/star.png" : "icons/star_grey.png";
				setImageDescriptor(ImageHelper.getImageDescriptor(imagePath));
				firePropertyChange(CHECKED, !favorite, favorite);
			}
		};
		loadFavoritesMenu.setToolTipText("Manage favorites");
		loadFavoritesMenu.setImageDescriptor(ImageHelper.getImageDescriptor("icons/star_grey.png"));

		linkWithEditorAction = new Action("Link with editor", IAction.AS_CHECK_BOX) {
			@Override
			public void run() {
				toggleLinking(isChecked());
			}
		};
		linkWithEditorAction.setImageDescriptor(ImageHelper.getImageDescriptor("icons/synchronize.gif"));
	}

	private void createMenu() {
		IMenuManager mgr = getViewSite().getActionBars().getMenuManager();
		IMenuManager subMgr = new MenuManager("Concealing strategies", ImageHelper.getImageDescriptor("icons/concealing.gif"), null);
		
		mgr.add(linkWithEditorAction);
		mgr.add(subMgr);
		subMgr.add(fadeConcealingAction);
		subMgr.add(visibilityConcealingAction);
	}

	private void createToolbar() {
		IToolBarManager mgr = getViewSite().getActionBars().getToolBarManager();

		mgr.add(elementCreationMenu);
		mgr.add(new Separator());
		mgr.add(showBaseRuleAction);
		mgr.add(showConfiguredRuleAction);
		mgr.add(showFullRuleAction);
		mgr.add(new Separator());
		mgr.add(loadFavoritesMenu);
		mgr.add(new Separator());
		mgr.add(linkWithEditorAction);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	@Override
	public void setContent(Configuration config) {
		Rule rule = config.getRule();

		viewer.setInput(config);
		ruleNameLabel.setText(rule.getName());
		loadFavoritesMenu.setChecked(configurationProvider.isFavorite(config));
		writableValue.setValue(rule);
	}

	@Override
	public Configuration getContent() {
		return config;
	}

	public void refresh() {
		viewer.refresh();
	}

	@Override
	public void editorSelectionChanged(IEditorPart activeEditor) {
		if (!this.linkingActive || !getViewSite().getPage().isPartVisible(this)) {
			return;
		}

		StructuredSelection selection = (StructuredSelection) activeEditor.getEditorSite().getSelectionProvider()
				.getSelection();
		if (selection.size() == 1 && selection.getFirstElement() instanceof RuleEditPart) {
			RuleEditPart ruleEditPart = (RuleEditPart) selection.getFirstElement();
			Rule rule = VariabilityModelHelper.getRuleForEditPart(ruleEditPart);
			config = configurationProvider.getConfiguration(rule);
			setContent(config);
			refresh();
		}

	}

	protected void toggleLinking(boolean checked) {
		this.linkingActive = checked;
		if (checked) {
			getSite().getPage().addSelectionListener(linkWithEditorSelectionListener);
			editorSelectionChanged(getSite().getPage().getActiveEditor());
		} else {
			getSite().getPage().removeSelectionListener(linkWithEditorSelectionListener);
		}
	}

	private void populateFavoritesDropDown(Rule rule) {
		loadFavoritesMenu.clearMenu();

		Set<Favorite> favorites = configurationProvider.getFavorites(rule);

		if (favorites != null) {
			for (Favorite favorite : favorites) {
				LoadFavoriteConfigurationAction loadConfigurationAction = new LoadFavoriteConfigurationAction(favorite,
						this);
				loadFavoritesMenu.addActionToMenu(loadConfigurationAction);
			}
		}
	}

	@Override
	public void selectedRuleChanged(RuleEditPart ruleEditPart) {
		if (ruleEditPart != null) {
			Rule rule = VariabilityModelHelper.getRuleForEditPart(ruleEditPart);
			config = configurationProvider.getConfiguration(rule);
			selectedRuleEditPart = ruleEditPart;
			setContent(config);
			populateFavoritesDropDown(rule);
			if (showConfiguredRuleAction.isChecked()) {
				showConfiguredRuleAction.run();
			}
			
			updateEditPolicy(ruleEditPart);

			refresh();
		}
	}

	private void runSelectedVisibilityAction() {
		if (showBaseRuleAction.isChecked()) {
			showBaseRuleAction.run();
		} else if (showConfiguredRuleAction.isChecked()) {
			showConfiguredRuleAction.run();
		}
	}

	private SelectionAdapter getSelectionAdapter(final TableColumn column, final int index) {
		SelectionAdapter selectionAdapter = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				comparator.setColumn(index);
				int sortDirection = comparator.getDirection();
				viewer.getTable().setSortDirection(sortDirection);
				viewer.getTable().setSortColumn(column);
				viewer.refresh();
			}
		};
		return selectionAdapter;
	}

	@Override
	public void tableViewerUpdated() {
		loadFavoritesMenu.setChecked(configurationProvider.isFavorite(config));
		loadFavoritesMenu.uncheckAll();
		if (showConfiguredRuleAction.isChecked()) {
			showConfiguredRuleAction.run();
		}
		refresh();
	}
}
