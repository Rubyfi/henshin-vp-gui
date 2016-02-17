package org.eclipse.emf.henshin.variability.configuration.ui.helpers;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

public class FigureDisableConcealingStrategy extends AbstractConcealingStrategy {

	@Override
	public void doReveal(AbstractGraphicalEditPart abstractEditPart) {
		setEnabled(abstractEditPart, true);
	}

	@Override
	public void doConceal(AbstractGraphicalEditPart abstractEditPart) {
		setEnabled(abstractEditPart, false);
	}

	private void setEnabled(AbstractGraphicalEditPart abstractEditPart, boolean enabled) {
		IFigure figure = getFigure(abstractEditPart);
		figure.setEnabled(false);
		figure.invalidate();
		abstractEditPart.refresh();
	}
}
