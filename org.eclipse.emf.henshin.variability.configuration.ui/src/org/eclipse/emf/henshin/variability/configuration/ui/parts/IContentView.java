package org.eclipse.emf.henshin.variability.configuration.ui.parts;

public interface IContentView <E> {
	
	public void setContent(E newContent);
	
	public E getContent();

}
