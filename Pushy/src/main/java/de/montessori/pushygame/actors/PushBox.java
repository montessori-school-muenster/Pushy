package de.montessori.pushygame.actors;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import de.montessori.gameengine.Actor;
import de.montessori.pushygame.controller.PushyActorManager;

public class PushBox extends Actor {

	private Point2D targetPoint;
	
	private Image defaultImage;
	
	private Image hitImage;
	
	private boolean onTarget;

	public PushBox(Image image, double x, double y) {
		super(image, x, y);
		defaultImage = image;
	}
	
	
	public void setHitImage(Image hitImage){
		this.hitImage = hitImage;
	}

	
	@Override
	public void act() {
		if (targetPoint != null) {
			move(targetPoint);
			targetPoint = null;
		}
		if(((PushyActorManager) getManager()).hitTarget(new Point2D(getX(), getY()))){
			setImage(hitImage);
			onTarget = true;
		} else {
			setImage(defaultImage);
			onTarget = false;
		}
	}

	
	@Override
	public boolean collide(Point2D targetPoint) {
		if (super.collide(targetPoint)) {
			return checkNeighbourCollision(targetPoint);
		}
		return false;
	}

	
	private boolean checkNeighbourCollision(Point2D targetPoint) {
		Point2D calculatedTargetPoint = ((PushyActorManager) getManager())
				.createTargetPoint(new Point2D(getX(), getY()));

		if (((PushyActorManager) getManager())
				.collideWithWall(calculatedTargetPoint)
				|| ((PushyActorManager) getManager())
						.collideWithBox(calculatedTargetPoint)) {
			return true;
		}

		System.out.println("PushBox-x: " + targetPoint.getX() + "PushBox-y: "
				+ targetPoint.getY());
		this.targetPoint = calculatedTargetPoint;
		return false;
	}
	
	
	public boolean isOnTarget(){
		return onTarget;
	}

}
