/*
 * Mandelbrot.java
 * 
 * Copyright 2015  <Daniel Grimshaw>
 * 
 * Program to draw the Mandelbrot Set
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * 
 */

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JComponent;

public class Mandelbrot extends JComponent {
	private static final double MAX_ITERATION = 1000.0;
	private final int maxrow, maxcol;
	private final boolean debug;
	
	public Mandelbrot(Graphics g, int rows, int cols) {
		this(g, rows, cols, false);
	}
	
	public Mandelbrot(Graphics g, int rows, int cols, boolean debug) {
		this.debug = debug;
		this.maxrow = rows;
		this.maxcol = cols;
		
		this.paint(g);
	}
	
	public void paint(Graphics g) {	
		if (debug)
			System.out.println("Painting...");
		int row, col; //col is column, not color
		
		double pMax = 1.0, pMin = -2.5, qMax = 1.0, qMin = -1.0;
		double deltaP = (pMax - pMin)/maxcol;
		double deltaQ = (qMax - qMin)/maxrow;
		
		for (col = 0; col < maxcol; col++) {
			for (row = 0; row < maxrow; row++) {
				double p = pMin + col*deltaP; // Scaled x-coordinate of the point
				double q = qMin + row*deltaQ; // Scaled y-coordinate of the point
				double x = 0.0, y = 0.0, iteration = 0.0;
				
				//2^16 is a reasonable bailout value
				while (x*x + y*y < (1<<16) && iteration < MAX_ITERATION) {
					double xTemp = x*x - y*y + p;
					y = 2*x*y + q;
					x = xTemp;
					iteration += 1;
				}
				
				if (iteration < MAX_ITERATION) {
					double zn = Math.sqrt(x*x + y*y);
					double nu = Math.log(Math.log(zn)/Math.log(2)) / Math.log(2);
					// Rearranging the potential function.
					// Could remove the sqrt and multiply log(zn) by 1/2, but less clear.
					// Dividing log(zn) by log(2) instead of log(N = 1<<8)
					// because I want the entire palette to range from the
					// center to radius 2, NOT the bailout radius.
					iteration += 1-nu;
				}
				
				double color1 = iteration;
				double color2 = iteration + 1;
				// iteration % 1 = fractional part of iteration
				double color = linearInterpolate(color1, color2, iteration % 1);
				
				putPixel(g, col, row, Color.HSBtoRGB(0.8F - 0.5F*(float)(color/MAX_ITERATION), 0.6F, 0.6F)); // 0.68F, 1.0F, 0.68F - 0.68F*(float)(color/MAX_ITERATION) 
			}
		}
	}
	
	private double linearInterpolate(double v0, double v1, double t) {
		// This is precise
		return (1-t)*v0 + t*v1;
	}
	
	public void putPixel(Graphics g, int col, int row, int color) {
		if (col > maxcol)
			throw new IllegalArgumentException("Column value is too large");
		if (row > maxrow)
			throw new IllegalArgumentException("Row value is too large");
		g.setColor(new Color(color));
		g.drawLine(col,row,col,row);
	}
		
	public static void main (String [] args) {
		//int frameWidth = 640;
		//int frameHeight = 480;
		
		JFrame frame = new JFrame("Mandelbrot Set On The Imaginary Plane");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setSize(frame.getWidth(), frame.getHeight());
		//frame.setUndecorated(true);
		frame.pack();
		
		frame.setVisible(true);
		frame.getContentPane().add(new Mandelbrot(frame.getGraphics(), frame.getContentPane().getHeight(), frame.getContentPane().getWidth(), true));
	}
}
