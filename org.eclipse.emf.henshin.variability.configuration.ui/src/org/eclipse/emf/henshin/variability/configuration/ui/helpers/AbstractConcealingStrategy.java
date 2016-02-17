package org.eclipse.emf.henshin.variability.configuration.ui.helpers;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderedNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;

public abstract class AbstractConcealingStrategy {
	
	public abstract void doReveal(AbstractGraphicalEditPart abstractEditPart);

	public abstract void doConceal(AbstractGraphicalEditPart abstractEditPart);
	
	public void apply(AbstractGraphicalEditPart abstractEditPart, boolean shouldConceal) {
		if(shouldConceal) {
			doConceal(abstractEditPart);
		} else {
			doReveal(abstractEditPart);
		}
	}
	
	protected IFigure getFigure(AbstractGraphicalEditPart abstractEditPart) {
		IFigure figure = abstractEditPart.getFigure();
		if(figure instanceof BorderedNodeFigure || figure instanceof DefaultSizeNodeFigure) {
			figure = (IFigure) figure.getChildren().get(0);
		}
		return figure;
	}
}
