package de.montessori.gameengine;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Actor {

	private ImageView imageView;

	private double x = 0;

	private double y = 0;

	private ActorManager manager;

	public Actor(Image image, double x, double y) {
		imageView = new ImageView(image);
		imageView.setY(y);
		imageView.setX(x);
		setX(x);
		setY(y);
	}
	
	public void setImage(Image image){
		imageView.setImage(image);
	}

	public abstract void act();

	public boolean collide(Point2D targetPoint) {
		if (getX() == targetPoint.getX() && getY() == targetPoint.getY()) {
			return true;
		} else
			return false;

	}

	public void registerManager(ActorManager manager) {
		this.manager = manager;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
		imageView.setX(x);
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
		imageView.setY(y);
	}

	public Node getNode() {
		return imageView;
	}


	public ActorManager getManager() {
		return manager;
	}
	
	public void move(Point2D targetPoint){
		setY(targetPoint.getY());
		setX(targetPoint.getX());
	}

//	public void moveUp() {
//		setY(getY() - PushyWorld.ACTOR_SIZE);
//	}
//
//	public void moveDown() {
//		setY(getY() + PushyWorld.ACTOR_SIZE);
//	}
//
//	public void moveRight() {
//		setX(getX() + PushyWorld.ACTOR_SIZE);
//	}
//
//	public void moveLeft() {
//		setX(getX() - PushyWorld.ACTOR_SIZE);
//	}

}
