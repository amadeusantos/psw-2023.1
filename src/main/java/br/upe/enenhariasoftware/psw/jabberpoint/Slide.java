/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software
 * Disciplina de Projeto de Software - 2023.1
 * 
 * Licensed under the Apache License, Version 2.0
 * https://www.apache.org/licenses/LICENSE-2.0
 * 
 * @author Ian F. Darwin, Helaine Lins
 */
package br.upe.enenhariasoftware.psw.jabberpoint;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Vector;

public class Slide {

	private static final long serialVersionUID = 1905122041950251207L;
	public final static int WIDTH = 1200;
	public final static int HEIGHT = 800;

	protected TextItem title;
	protected Vector<SlideItem> items;

	public Slide() {
		items = new Vector<SlideItem>();
	}

	public void append(SlideItem anItem) {
		items.addElement(anItem);
	}

	public String getTitle() {
		return title.getText();
	}

	public void setTitle(String newTitle) {
		title = new TextItem(0, newTitle);
	}

	public void append(int level, String message) {
		append(new TextItem(level, message));
	}

	public SlideItem getSlideItem(int number) {
		return (SlideItem) items.elementAt(number);
	}

	public Vector<SlideItem> getSlideItems() {
		return items;
	}

	public int getSize() {
		return items.size();
	}

	public void draw(Graphics g, Rectangle area, ImageObserver view) {
		float scale = getScale(area);

		int y = area.y;

		SlideItem slideItem = this.title;
		Style style = Style.getStyle(slideItem.getLevel());
		slideItem.draw(area.x, y, scale, g, style, view);

		y += slideItem.getBoundingBox(g, view, scale, style).height;

		for (int number = 0; number < getSize(); number++) {
			slideItem = (SlideItem) getSlideItems().elementAt(number);

			style = Style.getStyle(slideItem.getLevel());
			slideItem.draw(area.x, y, scale, g, style, view);

			y += slideItem.getBoundingBox(g, view, scale, style).height;
		}
	}

	private float getScale(Rectangle area) {
		return Math.min(((float) area.width) / ((float) WIDTH), ((float) area.height) / ((float) HEIGHT));
	}
}
