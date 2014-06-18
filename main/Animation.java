package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animation {

	private int speed;
	private int index = 0;
	private int count = 0;
        private int imageNumber = 0;
        private int tempNumber = 0;
	private double imageHeight = 0;
        private double imageWidth = 0;
        
        private BufferedImage img;
        public Animation(int speed, BufferedImage img, double imageHeight, double imageWidth, int imageNumber, int tempNumber) {
            this.speed = speed;
            this.img = img;
            this.imageHeight = imageHeight; //Height of the image by pixel
            this.imageWidth = imageWidth;   //Width of the single animation image by pixel
            this.imageNumber = imageNumber; //The number of animation for the image strip
            this.tempNumber = tempNumber;   //The actual image we will be showing starting with tempNumber
        }
        public Animation(int speed, BufferedImage img, double imageHeight, double imageWidth, int imageNumber){
            this.speed = speed;
            this.img = img;
            this.imageHeight = imageHeight; //Height of the image by pixel
            this.imageWidth = imageWidth;   //Width of the single animation image by pixel
            this.imageNumber = imageNumber; //The number of animation for the image strip
            this.tempNumber = 0;            //The actual image we will be showing starting with 0
        }
	
	public void runAnimation(){
		index++;
		if(index > speed){
			index = 0;
			tempNumber++;  //the image number we're actually looking at
		}
        if (tempNumber >= imageNumber) {
            tempNumber = 0;
        }
	}
	public void selectAnimationForBullet(){ // this source code for selecting the one of the pictures rather than looping the pictures.
		index++;
		//if(index> speed){
			index = 0;
		//}if ()
	}
	
	public void drawAnimation(Graphics g, double x, double y){
		g.drawImage(img, (int)x, (int)y, (int)x+(int)imageWidth, (int)y+(int)imageHeight, (int)imageWidth*tempNumber, 0, (int)imageWidth*tempNumber + (int)imageWidth, (int)imageHeight, null);
	}   //drawImage(image, x-coordinate where the image is placed, y-coordinate where the image is placed,
            //left-bound of where the image ends, right-bound of where the image ends,
            //left-bound of image, lower-bound of the image, right-bound of image, upper-bound of image, null);
	
	public void setCount(int count){
		this.count = count;
	}
	public int getCount(){
		return count;
	}
	public int getSpeed(){
		return speed;
	}
	public void setSpeed(int speed){
		this.speed = speed;
	}
	public void setImage(BufferedImage img){
		this.img = img;
	}
	public BufferedImage getImage(){
		return this.img;
	}
	
}
