package org.eclipse.emf.henshin.variability.configuration.ui.helpers;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

public class ShapeAlphaConcealingStrategy extends AbstractConcealingStrategy {

	@Override
	public void doReveal(AbstractGraphicalEditPart abstractEditPart) {
		IFigure figure = getFigure(abstractEditPart);
		if(figure instanceof Shape) {
			((Shape) figure).setAlpha(255);
			for(Object o : figure.getChildren()) {
				if(o instanceof Shape) {
					((Shape) o).setAlpha(255);
				}
			}
		} else {
			figure.setForegroundColor(ColorHelper.getRevealColor(figure.getForegroundColor()));
		}
	}

	@Override
	public void doConceal(AbstractGraphicalEditPart abstractEditPart) {
		IFigure figure = getFigure(abstractEditPart);
		if(figure instanceof Shape) {
			((Shape) figure).setAlpha(25);	
			for(Object o : figure.getChildren()) {
				if(o instanceof Shape) {
					((Shape) o).setAlpha(25);
				}
			}
		} else {
			figure.setForegroundColor(ColorHelper.getConcealColor(figure.getForegroundColor()));
		}
	}
}
