package de.montessori.pushygame.actors;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import de.montessori.gameengine.Actor;

public class Target extends Actor {

	public Target(Image image, double x, double y) {
		super(image, x, y);
	}

	@Override
	public void act() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean collide(Point2D targetPoint) {
		return super.collide(targetPoint);
	}

}
