package edu.ncist.wang.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

/*
 * 功能：验证码生成类，生成每次访问使用的验证码
 * 
 */

public class VerifyCode {
	
	//生成四位随机数字串
	public String randomString(){
		
		Random rnd = new Random();
		int randomNumber = 10000 + rnd.nextInt(10000);
		
		String randomString = "" + randomNumber;
		
		return randomString.substring(1);
	}

	public String imageOut(OutputStream out, int width, int height) throws IOException{
		
		String code = randomString();
		
		BufferedImage img = null;
		
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D graph = (Graphics2D)img.getGraphics();
		graph.setColor(Color.WHITE);
		
		graph.fillRect(0, 0, width, height);
		
		Font codeFont = new Font("Tahoma", Font.BOLD, height *3 /4);
		graph.setFont(codeFont);
		graph.setColor(Color.BLACK);
		
		for(int i =0; i< code.length(); i++){
			String displayChar = "" + code.charAt(i);
			
			graph.setColor(getRandomColor());
			graph.drawString(displayChar, 14 * i + 6, height *4 / 5);
		}
		
		graph.dispose();
		
		ImageIO.write(img, "jpg", out);
		
		return code;
	}
	
	private Color getRandomColor(){
		
		Random rnd = new Random();
		
		Color returnColor = new Color(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255));
		
		return returnColor;
	}
}
